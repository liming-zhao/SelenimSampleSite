package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DynamicInteractionModulesPage extends BaseHerokuPage {
    public DynamicInteractionModulesPage(WebDriver driver) {
        super(driver);
    }

    public void openDynamicControls() {
        navigateTo("/dynamic_controls");
    }

    public boolean isCheckboxPresent() {
        return !driver.findElements(By.cssSelector("#checkbox input")).isEmpty();
    }

    public void clickCheckboxToggleButton() {
        driver.findElement(By.cssSelector("#checkbox-example button")).click();
    }

    public void waitForLoadingToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
    }

    public String getDynamicControlsMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText().trim();
    }

    public boolean isInputEnabled() {
        return driver.findElement(By.cssSelector("#input-example input")).isEnabled();
    }

    public void clickInputToggleButton() {
        driver.findElement(By.cssSelector("#input-example button")).click();
    }

    public void typeIntoDynamicInput(String text) {
        WebElement input = driver.findElement(By.cssSelector("#input-example input"));
        input.clear();
        input.sendKeys(text);
    }

    public String getDynamicInputValue() {
        return driver.findElement(By.cssSelector("#input-example input")).getAttribute("value");
    }

    public void openEntryAd() {
        navigateTo("/entry_ad");
    }

    public boolean isEntryAdModalVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getEntryAdTitle() {
        return driver.findElement(By.cssSelector("#modal .modal-title h3")).getText().trim();
    }

    public String getEntryAdBodyText() {
        return driver.findElement(By.cssSelector("#modal .modal-body p")).getText().trim();
    }

    public void closeEntryAdModal() {
        driver.findElement(By.cssSelector("#modal .modal-footer p")).click();
    }

    public void waitEntryAdModalInvisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal")));
    }

    public boolean isEntryAdReopenLinkVisible() {
        return driver.findElement(By.id("restart-ad")).isDisplayed();
    }

    public void clickEntryAdReopenLink() {
        driver.findElement(By.id("restart-ad")).click();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void openExitIntent() {
        navigateTo("/exit_intent");
    }

    public boolean isExitIntentModalVisible() {
        return !driver.findElements(By.id("ouibounce-modal")).isEmpty()
            && driver.findElement(By.id("ouibounce-modal")).isDisplayed();
    }

    public void triggerExitIntentWithJs() {
        ((JavascriptExecutor) driver).executeScript(
            "document.dispatchEvent(new MouseEvent('mouseout', {relatedTarget: null, clientY: 0}));"
        );
    }

    public String getExitIntentModalTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ouibounce-modal h3"))).getText().trim();
    }

    public void closeExitIntentModal() {
        driver.findElement(By.cssSelector("#ouibounce-modal .modal-footer p")).click();
    }

    public void waitExitIntentModalInvisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("ouibounce-modal")));
    }

    public void openInfiniteScroll() {
        navigateTo("/infinite_scroll");
    }

    public int getInfiniteScrollParagraphCount() {
        List<WebElement> allParagraphs = driver.findElements(By.cssSelector(".jscroll-added p"));
        return allParagraphs.size();
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
