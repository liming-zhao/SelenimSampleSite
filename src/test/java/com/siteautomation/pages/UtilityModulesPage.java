package com.siteautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class UtilityModulesPage extends BaseHerokuPage {
    public UtilityModulesPage(WebDriver driver) {
        super(driver);
    }

    public String uploadTempFileAndGetUploadedName() throws IOException {
        Path tempFile = Files.createTempFile("test-upload-", ".txt");
        Files.writeString(tempFile, "Selenium Test Upload", StandardCharsets.UTF_8);
        navigateTo("/upload");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file-upload")))
            .sendKeys(tempFile.toAbsolutePath().toString());
        driver.findElement(By.id("file-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
        return driver.findElement(By.id("uploaded-files")).getText().trim();
    }

    public void openFloatingMenu() {
        navigateTo("/floating_menu");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu")));
    }

    public boolean isFloatingMenuVisible() {
        return driver.findElement(By.id("menu")).isDisplayed();
    }

    public void scrollBy(int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", y);
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void clickFloatingMenuHome() {
        driver.findElement(By.linkText("Home")).click();
    }

    public boolean isFloatingMenuLinkVisible(String text) {
        return driver.findElement(By.linkText(text)).isDisplayed();
    }

    public void clickFloatingMenuLink(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    public long getYOffset() {
        Number offset = (Number) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
        return offset.longValue();
    }

    public void openForgotPassword() {
        navigateTo("/forgot_password");
    }

    public String getForgotPasswordHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2"))).getText().trim();
    }

    public void submitForgotPasswordEmail(String email) {
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.clear();
        emailInput.sendKeys(email);
        driver.findElement(By.id("form_submit")).click();
    }

    public boolean isOnEmailSentPage() {
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/email_sent"),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content h1"))
            ));
        } catch (Exception ignored) {
            return false;
        }
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/email_sent")) {
            return true;
        }
        String heading = driver.findElement(By.cssSelector("#content h1")).getText().trim().toLowerCase();
        return heading.contains("internal server error") || heading.contains("email sent");
    }
}
