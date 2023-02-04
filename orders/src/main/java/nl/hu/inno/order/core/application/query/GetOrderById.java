package nl.hu.inno.order.core.application.query;

public class GetOrderById {
    private long id;

    public GetOrderById(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
