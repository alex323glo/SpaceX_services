package com.alex323glo.spacex.rest;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

// TODO create doc
/**
 * Created by alex323glo on 27.10.17.
 */
public class LoadPublicFilesServlet extends HttpServlet {

    private static final String DEFAULT_PUBLIC_RESOURCES_ROOT_PATH = "global/public";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] uriParts = req.getRequestURI().split("public");

        // when request is bad:
        if (uriParts == null || uriParts.length < 2 || uriParts[1].length() < 3) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("<h1>404: Not Found</h1>");
            return;
        }

        String relativeFilePath = DEFAULT_PUBLIC_RESOURCES_ROOT_PATH + uriParts[1];

        try {
            String contentType = "text/html;charset=UTF-8";

            String ext = relativeFilePath.split("\\.")[1];
            if (ext.equals("css")) {
                contentType = "text/css";
            } else if (ext.equals("js")) {
                contentType = "script";
            }

            resp.setContentType(contentType);

            resp.getOutputStream().write(IOUtils.toByteArray(new FileInputStream(relativeFilePath)));
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("<h1>404: Not Found</h1>");
        }
    }
}
