package nl.hu.inno.stock.core.application.command;

import nl.hu.inno.stock.core.domain.Ingredient;

import java.util.List;

public class NewDish {
    private final String name;
    private final List<Ingredient> ingredients;

    public NewDish(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
