package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FormInteractionModulesPage extends BaseHerokuPage {
    public FormInteractionModulesPage(WebDriver driver) {
        super(driver);
    }

    public void openHorizontalSlider() {
        navigateTo("/horizontal_slider");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='range']")));
    }

    public WebElement getSlider() {
        return driver.findElement(By.cssSelector("input[type='range']"));
    }

    public String getSliderValueLabel() {
        return driver.findElement(By.id("range")).getText().trim();
    }

    public void dragSliderByOffset(int xOffset) {
        new Actions(driver).dragAndDropBy(getSlider(), xOffset, 0).perform();
    }

    public void openInputs() {
        navigateTo("/inputs");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='number']")));
    }

    public WebElement getNumberInput() {
        return driver.findElement(By.cssSelector("input[type='number']"));
    }

    public void clearAndType(String text) {
        WebElement input = getNumberInput();
        input.clear();
        input.sendKeys(text);
    }

    public void pressKey(Keys key, int times) {
        WebElement input = getNumberInput();
        for (int i = 0; i < times; i++) {
            input.sendKeys(key);
        }
    }

    public String getInputValue() {
        return getNumberInput().getAttribute("value");
    }
}
