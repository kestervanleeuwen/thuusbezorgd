package nl.hu.inno.order.infrastructure.driver.messaging.event;
import java.time.Instant;

public class DeliveryOrderEvent {
    public Long eventId;
    public String eventKey;
    public Instant eventDate;
    public Long orderId;
}
