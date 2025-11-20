package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.TestConstants;
import com.framework.utils.TestMessages;
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
            TestMessages.ALERT_BUTTON_DISPLAYED);
        softAssert.assertTrue(homePage.isAlertButtonEnabled(), 
            TestMessages.ALERT_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "functional", "regression"}, description = "Verify alert popup appears with entered name")
    public void testAlertWithEnteredName() {
        // Enter name and trigger alert
        homePage.enterName(TestConstants.TEST_USER_NAME);
        homePage.clickAlert();
        
        // Switch to alert and verify
        Alert alert = driver.switchTo().alert();
        softAssert.assertNotNull(alert, TestMessages.ALERT_PRESENT);
        
        String alertText = alert.getText();
        softAssert.assertFalse(alertText.isEmpty(), 
            TestMessages.ALERT_TEXT_NOT_EMPTY);
        softAssert.assertTrue(alertText.contains(TestConstants.TEST_USER_NAME), 
            TestMessages.ALERT_TEXT_CONTAINS_NAME);
        
        // Accept alert
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify alert message format and content")
    public void testAlertMessageFormat() {
        String testName = TestConstants.SAMPLE_NAME_SELENIUM;
        homePage.enterName(testName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        
        // Verify alert contains the name
        softAssert.assertTrue(alertText.contains(testName), 
            TestMessages.ALERT_CONTAINS_EXACT_NAME + ": " + testName);
        
        // Verify alert has standard format (starts with "Hello")
        softAssert.assertTrue(alertText.startsWith("Hello"), 
            TestMessages.ALERT_STARTS_WITH_HELLO);
        
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify alert with empty name field")
    public void testAlertWithEmptyName() {
        // Click alert without entering name
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        softAssert.assertNotNull(alert, 
            TestMessages.ALERT_WITH_EMPTY_NAME);
        
        String alertText = alert.getText();
        softAssert.assertFalse(alertText.isEmpty(), 
            TestMessages.ALERT_DISPLAY_MESSAGE_EMPTY_NAME);
        
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify page remains interactive after accepting alert")
    public void testPageInteractiveAfterAlert() {
        homePage.enterName(TestConstants.AUTOMATION_TEST_NAME);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
        
        // Verify page is still interactive
        softAssert.assertTrue(homePage.isAlertButtonDisplayed(), 
            TestMessages.PAGE_INTERACTIVE_AFTER_ALERT);
        softAssert.assertTrue(homePage.isNameInputDisplayed(), 
            TestMessages.NAME_INPUT_DISPLAYED_AFTER_ALERT);
        
        // Verify we can interact with elements
        homePage.clearName();
        homePage.enterName(TestConstants.NEW_NAME);
        String nameValue = homePage.getNameValue();
        softAssert.assertEquals(nameValue, TestConstants.NEW_NAME, 
            TestMessages.ENTER_TEXT_AFTER_ALERT);
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify alert can be triggered multiple times")
    public void testMultipleAlertTriggers() {
        // First alert
        homePage.enterName(TestConstants.FIRST_ALERT_NAME);
        homePage.clickAlert();
        Alert alert1 = driver.switchTo().alert();
        String firstAlertText = alert1.getText();
        softAssert.assertTrue(firstAlertText.contains(TestConstants.FIRST_ALERT_NAME), 
            TestMessages.FIRST_ALERT_CONTAINS_TEXT);
        alert1.accept();
        
        // Second alert with different name
        homePage.clearName();
        homePage.enterName(TestConstants.SECOND_ALERT_NAME);
        homePage.clickAlert();
        Alert alert2 = driver.switchTo().alert();
        String secondAlertText = alert2.getText();
        softAssert.assertTrue(secondAlertText.contains(TestConstants.SECOND_ALERT_NAME), 
            TestMessages.SECOND_ALERT_CONTAINS_TEXT);
        alert2.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"regression"}, description = "Verify alert with special characters in name")
    public void testAlertWithSpecialCharacters() {
        String specialName = TestConstants.SPECIAL_CHARS_NAME;
        homePage.enterName(specialName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains(specialName), 
            TestMessages.ALERT_DISPLAYS_SPECIAL_CHARS);
        
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify alert with very long name")
    public void testAlertWithLongName() {
        String longName = TestConstants.LONG_NAME;
        homePage.enterName(longName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains(longName), 
            TestMessages.ALERT_DISPLAYS_LONG_NAMES);
        
        alert.accept();
        
        softAssert.assertAll();
    }

    // ==================== CONFIRM BOX TESTS ====================

    @Test(priority = 9, groups = {"smoke", "functional", "regression"}, description = "Verify Confirm button is displayed and enabled")
    public void testConfirmButtonVisibilityAndState() {
        softAssert.assertTrue(homePage.isConfirmBoxButtonDisplayed(), 
            TestMessages.CONFIRM_BUTTON_DISPLAYED);
        softAssert.assertTrue(homePage.isConfirmBoxButtonEnabled(), 
            TestMessages.CONFIRM_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"smoke", "functional", "regression"}, description = "Verify confirm box appears with entered name")
    public void testConfirmBoxWithEnteredName() {
        homePage.enterName(TestConstants.TEST_USER_NAME);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        softAssert.assertNotNull(confirm, TestMessages.CONFIRM_BOX_PRESENT);
        
        String confirmText = confirm.getText();
        softAssert.assertFalse(confirmText.isEmpty(), 
            TestMessages.CONFIRM_TEXT_NOT_EMPTY);
        softAssert.assertTrue(confirmText.contains(TestConstants.TEST_USER_NAME), 
            TestMessages.CONFIRM_TEXT_CONTAINS_NAME);
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"functional", "regression"}, description = "Verify confirm box message format")
    public void testConfirmBoxMessageFormat() {
        String testName = TestConstants.SAMPLE_NAME_CONFIRM;
        homePage.enterName(testName);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        
        // Verify confirm box contains the name
        softAssert.assertTrue(confirmText.contains(testName), 
            TestMessages.CONFIRM_BOX_CONTAINS_NAME + ": " + testName);
        
        // Verify confirm box has standard format
        softAssert.assertTrue(confirmText.startsWith("Hello"), 
            TestMessages.CONFIRM_STARTS_WITH_HELLO);
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"functional", "regression"}, description = "Verify accepting confirm box")
    public void testAcceptConfirmBox() {
        homePage.enterName(TestConstants.ACCEPT_TEST_NAME);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(TestConstants.ACCEPT_TEST_NAME), 
            TestMessages.CONFIRM_DISPLAYS_ENTERED_NAME);
        
        // Accept the confirm box
        confirm.accept();
        
        // Verify no alert is present after accepting
        boolean alertPresent = isAlertPresent();
        softAssert.assertFalse(alertPresent, 
            TestMessages.NO_ALERT_AFTER_ACCEPTING);
        
        softAssert.assertAll();
    }

    @Test(priority = 13, groups = {"functional", "regression"}, description = "Verify dismissing confirm box")
    public void testDismissConfirmBox() {
        homePage.enterName(TestConstants.DISMISS_TEST_NAME);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(TestConstants.DISMISS_TEST_NAME), 
            TestMessages.CONFIRM_DISPLAYS_ENTERED_NAME);
        
        // Dismiss the confirm box
        confirm.dismiss();
        
        // Verify no alert is present after dismissing
        boolean alertPresent = isAlertPresent();
        softAssert.assertFalse(alertPresent, 
            TestMessages.CONFIRM_DISMISSED);
        
        softAssert.assertAll();
    }

    @Test(priority = 14, groups = {"functional", "regression"}, description = "Verify confirm box with empty name")
    public void testConfirmBoxWithEmptyName() {
        // Click confirm box without entering name
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        softAssert.assertNotNull(confirm, 
            TestMessages.CONFIRM_WITH_EMPTY_NAME);
        
        String confirmText = confirm.getText();
        softAssert.assertFalse(confirmText.isEmpty(), 
            TestMessages.CONFIRM_DISPLAYS_MESSAGE_EMPTY_NAME);
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 15, groups = {"functional", "regression"}, description = "Verify page remains interactive after handling confirm box")
    public void testPageInteractiveAfterConfirmBox() {
        homePage.enterName(TestConstants.INTERACTIVE_TEST_NAME);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        confirm.accept();
        
        // Verify page is still interactive
        softAssert.assertTrue(homePage.isConfirmBoxButtonDisplayed(), 
            TestMessages.PAGE_INTERACTIVE_AFTER_CONFIRM);
        softAssert.assertTrue(homePage.isNameInputDisplayed(), 
            TestMessages.NAME_INPUT_DISPLAYED_AFTER_CONFIRM);
        
        // Verify we can interact with elements
        homePage.clearName();
        homePage.enterName(TestConstants.AFTER_CONFIRM_NAME);
        String nameValue = homePage.getNameValue();
        softAssert.assertEquals(nameValue, TestConstants.AFTER_CONFIRM_NAME, 
            TestMessages.ENTER_TEXT_AFTER_CONFIRM);
        
        softAssert.assertAll();
    }

    @Test(priority = 16, groups = {"functional", "regression"}, description = "Verify multiple confirm box triggers")
    public void testMultipleConfirmBoxTriggers() {
        // First confirm box - Accept
        homePage.enterName(TestConstants.FIRST_CONFIRM_NAME);
        homePage.clickConfirmBox();
        Alert confirm1 = driver.switchTo().alert();
        softAssert.assertTrue(confirm1.getText().contains(TestConstants.FIRST_CONFIRM_NAME), 
            TestMessages.FIRST_CONFIRM_CONTAINS_TEXT);
        confirm1.accept();
        
        // Second confirm box - Dismiss
        homePage.clearName();
        homePage.enterName(TestConstants.SECOND_CONFIRM_NAME);
        homePage.clickConfirmBox();
        Alert confirm2 = driver.switchTo().alert();
        softAssert.assertTrue(confirm2.getText().contains(TestConstants.SECOND_CONFIRM_NAME), 
            TestMessages.SECOND_CONFIRM_CONTAINS_TEXT);
        confirm2.dismiss();
        
        softAssert.assertAll();
    }

    @Test(priority = 17, groups = {"regression"}, description = "Verify confirm box with special characters")
    public void testConfirmBoxWithSpecialCharacters() {
        String specialName = TestConstants.CONFIRM_SPECIAL_CHARS_NAME;
        homePage.enterName(specialName);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(specialName), 
            TestMessages.CONFIRM_DISPLAYS_SPECIAL_CHARS);
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    // ==================== NAME INPUT FIELD TESTS ====================

    @Test(priority = 18, groups = {"smoke", "functional", "regression"}, description = "Verify name input field is displayed and enabled")
    public void testNameInputFieldVisibilityAndState() {
        softAssert.assertTrue(homePage.isNameInputDisplayed(), 
            TestMessages.NAME_INPUT_DISPLAYED);
        softAssert.assertTrue(homePage.isNameInputEnabled(), 
            TestMessages.NAME_INPUT_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 19, groups = {"functional", "regression"}, description = "Verify name input field accepts text")
    public void testNameInputAcceptsText() {
        homePage.enterName(TestConstants.TEST_INPUT_NAME);
        String value = homePage.getNameValue();
        
        softAssert.assertEquals(value, TestConstants.TEST_INPUT_NAME, 
            TestMessages.NAME_INPUT_ACCEPTS_TEXT);
        
        softAssert.assertAll();
    }

    @Test(priority = 20, groups = {"functional", "regression"}, description = "Verify name input can be cleared")
    public void testNameInputCanBeCleared() {
        homePage.enterName(TestConstants.CLEAR_TEST_NAME);
        softAssert.assertEquals(homePage.getNameValue(), TestConstants.CLEAR_TEST_NAME, 
            TestMessages.NAME_SHOULD_BE_ENTERED);
        
        homePage.clearName();
        String clearedValue = homePage.getNameValue();
        softAssert.assertTrue(clearedValue.isEmpty(), 
            TestMessages.NAME_INPUT_EMPTY_AFTER_CLEAR);
        
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
