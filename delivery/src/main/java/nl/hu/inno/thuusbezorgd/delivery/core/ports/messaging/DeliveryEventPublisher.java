package nl.hu.inno.thuusbezorgd.delivery.core.ports.messaging;

import nl.hu.inno.thuusbezorgd.delivery.core.domain.event.DeliveryEvent;

public interface DeliveryEventPublisher {
    void publish(DeliveryEvent event);
}
