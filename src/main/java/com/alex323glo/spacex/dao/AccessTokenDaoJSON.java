package com.alex323glo.spacex.dao;

import com.alex323glo.spacex.model.user.AccessToken;
import com.alex323glo.spacex.util.JSONUtil;
import com.alex323glo.spacex.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * DAO interface implementation for AccessToken data DAO.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see Dao
 * @see AccessToken
 * */
public class AccessTokenDaoJSON extends Dao<AccessToken> {

    /**
     * Private constructor. Instead use createInstance() method.
     *
     * @param dbRoot root path of Data Base.
     *
     * @see AccessTokenDaoJSON#createInstance(String)
     * */
    private AccessTokenDaoJSON(String dbRoot) {
        this.dbRoot = dbRoot;
        recordMap = new HashMap<>();
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
        if (data == null || data.getToken() == null) {
            return false;
        }

        try {
            JSONUtil.toFile(dbRoot + "/" + data.getToken() + ".json",
                    data, data.getClass());
        } catch (IOException e) {
            LogUtil.printMessage(this, "Can't save data \"" + data + "\" to JSON file.");
            e.printStackTrace();
            return false;
        }

        recordMap.put(data.getToken(), data);

        LogUtil.printMessage(this, "Was saved data \"" + data + "\" to JSON file.");
        return true;
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
        if (id == null) {
            throw new NullPointerException("id is null");
        }

        AccessToken data = recordMap.get(id);
        LogUtil.printMessage(this, "Was read data \"" + data + "\" from JSON file.");
        return data;
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
        if (id == null ||  !id.equals(newData.getToken())) {
            throw new NullPointerException("id is null or newData is wrong");
        }

        if (!recordMap.containsKey(id)) {
            LogUtil.printMessage(this, "Can't find and read data with key \"" + id + "\".");
            return null;
        }

        try {
            JSONUtil.toFile(dbRoot + "/" + id + ".json", newData, newData.getClass());
        } catch (IOException e) {
            LogUtil.printMessage(this, "Can't update data \"" + newData + "\" to JSON file.");
            e.printStackTrace();
            return null;
        }

        LogUtil.printMessage(this, "Updated data \"" + newData + "\" to JSON file.");
        return recordMap.put(id, newData);
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
        if (id == null) {
            throw new NullPointerException("id is null");
        }

        if (!recordMap.containsKey(id)) {
            LogUtil.printMessage(this, "Can't find and remove data with key \"" + id + "\".");
            return null;
        }

        File file = new File(dbRoot + "/" + id + ".json");
        if (file.exists() && file.delete()) {
            LogUtil.printMessage(this,
                    "Deleted data \"" + recordMap.get(id) + "\" from JSON file.");
            return recordMap.remove(id);
        }

        LogUtil.printMessage(this, "Can't delete data with key \"" + id + "\".");
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
        return recordMap.keySet();
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
        return new ArrayList<>(recordMap.values());
    }

    /**
     * Creates an instance of AccessTokenDaoJSON.
     *
     * @return new instance.
     * */
    public static AccessTokenDaoJSON createInstance(String dbRoot) {

        if (dbRoot == null) {
            throw new NullPointerException("dbRoot is null");
        }

        AccessTokenDaoJSON accessTokenDaoJSON = new AccessTokenDaoJSON(dbRoot);
        LogUtil.printMessage(accessTokenDaoJSON, "Created JSON AccessToken DAO.");

        Map<String, AccessToken> accessTokenMap = accessTokenDaoJSON.getRecordMap();

        File rootDir = new File(dbRoot);

        if (!rootDir.exists() || !rootDir.isDirectory()) {
            if (rootDir.mkdirs()) {
                LogUtil.printMessage(accessTokenDaoJSON,
                        "Successfully created a DB root directories chain ("
                                + rootDir.getAbsolutePath() + ").");
            } else {
                LogUtil.printMessage(accessTokenDaoJSON, "Can't create a DB root directories chain.");
            }
            return accessTokenDaoJSON;
        }

        LogUtil.printMessage(accessTokenDaoJSON, "Connected to DB root \"" + dbRoot + "\".");

        File[] dbFiles = rootDir.listFiles();

        if (dbFiles == null || dbFiles.length < 1) {
            LogUtil.printMessage(accessTokenDaoJSON, "Root directory is empty.");
            return accessTokenDaoJSON;
        }

        LogUtil.printMessage(accessTokenDaoJSON, "Root directory contains:");
        Arrays.stream(dbFiles).forEach(file -> System.out.println("\t\t" + file.getName()));

        for (File dbFile: dbFiles) {
            AccessToken tempAccessToken = JSONUtil.fromFile(dbFile.getPath(), AccessToken.class);
            if (tempAccessToken == null) {
                LogUtil.printMessage(accessTokenDaoJSON,
                        "Failed to read file \"" + dbFile.getName() + "\" It is not a JSON DB record!");
                continue;
            }
            accessTokenMap.put(tempAccessToken.getToken(), tempAccessToken);

            LogUtil.printMessage(accessTokenDaoJSON,
                    "Added from file \"" + dbFile.getName() + "\": " + tempAccessToken + ".");
        }

        return accessTokenDaoJSON;
    }
}
