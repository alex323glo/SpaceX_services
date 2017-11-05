import com.alex323glo.spacex.config.ObjectHolder;
import org.junit.*;

import java.rmi.NoSuchObjectException;

/**
 * Created by alex323glo on 05.11.17.
 */
public class TestObjectHolder {

    private static final String TEST_OBJECT_KEY = "testObject";
    private static final String TEST_OBJECT = "test object string";

    private static ObjectHolder objectHolder;

    @BeforeClass
    public static void prepareClass() {
        objectHolder = ObjectHolder.getInstance();
    }

    @After
    public void refresh() {
        objectHolder.clear();
    }

    @Test
    public void putObject() {
        Assert.assertNull(objectHolder.putObject(TEST_OBJECT_KEY, TEST_OBJECT));
    }

    @Test
    public void putObjectWithDuplicateKey() {
        objectHolder.putObject(TEST_OBJECT_KEY, TEST_OBJECT);
        Assert.assertEquals(TEST_OBJECT, objectHolder.putObject(TEST_OBJECT_KEY, new Object()));
    }

    @Test
    public void getObject() throws NoSuchObjectException {
        objectHolder.putObject(TEST_OBJECT_KEY, TEST_OBJECT);
        Assert.assertEquals(TEST_OBJECT, objectHolder.getObject(TEST_OBJECT_KEY));
    }

    @Test(expected = NoSuchObjectException.class)
    public void getNonExistingObject() throws NoSuchObjectException {
        objectHolder.getObject(TEST_OBJECT_KEY);
    }

}
