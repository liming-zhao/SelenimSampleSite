package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.MouseAndMenuModulesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MouseAndMenuModulesTest extends BaseTest {
    private MouseAndMenuModulesPage page;

    @BeforeMethod
    public void initPageObject() {
        page = new MouseAndMenuModulesPage(driver);
    }

    @Test(priority = 23, description = "TC-023 Hovers")
    public void tc023_hovers() {
        page.openHovers();
        Assert.assertEquals(page.getFigures().size(), 3);

        page.hoverFigure(0);
        Assert.assertTrue(page.isCaptionVisible(0));
        Assert.assertTrue(page.getCaptionText(0).toLowerCase().contains("user1"));
        Assert.assertTrue(page.getProfileHref(0).endsWith("/users/1"));

        page.hoverFigure(1);
        Assert.assertTrue(page.getCaptionText(1).toLowerCase().contains("user2"));
        Assert.assertTrue(page.getProfileHref(1).endsWith("/users/2"));

        page.hoverFigure(2);
        Assert.assertTrue(page.getCaptionText(2).toLowerCase().contains("user3"));
        Assert.assertTrue(page.getProfileHref(2).endsWith("/users/3"));
    }

    @Test(priority = 26, description = "TC-026 JQuery UI Menus")
    public void tc026_jqueryUiMenus() {
        page.openJqueryMenu();
        Assert.assertTrue(page.isMenuItemVisible("Enabled"));

        page.hoverMenuItem("Enabled");
        Assert.assertTrue(page.isMenuItemVisible("Downloads"));
        Assert.assertTrue(page.isMenuItemVisible("Back to JQuery UI"));

        page.hoverMenuItem("Downloads");
        Assert.assertTrue(page.isMenuItemVisible("PDF"));
        Assert.assertTrue(page.isMenuItemVisible("CSV"));
        Assert.assertTrue(page.isMenuItemVisible("Excel"));

        page.clickMenuItem("PDF");
        driver.navigate().back();
        page.openJqueryMenu();

        page.hoverMenuItem("Enabled");
        page.clickMenuItem("Back to JQuery UI");
        Assert.assertTrue(driver.getCurrentUrl().contains("jqueryui"));

        driver.navigate().back();
        page.openJqueryMenu();
        Assert.assertTrue(page.getMenuItemClass("Disabled").contains("ui-state-disabled"));
    }
}
