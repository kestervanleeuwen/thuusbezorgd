package nl.hu.inno.order.core.application.query;

public class GetOrderByUser {
    private String userId;

    public GetOrderByUser(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
