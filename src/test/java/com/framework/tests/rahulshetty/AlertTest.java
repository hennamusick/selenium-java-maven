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
        String testName = "Selenium Automation";
        homePage.enterName(testName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        
        // Verify alert contains the name
        softAssert.assertTrue(alertText.contains(testName), 
            "Alert should contain the exact name entered: " + testName);
        
        // Verify alert has standard format (starts with "Hello")
        softAssert.assertTrue(alertText.startsWith("Hello"), 
            "Alert message should start with 'Hello'");
        
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
            "Alert should display message even with empty name");
        
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
            "Name input should still be displayed after alert");
        
        // Verify we can interact with elements
        homePage.clearName();
        homePage.enterName("New Name");
        String nameValue = homePage.getNameValue();
        softAssert.assertEquals(nameValue, "New Name", 
            "Should be able to enter new text after handling alert");
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify alert can be triggered multiple times")
    public void testMultipleAlertTriggers() {
        // First alert
        homePage.enterName("First Alert");
        homePage.clickAlert();
        Alert alert1 = driver.switchTo().alert();
        String firstAlertText = alert1.getText();
        softAssert.assertTrue(firstAlertText.contains("First Alert"), 
            "First alert should contain 'First Alert'");
        alert1.accept();
        
        // Second alert with different name
        homePage.clearName();
        homePage.enterName("Second Alert");
        homePage.clickAlert();
        Alert alert2 = driver.switchTo().alert();
        String secondAlertText = alert2.getText();
        softAssert.assertTrue(secondAlertText.contains("Second Alert"), 
            "Second alert should contain 'Second Alert'");
        alert2.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"regression"}, description = "Verify alert with special characters in name")
    public void testAlertWithSpecialCharacters() {
        String specialName = "Test@User#2025!";
        homePage.enterName(specialName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains(specialName), 
            "Alert should display special characters correctly");
        
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify alert with very long name")
    public void testAlertWithLongName() {
        String longName = "This is a very long name to test how the alert handles extended text input";
        homePage.enterName(longName);
        homePage.clickAlert();
        
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains(longName), 
            "Alert should display long names correctly");
        
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
        String testName = "Confirm Test";
        homePage.enterName(testName);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        
        // Verify confirm box contains the name
        softAssert.assertTrue(confirmText.contains(testName), 
            "Confirm box should contain the entered name: " + testName);
        
        // Verify confirm box has standard format
        softAssert.assertTrue(confirmText.startsWith("Hello"), 
            "Confirm message should start with 'Hello'");
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"functional", "regression"}, description = "Verify accepting confirm box")
    public void testAcceptConfirmBox() {
        homePage.enterName("Accept Test");
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains("Accept Test"), 
            "Confirm box should display entered name");
        
        // Accept the confirm box
        confirm.accept();
        
        // Verify no alert is present after accepting
        boolean alertPresent = isAlertPresent();
        softAssert.assertFalse(alertPresent, 
            "No alert should be present after accepting confirm box");
        
        softAssert.assertAll();
    }

    @Test(priority = 13, groups = {"functional", "regression"}, description = "Verify dismissing confirm box")
    public void testDismissConfirmBox() {
        homePage.enterName("Dismiss Test");
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains("Dismiss Test"), 
            "Confirm box should display entered name");
        
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
            "Confirm box should display message even with empty name");
        
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 15, groups = {"functional", "regression"}, description = "Verify page remains interactive after handling confirm box")
    public void testPageInteractiveAfterConfirmBox() {
        homePage.enterName("Interactive Test");
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        confirm.accept();
        
        // Verify page is still interactive
        softAssert.assertTrue(homePage.isConfirmBoxButtonDisplayed(), 
            TestMessages.PAGE_INTERACTIVE_AFTER_CONFIRM);
        softAssert.assertTrue(homePage.isNameInputDisplayed(), 
            "Name input should still be displayed after confirm box");
        
        // Verify we can interact with elements
        homePage.clearName();
        homePage.enterName("After Confirm");
        String nameValue = homePage.getNameValue();
        softAssert.assertEquals(nameValue, "After Confirm", 
            "Should be able to enter new text after handling confirm box");
        
        softAssert.assertAll();
    }

    @Test(priority = 16, groups = {"functional", "regression"}, description = "Verify multiple confirm box triggers")
    public void testMultipleConfirmBoxTriggers() {
        // First confirm box - Accept
        homePage.enterName("First Confirm");
        homePage.clickConfirmBox();
        Alert confirm1 = driver.switchTo().alert();
        softAssert.assertTrue(confirm1.getText().contains("First Confirm"), 
            "First confirm should contain 'First Confirm'");
        confirm1.accept();
        
        // Second confirm box - Dismiss
        homePage.clearName();
        homePage.enterName("Second Confirm");
        homePage.clickConfirmBox();
        Alert confirm2 = driver.switchTo().alert();
        softAssert.assertTrue(confirm2.getText().contains("Second Confirm"), 
            "Second confirm should contain 'Second Confirm'");
        confirm2.dismiss();
        
        softAssert.assertAll();
    }

    @Test(priority = 17, groups = {"regression"}, description = "Verify confirm box with special characters")
    public void testConfirmBoxWithSpecialCharacters() {
        String specialName = "Confirm@Test#2025!";
        homePage.enterName(specialName);
        homePage.clickConfirmBox();
        
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(specialName), 
            "Confirm box should display special characters correctly");
        
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
        homePage.enterName("Test Input");
        String value = homePage.getNameValue();
        
        softAssert.assertEquals(value, "Test Input", 
            TestMessages.NAME_INPUT_ACCEPTS_TEXT);
        
        softAssert.assertAll();
    }

    @Test(priority = 20, groups = {"functional", "regression"}, description = "Verify name input can be cleared")
    public void testNameInputCanBeCleared() {
        homePage.enterName("Clear Me");
        softAssert.assertEquals(homePage.getNameValue(), "Clear Me", 
            "Name should be entered");
        
        homePage.clearName();
        String clearedValue = homePage.getNameValue();
        softAssert.assertTrue(clearedValue.isEmpty(), 
            "Name input should be empty after clearing");
        
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
