package com.framework.tests.rahulshetty;

import com.framework.pages.HomePage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DropdownTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        // Using baseUrl.1 - Rahul Shetty Academy AutomationPractice
        driver.get(ConfigReader.getBaseUrl(1));
        homePage = new HomePage(driver);
    }

    // Dropdown Tests
    
    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify dropdown is displayed")
    public void testDropdownDisplayed() {
        boolean isDisplayed = homePage.isDropdownDisplayed();
        softAssert.assertTrue(isDisplayed, 
            "Dropdown should be displayed on the page");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify selecting 'Option1' from dropdown")
    public void testSelectOption1() {
        homePage.selectDropdownByVisibleText("Option1");
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, "Option1", 
            "Selected dropdown text should be 'Option1'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify selecting 'Option2' from dropdown")
    public void testSelectOption2() {
        homePage.selectDropdownByVisibleText("Option2");
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, "Option2", 
            "Selected dropdown text should be 'Option2'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify selecting 'Option3' from dropdown")
    public void testSelectOption3() {
        homePage.selectDropdownByVisibleText("Option3");
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, "Option3", 
            "Selected dropdown text should be 'Option3'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify all dropdown options are available")
    public void testDropdownHasAllOptions() {
        List<String> options = homePage.getAllDropdownOptions();
        
        softAssert.assertTrue(options.contains("Select"), 
            "Dropdown should contain 'Select' option");
        softAssert.assertTrue(options.contains("Option1"), 
            "Dropdown should contain 'Option1'");
        softAssert.assertTrue(options.contains("Option2"), 
            "Dropdown should contain 'Option2'");
        softAssert.assertTrue(options.contains("Option3"), 
            "Dropdown should contain 'Option3'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 6, groups = {"regression"}, description = "Verify selecting dropdown by index")
    public void testSelectDropdownByIndex() {
        // Index 1 should be Option1 (index 0 is Select/default)
        homePage.selectDropdownByIndex(1);
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, "Option1", 
            "Selecting index 1 should select 'Option1'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 7, groups = {"regression"}, description = "Verify selecting dropdown by value")
    public void testSelectDropdownByValue() {
        homePage.selectDropdownByValue("option1");
        
        String selectedValue = homePage.getSelectedDropdownValue();
        softAssert.assertEquals(selectedValue, "option1", 
            "Selected dropdown value should be 'option1'");
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, "Option1", 
            "Selected dropdown text should be 'Option1'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 8, groups = {"smoke", "functional"}, description = "Verify default 'Select' option is present")
    public void testDefaultSelectOption() {
        List<String> options = homePage.getAllDropdownOptions();
        
        softAssert.assertFalse(options.isEmpty(), 
            "Dropdown should have options");
        softAssert.assertEquals(options.get(0), "Select", 
            "First option should be 'Select'");
        
        softAssert.assertAll();
    }
}
