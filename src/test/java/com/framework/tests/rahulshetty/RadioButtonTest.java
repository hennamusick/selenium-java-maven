package com.framework.tests.rahulshetty;

import com.framework.pages.HomePage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RadioButtonTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() throws InterruptedException {
        // Using baseUrl.1 - Rahul Shetty Academy AutomationPractice
        driver.get(ConfigReader.getBaseUrl(1));
        Thread.sleep(2000);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify radio buttons are displayed and enabled")
    public void testRadioButtonsVisibilityAndState() {
        // Best Practice 1: Verify all radio buttons are displayed
        softAssert.assertTrue(homePage.isRadio1Displayed(), "Radio1 should be displayed");
        softAssert.assertTrue(homePage.isRadio2Displayed(), "Radio2 should be displayed");
        softAssert.assertTrue(homePage.isRadio3Displayed(), "Radio3 should be displayed");
        
        // Best Practice 2: Verify all radio buttons are enabled
        softAssert.assertTrue(homePage.isRadio1Enabled(), "Radio1 should be enabled");
        softAssert.assertTrue(homePage.isRadio2Enabled(), "Radio2 should be enabled");
        softAssert.assertTrue(homePage.isRadio3Enabled(), "Radio3 should be enabled");
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify Radio1 selection and mutual exclusivity")
    public void testRadio1Selection() {
        // Best Practice 3: Click and verify selection
        homePage.clickRadio1();
        
        softAssert.assertTrue(homePage.isRadio1Selected(), 
            "Radio1 should be selected after clicking");
        
        // Best Practice 4: Verify mutual exclusivity (only one can be selected)
        softAssert.assertFalse(homePage.isRadio2Selected(), 
            "Radio2 should NOT be selected when Radio1 is selected");
        softAssert.assertFalse(homePage.isRadio3Selected(), 
            "Radio3 should NOT be selected when Radio1 is selected");
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify Radio2 selection and mutual exclusivity")
    public void testRadio2Selection() {
        homePage.clickRadio2();
        
        softAssert.assertTrue(homePage.isRadio2Selected(), 
            "Radio2 should be selected after clicking");
        
        // Verify others are not selected
        softAssert.assertFalse(homePage.isRadio1Selected(), 
            "Radio1 should NOT be selected when Radio2 is selected");
        softAssert.assertFalse(homePage.isRadio3Selected(), 
            "Radio3 should NOT be selected when Radio2 is selected");
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify Radio3 selection and mutual exclusivity")
    public void testRadio3Selection() {
        homePage.clickRadio3();
        
        softAssert.assertTrue(homePage.isRadio3Selected(), 
            "Radio3 should be selected after clicking");
        
        // Verify others are not selected
        softAssert.assertFalse(homePage.isRadio1Selected(), 
            "Radio1 should NOT be selected when Radio3 is selected");
        softAssert.assertFalse(homePage.isRadio2Selected(), 
            "Radio2 should NOT be selected when Radio3 is selected");
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify switching between radio buttons")
    public void testRadioButtonSwitching() {
        // Best Practice 5: Test switching behavior
        
        // Select Radio1
        homePage.clickRadio1();
        softAssert.assertTrue(homePage.isRadio1Selected(), "Radio1 should be selected");
        
        // Switch to Radio2
        homePage.clickRadio2();
        softAssert.assertFalse(homePage.isRadio1Selected(), 
            "Radio1 should be deselected after switching to Radio2");
        softAssert.assertTrue(homePage.isRadio2Selected(), 
            "Radio2 should be selected");
        
        // Switch to Radio3
        homePage.clickRadio3();
        softAssert.assertFalse(homePage.isRadio2Selected(), 
            "Radio2 should be deselected after switching to Radio3");
        softAssert.assertTrue(homePage.isRadio3Selected(), 
            "Radio3 should be selected");
        
        // Switch back to Radio1
        homePage.clickRadio1();
        softAssert.assertFalse(homePage.isRadio3Selected(), 
            "Radio3 should be deselected after switching to Radio1");
        softAssert.assertTrue(homePage.isRadio1Selected(), 
            "Radio1 should be selected again");
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"smoke", "functional", "regression"}, description = "Verify default radio button state (if any)")
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
            "At most one radio button should be selected by default");
        
        softAssert.assertAll();
    }
}
