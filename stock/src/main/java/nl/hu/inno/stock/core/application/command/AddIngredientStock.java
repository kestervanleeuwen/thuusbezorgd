package nl.hu.inno.stock.core.application.command;

public class AddIngredientStock {

    private final String name;
    private final int amount;

    public AddIngredientStock(String name, int amount) {
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
