package com.alex323glo.spacex.rest;

/**
 * General interface for embedded HTTP Server.
 *
 * @author alex323glo
 * @version 1.0.0
 * */
public interface SpaceXServer {

    /**
     * Starts HTTP server.
     *
     * @throws Exception when has some problems with HTTP Server tools.
     * @see Exception
     * */
    void start() throws Exception;

    /**
     * Stops HTTP server.
     *
     * @throws Exception when has some problems with HTTP Server tools.
     * @see Exception
     * */
    void stop() throws Exception;

}
