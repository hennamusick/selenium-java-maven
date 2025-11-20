package com.framework.tests.rahulshetty;

import com.framework.pages.HomePage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        // Using baseUrl.1 - Rahul Shetty Academy AutomationPractice
        driver.get(ConfigReader.getBaseUrl(1));
        waitForPageToLoad();
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify all checkboxes are displayed and enabled")
    public void testCheckboxesVisibilityAndState() {
        // Verify all checkboxes are displayed
        softAssert.assertTrue(homePage.isCheckbox1Displayed(), 
            "Checkbox1 should be displayed");
        softAssert.assertTrue(homePage.isCheckbox2Displayed(), 
            "Checkbox2 should be displayed");
        softAssert.assertTrue(homePage.isCheckbox3Displayed(), 
            "Checkbox3 should be displayed");
        
        // Verify all checkboxes are enabled
        softAssert.assertTrue(homePage.isCheckbox1Enabled(), 
            "Checkbox1 should be enabled");
        softAssert.assertTrue(homePage.isCheckbox2Enabled(), 
            "Checkbox2 should be enabled");
        softAssert.assertTrue(homePage.isCheckbox3Enabled(), 
            "Checkbox3 should be enabled");
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "functional", "regression"}, description = "Verify default unchecked state of all checkboxes")
    public void testDefaultUncheckedState() {
        // Verify all checkboxes are unchecked by default
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be unchecked by default");
        softAssert.assertFalse(homePage.isCheckbox2Selected(), 
            "Checkbox2 should be unchecked by default");
        softAssert.assertFalse(homePage.isCheckbox3Selected(), 
            "Checkbox3 should be unchecked by default");
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify checking Checkbox1")
    public void testCheckCheckbox1() {
        homePage.clickCheckbox1();
        
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be checked after clicking");
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify checking Checkbox2")
    public void testCheckCheckbox2() {
        homePage.clickCheckbox2();
        
        softAssert.assertTrue(homePage.isCheckbox2Selected(), 
            "Checkbox2 should be checked after clicking");
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify checking Checkbox3")
    public void testCheckCheckbox3() {
        homePage.clickCheckbox3();
        
        softAssert.assertTrue(homePage.isCheckbox3Selected(), 
            "Checkbox3 should be checked after clicking");
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify unchecking Checkbox1")
    public void testUncheckCheckbox1() {
        // First check it
        homePage.clickCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be checked");
        
        // Then uncheck it
        homePage.clickCheckbox1();
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be unchecked after clicking again");
        
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify multiple checkboxes can be selected simultaneously")
    public void testMultipleCheckboxSelection() {
        // Check all three checkboxes
        homePage.clickCheckbox1();
        homePage.clickCheckbox2();
        homePage.clickCheckbox3();
        
        // Verify all are checked
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be checked");
        softAssert.assertTrue(homePage.isCheckbox2Selected(), 
            "Checkbox2 should be checked");
        softAssert.assertTrue(homePage.isCheckbox3Selected(), 
            "Checkbox3 should be checked");
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify toggle behavior of checkboxes")
    public void testCheckboxToggleBehavior() {
        // Checkbox1: Check -> Uncheck -> Check
        homePage.clickCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be checked on first click");
        
        homePage.clickCheckbox1();
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be unchecked on second click");
        
        homePage.clickCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be checked again on third click");
        
        softAssert.assertAll();
    }

    @Test(priority = 9, groups = {"functional", "regression"}, description = "Verify checking checkboxes independently")
    public void testIndependentCheckboxBehavior() {
        // Check Checkbox1
        homePage.clickCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be checked");
        softAssert.assertFalse(homePage.isCheckbox2Selected(), 
            "Checkbox2 should remain unchecked");
        softAssert.assertFalse(homePage.isCheckbox3Selected(), 
            "Checkbox3 should remain unchecked");
        
        // Check Checkbox2
        homePage.clickCheckbox2();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should remain checked");
        softAssert.assertTrue(homePage.isCheckbox2Selected(), 
            "Checkbox2 should be checked");
        softAssert.assertFalse(homePage.isCheckbox3Selected(), 
            "Checkbox3 should remain unchecked");
        
        // Check Checkbox3
        homePage.clickCheckbox3();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should remain checked");
        softAssert.assertTrue(homePage.isCheckbox2Selected(), 
            "Checkbox2 should remain checked");
        softAssert.assertTrue(homePage.isCheckbox3Selected(), 
            "Checkbox3 should be checked");
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"regression"}, description = "Verify selective unchecking of checkboxes")
    public void testSelectiveUnchecking() {
        // Check all checkboxes
        homePage.clickCheckbox1();
        homePage.clickCheckbox2();
        homePage.clickCheckbox3();
        
        // Uncheck only Checkbox2
        homePage.clickCheckbox2();
        
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should remain checked");
        softAssert.assertFalse(homePage.isCheckbox2Selected(), 
            "Checkbox2 should be unchecked");
        softAssert.assertTrue(homePage.isCheckbox3Selected(), 
            "Checkbox3 should remain checked");
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"smoke", "functional"}, description = "Verify safe check methods (idempotent)")
    public void testSafeCheckMethods() {
        // Use safe check method - should check the checkbox
        homePage.checkCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be checked");
        
        // Call again - should remain checked (idempotent)
        homePage.checkCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should remain checked after calling checkCheckbox1 again");
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"functional"}, description = "Verify safe uncheck methods (idempotent)")
    public void testSafeUncheckMethods() {
        // First check the checkbox
        homePage.checkCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be checked");
        
        // Use safe uncheck method
        homePage.uncheckCheckbox1();
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            "Checkbox1 should be unchecked");
        
        // Call again - should remain unchecked (idempotent)
        homePage.uncheckCheckbox1();
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            "Checkbox1 should remain unchecked after calling uncheckCheckbox1 again");
        
        softAssert.assertAll();
    }
}
