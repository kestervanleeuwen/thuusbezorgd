package nl.hu.inno.order.infrastructure.driver.web.request;

import nl.hu.inno.order.core.domain.ReviewRating;

import javax.validation.constraints.NotNull;

public class DeliveryReviewRequest {
    @NotNull
    public String userId;

    @NotNull
    public Long deliveryId;

    @NotNull
    public int rating;
}
