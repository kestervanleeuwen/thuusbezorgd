package nl.hu.inno.order.core.application.command;

public class ProcessDishes {
    private final Long orderId;

    public ProcessDishes(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
