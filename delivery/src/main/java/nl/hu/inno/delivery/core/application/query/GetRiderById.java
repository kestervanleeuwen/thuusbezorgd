package nl.hu.inno.delivery.core.application.query;

public class GetRiderById {
    private final Long id;

    public GetRiderById(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
