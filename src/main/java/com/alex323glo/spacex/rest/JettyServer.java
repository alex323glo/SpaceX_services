package com.alex323glo.spacex.rest;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

// TODO create doc
/**
 * Created by alex323glo on 27.10.17.
 */
public class JettyServer implements SpaceXServer {

    private static final int DEFAULT_PORT = 8000;

    private Server server;

    public JettyServer(int port) {
        // Create server and server's connector:
        server = new Server();
        ServerConnector connector = new ServerConnector(server);

        // Setup server:
        connector.setPort(port > 0 ? port : DEFAULT_PORT);
        server.setConnectors(new Connector[]{connector});

        // Add servlet handler to server:
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        // Add servlets to servlet handler:
        servletHandler.addServletWithMapping(TestServlet.class,"/test");    // test servlet
        servletHandler.addServletWithMapping(LoadPublicFilesServlet.class, "/public/*");
        servletHandler.addServletWithMapping(LoadAjaxServlet.class, "/load-ajax/*");
        // Other servlets...
    }


    @Override
    public void start() throws Exception {
        server.start();
    }

    @Override
    public void stop() throws Exception {
        server.stop();
    }
}
