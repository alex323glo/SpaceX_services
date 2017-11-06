package com.alex323glo.spacex;

import com.alex323glo.spacex.config.ConfigHolder;
import com.alex323glo.spacex.config.ObjectHolder;
import com.alex323glo.spacex.controller.MainController;
import com.alex323glo.spacex.controller.MainControllerImpl;
import com.alex323glo.spacex.dao.AccessTokenDao;
import com.alex323glo.spacex.dao.UserDao;
import com.alex323glo.spacex.exception.ArgumentValidationException;
import com.alex323glo.spacex.rest.JettyServer;
import com.alex323glo.spacex.rest.SpaceXServer;

import java.io.IOException;

/**
 * Main class with execution entry point (main() method).
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class Run {

    private static ObjectHolder objectHolder;
    private static ConfigHolder configHolder;
    private static MainController mainController;

    public static void main(String[] args) throws Exception {

        prepareBeforeRun();     // throws ArgumentValidationException !

        SpaceXServer spaceXServer = new JettyServer(
                configHolder.getProperty("app.host"),
                Integer.valueOf(configHolder.getProperty("app.port"))
        );                      // throws ArgumentValidationException !

        spaceXServer.start();   // throws Exception !
    }

    private static void prepareBeforeRun() throws ArgumentValidationException {

        // Assign static:
        objectHolder = ObjectHolder.getInstance();
        configHolder = ConfigHolder.getInstance();
        mainController = MainControllerImpl.create(
                UserDao.create(),
                AccessTokenDao.create());   // throws ArgumentValidationException !

        // Load objects to ObjectHolder:
        objectHolder.putObject("mainController", mainController);
    }

}
