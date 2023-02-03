package nl.hu.inno.stock.core.application.query;

public class GetIngredientById {
    private final Long id;

    public GetIngredientById(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
