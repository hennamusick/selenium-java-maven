package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.rahulshetty.RahulShettyConstants;
import com.framework.utils.rahulshetty.RahulShettyMessages;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for show/hide element functionality on Rahul Shetty Academy practice page.
 */
@Epic("Rahul Shetty Academy")
@Feature("Element Visibility Controls")
public class HideShowTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
        homePage.scrollToHideShowSection(); // Scroll to make elements fully visible
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify Hide and Show buttons are displayed and enabled")
    public void testHideShowButtonsVisibilityAndState() {
        softAssert.assertTrue(homePage.isHideButtonDisplayed(), 
            RahulShettyMessages.HIDE_BUTTON_DISPLAYED);
        softAssert.assertTrue(homePage.isShowButtonDisplayed(), 
            RahulShettyMessages.SHOW_BUTTON_DISPLAYED);
        softAssert.assertTrue(homePage.isHideButtonEnabled(), 
            RahulShettyMessages.HIDE_BUTTON_ENABLED);
        softAssert.assertTrue(homePage.isShowButtonEnabled(), 
            RahulShettyMessages.SHOW_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "functional", "regression"}, description = "Verify text box is displayed by default")
    public void testTextBoxDisplayedByDefault() {
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE_BY_DEFAULT);
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify clicking Hide button hides the text box")
    public void testHideButtonHidesTextBox() {
        // Verify text box is visible initially
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_DISPLAYED_INITIALLY);
        
        // Click Hide button
        homePage.clickHideButton();
        waitForPageToLoad();
        
        // Verify text box is now hidden
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_HIDDEN_AFTER_HIDE);
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify clicking Show button shows the text box")
    public void testShowButtonShowsTextBox() {
        // Hide the text box first
        homePage.clickHideButton();
        waitForPageToLoad();
        
        // Verify it's hidden
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_HIDDEN);
        
        // Click Show button
        homePage.clickShowButton();
        waitForPageToLoad();
        
        // Verify text box is now visible
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE_AFTER_SHOW);
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify Hide and Show cycle works correctly")
    public void testHideShowCycle() {
        // Initially visible
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE);
        
        // Hide it
        homePage.clickHideButton();
        waitForPageToLoad();
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_HIDDEN);
        
        // Show it
        homePage.clickShowButton();
        waitForPageToLoad();
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE);
        
        // Hide again
        homePage.clickHideButton();
        waitForPageToLoad();
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_HIDDEN);
        
        // Show again
        homePage.clickShowButton();
        waitForPageToLoad();
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE);
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify text box accepts input when visible")
    public void testTextBoxAcceptsInputWhenVisible() {
        // Ensure text box is visible
        if (!homePage.isDisplayedTextBoxVisible()) {
            homePage.clickShowButton();
            waitForPageToLoad();
        }
        
        // Enter text
        homePage.enterTextInDisplayedTextBox(RahulShettyConstants.HIDE_SHOW_TEST_TEXT);
        
        // Verify text was entered
        String actualValue = homePage.getDisplayedTextBoxValue();
        softAssert.assertEquals(actualValue, RahulShettyConstants.HIDE_SHOW_TEST_TEXT, 
            RahulShettyMessages.TEXTBOX_ACCEPTS_INPUT);
        
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify text box value persists after hide/show cycle")
    public void testTextBoxValuePersistsAfterHideShow() {
        // Ensure text box is visible and enter text
        if (!homePage.isDisplayedTextBoxVisible()) {
            homePage.clickShowButton();
            waitForPageToLoad();
        }
        
        homePage.enterTextInDisplayedTextBox(RahulShettyConstants.HIDE_SHOW_SAMPLE_TEXT);
        String valueBeforeHide = homePage.getDisplayedTextBoxValue();
        
        // Hide the text box
        homePage.clickHideButton();
        waitForPageToLoad();
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_HIDDEN);
        
        // Show the text box
        homePage.clickShowButton();
        waitForPageToLoad();
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE);
        
        // Verify value persists
        String valueAfterShow = homePage.getDisplayedTextBoxValue();
        softAssert.assertEquals(valueAfterShow, valueBeforeHide, 
            RahulShettyMessages.TEXTBOX_VALUE_PERSISTS);
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify Hide and Show buttons remain visible throughout")
    public void testHideShowButtonsAlwaysVisible() {
        // Initially visible
        softAssert.assertTrue(homePage.isHideButtonDisplayed(), 
            RahulShettyMessages.HIDE_BUTTON_REMAINS_VISIBLE);
        softAssert.assertTrue(homePage.isShowButtonDisplayed(), 
            RahulShettyMessages.SHOW_BUTTON_REMAINS_VISIBLE);
        
        // After hiding text box
        homePage.clickHideButton();
        waitForPageToLoad();
        softAssert.assertTrue(homePage.isHideButtonDisplayed(), 
            RahulShettyMessages.HIDE_BUTTON_REMAINS_VISIBLE);
        softAssert.assertTrue(homePage.isShowButtonDisplayed(), 
            RahulShettyMessages.SHOW_BUTTON_REMAINS_VISIBLE);
        
        // After showing text box
        homePage.clickShowButton();
        waitForPageToLoad();
        softAssert.assertTrue(homePage.isHideButtonDisplayed(), 
            RahulShettyMessages.HIDE_BUTTON_REMAINS_VISIBLE);
        softAssert.assertTrue(homePage.isShowButtonDisplayed(), 
            RahulShettyMessages.SHOW_BUTTON_REMAINS_VISIBLE);
        
        softAssert.assertAll();
    }

    @Test(priority = 9, groups = {"regression"}, description = "Verify clicking Hide button multiple times")
    public void testMultipleHideClicks() {
        // Ensure text box is visible
        if (!homePage.isDisplayedTextBoxVisible()) {
            homePage.clickShowButton();
            waitForPageToLoad();
        }
        
        // Hide it
        homePage.clickHideButton();
        waitForPageToLoad();
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_HIDDEN);
        
        // Click Hide again (should remain hidden)
        homePage.clickHideButton();
        waitForPageToLoad();
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_STILL_HIDDEN);
        
        // Click Hide third time
        homePage.clickHideButton();
        waitForPageToLoad();
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_STILL_HIDDEN);
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"regression"}, description = "Verify clicking Show button multiple times")
    public void testMultipleShowClicks() {
        // Hide the text box first
        homePage.clickHideButton();
        waitForPageToLoad();
        
        // Show it
        homePage.clickShowButton();
        waitForPageToLoad();
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE);
        
        // Click Show again (should remain visible)
        homePage.clickShowButton();
        waitForPageToLoad();
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_STILL_VISIBLE);
        
        // Click Show third time
        homePage.clickShowButton();
        waitForPageToLoad();
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_STILL_VISIBLE);
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"functional", "regression"}, description = "Verify text box is enabled when visible")
    public void testTextBoxEnabledWhenVisible() {
        // Ensure text box is visible
        if (!homePage.isDisplayedTextBoxVisible()) {
            homePage.clickShowButton();
            waitForPageToLoad();
        }
        
        // Verify it's enabled
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE);
        softAssert.assertTrue(homePage.isDisplayedTextBoxEnabled(), 
            RahulShettyMessages.TEXTBOX_ENABLED_WHEN_VISIBLE);
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"functional", "regression"}, description = "Verify clearing text box and hide/show cycle")
    public void testClearTextBoxAndHideShow() {
        // Ensure text box is visible
        if (!homePage.isDisplayedTextBoxVisible()) {
            homePage.clickShowButton();
            waitForPageToLoad();
        }
        
        // Enter text
        homePage.enterTextInDisplayedTextBox(RahulShettyConstants.HIDE_SHOW_TEST_TEXT);
        
        // Clear text
        homePage.clearDisplayedTextBox();
        String clearedValue = homePage.getDisplayedTextBoxValue();
        softAssert.assertEquals(clearedValue, "", 
            RahulShettyMessages.TEXTBOX_CLEARED_SUCCESSFULLY);
        
        // Hide and show
        homePage.clickHideButton();
        waitForPageToLoad();
        homePage.clickShowButton();
        waitForPageToLoad();
        
        // Verify text box is still empty
        String valueAfterShow = homePage.getDisplayedTextBoxValue();
        softAssert.assertEquals(valueAfterShow, "", 
            RahulShettyMessages.TEXTBOX_VALUE_PERSISTS);
        
        softAssert.assertAll();
    }

    @Test(priority = 13, groups = {"functional", "regression"}, description = "Verify entering new text after show")
    public void testEnterNewTextAfterShow() {
        // Hide and show cycle
        homePage.clickHideButton();
        waitForPageToLoad();
        homePage.clickShowButton();
        waitForPageToLoad();
        
        // Enter new text
        homePage.clearDisplayedTextBox();
        homePage.enterTextInDisplayedTextBox(RahulShettyConstants.HIDE_SHOW_NEW_TEXT);
        
        // Verify new text is entered
        String actualValue = homePage.getDisplayedTextBoxValue();
        softAssert.assertEquals(actualValue, RahulShettyConstants.HIDE_SHOW_NEW_TEXT, 
            RahulShettyMessages.TEXTBOX_ACCEPTS_INPUT);
        
        softAssert.assertAll();
    }

    @Test(priority = 14, groups = {"functional", "regression"}, description = "Verify rapid Hide/Show clicks")
    public void testRapidHideShowClicks() {
        // Rapid clicks - Hide
        for (int i = 0; i < 3; i++) {
            homePage.clickHideButton();
            homePage.clickShowButton();
        }
        
        waitForPageToLoad();
        
        // Should be visible after last show
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE);
        
        softAssert.assertAll();
    }

    @Test(priority = 15, groups = {"regression"}, description = "Verify starting with Hide then Show restores visibility")
    public void testStartWithHide() {
        // Click Hide first (even if already visible)
        homePage.clickHideButton();
        waitForPageToLoad();
        
        // Verify hidden
        softAssert.assertFalse(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_HIDDEN);
        
        // Click Show
        homePage.clickShowButton();
        waitForPageToLoad();
        
        // Verify visible
        softAssert.assertTrue(homePage.isDisplayedTextBoxVisible(), 
            RahulShettyMessages.TEXTBOX_VISIBLE);
        
        softAssert.assertAll();
    }
}
