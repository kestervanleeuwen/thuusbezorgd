package nl.hu.inno.thuusbezorgd.order.core.application.query;

public class GetOrders {
    private final String orderBy;
    private final String direction;

    public GetOrders(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "user";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}
