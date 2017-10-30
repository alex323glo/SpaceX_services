package com.alex323glo.spacex.exception;

/**
 * Exception, which is thrown, when system tries to register
 * user with email, which is currently exists in system's Data Base.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see AppException
 */
public class EmailDuplicationException extends AppException {
    /**
     * Constructs a new com.alex323glo.spacex.exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public EmailDuplicationException(String message) {
        super(message);
    }
}
