package common.exceptions;

public class AutomationInterruptedException extends RuntimeException {
    public AutomationInterruptedException(final String message) {
        super(message);
    }
}