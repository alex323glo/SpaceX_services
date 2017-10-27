package controller;

import exception.EmailDuplicationException;
import exception.ArgumentValidationException;
import exception.AccessRestrictionException;
import exception.EmailNotExistsException;
import exception.TokenNotExistsException;
import model.UserInfo;

/**
 * Business logic of User actor.
 *
 * @author alex323glo
 * @version 1.0.0
 * */
public interface UserController {

    /**
     * Registers (signs in) new user in the system.
     * 
     * @param email user's email, which must be unique.         
     * @param password user's password.
     * @return accessToken, generated after client's 
     * successful registration.
     * 
     * @throws EmailDuplicationException if entered email is 
     * not unique - system's DB contains registered user with
     * equal email.
     * @throws ArgumentValidationException when any argument doesn't pass validation.
     * 
     * @see EmailDuplicationException
     * * */
    String signIn(String email, String password)
            throws EmailDuplicationException, ArgumentValidationException;

    /**
     * Gives access to client as registered user.
     * 
     * @param email user's email, equal to target registered user.
     * @param password user's password, equal to target registered user.
     * @return accessToken, generated after client's successful log in.
     * 
     * @throws AccessRestrictionException if entered email and/or password are
     * not valid or didn't pass verification.
     * @throws ArgumentValidationException when any argument doesn't pass validation.
     * 
     * @see AccessRestrictionException
     * @see ArgumentValidationException
     * */
    String logIn(String email, String password)
            throws AccessRestrictionException, ArgumentValidationException;

    /**
     * Updates personal info container of registered user.
     * 
     * @param email unique email of User in system's DB, whose info we want to update.
     * @param userInfo part of user info, which will be updated in system's DB.
     * @return token, which informs client about successful update.
     * 
     * @throws EmailNotExistsException when system's DB doesn't contain registered user 
     * with email equal to entered one.
     * @throws ArgumentValidationException when any argument doesn't pass validation.
     *
     * @see UserInfo
     * @see EmailNotExistsException
     * @see ArgumentValidationException
     * */
    String addPersonalInfo(String email, UserInfo userInfo) 
            throws EmailNotExistsException, ArgumentValidationException;

    /**
     * Loges user out. Destroys access token, when client, who used it before 
     * to access service as registered user, loges out from system.
     * 
     * @param email email of user in system's DB, which client used to access services.
     * @param accessToken token which will be marked as not valid - will be removed from list
     *                    of accessTokens in system's DB.
     * @return token, which informs client about successful log out.
     * 
     * @throws EmailNotExistsException when system's DB doesn't contain registered user
     * with email equal to entered one.
     * @throws TokenNotExistsException when system's DB doesn't contain such accessToken.
     * @throws ArgumentValidationException when any argument doesn't pass validation.
     *
     * @see EmailNotExistsException
     * @see TokenNotExistsException
     * @see ArgumentValidationException
     * */
    String logOut(String email, String accessToken)
            throws EmailNotExistsException, TokenNotExistsException, ArgumentValidationException;
}
