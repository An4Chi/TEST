package common.exceptions;

public class FacadeException extends RuntimeException {
    private final int statusCode;

    public FacadeException(final int statusCode, final String message) {
        super(message);

        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
