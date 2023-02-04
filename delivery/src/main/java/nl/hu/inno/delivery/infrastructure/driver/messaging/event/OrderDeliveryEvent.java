package nl.hu.inno.delivery.infrastructure.driver.messaging.event;

import java.time.Instant;

public class OrderDeliveryEvent {
    public Long eventId;
    public String eventKey;
    public Instant eventDate;
    public Long orderId;
}
