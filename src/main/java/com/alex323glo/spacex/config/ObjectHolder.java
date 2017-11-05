package com.alex323glo.spacex.config;

import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton, which used as static object holder.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class ObjectHolder {

    private static ObjectHolder objectHolderSingleton;

    private Map<String, Object> staticObjects;

    /**
     * Private constructor. Assigns static objects' map.
     * */
    private ObjectHolder() {
        staticObjects = new HashMap<>();
    }

    /**
     * Clears staticObjects map.
     * */
    public void clear() {
        staticObjects.clear();
    }

    /**
     * Maps needed static object by String key.
     *
     * @param key identifier of static object.
     * @return needed static object.
     *
     * @throws NoSuchObjectException when static objects' map doesn't
     * contain object with such key.
     *
     * @see NoSuchObjectException
     * */
    public Object getObject(String key) throws NoSuchObjectException {
        if (!staticObjects.containsKey(key)) {
            throw new NoSuchObjectException("static object not found");
        }
        return staticObjects.get(key);
    }

    /**
     * Stores needed static object by String key.
     *
     * @param key identifier of static object.
     * @param object static object, which will be stored.
     * @return object, which was stored in map by entered key and will be
     * removed by new object, or null if map doesn't contain object with
     * entered key.
     * */
    public Object putObject(String key, Object object) {
        return staticObjects.put(key, object);
    }
    
    /**
     * Gives single (static) instance of this Singleton (lazy).
     *
     * @return static Singleton instance (field objectHolderSingleton).
     *
     * @see ObjectHolder#objectHolderSingleton
     * */
    public static ObjectHolder getInstance() {
        if (objectHolderSingleton == null) {
            objectHolderSingleton = new ObjectHolder();
        }

        return objectHolderSingleton;
    }

}