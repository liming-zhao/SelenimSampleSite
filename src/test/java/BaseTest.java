package com.siteautomation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.logging.Level;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.logging.Level;

/**
 * BaseTest class per PRD specifications
 * Provides common setup and teardown for all test classes
 */
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final String BASE_URL = "https://the-internet.herokuapp.com";
    protected static final int IMPLICIT_WAIT_SECONDS = 5;
    protected static final int EXPLICIT_WAIT_SECONDS = 10;

    /**
     * Configure ChromeOptions per PRD section 2.1
     */
    protected ChromeOptions getChromeOptionsOld() {
        ChromeOptions options = new ChromeOptions();
        
        // Disable browser notifications
        options.addArguments("--disable-notifications");
        
        // Start maximized
        options.addArguments("--start-maximized");
        
        // Disable info bars
        options.addArguments("--disable-infobars");
        
        // Additional stability options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        // Configure logging preferences for browser console logs
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(org.openqa.selenium.logging.LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", loggingPreferences);
        
        return options;
    }

    public ChromeOptions getChromeOptions(){
       ChromeOptions options = new ChromeOptions();
    
    // Disable browser notifications
    options.addArguments("--disable-notifications");
    
    // Start maximized
    options.addArguments("--start-maximized");
    
    // Disable info bars
    options.addArguments("--disable-infobars");
    
    // Additional stability options
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    
    // ADD THESE FOR CI STABILITY
    options.addArguments("--disable-gpu");
    //options.addArguments("--single-process");
    
    // Configure logging preferences for browser console logs
    LoggingPreferences loggingPreferences = new LoggingPreferences();
    loggingPreferences.enable(org.openqa.selenium.logging.LogType.BROWSER, Level.ALL);
    options.setCapability("goog:loggingPrefs", loggingPreferences);
    
    return options;

    }

    /**
     * Configure ChromeOptions with download directory preference
     */
    protected ChromeOptions getChromeOptionsWithDownload(String downloadPath) {
        ChromeOptions options = getChromeOptionsFix();
        options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
            put("download.default_directory", downloadPath);
        }});
        return options;
    }

    /**
     * Configure ChromeOptions with geolocation permission
     */
    protected ChromeOptions getChromeOptionsWithGeolocation() {
        ChromeOptions options = getChromeOptionsFix();
        options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
            put("profile.default_content_setting_values.geolocation", 1);
        }});
        return options;
    }

    /**
     * Setup method executed before each test method
     * Per PRD section 2.3
     */
    @BeforeMethod
    public void setUp() {
        // Chrome 147 emits CDP lookup warnings on Selenium 4.18; suppress noise in test logs.
        java.util.logging.Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.SEVERE);
        java.util.logging.Logger.getLogger("org.openqa.selenium.chromium.ChromiumDriver").setLevel(Level.SEVERE);

        // Use WebDriverManager to auto-manage driver binaries
        WebDriverManager.chromedriver().setup();
        
        // Initialize WebDriver with configured ChromeOptions
        driver = new ChromeDriver(getChromeOptionsFix());
        
        // Set implicit wait to 5 seconds per PRD
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        
        // Maximize browser window
        driver.manage().window().maximize();
        
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
    }

    /**
     * Teardown method executed after each test method
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Navigate to a specific page on the target application
     */
    protected void navigateTo(String path) {
        driver.get(BASE_URL + path);
    }
}
