package nl.hu.inno.order.infrastructure.driver.web.request;

import javax.validation.constraints.NotNull;

public class DishReviewRequest {
    @NotNull
    public String userId;

    @NotNull
    public Long dishId;

    @NotNull
    public int rating;
}
