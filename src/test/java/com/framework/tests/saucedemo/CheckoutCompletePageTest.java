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
                  "Confirmation page title should be displayed");
        assertEquals(checkoutCompletePage.getPageTitle(), SauceDemoConstants.CHECKOUT_COMPLETE_TITLE,
                    "Page title should be 'Checkout: Complete!'");
    }

    @Test(priority = 2, groups = {"smoke", "confirmation", "regression"})
    @Story("Confirmation Page Display")
    @Description("Verify checkout complete page URL is correct")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutCompletePageUrl() {
        assertTrue(checkoutCompletePage.isOnCheckoutCompletePage(),
                  "Should be on checkout complete page");
        assertTrue(checkoutCompletePage.getCurrentUrl().contains("checkout-complete"),
                  "URL should contain 'checkout-complete'");
    }

    @Test(priority = 3, groups = {"smoke", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify success icon is displayed on confirmation page")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessIconDisplay() {
        assertTrue(checkoutCompletePage.isSuccessIconDisplayed(),
                  "Success icon should be displayed");
    }

    @Test(priority = 4, groups = {"smoke", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify confirmation header is displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testConfirmationHeaderDisplay() {
        assertTrue(checkoutCompletePage.isConfirmationHeaderDisplayed(),
                  "Confirmation header should be displayed");
        assertFalse(checkoutCompletePage.getConfirmationHeader().isEmpty(),
                   "Confirmation header should contain text");
    }

    @Test(priority = 5, groups = {"smoke", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify confirmation message is displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testConfirmationMessageDisplay() {
        assertTrue(checkoutCompletePage.isConfirmationMessageDisplayed(),
                  "Confirmation message should be displayed");
        assertFalse(checkoutCompletePage.getConfirmationMessage().isEmpty(),
                   "Confirmation message should contain text");
    }

    // ==================== CONFIRMATION CONTENT TESTS ====================

    @Test(priority = 6, groups = {"functional", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify confirmation header contains success text")
    @Severity(SeverityLevel.CRITICAL)
    public void testConfirmationHeaderContent() {
        String header = checkoutCompletePage.getConfirmationHeader();
        assertTrue(header.toLowerCase().contains("complete") || 
                  header.toLowerCase().contains("thank"),
                  "Header should indicate successful completion");
        assertTrue(checkoutCompletePage.isConfirmationHeaderCorrect(),
                  "Confirmation header validation should pass");
    }

    @Test(priority = 7, groups = {"functional", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify confirmation message indicates order dispatch")
    @Severity(SeverityLevel.CRITICAL)
    public void testConfirmationMessageContent() {
        String message = checkoutCompletePage.getConfirmationMessage();
        assertTrue(message.toLowerCase().contains("order"),
                  "Message should mention 'order'");
        assertTrue(message.toLowerCase().contains("dispatch") || 
                  message.toLowerCase().contains("deliver") ||
                  message.toLowerCase().contains("arrive"),
                  "Message should indicate delivery/dispatch");
        assertTrue(checkoutCompletePage.isConfirmationMessageCorrect(),
                  "Confirmation message validation should pass");
    }

    @Test(priority = 8, groups = {"functional", "confirmation", "regression"})
    @Story("Success Confirmation")
    @Description("Verify order success indicators are present")
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderSuccessIndicators() {
        assertTrue(checkoutCompletePage.isOrderSuccessful(),
                  "Order should be marked as successful");
    }

    // ==================== BACK HOME BUTTON TESTS ====================

    @Test(priority = 9, groups = {"smoke", "confirmation", "regression"})
    @Story("Navigation")
    @Description("Verify Back Home button is displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testBackHomeButtonDisplay() {
        assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed(),
                  "Back Home button should be displayed");
    }

    @Test(priority = 10, groups = {"functional", "confirmation", "regression"})
    @Story("Navigation")
    @Description("Verify Back Home button is enabled")
    @Severity(SeverityLevel.CRITICAL)
    public void testBackHomeButtonEnabled() {
        assertTrue(checkoutCompletePage.isBackHomeButtonEnabled(),
                  "Back Home button should be enabled");
    }

    @Test(priority = 11, groups = {"functional", "confirmation", "regression"})
    @Story("Navigation")
    @Description("Verify Back Home button has correct text")
    @Severity(SeverityLevel.NORMAL)
    public void testBackHomeButtonText() {
        String buttonText = checkoutCompletePage.getBackHomeButtonText();
        assertEquals(buttonText, SauceDemoConstants.BACK_HOME_BUTTON_TEXT,
                    "Back Home button text should be 'Back Home'");
    }

    // ==================== PAGE COMPLETENESS TESTS ====================

    @Test(priority = 12, groups = {"smoke", "confirmation", "regression"})
    @Story("Confirmation Page")
    @Description("Verify all confirmation page elements are displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testAllConfirmationElementsDisplay() {
        assertTrue(checkoutCompletePage.isOrderConfirmationComplete(),
                  "All order confirmation elements should be displayed");
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
                  "Should navigate back to inventory page");
    }

    // ==================== MESSAGE CONTENT TESTS ====================

    @Test(priority = 15, groups = {"functional", "confirmation", "regression"})
    @Story("Confirmation Content")
    @Description("Verify confirmation header is not empty")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmationHeaderNotEmpty() {
        String header = checkoutCompletePage.getConfirmationHeader();
        assertFalse(header.trim().isEmpty(),
                   "Confirmation header should not be empty");
        assertTrue(header.length() > 5,
                  "Confirmation header should have meaningful content");
    }

    @Test(priority = 16, groups = {"functional", "confirmation", "regression"})
    @Story("Confirmation Content")
    @Description("Verify confirmation message is not empty")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmationMessageNotEmpty() {
        String message = checkoutCompletePage.getConfirmationMessage();
        assertFalse(message.trim().isEmpty(),
                   "Confirmation message should not be empty");
        assertTrue(message.length() > 10,
                  "Confirmation message should have meaningful content");
    }

    @Test(priority = 17, groups = {"functional", "confirmation", "regression"})
    @Story("Confirmation Content")
    @Description("Verify confirmation message grammar and readability")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmationMessageReadability() {
        String message = checkoutCompletePage.getConfirmationMessage();
        
        // Should not have excessive punctuation or malformed text
        assertTrue(!message.contains("!!") && !message.contains("??"),
                  "Message should not have repeated punctuation");
        
        // Should contain expected keywords
        assertTrue(message.toLowerCase().contains(" "),
                  "Message should contain proper spacing");
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
                  "Success icon should be clearly visible");
    }

    @Test(priority = 19, groups = {"functional", "confirmation", "regression"})
    @Story("Order Confirmation")
    @Description("Verify order completion page is final step")
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderCompletionPageIsFinalStep() {
        // Verify we're on correct page
        assertTrue(checkoutCompletePage.isOnCheckoutCompletePage());
        
        // Verify order was successful
        assertTrue(checkoutCompletePage.isOrderSuccessful());
        
        // Verify all confirmation elements present
        assertTrue(checkoutCompletePage.isOrderConfirmationComplete());
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
                  "Should be on checkout complete page");
        assertTrue(checkoutCompletePage.isOrderSuccessful(),
                  "Order should be successful");
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
                     "Page title should not be null");
        
        // Should show success elements
        assertTrue(checkoutCompletePage.isSuccessIconDisplayed());
        assertTrue(checkoutCompletePage.isConfirmationHeaderDisplayed());
        assertTrue(checkoutCompletePage.isConfirmationMessageDisplayed());
    }

    @Test(priority = 23, groups = {"functional", "confirmation", "regression"})
    @Story("Confirmation Accuracy")
    @Description("Verify confirmation displays after successful payment processing")
    @Severity(SeverityLevel.CRITICAL)
    public void testConfirmationAccuracy() {
        // Verify this is the confirmation page
        assertTrue(checkoutCompletePage.isOnCheckoutCompletePage(),
                  "Should be on checkout-complete page");
        
        // Verify success indicators
        assertTrue(checkoutCompletePage.isOrderSuccessful(),
                  "Order should be marked successful");
        
        // Verify user can proceed
        assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed() &&
                  checkoutCompletePage.isBackHomeButtonEnabled(),
                  "Should be able to return to home");
    }

    @Test(priority = 24, groups = {"functional", "confirmation", "regression"})
    @Story("Message Validation")
    @Description("Verify order dispatch confirmation message contains key information")
    @Severity(SeverityLevel.NORMAL)
    public void testOrderDispatchMessageContent() {
        String message = checkoutCompletePage.getConfirmationMessage();
        
        // Should mention order
        assertTrue(message.toLowerCase().contains("order"),
                  "Should mention 'order'");
        
        // Should indicate delivery
        assertTrue(message.toLowerCase().matches(".*(?:dispatch|deliver|arrive|send).*"),
                  "Should indicate how order will be delivered");
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
