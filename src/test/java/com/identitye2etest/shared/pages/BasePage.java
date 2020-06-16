package com.identitye2etest.shared.pages;

import com.identitye2etest.shared.webdriver.WebDriverProvider;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriverProvider webDriverProvider = WebDriverProvider.getInstance();
    protected WebDriver driver = webDriverProvider.getDriver();
}
