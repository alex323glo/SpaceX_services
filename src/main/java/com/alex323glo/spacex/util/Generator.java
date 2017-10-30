package com.alex323glo.spacex.util;

import java.util.Set;
import java.util.UUID;

/**
 * Container of methods for value generating in different parts of
 * logic of app.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class Generator {

    public static String generateAccessToken(Set<String> tokenSet) {
        String token = UUID.randomUUID().toString();
        while (tokenSet.contains(token)) {
            token = UUID.randomUUID().toString();
        }
        return token;
    }

}
