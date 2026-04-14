package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.BasicAuthPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC-001: Basic Auth Test
 * Verify that the application correctly enforces HTTP Basic Authentication
 */
public class BasicAuthTest extends BaseTest {
    
    @Test(priority = 1, description = "TC-001: Verify Basic Auth with valid credentials")
    public void testBasicAuthWithValidCredentials() {
        BasicAuthPage basicAuthPage = new BasicAuthPage(driver);
        
        // Navigate with credentials injected in URL
        basicAuthPage.navigateWithAuth();
        
        // Verify success message is displayed
        Assert.assertTrue(basicAuthPage.isSuccessMessageDisplayed(), 
            "Success message should be displayed after authentication");
        
        // Verify success message text
        String message = basicAuthPage.getSuccessMessage();
        Assert.assertTrue(message.contains("Congratulations! You must have the proper credentials."),
            "Success message should contain expected text");
        
        // Verify page title
        Assert.assertEquals(driver.getTitle(), "The Internet",
            "Page title should be 'The Internet'");
    }
}
