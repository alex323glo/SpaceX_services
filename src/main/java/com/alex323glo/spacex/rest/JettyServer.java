package com.alex323glo.spacex.rest;

import com.alex323glo.spacex.exception.ArgumentValidationException;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Implementation of Embedded HTTP Server powered
 * by Jetty Server Tools.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see Server
 * @see Connector
 * @see ServerConnector
 * @see ServletHandler
 * */
public class JettyServer implements SpaceXServer {

    private Server server;

    /**
     * Constructor, which creates Jetty Server with needed port and host name.
     *
     * @param host host name, which will be set to server.
     * @param port port number, which will be set to server.
     * @throws ArgumentValidationException if host or port are not valid.
     *
     * @see ArgumentValidationException
     * */
    public JettyServer(String host, int port) throws ArgumentValidationException {
        // Validation:
        if (host == null) {
            throw new ArgumentValidationException("host is null");
        }
        if (port < 1) {
            throw new ArgumentValidationException("port number is less then 1");
        }

        // Create server and server's connector:
        server = new Server();

        // Setup server using ServerConnector:
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(port);
        serverConnector.setHost(host);
        server.setConnectors(new Connector[]{serverConnector});

        // Add Servlet handler to server:
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        // Add Servlets to Servlet handler:
        servletHandler.addServletWithMapping(TestServlet.class,"/test");
        servletHandler.addServletWithMapping(LoadPublicFilesServlet.class, "/public/*");
        servletHandler.addServletWithMapping(LoadAjaxServlet.class, "/load-ajax/*");
    }

    /**
     * Starts HTTP server.
     *
     * @throws Exception when has some problems with HTTP Server tools.
     * @see Exception
     */
    @Override
    public void start() throws Exception {
        server.start();
    }

    /**
     * Stops HTTP server.
     *
     * @throws Exception when has some problems with HTTP Server tools.
     * @see Exception
     */
    @Override
    public void stop() throws Exception {
        server.stop();
    }
}
