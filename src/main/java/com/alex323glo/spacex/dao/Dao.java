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
     * @return true, if operation was successful, and false, if it wasn't.
     *
     * @see Model
     * */
    boolean create(Model data);

    /**
     * Reads record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from needed record or null, if DB doesn't contain record with such id.
     *
     * @see Model
     * */
    Model read(String id);

    /**
     * Updates record in system's DB.
     * If record with entered id isn't stored in DB, it creates new record with entered id and data.
     *
     * @param id identifier of needed record in system's DB.
     * @param newData new data, which will replace existing data
     *                of needed record.
     * @return old data from updated record or null, if if DB doesn't contain record with such id.
     *
     * @see Model
     * */
    Model update(String id, Model newData);

    /**
     * Removes record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from removed record or null, if if DB doesn't contain record with such id.
     *
     * @see Model
     * */
    Model delete(String id);

    /**
     * Gives Set of all keys of table from system's DB.
     *
     * @return key Set of current DB table.
     *
     * @see Set
     * */
    Set<String> allKeys();

    /**
     * Gives List of all records of table from system's DB.
     *
     * @return value List of current DB table.
     *
     * @see Model
     * @see List
     * */
    List<Model> allRecords();
}
