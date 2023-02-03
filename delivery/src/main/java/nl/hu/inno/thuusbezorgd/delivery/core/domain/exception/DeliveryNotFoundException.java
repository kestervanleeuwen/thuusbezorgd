package nl.hu.inno.thuusbezorgd.delivery.core.domain.exception;

public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(String message) {
        super(message);
    }
}
