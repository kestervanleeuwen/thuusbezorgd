package nl.hu.inno.stock.core.application;

import nl.hu.inno.stock.core.application.command.*;
import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.core.domain.Ingredient;
import nl.hu.inno.stock.core.domain.exception.IngredientNotAvailableException;
import nl.hu.inno.stock.core.ports.storage.DishRepository;
import nl.hu.inno.stock.core.ports.storage.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishCommandHandler {
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    public DishCommandHandler(DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Dish handle(NewDish command) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Long ingredientId : command.getIngredientIds()) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            ingredients.add(ingredient.get());
        }
        Dish dish = new Dish(command.getName(), ingredients);
        return dishRepository.save(dish);
    }

    public Ingredient handle(NewIngredient command) {
        Ingredient ingredient = new Ingredient(command.getName(), command.isVegetarian());
        return ingredientRepository.save(ingredient);
    }

    public Ingredient handle(AddIngredientStock command) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(command.getId());
        Ingredient ingredient = optionalIngredient.get();

        ingredient.deliver(command.getAmount());
        return ingredientRepository.save(ingredient);
    }

    public Ingredient handle(RemoveIngredientStock command) {
        Ingredient ingredient = ingredientRepository.findByName(command.getName());
        ingredient.take(command.getAmount());
        return ingredientRepository.save(ingredient);
    }

    public void handle(PrepareDishes command) {
        List<Dish> dishes = new ArrayList<>();
        for (Long dishId : command.getDishIds()) {
            Optional<Dish> dish = dishRepository.findById(dishId);
            dishes.add(dish.get());
        }

        for (Dish dish : dishes) {
            for(Ingredient ingredient : dish.getIngredients()) {
                if (ingredient.getNrInStock() < 1) throw new IngredientNotAvailableException(ingredient.getName() + " is not available");
            }
            dish.prepare();

            for(Ingredient ingredient : dish.getIngredients()) {
                ingredientRepository.save(ingredient);
            }
        }
    }

}
