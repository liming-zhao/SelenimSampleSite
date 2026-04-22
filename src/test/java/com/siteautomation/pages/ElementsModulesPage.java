package com.siteautomation.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElementsModulesPage extends BaseHerokuPage {
    public ElementsModulesPage(WebDriver driver) {
        super(driver);
    }

    public int getBrokenImagesCount() throws IOException {
        navigateTo("/broken_images");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("img")));
        List<WebElement> images = driver.findElements(By.tagName("img"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int brokenCount = 0;

        for (WebElement image : images) {
            String src = image.getAttribute("src");
            Number naturalWidth = (Number) js.executeScript("return arguments[0].naturalWidth;", image);
            boolean zeroWidth = naturalWidth == null || naturalWidth.intValue() == 0;
            boolean badHttp = false;

            if (src != null && !src.isBlank()) {
                HttpURLConnection connection = (HttpURLConnection) new URL(src).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                badHttp = connection.getResponseCode() >= 400;
            }

            if (zeroWidth || badHttp) {
                brokenCount++;
            }
        }
        return brokenCount;
    }

    public List<String> getChallengingDomButtonClasses() {
        navigateTo("/challenging_dom");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("canvas")));
        List<String> classes = new ArrayList<>();
        for (WebElement button : driver.findElements(By.cssSelector(".button"))) {
            classes.add(button.getAttribute("class"));
        }
        return classes;
    }

    public void clickAllChallengingDomButtons() {
        for (WebElement button : driver.findElements(By.cssSelector(".button"))) {
            button.click();
        }
    }

    public String getActiveElementTagName() {
        return driver.switchTo().activeElement().getTagName();
    }

    public int getChallengingDomTableHeaderCount() {
        return driver.findElement(By.tagName("table")).findElements(By.cssSelector("thead th")).size();
    }

    public int getChallengingDomTableRowCount() {
        return driver.findElement(By.tagName("table")).findElements(By.cssSelector("tbody tr")).size();
    }

    public boolean isChallengingDomCanvasDisplayed() {
        return driver.findElement(By.tagName("canvas")).isDisplayed();
    }

    public void openCheckboxes() {
        navigateTo("/checkboxes");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkboxes")));
    }

    public int getCheckboxCount() {
        return driver.findElements(By.cssSelector("form#checkboxes input[type='checkbox']")).size();
    }

    public List<Boolean> getCheckboxStates() {
        List<Boolean> states = new ArrayList<>();
        List<WebElement> boxes = driver.findElements(By.cssSelector("form#checkboxes input[type='checkbox']"));
        for (WebElement box : boxes) {
            states.add(box.isSelected());
        }
        return states;
    }

    public void toggleAllCheckboxes() {
        for (WebElement box : driver.findElements(By.cssSelector("form#checkboxes input[type='checkbox']"))) {
            box.click();
        }
    }

    public String rightClickContextMenuAndAcceptAlert() {
        navigateTo("/context_menu");
        WebElement hotspot = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hot-spot")));
        new Actions(driver).contextClick(hotspot).perform();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public Set<String> getDisappearingElementsAcrossRefreshes(int refreshCount) {
        navigateTo("/disappearing_elements");
        Set<String> seenItems = new HashSet<>();
        for (int i = 0; i < refreshCount; i++) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul li")));
            for (WebElement item : driver.findElements(By.cssSelector("ul li"))) {
                seenItems.add(item.getText().trim());
            }
            driver.navigate().refresh();
        }
        return seenItems;
    }

    public void openDragAndDrop() {
        navigateTo("/drag_and_drop");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-a")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-b")));
    }

    public String getLeftColumnHeaderText() {
        return driver.findElement(By.cssSelector("#columns .column:first-child header")).getText();
    }

    public void dragAtoB() {
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));
        new Actions(driver).dragAndDrop(columnA, columnB).perform();
    }

    public void forceSwapColumnsWithJs() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "const a=document.querySelector('#column-a');" +
                "const b=document.querySelector('#column-b');" +
                "a.parentNode.insertBefore(b,a);"
        );
    }

    public void openDropdown() {
        navigateTo("/dropdown");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dropdown")));
    }

    public void selectDropdownByVisibleText(String text) {
        new Select(driver.findElement(By.id("dropdown"))).selectByVisibleText(text);
    }

    public void selectDropdownByIndex(int index) {
        new Select(driver.findElement(By.id("dropdown"))).selectByIndex(index);
    }

    public void selectDropdownByValue(String value) {
        new Select(driver.findElement(By.id("dropdown"))).selectByValue(value);
    }

    public String getSelectedDropdownText() {
        return new Select(driver.findElement(By.id("dropdown"))).getFirstSelectedOption().getText().trim();
    }

    public String getSelectedDropdownValue() {
        return new Select(driver.findElement(By.id("dropdown"))).getFirstSelectedOption().getAttribute("value");
    }
}
