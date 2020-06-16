package com.identitye2etest.shared.configuration;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtilities {
    private static Properties environmentProperties = new Properties();

    private final static String environmentPropertiesPath = "src/test/resources/properties/local.properties";
    private final static String driverProperty = "webdriver.browser";

    private final static String DEFUALT_BROWSER="chrome";

    static {
        initConfig();
    }

    public static String getWebdriverBrowser() {

        String browser = environmentProperties.getProperty(driverProperty);
        if( browser == null || (0 == browser.length()) )
            browser = DEFUALT_BROWSER;

        return browser;
    }

    private static void initConfig() {
        try {
            InputStream is = new FileInputStream(environmentPropertiesPath);
            environmentProperties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Could not load properties", e);
        }
    }


}
