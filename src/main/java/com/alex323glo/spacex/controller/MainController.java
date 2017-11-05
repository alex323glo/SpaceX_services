package com.alex323glo.spacex.controller;

import com.alex323glo.spacex.exception.*;
import com.alex323glo.spacex.model.user.User;
import com.alex323glo.spacex.exception.DaoRecordNotFoundException;

/**
 * Business logic of System actor.
 *
 * @author alex323glo
 * @version 1.0.0
 * */
public interface MainController {

    /**
     * Writes user data to system's DB.
     *
     * @param user user's data which will be written to system's DB.
     * @return true if operation was successful and false - if it wasn't.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordOverrideException when argument's id is already in use in DB.
     *
     * @see User
     * @see ArgumentValidationException
     * @see DaoRecordOverrideException
     * */
    boolean addUser(User user)
            throws ArgumentValidationException, DaoRecordOverrideException;

    /**
     * Generates access token by entered user's data.
     *
     * @param user user's data, which will be used to generate accessToken.
     * @return new accessToken String.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException when DB doesn't contain such user.
     *
     * @see User
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     * */
    String verifyUserAccess(User user)
            throws ArgumentValidationException, DaoRecordNotFoundException;

    /**
     * Finds target identifier (User email) of Access Token.
     *
     * @param accessToken Access Token String which we are searching for in DB.
     * @return target identifier (user email) of Access Token.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException when DB doesn't contain such Access Token.
     *
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     * */
    String getAccessTokenTarget(String accessToken)
            throws ArgumentValidationException, DaoRecordNotFoundException;

    /**
     * Destroys entered access token in DB.
     *
     * @param accessToken access token String, which will be removed from DB.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException when DB doesn't contain such accessToken record.
     *
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     * */
    void restrictUserAccess(String accessToken)
            throws ArgumentValidationException, DaoRecordNotFoundException;

    /**
     * Creates a byte array of needed public file from system's resources.
     *
     * @param fileName name of searched public file in system's resources.
     *
     * @return needed public file converted to byte array.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws LoadFileException when system has some problems with loading of
     * needed file from system's resources.
     *
     * @see ArgumentValidationException
     * @see LoadFileException
     * */
    byte[] loadPublicFile(String fileName) throws ArgumentValidationException, LoadFileException;

    /**
     * Creates a byte array of needed private file from system's resources.
     *
     * @param fileName name of searched private file in system's resources.
     * @param accessToken token, which verifies access to needed private file
     *                    from system's resources.
     *
     * @return needed private file converted to byte array.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws TokenNotExistsException when system's DB doesn't contain such accessToken.
     * @throws LoadFileException when system has some problems with loading of
     * needed file from system's resources.
     *
     * @see ArgumentValidationException
     * @see LoadFileException
     * */
    byte[] loadPrivateFile(String fileName, String accessToken)
            throws ArgumentValidationException, TokenNotExistsException, LoadFileException;
}
