package dao;

import exception.DAOException;

/**
 * Data Access Object (DAO) interface for needed model.
 * This interface gives access to CRUD operations with
 * system's DB (Create, Read, Update and Delete operations).
 * Generic is used to make interface for flexible for
 * any types of system's data models (such as User).
 *
 * @author alex323glo
 * @version 1.0.0
 * @see Model
 * @see model.User
 * */
public interface Dao<Model> {

    /**
     * Creates a record in system's DB.
     *
     * @param data data which will be written to DB.
     * @return true if operation was successful.
     *
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see Model
     * @see DAOException
     * */
    boolean create(Model data) throws DAOException;

    /**
     * Reads record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from needed record.
     *
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see Model
     * @see DAOException
     * */
    Model read(String id) throws DAOException;

    /**
     * Updates record in system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @param newData new data, which will replace existing data
     *                of needed record.
     * @return old data from updated record.
     *
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see Model
     * @see DAOException
     * */
    Model update(String id, Model newData) throws DAOException;

    /**
     * Removes record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from removed record.
     *
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see Model
     * @see DAOException
     * */
    Model delete(String id) throws DAOException;
}
