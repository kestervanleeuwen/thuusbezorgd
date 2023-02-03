package nl.hu.inno.thuusbezorgd.order.core.application.command;

import nl.hu.inno.thuusbezorgd.order.core.domain.OrderStatus;

public class ChangeOrderStatus {
    private long id;
    private OrderStatus status;

    public ChangeOrderStatus(long id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
