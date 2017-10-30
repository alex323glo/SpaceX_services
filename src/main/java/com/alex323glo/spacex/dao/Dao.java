package com.alex323glo.spacex.dao;

import com.alex323glo.spacex.exception.DAOException;
import com.alex323glo.spacex.exception.DaoRecordNotFoundException;
import com.alex323glo.spacex.exception.DaoRecordOverrideException;
import com.alex323glo.spacex.model.user.User;

import java.util.List;
import java.util.Set;

/**
 * Data Access Object (DAO) interface for needed com.alex323glo.spacex.model.
 * This interface gives access to CRUD operations with
 * system's DB (Create, Read, Update and Delete operations).
 * Generic is used to make interface for flexible for
 * any types of system's data models (such as User).
 *
 * @author alex323glo
 * @version 1.0.0
 * @see Model
 * @see User
 * */
public interface Dao<Model> {

    /**
     * Creates a record in system's DB.
     *
     * @param data data which will be written to DB.
     * @return true if operation was successful.
     *
     * @throws DaoRecordOverrideException when DB contains record with equal key.
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see Model
     * @see DaoRecordOverrideException
     * @see DAOException
     * */
    boolean create(Model data) throws DaoRecordOverrideException, DAOException;

    /**
     * Reads record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from needed record.
     *
     * @throws DaoRecordNotFoundException when DB doesn't contain record with such id.
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see Model
     * @see DAOException
     * */
    Model read(String id) throws DaoRecordNotFoundException, DAOException;

    /**
     * Updates record in system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @param newData new data, which will replace existing data
     *                of needed record.
     * @return old data from updated record.
     *
     * @throws DaoRecordNotFoundException when DB doesn't contain record with such id.
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see Model
     * @see DAOException
     * */
    Model update(String id, Model newData) throws DaoRecordNotFoundException, DAOException;

    /**
     * Removes record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from removed record.
     *
     * @throws DaoRecordNotFoundException when DB doesn't contain record with such id.
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see Model
     * @see DAOException
     * */
    Model delete(String id) throws DaoRecordNotFoundException, DAOException;

    /**
     * Gives Set of all keys of table from system's DB.
     *
     * @return key Set of current DB table.
     *
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see DAOException
     * */
    Set<String> allKeys() throws DAOException;

    /**
     * Gives list of records with equal values.
     *
     * @param value record, which value must be equal to values
     *              of all records in result record List.
     * @return result list of records.
     *
     * @throws DAOException when system can't carry out
     * this operation with DB.
     *
     * @see List
     * @see DAOException
     * */
    List<Model> equalValues(Model value) throws DAOException;
}
