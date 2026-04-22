package com.siteautomation.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JavascriptAlertsPage extends BaseHerokuPage {
    public JavascriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    public void openJavascriptAlerts() {
        navigateTo("/javascript_alerts");
    }

    public String clickJsAlertAndAccept() {
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public String clickJsConfirm(boolean accept) {
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert confirm = wait.until(ExpectedConditions.alertIsPresent());
        String text = confirm.getText();
        if (accept) {
            confirm.accept();
        } else {
            confirm.dismiss();
        }
        return text;
    }

    public String clickJsPrompt(String input) {
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert prompt = wait.until(ExpectedConditions.alertIsPresent());
        String text = prompt.getText();
        prompt.sendKeys(input);
        prompt.accept();
        return text;
    }

    public String getJavascriptResultText() {
        return driver.findElement(By.id("result")).getText().trim();
    }
}
