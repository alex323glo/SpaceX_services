package exception;

/**
 * Exception, which is thrown, when system has problems with load of
 * needed file from system's resources.
 */
public class LoadFileException extends AppException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public LoadFileException(String message) {
        super(message);
    }
}
