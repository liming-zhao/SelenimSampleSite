package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class DomAndWindowsModulesPage extends BaseHerokuPage {
    public DomAndWindowsModulesPage(WebDriver driver) {
        super(driver);
    }

    public void openLargeDom() {
        navigateTo("/large");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("large-table")));
    }

    public boolean isLargeTableDisplayed() {
        return driver.findElement(By.id("large-table")).isDisplayed();
    }

    public int getLargeTableRowCount() {
        return driver.findElements(By.cssSelector("#large-table tbody tr")).size();
    }

    public boolean isSibling23Displayed() {
        return driver.findElement(By.id("sibling-2.3")).isDisplayed();
    }

    public boolean isDeepElementPresent() {
        return !driver.findElements(By.xpath("//div[@id='sibling-1.1']//span")).isEmpty();
    }

    public void openMultipleWindows() {
        navigateTo("/windows");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Click Here")));
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public void clickOpenNewWindow() {
        driver.findElement(By.linkText("Click Here")).click();
    }

    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
    }

    public String getHeadingText() {
        return driver.findElement(By.cssSelector(".example h3")).getText().trim();
    }

    public boolean isClickHereLinkVisible() {
        return driver.findElement(By.linkText("Click Here")).isDisplayed();
    }
}
