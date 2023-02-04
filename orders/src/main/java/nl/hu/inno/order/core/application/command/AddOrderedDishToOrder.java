package nl.hu.inno.order.core.application.command;

public class AddOrderedDishToOrder {
    private Long orderId;
    private Long dishId;

    public AddOrderedDishToOrder(Long orderId, Long dishId) {
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getDishId() {
        return dishId;
    }

}
