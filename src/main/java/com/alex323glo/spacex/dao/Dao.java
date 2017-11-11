package com.alex323glo.spacex.dao;

import com.alex323glo.spacex.model.user.User;

import java.util.List;
import java.util.Map;
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
public abstract class Dao<Model> {

    protected String dbRoot;

    protected Map<String, Model> recordMap;

    /**
     * Getter for dbRoot field.
     *
     * @return field value.
     * */
    public String getDbRoot() {
        return dbRoot;
    }

    /**
     * Setter for dbRoot field.
     *
     * @param dbRoot initial field value.
     * */
    public void setDbRoot(String dbRoot) {
        this.dbRoot = dbRoot;
    }

    /**
     * Getter for recordMap field.
     *
     * @return field value.
     *
     * @see Map
     * */
    public Map<String, Model> getRecordMap() {
        return recordMap;
    }

    /**
     * Setter for recordMap field.
     *
     * @param recordMap initial field value.
     *
     * @see Map
     * */
    public void setRecordMap(Map<String, Model> recordMap) {
        this.recordMap = recordMap;
    }

    /**
     * Creates a record in system's DB.
     *
     * @param data data which will be written to DB.
     * @return true, if operation was successful, and false, if it wasn't.
     *
     * @see Model
     * */
    public abstract boolean create(Model data);

    /**
     * Reads record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from needed record or null, if DB doesn't contain record with such id.
     *
     * @see Model
     * */
    public abstract Model read(String id);

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
    public abstract Model update(String id, Model newData);

    /**
     * Removes record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from removed record or null, if if DB doesn't contain record with such id.
     *
     * @see Model
     * */
    public abstract Model delete(String id);

    /**
     * Gives Set of all keys of table from system's DB.
     *
     * @return key Set of current DB table.
     *
     * @see Set
     * */
    public abstract Set<String> allKeys();

    /**
     * Gives List of all records of table from system's DB.
     *
     * @return value List of current DB table.
     *
     * @see Model
     * @see List
     * */
    public abstract List<Model> allRecords();
}
