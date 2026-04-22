package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.UtilityModulesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class UtilityModulesTest extends BaseTest {
    private UtilityModulesPage utilityModulesPage;

    @BeforeMethod
    public void initPageObject() {
        utilityModulesPage = new UtilityModulesPage(driver);
    }

    @Test(priority = 16, description = "TC-016 File Upload")
    public void tc016_fileUpload() throws IOException {
        String uploadedName = utilityModulesPage.uploadTempFileAndGetUploadedName();
        Assert.assertTrue(uploadedName.startsWith("test-upload-"));
        Assert.assertTrue(uploadedName.endsWith(".txt"));
    }

    @Test(priority = 17, description = "TC-017 Floating Menu")
    public void tc017_floatingMenu() {
        utilityModulesPage.openFloatingMenu();
        Assert.assertTrue(utilityModulesPage.isFloatingMenuVisible());
        Assert.assertTrue(utilityModulesPage.isFloatingMenuLinkVisible("Home"));
        Assert.assertTrue(utilityModulesPage.isFloatingMenuLinkVisible("News"));
        Assert.assertTrue(utilityModulesPage.isFloatingMenuLinkVisible("Contact"));
        Assert.assertTrue(utilityModulesPage.isFloatingMenuLinkVisible("About"));

        utilityModulesPage.scrollBy(300);
        Assert.assertTrue(utilityModulesPage.isFloatingMenuVisible());
        utilityModulesPage.scrollBy(600);
        Assert.assertTrue(utilityModulesPage.isFloatingMenuVisible());
        utilityModulesPage.scrollToBottom();
        Assert.assertTrue(utilityModulesPage.isFloatingMenuVisible());

        utilityModulesPage.clickFloatingMenuLink("News");
        Assert.assertTrue(utilityModulesPage.isFloatingMenuVisible());
        utilityModulesPage.clickFloatingMenuLink("Contact");
        Assert.assertTrue(utilityModulesPage.isFloatingMenuVisible());
        utilityModulesPage.clickFloatingMenuLink("About");
        Assert.assertTrue(utilityModulesPage.isFloatingMenuVisible());
        utilityModulesPage.clickFloatingMenuHome();
        Assert.assertTrue(utilityModulesPage.getYOffset() <= 5, "Page should scroll near top");
    }

    @Test(priority = 18, description = "TC-018 Forgot Password")
    public void tc018_forgotPassword() {
        utilityModulesPage.openForgotPassword();
        Assert.assertEquals(utilityModulesPage.getForgotPasswordHeader(), "Forgot Password");
        utilityModulesPage.submitForgotPasswordEmail("testuser@example.com");
        Assert.assertTrue(utilityModulesPage.isOnEmailSentPage());
    }
}
