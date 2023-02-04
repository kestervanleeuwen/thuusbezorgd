package nl.hu.inno.order.core.application.command;

public class ReceivedOrder {
    private long id;

    public ReceivedOrder(long id) {
        this.id = id;

    }

    public long getId() {
        return id;
    }
}
