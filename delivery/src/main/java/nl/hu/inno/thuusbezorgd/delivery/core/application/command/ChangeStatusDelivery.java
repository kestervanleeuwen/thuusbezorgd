package nl.hu.inno.thuusbezorgd.delivery.core.application.command;

public class ChangeStatusDelivery {
    private final Long id;
    private final boolean completed;

    public ChangeStatusDelivery(Long id, boolean completed) {
        this.id = id;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }
}
