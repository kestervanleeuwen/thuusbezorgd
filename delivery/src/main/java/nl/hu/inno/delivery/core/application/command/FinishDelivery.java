package nl.hu.inno.delivery.core.application.command;

public class FinishDelivery {
    private final Long id;

    public FinishDelivery(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
