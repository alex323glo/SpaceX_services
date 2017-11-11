import com.alex323glo.spacex.config.ConfigHolder;
import com.alex323glo.spacex.controller.MainController;
import com.alex323glo.spacex.controller.MainControllerImpl;
import com.alex323glo.spacex.dao.AccessTokenDaoJSON;
import com.alex323glo.spacex.dao.Dao;
import com.alex323glo.spacex.dao.UserDaoJSON;
import com.alex323glo.spacex.exception.*;
import com.alex323glo.spacex.model.user.AccessToken;
import com.alex323glo.spacex.model.user.User;
import com.alex323glo.spacex.model.user.UserBuilder;
import com.alex323glo.spacex.util.FileUtil;
import org.junit.*;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by alex323glo on 30.10.17.
 */

public class TestMainController {

    private static final String TEST_PUBLIC_FILE_NAME = "test_public_file.test";
    private static final String TEST_PUBLIC_FILE_PATH =
            ConfigHolder.getInstance().getProperty("app.global.public") + TEST_PUBLIC_FILE_NAME;

    private static final String TEST_PRIVATE_FILE_NAME = "test_private_file.test";
    private static final String TEST_PRIVATE_FILE_PATH =
            ConfigHolder.getInstance().getProperty("app.global.private") + TEST_PRIVATE_FILE_NAME;

    private static final byte[] testByteArray = {1, 2, 3, 4, 5};

    private static MainController mainController;

    private static User testCorrectUser = new UserBuilder()
            .email("email")
            .password("password")
            .userInfoMap(new HashMap<>())
            .build();

    private static AccessToken testAccessToken = new AccessToken("111", testCorrectUser.getEmail());

    @BeforeClass
    public static void prepareClass() throws ArgumentValidationException, DAOException {

        String[] accessTokenDaoKeys = new String[]{"a1-b2-c3"};

        Dao<User> userDao = Mockito.mock(UserDaoJSON.class);
        Mockito.when(userDao.create(Mockito.any())).thenReturn(true);
        Mockito.when(userDao.read(testCorrectUser.getEmail())).thenReturn(testCorrectUser);

        Dao<AccessToken> accessTokenDao = Mockito.mock(AccessTokenDaoJSON.class);
        Mockito.when(accessTokenDao.allKeys()).thenReturn(new HashSet<>(Arrays.asList(accessTokenDaoKeys)));
        Mockito.when(accessTokenDao.create(Mockito.any())).thenReturn(true);
        Mockito.when(accessTokenDao.read(testAccessToken.getToken())).thenReturn(testAccessToken);
        Mockito.when(accessTokenDao.delete(testAccessToken.getToken())).thenReturn(testAccessToken);

        mainController = MainControllerImpl.create(userDao, accessTokenDao); // throws ArgumentValidationException !

    }

    @Before
    public void prepare() {
        testCorrectUser = new UserBuilder()
                .email("email")
                .password("password")
                .userInfoMap(new HashMap<>())
                .build();

        testAccessToken = new AccessToken("111", testCorrectUser.getEmail());
    }

    @Test
    public void addUser()
            throws ArgumentValidationException, DAOException {
        mainController.addUser(new User("email1", "pass1"));
    }

    @Test(expected = DaoRecordOverrideException.class)
    public void addDuplicateUser()
            throws ArgumentValidationException, DAOException {
        // userDao already contains such user...
        mainController.addUser(testCorrectUser);
    }

    @Test(expected = ArgumentValidationException.class)
    public void addNullUser()
            throws ArgumentValidationException, DAOException {
        mainController.addUser(null);
    }

    @Test
    public void verifyUserAccess()
            throws ArgumentValidationException, DAOException {
        // userDao already contains such user...
        Assert.assertNotNull(mainController.verifyUserAccess(testCorrectUser));
    }

    @Test(expected = DaoRecordNotFoundException.class)
    public void verifyUserAccessWithNonRegisteredUser()
            throws ArgumentValidationException, DAOException {
        mainController.verifyUserAccess(new User("email1", "pass1"));
    }

    @Test(expected = ArgumentValidationException.class)
    public void verifyUserAccessWithNullUser()
            throws ArgumentValidationException, DAOException {
        mainController.verifyUserAccess(null);
    }

