package nl.hu.inno.stock.core.application.query;

public class GetDishById {
    private final Long id;

    public GetDishById(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
