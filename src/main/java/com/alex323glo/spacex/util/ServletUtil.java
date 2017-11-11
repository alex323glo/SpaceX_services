package com.alex323glo.spacex.util;

import com.alex323glo.spacex.exception.FilePathException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Container for HttpServlet logic methods in different parts of app.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see javax.servlet.http.HttpServlet
 */
public class ServletUtil {

    /**
     * Sets short HTTP response with needed status code.
     * Response message generated sing response status code
     *
     * @param response HttpServletResponse, which will be sent by Servlet.
     * @param responseStatus HTTP Response status code, which will be set to response.
     * @param responseMessage text of message.
     *
     * @see HttpServletResponse
     * */
    public static void setQuickResponse(HttpServletResponse response, int responseStatus, String responseMessage) {
        if (responseStatus < 400 || responseStatus > 599 || responseMessage == null) {
            responseStatus = 400;
            responseMessage = "Bad Request";
        }
        response.setStatus(responseStatus);
        response.setContentType("text/html");
        try {
            response.getWriter().write("<h1>ERROR " + responseStatus + ": " + responseMessage + "!<h1/>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets HTTP response content type by requested file extension
     * using PathUtil.
     *
     * @param response HttpServletResponse, which will be sent by Servlet.
     * @param fileName request file name, which will be used to recognise content type.
     * @return true, if content type was recognised successfully, and false, if it wasn't.
     *
     * @see PathUtil
     * @see HttpServletResponse
     * */
    public static boolean setResponseContentType(HttpServletResponse response, String fileName) {
        try {
            String contentType = PathUtil.getFileContentType(fileName);
            response.setContentType(contentType);
            return true;
        } catch (FilePathException e) {
            e.printStackTrace();
            return false;
        }
    }

}
