package nl.hu.inno.stock.infrastructure.driver.web.request;

import javax.validation.constraints.NotNull;

public class DeliverIngredientsRequest {
    @NotNull
    public Long ingredientId;

    @NotNull
    public int amount;
}
