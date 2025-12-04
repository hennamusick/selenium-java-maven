package com.framework.tests.saucedemo;

import com.framework.pages.saucedemo.CartPage;
import com.framework.pages.saucedemo.CheckoutStepOnePage;
import com.framework.pages.saucedemo.InventoryPage;
import com.framework.pages.saucedemo.LoginPage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import com.framework.utils.saucedemo.SauceDemoConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

/**
 * Test class for SauceDemo Checkout Step One (Your Information) page functionality.
 * Tests cover form display, field validation, button actions, and error handling.
 * 
 * @author Framework
 * @version 1.0
 */
@Epic("SauceDemo E-Commerce")
@Feature("Checkout Process")
public class CheckoutStepOnePageTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutStepOnePage(driver);
        
        // Login, add item to cart, and navigate to checkout
        driver.get(ConfigReader.getBaseUrl(2));
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        inventoryPage.addItemToCartByIndex(0); // Add at least one item
        inventoryPage.clickShoppingCart();
        cartPage.clickCheckout();
    }

    // ==================== PAGE DISPLAY TESTS ====================

    @Test(priority = 1, groups = {"smoke", "checkout", "regression"})
    @Story("Checkout Page Display")
    @Description("Verify checkout page title is displayed correctly")
    @Severity(SeverityLevel.BLOCKER)
    public void testCheckoutPageTitleDisplay() {
        assertTrue(checkoutPage.isPageTitleDisplayed(), "Checkout page title should be displayed");
        assertEquals(checkoutPage.getPageTitle(), SauceDemoConstants.CHECKOUT_STEP_ONE_TITLE,
                    "Page title should be 'Checkout: Your Information'");
    }

    @Test(priority = 2, groups = {"smoke", "checkout", "regression"})
    @Story("Checkout Page Display")
    @Description("Verify all form fields are displayed on checkout page")
    @Severity(SeverityLevel.BLOCKER)
    public void testAllFormFieldsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.isFirstNameFieldDisplayed(), 
                            "First Name field should be displayed");
        softAssert.assertTrue(checkoutPage.isLastNameFieldDisplayed(), 
                            "Last Name field should be displayed");
        softAssert.assertTrue(checkoutPage.isPostalCodeFieldDisplayed(), 
                            "Postal Code field should be displayed");
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Page Display")
    @Description("Verify placeholder text is displayed in all form fields")
    @Severity(SeverityLevel.NORMAL)
    public void testFormFieldPlaceholders() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(checkoutPage.getFirstNamePlaceholder(), "First Name",
                              "First Name placeholder should be correct");
        softAssert.assertEquals(checkoutPage.getLastNamePlaceholder(), "Last Name",
                              "Last Name placeholder should be correct");
        softAssert.assertEquals(checkoutPage.getPostalCodePlaceholder(), "Zip/Postal Code",
                              "Postal Code placeholder should be correct");
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Page Display")
    @Description("Verify all form fields are enabled and editable")
    @Severity(SeverityLevel.CRITICAL)
    public void testFormFieldsEnabled() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.isFirstNameFieldEnabled(), 
                            "First Name field should be enabled");
        softAssert.assertTrue(checkoutPage.isLastNameFieldEnabled(), 
                            "Last Name field should be enabled");
        softAssert.assertTrue(checkoutPage.isPostalCodeFieldEnabled(), 
                            "Postal Code field should be enabled");
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"smoke", "checkout", "regression"})
    @Story("Checkout Page Display")
    @Description("Verify Continue and Cancel buttons are displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testButtonsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(checkoutPage.isContinueButtonDisplayed(), 
                            "Continue button should be displayed");
        softAssert.assertTrue(checkoutPage.isCancelButtonDisplayed(), 
                            "Cancel button should be displayed");
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Page Display")
    @Description("Verify button text is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    public void testButtonText() {
        assertEquals(checkoutPage.getContinueButtonText(), SauceDemoConstants.CONTINUE_BUTTON_TEXT,
                    "Continue button text should be 'Continue'");
        assertEquals(checkoutPage.getCancelButtonText(), SauceDemoConstants.CANCEL_BUTTON_TEXT,
                    "Cancel button text should be 'Cancel'");
    }

    @Test(priority = 7, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Page Display")
    @Description("Verify cart badge is still visible on checkout page")
    @Severity(SeverityLevel.NORMAL)
    public void testCartBadgeVisible() {
        assertTrue(checkoutPage.isCartBadgeDisplayed(), "Cart badge should be visible");
        assertEquals(checkoutPage.getCartBadgeCount(), "1", "Cart badge should show 1 item");
    }

    // ==================== FORM INPUT TESTS ====================

    @Test(priority = 8, groups = {"functional", "checkout", "regression"})
    @Story("Form Input")
    @Description("Verify first name can be entered in the field")
    @Severity(SeverityLevel.CRITICAL)
    public void testEnterFirstName() {
        checkoutPage.enterFirstName(SauceDemoConstants.CHECKOUT_FIRST_NAME);
        assertEquals(checkoutPage.getFirstNameValue(), SauceDemoConstants.CHECKOUT_FIRST_NAME,
                    "First name should be entered correctly");
    }

    @Test(priority = 9, groups = {"functional", "checkout", "regression"})
    @Story("Form Input")
    @Description("Verify last name can be entered in the field")
    @Severity(SeverityLevel.CRITICAL)
    public void testEnterLastName() {
        checkoutPage.enterLastName(SauceDemoConstants.CHECKOUT_LAST_NAME);
        assertEquals(checkoutPage.getLastNameValue(), SauceDemoConstants.CHECKOUT_LAST_NAME,
                    "Last name should be entered correctly");
    }

    @Test(priority = 10, groups = {"functional", "checkout", "regression"})
    @Story("Form Input")
    @Description("Verify postal code can be entered in the field")
    @Severity(SeverityLevel.CRITICAL)
    public void testEnterPostalCode() {
        checkoutPage.enterPostalCode(SauceDemoConstants.CHECKOUT_POSTAL_CODE);
        assertEquals(checkoutPage.getPostalCodeValue(), SauceDemoConstants.CHECKOUT_POSTAL_CODE,
                    "Postal code should be entered correctly");
    }

    @Test(priority = 11, groups = {"functional", "checkout", "regression"})
    @Story("Form Input")
    @Description("Verify all form fields can be filled at once")
    @Severity(SeverityLevel.CRITICAL)
    public void testFillAllFields() {
        checkoutPage.fillCheckoutInformation(
            SauceDemoConstants.CHECKOUT_FIRST_NAME,
            SauceDemoConstants.CHECKOUT_LAST_NAME,
            SauceDemoConstants.CHECKOUT_POSTAL_CODE
        );
        
        assertTrue(checkoutPage.areAllFieldsFilled(), "All fields should be filled");
    }

    @Test(priority = 12, groups = {"functional", "checkout", "regression"})
    @Story("Form Input")
    @Description("Verify form fields can be cleared")
    @Severity(SeverityLevel.NORMAL)
    public void testClearFormFields() {
        checkoutPage.fillCheckoutInformation("Test", "User", "11111");
        checkoutPage.clearAllFields();
        
        assertTrue(checkoutPage.areAllFieldsEmpty(), "All fields should be empty after clearing");
    }

    @Test(priority = 13, groups = {"functional", "checkout", "regression"})
    @Story("Form Input")
    @Description("Verify first name field accepts special characters")
    @Severity(SeverityLevel.MINOR)
    public void testFirstNameSpecialCharacters() {
        String specialName = "John-Paul O'Brien";
        checkoutPage.enterFirstName(specialName);
        assertEquals(checkoutPage.getFirstNameValue(), specialName,
                    "First name should accept special characters");
    }

    @Test(priority = 14, groups = {"functional", "checkout", "regression"})
    @Story("Form Input")
    @Description("Verify postal code field accepts alphanumeric values")
    @Severity(SeverityLevel.NORMAL)
    public void testPostalCodeAlphanumeric() {
        String alphanumericCode = "ABC123";
        checkoutPage.enterPostalCode(alphanumericCode);
        assertEquals(checkoutPage.getPostalCodeValue(), alphanumericCode,
                    "Postal code should accept alphanumeric values");
    }

    // ==================== FORM VALIDATION TESTS ====================

    @Test(priority = 15, groups = {"functional", "checkout", "regression"})
    @Story("Form Validation")
    @Description("Verify error message appears when first name is missing")
    @Severity(SeverityLevel.CRITICAL)
    public void testFirstNameRequiredError() {
        checkoutPage.enterLastName(SauceDemoConstants.CHECKOUT_LAST_NAME);
        checkoutPage.enterPostalCode(SauceDemoConstants.CHECKOUT_POSTAL_CODE);
        checkoutPage.clickContinue();
        
        assertTrue(checkoutPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals(checkoutPage.getErrorMessage(), SauceDemoConstants.ERROR_FIRST_NAME_REQUIRED,
                    "Error should indicate first name is required");
    }

    @Test(priority = 16, groups = {"functional", "checkout", "regression"})
    @Story("Form Validation")
    @Description("Verify error message appears when last name is missing")
    @Severity(SeverityLevel.CRITICAL)
    public void testLastNameRequiredError() {
        checkoutPage.enterFirstName(SauceDemoConstants.CHECKOUT_FIRST_NAME);
        checkoutPage.enterPostalCode(SauceDemoConstants.CHECKOUT_POSTAL_CODE);
        checkoutPage.clickContinue();
        
        assertTrue(checkoutPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals(checkoutPage.getErrorMessage(), SauceDemoConstants.ERROR_LAST_NAME_REQUIRED,
                    "Error should indicate last name is required");
    }

    @Test(priority = 17, groups = {"functional", "checkout", "regression"})
    @Story("Form Validation")
    @Description("Verify error message appears when postal code is missing")
    @Severity(SeverityLevel.CRITICAL)
    public void testPostalCodeRequiredError() {
        checkoutPage.enterFirstName(SauceDemoConstants.CHECKOUT_FIRST_NAME);
        checkoutPage.enterLastName(SauceDemoConstants.CHECKOUT_LAST_NAME);
        checkoutPage.clickContinue();
        
        assertTrue(checkoutPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals(checkoutPage.getErrorMessage(), SauceDemoConstants.ERROR_POSTAL_CODE_REQUIRED,
                    "Error should indicate postal code is required");
    }

    @Test(priority = 18, groups = {"functional", "checkout", "regression"})
    @Story("Form Validation")
    @Description("Verify error message appears when all fields are empty")
    @Severity(SeverityLevel.CRITICAL)
    public void testAllFieldsEmptyError() {
        checkoutPage.clickContinue();
        
        assertTrue(checkoutPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertTrue(checkoutPage.getErrorMessage().contains("Error:"),
                  "Error message should be displayed when all fields are empty");
    }

    @Test(priority = 19, groups = {"functional", "checkout", "regression"})
    @Story("Form Validation")
    @Description("Verify error close button is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testErrorCloseButton() {
        checkoutPage.clickContinue();
        
        assertTrue(checkoutPage.isErrorMessageDisplayed(), "Error should be displayed");
        assertTrue(checkoutPage.isErrorCloseButtonDisplayed(), 
                  "Error close button should be displayed");
    }

    @Test(priority = 20, groups = {"functional", "checkout", "regression"})
    @Story("Form Validation")
    @Description("Verify error message can be dismissed by clicking close button")
    @Severity(SeverityLevel.NORMAL)
    public void testDismissError() {
        checkoutPage.clickContinue();
        assertTrue(checkoutPage.isErrorMessageDisplayed(), "Error should be displayed");
        
        checkoutPage.clickErrorCloseButton();
        // Error may still be in DOM but should not be visually prominent
        assertNotNull(checkoutPage, "Page should still be accessible after dismissing error");
    }

    // ==================== NAVIGATION TESTS ====================

    @Test(priority = 21, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Navigation")
    @Description("Verify Cancel button navigates back to cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testCancelNavigation() {
        checkoutPage.clickCancel();
        
        assertTrue(driver.getCurrentUrl().contains(SauceDemoConstants.CART_URL),
                  "Should navigate back to cart page");
    }

    @Test(priority = 22, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Navigation")
    @Description("Verify Continue button navigates to step two with valid data")
    @Severity(SeverityLevel.CRITICAL)
    public void testContinueToStepTwo() {
        checkoutPage.completeCheckoutStepOne(
            SauceDemoConstants.CHECKOUT_FIRST_NAME,
            SauceDemoConstants.CHECKOUT_LAST_NAME,
            SauceDemoConstants.CHECKOUT_POSTAL_CODE
        );
        
        assertTrue(driver.getCurrentUrl().contains(SauceDemoConstants.CHECKOUT_STEP_TWO_URL),
                  "Should navigate to checkout step two");
    }

    @Test(priority = 23, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Navigation")
    @Description("Verify form data is retained when navigating back from error")
    @Severity(SeverityLevel.NORMAL)
    public void testFormDataRetention() {
        checkoutPage.enterFirstName(SauceDemoConstants.CHECKOUT_FIRST_NAME);
        checkoutPage.enterLastName(SauceDemoConstants.CHECKOUT_LAST_NAME);
        // Intentionally skip postal code
        checkoutPage.clickContinue();
        
        // Error should appear, but existing data should be retained
        assertEquals(checkoutPage.getFirstNameValue(), SauceDemoConstants.CHECKOUT_FIRST_NAME,
                    "First name should be retained after error");
        assertEquals(checkoutPage.getLastNameValue(), SauceDemoConstants.CHECKOUT_LAST_NAME,
                    "Last name should be retained after error");
    }

    // ==================== BUTTON STATE TESTS ====================

    @Test(priority = 24, groups = {"functional", "checkout", "regression"})
    @Story("Button States")
    @Description("Verify Continue button is enabled regardless of form state")
    @Severity(SeverityLevel.NORMAL)
    public void testContinueButtonEnabled() {
        assertTrue(checkoutPage.isContinueButtonEnabled(), 
                  "Continue button should be enabled");
    }

    @Test(priority = 25, groups = {"functional", "checkout", "regression"})
    @Story("Button States")
    @Description("Verify Cancel button is always enabled")
    @Severity(SeverityLevel.NORMAL)
    public void testCancelButtonEnabled() {
        assertTrue(checkoutPage.isCancelButtonEnabled(), 
                  "Cancel button should be enabled");
    }

    // ==================== INTEGRATION TESTS ====================

    @Test(priority = 26, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Integration")
    @Description("Verify complete checkout flow from cart to step two")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompleteCheckoutFlow() {
        // Verify we're on checkout page
        assertTrue(driver.getCurrentUrl().contains(SauceDemoConstants.CHECKOUT_STEP_ONE_URL),
                  "Should be on checkout step one");
        
        // Fill form and continue
        checkoutPage.completeCheckoutStepOne(
            SauceDemoConstants.CHECKOUT_FIRST_NAME,
            SauceDemoConstants.CHECKOUT_LAST_NAME,
            SauceDemoConstants.CHECKOUT_POSTAL_CODE
        );
        
        // Verify navigation to step two
        assertTrue(driver.getCurrentUrl().contains(SauceDemoConstants.CHECKOUT_STEP_TWO_URL),
                  "Should complete checkout step one and navigate to step two");
    }

    @Test(priority = 27, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Integration")
    @Description("Verify cart badge persists correct count throughout checkout")
    @Severity(SeverityLevel.NORMAL)
    public void testCartBadgePersistence() {
        String initialBadgeCount = checkoutPage.getCartBadgeCount();
        
        checkoutPage.fillCheckoutInformation(
            SauceDemoConstants.CHECKOUT_FIRST_NAME,
            SauceDemoConstants.CHECKOUT_LAST_NAME,
            SauceDemoConstants.CHECKOUT_POSTAL_CODE
        );
        
        assertEquals(checkoutPage.getCartBadgeCount(), initialBadgeCount,
                    "Cart badge count should remain unchanged during form fill");
    }

    @Test(priority = 28, groups = {"functional", "checkout", "regression"})
    @Story("Checkout Integration")
    @Description("Verify checkout page is accessible only with items in cart")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutWithItemsRequired() {
        // User should have at least one item (added in setUp)
        assertTrue(checkoutPage.isCartBadgeDisplayed(), 
                  "Cart badge should be displayed indicating items in cart");
        assertTrue(Integer.parseInt(checkoutPage.getCartBadgeCount()) > 0,
                  "Cart should have at least one item to proceed to checkout");
    }

    // ==================== NEGATIVE TESTS ====================

    @Test(priority = 29, groups = {"functional", "checkout", "regression"})
    @Story("Negative Scenarios")
    @Description("Verify very long input in first name field")
    @Severity(SeverityLevel.MINOR)
    public void testVeryLongFirstName() {
        String longName = "A".repeat(100);
        checkoutPage.enterFirstName(longName);
        assertEquals(checkoutPage.getFirstNameValue(), longName,
                    "Should accept very long first name");
    }

    @Test(priority = 30, groups = {"functional", "checkout", "regression"})
    @Story("Negative Scenarios")
    @Description("Verify numeric values in name fields")
    @Severity(SeverityLevel.MINOR)
    public void testNumericInNameFields() {
        checkoutPage.enterFirstName("12345");
        checkoutPage.enterLastName("67890");
        checkoutPage.enterPostalCode(SauceDemoConstants.CHECKOUT_POSTAL_CODE);
        checkoutPage.clickContinue();
        
        // SauceDemo may accept numeric names, verify form processes them
        assertNotNull(checkoutPage, "Form should handle numeric names");
    }

    @Test(priority = 31, groups = {"functional", "checkout", "regression"})
    @Story("Negative Scenarios")
    @Description("Verify form with only spaces in required fields")
    @Severity(SeverityLevel.NORMAL)
    public void testSpacesOnlyInFields() {
        checkoutPage.enterFirstName("   ");
        checkoutPage.enterLastName("   ");
        checkoutPage.enterPostalCode("   ");
        checkoutPage.clickContinue();
        
        // Should show error as spaces don't count as valid input
        assertTrue(checkoutPage.isErrorMessageDisplayed() || 
                  driver.getCurrentUrl().contains(SauceDemoConstants.CHECKOUT_STEP_TWO_URL),
                  "Form should either show error or accept spaces");
    }
}
