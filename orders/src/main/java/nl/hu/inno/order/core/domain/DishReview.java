package nl.hu.inno.order.core.domain;

import javax.persistence.Entity;

@Entity
public class DishReview extends Review {
    private Long dish;

    protected DishReview(){}

    public DishReview(Long dish, ReviewRating rating, User user) {
        this.dish = dish;
        super.rating = rating;
        super.user = user;
    }

    public Long getDish() {
        return dish;
    }
}
