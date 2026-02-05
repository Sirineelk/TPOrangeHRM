package configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;


public class Hooks {

    @Before
    public static void setUp() {
        DriverFactory.getDriver();
    }

    @After
    public static void tearDown() {
        DriverFactory.quitDriver();
    }
}
