package exception;

/**
 * Exception, which is thrown, when system tries to give access
 * to client as non-existed in system's DB User. Could be caused when
 * client inputs non-existing email or/and non-existing password while logging in.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see AppException
 */
public class AccessRestrictionException extends AppException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AccessRestrictionException(String message) {
        super(message);
    }
}
