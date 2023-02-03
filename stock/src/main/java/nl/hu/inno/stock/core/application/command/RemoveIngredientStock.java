package nl.hu.inno.stock.core.application.command;

public class RemoveIngredientStock {
    private final String name;
    private final int amount;

    public RemoveIngredientStock(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
