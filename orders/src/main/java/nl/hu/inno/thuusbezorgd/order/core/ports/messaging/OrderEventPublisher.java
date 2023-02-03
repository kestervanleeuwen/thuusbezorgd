package nl.hu.inno.thuusbezorgd.order.core.ports.messaging;

import nl.hu.inno.thuusbezorgd.order.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}
