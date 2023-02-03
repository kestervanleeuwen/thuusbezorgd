package nl.hu.inno.stock.core.application.query;

public class GetDishByName {
    private final String name;

    public GetDishByName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
