package nl.hu.inno.thuusbezorgd.delivery.core.application.query;

public class GetRiders {
    private final String orderBy;
    private final String direction;

    public GetRiders(String orderBy, String direction) {
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
