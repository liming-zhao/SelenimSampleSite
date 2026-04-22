package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class DynamicModulesPage extends BaseHerokuPage {
    public DynamicModulesPage(WebDriver driver) {
        super(driver);
    }

    public void openDynamicContent() {
        navigateTo("/dynamic_content");
    }

    public List<String> getDynamicContentImages() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".large-2 img")));
        List<String> values = new ArrayList<>();
        for (WebElement element : driver.findElements(By.cssSelector(".large-2 img"))) {
            values.add(element.getAttribute("src"));
        }
        return values;
    }

    public List<String> getDynamicContentTexts() {
        List<String> values = new ArrayList<>();
        for (WebElement element : driver.findElements(By.cssSelector(".large-10"))) {
            values.add(element.getText().trim());
        }
        return values;
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public boolean isDynamicLoadingOneFinishInitiallyHidden() {
        navigateTo("/dynamic_loading/1");
        try {
            return !driver.findElement(By.id("finish")).isDisplayed();
        } catch (TimeoutException e) {
            return true;
        }
    }

    public String runDynamicLoadingOne() {
        driver.findElement(By.cssSelector("#start button")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish"))).getText().trim();
    }

    public boolean isDynamicLoadingTwoFinishAbsent() {
        navigateTo("/dynamic_loading/2");
        return driver.findElements(By.id("finish")).isEmpty();
    }

    public String runDynamicLoadingTwo() {
        driver.findElement(By.cssSelector("#start button")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish"))).getText().trim();
    }
}
