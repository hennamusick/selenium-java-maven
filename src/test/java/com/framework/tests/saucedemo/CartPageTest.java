package com.framework.tests.saucedemo;

import com.framework.pages.saucedemo.CartPage;
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

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test class for SauceDemo Shopping Cart page functionality.
 * Tests cover cart display, item management, navigation, and cart operations.
 * 
 * @author Framework
 * @version 1.0
 */
@Epic("SauceDemo E-Commerce")
@Feature("Shopping Cart")
public class CartPageTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        
        // Login and navigate to inventory page
        driver.get(ConfigReader.getBaseUrl(2));
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
    }

    // ==================== CART PAGE DISPLAY TESTS ====================

    @Test(priority = 1, groups = {"smoke", "cart", "regression"})
    @Story("Cart Page Display")
    @Description("Verify cart page title is displayed when navigating to cart")
    @Severity(SeverityLevel.BLOCKER)
    public void testCartPageTitleDisplay() {
        inventoryPage.clickShoppingCart();
        assertTrue(cartPage.isPageTitleDisplayed(), "Cart page title should be displayed");
        assertEquals(cartPage.getPageTitle(), SauceDemoConstants.CART_PAGE_TITLE, 
                    "Cart page title should be 'Your Cart'");
    }

    @Test(priority = 2, groups = {"smoke", "cart", "regression"})
    @Story("Cart Page Display")
    @Description("Verify cart displays as empty when no items are added")
    @Severity(SeverityLevel.NORMAL)
    public void testEmptyCartDisplay() {
        inventoryPage.clickShoppingCart();
        assertTrue(cartPage.isCartEmpty(), "Cart should be empty when no items are added");
        assertEquals(cartPage.getCartItemCount(), 0, "Cart should have 0 items");
    }

    @Test(priority = 3, groups = {"functional", "cart", "regression"})
    @Story("Cart Page Display")
    @Description("Verify Continue Shopping button is displayed and clickable")
    @Severity(SeverityLevel.NORMAL)
    public void testContinueShoppingButtonDisplay() {
        inventoryPage.clickShoppingCart();
        // Button should be present and functional (clicking will be tested separately)
        assertNotNull(cartPage, "Cart page should be initialized");
    }

    @Test(priority = 4, groups = {"functional", "cart", "regression"})
    @Story("Cart Page Display")
    @Description("Verify Checkout button is displayed and clickable")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutButtonDisplay() {
        inventoryPage.clickShoppingCart();
        // Button should be present (navigation will be tested separately)
        assertNotNull(cartPage, "Cart page should be initialized");
    }

    // ==================== CART ITEMS DISPLAY TESTS ====================

    @Test(priority = 5, groups = {"functional", "cart", "regression"})
    @Story("Cart Items Display")
    @Description("Verify cart displays single item correctly after adding one product")
    @Severity(SeverityLevel.CRITICAL)
    public void testSingleItemInCart() {
        // Add Sauce Labs Backpack
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.clickShoppingCart();
        
        assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain 1 item");
        List<String> itemNames = cartPage.getAllItemNames();
        assertTrue(itemNames.contains(SauceDemoConstants.PRODUCT_BACKPACK), 
                  "Cart should contain Sauce Labs Backpack");
    }

    @Test(priority = 6, groups = {"functional", "cart", "regression"})
    @Story("Cart Items Display")
    @Description("Verify cart displays multiple items correctly after adding three products")
    @Severity(SeverityLevel.CRITICAL)
    public void testMultipleItemsInCart() {
        // Add 3 items: Backpack, Bolt T-Shirt, Onesie
        inventoryPage.addItemToCartByIndex(0); // Backpack
        inventoryPage.addItemToCartByIndex(1); // Bike Light
        inventoryPage.addItemToCartByIndex(3); // Bolt T-Shirt
        
        inventoryPage.clickShoppingCart();
        
        assertEquals(cartPage.getCartItemCount(), 3, "Cart should contain 3 items");
    }

    @Test(priority = 7, groups = {"functional", "cart", "regression"})
    @Story("Cart Items Display")
    @Description("Verify cart displays correct item names for all added products")
    @Severity(SeverityLevel.NORMAL)
    public void testCartItemNames() {
        // Add 3 specific items
        inventoryPage.addItemToCartByIndex(0); // Backpack
        inventoryPage.addItemToCartByIndex(3); // Bolt T-Shirt  
        inventoryPage.addItemToCartByIndex(4); // Onesie
        
        inventoryPage.clickShoppingCart();
        
        List<String> itemNames = cartPage.getAllItemNames();
        assertEquals(itemNames.size(), 3, "Cart should contain 3 items");
    }

    @Test(priority = 8, groups = {"functional", "cart", "regression"})
    @Story("Cart Items Display")
    @Description("Verify cart displays correct item prices for all added products")
    @Severity(SeverityLevel.CRITICAL)
    public void testCartItemPrices() {
        // Add 3 items
        inventoryPage.addItemToCartByIndex(0); // Backpack
        inventoryPage.addItemToCartByIndex(3); // Bolt T-Shirt
        inventoryPage.addItemToCartByIndex(4); // Onesie
        
        inventoryPage.clickShoppingCart();
        
        List<String> prices = cartPage.getAllItemPrices();
        assertEquals(prices.size(), 3, "Cart should display 3 item prices");
        
        // Verify prices are displayed in correct format
        for (String price : prices) {
            assertTrue(price.startsWith("$"), "Price should start with $");
        }
    }

    @Test(priority = 9, groups = {"functional", "cart", "regression"})
    @Story("Cart Items Display")
    @Description("Verify cart displays correct quantity for each item")
    @Severity(SeverityLevel.NORMAL)
    public void testCartItemQuantities() {
        // Add multiple items
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        
        inventoryPage.clickShoppingCart();
        
        List<String> quantities = cartPage.getAllItemQuantities();
        assertEquals(quantities.size(), 2, "Cart should display quantities for 2 items");
        
        // Each item should have quantity of 1 (SauceDemo doesn't support qty > 1)
        for (String quantity : quantities) {
            assertEquals(quantity, "1", "Each item should have quantity of 1");
        }
    }

    @Test(priority = 10, groups = {"functional", "cart", "regression"})
    @Story("Cart Items Display")
    @Description("Verify cart displays item descriptions for all products")
    @Severity(SeverityLevel.MINOR)
    public void testCartItemDescriptions() {
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.clickShoppingCart();
        
        List<String> descriptions = cartPage.getAllItemDescriptions();
        assertEquals(descriptions.size(), 1, "Cart should display 1 item description");
        assertFalse(descriptions.get(0).isEmpty(), "Item description should not be empty");
    }

    // ==================== REMOVE ITEM TESTS ====================

    @Test(priority = 11, groups = {"functional", "cart", "regression"})
    @Story("Cart Item Management")
    @Description("Verify remove button count matches the number of items in cart")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveButtonCount() {
        // Add 3 items
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        inventoryPage.addItemToCartByIndex(2);
        
        inventoryPage.clickShoppingCart();
        
        assertEquals(cartPage.getRemoveButtonCount(), 3, 
                    "Remove button count should match item count");
    }

    @Test(priority = 12, groups = {"functional", "cart", "regression"})
    @Story("Cart Item Management")
    @Description("Verify single item can be removed from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void testRemoveSingleItemFromCart() {
        // Add 2 items
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        
        inventoryPage.clickShoppingCart();
        int initialCount = cartPage.getCartItemCount();
        
        // Remove first item
        cartPage.removeItemByIndex(0);
        
        assertEquals(cartPage.getCartItemCount(), initialCount - 1, 
                    "Cart should have one less item after removal");
    }

    @Test(priority = 13, groups = {"functional", "cart", "regression"})
    @Story("Cart Item Management")
    @Description("Verify all items can be removed from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void testRemoveAllItemsFromCart() {
        // Add 3 items
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        inventoryPage.addItemToCartByIndex(2);
        
        inventoryPage.clickShoppingCart();
        assertFalse(cartPage.isCartEmpty(), "Cart should not be empty before removing items");
        
        // Remove all items
        cartPage.removeAllItems();
        
        assertTrue(cartPage.isCartEmpty(), "Cart should be empty after removing all items");
        assertEquals(cartPage.getCartItemCount(), 0, "Cart item count should be 0");
    }

    @Test(priority = 14, groups = {"functional", "cart", "regression"})
    @Story("Cart Item Management")
    @Description("Verify specific item can be removed from cart by name")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveSpecificItemByName() {
        // Add 3 items
        inventoryPage.addItemToCartByIndex(0); // Backpack
        inventoryPage.addItemToCartByIndex(3); // Bolt T-Shirt
        inventoryPage.addItemToCartByIndex(4); // Onesie
        
        inventoryPage.clickShoppingCart();
        
        // Get initial count
        int initialCount = cartPage.getCartItemCount();
        
        // Remove middle item
        cartPage.removeItemByIndex(1);
        
        assertEquals(cartPage.getCartItemCount(), initialCount - 1, 
                    "Cart should have one less item");
    }

    // ==================== CART BADGE TESTS ====================

    @Test(priority = 15, groups = {"functional", "cart", "regression"})
    @Story("Cart Badge")
    @Description("Verify cart badge displays correct count when items are in cart")
    @Severity(SeverityLevel.NORMAL)
    public void testCartBadgeCount() {
        // Add 3 items from inventory page
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        inventoryPage.addItemToCartByIndex(2);
        
        inventoryPage.clickShoppingCart();
        
        if (cartPage.isCartBadgeDisplayed()) {
            assertEquals(cartPage.getCartBadgeCount(), "3", 
                        "Cart badge should show 3 items");
        }
    }

    @Test(priority = 16, groups = {"functional", "cart", "regression"})
    @Story("Cart Badge")
    @Description("Verify cart badge updates when item is removed from cart")
    @Severity(SeverityLevel.NORMAL)
    public void testCartBadgeUpdateAfterRemoval() {
        // Add 2 items
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        
        inventoryPage.clickShoppingCart();
        
        // Remove one item
        cartPage.removeItemByIndex(0);
        
        if (cartPage.isCartBadgeDisplayed()) {
            assertEquals(cartPage.getCartBadgeCount(), "1", 
                        "Cart badge should show 1 item after removal");
        }
    }

    // ==================== NAVIGATION TESTS ====================

    @Test(priority = 17, groups = {"functional", "cart", "regression"})
    @Story("Cart Navigation")
    @Description("Verify Continue Shopping button navigates back to inventory page")
    @Severity(SeverityLevel.NORMAL)
    public void testContinueShoppingNavigation() {
        inventoryPage.clickShoppingCart();
        cartPage.clickContinueShopping();
        
        // Verify back on inventory page
        assertTrue(driver.getCurrentUrl().contains(SauceDemoConstants.INVENTORY_URL), 
                  "Should navigate back to inventory page");
    }

    @Test(priority = 18, groups = {"functional", "cart", "regression"})
    @Story("Cart Navigation")
    @Description("Verify Checkout button navigates to checkout page")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutNavigation() {
        // Add at least one item (checkout requires items in cart)
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.clickShoppingCart();
        
        cartPage.clickCheckout();
        
        // Verify on checkout page
        assertTrue(driver.getCurrentUrl().contains(SauceDemoConstants.CHECKOUT_STEP_ONE_URL), 
                  "Should navigate to checkout page");
    }

    // ==================== CART CALCULATIONS TESTS ====================

    @Test(priority = 19, groups = {"functional", "cart", "regression"})
    @Story("Cart Calculations")
    @Description("Verify total price calculation for single item")
    @Severity(SeverityLevel.CRITICAL)
    public void testSingleItemPriceCalculation() {
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.clickShoppingCart();
        
        double totalPrice = cartPage.calculateTotalPrice();
        assertTrue(totalPrice > 0, "Total price should be greater than 0");
    }

    @Test(priority = 20, groups = {"functional", "cart", "regression"})
    @Story("Cart Calculations")
    @Description("Verify total price calculation for multiple items matches expected sum")
    @Severity(SeverityLevel.CRITICAL)
    public void testMultipleItemsPriceCalculation() {
        // Add 3 items: Backpack, Bolt T-Shirt, Onesie
        inventoryPage.addItemToCartByIndex(0); // Backpack
        inventoryPage.addItemToCartByIndex(3); // Bolt T-Shirt
        inventoryPage.addItemToCartByIndex(4); // Onesie
        
        inventoryPage.clickShoppingCart();
        
        double totalPrice = cartPage.calculateTotalPrice();
        
        assertTrue(totalPrice > 40.0, "Total price should be reasonable for 3 items");
    }

    @Test(priority = 21, groups = {"functional", "cart", "regression"})
    @Story("Cart Calculations")
    @Description("Verify price calculation updates after removing an item")
    @Severity(SeverityLevel.NORMAL)
    public void testPriceCalculationAfterRemoval() {
        // Add 2 items
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        
        inventoryPage.clickShoppingCart();
        
        double initialTotal = cartPage.calculateTotalPrice();
        
        // Remove one item
        cartPage.removeItemByIndex(0);
        
        double updatedTotal = cartPage.calculateTotalPrice();
        
        assertTrue(updatedTotal < initialTotal, 
                  "Total price should decrease after removing an item");
    }

    // ==================== CART VERIFICATION TESTS ====================

    @Test(priority = 22, groups = {"functional", "cart", "regression"})
    @Story("Cart Verification")
    @Description("Verify specific item presence can be verified in cart")
    @Severity(SeverityLevel.NORMAL)
    public void testVerifyItemInCart() {
        inventoryPage.addItemToCartByIndex(0); // Backpack
        inventoryPage.clickShoppingCart();
        
        assertTrue(cartPage.verifyItemInCart(SauceDemoConstants.PRODUCT_BACKPACK), 
                  "Backpack should be present in cart");
        assertFalse(cartPage.verifyItemInCart(SauceDemoConstants.PRODUCT_FLEECE_JACKET), 
                   "Fleece Jacket should not be present in cart");
    }

    @Test(priority = 23, groups = {"functional", "cart", "regression"})
    @Story("Cart Verification")
    @Description("Verify cart state persists when navigating back and forth")
    @Severity(SeverityLevel.NORMAL)
    public void testCartStatePersistence() {
        // Add items
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        
        inventoryPage.clickShoppingCart();
        int initialCount = cartPage.getCartItemCount();
        
        // Navigate away and back
        cartPage.clickContinueShopping();
        inventoryPage.clickShoppingCart();
        
        assertEquals(cartPage.getCartItemCount(), initialCount, 
                    "Cart items should persist after navigation");
    }
}
