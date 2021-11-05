package by.zvezdina.composite.exception;

public class TextHandlingException extends Exception {

    public TextHandlingException() {
    }

    public TextHandlingException(String message) {
        super(message);
    }

    public TextHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextHandlingException(Throwable cause) {
        super(cause);
    }
}
