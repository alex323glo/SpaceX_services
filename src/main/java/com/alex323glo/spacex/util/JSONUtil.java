package com.alex323glo.spacex.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by alex323glo on 10.11.17.
 */
public class JSONUtil {

    private static Gson gson;

    public static boolean toFile(String filePath, Object object, Type objectType) throws IOException {

        String stringJSON = toString(object, objectType);

        FileUtil.writeTextFile(filePath, new String[]{stringJSON}); // throws IOException !
        return true;
    }

    public static String toString(Object object, Type objectType) {
        if (object == null || objectType == null) {
            throw new NullPointerException("object or objectType is null");
        }

        if (gson == null) {
            gson = new Gson();
        }

        return gson.toJson(object, objectType);
    }

    public static <T> T fromFile(String filePath, Type objectType) {
        if (filePath == null) {
            throw new NullPointerException("filePath is null");
        }

        try {
            String stringJSON = FileUtil.readTextFile(filePath);
            return fromString(stringJSON, objectType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static  <T> T fromString(String stringJSON, Type objectType) {
        if (stringJSON == null || objectType == null) {
            throw new NullPointerException("stringJSON or objectType is null");
        }

        if (gson == null) {
            gson = new Gson();
        }

        return gson.fromJson(stringJSON, objectType);
    }

}
