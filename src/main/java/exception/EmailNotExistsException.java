package exception;

/**
 * Exception, which is thrown when system tries to access User data in
 * system's DB by non-existing email. That's because system's DB uses
 * user's email as a key for storing user's data in system's DB.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see AppException
 */
public class EmailNotExistsException extends AppException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public EmailNotExistsException(String message) {
        super(message);
    }
}
