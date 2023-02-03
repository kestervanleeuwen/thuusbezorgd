package nl.hu.inno.thuusbezorgd.order.infrastructure.driver.messaging;

import nl.hu.inno.thuusbezorgd.order.core.application.OrderCommandHandler;
import nl.hu.inno.thuusbezorgd.order.core.application.command.NewOrder;
import nl.hu.inno.thuusbezorgd.order.core.domain.event.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "#{'${messaging.queue.delivery-order}'}")
public class RabbitMqEventListener {
    private final OrderCommandHandler commandHandler;

    public RabbitMqEventListener(OrderCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


//    void listen(OrderEvent event) {
//        switch (event.getEventKey()) {
//            case "delivery.order.received":
//                this.commandHandler.handle(
//                        new NewOrder(event)
//                );
//                break:
//            case "delivery.order.completed":
//                this.commandHandler.handle(
//                        new NewOrder(event)
//                );
//                break:
//        }
//
//    }
}
