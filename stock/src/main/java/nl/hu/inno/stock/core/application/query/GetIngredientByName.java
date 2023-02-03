package nl.hu.inno.stock.core.application.query;

public class GetIngredientByName {
    private final String name;

    public GetIngredientByName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
