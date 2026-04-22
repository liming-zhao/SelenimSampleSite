package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthModulesPage extends BaseHerokuPage {
    public AuthModulesPage(WebDriver driver) {
        super(driver);
    }

    public void openBasicAuth() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
    }

    public void openDigestAuth() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/digest_auth");
    }

    public String getAuthMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.example p"))).getText();
    }
}
