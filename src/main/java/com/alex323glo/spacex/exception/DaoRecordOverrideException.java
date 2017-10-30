package com.alex323glo.spacex.exception;

/**
 * Exception, which is thrown, when system tries to write new record to DB.
 * Caused if id of new record is already used in DB.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see AppException
 */
public class DaoRecordOverrideException extends DAOException {
    /**
     * Constructs a new com.alex323glo.spacex.exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DaoRecordOverrideException(String message) {
        super(message);
    }
}
