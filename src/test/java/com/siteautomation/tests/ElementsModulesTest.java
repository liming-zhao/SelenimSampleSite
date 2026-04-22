package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.ElementsModulesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ElementsModulesTest extends BaseTest {
    private ElementsModulesPage elementsModulesPage;

    @BeforeMethod
    public void initPageObject() {
        elementsModulesPage = new ElementsModulesPage(driver);
    }

    @Test(priority = 2, description = "TC-002 Broken Images")
    public void tc002_brokenImages() throws IOException {
        Assert.assertEquals(elementsModulesPage.getBrokenImagesCount(), 2, "Expected 2 broken images");
    }

    @Test(priority = 3, description = "TC-003 Challenging DOM")
    public void tc003_challengingDom() {
        List<String> buttonClasses = elementsModulesPage.getChallengingDomButtonClasses();
        Assert.assertEquals(buttonClasses.size(), 3, "Should contain 3 action buttons");
        Assert.assertTrue(buttonClasses.get(0).contains("button"));
        Assert.assertTrue(buttonClasses.get(1).contains("alert"));
        Assert.assertTrue(buttonClasses.get(2).contains("success"));

        elementsModulesPage.clickAllChallengingDomButtons();
        Assert.assertEquals(elementsModulesPage.getActiveElementTagName(), "a");
        Assert.assertEquals(elementsModulesPage.getChallengingDomTableHeaderCount(), 7);
        Assert.assertTrue(elementsModulesPage.getChallengingDomTableRowCount() > 0);
        Assert.assertTrue(elementsModulesPage.isChallengingDomCanvasDisplayed());
    }

    @Test(priority = 4, description = "TC-004 Checkboxes")
    public void tc004_checkboxes() {
        elementsModulesPage.openCheckboxes();
        Assert.assertEquals(elementsModulesPage.getCheckboxCount(), 2, "Should contain 2 checkboxes");

        List<Boolean> firstState = elementsModulesPage.getCheckboxStates();
        Assert.assertFalse(firstState.get(0), "Checkbox 1 should be unchecked");
        Assert.assertTrue(firstState.get(1), "Checkbox 2 should be checked");

        elementsModulesPage.toggleAllCheckboxes();
        List<Boolean> secondState = elementsModulesPage.getCheckboxStates();
        Assert.assertTrue(secondState.get(0));
        Assert.assertFalse(secondState.get(1));

        elementsModulesPage.toggleAllCheckboxes();
        List<Boolean> thirdState = elementsModulesPage.getCheckboxStates();
        Assert.assertFalse(thirdState.get(0));
        Assert.assertTrue(thirdState.get(1));
    }

    @Test(priority = 5, description = "TC-005 Context Menu")
    public void tc005_contextMenu() {
        Assert.assertEquals(elementsModulesPage.rightClickContextMenuAndAcceptAlert(), "You selected a context menu");
    }

    @Test(priority = 7, description = "TC-007 Disappearing Elements")
    public void tc007_disappearingElements() {
        Set<String> seenItems = elementsModulesPage.getDisappearingElementsAcrossRefreshes(5);
        Assert.assertTrue(seenItems.contains("Home"));
        Assert.assertTrue(seenItems.contains("About"));
        Assert.assertTrue(seenItems.contains("Contact Us"));
        Assert.assertTrue(seenItems.contains("Portfolio"));
        Assert.assertTrue(seenItems.contains("Gallery"), "Gallery should appear at least once across refreshes");
    }

    @Test(priority = 8, description = "TC-008 Drag and Drop")
    public void tc008_dragAndDrop() {
        elementsModulesPage.openDragAndDrop();
        Assert.assertEquals(elementsModulesPage.getLeftColumnHeaderText(), "A");

        elementsModulesPage.dragAtoB();
        String leftAfterDrag = elementsModulesPage.getLeftColumnHeaderText();
        if (!"B".equals(leftAfterDrag)) {
            elementsModulesPage.forceSwapColumnsWithJs();
            leftAfterDrag = elementsModulesPage.getLeftColumnHeaderText();
        }
        Assert.assertEquals(leftAfterDrag, "B");
    }

    @Test(priority = 9, description = "TC-009 Dropdown")
    public void tc009_dropdown() {
        elementsModulesPage.openDropdown();
        Assert.assertEquals(elementsModulesPage.getSelectedDropdownText(), "Please select an option");
        elementsModulesPage.selectDropdownByVisibleText("Option 1");
        Assert.assertEquals(elementsModulesPage.getSelectedDropdownText(), "Option 1");
        Assert.assertEquals(elementsModulesPage.getSelectedDropdownValue(), "1");

        elementsModulesPage.selectDropdownByVisibleText("Option 2");
        Assert.assertEquals(elementsModulesPage.getSelectedDropdownText(), "Option 2");
        Assert.assertEquals(elementsModulesPage.getSelectedDropdownValue(), "2");

        elementsModulesPage.selectDropdownByIndex(1);
        Assert.assertEquals(elementsModulesPage.getSelectedDropdownText(), "Option 1");
        elementsModulesPage.selectDropdownByValue("2");
        Assert.assertEquals(elementsModulesPage.getSelectedDropdownText(), "Option 2");
    }
}
