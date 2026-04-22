package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FramesModulesPage extends BaseHerokuPage {
    public FramesModulesPage(WebDriver driver) {
        super(driver);
    }

    public void openFrames() {
        navigateTo("/frames");
    }

    public boolean isIFrameLinkVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("iFrame"))).isDisplayed();
    }

    public boolean isNestedFramesLinkVisible() {
        return driver.findElement(By.linkText("Nested Frames")).isDisplayed();
    }

    public String typeInIFrameEditor(String text) {
        driver.findElement(By.linkText("iFrame")).click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("mce_0_ifr")));
        WebElement editorBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));
        editorBody.clear();
        editorBody.sendKeys(text);
        String value = editorBody.getText().trim();
        driver.switchTo().defaultContent();
        return value;
    }

    public String getNestedFrameText(String parentFrame, String frame, By locator) {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        if (parentFrame != null && !parentFrame.isBlank()) {
            driver.switchTo().frame(parentFrame);
        }
        driver.switchTo().frame(frame);
        String text = driver.findElement(locator).getText().trim();
        driver.switchTo().defaultContent();
        return text;
    }
}
