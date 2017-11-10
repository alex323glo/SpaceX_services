package com.alex323glo.spacex.util;

// TODO doc.
/**
 * Created by alex323glo on 10.11.17.
 */
public class LogUtil {

    public static void printMessage(Object actor, String logMessage){
        System.out.println(" * " + createLogMessage(actor, logMessage));
    }

    public static String createLogMessage(Object actor, String logMessage) {
        if (actor == null || logMessage == null) {
            return null;
        }

        return String.format("%s(%s): %s", actor.getClass().getSimpleName(), actor.hashCode(), logMessage);
    }

}
