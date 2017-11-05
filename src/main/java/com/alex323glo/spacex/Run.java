package com.alex323glo.spacex;

import com.alex323glo.spacex.config.ConfigHolder;
import com.alex323glo.spacex.config.ObjectHolder;
import com.alex323glo.spacex.rest.JettyServer;
import com.alex323glo.spacex.rest.SpaceXServer;

import java.io.IOException;

// TODO add doc
/**
 * Created by alex323glo on 27.10.17.
 */
public class Run {

    private static ObjectHolder objectHolder;
    private static ConfigHolder configHolder;

    public static void main(String[] args) {

        if (!prepareBeforeRun()) {
            System.out.println("Can't prepare before main() run!");
            return;
        }

        SpaceXServer spaceXServer = new JettyServer(
                Integer.valueOf(configHolder.getProperty("app.port")));

        try {
            spaceXServer.start();
        } catch (Exception e) {
            System.out.println("Can't start server!!!");
            e.printStackTrace();
        }
    }

    /**
     * Run all initialisations before running main().
     *
     * @see ObjectHolder
     * @see ConfigHolder
     * */
    private static boolean prepareBeforeRun() {
        objectHolder = ObjectHolder.getInstance();

        configHolder = ConfigHolder.getInstance();
        if (configHolder == null) {
            return false;
        }

        return true;
    }

}
