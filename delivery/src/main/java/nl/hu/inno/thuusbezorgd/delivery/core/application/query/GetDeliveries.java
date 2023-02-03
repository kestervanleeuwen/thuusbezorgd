package nl.hu.inno.thuusbezorgd.delivery.core.application.query;

public class GetDeliveries {
    private final String orderBy;
    private final String direction;

    public GetDeliveries(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "name";
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
