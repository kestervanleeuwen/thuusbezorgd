package nl.hu.inno.order.core.application.query;

public class GetOrderByUser {
    private long userId;

    public GetOrderByUser(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
