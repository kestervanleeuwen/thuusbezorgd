package nl.hu.inno.thuusbezorgd.order.core.domain.event;

public class NewOrderEvent extends OrderEvent {
    private final Long orderId;

    public NewOrderEvent(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getEventKey() {
        return "order.delivery.created";
    }

    public Long getOrderId() {
        return orderId;
    }
}
