package exception;

/**
 * Exception. which is thrown, when system tries to access DB using some DAO.
 * Caused if DAO can't carry out some needed operations with DB.
 */
public class DAOException extends AppException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DAOException(String message) {
        super(message);
    }
}
