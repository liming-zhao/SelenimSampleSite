package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FileDownloadPage extends BaseHerokuPage {
    public FileDownloadPage(WebDriver driver) {
        super(driver);
    }

    public void openDownloadPage() {
        navigateTo("/download");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".example")));
    }

    public List<WebElement> getDownloadLinks() {
        return driver.findElements(By.cssSelector(".example a"));
    }

    public void clickFirstDownloadLink() {
        getDownloadLinks().get(0).click();
    }
}
