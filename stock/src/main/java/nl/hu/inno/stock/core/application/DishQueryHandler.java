package nl.hu.inno.stock.core.application;

import nl.hu.inno.stock.core.application.query.*;
import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.core.domain.Ingredient;
import nl.hu.inno.stock.core.domain.exception.DishNotFoundException;
import nl.hu.inno.stock.core.ports.storage.DishRepository;
import nl.hu.inno.stock.core.ports.storage.IngredientRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishQueryHandler {
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    public DishQueryHandler(DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Dish handle(GetDishById query) {
        return dishRepository.findById(query.getId())
                .orElseThrow(() -> new DishNotFoundException("Dish not found"));
    }

    public Dish handle(GetDishByName query) {
        return dishRepository.findByName(query.getName());
    }

    public List<Dish> handle(GetDishes query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return dishRepository.findAll(sort);
    }

    public Ingredient handle(GetIngredientById query) {
        return ingredientRepository.findById(query.getId())
                .orElseThrow(() -> new DishNotFoundException("Ingredient not found"));
    }

    public Ingredient handle(GetIngredientByName query) {
        return ingredientRepository.findByName(query.getName());
    }

    public List<Ingredient> handle(GetIngredients query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return ingredientRepository.findAll(sort);
    }
    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
