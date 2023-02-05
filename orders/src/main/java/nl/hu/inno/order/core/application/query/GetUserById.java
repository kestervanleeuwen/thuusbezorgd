package nl.hu.inno.order.core.application.query;

public class GetUserById {
    private final String id;

    public GetUserById(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
