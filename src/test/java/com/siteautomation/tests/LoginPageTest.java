package com.siteautomation.tests;


import com.siteautomation.pages.HomePage;
import com.siteautomation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class LoginPageTest {


    WebDriver driver;
    final String expectedSubject = "Secure Area";
    String username;
    String passwd;

    LoginPageTest() {

        driver = new ChromeDriver();
        String driverPath = System.getProperty("webdriver.chrome.driver");
        if (driverPath != null) {
            System.out.println("ChromeDriver path set via System Property: " + driverPath);
            // You can then manually check the version of the executable at this path
        } else {
            System.out.println("webdriver.chrome.driver system property is not explicitly set.");
        }
    }


    @Test
    public void login() {
        LoginPage signInPage = new LoginPage(driver);
        signInPage.navigate();
        HomePage homePage = signInPage.login(username, passwd);
        String subject = homePage.getSubject();
        assertNotNull(subject);
        assertEquals(expectedSubject, subject);
    }

    @Test
    public void loginBasic() {
        LoginPage signInPage = new LoginPage(driver);
        signInPage.navigate();
        assertNotNull(signInPage);
    }


    @BeforeMethod
    public void setUp() {

        username = "tomsmith";
        passwd = "SuperSecretPassword!";
        //username = "admin";
        //passwd = "admin";
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}