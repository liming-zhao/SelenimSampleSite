package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.FileDownloadPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class FileDownloadTest extends BaseTest {

    @Test(priority = 15, description = "TC-015 File Download")
    public void tc015_fileDownload() throws IOException {
        Path downloadDir = Files.createTempDirectory("downloads-");
        if (driver != null) {
            driver.quit();
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptionsWithDownload(downloadDir.toAbsolutePath().toString()));
        wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));

        FileDownloadPage page = new FileDownloadPage(driver);
        page.openDownloadPage();
        Assert.assertTrue(page.getDownloadLinks().size() > 0, "At least one downloadable file should exist");

        String expectedFileName = page.getDownloadLinks().get(0).getText().trim();
        Assert.assertEquals(Files.list(downloadDir).count(), 0, "Download directory should start empty");
        page.clickFirstDownloadLink();

        Path targetFile = downloadDir.resolve(expectedFileName);
        Instant end = Instant.now().plusSeconds(10);
        while (Instant.now().isBefore(end) && !Files.exists(targetFile)) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Assert.assertTrue(Files.exists(targetFile), "Downloaded file should exist");
        Assert.assertTrue(Files.size(targetFile) > 0, "Downloaded file should have non-zero size");
    }
}
