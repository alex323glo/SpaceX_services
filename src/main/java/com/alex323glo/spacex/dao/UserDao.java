package com.alex323glo.spacex.dao;

import com.alex323glo.spacex.model.user.User;

import java.util.List;
import java.util.Set;

// TODO finish UserDao (implementation of Dao<User>)
/**
 * DAO interface implementation for User data DAO.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see Dao
 * @see User
 * */
public class UserDao implements Dao<User> {

    /**
     * Private empty constructor. Instead use create() method.
     *
     * @see UserDao#create()
     * */
    private UserDao() {

    }

    /**
     * Creates a record in system's DB.
     *
     * @param data data which will be written to DB.
     * @return true, if operation was successful, and false, if it wasn't.
     * @see User
     */
    @Override
    public boolean create(User data) {
        return false;
    }

    /**
     * Reads record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from needed record or null, if DB doesn't contain record with such id.
     * @see User
     */
    @Override
    public User read(String id) {
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
     * @see User
     */
    @Override
    public User update(String id, User newData) {
        return null;
    }

    /**
     * Removes record from system's DB.
     *
     * @param id identifier of needed record in system's DB.
     * @return data from removed record or null, if if DB doesn't contain record with such id.
     * @see User
     */
    @Override
    public User delete(String id) {
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
     * @see User
     * @see List
     */
    @Override
    public List<User> allRecords() {
        return null;
    }

    /**
     * Creates an instance of UserDao.
     *
     * @return new instance.
     * */
    public static UserDao create() {
        UserDao userDao = new UserDao();

        return userDao;
    }

}
