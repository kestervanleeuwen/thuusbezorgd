package nl.hu.inno.order.core.application.command;

public class ReceivedOrder {
    private Long orderId;
    private Long deliveryId;


    public ReceivedOrder(Long orderId, Long deliveryId) {
        this.orderId = orderId;
        this.deliveryId = deliveryId;

    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }
}
