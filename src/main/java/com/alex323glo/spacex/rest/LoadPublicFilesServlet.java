package com.alex323glo.spacex.rest;

import com.alex323glo.spacex.config.ObjectHolder;
import com.alex323glo.spacex.controller.MainController;
import com.alex323glo.spacex.exception.LoadFileException;
import com.alex323glo.spacex.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * HTTP Servlet, which serves GET requests for loading public static files.
 * Request URL: "/public/*"
 *
 * @author alex323glo
 * @version 1.0.0
 * @see HttpServlet
 */
public class LoadPublicFilesServlet extends HttpServlet {

    /**
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request.
     *
     * @param req  an {@link HttpServletRequest} object that
     *             contains the request the client has made
     *             of the servlet
     * @param resp an {@link HttpServletResponse} object that
     *             contains the response the servlet sends
     *             to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the GET request
     * @throws ServletException if the request for the GET
     *                          could not be handled
     * @see ServletResponse#setContentType
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Gets ref to MainController object using ObjectHolder:
        MainController mainControllerRef =
                (MainController) ObjectHolder.getInstance().getObject("mainController");
        if (mainControllerRef == null) {
            ServletUtil.setQuickResponse(resp,
                    HttpServletResponse.SC_NOT_IMPLEMENTED, "Not Implemented");
            return;
        }

        // Gets requested public file name from request URI:
        String[] uriParts = req.getRequestURI().split("public/");
        if (uriParts == null || uriParts.length < 2 || uriParts[1].length() < 3) {
            ServletUtil.setQuickResponse(resp,
                    HttpServletResponse.SC_NOT_FOUND, "Not Found");
            return;
        }

        String fileName = uriParts[1];

        // Switch content type of response body according to type of requested file:
        if (!ServletUtil.setResponseContentType(resp, fileName)) {
            ServletUtil.setQuickResponse(resp,
                    HttpServletResponse.SC_NOT_FOUND, "Not Found");
            return;
        }

        // Uses MainController object to load  public file to response body:
        try {
            byte[] loadedBytes = mainControllerRef.loadPublicFile(fileName);
            resp.getOutputStream().write(loadedBytes);
        } catch (LoadFileException lfe) {
            lfe.printStackTrace();
            ServletUtil.setQuickResponse(resp,
                    HttpServletResponse.SC_NOT_FOUND, "Not Found");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            ServletUtil.setQuickResponse(resp,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
            return;
        }

        // If all is OK, sets response HTTP status code to 200:
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
