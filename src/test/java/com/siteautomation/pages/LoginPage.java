package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Page Object for Form Authentication page
 * TC-019: Form Authentication
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private static final String PAGE_URL = "https://the-internet.herokuapp.com/login";
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector(".radius");
    private By flashMessage = By.id("flash");
    private By logoutButton = By.cssSelector(".button.secondary.radius");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigate() {
        driver.get(PAGE_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
    }
    
    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }
    
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }
    
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
    
    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new HomePage(driver);
    }
    
    public String getFlashMessage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(flashMessage)).getText();
    }
    
    public boolean isFlashMessageDisplayed() {
        try {
            return driver.findElement(flashMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isOnSecurePage() {
        return driver.getCurrentUrl().contains("/secure");
    }
    
    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }
    
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/login");
    }
}