    @Test
    public void getAccessTokenTarget()
            throws DaoRecordNotFoundException, ArgumentValidationException {
        // accessTokenDao already contains such access token...
        Assert.assertNotNull(mainController.getAccessTokenTarget(testAccessToken.getToken()));
    }

    @Test(expected = DaoRecordNotFoundException.class)
    public void getAccessTokenTargetFromNotExistingToken()
            throws DaoRecordNotFoundException, ArgumentValidationException {
        mainController.getAccessTokenTarget("some_key");
    }

    @Test(expected = ArgumentValidationException.class)
    public void getAccessTokenTargetFromNull()
            throws DaoRecordNotFoundException, ArgumentValidationException {
        mainController.getAccessTokenTarget(null);
    }

    @Test
    public void restrictUserAccess()
            throws DaoRecordNotFoundException, ArgumentValidationException {
        // accessTokenDao already contains such access token...
        mainController.restrictUserAccess(testAccessToken.getToken());
    }

    @Test(expected = DaoRecordNotFoundException.class)
    public void restrictUserAccessWithNonExistingToken()
            throws DaoRecordNotFoundException, ArgumentValidationException {
        mainController.restrictUserAccess("som access token");
    }

    @Test(expected = ArgumentValidationException.class)
    public void restrictUserAccessWithNull()
            throws DaoRecordNotFoundException, ArgumentValidationException {
        mainController.restrictUserAccess(null);
    }

    @Test
    public void loadPublicFile() throws IOException, ArgumentValidationException, LoadFileException {

        prepareTestFile(TEST_PUBLIC_FILE_PATH, testByteArray);  // throws IOException !

        byte[] buffer = mainController.loadPublicFile(TEST_PUBLIC_FILE_NAME);
        Assert.assertNotNull(buffer);

        deleteTestFile(TEST_PUBLIC_FILE_PATH);
    }

    @Test(expected = LoadFileException.class)
    public void loadNonExistingPublicFile() throws IOException, ArgumentValidationException, LoadFileException {
        mainController.loadPublicFile(TEST_PUBLIC_FILE_NAME);
    }

    @Test(expected = ArgumentValidationException.class)
    public void loadPublicFileWithNullFileName() throws IOException, ArgumentValidationException, LoadFileException {
        mainController.loadPublicFile(null);
    }

    @Test
    public void loadPrivateFile()
            throws IOException, DAOException, ArgumentValidationException, LoadFileException, TokenNotExistsException {
        // accessTokenDao already contains such access token...
        prepareTestFile(TEST_PRIVATE_FILE_PATH, testByteArray); // throws IOException !

        Assert.assertNotNull(mainController.loadPrivateFile(TEST_PRIVATE_FILE_NAME, testAccessToken.getToken()));

        deleteTestFile(TEST_PRIVATE_FILE_PATH);
    }

    @Test(expected = TokenNotExistsException.class)
    public void loadPrivateFileWithNonExistingToken()
            throws IOException, ArgumentValidationException, LoadFileException, TokenNotExistsException {

        prepareTestFile(TEST_PRIVATE_FILE_PATH, testByteArray);

        mainController.loadPrivateFile(TEST_PRIVATE_FILE_NAME, "wrong_access_token");

        deleteTestFile(TEST_PRIVATE_FILE_PATH);
    }

    @Test(expected = LoadFileException.class)
    public void loadNonExistingPrivateFile()
            throws IOException, DAOException, ArgumentValidationException, LoadFileException, TokenNotExistsException {

        mainController.loadPrivateFile(TEST_PRIVATE_FILE_NAME, testAccessToken.getToken());
    }

    @Test(expected = ArgumentValidationException.class)
    public void loadPrivateFileByNullArguments()
            throws IOException, ArgumentValidationException, LoadFileException, TokenNotExistsException {
        mainController.loadPrivateFile(null, null);
    }



    // Test preparation private methods:

    private static void prepareTestFile(String path, byte[] content) throws IOException {
        FileUtil.writeByteFile(path, content);
    }

    private static void deleteTestFile(String path) {
        File testFile = new File(path);
        testFile.delete();
    }
}
