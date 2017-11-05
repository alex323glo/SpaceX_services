package com.alex323glo.spacex.rest;

import com.alex323glo.spacex.exception.FilePathException;
import org.apache.commons.io.IOUtils;
import com.alex323glo.spacex.util.PathUtil;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO create doc
/**
 * Created by alex323glo on 28.10.17.
 */
public class LoadAjaxServlet extends HttpServlet {

    private static final String DEFAULT_LOAD_AJAX_RESOURCE_ROOT_PATH =
            "global/load/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException {
        String param = req.getParameter("id");
        String contentType = "";

        try {
            contentType = PathUtil.getFileContentType(param);
        } catch (FilePathException e) {
            e.printStackTrace();
            setErrorResp(resp);
            return;
        }

        resp.setContentType(contentType);

        String relativePath = DEFAULT_LOAD_AJAX_RESOURCE_ROOT_PATH + param;

        try {
            resp.getOutputStream().write(
                    IOUtils.toByteArray(new FileInputStream(relativePath))
            );
        } catch (IOException e) {
            e.printStackTrace();
            setErrorResp(resp);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setErrorResp(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.setContentType("text/html");
        try {
            response.getWriter().write("<h1>ERROR 404: Not Found.<h1/>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
