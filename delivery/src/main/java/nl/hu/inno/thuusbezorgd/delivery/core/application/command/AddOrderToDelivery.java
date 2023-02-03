package nl.hu.inno.thuusbezorgd.delivery.core.application.command;

public class AddOrderToDelivery {

    private final Long deliveryId;
    private final Long orderId;

    public AddOrderToDelivery(Long deliveryId, Long orderId) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
