package nl.hu.inno.delivery.core.domain.event;

public class OrderReceived extends DeliveryEvent {
    private final Long orderId;
    private final Long deliveryId;

    public OrderReceived(Long orderId, Long deliveryId) {
        this.orderId = orderId;
        this.deliveryId = deliveryId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    @Override
    public String getEventKey() {
        return "delivery.order.received";
    }
}
