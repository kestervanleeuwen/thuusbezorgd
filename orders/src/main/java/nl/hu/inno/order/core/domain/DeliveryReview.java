package nl.hu.inno.order.core.domain;

import javax.persistence.Entity;

@Entity
public class DeliveryReview extends Review {
    private Long delivery;


    protected DeliveryReview() {
    }

    public DeliveryReview(Long delivery, ReviewRating rating, User user) {
        this.delivery = delivery;
        super.rating = rating;
        super.user = user;
    }

    public Long getDelivery() {
        return delivery;
    }
}
