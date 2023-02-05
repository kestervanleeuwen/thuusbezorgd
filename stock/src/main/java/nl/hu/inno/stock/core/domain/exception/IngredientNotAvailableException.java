package nl.hu.inno.stock.core.domain.exception;

public class IngredientNotAvailableException extends RuntimeException {
    public IngredientNotAvailableException(String message) {
        super(message);
    }
}
