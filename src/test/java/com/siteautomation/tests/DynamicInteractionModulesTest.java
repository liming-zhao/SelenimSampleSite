package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.DynamicInteractionModulesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DynamicInteractionModulesTest extends BaseTest {
    private DynamicInteractionModulesPage page;

    @BeforeMethod
    public void initPageObject() {
        page = new DynamicInteractionModulesPage(driver);
    }

    @Test(priority = 11, description = "TC-011 Dynamic Controls")
    public void tc011_dynamicControls() {
        page.openDynamicControls();
        Assert.assertTrue(page.isCheckboxPresent());

        page.clickCheckboxToggleButton();
        page.waitForLoadingToDisappear();
        Assert.assertFalse(page.isCheckboxPresent());
        Assert.assertEquals(page.getDynamicControlsMessage(), "It's gone!");

        page.clickCheckboxToggleButton();
        page.waitForLoadingToDisappear();
        Assert.assertTrue(page.isCheckboxPresent());
        Assert.assertEquals(page.getDynamicControlsMessage(), "It's back!");

        Assert.assertFalse(page.isInputEnabled());
        page.clickInputToggleButton();
        page.waitForLoadingToDisappear();
        Assert.assertTrue(page.isInputEnabled());
        page.typeIntoDynamicInput("Hello World");
        Assert.assertEquals(page.getDynamicInputValue(), "Hello World");

        page.clickInputToggleButton();
        page.waitForLoadingToDisappear();
        Assert.assertFalse(page.isInputEnabled());
    }

    @Test(priority = 13, description = "TC-013 Entry Ad")
    public void tc013_entryAd() {
        page.openEntryAd();
        Assert.assertTrue(page.isEntryAdModalVisible());
        Assert.assertEquals(page.getEntryAdTitle(), "THIS IS A MODAL WINDOW");
        Assert.assertFalse(page.getEntryAdBodyText().isBlank());

        page.closeEntryAdModal();
        page.waitEntryAdModalInvisible();
        page.clickEntryAdReopenLink();
        Assert.assertTrue(page.isEntryAdModalVisible());

        page.closeEntryAdModal();
        Assert.assertTrue(page.isEntryAdReopenLinkVisible());
        page.clickEntryAdReopenLink();
        Assert.assertTrue(page.isEntryAdModalVisible());
    }

    @Test(priority = 14, description = "TC-014 Exit Intent")
    public void tc014_exitIntent() {
        page.openExitIntent();
        page.triggerExitIntentWithJs();
        Assert.assertTrue(page.isExitIntentModalVisible());
        Assert.assertTrue(page.getExitIntentModalTitle().toLowerCase().contains("modal window"));
        page.closeExitIntentModal();
        page.waitExitIntentModalInvisible();
    }

    @Test(priority = 24, description = "TC-024 Infinite Scroll")
    public void tc024_infiniteScroll() {
        page.openInfiniteScroll();
        int initialCount = page.getInfiniteScrollParagraphCount();

        page.scrollToBottom();
        page.waitForInfiniteScrollCountGreaterThan(initialCount);
        int secondCount = page.getInfiniteScrollParagraphCount();
        Assert.assertTrue(secondCount > initialCount);

        page.scrollToBottom();
        page.waitForInfiniteScrollCountGreaterThan(secondCount);
        int thirdCount = page.getInfiniteScrollParagraphCount();
        Assert.assertTrue(thirdCount > secondCount);
    }
}
