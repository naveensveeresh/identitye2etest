package com.identitye2etest.shared.webdriver;

import com.identitye2etest.shared.configuration.ConfigUtilities;
import org.openqa.selenium.WebDriver;

public class WebDriverProvider {
    private static WebDriverProvider INSTANCE;
    private WebDriver webDriver = null;
    private String browser;

    private WebDriverProvider() {
        browser = getBrowserName();
    }

    public static WebDriverProvider getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new WebDriverProvider();
        }

        return INSTANCE;
    }

    private String getBrowserName() {
        String browser;
        //Read from command line. If it is not provided then read from properties file.
        browser = System.getProperty("webdriver", ConfigUtilities.getWebdriverBrowser());

        return browser;
    }

    public WebDriver getDriver() {
        if(webDriver != null) {
            return webDriver;
        }

        if(browser.toLowerCase().trim().equals(Driver.CHROME.browser())) {
            webDriver = new ChromeDriverProvider(false).getDriver();
        }
        else if(browser.toLowerCase().trim().equals(Driver.CHROME_HEADLESS.browser())) {
            webDriver = new ChromeDriverProvider(true).getDriver();
        }
        return webDriver;
    }

    public void tearDown() {
        webDriver.close();
        webDriver.quit();
    }
}
