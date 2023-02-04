package nl.hu.inno.order.core.domain.exception;

public class OrderNotFoundException  extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
