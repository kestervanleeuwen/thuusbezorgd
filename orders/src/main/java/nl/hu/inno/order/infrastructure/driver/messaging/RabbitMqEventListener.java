package nl.hu.inno.order.infrastructure.driver.messaging;

import com.rabbitmq.client.Channel;
import nl.hu.inno.order.core.application.OrderCommandHandler;
import nl.hu.inno.order.core.application.command.FinishOrder;
import nl.hu.inno.order.core.application.command.NewOrder;
import nl.hu.inno.order.core.application.command.ReceivedOrder;
import nl.hu.inno.order.core.domain.event.OrderEvent;
import nl.hu.inno.order.infrastructure.driver.messaging.event.DeliveryOrderEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMqEventListener {
    private final OrderCommandHandler commandHandler;

    public RabbitMqEventListener(OrderCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    @RabbitListener(queues = "#{'${messaging.queue.delivery-order}'}", ackMode = "MANUAL")
    void listen(DeliveryOrderEvent event, Message message, Channel channel) throws IOException {
        switch (event.eventKey) {
            case "delivery.order.received":
                System.out.println(event.orderId);
                System.out.println(event.deliveryId);
                this.commandHandler.handle(
                        new ReceivedOrder(Long.parseLong(event.orderId), Long.parseLong(event.deliveryId))
                );
                break;
            case "delivery.order.completed":
                this.commandHandler.handle(
                        new FinishOrder(Long.parseLong(event.orderId))
                );
                break;
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }
}
