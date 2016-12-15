package com.browserstack.steps;

import com.browserstack.tests.BasicSearchTest;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertEquals;

//public class BasicSearch extends BrowserStackSteps {
public class BasicSearch {

    public static RemoteWebDriver driver;
    public WebElement element;

    public boolean needsToSetUp = true;
    public String stage = "Initial";


    @Before("@browserstack") public void start_session() {
//        System.out.print("Before");
        if (needsToSetUp) {
            driver = BasicSearchTest.getDriver(stage);
            needsToSetUp = false;
        }
        System.out.print("MyDriver: " + driver.getSessionId().toString() + "\n");
    }

    @After("@browserstack") public void close_session() {
        stage = "teardown";
        driver.quit();
    }

    @Given("^I am on Google$")
    public void I_am_on_Google() {
        stage = "I am on Google";
        driver.get("http://www.google.com");
    }

    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void I_fill_in_with(String id, String searchTerm) {
        stage = "I fill in and search";
        element = driver.findElement(By.name(id));
        element.sendKeys(searchTerm);
    }

    @When("^I submit$")
    public void I_submit() {
        stage = "I submit";
        element.submit();
    }

    @Then("^I should see the title \"([^\"]*)\"$")
    public void I_should_see_the_title(String expectedTitle) throws InterruptedException {
        stage = "I validate title";
        Thread.sleep(2000);
        assertEquals(expectedTitle, driver.getTitle());
    }
}
