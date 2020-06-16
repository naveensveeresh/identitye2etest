package com.identitye2etest.shared.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverProvider {
    Boolean headless = false;

    ChromeDriverProvider(Boolean headless) {
        this.headless = headless;
    }

    public WebDriver getDriver() {
        return new ChromeDriver(getChromeOptions());
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if(headless)
            options.addArguments("--headless");

        return options;
    }
}
