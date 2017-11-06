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
import java.io.IOException;

/**
 * HTTP Servlet, which serves AJAX GET requests.
 * Request URL: "/load-ajax/*"
 *
 * @author alex323glo
 * @version 1.0.0
 * @see HttpServlet
 */
public class LoadAjaxServlet extends HttpServlet {

    private static final String AJAX_ROOT_SUFFIX = "ajax/";

    /**
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request.
     * Here "id" parameter stands for filename (with extension in the end).
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Gets ref to MainController object using ObjectHolder:
        MainController mainControllerRef =
                (MainController) ObjectHolder.getInstance().getObject("mainController");
        if (mainControllerRef == null) {
            ServletUtil.setQuickResponse(resp,
                    HttpServletResponse.SC_NOT_IMPLEMENTED, "Not Implemented");
            return;
        }

        // Get AJAX file name from request parameters:
        String ajaxFileName = req.getParameter("id");
        if (ajaxFileName == null) {
            ServletUtil.setQuickResponse(resp,
                    HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
            return;
        }

        // Switch content type of response body according to type of requested file:
        if (!ServletUtil.setResponseContentType(resp, ajaxFileName)) {
            ServletUtil.setQuickResponse(resp,
                    HttpServletResponse.SC_NOT_FOUND, "Not Found");
            return;
        }

        // Uses MainController object to load AJAX (public) file to response body:
        try {
//            resp.getOutputStream().write(
//                    IOUtils.toByteArray(new FileInputStream(relativePath))
//            );
            String relativePath = AJAX_ROOT_SUFFIX + ajaxFileName;
            byte[] loadedBytes = mainControllerRef.loadPublicFile(relativePath);
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
