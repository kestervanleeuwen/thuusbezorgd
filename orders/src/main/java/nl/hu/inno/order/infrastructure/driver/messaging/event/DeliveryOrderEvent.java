package nl.hu.inno.order.infrastructure.driver.messaging.event;
import java.time.Instant;
import java.util.UUID;

public class DeliveryOrderEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public String orderId;
    public String deliveryId;
}
