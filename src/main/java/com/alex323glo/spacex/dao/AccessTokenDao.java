package com.alex323glo.spacex.dao;

import com.alex323glo.spacex.model.user.AccessToken;

import java.util.List;
import java.util.Set;

// TODO finish AccessTokenDao (implementation of Dao<AccessToken>)
/**
 * DAO interface implementation for AccessToken data DAO.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see Dao
 * @see AccessToken
 * */
public class AccessTokenDao implements Dao<AccessToken> {

    /**
     * Private empty constructor. Instead use create() method.
     *
     * @see AccessTokenDao#create()
     * */
    private AccessTokenDao() {

    }

    /**
     * Creates a record in system's DB.
     *
     * @param data data which will be written to DB.
     * @return true, if operation was successful, and false, if it wasn't.
     * @see AccessToken
     */
    @Override
    public boolean create(AccessToken data) {
        return false;
    }

    /**
     * Reads record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from needed record or null, if DB doesn't contain record with such id.
     * @see AccessToken
     */
    @Override
    public AccessToken read(String id) {
        return null;
    }

    /**
     * Updates record in system's DB.
     * If record with entered id isn't stored in DB, it creates new record with entered id and data.
     *
     * @param id      identifier of needed record in system's DB.
     * @param newData new data, which will replace existing data
     *                of needed record.
     * @return old data from updated record or null, if if DB doesn't contain record with such id.
     * @see AccessToken
     */
    @Override
    public AccessToken update(String id, AccessToken newData) {
        return null;
    }

    /**
     * Removes record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from removed record or null, if if DB doesn't contain record with such id.
     * @see AccessToken
     */
    @Override
    public AccessToken delete(String id) {
        return null;
    }

    /**
     * Gives Set of all keys of table from system's DB.
     *
     * @return key Set of current DB table.
     * @see Set
     */
    @Override
    public Set<String> allKeys() {
        return null;
    }

    /**
     * Gives List of all records of table from system's DB.
     *
     * @return value List of current DB table.
     * @see AccessToken
     * @see List
     */
    @Override
    public List<AccessToken> allRecords() {
        return null;
    }

    /**
     * Creates an instance of AccessTokenDao.
     *
     * @return new instance.
     * */
    public static AccessTokenDao create() {
        AccessTokenDao accessTokenDao = new AccessTokenDao();

        return accessTokenDao;
    }
}
