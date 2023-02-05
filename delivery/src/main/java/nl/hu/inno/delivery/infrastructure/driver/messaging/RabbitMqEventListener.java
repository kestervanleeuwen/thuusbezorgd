package nl.hu.inno.delivery.infrastructure.driver.messaging;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import nl.hu.inno.delivery.core.application.DeliveryCommandHandler;
import nl.hu.inno.delivery.core.application.command.NewDelivery;
import nl.hu.inno.delivery.infrastructure.driver.messaging.event.OrderDeliveryEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMqEventListener {
    private final DeliveryCommandHandler commandHandler;

    public RabbitMqEventListener(DeliveryCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.order-delivery}'}", ackMode = "MANUAL")
    void listen(OrderDeliveryEvent event, Message message, Channel channel) throws IOException {
        System.out.println("log arrived");
        switch (event.eventKey) {
            case "order.delivery.created":
                this.commandHandler.handle(new NewDelivery(Long.parseLong(event.orderId)));
                break;
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
