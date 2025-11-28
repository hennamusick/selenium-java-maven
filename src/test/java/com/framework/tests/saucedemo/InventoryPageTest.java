package com.framework.tests.saucedemo;

import com.framework.pages.saucedemo.InventoryPage;
import com.framework.pages.saucedemo.LoginPage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import com.framework.utils.saucedemo.SauceDemoConstants;
import com.framework.utils.saucedemo.SauceDemoMessages;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Inventory Page Test Class for SauceDemo
 * Contains tests for inventory page display, product functionality, and navigation
 * Note: Login is handled in BeforeMethod to focus tests on inventory page features
 */
@Epic("SauceDemo E-Commerce")
@Feature("Inventory and Products")
public class InventoryPageTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        // Using baseUrl.2 - https://www.saucedemo.com
        driver.get(ConfigReader.getBaseUrl(2));
        waitForPageToLoad();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        
        // Login to reach inventory page for all tests
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify inventory page displays correctly")
    @Description("Validate that inventory page displays correctly with title and logo after login")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Inventory Page Display")
    public void testInventoryPageDisplay() {
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), SauceDemoMessages.INVENTORY_TITLE_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getPageTitle(), SauceDemoConstants.PRODUCTS_TITLE, 
            SauceDemoMessages.INVENTORY_TITLE_CORRECT);
        softAssert.assertTrue(inventoryPage.isAppLogoDisplayed(), SauceDemoMessages.APP_LOGO_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "regression"}, description = "Verify inventory page elements")
    @Description("Validate that all key inventory page elements are displayed (menu, cart, sort dropdown)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Inventory Page Display")
    public void testInventoryPageElements() {
        softAssert.assertTrue(inventoryPage.isMenuButtonDisplayed(), SauceDemoMessages.MENU_BUTTON_DISPLAYED);
        softAssert.assertTrue(inventoryPage.isShoppingCartDisplayed(), SauceDemoMessages.SHOPPING_CART_DISPLAYED);
        softAssert.assertTrue(inventoryPage.isSortDropdownDisplayed(), SauceDemoMessages.SORT_DROPDOWN_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"smoke", "regression"}, description = "Verify inventory items are displayed")
    @Description("Validate that correct number of inventory items are displayed on the page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Display")
    public void testInventoryItemsDisplayed() {
        int itemCount = inventoryPage.getInventoryItemCount();
        softAssert.assertTrue(itemCount > 0, SauceDemoMessages.INVENTORY_ITEMS_DISPLAYED);
        softAssert.assertEquals(itemCount, SauceDemoConstants.PRODUCT_COUNT, 
            SauceDemoMessages.INVENTORY_COUNT_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify product names are displayed")
    @Description("Validate that all product names are displayed and count matches expected products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Display")
    public void testProductNamesDisplayed() {
        var productNames = inventoryPage.getAllProductNames();
        softAssert.assertFalse(productNames.isEmpty(), SauceDemoMessages.PRODUCT_NAMES_DISPLAYED);
        softAssert.assertEquals(productNames.size(), SauceDemoConstants.PRODUCT_COUNT, 
            SauceDemoMessages.PRODUCT_NAMES_DISPLAYED);
        
        softAssert.assertAll();
    }

    // ==================== SORT/FILTER FUNCTIONALITY TESTS ====================

    @Test(priority = 5, groups = {"smoke", "functional", "regression"}, description = "Verify sort dropdown is displayed and enabled")
    @Description("Validate that sort dropdown is visible and interactive on inventory page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sort Functionality")
    public void testSortDropdownDisplayedAndEnabled() {
        softAssert.assertTrue(inventoryPage.isSortDropdownDisplayed(), "Sort dropdown should be displayed");
        softAssert.assertTrue(inventoryPage.isSortDropdownEnabled(), "Sort dropdown should be enabled");
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify default sort option")
    @Description("Validate that default sort option is 'Name (A to Z)'")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort Functionality")
    public void testDefaultSortOption() {
        String selectedOption = inventoryPage.getSelectedSortOption();
        softAssert.assertEquals(selectedOption, SauceDemoConstants.SORT_NAME_ASC, 
            "Default sort should be 'Name (A to Z)'");
        
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify all sort options are available")
    @Description("Validate that all 4 sort options are present in dropdown")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort Functionality")
    public void testAllSortOptionsAvailable() {
        List<String> sortOptions = inventoryPage.getAllSortOptions();
        
        softAssert.assertEquals(sortOptions.size(), 4, "Should have 4 sort options");
        softAssert.assertTrue(sortOptions.contains(SauceDemoConstants.SORT_NAME_ASC), 
            "Should contain 'Name (A to Z)'");
        softAssert.assertTrue(sortOptions.contains(SauceDemoConstants.SORT_NAME_DESC), 
            "Should contain 'Name (Z to A)'");
        softAssert.assertTrue(sortOptions.contains(SauceDemoConstants.SORT_PRICE_LOW_HIGH), 
            "Should contain 'Price (low to high)'");
        softAssert.assertTrue(sortOptions.contains(SauceDemoConstants.SORT_PRICE_HIGH_LOW), 
            "Should contain 'Price (high to low)'");
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"functional", "regression"}, description = "Verify sort by name A to Z")
    @Description("Validate that products are sorted alphabetically A to Z")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sort Functionality")
    public void testSortByNameAtoZ() {
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_NAME_ASC);
        
        List<String> productNames = inventoryPage.getAllProductNames();
        List<String> sortedNames = productNames.stream().sorted().toList();
        
        softAssert.assertEquals(productNames, sortedNames, "Products should be sorted A to Z");
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_NAME_ASC,
            "Selected option should be 'Name (A to Z)'");
        
        softAssert.assertAll();
    }

    @Test(priority = 9, groups = {"functional", "regression"}, description = "Verify sort by name Z to A")
    @Description("Validate that products are sorted alphabetically Z to A")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sort Functionality")
    public void testSortByNameZtoA() {
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_NAME_DESC);
        
        List<String> productNames = inventoryPage.getAllProductNames();
        List<String> sortedNames = productNames.stream()
                .sorted((a, b) -> b.compareTo(a))
                .toList();
        
        softAssert.assertEquals(productNames, sortedNames, "Products should be sorted Z to A");
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_NAME_DESC,
            "Selected option should be 'Name (Z to A)'");
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"functional", "regression"}, description = "Verify sort by price low to high")
    @Description("Validate that products are sorted by price in ascending order")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sort Functionality")
    public void testSortByPriceLowToHigh() {
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_LOW_HIGH);
        
        List<String> prices = inventoryPage.getAllProductPrices();
        List<Double> priceValues = prices.stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .toList();
        
        List<Double> sortedPrices = priceValues.stream().sorted().toList();
        
        softAssert.assertEquals(priceValues, sortedPrices, "Products should be sorted by price low to high");
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_PRICE_LOW_HIGH,
            "Selected option should be 'Price (low to high)'");
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"functional", "regression"}, description = "Verify sort by price high to low")
    @Description("Validate that products are sorted by price in descending order")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sort Functionality")
    public void testSortByPriceHighToLow() {
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_HIGH_LOW);
        
        List<String> prices = inventoryPage.getAllProductPrices();
        List<Double> priceValues = prices.stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .toList();
        
        List<Double> sortedPrices = priceValues.stream()
                .sorted((a, b) -> b.compareTo(a))
                .toList();
        
        softAssert.assertEquals(priceValues, sortedPrices, "Products should be sorted by price high to low");
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_PRICE_HIGH_LOW,
            "Selected option should be 'Price (high to low)'");
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"regression"}, description = "Verify switching between sort options")
    @Description("Validate that sort order changes when switching between different sort options")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort Functionality")
    public void testSwitchingSortOptions() {
        // Sort A to Z
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_NAME_ASC);
        String firstOption = inventoryPage.getSelectedSortOption();
        softAssert.assertEquals(firstOption, SauceDemoConstants.SORT_NAME_ASC);
        
        // Switch to Z to A
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_NAME_DESC);
        String secondOption = inventoryPage.getSelectedSortOption();
        softAssert.assertEquals(secondOption, SauceDemoConstants.SORT_NAME_DESC);
        
        // Switch to Price Low to High
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_LOW_HIGH);
        String thirdOption = inventoryPage.getSelectedSortOption();
        softAssert.assertEquals(thirdOption, SauceDemoConstants.SORT_PRICE_LOW_HIGH);
        
        softAssert.assertAll();
    }

    // ==================== SHOPPING CART FUNCTIONALITY TESTS ====================

    @Test(priority = 13, groups = {"smoke", "functional", "regression"}, description = "Verify add to cart button functionality")
    @Description("Validate that clicking 'Add to cart' button adds item and shows cart badge")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Shopping Cart")
    public void testAddSingleItemToCart() {
        int initialAddButtons = inventoryPage.getAddToCartButtonCount();
        softAssert.assertEquals(initialAddButtons, SauceDemoConstants.PRODUCT_COUNT, 
            "Should have 6 'Add to cart' buttons initially");
        
        inventoryPage.addFirstItemToCart();
        
        softAssert.assertTrue(inventoryPage.isCartBadgeDisplayed(), "Cart badge should be displayed");
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart badge should show 1 item");
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 1, "Should have 1 'Remove' button");
        
        softAssert.assertAll();
    }

    @Test(priority = 14, groups = {"functional", "regression"}, description = "Verify add multiple items to cart")
    @Description("Validate that multiple items can be added to cart and badge count updates correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Shopping Cart")
    public void testAddMultipleItemsToCart() {
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        inventoryPage.addItemToCartByIndex(2);
        
        softAssert.assertTrue(inventoryPage.isCartBadgeDisplayed(), "Cart badge should be displayed");
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "3", "Cart badge should show 3 items");
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 3, "Should have 3 'Remove' buttons");
        
        softAssert.assertAll();
    }

    @Test(priority = 15, groups = {"functional", "regression"}, description = "Verify remove item from cart")
    @Description("Validate that clicking 'Remove' button removes item and updates cart badge")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Shopping Cart")
    public void testRemoveItemFromCart() {
        inventoryPage.addFirstItemToCart();
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart should have 1 item");
        
        inventoryPage.removeFirstItemFromCart();
        
        softAssert.assertFalse(inventoryPage.isCartBadgeDisplayed(), "Cart badge should not be displayed");
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 0, "Should have 0 'Remove' buttons");
        softAssert.assertEquals(inventoryPage.getAddToCartButtonCount(), SauceDemoConstants.PRODUCT_COUNT,
            "Should have 6 'Add to cart' buttons");
        
        softAssert.assertAll();
    }

    @Test(priority = 16, groups = {"functional", "regression"}, description = "Verify add all items to cart")
    @Description("Validate that all 6 items can be added to cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("Shopping Cart")
    public void testAddAllItemsToCart() {
        inventoryPage.addAllItemsToCart();
        
        softAssert.assertTrue(inventoryPage.isCartBadgeDisplayed(), "Cart badge should be displayed");
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "6", "Cart badge should show 6 items");
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 6, "Should have 6 'Remove' buttons");
        softAssert.assertEquals(inventoryPage.getAddToCartButtonCount(), 0, "Should have 0 'Add to cart' buttons");
        
        softAssert.assertAll();
    }

    @Test(priority = 17, groups = {"regression"}, description = "Verify remove all items from cart")
    @Description("Validate that all items can be removed from cart and badge disappears")
    @Severity(SeverityLevel.NORMAL)
    @Story("Shopping Cart")
    public void testRemoveAllItemsFromCart() {
        inventoryPage.addAllItemsToCart();
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "6", "Cart should have 6 items");
        
        inventoryPage.removeAllItemsFromCart();
        
        softAssert.assertFalse(inventoryPage.isCartBadgeDisplayed(), "Cart badge should not be displayed");
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 0, "Should have 0 'Remove' buttons");
        softAssert.assertEquals(inventoryPage.getAddToCartButtonCount(), SauceDemoConstants.PRODUCT_COUNT,
            "All 6 'Add to cart' buttons should be restored");
        
        softAssert.assertAll();
    }

    @Test(priority = 18, groups = {"regression"}, description = "Verify cart persists after sorting")
    @Description("Validate that items in cart remain after changing sort order")
    @Severity(SeverityLevel.NORMAL)
    @Story("Shopping Cart")
    public void testCartPersistsAfterSorting() {
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(2);
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "2", "Cart should have 2 items");
        
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_HIGH_LOW);
        
        softAssert.assertTrue(inventoryPage.isCartBadgeDisplayed(), "Cart badge should still be displayed");
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "2", "Cart should still have 2 items");
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 2, "Should still have 2 'Remove' buttons");
        
        softAssert.assertAll();
    }

    @Test(priority = 19, groups = {"regression"}, description = "Verify cart badge not displayed when empty")
    @Description("Validate that cart badge is hidden when no items are in cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("Shopping Cart")
    public void testCartBadgeNotDisplayedWhenEmpty() {
        softAssert.assertFalse(inventoryPage.isCartBadgeDisplayed(), 
            "Cart badge should not be displayed when cart is empty");
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 0, "Should have 0 'Remove' buttons");
        
        softAssert.assertAll();
    }

    @Test(priority = 20, groups = {"smoke", "regression"}, description = "Verify shopping cart link is clickable")
    @Description("Validate that shopping cart icon is clickable")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Shopping Cart")
    public void testShoppingCartLinkClickable() {
        softAssert.assertTrue(inventoryPage.isShoppingCartDisplayed(), "Shopping cart should be displayed");
        softAssert.assertTrue(inventoryPage.isShoppingCartEnabled(), "Shopping cart should be enabled/clickable");
        
        softAssert.assertAll();
    }
}
