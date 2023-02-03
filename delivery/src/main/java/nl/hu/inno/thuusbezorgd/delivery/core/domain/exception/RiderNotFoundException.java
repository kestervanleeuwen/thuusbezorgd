package nl.hu.inno.thuusbezorgd.delivery.core.domain.exception;

public class RiderNotFoundException extends RuntimeException {
    public RiderNotFoundException(String message) {
        super(message);
    }
}

