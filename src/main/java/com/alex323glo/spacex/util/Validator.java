package com.alex323glo.spacex.util;

import com.alex323glo.spacex.exception.ArgumentValidationException;
import com.alex323glo.spacex.model.user.User;

/**
 * Container for methods of validation in different parts of
 * logic of app.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class Validator {

    public static void validateUser(User user) throws ArgumentValidationException {
        if (user == null) {
            throw new ArgumentValidationException("user is null");
        }

        if (user.getEmail() == null) {
            throw new ArgumentValidationException("user.email is null");
        }
    }

}
