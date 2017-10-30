package com.alex323glo.spacex.exception;

/**
 * Exception, which is thrown, when system has problems with load of
 * needed file from system's resources.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see AppException
 */
public class LoadFileException extends AppException {
    /**
     * Constructs a new com.alex323glo.spacex.exception with the specified detail message.  The
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
