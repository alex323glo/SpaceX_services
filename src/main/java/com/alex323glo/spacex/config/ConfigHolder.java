package com.alex323glo.spacex.config;

import com.alex323glo.spacex.Run;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton, which used as config holder.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class ConfigHolder {

    /**
     * Ref to single static ConfigHolder object.
     * */
    private static ConfigHolder thisConfigHolder;

    private static final String CONFIG_FILE_PATH = "/app.properties";
    private final Properties properties;

    /**
     * Locked constructor. Loads properties field value
     * from configuration file (which is located in classpath).
     *
     * @throws IOException if can't read config file from classpath.
     *
     * @see IOException
     * */
    private ConfigHolder() throws IOException {
        properties = new Properties();
        properties.load(
                new FileInputStream(
                        new File(
                                Run.class.getResource(CONFIG_FILE_PATH).getFile()
                        ).getAbsolutePath()));
    }

    /**
     * Searches for property by entered property key.
     *
     * @param propertyKey String key of needed property.
     * @return property, which matches entered key, or null
     * if property with such key doesn't exist.
     * */
    public String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    /**
     * Static accessor to singleton.
     *
     * @return instance of ConfigHolder.
     * @throws IOException when can't construct instance
     * (some problems with properties field).
     *
     * @see IOException
     * */
    public static ConfigHolder getInstance() throws IOException {
        if (thisConfigHolder == null) {
            thisConfigHolder = new ConfigHolder();
        }

        return thisConfigHolder;
    }

}
