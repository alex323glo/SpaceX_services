package controller;

import exception.ArgumentValidationException;
import exception.LoadFileException;
import exception.TokenNotExistsException;
import model.User;

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
     *
     * @see User
     * @see ArgumentValidationException
     * */
    void addUser(User user) throws ArgumentValidationException;

    /**
     * Generates access token by entered user's data.
     *
     * @param user user's data, which will be used to generate accessToken.
     * @return accessToken String.
     *
     * @throws ArgumentValidationException when argument doesn't pass validation.
     *
     * @see User
     * @see ArgumentValidationException
     * */
    String verifyUserAccess(User user) throws ArgumentValidationException;

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
