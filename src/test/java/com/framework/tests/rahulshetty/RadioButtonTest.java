package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.rahulshetty.RahulShettyMessages;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Radio Button Test Class
 * Tests radio button selection, mutual exclusivity, and switching behavior
 */
@Epic("Rahul Shetty Academy")
@Feature("Radio Button Controls")
public class RadioButtonTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify radio buttons are displayed and enabled")
    @Description("Validate that all three radio buttons are displayed and enabled")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Radio Button Display")
    public void testRadioButtonsVisibilityAndState() {
        // Best Practice 1: Verify all radio buttons are displayed
        softAssert.assertTrue(homePage.isRadio1Displayed(), RahulShettyMessages.RADIO1_DISPLAYED);
        softAssert.assertTrue(homePage.isRadio2Displayed(), RahulShettyMessages.RADIO2_DISPLAYED);
        softAssert.assertTrue(homePage.isRadio3Displayed(), RahulShettyMessages.RADIO3_DISPLAYED);
        
        // Best Practice 2: Verify all radio buttons are enabled
        softAssert.assertTrue(homePage.isRadio1Enabled(), RahulShettyMessages.RADIO1_ENABLED);
        softAssert.assertTrue(homePage.isRadio2Enabled(), RahulShettyMessages.RADIO2_ENABLED);
        softAssert.assertTrue(homePage.isRadio3Enabled(), RahulShettyMessages.RADIO3_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify Radio1 selection and mutual exclusivity")
    @Description("Validate that Radio1 can be selected and deselects other radio buttons (mutual exclusivity)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Radio Button Selection")
    public void testRadio1Selection() {
        // Best Practice 3: Click and verify selection
        homePage.clickRadio1();
        
        softAssert.assertTrue(homePage.isRadio1Selected(), 
            RahulShettyMessages.RADIO1_SELECTED_AFTER_CLICK);
        
        // Best Practice 4: Verify mutual exclusivity (only one can be selected)
        softAssert.assertFalse(homePage.isRadio2Selected(), 
            RahulShettyMessages.RADIO2_NOT_SELECTED_WHEN_RADIO1_SELECTED);
        softAssert.assertFalse(homePage.isRadio3Selected(), 
            RahulShettyMessages.RADIO3_NOT_SELECTED_WHEN_RADIO1_SELECTED);
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify Radio2 selection and mutual exclusivity")
    @Description("Validate that Radio2 can be selected and deselects other radio buttons (mutual exclusivity)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Radio Button Selection")
    public void testRadio2Selection() {
        homePage.clickRadio2();
        
        softAssert.assertTrue(homePage.isRadio2Selected(), 
            RahulShettyMessages.RADIO2_SELECTED_AFTER_CLICK);
        
        // Verify others are not selected
        softAssert.assertFalse(homePage.isRadio1Selected(), 
            RahulShettyMessages.RADIO1_NOT_SELECTED_WHEN_RADIO2_SELECTED);
        softAssert.assertFalse(homePage.isRadio3Selected(), 
            RahulShettyMessages.RADIO3_NOT_SELECTED_WHEN_RADIO2_SELECTED);
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify Radio3 selection and mutual exclusivity")
    @Description("Validate that Radio3 can be selected and deselects other radio buttons (mutual exclusivity)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Radio Button Selection")
    public void testRadio3Selection() {
        homePage.clickRadio3();
        
        softAssert.assertTrue(homePage.isRadio3Selected(), 
            RahulShettyMessages.RADIO3_SELECTED_AFTER_CLICK);
        
        // Verify others are not selected
        softAssert.assertFalse(homePage.isRadio1Selected(), 
            RahulShettyMessages.RADIO1_NOT_SELECTED_WHEN_RADIO3_SELECTED);
        softAssert.assertFalse(homePage.isRadio2Selected(), 
            RahulShettyMessages.RADIO2_NOT_SELECTED_WHEN_RADIO3_SELECTED);
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify switching between radio buttons")
    @Description("Validate that switching between radio buttons works correctly with proper deselection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Radio Button Switching")
    public void testRadioButtonSwitching() {
        // Best Practice 5: Test switching behavior
        
        // Select Radio1
        homePage.clickRadio1();
        softAssert.assertTrue(homePage.isRadio1Selected(), RahulShettyMessages.RADIO1_SELECTED);
        
        // Switch to Radio2
        homePage.clickRadio2();
        softAssert.assertFalse(homePage.isRadio1Selected(), 
            RahulShettyMessages.RADIO1_DESELECTED_AFTER_RADIO2);
        softAssert.assertTrue(homePage.isRadio2Selected(), 
            RahulShettyMessages.RADIO2_SELECTED);
        
        // Switch to Radio3
        homePage.clickRadio3();
        softAssert.assertFalse(homePage.isRadio2Selected(), 
            RahulShettyMessages.RADIO2_DESELECTED_AFTER_RADIO3);
        softAssert.assertTrue(homePage.isRadio3Selected(), 
            RahulShettyMessages.RADIO3_SELECTED);
        
        // Switch back to Radio1
        homePage.clickRadio1();
        softAssert.assertFalse(homePage.isRadio3Selected(), 
            RahulShettyMessages.RADIO3_DESELECTED_AFTER_RADIO1);
        softAssert.assertTrue(homePage.isRadio1Selected(), 
            RahulShettyMessages.RADIO1_SELECTED_AGAIN);
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"smoke", "functional", "regression"}, description = "Verify default radio button state (if any)")
    @Description("Validate the default state of radio buttons and ensure maximum one is selected")
    @Severity(SeverityLevel.NORMAL)
    @Story("Radio Button Display")
    public void testDefaultRadioButtonState() {
        // Best Practice 6: Verify initial/default state
        // Check if any radio button is selected by default
        
        boolean radio1Selected = homePage.isRadio1Selected();
        boolean radio2Selected = homePage.isRadio2Selected();
        boolean radio3Selected = homePage.isRadio3Selected();
        
        // Document the default state (none selected is valid)
        System.out.println("Default state - Radio1: " + radio1Selected + 
                          ", Radio2: " + radio2Selected + 
                          ", Radio3: " + radio3Selected);
        
        // Verify that if any is selected, only one is selected
        int selectedCount = (radio1Selected ? 1 : 0) + 
                          (radio2Selected ? 1 : 0) + 
                          (radio3Selected ? 1 : 0);
        
        softAssert.assertTrue(selectedCount <= 1, 
            RahulShettyMessages.MAX_ONE_RADIO_SELECTED_DEFAULT);
        
        softAssert.assertAll();
    }
}
