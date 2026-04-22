package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class BrowserApiModulesPage extends BaseHerokuPage {
    public BrowserApiModulesPage(WebDriver driver) {
        super(driver);
    }

    public void openGeolocation() {
        navigateTo("/geolocation");
    }

    public String getGeolocationHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".example h3"))).getText().trim();
    }

    public void clickWhereAmI() {
        driver.findElement(By.cssSelector(".example button")).click();
    }

    public String getLatitude() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lat-value"))).getText().trim();
    }

    public String getLongitude() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("long-value"))).getText().trim();
    }

    public void openJavascriptErrorPage() {
        navigateTo("/javascript_error");
    }

    public List<LogEntry> getBrowserLogs() {
        LogEntries entries = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> list = new ArrayList<>();
        entries.forEach(list::add);
        return list;
    }

    public String getPageBodyText() {
        return driver.findElement(By.tagName("body")).getText();
    }
}
