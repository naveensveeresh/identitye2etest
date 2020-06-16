package com.identitye2etest.pages;

import com.identitye2etest.shared.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarTaxCheckPage extends BasePage {
    String carTaxCheckURL = "https://cartaxcheck.co.uk/";

    private final String enterRegistraionCSS = "#vrm-input";
    private final String freeCarCheckButton = "#vehicle-search > button";
    private final String registraionXPath = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[1]/dd";
    private final String makeXPath = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[2]/dd";
    private final String moduleXPath = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[3]/dd";
    private final String colourXPath = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[4]/dd";
    private final String yearXPath = "//*[@id=\"m\"]/div/div[3]/div[1]/div/span/div[2]/dl[5]/dd";

    public void openUrl() {
        driver.get(carTaxCheckURL);
    }

    public void enterRegistration(String carRegistration) {
        driver.findElement(By.cssSelector(enterRegistraionCSS)).sendKeys(carRegistration);
    }

    public void clickFreeCarCheck() {
        driver.findElement(By.cssSelector(freeCarCheckButton)).click();
    }

    public String getRegistration() {
        try {
            return driver.findElement(By.xpath(registraionXPath)).getText();
        } catch (NoSuchElementException e) {
            return "not found";
        }
    }

    public String getMake() {
        try {
            return driver.findElement(By.xpath(makeXPath)).getText();
        } catch (NoSuchElementException e) {
            return "not found";

        }
    }

    public String getModel() {
        String model = "not found";
        try {
            model = driver.findElement(By.xpath(moduleXPath)).getText();
        } catch (NoSuchElementException e) {

        } finally {
            return model;
        }
    }

    public String getColour() {
        String colour = "not found";
        try {
            colour = driver.findElement(By.xpath(colourXPath)).getText();
        } catch (NoSuchElementException e) {

        } finally {
            return colour;
        }
    }

    public String getYear() {
        String year = "not found";
        try {
            year = driver.findElement(By.xpath(yearXPath)).getText();
        } catch (NoSuchElementException e) {

        } finally {
            return year;
        }
    }

}
