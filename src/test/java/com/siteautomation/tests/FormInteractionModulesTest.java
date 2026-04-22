package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.FormInteractionModulesPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormInteractionModulesTest extends BaseTest {
    private FormInteractionModulesPage page;

    @BeforeMethod
    public void initPageObject() {
        page = new FormInteractionModulesPage(driver);
    }

    @Test(priority = 22, description = "TC-022 Horizontal Slider")
    public void tc022_horizontalSlider() {
        page.openHorizontalSlider();
        WebElement slider = page.getSlider();
        Assert.assertEquals(slider.getAttribute("value"), "0");

        slider.click();
        slider.sendKeys(Keys.ARROW_RIGHT);
        Assert.assertEquals(page.getSliderValueLabel(), "0.5");

        for (int i = 0; i < 4; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        Assert.assertEquals(page.getSliderValueLabel(), "2.5");

        for (int i = 0; i < 2; i++) {
            slider.sendKeys(Keys.ARROW_LEFT);
        }
        Assert.assertEquals(page.getSliderValueLabel(), "1.5");
    }

    @Test(priority = 25, description = "TC-025 Inputs")
    public void tc025_inputs() {
        page.openInputs();
        Assert.assertEquals(page.getInputValue(), "");

        page.clearAndType("42");
        Assert.assertEquals(page.getInputValue(), "42");

        page.pressKey(Keys.ARROW_UP, 3);
        Assert.assertEquals(page.getInputValue(), "45");

        page.pressKey(Keys.ARROW_DOWN, 5);
        Assert.assertEquals(page.getInputValue(), "40");

        page.clearAndType("abc");
        Assert.assertTrue(page.getInputValue().isEmpty() || !"abc".equals(page.getInputValue()));

        page.clearAndType("-10");
        Assert.assertEquals(page.getInputValue(), "-10");

        page.clearAndType("9999999");
        Assert.assertEquals(page.getInputValue(), "9999999");
    }
}
