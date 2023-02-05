package nl.hu.inno.stock.core.application.command;

public class AddIngredientStock {

    private final Long id;
    private final int amount;

    public AddIngredientStock(Long id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    } 
}
