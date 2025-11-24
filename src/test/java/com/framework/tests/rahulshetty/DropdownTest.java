package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.rahulshetty.RahulShettyConstants;
import com.framework.utils.rahulshetty.RahulShettyMessages;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DropdownTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    // Dropdown Tests
    
    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify dropdown is displayed")
    public void testDropdownDisplayed() {
        boolean isDisplayed = homePage.isDropdownDisplayed();
        softAssert.assertTrue(isDisplayed, 
            RahulShettyMessages.DROPDOWN_DISPLAYED);
        
        softAssert.assertAll();
    }
    
    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify selecting 'Option1' from dropdown")
    public void testSelectOption1() {
        homePage.selectDropdownByVisibleText(RahulShettyConstants.DROPDOWN_OPTION1);
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, RahulShettyConstants.DROPDOWN_OPTION1, 
            RahulShettyMessages.DROPDOWN_SELECTED_TEXT_OPTION1);
        
        softAssert.assertAll();
    }
    
    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify selecting 'Option2' from dropdown")
    public void testSelectOption2() {
        homePage.selectDropdownByVisibleText(RahulShettyConstants.DROPDOWN_OPTION2);
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, RahulShettyConstants.DROPDOWN_OPTION2, 
            RahulShettyMessages.DROPDOWN_SELECTED_TEXT_OPTION2);
        
        softAssert.assertAll();
    }
    
    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify selecting 'Option3' from dropdown")
    public void testSelectOption3() {
        homePage.selectDropdownByVisibleText(RahulShettyConstants.DROPDOWN_OPTION3);
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, RahulShettyConstants.DROPDOWN_OPTION3, 
            RahulShettyMessages.DROPDOWN_SELECTED_TEXT_OPTION3);
        
        softAssert.assertAll();
    }
    
    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify all dropdown options are available")
    public void testDropdownHasAllOptions() {
        List<String> options = homePage.getAllDropdownOptions();
        
        softAssert.assertTrue(options.contains("Select"), 
            RahulShettyMessages.DROPDOWN_CONTAINS_SELECT);
        softAssert.assertTrue(options.contains(RahulShettyConstants.DROPDOWN_OPTION1), 
            RahulShettyMessages.DROPDOWN_CONTAINS_OPTION1);
        softAssert.assertTrue(options.contains(RahulShettyConstants.DROPDOWN_OPTION2), 
            RahulShettyMessages.DROPDOWN_CONTAINS_OPTION2);
        softAssert.assertTrue(options.contains(RahulShettyConstants.DROPDOWN_OPTION3), 
            RahulShettyMessages.DROPDOWN_CONTAINS_OPTION3);
        
        softAssert.assertAll();
    }
    
    @Test(priority = 6, groups = {"regression"}, description = "Verify selecting dropdown by index")
    public void testSelectDropdownByIndex() {
        // Index 1 should be Option1 (index 0 is Select/default)
        homePage.selectDropdownByIndex(1);
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, RahulShettyConstants.DROPDOWN_OPTION1, 
            RahulShettyMessages.DROPDOWN_INDEX_1_IS_OPTION1);
        
        softAssert.assertAll();
    }
    
    @Test(priority = 7, groups = {"regression"}, description = "Verify selecting dropdown by value")
    public void testSelectDropdownByValue() {
        homePage.selectDropdownByValue("option1");
        
        String selectedValue = homePage.getSelectedDropdownValue();
        softAssert.assertEquals(selectedValue, "option1", 
            RahulShettyMessages.DROPDOWN_VALUE_OPTION1);
        
        String selectedText = homePage.getSelectedDropdownText();
        softAssert.assertEquals(selectedText, RahulShettyConstants.DROPDOWN_OPTION1, 
            RahulShettyMessages.DROPDOWN_SELECTED_TEXT_OPTION1);
        
        softAssert.assertAll();
    }
    
    @Test(priority = 8, groups = {"smoke", "functional"}, description = "Verify default 'Select' option is present")
    public void testDefaultSelectOption() {
        List<String> options = homePage.getAllDropdownOptions();
        
        softAssert.assertFalse(options.isEmpty(), 
            RahulShettyMessages.DROPDOWN_HAS_OPTIONS);
        softAssert.assertEquals(options.get(0), "Select", 
            RahulShettyMessages.DROPDOWN_FIRST_OPTION_SELECT);
        
        softAssert.assertAll();
    }
}
