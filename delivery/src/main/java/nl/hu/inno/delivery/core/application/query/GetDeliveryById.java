package nl.hu.inno.delivery.core.application.query;

public class GetDeliveryById {
private final Long id;

    public GetDeliveryById(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
