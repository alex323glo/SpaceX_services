package com.alex323glo.spacex.controller;

import com.alex323glo.spacex.config.ConfigHolder;
import com.alex323glo.spacex.dao.Dao;
import com.alex323glo.spacex.exception.*;
import com.alex323glo.spacex.model.user.AccessToken;
import com.alex323glo.spacex.model.user.User;
import com.alex323glo.spacex.util.FileUtil;
import com.alex323glo.spacex.util.Generator;
import com.alex323glo.spacex.util.PathUtil;
import com.alex323glo.spacex.util.Validator;

import java.io.IOException;

/**
 * Implementation of MainController interface.
 *
 * @author alex323glo
 * @version 1.0.0
 * @see MainController
 * @see Dao
 */
public class MainControllerImpl implements MainController {

    private Dao<User> userDao;
    private Dao<AccessToken> accessTokenDao;

    /**
     * Blocked empty constructor.
     * Use create() method instead.
     *
     * @see MainControllerImpl#create(Dao, Dao)
     * */
    private MainControllerImpl() {

    }

    /**
     * Getter for userDao field.
     *
     * @return userDao field value.
     * @see Dao
     * @see User
     * */
    public Dao<User> getUserDao() {
        return userDao;
    }

    /**
     * Setter for userDao field.
     *
     * @param userDao initial userDao field value.
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @see Dao
     * @see User
     * @see ArgumentValidationException
     * */
    public void setUserDao(Dao<User> userDao) throws ArgumentValidationException {
        // validation:
        if (userDao == null) {
            throw new ArgumentValidationException("userDao is null");
        }

        this.userDao = userDao;
    }

    /**
     * Getter for accessTokenDao field.
     *
     * @return accessTokenDao field value.
     * @see Dao
     * @see AccessToken
     * */
    public Dao<AccessToken> getAccessTokenDao() {
        return accessTokenDao;
    }

    /**
     * Setter for accessTokenDao field.
     *
     * @param accessTokenDao initial accessTokenDao field value.
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @see Dao
     * @see AccessToken
     * @see ArgumentValidationException
     * */
    public void setAccessTokenDao(Dao<AccessToken> accessTokenDao) throws ArgumentValidationException {
        if (accessTokenDao == null) {
            throw new ArgumentValidationException("accessTokenDao is null");
        }
        this.accessTokenDao = accessTokenDao;
    }

    /**
     * Writes user data to system's DB.
     *
     * @param user user's data which will be written to system's DB.
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordOverrideException  when argument's id is already in use in DB.
     * @see User
     * @see ArgumentValidationException
     * @see DaoRecordOverrideException
     */
    @Override
    public boolean addUser(User user) throws ArgumentValidationException, DaoRecordOverrideException {

        Validator.validateUser(user);   // throws ArgumentValidationException !

        if (userDao.read(user.getEmail()) != null) {
            throw new DaoRecordOverrideException("writing data with key, which is in use");
        }

        return userDao.create(user);
    }

    /**
     * Generates access token by entered user's data.
     *
     * @param user user's data, which will be used to generate accessToken.
     * @return new accessToken String.
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException  when DB doesn't contain such user.
     * @see User
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     */
    @Override
    public String verifyUserAccess(User user)
            throws ArgumentValidationException, DaoRecordNotFoundException {

        Validator.validateUser(user);   // throws ArgumentValidationException !

        User userFromDb = userDao.read(user.getEmail());
        if (userFromDb == null) {
            throw new DaoRecordNotFoundException("no user with such key in DB");
        }

        String generatedToken =
                Generator.generateAccessToken(accessTokenDao.allKeys());

        accessTokenDao.create(new AccessToken(generatedToken, userFromDb.getEmail()));

        return generatedToken;
    }

    /**
     * Finds target identifier (User email) of Access Token.
     *
     * @param accessToken Access Token String which we are searching for in DB.
     * @return target identifier (user email) of Access Token.
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException  when DB doesn't contain such Access Token.
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     */
    @Override
    public String getAccessTokenTarget(String accessToken)
            throws ArgumentValidationException, DaoRecordNotFoundException {

        if (accessToken == null || accessToken.isEmpty()) {
            throw new ArgumentValidationException("accessToken is null or empty");
        }

        AccessToken target = accessTokenDao.read(accessToken);
        if (target == null) {
            throw new DaoRecordNotFoundException("no access token with such key in DB");
        }

        return target.getUserEmail();
    }

    /**
     * Destroys entered access token in DB.
     *
     * @param accessToken access token String, which will be removed from DB.
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws DaoRecordNotFoundException  when DB doesn't contain such accessToken record.
     * @see ArgumentValidationException
     * @see DaoRecordNotFoundException
     */
    @Override
    public void restrictUserAccess(String accessToken)
            throws ArgumentValidationException, DaoRecordNotFoundException {

        if (accessToken == null || accessToken.isEmpty()) {
            throw new ArgumentValidationException("accessToken is null or empty");
        }

        AccessToken removedToken = accessTokenDao.delete(accessToken);
        if (removedToken == null) {
            throw new DaoRecordNotFoundException("no access token with such key in DB");
        }
    }

