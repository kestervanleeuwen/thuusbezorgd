package nl.hu.inno.stock.infrastructure.driver.messaging;

import com.rabbitmq.client.Channel;
import nl.hu.inno.stock.core.application.DishCommandHandler;
import nl.hu.inno.stock.core.application.command.PrepareDishes;
import nl.hu.inno.stock.infrastructure.driver.messaging.event.StockPrepareEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMqEventListener {
    private final DishCommandHandler dishCommandHandler;

    public RabbitMqEventListener(DishCommandHandler dishCommandHandler) {
        this.dishCommandHandler = dishCommandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.order-stock}'}", ackMode = "MANUAL")
    void listen(StockPrepareEvent event, Message message, Channel channel) throws IOException {
        dishCommandHandler.handle(new PrepareDishes(event.dishIds));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }
}
