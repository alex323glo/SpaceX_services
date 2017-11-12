import com.alex323glo.spacex.dao.Dao;
import com.alex323glo.spacex.dao.UserDaoJSON;
import com.alex323glo.spacex.model.user.InfoType;
import com.alex323glo.spacex.model.user.User;
import com.alex323glo.spacex.model.user.UserBuilder;
import com.alex323glo.spacex.model.user.UserInfo;
import com.alex323glo.spacex.util.JSONUtil;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by alex323glo on 12.11.17.
 */
public class TestDaoUser {

    private static final String DB_ROOT = "src/test/resources/json_db/user/";

    private static final User[] USERS = {
            new UserBuilder()
                    .email("e1@gmail.com")
                    .password("111")
                    .build(),
            new UserBuilder()
                    .email("e2@gmail.com")
                    .password("222")
                    .info(new UserInfo(InfoType.PHONE, "022-222-22-22"))
                    .build(),
            new UserBuilder()
                    .email("e3@gmail.com")
                    .password("333")
                    .info(new UserInfo(InfoType.USERNAME, "user333"))
                    .build(),
            new UserBuilder()
                    .email("e4@gmail.com")
                    .password("444")
                    .info(new UserInfo(InfoType.USERNAME, "user444"))
                    .info(new UserInfo(InfoType.PHONE, "044-444-44-44"))
                    .build()
    };

    private static final String[] PATHS = {
            DB_ROOT + USERS[0].getEmail() + ".json",
            DB_ROOT + USERS[1].getEmail() + ".json",
            DB_ROOT + USERS[2].getEmail() + ".json",
            DB_ROOT + USERS[3].getEmail() + ".json"
    };

    private static Dao<User> userDao;

    @BeforeClass
    public static void prepareTests() throws IOException {
        for (int i = 0; i < USERS.length; i++) {
            JSONUtil.toFile(PATHS[i],
                    USERS[i],
                    User.class);
        }
    }

    @Before
    public void prepare() {
        userDao = UserDaoJSON.createInstance(DB_ROOT);
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
        User testUser = new UserBuilder().email("email@gmail.com").build();
        Assert.assertTrue(userDao.create(testUser));
        Assert.assertEquals(testUser, JSONUtil.fromFile(DB_ROOT + "email@gmail.com.json", User.class));

        File file = new File(DB_ROOT + "email@gmail.com.json");
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    @Test
    public void createNullRecord() {
        Assert.assertFalse(userDao.create(null));
    }

    @Test
    public void createNullId() {
        Assert.assertFalse(userDao.create(new User(null, "password")));
    }

    @Test
    public void read() {
        for (User USER : USERS) {
            Assert.assertEquals(USER, userDao.read(USER.getEmail()));
        }
    }

    @Test
    public void readNonExistingId() {
        Assert.assertNull(userDao.read("non_existing_email"));
    }

    @Test(expected = NullPointerException.class)
    public void readNullId() {
        userDao.read(null);
    }

    @Test
    public void update() throws IOException {
        User testUser = new UserBuilder().email("test@gmail.com").password("pass").build();
        User anotherTestUser = new UserBuilder().email("test@gmail.com").password("new_pass").build();
        userDao.create(testUser);

        Assert.assertEquals(testUser, userDao.update(testUser.getEmail(), anotherTestUser));
        Assert.assertEquals(anotherTestUser, JSONUtil.fromFile(DB_ROOT + "test@gmail.com.json",
                User.class));

        File file = new File(DB_ROOT + "test@gmail.com.json");
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    @Test(expected = NullPointerException.class)
    public void updateWithNullId() {
        userDao.update(null, new UserBuilder().email("email@gmail.com").build());
    }

    @Test(expected = NullPointerException.class)
    public void updateWithWrongId() {
        userDao.update("email@gmail.com", new UserBuilder().email("another-email@gmail.com").build());
    }

    @Test
    public void updateNonExistingRecord() {
        String testEmail = "non_existing_email";
        Assert.assertNull(userDao.update(testEmail, new UserBuilder().email(testEmail).build()));
    }

    @Test
    public void delete() {
        User testUser = new UserBuilder().email("email@gmail.com").build();
        userDao.create(testUser);

        Assert.assertEquals(testUser, userDao.delete(testUser.getEmail()));

        File file = new File(DB_ROOT + "email@gmail.com.json");
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    @Test(expected = NullPointerException.class)
    public void deleteWithNullId() {
        userDao.delete(null);
    }

    @Test
    public void deleteNonExistingRecord() {
        Assert.assertNull(userDao.delete("non_existing_email"));
    }

    @Test
    public void allKeys() {
        Set<String> keySet = userDao.allKeys();
        for (int i = 0; i < USERS.length; i++) {
            Assert.assertTrue(keySet.contains(USERS[i].getEmail()));
        }
    }

    @Test
    public void allRecords() {
        List<User> recordList = userDao.allRecords();
        for (User USER : USERS) {
            Assert.assertTrue(recordList.contains(USER));
        }
    }

}
