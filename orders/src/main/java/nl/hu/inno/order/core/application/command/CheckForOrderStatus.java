package nl.hu.inno.order.core.application.command;

import nl.hu.inno.order.core.domain.OrderStatus;

public class CheckForOrderStatus {
    private final Long id;

    public CheckForOrderStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
