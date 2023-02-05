package nl.hu.inno.stock.infrastructure.driver.web.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NewDishRequest {
    @NotNull
    public String name;

    @NotNull
    public List<Long> ingredientIds;


}
