package com.alex323glo.spacex.exception;

/**
 * Exception, which i thrown, when system tries to use
 * not existing token. Caused when system's DB doesn't contain
 * such token in tokens' list.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see AppException
 */
public class TokenNotExistsException extends AppException {
    /**
     * Constructs a new com.alex323glo.spacex.exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public TokenNotExistsException(String message) {
        super(message);
    }
}
