package com.siteautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseHerokuPage {
    protected static final String BASE_URL = "https://the-internet.herokuapp.com";
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BaseHerokuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void navigateTo(String path) {
        driver.get(BASE_URL + path);
    }
}
