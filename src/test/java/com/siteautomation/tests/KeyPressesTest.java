package com.siteautomation.tests;

import com.siteautomation.base.BaseTest;
import com.siteautomation.pages.KeyPressesPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeyPressesTest extends BaseTest {
    private KeyPressesPage keyPressesPage;

    @BeforeMethod
    public void initPageObject() {
        keyPressesPage = new KeyPressesPage(driver);
    }

    @Test(priority = 29, description = "TC-029 Key Presses")
    public void tc029_keyPresses() {
        keyPressesPage.openKeyPresses();
        keyPressesPage.sendSpecialKeyToTarget(Keys.ENTER);
        assertResult("You entered: ENTER");
        keyPressesPage.sendSpecialKeyToTarget(Keys.SHIFT);
        assertResult("You entered: SHIFT");
        keyPressesPage.sendSpecialKeyToTarget(Keys.TAB);
        assertResult("You entered: TAB");
        keyPressesPage.openKeyPresses();
        keyPressesPage.sendSpecialKeyToTarget(Keys.LEFT);
        assertResult("You entered: LEFT");

        keyPressesPage.sendKeyToTarget("A");
        assertResult("You entered: A");
        keyPressesPage.sendKeyToTarget("5");
        assertResult("You entered: 5");
        keyPressesPage.sendSpecialKeyToTarget(Keys.SPACE);
        assertResult("You entered: SPACE");
    }

    private void assertResult(String expected) {
        Assert.assertEquals(keyPressesPage.getKeyPressResult(), expected);
    }
}
