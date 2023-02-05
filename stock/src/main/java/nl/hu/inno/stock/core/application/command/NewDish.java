package nl.hu.inno.stock.core.application.command;

import nl.hu.inno.stock.core.domain.Ingredient;

import java.util.List;

public class NewDish {
    private final String name;
    private final List<Long> ingredientIds;

    public NewDish(String name, List<Long> ingredientIds) {
        this.name = name;
        this.ingredientIds = ingredientIds;
    }

    public String getName() {
        return name;
    }

    public List<Long> getIngredientIds() {
        return ingredientIds;
    }
}
