package nl.hu.inno.stock.infrastructure.driver.web;

import nl.hu.inno.stock.core.application.DishCommandHandler;
import nl.hu.inno.stock.core.application.DishQueryHandler;
import nl.hu.inno.stock.core.application.command.NewDish;
import nl.hu.inno.stock.core.application.command.PrepareDishes;
import nl.hu.inno.stock.core.application.query.GetDishById;
import nl.hu.inno.stock.core.application.query.GetDishByName;
import nl.hu.inno.stock.core.application.query.GetDishes;
import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.infrastructure.driver.web.request.NewDishRequest;
import nl.hu.inno.stock.infrastructure.driver.web.request.PrepareRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    private final DishCommandHandler commandHandler;
    private final DishQueryHandler queryHandler;

    public DishController(DishCommandHandler commandHandler, DishQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping
    public List<Dish> getAllDishes(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return queryHandler.handle(new GetDishes(orderBy, direction));
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable Long id) {
        return this.queryHandler.handle(new GetDishById(id));
    }

    @GetMapping("/name/{name}")
    public Dish getDishesByName(@PathVariable String name) {
        return this.queryHandler.handle(new GetDishByName(name));
    }

    @PostMapping("/prepare")
    public void prepareDishes(@RequestBody PrepareRequest request) {
        this.commandHandler.handle(new PrepareDishes(request.dishIds));
    }

    @PostMapping
    public void newDish(@RequestBody NewDishRequest request) {
        this.commandHandler.handle(new NewDish(request.name, request.ingredientIds));
    }


}
