package com.alex323glo.spacex.exception;

/**
 * Exception, which is thrown, when system tries to find a record in DB.
 * Caused if DB doesn't contain a record with key equal to entered key.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see AppException
 */
public class DaoRecordNotFoundException extends DAOException {
    /**
     * Constructs a new com.alex323glo.spacex.exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DaoRecordNotFoundException(String message) {
        super(message);
    }
}
