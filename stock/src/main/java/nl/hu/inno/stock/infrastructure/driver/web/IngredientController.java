package nl.hu.inno.stock.infrastructure.driver.web;

import nl.hu.inno.stock.core.application.DishCommandHandler;
import nl.hu.inno.stock.core.application.DishQueryHandler;
import nl.hu.inno.stock.core.application.command.PrepareDishes;
import nl.hu.inno.stock.core.application.query.GetIngredientById;
import nl.hu.inno.stock.core.application.query.GetIngredientByName;
import nl.hu.inno.stock.core.application.query.GetIngredients;
import nl.hu.inno.stock.core.domain.Ingredient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final DishCommandHandler commandHandler;
    private final DishQueryHandler queryHandler;

    public IngredientController(DishCommandHandler commandHandler, DishQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return queryHandler.handle(new GetIngredients(orderBy, direction));
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        return this.queryHandler.handle(new GetIngredientById(id));
    }

    @GetMapping("/name/{name}")
    public Ingredient getIngredientByName(@PathVariable String name) {
        return this.queryHandler.handle(new GetIngredientByName(name));
    }

}
