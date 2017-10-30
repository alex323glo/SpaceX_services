package com.alex323glo.spacex.exception;

/**
 * General Exception type for SpaceX application.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see Exception
 */
public class AppException extends Exception {
    /**
     * Constructs a new com.alex323glo.spacex.exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public AppException(String message) {
        super(message);
    }
}
