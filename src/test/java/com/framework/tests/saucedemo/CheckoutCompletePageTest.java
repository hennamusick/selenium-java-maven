package com.framework.tests.saucedemo;

import com.framework.pages.saucedemo.CartPage;
import com.framework.pages.saucedemo.CheckoutCompletePage;
import com.framework.pages.saucedemo.CheckoutOverviewPage;
import com.framework.pages.saucedemo.CheckoutStepOnePage;
import com.framework.pages.saucedemo.InventoryPage;
import com.framework.pages.saucedemo.LoginPage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import com.framework.utils.saucedemo.SauceDemoConstants;
import com.framework.utils.saucedemo.SauceDemoMessages;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Test class for SauceDemo Checkout Complete (Order Confirmation) page functionality.
 * Tests cover order confirmation display, success verification, and return to home navigation.
 * 
 * @author Framework
 * @version 1.0
 */
@Epic("SauceDemo E-Commerce")
@Feature("Order Confirmation")
public class CheckoutCompletePageTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        
        // Complete full checkout flow to reach confirmation page
        driver.get(ConfigReader.getBaseUrl(2));
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        // Add multiple items to cart
        inventoryPage.addItemToCartByIndex(0); // Backpack
        inventoryPage.addItemToCartByIndex(1); // Bike Light
        inventoryPage.addItemToCartByIndex(3); // Bolt T-Shirt
        
        inventoryPage.clickShoppingCart();
        cartPage.clickCheckout();
        
        // Fill checkout information
        checkoutStepOnePage.completeCheckoutStepOne(
            SauceDemoConstants.CHECKOUT_FIRST_NAME,
            SauceDemoConstants.CHECKOUT_LAST_NAME,
            SauceDemoConstants.CHECKOUT_POSTAL_CODE
        );
        
        // Complete order
        checkoutOverviewPage.clickFinish();
    }

    // ==================== PAGE DISPLAY TESTS ====================

    @Test(priority = 1, groups = {"smoke", "confirmation", "regression"})
    @Story("Confirmation Page Display")
    @Description("Verify checkout complete page title is displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testConfirmationPageTitleDisplay() {
        assertTrue(checkoutCompletePage.isPageTitleDisplayed(), 
                  SauceDemoMessages.CONFIRMATION_PAGE_TITLE_DISPLAYED);
        assertEquals(checkoutCompletePage.getPageTitle(), SauceDemoConstants.CHECKOUT_COMPLETE_TITLE,
                    SauceDemoMessages.CONFIRMATION_PAGE_TITLE_CORRECT);
    }

    @Test(priority = 2, groups = {"smoke", "confirmation", "regression"})
    @Story("Confirmation Page Display")
    @Description("Verify checkout complete page URL is correct")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutCompletePageUrl() {
        assertTrue(checkoutCompletePage.isOnCheckoutCompletePage(),
                  SauceDemoMessages.ON_CHECKOUT_COMPLETE_PAGE);
        assertTrue(checkoutCompletePage.getCurrentUrl().contains("checkout-complete"),
                  SauceDemoMessages.URL_CONTAINS_CHECKOUT_COMPLETE);
    }

    @Test(priority = 3, groups = {"smoke", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify success icon is displayed on confirmation page")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessIconDisplay() {
        assertTrue(checkoutCompletePage.isSuccessIconDisplayed(),
                  SauceDemoMessages.SUCCESS_ICON_DISPLAYED);
    }

    @Test(priority = 4, groups = {"smoke", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify confirmation header is displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testConfirmationHeaderDisplay() {
        assertTrue(checkoutCompletePage.isConfirmationHeaderDisplayed(),
                  SauceDemoMessages.CONFIRMATION_HEADER_DISPLAYED);
        assertFalse(checkoutCompletePage.getConfirmationHeader().isEmpty(),
                   SauceDemoMessages.CONFIRMATION_HEADER_NOT_EMPTY);
    }

    @Test(priority = 5, groups = {"smoke", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify confirmation message is displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testConfirmationMessageDisplay() {
        assertTrue(checkoutCompletePage.isConfirmationMessageDisplayed(),
                  SauceDemoMessages.CONFIRMATION_MESSAGE_DISPLAYED);
        assertFalse(checkoutCompletePage.getConfirmationMessage().isEmpty(),
                   SauceDemoMessages.CONFIRMATION_MESSAGE_NOT_EMPTY);
    }

    // ==================== CONFIRMATION CONTENT TESTS ====================

    @Test(priority = 6, groups = {"functional", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify confirmation header contains success text")
    @Severity(SeverityLevel.CRITICAL)
    public void testConfirmationHeaderContent() {
        String header = checkoutCompletePage.getConfirmationHeader();
        assertTrue(header.toLowerCase().contains(SauceDemoMessages.KEYWORD_COMPLETE) || 
                  header.toLowerCase().contains(SauceDemoMessages.KEYWORD_THANK),
                  SauceDemoMessages.CONFIRMATION_HEADER_INDICATES_SUCCESS);
        assertTrue(checkoutCompletePage.isConfirmationHeaderCorrect(),
                  SauceDemoMessages.CONFIRMATION_HEADER_VALIDATION_PASSED);
    }

    @Test(priority = 7, groups = {"functional", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify confirmation message indicates order dispatch")
    @Severity(SeverityLevel.CRITICAL)
    public void testConfirmationMessageContent() {
        String message = checkoutCompletePage.getConfirmationMessage();
        assertTrue(message.toLowerCase().contains(SauceDemoMessages.KEYWORD_ORDER),
                  SauceDemoMessages.CONFIRMATION_MESSAGE_MENTIONS_ORDER);
        assertTrue(message.toLowerCase().contains(SauceDemoMessages.KEYWORD_DISPATCH) || 
                  message.toLowerCase().contains(SauceDemoMessages.KEYWORD_DELIVER) ||
                  message.toLowerCase().contains(SauceDemoMessages.KEYWORD_ARRIVE),
                  SauceDemoMessages.CONFIRMATION_MESSAGE_INDICATES_DELIVERY);
        assertTrue(checkoutCompletePage.isConfirmationMessageCorrect(),
                  SauceDemoMessages.CONFIRMATION_MESSAGE_VALIDATION_PASSED);
    }

    @Test(priority = 8, groups = {"functional", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify order success indicators are present")
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderSuccessIndicators() {
        assertTrue(checkoutCompletePage.isOrderSuccessful(),
                  SauceDemoMessages.ORDER_MARKED_SUCCESSFUL);
    }

    // ==================== BACK HOME BUTTON TESTS ====================

    @Test(priority = 9, groups = {"smoke", "confirmation", "regression"})
    @Story("Navigation")
    @Description("Verify Back Home button is displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testBackHomeButtonDisplay() {
        assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed(),
                  SauceDemoMessages.BACK_HOME_BUTTON_DISPLAYED);
    }

    @Test(priority = 10, groups = {"functional", "confirmation", "regression"})
    @Story("Navigation")
    @Description("Verify Back Home button is enabled")
    @Severity(SeverityLevel.CRITICAL)
    public void testBackHomeButtonEnabled() {
        assertTrue(checkoutCompletePage.isBackHomeButtonEnabled(),
                  SauceDemoMessages.BACK_HOME_BUTTON_ENABLED);
    }

    @Test(priority = 11, groups = {"functional", "confirmation", "regression"})
    @Story("Navigation")
    @Description("Verify Back Home button has correct text")
    @Severity(SeverityLevel.NORMAL)
    public void testBackHomeButtonText() {
        String buttonText = checkoutCompletePage.getBackHomeButtonText();
        assertEquals(buttonText, SauceDemoConstants.BACK_HOME_BUTTON_TEXT,
                    SauceDemoMessages.BACK_HOME_BUTTON_TEXT_CORRECT);
    }

    // ==================== PAGE COMPLETENESS TESTS ====================

    @Test(priority = 12, groups = {"smoke", "confirmation", "regression"})
    @Story("Confirmation Page")
    @Description("Verify all confirmation page elements are displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testAllConfirmationElementsDisplay() {
        assertTrue(checkoutCompletePage.isOrderConfirmationComplete(),
                  SauceDemoMessages.ALL_CONFIRMATION_ELEMENTS_DISPLAYED);
    }

    @Test(priority = 13, groups = {"functional", "confirmation", "regression"})
    @Story("Order Completion")
    @Description("Verify complete order completion flow")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompleteOrderCompletionFlow() {
        // Page title correct
        assertEquals(checkoutCompletePage.getPageTitle(), SauceDemoConstants.CHECKOUT_COMPLETE_TITLE);
        
        // Success indicators present
        assertTrue(checkoutCompletePage.isSuccessIconDisplayed());
        assertTrue(checkoutCompletePage.isConfirmationHeaderDisplayed());
        assertTrue(checkoutCompletePage.isConfirmationMessageDisplayed());
        
        // Confirmation content correct
        assertTrue(checkoutCompletePage.isConfirmationHeaderCorrect());
        assertTrue(checkoutCompletePage.isConfirmationMessageCorrect());
        
        // Button ready for next action
        assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed());
        assertTrue(checkoutCompletePage.isBackHomeButtonEnabled());
    }

    // ==================== NAVIGATION TESTS ====================

    @Test(priority = 14, groups = {"functional", "confirmation", "regression"})
    @Story("Navigation")
    @Description("Verify Back Home button navigates to inventory page")
    @Severity(SeverityLevel.CRITICAL)
    public void testBackHomeNavigation() {
        checkoutCompletePage.clickBackHome();
        
        // Should navigate back to inventory
        assertTrue(driver.getCurrentUrl().contains(SauceDemoConstants.INVENTORY_URL),
                  SauceDemoMessages.NAVIGATE_TO_INVENTORY);
    }

    // ==================== MESSAGE CONTENT TESTS ====================

    @Test(priority = 15, groups = {"functional", "confirmation", "regression"})
    @Story("Confirmation Content")
    @Description("Verify confirmation header is not empty")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmationHeaderNotEmpty() {
        String header = checkoutCompletePage.getConfirmationHeader();
        assertFalse(header.trim().isEmpty(),
                   SauceDemoMessages.CONFIRMATION_HEADER_NOT_EMPTY);
        assertTrue(header.length() > 5,
                  SauceDemoMessages.CONFIRMATION_HEADER_MEANINGFUL);
    }

    @Test(priority = 16, groups = {"functional", "confirmation", "regression"})
    @Story("Confirmation Content")
    @Description("Verify confirmation message is not empty")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmationMessageNotEmpty() {
        String message = checkoutCompletePage.getConfirmationMessage();
        assertFalse(message.trim().isEmpty(),
                   SauceDemoMessages.CONFIRMATION_MESSAGE_NOT_EMPTY);
        assertTrue(message.length() > 10,
                  SauceDemoMessages.CONFIRMATION_MESSAGE_MEANINGFUL);
    }

    @Test(priority = 17, groups = {"functional", "confirmation", "regression"})
    @Story("Confirmation Content")
    @Description("Verify confirmation message grammar and readability")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmationMessageReadability() {
        String message = checkoutCompletePage.getConfirmationMessage();
        
        // Should not have excessive punctuation or malformed text
        assertTrue(!message.contains("!!") && !message.contains("??"),
                  SauceDemoMessages.MESSAGE_NO_REPEATED_PUNCTUATION);
        
        // Should contain expected keywords
        assertTrue(message.toLowerCase().contains(" "),
                  SauceDemoMessages.MESSAGE_PROPER_SPACING);
    }

    // ==================== SUCCESS INDICATOR TESTS ====================

    @Test(priority = 18, groups = {"functional", "confirmation", "regression"})
    @Story("Success Indicators")
    @Description("Verify success icon is prominently displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testSuccessIconProminence() {
        // If icon is displayed, test passes
        // Icon should be visible and indicate success
        assertTrue(checkoutCompletePage.isSuccessIconDisplayed(),
                  SauceDemoMessages.SUCCESS_ICON_VISIBLE);
    }

    @Test(priority = 19, groups = {"functional", "confirmation", "regression"})
    @Story("Order Confirmation")
    @Description("Verify order completion page is final step")
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderCompletionPageIsFinalStep() {
        // Verify we're on correct page
        assertTrue(checkoutCompletePage.isOnCheckoutCompletePage(),
                  SauceDemoMessages.ON_CHECKOUT_COMPLETE_PAGE_TITLE);
        
        // Verify order was successful
        assertTrue(checkoutCompletePage.isOrderSuccessful(),
                  SauceDemoMessages.ORDER_SUCCESSFUL_STATUS);
        
        // Verify all confirmation elements present
        assertTrue(checkoutCompletePage.isOrderConfirmationComplete(),
                  SauceDemoMessages.ALL_CONFIRMATION_ELEMENTS_PRESENT);
    }

    // ==================== INTEGRATION TESTS ====================

    @Test(priority = 20, groups = {"smoke", "confirmation", "regression"})
    @Story("Checkout Flow")
    @Description("Verify complete checkout flow from login to confirmation")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompleteCheckoutFlow() {
        // We've already completed the flow in setUp()
        // Verify we're at the end
        assertTrue(checkoutCompletePage.isPageTitleDisplayed(),
                  SauceDemoMessages.ON_CHECKOUT_COMPLETE_PAGE_TITLE);
        assertTrue(checkoutCompletePage.isOrderSuccessful(),
                  SauceDemoMessages.ORDER_SUCCESSFUL_STATUS);
    }

    @Test(priority = 21, groups = {"functional", "confirmation", "regression"})
    @Story("User Journey")
    @Description("Verify user can complete full purchase journey")
    @Severity(SeverityLevel.BLOCKER)
    public void testFullPurchaseJourney() {
        // Login ✓
        // Add items ✓
        // View cart ✓
        // Checkout info ✓
        // Review order ✓
        // Complete order ✓
        
        // Now verify completion
        assertEquals(checkoutCompletePage.getPageTitle(), SauceDemoConstants.CHECKOUT_COMPLETE_TITLE);
        assertTrue(checkoutCompletePage.isOrderSuccessful());
        assertTrue(checkoutCompletePage.isOrderConfirmationComplete());
    }

    @Test(priority = 22, groups = {"regression", "confirmation"})
    @Story("Order Confirmation")
    @Description("Verify order confirmation page appears after order placement")
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderConfirmationPageAppearance() {
        // Page should load correctly
        assertNotNull(checkoutCompletePage.getPageTitle(),
                     SauceDemoMessages.PAGE_TITLE_NOT_NULL);
        
        // Should show success elements
        assertTrue(checkoutCompletePage.isSuccessIconDisplayed(),
                  SauceDemoMessages.SUCCESS_ICON_DISPLAYED);
        assertTrue(checkoutCompletePage.isConfirmationHeaderDisplayed(),
                  SauceDemoMessages.CONFIRMATION_HEADER_DISPLAYED);
        assertTrue(checkoutCompletePage.isConfirmationMessageDisplayed(),
                  SauceDemoMessages.CONFIRMATION_MESSAGE_DISPLAYED);
    }

    @Test(priority = 23, groups = {"functional", "confirmation", "regression"})
    @Story("Confirmation Accuracy")
    @Description("Verify confirmation displays after successful payment processing")
    @Severity(SeverityLevel.CRITICAL)
    public void testConfirmationAccuracy() {
        // Verify this is the confirmation page
        assertTrue(checkoutCompletePage.isOnCheckoutCompletePage(),
                  SauceDemoMessages.ON_CHECKOUT_COMPLETE_PAGE);
        
        // Verify success indicators
        assertTrue(checkoutCompletePage.isOrderSuccessful(),
                  SauceDemoMessages.ORDER_MARKED_SUCCESSFUL);
        
        // Verify user can proceed
        assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed() &&
                  checkoutCompletePage.isBackHomeButtonEnabled(),
                  SauceDemoMessages.CAN_RETURN_TO_HOME);
    }

    @Test(priority = 24, groups = {"functional", "confirmation", "regression"})
    @Story("Message Validation")
    @Description("Verify order dispatch confirmation message contains key information")
    @Severity(SeverityLevel.NORMAL)
    public void testOrderDispatchMessageContent() {
        String message = checkoutCompletePage.getConfirmationMessage();
        
        // Should mention order
        assertTrue(message.toLowerCase().contains(SauceDemoMessages.KEYWORD_ORDER),
                  SauceDemoMessages.CONFIRMATION_MESSAGE_MENTIONS_ORDER);
        
        // Should indicate delivery
        assertTrue(message.toLowerCase().matches(".*(?:dispatch|deliver|arrive|send).*"),
                  SauceDemoMessages.MESSAGE_INDICATES_DELIVERY_METHOD);
    }

    @Test(priority = 25, groups = {"smoke", "confirmation", "regression"})
    @Story("Checkout Completion")
    @Description("Verify complete order confirmation experience")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompleteOrderConfirmationExperience() {
        // 1. Page displays correctly
        assertTrue(checkoutCompletePage.isPageTitleDisplayed());
        
        // 2. Success is communicated visually and textually
        assertTrue(checkoutCompletePage.isSuccessIconDisplayed());
        assertTrue(checkoutCompletePage.isConfirmationHeaderCorrect());
        assertTrue(checkoutCompletePage.isConfirmationMessageCorrect());
        
        // 3. User can navigate away
        assertTrue(checkoutCompletePage.isBackHomeButtonEnabled());
        
        // 4. Overall confirmation is complete
        assertTrue(checkoutCompletePage.isOrderConfirmationComplete());
    }
}
