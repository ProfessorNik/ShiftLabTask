package balyasnikov.nikolay.computerstore.application.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
