package exception;

/**
 * Exception, which is thrown, when entered arguments can't pass
 * validation. Mainly thrown by validator, when argument doesn't
 * pass business method's requirements
 *
 * @author alex323glo
 * @version 1.0.0
 * @see AppException
 */
public class ArgumentValidationException extends AppException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ArgumentValidationException(String message) {
        super(message);
    }
}
