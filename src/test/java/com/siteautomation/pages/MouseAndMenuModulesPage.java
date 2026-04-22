package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MouseAndMenuModulesPage extends BaseHerokuPage {
    public MouseAndMenuModulesPage(WebDriver driver) {
        super(driver);
    }

    public void openHovers() {
        navigateTo("/hovers");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".figure")));
    }

    public List<WebElement> getFigures() {
        return driver.findElements(By.cssSelector(".figure"));
    }

    public void hoverFigure(int index) {
        new Actions(driver).moveToElement(getFigures().get(index)).perform();
    }

    public boolean isCaptionVisible(int index) {
        return getFigures().get(index).findElement(By.cssSelector(".figcaption")).isDisplayed();
    }

    public String getCaptionText(int index) {
        return getFigures().get(index).findElement(By.cssSelector(".figcaption h5")).getText().trim();
    }

    public String getProfileHref(int index) {
        return getFigures().get(index).findElement(By.cssSelector(".figcaption a")).getAttribute("href");
    }

    public void clickProfileLink(int index) {
        getFigures().get(index).findElement(By.cssSelector(".figcaption a")).click();
    }

    public void openJqueryMenu() {
        navigateTo("/jqueryui/menu");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu")));
    }

    public WebElement menuItem(String text) {
        return driver.findElement(By.xpath("//ul[@id='menu']//div[normalize-space()='" + text + "']"));
    }

    public void hoverMenuItem(String text) {
        new Actions(driver).moveToElement(menuItem(text)).perform();
    }

    public void clickMenuItem(String text) {
        menuItem(text).click();
    }

    public boolean isMenuItemVisible(String text) {
        return menuItem(text).isDisplayed();
    }

    public String getMenuItemClass(String text) {
        return menuItem(text).getAttribute("class");
    }
}
