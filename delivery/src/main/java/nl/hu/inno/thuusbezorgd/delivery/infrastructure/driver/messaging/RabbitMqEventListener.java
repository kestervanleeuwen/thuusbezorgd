package nl.hu.inno.thuusbezorgd.delivery.infrastructure.driver.messaging;

import nl.hu.inno.thuusbezorgd.delivery.core.application.DeliveryCommandHandler;
import nl.hu.inno.thuusbezorgd.delivery.core.application.command.NewDelivery;
import nl.hu.inno.thuusbezorgd.delivery.infrastructure.driver.messaging.event.OrderDeliveryEvent;
import nl.hu.inno.thuusbezorgd.order.core.application.OrderCommandHandler;
import nl.hu.inno.thuusbezorgd.order.core.domain.event.OrderEvent;
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
