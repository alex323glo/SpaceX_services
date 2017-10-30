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
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordOverrideException when argument's id is already in use in DB.
     * @throws DAOException when Dao can't write new user data to DB.
     *
     * @see User
     * @see ArgumentValidationException
     * @see DaoRecordOverrideException
     * @see DAOException
     * */
    void addUser(User user)
            throws ArgumentValidationException, DaoRecordOverrideException, DAOException;

    /**
     * Generates access token by entered user's data.
     *
     * @param user user's data, which will be used to generate accessToken.
     * @return new accessToken String.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException when DB doesn't contain such user.
     * @throws DAOException when Dao can't read user data from DB by entered key.
     *
     * @see User
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     * @see DAOException
     * */
    String verifyUserAccess(User user)
            throws ArgumentValidationException, DaoRecordNotFoundException, DAOException;

    /**
     * Finds target identifier (User email) of Access Token.
     *
     * @param accessToken Access Token String which we are searching for in DB.
     * @return target identifier (user email) of Access Token.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException when DB doesn't contain such Access Token.
     * @throws DAOException when Dao can't read record from DB.
     *
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     * @see DAOException
     * */
    String getAccessTokenTarget(String accessToken)
            throws ArgumentValidationException, DaoRecordNotFoundException, DAOException;

    /**
     * Destroys entered access token in DB.
     *
     * @param accessToken access token String, which will be removed from DB.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException when DB doesn't contain such accessToken record.
     * @throws DAOException when Dao can't read record from DB.
     *
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     * @see DAOException
     * */
    void restrictUserAccess(String accessToken)
            throws ArgumentValidationException, DaoRecordNotFoundException, DAOException;

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
