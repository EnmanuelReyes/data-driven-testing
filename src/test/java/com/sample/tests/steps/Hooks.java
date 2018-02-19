package com.sample.tests.steps;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 01/17/18
 * Time: 10:38 AM
 */
public class Hooks {

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
        Driver.current().get(Configuration.get("url"));
    }

    @After
    public void tearDown() {
        Driver.current().quit();
    }

}
