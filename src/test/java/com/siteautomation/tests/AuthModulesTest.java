package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.AuthModulesPage;
import com.siteautomation.pages.HomePage;
import com.siteautomation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthModulesTest extends BaseTest {
    private AuthModulesPage authModulesPage;

    @BeforeMethod
    public void initPageObject() {
        authModulesPage = new AuthModulesPage(driver);
    }

    @Test(priority = 1, description = "TC-001 Basic Auth")
    public void tc001_basicAuth() {
        authModulesPage.openBasicAuth();
        Assert.assertTrue(authModulesPage.getAuthMessage().contains("Congratulations! You must have the proper credentials."));
        Assert.assertEquals(driver.getTitle(), "The Internet");
    }

    @Test(priority = 6, description = "TC-006 Digest Auth")
    public void tc006_digestAuth() {
        authModulesPage.openDigestAuth();
        Assert.assertTrue(authModulesPage.getAuthMessage().contains("Congratulations! You must have the proper credentials."));
    }

    @Test(priority = 19, description = "TC-019 Form Authentication")
    public void tc019_formAuthentication() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.enterUsername("invaliduser");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"));

        loginPage.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(loginPage.getFlashMessage().contains("You logged into a secure area!"));
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getSubject().trim(), "Secure Area");

        loginPage.clickLogout();
        Assert.assertTrue(loginPage.getFlashMessage().contains("You logged out of the secure area!"));
    }
}
