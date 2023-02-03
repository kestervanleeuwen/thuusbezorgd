package nl.hu.inno.thuusbezorgd.order.core.domain;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class  OrderedDish {
    @Embeddable
    public static class OrderedDishId implements Serializable {

        private Long dish;

        @ManyToOne
        private Order order;
    }

    @EmbeddedId
    private OrderedDishId id;

    protected OrderedDish() {

    }

    public OrderedDish(Order owner, Long dish) {
        this.id = new OrderedDishId();
        this.id.order = owner;
        this.id.dish = dish;
        this.nr = 1;
    }

    private int nr;

    public int getNr() {
        return nr;
    }

    public Order getOrder() {
        return this.id.order;
    }

    public Long getDish() {
        return this.id.dish;
    }

    public OrderedDishId getId() {
        return id;
    }
}
