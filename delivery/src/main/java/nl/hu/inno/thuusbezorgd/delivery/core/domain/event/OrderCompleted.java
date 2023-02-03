package nl.hu.inno.thuusbezorgd.delivery.core.domain.event;

public class OrderCompleted extends DeliveryEvent {
    private final Long orderId;

    public OrderCompleted(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }


    @Override
    public String getEventKey() {
        return "delivery.order.completed";
    }
}
