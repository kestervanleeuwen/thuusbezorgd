package nl.hu.inno.stock.infrastructure.driver.web.request;

import javax.validation.constraints.NotNull;

public class NewIngredientRequest {
    @NotNull
    public String name;

    @NotNull
    public boolean isVegetarian;
}
