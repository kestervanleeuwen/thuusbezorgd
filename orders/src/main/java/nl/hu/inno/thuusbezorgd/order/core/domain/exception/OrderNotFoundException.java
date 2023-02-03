package nl.hu.inno.thuusbezorgd.order.core.domain.exception;

public class OrderNotFoundException  extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
