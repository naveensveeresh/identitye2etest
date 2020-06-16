package com.identitye2etest.shared.webdriver;

public enum Driver {
    CHROME("chrome"),
    SAFARI("safari"),
    FIREFOX("firefox"),
    INTERNET_EXPLORER("ie"),
    EDGE("edge"),
    CHROME_HEADLESS("chrome_headless");

    private String browser;

    Driver(String browser) {
        this.browser = browser;
    }

    public String browser() {
        return browser;
    }
}
