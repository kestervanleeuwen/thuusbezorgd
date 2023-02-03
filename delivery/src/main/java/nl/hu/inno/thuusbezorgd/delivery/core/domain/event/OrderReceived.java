package nl.hu.inno.thuusbezorgd.delivery.core.domain.event;

public class OrderReceived extends DeliveryEvent {
    private final Long orderId;

    public OrderReceived(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    @Override
    public String getEventKey() {
        return "delivery.order.received";
    }
}
