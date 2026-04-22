package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.FramesModulesPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FramesModulesTest extends BaseTest {
    private FramesModulesPage framesModulesPage;

    @BeforeMethod
    public void initPageObject() {
        framesModulesPage = new FramesModulesPage(driver);
    }

    @Test(priority = 20, description = "TC-020 Frames")
    public void tc020_frames() {
        framesModulesPage.openFrames();
        Assert.assertTrue(framesModulesPage.isIFrameLinkVisible());
        Assert.assertTrue(framesModulesPage.isNestedFramesLinkVisible());
        Assert.assertEquals(framesModulesPage.typeInIFrameEditor("Selenium Frame Test"), "Selenium Frame Test");
        Assert.assertEquals(framesModulesPage.getNestedFrameText("frame-top", "frame-left", By.tagName("body")), "LEFT");
        Assert.assertEquals(framesModulesPage.getNestedFrameText("frame-top", "frame-middle", By.id("content")), "MIDDLE");
        Assert.assertEquals(framesModulesPage.getNestedFrameText(null, "frame-bottom", By.tagName("body")), "BOTTOM");
    }
}
