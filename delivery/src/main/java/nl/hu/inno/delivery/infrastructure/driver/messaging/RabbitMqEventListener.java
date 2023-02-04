package nl.hu.inno.delivery.infrastructure.driver.messaging;

import nl.hu.inno.delivery.core.application.DeliveryCommandHandler;
import nl.hu.inno.delivery.core.application.command.NewDelivery;
import nl.hu.inno.delivery.infrastructure.driver.messaging.event.OrderDeliveryEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "#{'${messaging.queue.order-delivery}'}")
public class RabbitMqEventListener {
    private final DeliveryCommandHandler commandHandler;

    public RabbitMqEventListener(DeliveryCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    void listen(OrderDeliveryEvent event) {
        switch (event.eventKey) {
            case "order.delivery.created":
                this.commandHandler.handle(new NewDelivery(event.eventId));
                break;
        }

    }
}
