package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.TestMessages;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify all checkboxes are displayed and enabled")
    public void testCheckboxesVisibilityAndState() {
        // Verify all checkboxes are displayed
        softAssert.assertTrue(homePage.isCheckbox1Displayed(), 
            TestMessages.CHECKBOX1_DISPLAYED);
        softAssert.assertTrue(homePage.isCheckbox2Displayed(), 
            TestMessages.CHECKBOX2_DISPLAYED);
        softAssert.assertTrue(homePage.isCheckbox3Displayed(), 
            TestMessages.CHECKBOX3_DISPLAYED);
        
        // Verify all checkboxes are enabled
        softAssert.assertTrue(homePage.isCheckbox1Enabled(), 
            TestMessages.CHECKBOX1_ENABLED);
        softAssert.assertTrue(homePage.isCheckbox2Enabled(), 
            TestMessages.CHECKBOX2_ENABLED);
        softAssert.assertTrue(homePage.isCheckbox3Enabled(), 
            TestMessages.CHECKBOX3_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "functional", "regression"}, description = "Verify default unchecked state of all checkboxes")
    public void testDefaultUncheckedState() {
        // Verify all checkboxes are unchecked by default
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_UNCHECKED_DEFAULT);
        softAssert.assertFalse(homePage.isCheckbox2Selected(), 
            TestMessages.CHECKBOX2_UNCHECKED_DEFAULT);
        softAssert.assertFalse(homePage.isCheckbox3Selected(), 
            TestMessages.CHECKBOX3_UNCHECKED_DEFAULT);
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify checking Checkbox1")
    public void testCheckCheckbox1() {
        homePage.clickCheckbox1();
        
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_CHECKED_AFTER_CLICK);
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify checking Checkbox2")
    public void testCheckCheckbox2() {
        homePage.clickCheckbox2();
        
        softAssert.assertTrue(homePage.isCheckbox2Selected(), 
            TestMessages.CHECKBOX2_CHECKED_AFTER_CLICK);
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify checking Checkbox3")
    public void testCheckCheckbox3() {
        homePage.clickCheckbox3();
        
        softAssert.assertTrue(homePage.isCheckbox3Selected(), 
            TestMessages.CHECKBOX3_CHECKED_AFTER_CLICK);
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify unchecking Checkbox1")
    public void testUncheckCheckbox1() {
        // First check it
        homePage.clickCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_SELECTED);
        
        // Then uncheck it
        homePage.clickCheckbox1();
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_UNCHECKED_AFTER_CLICK);
        
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
            TestMessages.CHECKBOX1_SELECTED);
        softAssert.assertTrue(homePage.isCheckbox2Selected(), 
            TestMessages.CHECKBOX2_SELECTED);
        softAssert.assertTrue(homePage.isCheckbox3Selected(), 
            TestMessages.CHECKBOX3_SELECTED);
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify toggle behavior of checkboxes")
    public void testCheckboxToggleBehavior() {
        // Checkbox1: Check -> Uncheck -> Check
        homePage.clickCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_CHECKED_FIRST_CLICK);
        
        homePage.clickCheckbox1();
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_UNCHECKED_SECOND_CLICK);
        
        homePage.clickCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_CHECKED_THIRD_CLICK);
        
        softAssert.assertAll();
    }

    @Test(priority = 9, groups = {"functional", "regression"}, description = "Verify checking checkboxes independently")
    public void testIndependentCheckboxBehavior() {
        // Check Checkbox1
        homePage.clickCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_SELECTED);
        softAssert.assertFalse(homePage.isCheckbox2Selected(), 
            TestMessages.CHECKBOX2_UNCHECKED);
        softAssert.assertFalse(homePage.isCheckbox3Selected(), 
            TestMessages.CHECKBOX3_UNCHECKED);
        
        // Check Checkbox2
        homePage.clickCheckbox2();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_REMAINS_CHECKED);
        softAssert.assertTrue(homePage.isCheckbox2Selected(), 
            TestMessages.CHECKBOX2_SELECTED);
        softAssert.assertFalse(homePage.isCheckbox3Selected(), 
            TestMessages.CHECKBOX3_UNCHECKED);
        
        // Check Checkbox3
        homePage.clickCheckbox3();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_REMAINS_CHECKED);
        softAssert.assertTrue(homePage.isCheckbox2Selected(), 
            TestMessages.CHECKBOX2_REMAINS_CHECKED);
        softAssert.assertTrue(homePage.isCheckbox3Selected(), 
            TestMessages.CHECKBOX3_SELECTED);
        
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
            TestMessages.CHECKBOX1_REMAINS_CHECKED);
        softAssert.assertFalse(homePage.isCheckbox2Selected(), 
            TestMessages.CHECKBOX2_UNCHECKED);
        softAssert.assertTrue(homePage.isCheckbox3Selected(), 
            TestMessages.CHECKBOX3_REMAINS_CHECKED);
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"smoke", "functional"}, description = "Verify safe check methods (idempotent)")
    public void testSafeCheckMethods() {
        // Use safe check method - should check the checkbox
        homePage.checkCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_SELECTED);
        
        // Call again - should remain checked (idempotent)
        homePage.checkCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_REMAINS_CHECKED_AGAIN);
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"functional"}, description = "Verify safe uncheck methods (idempotent)")
    public void testSafeUncheckMethods() {
        // First check the checkbox
        homePage.checkCheckbox1();
        softAssert.assertTrue(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_SELECTED);
        
        // Use safe uncheck method
        homePage.uncheckCheckbox1();
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_UNCHECKED);
        
        // Call again - should remain unchecked (idempotent)
        homePage.uncheckCheckbox1();
        softAssert.assertFalse(homePage.isCheckbox1Selected(), 
            TestMessages.CHECKBOX1_REMAINS_UNCHECKED_AGAIN);
        
        softAssert.assertAll();
    }
}
