package nl.hu.inno.stock.core.application.command;

public class NewIngredient {
    private final String name;
    private final boolean isVegetarian;

    public NewIngredient(String name, boolean isVegetarian) {
        this.name = name;
        this.isVegetarian = isVegetarian;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }
}
