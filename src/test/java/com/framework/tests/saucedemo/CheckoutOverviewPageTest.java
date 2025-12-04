package com.framework.tests.saucedemo;

import com.framework.pages.saucedemo.CartPage;
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

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test class for SauceDemo Checkout Overview (Step Two) page functionality.
 * Tests cover order review, item display, pricing calculations, and checkout completion.
 * 
 * @author Framework
 * @version 1.0
 */
@Epic("SauceDemo E-Commerce")
@Feature("Checkout Overview")
public class CheckoutOverviewPageTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutOverviewPage checkoutOverviewPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        
        // Login, add items to cart, and navigate to checkout overview
        driver.get(ConfigReader.getBaseUrl(2));
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        // Add multiple items to cart
        inventoryPage.addItemToCartByIndex(0); // Backpack
        inventoryPage.addItemToCartByIndex(1); // Bike Light
        inventoryPage.addItemToCartByIndex(3); // Bolt T-Shirt
        
        inventoryPage.clickShoppingCart();
        cartPage.clickCheckout();
        
        // Fill checkout information and proceed to overview
        checkoutStepOnePage.completeCheckoutStepOne(
            SauceDemoConstants.CHECKOUT_FIRST_NAME,
            SauceDemoConstants.CHECKOUT_LAST_NAME,
            SauceDemoConstants.CHECKOUT_POSTAL_CODE
        );
    }

    // ==================== PAGE DISPLAY TESTS ====================

    @Test(priority = 1, groups = {"smoke", "overview", "regression"})
    @Story("Checkout Overview Display")
    @Description("Verify checkout overview page title is displayed correctly")
    @Severity(SeverityLevel.BLOCKER)
    public void testOverviewPageTitleDisplay() {
        assertTrue(checkoutOverviewPage.isPageTitleDisplayed(), 
                  SauceDemoMessages.OVERVIEW_PAGE_TITLE_DISPLAYED);
        assertEquals(checkoutOverviewPage.getPageTitle(), SauceDemoConstants.CHECKOUT_OVERVIEW_TITLE,
                    SauceDemoMessages.OVERVIEW_PAGE_TITLE_CORRECT);
    }

    @Test(priority = 2, groups = {"smoke", "overview", "regression"})
    @Story("Checkout Overview Display")
    @Description("Verify all cart items are displayed on overview page")
    @Severity(SeverityLevel.CRITICAL)
    public void testAllItemsDisplayedOnOverview() {
        assertEquals(checkoutOverviewPage.getCartItemCount(), 3, 
                    SauceDemoMessages.DISPLAY_ALL_THREE_ITEMS);
        assertTrue(checkoutOverviewPage.areAllItemsCompletelyDisplayed(),
                  SauceDemoMessages.ALL_ITEMS_HAVE_DETAILS);
    }

    @Test(priority = 3, groups = {"functional", "overview", "regression"})
    @Story("Item Information")
    @Description("Verify item names are displayed correctly on overview")
    @Severity(SeverityLevel.CRITICAL)
    public void testItemNamesDisplay() {
        List<String> itemNames = checkoutOverviewPage.getAllItemNames();
        
        assertFalse(itemNames.isEmpty(), SauceDemoMessages.ITEM_NAMES_DISPLAYED);
        assertEquals(itemNames.size(), 3, SauceDemoMessages.DISPLAY_THREE_ITEM_NAMES);
        assertTrue(itemNames.get(0).contains("Backpack") || 
                  itemNames.get(0).contains("Sauce Labs"),
                  SauceDemoMessages.FIRST_ITEM_BACKPACK);
    }

    @Test(priority = 4, groups = {"functional", "overview", "regression"})
    @Story("Item Information")
    @Description("Verify item descriptions are displayed on overview")
    @Severity(SeverityLevel.CRITICAL)
    public void testItemDescriptionsDisplay() {
        List<String> descriptions = checkoutOverviewPage.getAllItemDescriptions();
        
        assertFalse(descriptions.isEmpty(), SauceDemoMessages.ITEM_DESCRIPTIONS_DISPLAYED);
        assertEquals(descriptions.size(), 3, SauceDemoMessages.DISPLAY_THREE_DESCRIPTIONS);
        assertFalse(descriptions.get(0).isEmpty(), SauceDemoMessages.DESCRIPTION_HAS_CONTENT);
    }

    @Test(priority = 5, groups = {"functional", "overview", "regression"})
    @Story("Item Information")
    @Description("Verify item prices are displayed in correct format")
    @Severity(SeverityLevel.CRITICAL)
    public void testItemPricesDisplay() {
        List<String> prices = checkoutOverviewPage.getAllItemPrices();
        
        assertEquals(prices.size(), 3, SauceDemoMessages.DISPLAY_THREE_ITEM_PRICES);
        
        // Verify price format (e.g., $29.99)
        for (String price : prices) {
            assertTrue(price.matches("\\$\\d+\\.\\d{2}"), 
                      "Price should be in format $XX.XX");
        }
    }

    @Test(priority = 6, groups = {"functional", "overview", "regression"})
    @Story("Item Information")
    @Description("Verify item quantities are displayed correctly")
    @Severity(SeverityLevel.CRITICAL)
    public void testItemQuantitiesDisplay() {
        List<String> quantities = checkoutOverviewPage.getAllItemQuantities();
        
        assertEquals(quantities.size(), 3, SauceDemoMessages.DISPLAY_THREE_QUANTITIES);
        
        // Each item added once should have quantity of 1
        for (String qty : quantities) {
            assertEquals(qty, "1", SauceDemoMessages.EACH_ITEM_QUANTITY_ONE);
        }
    }

    // ==================== ORDER SUMMARY TESTS ====================

    @Test(priority = 7, groups = {"functional", "overview", "regression"})
    @Story("Order Summary")
    @Description("Verify order summary section is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void testSummaryInfoDisplay() {
        assertTrue(checkoutOverviewPage.isSummaryInfoDisplayed(), 
                  SauceDemoMessages.ORDER_SUMMARY_DISPLAYED);
    }

    @Test(priority = 8, groups = {"functional", "overview", "regression"})
    @Story("Order Summary")
    @Description("Verify subtotal is displayed and correctly calculated")
    @Severity(SeverityLevel.CRITICAL)
    public void testSubtotalDisplay() {
        String subtotalLabel = checkoutOverviewPage.getSubtotalLabel();
        double subtotal = checkoutOverviewPage.getSubtotal();
        
        assertTrue(subtotalLabel.contains("Subtotal"), SauceDemoMessages.DISPLAY_SUBTOTAL_LABEL);
        assertTrue(subtotalLabel.matches(".*\\$\\d+\\.\\d{2}.*"), 
                  SauceDemoMessages.SUBTOTAL_CORRECT_FORMAT);
        assertTrue(subtotal > 0, SauceDemoMessages.SUBTOTAL_GREATER_THAN_ZERO);
    }

    @Test(priority = 9, groups = {"functional", "overview", "regression"})
    @Story("Order Summary")
    @Description("Verify tax is displayed and calculated")
    @Severity(SeverityLevel.CRITICAL)
    public void testTaxDisplay() {
        String taxLabel = checkoutOverviewPage.getTaxLabel();
        double tax = checkoutOverviewPage.getTax();
        
        assertTrue(taxLabel.contains("Tax"), SauceDemoMessages.DISPLAY_TAX_LABEL);
        assertTrue(taxLabel.matches(".*\\$\\d+\\.\\d{2}.*"), 
                  SauceDemoMessages.TAX_CORRECT_FORMAT);
        assertTrue(tax > 0, SauceDemoMessages.TAX_GREATER_THAN_ZERO);
    }

    @Test(priority = 10, groups = {"functional", "overview", "regression"})
    @Story("Order Summary")
    @Description("Verify total is displayed correctly")
    @Severity(SeverityLevel.CRITICAL)
    public void testTotalDisplay() {
        String totalLabel = checkoutOverviewPage.getTotalLabel();
        double total = checkoutOverviewPage.getTotal();
        
        assertTrue(totalLabel.contains("Total"), SauceDemoMessages.DISPLAY_TOTAL_LABEL);
        assertTrue(totalLabel.matches(".*\\$\\d+\\.\\d{2}.*"), 
                  SauceDemoMessages.SUBTOTAL_CORRECT_FORMAT);
        assertTrue(total > 0, SauceDemoMessages.TOTAL_GREATER_THAN_ZERO);
    }

    @Test(priority = 11, groups = {"functional", "overview", "regression"})
    @Story("Pricing Calculation")
    @Description("Verify subtotal equals sum of item prices")
    @Severity(SeverityLevel.CRITICAL)
    public void testSubtotalCalculation() {
        assertTrue(checkoutOverviewPage.isSubtotalCorrect(),
                  "Subtotal should equal sum of all item prices");
    }

    @Test(priority = 12, groups = {"functional", "overview", "regression"})
    @Story("Pricing Calculation")
    @Description("Verify total equals subtotal plus tax")
    @Severity(SeverityLevel.CRITICAL)
    public void testTotalCalculation() {
        assertTrue(checkoutOverviewPage.isTotalCorrect(),
                  "Total should equal subtotal plus tax");
    }

    @Test(priority = 13, groups = {"functional", "overview", "regression"})
    @Story("Pricing Calculation")
    @Description("Verify calculated items total matches order summary")
    @Severity(SeverityLevel.NORMAL)
    public void testCalculatedItemsTotalAccuracy() {
        double calculatedTotal = checkoutOverviewPage.calculateItemsTotal();
        double subtotal = checkoutOverviewPage.getSubtotal();
        
        assertEquals(calculatedTotal, subtotal, 0.01,
                    "Calculated items total should match subtotal");
    }

    // ==================== ITEM DETAIL TESTS ====================

    @Test(priority = 14, groups = {"functional", "overview", "regression"})
    @Story("Item Details")
    @Description("Verify specific item can be retrieved by index")
    @Severity(SeverityLevel.NORMAL)
    public void testGetItemByIndex() {
        String firstItemName = checkoutOverviewPage.getItemNameByIndex(0);
        String firstItemDesc = checkoutOverviewPage.getItemDescriptionByIndex(0);
        String firstItemPrice = checkoutOverviewPage.getItemPriceByIndex(0);
        String firstItemQty = checkoutOverviewPage.getItemQuantityByIndex(0);
        
        assertFalse(firstItemName.isEmpty(), SauceDemoMessages.ITEM_NAME_NOT_EMPTY);
        assertFalse(firstItemDesc.isEmpty(), SauceDemoMessages.ITEM_DESCRIPTION_NOT_EMPTY_CHECKOUT);
        assertTrue(firstItemPrice.matches("\\$\\d+\\.\\d{2}"), SauceDemoMessages.ITEM_PRICE_FORMAT_INVALID);
        assertEquals(firstItemQty, "1", SauceDemoMessages.ITEM_QUANTITY_ONE_TEXT);
    }

    @Test(priority = 15, groups = {"functional", "overview", "regression"})
    @Story("Item Display")
    @Description("Verify all items are individually displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testAllItemsIndividuallyDisplayed() {
        for (int i = 0; i < checkoutOverviewPage.getCartItemCount(); i++) {
            assertTrue(checkoutOverviewPage.isItemDisplayed(i),
                      "Item at index " + i + " should be displayed");
        }
    }

    // ==================== BUTTON TESTS ====================

    @Test(priority = 16, groups = {"smoke", "overview", "regression"})
    @Story("Button Display")
    @Description("Verify Finish and Cancel buttons are displayed")
    @Severity(SeverityLevel.BLOCKER)
    public void testButtonsDisplayed() {
        assertTrue(checkoutOverviewPage.isFinishButtonDisplayed(), 
                  "Finish button should be displayed");
        assertTrue(checkoutOverviewPage.isCancelButtonDisplayed(), 
                  "Cancel button should be displayed");
    }

    @Test(priority = 17, groups = {"functional", "overview", "regression"})
    @Story("Button States")
    @Description("Verify Finish and Cancel buttons are enabled")
    @Severity(SeverityLevel.CRITICAL)
    public void testButtonsEnabled() {
        assertTrue(checkoutOverviewPage.isFinishButtonEnabled(), 
                  "Finish button should be enabled");
        assertTrue(checkoutOverviewPage.isCancelButtonEnabled(), 
                  "Cancel button should be enabled");
    }

    @Test(priority = 18, groups = {"functional", "overview", "regression"})
    @Story("Button Display")
    @Description("Verify button text is displayed correctly")
    @Severity(SeverityLevel.NORMAL)
    public void testButtonText() {
        String finishText = checkoutOverviewPage.getFinishButtonText();
        String cancelText = checkoutOverviewPage.getCancelButtonText();
        
        assertEquals(finishText, SauceDemoConstants.FINISH_BUTTON_TEXT,
                    "Finish button text should be 'Finish'");
        assertEquals(cancelText, SauceDemoConstants.CANCEL_BUTTON_TEXT,
                    "Cancel button text should be 'Cancel'");
    }

    // ==================== NAVIGATION TESTS ====================

    @Test(priority = 19, groups = {"functional", "overview", "regression"})
    @Story("Order Review")
    @Description("Verify cart badge displays correct item count")
    @Severity(SeverityLevel.NORMAL)
    public void testCartBadgeOnOverviewPage() {
        assertTrue(checkoutOverviewPage.isCartBadgeDisplayed(), 
                  "Cart badge should be displayed");
        assertEquals(checkoutOverviewPage.getCartBadgeCount(), "3", 
                    "Cart badge should show 3 items");
    }

    @Test(priority = 20, groups = {"functional", "overview", "regression"})
    @Story("Order Review")
    @Description("Verify overview page displays all added items from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void testOverviewDisplaysCartItems() {
        assertEquals(checkoutOverviewPage.getCartItemCount(), 3,
                    "Overview should display all 3 items from cart");
        assertTrue(checkoutOverviewPage.areAllItemsCompletelyDisplayed(),
                  "All items should be completely displayed with all details");
    }

    // ==================== PRICING VALIDATION TESTS ====================

    @Test(priority = 21, groups = {"regression", "overview"})
    @Story("Price Validation")
    @Description("Verify all prices are positive numbers")
    @Severity(SeverityLevel.NORMAL)
    public void testAllPricesArePositive() {
        List<String> prices = checkoutOverviewPage.getAllItemPrices();
        
        for (String price : prices) {
            double priceValue = checkoutOverviewPage.extractPrice(price);
            assertTrue(priceValue > 0, SauceDemoMessages.PRICE_SHOULD_BE_POSITIVE + ": " + price);
        }
    }

    @Test(priority = 22, groups = {"functional", "overview", "regression"})
    @Story("Price Validation")
    @Description("Verify subtotal is less than total (due to tax)")
    @Severity(SeverityLevel.NORMAL)
    public void testSubtotalLessThanTotal() {
        double subtotal = checkoutOverviewPage.getSubtotal();
        double total = checkoutOverviewPage.getTotal();
        
        assertTrue(subtotal < total, 
                  "Subtotal should be less than total (tax should be added)");
    }

    @Test(priority = 23, groups = {"functional", "overview", "regression"})
    @Story("Price Validation")
    @Description("Verify tax amount is reasonable (between 5-10% of subtotal)")
    @Severity(SeverityLevel.NORMAL)
    public void testTaxPercentageReasonable() {
        double subtotal = checkoutOverviewPage.getSubtotal();
        double tax = checkoutOverviewPage.getTax();
        
        double taxPercentage = (tax / subtotal) * 100;
        
        assertTrue(taxPercentage >= 5 && taxPercentage <= 12,
                  "Tax percentage should be reasonable: " + taxPercentage + "%");
    }

    // ==================== ITEM QUANTITY VALIDATION ====================

    @Test(priority = 24, groups = {"functional", "overview", "regression"})
    @Story("Item Validation")
    @Description("Verify all quantities are positive integers")
    @Severity(SeverityLevel.NORMAL)
    public void testAllQuantitiesArePositive() {
        List<String> quantities = checkoutOverviewPage.getAllItemQuantities();
        
        for (String qty : quantities) {
            int quantity = Integer.parseInt(qty);
            assertTrue(quantity > 0, SauceDemoMessages.QUANTITY_SHOULD_BE_POSITIVE + ": " + qty);
        }
    }

    @Test(priority = 25, groups = {"functional", "overview", "regression"})
    @Story("Item Validation")
    @Description("Verify each item description contains meaningful content")
    @Severity(SeverityLevel.NORMAL)
    public void testItemDescriptionsHaveContent() {
        List<String> descriptions = checkoutOverviewPage.getAllItemDescriptions();
        
        for (String description : descriptions) {
            assertFalse(description.trim().isEmpty(), 
                       "Description should not be empty or whitespace only");
            assertTrue(description.length() > 5, 
                      "Description should have meaningful content");
        }
    }

    // ==================== COMPREHENSIVE REVIEW TESTS ====================

    @Test(priority = 26, groups = {"smoke", "overview", "regression"})
    @Story("Order Review")
    @Description("Verify complete order overview with all elements")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompleteOrderOverviewDisplay() {
        // Page title
        assertTrue(checkoutOverviewPage.isPageTitleDisplayed());
        
        // Items
        assertEquals(checkoutOverviewPage.getCartItemCount(), 3);
        assertTrue(checkoutOverviewPage.areAllItemsCompletelyDisplayed());
        
        // Summary
        assertTrue(checkoutOverviewPage.isSummaryInfoDisplayed());
        assertTrue(checkoutOverviewPage.isSubtotalCorrect());
        assertTrue(checkoutOverviewPage.isTotalCorrect());
        
        // Buttons
        assertTrue(checkoutOverviewPage.isFinishButtonDisplayed());
        assertTrue(checkoutOverviewPage.isCancelButtonDisplayed());
    }

    @Test(priority = 27, groups = {"regression", "overview"})
    @Story("Order Review")
    @Description("Verify pricing logic throughout order review")
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderPricingLogic() {
        double subtotal = checkoutOverviewPage.getSubtotal();
        double tax = checkoutOverviewPage.getTax();
        double total = checkoutOverviewPage.getTotal();
        double calculatedItems = checkoutOverviewPage.calculateItemsTotal();
        
        // Subtotal = sum of items
        assertEquals(calculatedItems, subtotal, 0.01);
        
        // Tax > 0
        assertTrue(tax > 0);
        
        // Total = Subtotal + Tax
        assertEquals(total, subtotal + tax, 0.01);
    }
}
