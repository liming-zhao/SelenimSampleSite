package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.DomAndWindowsModulesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class DomAndWindowsModulesTest extends BaseTest {
    private DomAndWindowsModulesPage page;

    @BeforeMethod
    public void initPageObject() {
        page = new DomAndWindowsModulesPage(driver);
    }

    @Test(priority = 30, description = "TC-030 Large & Deep DOM")
    public void tc030_largeAndDeepDom() {
        long start = System.currentTimeMillis();
        page.openLargeDom();
        long elapsed = System.currentTimeMillis() - start;

        Assert.assertTrue(elapsed < 30000, "Page should load within 30 seconds");
        Assert.assertTrue(page.isLargeTableDisplayed());
        Assert.assertTrue(page.getLargeTableRowCount() > 50);
        Assert.assertTrue(page.isDeepElementPresent());
        Assert.assertTrue(page.isSibling23Displayed());
    }

    @Test(priority = 31, description = "TC-031 Multiple Windows")
    public void tc031_multipleWindows() {
        page.openMultipleWindows();
        String originalWindow = page.getCurrentWindowHandle();
        Assert.assertEquals(page.getWindowHandles().size(), 1);

        page.clickOpenNewWindow();
        wait.until(d -> page.getWindowHandles().size() == 2);

        Set<String> handles = page.getWindowHandles();
        String newWindow = handles.stream().filter(h -> !h.equals(originalWindow)).findFirst().orElseThrow();
        page.switchToWindow(newWindow);

        Assert.assertTrue(driver.getCurrentUrl().contains("/windows/new"));
        Assert.assertEquals(page.getHeadingText(), "New Window");

        driver.close();
        page.switchToWindow(originalWindow);
        Assert.assertTrue(driver.getCurrentUrl().contains("/windows"));
        Assert.assertTrue(page.isClickHereLinkVisible());
    }
}
