package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Page Object for Basic Auth page
 * TC-001: Basic Auth
 */
public class BasicAuthPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/basic_auth";
    private static final String AUTH_URL = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
    
    private By successMessage = By.cssSelector("div.example p");
    
    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateWithAuth() {
        driver.get(AUTH_URL);
    }
    
    public String getSuccessMessage() {
        WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
        return message.getText();
    }
    
    public boolean isSuccessMessageDisplayed() {
        try {
            WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
            return message.isDisplayed() && message.getText().contains("Congratulations");
        } catch (Exception e) {
            return false;
        }
    }
}
