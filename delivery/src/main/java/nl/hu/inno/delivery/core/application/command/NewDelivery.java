package nl.hu.inno.delivery.core.application.command;

public class NewDelivery {
    private final Long order;

    public NewDelivery(Long order) {
        this.order = order;
    }

    public Long getOrder() {
        return order;
    }
}
