package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class KeyPressesPage extends BaseHerokuPage {
    public KeyPressesPage(WebDriver driver) {
        super(driver);
    }

    public void openKeyPresses() {
        navigateTo("/key_presses");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("target")));
    }

    public void sendKeyToTarget(CharSequence key) {
        driver.findElement(By.id("target")).sendKeys(key);
    }

    public void sendSpecialKeyToTarget(Keys key) {
        driver.findElement(By.id("target")).sendKeys(key);
    }

    public String getKeyPressResult() {
        return driver.findElement(By.id("result")).getText().trim();
    }
}
