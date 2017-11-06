package com.alex323glo.spacex.util;

import com.alex323glo.spacex.exception.FilePathException;

/**
 * Container for methods of path processing in different parts of
 * logic of app.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class PathUtil {

    public static String getFileContentType(String path) throws FilePathException {
        if (path == null || !path.contains(".")) {
            throw new FilePathException("no extension detected in file path \"" + path + "\"");
        }
        String extension = path.split("\\.")[1];
        switch (extension) {
            // For "*.css" files:
            case "css":
                return "text/css";

            // For "*.js" files:
            case "js":
                return "script";

            // For "*.html" files:
            case "html":
                return "text/html;charset=UTF-8";

            // For "img", "png" and "jpg" files:
            case "img":
            case "png":
            case "jpg":
                return "image/png";

            // For other files:
            default:
                return "text/html";
        }
    }

}
