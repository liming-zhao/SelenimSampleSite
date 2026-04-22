package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.BrowserApiModulesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class BrowserApiModulesTest extends BaseTest {

    @Test(priority = 21, description = "TC-021 Geolocation")
    public void tc021_geolocation() {
        if (driver != null) {
            driver.quit();
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptionsWithGeolocation());
        wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));

        BrowserApiModulesPage page = new BrowserApiModulesPage(driver);
        page.openGeolocation();
        Assert.assertEquals(page.getGeolocationHeader(), "Geolocation");
        page.clickWhereAmI();

        double lat = Double.parseDouble(page.getLatitude());
        double lng = Double.parseDouble(page.getLongitude());
        Assert.assertTrue(lat >= -90 && lat <= 90);
        Assert.assertTrue(lng >= -180 && lng <= 180);
    }

    @Test(priority = 28, description = "TC-028 JavaScript onload Event Error")
    public void tc028_javascriptOnloadEventError() {
        BrowserApiModulesPage page = new BrowserApiModulesPage(driver);
        page.openJavascriptErrorPage();

        List<LogEntry> logs = page.getBrowserLogs();
        Assert.assertFalse(logs.isEmpty(), "Browser logs should not be empty");

        boolean severeFound = logs.stream().anyMatch(log -> log.getLevel().getName().equalsIgnoreCase("SEVERE"));
        Assert.assertTrue(severeFound, "At least one SEVERE browser log should exist");

        boolean matchingError = logs.stream().anyMatch(log -> {
            String msg = log.getMessage().toLowerCase(Locale.ROOT);
            return msg.contains("cannot read") || msg.contains("undefined") || msg.contains("javascript");
        });
        Assert.assertTrue(matchingError, "Expected JS error message in browser logs");
        Assert.assertFalse(page.getPageBodyText().isBlank(), "Page body should still render");
    }
}
