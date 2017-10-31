import com.alex323glo.spacex.config.ConfigHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by alex323glo on 31.10.17.
 */
//@Ignore
public class TestConfigHolder {

    private static ConfigHolder configHolder;

    @Before
    public void prepare() throws IOException {
        configHolder = ConfigHolder.getInstance();
    }

    @Test
    public void getAllProperties() {
        String[] keys = {
                "app.host",
                "app.port",
                "app.global.load",
                "app.global.public",
                "app.global.private"
        };
        String[] expectedValues = {
                "localhost",
                "8000",
                "global/load/",
                "global/public/",
                "global/private/"
        };

        String[] results = new String[keys.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = configHolder.getProperty(keys[i]);
        }

        Assert.assertArrayEquals(expectedValues, results);
    }

    @Test
    public void getNonExistingProperty() {
        Assert.assertNull(configHolder.getProperty("..."));
    }

}
