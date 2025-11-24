package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.rahulshetty.RahulShettyConstants;
import com.framework.utils.rahulshetty.RahulShettyMessages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Comprehensive test suite for Alert and Confirm box functionality
 * Tests verify popup messages, accept/dismiss behavior, and page interaction
 */
public class AlertTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    // ==================== ALERT BUTTON TESTS ====================

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify Alert button is displayed and enabled")
    public void testAlertButtonVisibilityAndState() {
        softAssert.assertTrue(homePage.isAlertButtonDisplayed(), 
            RahulShettyMessages.ALERT_BUTTON_DISPLAYED);
        softAssert.assertTrue(homePage.isAlertButtonEnabled(), 
            RahulShettyMessages.ALERT_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "functional", "regression"}, description = "Verify alert popup appears with entered name")
    public void testAlertWithEnteredName() {
        // Enter name and trigger alert
        homePage.enterName(RahulShettyConstants.TEST_USER_NAME);
        homePage.clickAlert();
        
        // Switch to alert and verify
        Alert alert = driver.switchTo().alert();
        softAssert.assertNotNull(alert, RahulShettyMessages.ALERT_PRESENT);
        
        String alertText = alert.getText();
        softAssert.assertFalse(alertText.isEmpty(), 
            RahulShettyMessages.ALERT_TEXT_NOT_EMPTY);
        softAssert.assertTrue(alertText.contains(RahulShettyConstants.TEST_USER_NAME), 
            RahulShettyMessages.ALERT_TEXT_CONTAINS_NAME);
        
        // Accept alert
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify alert message format and content")
    public void testAlertMessageFormat() {
        String testName = RahulShettyConstants.SAMPLE_NAME_SELENIUM;
        homePage.enterName(testName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        
        // Verify alert contains the name
        softAssert.assertTrue(alertText.contains(testName), 
            RahulShettyMessages.ALERT_CONTAINS_EXACT_NAME + ": " + testName);
        
        // Verify alert has standard format (starts with "Hello")
        softAssert.assertTrue(alertText.startsWith("Hello"), 
            RahulShettyMessages.ALERT_STARTS_WITH_HELLO);
        
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify alert with empty name field")
    public void testAlertWithEmptyName() {
        // Click alert without entering name
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        softAssert.assertNotNull(alert, 
            RahulShettyMessages.ALERT_WITH_EMPTY_NAME);
        
        String alertText = alert.getText();
        softAssert.assertFalse(alertText.isEmpty(), 
            RahulShettyMessages.ALERT_DISPLAY_MESSAGE_EMPTY_NAME);
        
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify page remains interactive after accepting alert")
    public void testPageInteractiveAfterAlert() {
        homePage.enterName(RahulShettyConstants.AUTOMATION_TEST_NAME);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
        
        // Verify page is still interactive
        softAssert.assertTrue(homePage.isAlertButtonDisplayed(), 
            RahulShettyMessages.PAGE_INTERACTIVE_AFTER_ALERT);
        softAssert.assertTrue(homePage.isNameInputDisplayed(), 
            RahulShettyMessages.NAME_INPUT_DISPLAYED_AFTER_ALERT);
        
        // Verify we can interact with elements
        homePage.clearName();
        homePage.enterName(RahulShettyConstants.NEW_NAME);
        String nameValue = homePage.getNameValue();
        softAssert.assertEquals(nameValue, RahulShettyConstants.NEW_NAME, 
            RahulShettyMessages.ENTER_TEXT_AFTER_ALERT);
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify alert can be triggered multiple times")
    public void testMultipleAlertTriggers() {
        // First alert
        homePage.enterName(RahulShettyConstants.FIRST_ALERT_NAME);
        homePage.clickAlert();
        Alert alert1 = driver.switchTo().alert();
        String firstAlertText = alert1.getText();
        softAssert.assertTrue(firstAlertText.contains(RahulShettyConstants.FIRST_ALERT_NAME), 
            RahulShettyMessages.FIRST_ALERT_CONTAINS_TEXT);
        alert1.accept();
        
        // Second alert with different name
        homePage.clearName();
        homePage.enterName(RahulShettyConstants.SECOND_ALERT_NAME);
        homePage.clickAlert();
        Alert alert2 = driver.switchTo().alert();
        String secondAlertText = alert2.getText();
        softAssert.assertTrue(secondAlertText.contains(RahulShettyConstants.SECOND_ALERT_NAME), 
            RahulShettyMessages.SECOND_ALERT_CONTAINS_TEXT);
        alert2.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"regression"}, description = "Verify alert with special characters in name")
    public void testAlertWithSpecialCharacters() {
        String specialName = RahulShettyConstants.SPECIAL_CHARS_NAME;
        homePage.enterName(specialName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains(specialName), 
            RahulShettyMessages.ALERT_DISPLAYS_SPECIAL_CHARS);
        
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify alert with very long name")
    public void testAlertWithLongName() {
        String longName = RahulShettyConstants.LONG_NAME;
        homePage.enterName(longName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains(longName), 
            RahulShettyMessages.ALERT_DISPLAYS_LONG_NAMES);
        
        alert.accept();
        
        softAssert.assertAll();
    }

    // ==================== CONFIRM BOX TESTS ====================

    @Test(priority = 9, groups = {"smoke", "functional", "regression"}, description = "Verify Confirm button is displayed and enabled")
    public void testConfirmButtonVisibilityAndState() {
        softAssert.assertTrue(homePage.isConfirmBoxButtonDisplayed(), 
            RahulShettyMessages.CONFIRM_BUTTON_DISPLAYED);
        softAssert.assertTrue(homePage.isConfirmBoxButtonEnabled(), 
            RahulShettyMessages.CONFIRM_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"smoke", "functional", "regression"}, description = "Verify confirm box appears with entered name")
    public void testConfirmBoxWithEnteredName() {
        homePage.enterName(RahulShettyConstants.TEST_USER_NAME);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        softAssert.assertNotNull(confirm, RahulShettyMessages.CONFIRM_BOX_PRESENT);
        
        String confirmText = confirm.getText();
        softAssert.assertFalse(confirmText.isEmpty(), 
            RahulShettyMessages.CONFIRM_TEXT_NOT_EMPTY);
        softAssert.assertTrue(confirmText.contains(RahulShettyConstants.TEST_USER_NAME), 
            RahulShettyMessages.CONFIRM_TEXT_CONTAINS_NAME);
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"functional", "regression"}, description = "Verify confirm box message format")
    public void testConfirmBoxMessageFormat() {
        String testName = RahulShettyConstants.SAMPLE_NAME_CONFIRM;
        homePage.enterName(testName);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        
        // Verify confirm box contains the name
        softAssert.assertTrue(confirmText.contains(testName), 
            RahulShettyMessages.CONFIRM_BOX_CONTAINS_NAME + ": " + testName);
        
        // Verify confirm box has standard format
        softAssert.assertTrue(confirmText.startsWith("Hello"), 
            RahulShettyMessages.CONFIRM_STARTS_WITH_HELLO);
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"functional", "regression"}, description = "Verify accepting confirm box")
    public void testAcceptConfirmBox() {
        homePage.enterName(RahulShettyConstants.ACCEPT_TEST_NAME);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(RahulShettyConstants.ACCEPT_TEST_NAME), 
            RahulShettyMessages.CONFIRM_DISPLAYS_ENTERED_NAME);
        
        // Accept the confirm box
        confirm.accept();
        
        // Verify no alert is present after accepting
        boolean alertPresent = isAlertPresent();
        softAssert.assertFalse(alertPresent, 
            RahulShettyMessages.NO_ALERT_AFTER_ACCEPTING);
        
        softAssert.assertAll();
    }

    @Test(priority = 13, groups = {"functional", "regression"}, description = "Verify dismissing confirm box")
    public void testDismissConfirmBox() {
        homePage.enterName(RahulShettyConstants.DISMISS_TEST_NAME);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(RahulShettyConstants.DISMISS_TEST_NAME), 
            RahulShettyMessages.CONFIRM_DISPLAYS_ENTERED_NAME);
        
        // Dismiss the confirm box
        confirm.dismiss();
        
        // Verify no alert is present after dismissing
        boolean alertPresent = isAlertPresent();
        softAssert.assertFalse(alertPresent, 
            RahulShettyMessages.CONFIRM_DISMISSED);
        
        softAssert.assertAll();
    }

    @Test(priority = 14, groups = {"functional", "regression"}, description = "Verify confirm box with empty name")
    public void testConfirmBoxWithEmptyName() {
        // Click confirm box without entering name
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        softAssert.assertNotNull(confirm, 
            RahulShettyMessages.CONFIRM_WITH_EMPTY_NAME);
        
        String confirmText = confirm.getText();
        softAssert.assertFalse(confirmText.isEmpty(), 
            RahulShettyMessages.CONFIRM_DISPLAYS_MESSAGE_EMPTY_NAME);
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 15, groups = {"functional", "regression"}, description = "Verify page remains interactive after handling confirm box")
    public void testPageInteractiveAfterConfirmBox() {
        homePage.enterName(RahulShettyConstants.INTERACTIVE_TEST_NAME);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        confirm.accept();
        
        // Verify page is still interactive
        softAssert.assertTrue(homePage.isConfirmBoxButtonDisplayed(), 
            RahulShettyMessages.PAGE_INTERACTIVE_AFTER_CONFIRM);
        softAssert.assertTrue(homePage.isNameInputDisplayed(), 
            RahulShettyMessages.NAME_INPUT_DISPLAYED_AFTER_CONFIRM);
        
        // Verify we can interact with elements
        homePage.clearName();
        homePage.enterName(RahulShettyConstants.AFTER_CONFIRM_NAME);
        String nameValue = homePage.getNameValue();
        softAssert.assertEquals(nameValue, RahulShettyConstants.AFTER_CONFIRM_NAME, 
            RahulShettyMessages.ENTER_TEXT_AFTER_CONFIRM);
        
        softAssert.assertAll();
    }

    @Test(priority = 16, groups = {"functional", "regression"}, description = "Verify multiple confirm box triggers")
    public void testMultipleConfirmBoxTriggers() {
        // First confirm box - Accept
        homePage.enterName(RahulShettyConstants.FIRST_CONFIRM_NAME);
        homePage.clickConfirmBox();
        Alert confirm1 = driver.switchTo().alert();
        softAssert.assertTrue(confirm1.getText().contains(RahulShettyConstants.FIRST_CONFIRM_NAME), 
            RahulShettyMessages.FIRST_CONFIRM_CONTAINS_TEXT);
        confirm1.accept();
        
        // Second confirm box - Dismiss
        homePage.clearName();
        homePage.enterName(RahulShettyConstants.SECOND_CONFIRM_NAME);
        homePage.clickConfirmBox();
        Alert confirm2 = driver.switchTo().alert();
        softAssert.assertTrue(confirm2.getText().contains(RahulShettyConstants.SECOND_CONFIRM_NAME), 
            RahulShettyMessages.SECOND_CONFIRM_CONTAINS_TEXT);
        confirm2.dismiss();
        
        softAssert.assertAll();
    }

    @Test(priority = 17, groups = {"regression"}, description = "Verify confirm box with special characters")
    public void testConfirmBoxWithSpecialCharacters() {
        String specialName = RahulShettyConstants.CONFIRM_SPECIAL_CHARS_NAME;
        homePage.enterName(specialName);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(specialName), 
            RahulShettyMessages.CONFIRM_DISPLAYS_SPECIAL_CHARS);
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    // ==================== NAME INPUT FIELD TESTS ====================

    @Test(priority = 18, groups = {"smoke", "functional", "regression"}, description = "Verify name input field is displayed and enabled")
    public void testNameInputFieldVisibilityAndState() {
        softAssert.assertTrue(homePage.isNameInputDisplayed(), 
            RahulShettyMessages.NAME_INPUT_DISPLAYED);
        softAssert.assertTrue(homePage.isNameInputEnabled(), 
            RahulShettyMessages.NAME_INPUT_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 19, groups = {"functional", "regression"}, description = "Verify name input field accepts text")
    public void testNameInputAcceptsText() {
        homePage.enterName(RahulShettyConstants.TEST_INPUT_NAME);
        String value = homePage.getNameValue();
        
        softAssert.assertEquals(value, RahulShettyConstants.TEST_INPUT_NAME, 
            RahulShettyMessages.NAME_INPUT_ACCEPTS_TEXT);
        
        softAssert.assertAll();
    }

    @Test(priority = 20, groups = {"functional", "regression"}, description = "Verify name input can be cleared")
    public void testNameInputCanBeCleared() {
        homePage.enterName(RahulShettyConstants.CLEAR_TEST_NAME);
        softAssert.assertEquals(homePage.getNameValue(), RahulShettyConstants.CLEAR_TEST_NAME, 
            RahulShettyMessages.NAME_SHOULD_BE_ENTERED);
        
        homePage.clearName();
        String clearedValue = homePage.getNameValue();
        softAssert.assertTrue(clearedValue.isEmpty(), 
            RahulShettyMessages.NAME_INPUT_EMPTY_AFTER_CLEAR);
        
        softAssert.assertAll();
    }

    // ==================== HELPER METHODS ====================

    /**
     * Check if an alert is present without throwing exception
     * @return true if alert is present, false otherwise
     */
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
