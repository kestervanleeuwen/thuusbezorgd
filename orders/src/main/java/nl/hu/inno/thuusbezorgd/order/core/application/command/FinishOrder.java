package nl.hu.inno.thuusbezorgd.order.core.application.command;

public class FinishOrder {
    private final Long id;

    public FinishOrder(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
