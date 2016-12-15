package com.browserstack.steps;
import com.browserstack.local.Local;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.browserstack.tests.BasicSearchTest;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackSteps {

//    public static RemoteWebDriver driver;
//    public WebElement element;
//
//    @Before("@browserstack")
//    public void start_session() {
//        driver = BasicSearchTest.getDriver("browser");
//    }
//
//    @After
//    public void close_session() {
//        driver.quit();
//    }

}