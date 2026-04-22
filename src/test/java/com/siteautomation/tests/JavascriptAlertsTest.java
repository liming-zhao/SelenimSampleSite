package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.JavascriptAlertsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavascriptAlertsTest extends BaseTest {
    private JavascriptAlertsPage javascriptAlertsPage;

    @BeforeMethod
    public void initPageObject() {
        javascriptAlertsPage = new JavascriptAlertsPage(driver);
    }

    @Test(priority = 27, description = "TC-027 JavaScript Alerts")
    public void tc027_javascriptAlerts() {
        javascriptAlertsPage.openJavascriptAlerts();
        Assert.assertEquals(javascriptAlertsPage.clickJsAlertAndAccept(), "I am a JS Alert");
        Assert.assertEquals(javascriptAlertsPage.getJavascriptResultText(), "You successfully clicked an alert");

        Assert.assertEquals(javascriptAlertsPage.clickJsConfirm(true), "I am a JS Confirm");
        Assert.assertEquals(javascriptAlertsPage.getJavascriptResultText(), "You clicked: Ok");

        Assert.assertEquals(javascriptAlertsPage.clickJsConfirm(false), "I am a JS Confirm");
        Assert.assertEquals(javascriptAlertsPage.getJavascriptResultText(), "You clicked: Cancel");

        Assert.assertEquals(javascriptAlertsPage.clickJsPrompt("Selenium Test Input"), "I am a JS prompt");
        Assert.assertEquals(javascriptAlertsPage.getJavascriptResultText(), "You entered: Selenium Test Input");
    }
}
