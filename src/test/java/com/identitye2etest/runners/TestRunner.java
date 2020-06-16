package com.identitye2etest.runners;


import com.identitye2etest.shared.webdriver.WebDriverProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources/features/",
        glue = "com.identitye2etest.steps"
)

public class TestRunner {
    @BeforeClass
    public static void executeBeforeTests() {
        WebDriverProvider webDriverProvider = WebDriverProvider.getInstance();
        WebDriver driver = webDriverProvider.getDriver();
    }

    @AfterClass
    public static void tearDown() {
        WebDriverProvider webDriverProvider = WebDriverProvider.getInstance();
        webDriverProvider.tearDown();
    }
}
