package com.browserstack.tests;

import com.browserstack.local.Local;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features/BasicSearch.feature"},
        glue = {"com.browserstack.steps"}
        )
//public class BasicSearchTest extends BrowserStackTest {
public class BasicSearchTest extends AbstractTestNGCucumberTests {
//    public class BasicSearchTest {


    private static RemoteWebDriver driver;
    public static Local l;

    @BeforeTest
    @org.testng.annotations.Parameters(value={"config", "environment"})
    public static void setUp (String config_file, String environment) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + config_file));
        JSONObject envs = (JSONObject) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities= (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) config.get("user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("key");
        }

        if (capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true") {
            l = new Local();
            Map<String, String> options = new HashMap<String, String>();
            options.put("key", accessKey);
            l.start(options);
        }

        driver = new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
        System.out.print("Driver created: " + driver.getSessionId().toString() + "\n");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



    }

    @Test
    public static RemoteWebDriver getDriver(String stage) {
//        System.out.print("Test received driver: " + stage + ", " + driver.getSessionId().toString() + "\n");
        return driver;
    }

}