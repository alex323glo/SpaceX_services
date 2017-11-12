package com.alex323glo.spacex.controller;

import com.alex323glo.spacex.exception.*;
import com.alex323glo.spacex.model.user.AccessToken;
import com.alex323glo.spacex.model.user.User;
import com.alex323glo.spacex.model.user.UserBuilder;
import com.alex323glo.spacex.model.user.UserInfo;

// TODO finish implementation of UserController.
/**
 * TODO add doc.
 */
public class UserControllerImpl implements UserController {

    private MainController mainController;

    /**
     * Private Constructor.
     * Better use create() method instead.
     *
     * @see
     * */
    public UserControllerImpl(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * TODO doc.
     * */
    public MainController getMainController() {
        return mainController;
    }

    /**
     * TODO doc.
     * */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Registers (signs in) new user in the system.
     *
     * @param email    user's email, which must be unique.
     * @param password user's password.
     * @return accessToken, generated after client's
     * successful registration.
     * @throws EmailDuplicationException   if entered email is
     *                                     not unique - system's DB contains registered user with
     *                                     equal email.
     * @throws ArgumentValidationException when any argument doesn't pass validation.
     * @see EmailDuplicationException
     */
    @Override
    public String signIn(String email, String password)
            throws EmailDuplicationException, ArgumentValidationException {
        // Adds user to DB:
        User user = new UserBuilder().email(email).password(password).build();
        try {
            mainController.addUser(user);   // throws ArgumentValidationException !
        } catch (DaoRecordOverrideException e) {
            e.printStackTrace();
            throw new EmailDuplicationException("such email already exists in DB");
        }

        // Generates AccessToken String:
        try {
            return mainController.verifyUserAccess(user);   // throws ArgumentValidationException !
        } catch (DaoRecordNotFoundException e) {    // if system has some problems with DAO.
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gives access to client as registered user.
     *
     * @param email    user's email, equal to target registered user.
     * @param password user's password, equal to target registered user.
     * @return accessToken, generated after client's successful log in.
     * @throws AccessRestrictionException  if entered email and/or password are
     *                                     not valid or didn't pass verification.
     * @throws ArgumentValidationException when any argument doesn't pass validation.
     * @see AccessRestrictionException
     * @see ArgumentValidationException
     */
    @Override
    public String logIn(String email, String password)
            throws AccessRestrictionException, ArgumentValidationException {
        // Generates AccessToken String:
        User user = new UserBuilder().email(email).password(password).build();
        try {
            return mainController.verifyUserAccess(user);   // throws ArgumentValidationException !
        } catch (DaoRecordNotFoundException e) {    // if system has some problems with DAO:
            e.printStackTrace();
            throw new AccessRestrictionException("no registered user with such email and pass in DB");
        }
    }

    /**
     * Updates personal info container of registered user.
     *
     * @param email    unique email of User in system's DB, whose info we want to update.
     * @param userInfo part of user info, which will be updated in system's DB.
     * @return token, which informs client about successful update.
     * @throws EmailNotExistsException     when system's DB doesn't contain registered user
     *                                     with email equal to entered one.
     * @throws ArgumentValidationException when any argument doesn't pass validation.
     * @see UserInfo
     * @see EmailNotExistsException
     * @see ArgumentValidationException
     */
    @Override
    public String addPersonalInfo(String email, UserInfo userInfo)
            throws EmailNotExistsException, ArgumentValidationException {
        // Find user in DB:
        User user = ((MainControllerImpl) mainController).getUserDao().read(email);
        if (user == null) {
            throw new EmailNotExistsException("no user with such email in DB");
        }

        // Update user data:
        user.getUserInfoMap().put(userInfo.getType(), userInfo);

        // Update new data to DB:
        ((MainControllerImpl) mainController).getUserDao().update(email, user);

        // TODO update logic of addPersonalInfo(String, UserInfo) method (what to return?)
        return "successful_update";
    }

    /**
     * Loges user out. Destroys access token, when client, who used it before
     * to access service as registered user, loges out from system.
     *
     * @param email       email of user in system's DB, which client used to access services.
     * @param accessToken token which will be marked as not valid - will be removed from list
     *                    of accessTokens in system's DB.
     * @return token, which informs client about successful log out.
     * @throws EmailNotExistsException     when system's DB doesn't contain registered user
     *                                     with email equal to entered one.
     * @throws TokenNotExistsException     when system's DB doesn't contain such accessToken.
     * @throws ArgumentValidationException when any argument doesn't pass validation.
     * @see EmailNotExistsException
     * @see TokenNotExistsException
     * @see ArgumentValidationException
     */
    @Override
    public String logOut(String email, String accessToken)
            throws EmailNotExistsException, TokenNotExistsException, ArgumentValidationException {
        // TODO update logic of logOut(String, String) method (do we need 'email' param? what to return?)
        // Find and remove access token from DB:
        try {
            mainController.restrictUserAccess(accessToken);
            return "removed_token:" + accessToken;
        } catch (DaoRecordNotFoundException e) {
            e.printStackTrace();
            throw new TokenNotExistsException("no such access token in DB");
        }
    }

    /**
     * TODO doc.
     * */
    public static UserControllerImpl create(MainController mainController) {
        if (mainController == null) {
            throw new NullPointerException("mainController is null");
        }

        UserControllerImpl userControllerImpl = new UserControllerImpl(mainController);

        return userControllerImpl;
    }
}
