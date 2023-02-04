package nl.hu.inno.order.core.ports.messaging;

import nl.hu.inno.order.core.domain.event.OrderEvent;
import nl.hu.inno.order.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}
