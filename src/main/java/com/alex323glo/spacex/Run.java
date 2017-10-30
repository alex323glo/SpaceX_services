package com.alex323glo.spacex;

import com.alex323glo.spacex.rest.JettyServer;
import com.alex323glo.spacex.rest.SpaceXServer;

// TODO add doc
/**
 * Created by alex323glo on 27.10.17.
 */
public class Run {



    public static void main(String[] args) {
        SpaceXServer spaceXServer = new JettyServer(8000);

        try {
            spaceXServer.start();
        } catch (Exception e) {
            System.out.println("Can't start server!!!");
            e.printStackTrace();
        }
    }

}