    /**
     * Creates a byte array of needed public file from system's resources.
     *
     * @param fileName name of searched public file in system's resources.
     * @return needed public file converted to byte array.
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws LoadFileException           when system has some problems with loading of
     *                                     needed file from system's resources.
     * @see ArgumentValidationException
     * @see LoadFileException
     */
    @Override
    public byte[] loadPublicFile(String fileName)
            throws ArgumentValidationException, LoadFileException {
        // Validation:
        if (fileName == null || fileName.isEmpty()) {
            throw new ArgumentValidationException("fileName is null or empty");
        }

        String publicFilePathRoot = ConfigHolder.getInstance().getProperty("app.global.public");

        try {
            // Checks file type:
            String fileType = PathUtil.getFileContentType(fileName);
            if (fileType.equals("text/html") || fileType.equals("text/html;charset=UTF-8")
                    || fileType.equals("text/css") || fileType.equals("script")) {

                String result = FileUtil.readTextFile(publicFilePathRoot + fileName);
                if (result == null) {
                    throw new LoadFileException("public file \"" + fileName + "\" doesn't exist");
                }
                return result.getBytes();

            } else {

                byte[] loadedBytes = FileUtil.readByteFile(publicFilePathRoot + fileName);
                if (loadedBytes == null) {
                    throw new LoadFileException("public file \"" + fileName + "\" doesn't exist");
                }
                return loadedBytes;

            }
        } catch (FilePathException fpe) {
            fpe.printStackTrace();
            throw new LoadFileException("public file \"" + fileName + "\" has wrong extension");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new LoadFileException("can't load public file \"" + fileName + "\"");
        }
    }

    /**
     * Creates a byte array of needed private file from system's resources.
     *
     * @param fileName    name of searched private file in system's resources.
     * @param accessToken token, which verifies access to needed private file
     *                    from system's resources.
     * @return needed private file converted to byte array.
     * @throws ArgumentValidationException when argument doesn't pass validation.
     * @throws TokenNotExistsException     when system's DB doesn't contain such accessToken.
     * @throws LoadFileException           when system has some problems with loading of
     *                                     needed file from system's resources.
     * @see ArgumentValidationException
     * @see LoadFileException
     */
    @Override
    public byte[] loadPrivateFile(String fileName, String accessToken)
            throws ArgumentValidationException, TokenNotExistsException, LoadFileException {
        // Validation:
        if (fileName == null || fileName.isEmpty()) {
            throw new ArgumentValidationException("fileName is null or empty");
        }
        if (accessToken == null || accessToken.isEmpty()) {
            throw new ArgumentValidationException("accessToken is null or empty");
        }

        if (accessTokenDao.read(accessToken) == null) {
            throw new TokenNotExistsException("no such token registered");
        }

        String privateFilePathRoot = ConfigHolder.getInstance().getProperty("app.global.private");

        try {
            // Checks file type:
            String fileType = PathUtil.getFileContentType(fileName);
            if (fileType.equals("text/html") || fileType.equals("text/html;charset=UTF-8")
                    || fileType.equals("text/css") || fileType.equals("script")) {

                String result = FileUtil.readTextFile(privateFilePathRoot + fileName);
                if (result == null) {
                    throw new LoadFileException("public file \"" + fileName + "\" doesn't exist");
                }
                return result.getBytes();

            } else {

                byte[] loadedBytes = FileUtil.readByteFile(privateFilePathRoot + fileName);
                if (loadedBytes == null) {
                    throw new LoadFileException("public file \"" + fileName + "\" doesn't exist");
                }
                return loadedBytes;

            }
        } catch (FilePathException fpe) {
            fpe.printStackTrace();
            throw new LoadFileException("private file \"" + fileName + "\" has wrong extension");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new LoadFileException("can't load private file \"" + fileName + "\"");
        }
    }

    /**
     * Creates an instance of MainControllerImpl.
     *
     * @param userDao constructor parameter; ref to NotNull Dao<User> instance.
     * @param accessTokenDao constructor parameter; ref to NotNull Dao<AccessToken> instance.
     * @return instance of MainControllerImpl with set Dao<User> and Dao<AccessToken> instances.
     * @throws ArgumentValidationException when arguments don't pass validation.
     * @see Dao
     * @see User
     * @see AccessToken
     * @see ArgumentValidationException
     * */
    public static MainControllerImpl create(Dao<User> userDao, Dao<AccessToken> accessTokenDao)
            throws ArgumentValidationException {
        MainControllerImpl controller = new MainControllerImpl();

        controller.setUserDao(userDao);                 // throws ArgumentValidationException !
        controller.setAccessTokenDao(accessTokenDao);   // throws ArgumentValidationException !

        return controller;
    }

}
