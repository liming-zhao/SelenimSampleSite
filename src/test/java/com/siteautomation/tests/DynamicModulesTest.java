package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.DynamicModulesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DynamicModulesTest extends BaseTest {
    private DynamicModulesPage dynamicModulesPage;

    @BeforeMethod
    public void initPageObject() {
        dynamicModulesPage = new DynamicModulesPage(driver);
    }

    @Test(priority = 10, description = "TC-010 Dynamic Content")
    public void tc010_dynamicContent() {
        dynamicModulesPage.openDynamicContent();
        List<String> firstImages = dynamicModulesPage.getDynamicContentImages();
        List<String> firstTexts = dynamicModulesPage.getDynamicContentTexts();

        dynamicModulesPage.refreshPage();
        List<String> secondImages = dynamicModulesPage.getDynamicContentImages();
        List<String> secondTexts = dynamicModulesPage.getDynamicContentTexts();

        boolean imageChanged = !firstImages.equals(secondImages);
        boolean textChanged = !firstTexts.equals(secondTexts);
        Assert.assertTrue(imageChanged || textChanged, "At least one image or text block should change");

        dynamicModulesPage.refreshPage();
        List<String> thirdImages = dynamicModulesPage.getDynamicContentImages();
        List<String> thirdTexts = dynamicModulesPage.getDynamicContentTexts();
        boolean changedAgain = !secondImages.equals(thirdImages) || !secondTexts.equals(thirdTexts);
        Assert.assertTrue(changedAgain, "Dynamic content should keep changing on subsequent refresh");
    }

    @Test(priority = 12, description = "TC-012 Dynamic Loading")
    public void tc012_dynamicLoading() {
        Assert.assertTrue(dynamicModulesPage.isDynamicLoadingOneFinishInitiallyHidden(), "Finish should initially be hidden");
        Assert.assertEquals(dynamicModulesPage.runDynamicLoadingOne(), "Hello World!");

        Assert.assertTrue(dynamicModulesPage.isDynamicLoadingTwoFinishAbsent(), "Finish should not yet exist");
        Assert.assertEquals(dynamicModulesPage.runDynamicLoadingTwo(), "Hello World!");
    }
}
