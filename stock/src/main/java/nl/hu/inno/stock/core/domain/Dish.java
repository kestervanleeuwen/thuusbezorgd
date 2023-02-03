package nl.hu.inno.stock.core.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Dish {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Ingredient> ingredients;

    protected Dish() {
        //For Hibernate
    }

    public Dish(String name, List<Ingredient> ingredients) {
        if (ingredients.size() == 0) {
            throw new IllegalArgumentException("Cannot have 0 ingredients");
        }

        this.name = name;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public boolean isVegetarian() {
        return this.ingredients.stream().allMatch(Ingredient::isVegetarian);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getAvailable() {
        return this.getIngredients().stream().mapToInt(Ingredient::getNrInStock).min().orElse(0);
    }

    public void prepare(){
        for(Ingredient i: this.ingredients){
            i.take(1);
        }
    }
}
