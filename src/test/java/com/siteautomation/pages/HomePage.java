package com.siteautomation.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {

    private WebDriver driver;

    private By subjectBy = By.cssSelector("div.example h2");
    private By logoutBy = By.cssSelector(".button.secondary.radius");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains("/secure")) {
            throw new IllegalStateException("Expected secure area, current URL: " + driver.getCurrentUrl());
        }
    }

    public String getSubject() {
        WebElement subject = driver.findElement(subjectBy);
        if (subject == null) {
            throw new IllegalStateException("Subject element is null");
        }
        return subject.getText();
    }

    private LoginPage logout() {
        driver.findElement(logoutBy).click();
        return new LoginPage(driver);
    }


}
