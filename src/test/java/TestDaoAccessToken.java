import com.alex323glo.spacex.dao.AccessTokenDaoJSON;
import com.alex323glo.spacex.dao.Dao;
import com.alex323glo.spacex.model.user.AccessToken;
import com.alex323glo.spacex.util.JSONUtil;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by alex323glo on 12.11.17.
 */
public class TestDaoAccessToken {

    private static final String DB_ROOT = "src/test/resources/json_db/access_token/";

    private static final AccessToken[] ACCESS_TOKENS = {
            new AccessToken("token_1", "e1@gmail.com"),
            new AccessToken("token_2", "e2@gmail.com"),
            new AccessToken("token_3", "e3@gmail.com"),
    };

    private static final String[] PATHS = {
            DB_ROOT + ACCESS_TOKENS[0].getToken() + ".json",
            DB_ROOT + ACCESS_TOKENS[1].getToken() + ".json",
            DB_ROOT + ACCESS_TOKENS[2].getToken() + ".json"
    };

    private static Dao<AccessToken> accessTokenDao;

    @BeforeClass
    public static void prepareTests() throws IOException {
        for (int i = 0; i < ACCESS_TOKENS.length; i++) {
            JSONUtil.toFile(PATHS[i],
                    ACCESS_TOKENS[i],
                    AccessToken.class);
        }
    }

    @Before
    public void prepare() {
        accessTokenDao = AccessTokenDaoJSON.createInstance(DB_ROOT);
    }

    @AfterClass
    public static void cleanAfterTests() throws IOException {
        for (String PATH : PATHS) {
            File file = new File(PATH);
            if (file.exists() && file.isFile()) {
                if (!file.delete()) {
                    throw new IOException("can't delete file " + PATH);
                }
            }
        }
    }


    // Tests:

    @Test
    public void create() {
        AccessToken testAccessToken = new AccessToken("test", "test@gmail.com");
        Assert.assertTrue(accessTokenDao.create(testAccessToken));
        Assert.assertEquals(testAccessToken, JSONUtil.fromFile(DB_ROOT + "test.json", AccessToken.class));

        File file = new File(DB_ROOT + "test.json");
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    @Test
    public void createNullRecord() {
        Assert.assertFalse(accessTokenDao.create(null));
    }

    @Test
    public void createNullId() {
        Assert.assertFalse(accessTokenDao.create(new AccessToken(null, "email@gmail.com")));
    }

    @Test
    public void read() {
        for (AccessToken ACCESS_TOKEN : ACCESS_TOKENS) {
            Assert.assertEquals(ACCESS_TOKEN, accessTokenDao.read(ACCESS_TOKEN.getToken()));
        }
    }

    @Test
    public void readNonExistingId() {
        Assert.assertNull(accessTokenDao.read("non_existing_access_token"));
    }

    @Test(expected = NullPointerException.class)
    public void readNullId() {
        accessTokenDao.read(null);
    }

    @Test
    public void update() throws IOException {
        AccessToken testAccessToken = new AccessToken("test", "test@gmail.com");
        AccessToken anotherTestAccessToken = new AccessToken("test", "another_test@gmail.com");
        accessTokenDao.create(testAccessToken);

        Assert.assertEquals(testAccessToken, accessTokenDao.update(testAccessToken.getToken(),
                anotherTestAccessToken));
        Assert.assertEquals(anotherTestAccessToken, JSONUtil.fromFile(DB_ROOT + "test.json",
                AccessToken.class));

        File file = new File(DB_ROOT + "test.json");
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    @Test(expected = NullPointerException.class)
    public void updateWithNullId() {
        accessTokenDao.update(null, new AccessToken("token", "email@gmail.com"));
    }

    @Test(expected = NullPointerException.class)
    public void updateWithWrongId() {
        accessTokenDao.update("token", new AccessToken("another_token", "email@gmail.com"));
    }

    @Test
    public void updateNonExistingRecord() {
        String testToken = "non_existing_token";
        Assert.assertNull(accessTokenDao.update(testToken,
                new AccessToken(testToken, "email@gmail.com")));
    }

    @Test
    public void delete() {
        AccessToken testAccessToken = new AccessToken("test", "email@gmail.com");
        accessTokenDao.create(testAccessToken);

        Assert.assertEquals(testAccessToken, accessTokenDao.delete(testAccessToken.getToken()));

        File file = new File(DB_ROOT + "test.json");
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    @Test(expected = NullPointerException.class)
    public void deleteWithNullId() {
        accessTokenDao.delete(null);
    }

    @Test
    public void deleteNonExistingRecord() {
        Assert.assertNull(accessTokenDao.delete("non_existing_token"));
    }

    @Test
    public void allKeys() {
        Set<String> keySet = accessTokenDao.allKeys();
        for (int i = 0; i < ACCESS_TOKENS.length; i++) {
            Assert.assertTrue(keySet.contains(ACCESS_TOKENS[i].getToken()));
        }
    }

    @Test
    public void allRecords() {
        List<AccessToken> recordList = accessTokenDao.allRecords();
        for (AccessToken ACCESS_TOKEN : ACCESS_TOKENS) {
            Assert.assertTrue(recordList.contains(ACCESS_TOKEN));
        }
    }

}
