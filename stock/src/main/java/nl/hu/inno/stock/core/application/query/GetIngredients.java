package nl.hu.inno.stock.core.application.query;

public class GetIngredients {
    private final String orderBy;
    private final String direction;

    public GetIngredients(String orderBy, String direction) {
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
