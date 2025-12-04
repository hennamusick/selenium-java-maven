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

import java.util.List;

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
        softAssert.assertTrue(inventoryPage.isSortDropdownDisplayed(), SauceDemoMessages.SORT_DROPDOWN_DISPLAYED);
        softAssert.assertTrue(inventoryPage.isSortDropdownEnabled(), SauceDemoMessages.SORT_DROPDOWN_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify default sort option")
    @Description("Validate that default sort option is 'Name (A to Z)'")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort Functionality")
    public void testDefaultSortOption() {
        String selectedOption = inventoryPage.getSelectedSortOption();
        softAssert.assertEquals(selectedOption, SauceDemoConstants.SORT_NAME_ASC, 
            SauceDemoMessages.DEFAULT_SORT_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify all sort options are available")
    @Description("Validate that all 4 sort options are present in dropdown")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort Functionality")
    public void testAllSortOptionsAvailable() {
        List<String> sortOptions = inventoryPage.getAllSortOptions();
        
        softAssert.assertEquals(sortOptions.size(), 4, SauceDemoMessages.SORT_OPTIONS_COUNT_CORRECT);
        softAssert.assertTrue(sortOptions.contains(SauceDemoConstants.SORT_NAME_ASC), 
            SauceDemoMessages.SORT_OPTION_NAME_ASC_AVAILABLE);
        softAssert.assertTrue(sortOptions.contains(SauceDemoConstants.SORT_NAME_DESC), 
            SauceDemoMessages.SORT_OPTION_NAME_DESC_AVAILABLE);
        softAssert.assertTrue(sortOptions.contains(SauceDemoConstants.SORT_PRICE_LOW_HIGH), 
            SauceDemoMessages.SORT_OPTION_PRICE_LOW_HIGH_AVAILABLE);
        softAssert.assertTrue(sortOptions.contains(SauceDemoConstants.SORT_PRICE_HIGH_LOW), 
            SauceDemoMessages.SORT_OPTION_PRICE_HIGH_LOW_AVAILABLE);
        
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
        
        softAssert.assertEquals(productNames, sortedNames, SauceDemoMessages.PRODUCTS_SORTED_A_TO_Z);
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_NAME_ASC,
            SauceDemoMessages.SELECTED_SORT_OPTION_CORRECT);
        
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
        
        softAssert.assertEquals(productNames, sortedNames, SauceDemoMessages.PRODUCTS_SORTED_Z_TO_A);
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_NAME_DESC,
            SauceDemoMessages.SELECTED_SORT_OPTION_CORRECT);
        
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
        
        softAssert.assertEquals(priceValues, sortedPrices, SauceDemoMessages.PRODUCTS_SORTED_PRICE_LOW_HIGH);
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_PRICE_LOW_HIGH,
            SauceDemoMessages.SELECTED_SORT_OPTION_CORRECT);
        
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
        
        softAssert.assertEquals(priceValues, sortedPrices, SauceDemoMessages.PRODUCTS_SORTED_PRICE_HIGH_LOW);
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_PRICE_HIGH_LOW,
            SauceDemoMessages.SELECTED_SORT_OPTION_CORRECT);
        
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
            SauceDemoMessages.ADD_TO_CART_BUTTONS_COUNT_CORRECT);
        
        inventoryPage.addFirstItemToCart();
        
        softAssert.assertTrue(inventoryPage.isCartBadgeDisplayed(), SauceDemoMessages.CART_BADGE_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "1", SauceDemoMessages.CART_BADGE_SHOWS_ONE);
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 1, SauceDemoMessages.ADD_BUTTON_BECOMES_REMOVE);
        
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
        
        softAssert.assertTrue(inventoryPage.isCartBadgeDisplayed(), SauceDemoMessages.CART_BADGE_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "3", SauceDemoMessages.CART_BADGE_SHOWS_THREE);
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 3, SauceDemoMessages.REMOVE_BUTTONS_COUNT_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 15, groups = {"functional", "regression"}, description = "Verify remove item from cart")
    @Description("Validate that clicking 'Remove' button removes item and updates cart badge")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Shopping Cart")
    public void testRemoveItemFromCart() {
        inventoryPage.addFirstItemToCart();
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "1", SauceDemoMessages.CART_BADGE_SHOWS_ONE);
        
        inventoryPage.removeFirstItemFromCart();
        
        softAssert.assertFalse(inventoryPage.isCartBadgeDisplayed(), SauceDemoMessages.CART_BADGE_NOT_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 0, SauceDemoMessages.REMOVE_BUTTON_BECOMES_ADD);
        softAssert.assertEquals(inventoryPage.getAddToCartButtonCount(), SauceDemoConstants.PRODUCT_COUNT,
            SauceDemoMessages.ADD_TO_CART_BUTTONS_COUNT_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 16, groups = {"functional", "regression"}, description = "Verify add all items to cart")
    @Description("Validate that all 6 items can be added to cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("Shopping Cart")
    public void testAddAllItemsToCart() {
        inventoryPage.addAllItemsToCart();
        
        softAssert.assertTrue(inventoryPage.isCartBadgeDisplayed(), SauceDemoMessages.CART_BADGE_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "6", SauceDemoMessages.CART_BADGE_SHOWS_SIX);
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 6, SauceDemoMessages.ALL_REMOVE_BUTTONS_PRESENT);
        softAssert.assertEquals(inventoryPage.getAddToCartButtonCount(), 0, SauceDemoMessages.ALL_ADD_BUTTONS_PRESENT);
        
        softAssert.assertAll();
    }

    @Test(priority = 17, groups = {"regression"}, description = "Verify remove all items from cart")
    @Description("Validate that all items can be removed from cart and badge disappears")
    @Severity(SeverityLevel.NORMAL)
    @Story("Shopping Cart")
    public void testRemoveAllItemsFromCart() {
        inventoryPage.addAllItemsToCart();
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "6", SauceDemoMessages.CART_BADGE_SHOWS_SIX);
        
        inventoryPage.removeAllItemsFromCart();
        
        softAssert.assertFalse(inventoryPage.isCartBadgeDisplayed(), SauceDemoMessages.CART_BADGE_NOT_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 0, SauceDemoMessages.REMOVE_BUTTON_BECOMES_ADD);
        softAssert.assertEquals(inventoryPage.getAddToCartButtonCount(), SauceDemoConstants.PRODUCT_COUNT,
            SauceDemoMessages.ALL_ADD_BUTTONS_PRESENT);
        
        softAssert.assertAll();
    }

    @Test(priority = 18, groups = {"regression"}, description = "Verify cart persists after sorting")
    @Description("Validate that items in cart remain after changing sort order")
    @Severity(SeverityLevel.NORMAL)
    @Story("Shopping Cart")
    public void testCartPersistsAfterSorting() {
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(2);
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "2", SauceDemoMessages.CART_BADGE_COUNT_UPDATED);
        
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_HIGH_LOW);
        
        softAssert.assertTrue(inventoryPage.isCartBadgeDisplayed(), SauceDemoMessages.CART_BADGE_STILL_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getCartBadgeCount(), "2", SauceDemoMessages.CART_COUNT_UNCHANGED);
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 2, SauceDemoMessages.CART_ITEMS_REMAIN);
        
        softAssert.assertAll();
    }

    @Test(priority = 19, groups = {"regression"}, description = "Verify cart badge not displayed when empty")
    @Description("Validate that cart badge is hidden when no items are in cart")
    @Severity(SeverityLevel.NORMAL)
    @Story("Shopping Cart")
    public void testCartBadgeNotDisplayedWhenEmpty() {
        softAssert.assertFalse(inventoryPage.isCartBadgeDisplayed(), 
            SauceDemoMessages.CART_BADGE_NOT_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getRemoveButtonCount(), 0, SauceDemoMessages.REMOVE_BUTTON_BECOMES_ADD);
        
        softAssert.assertAll();
    }

    @Test(priority = 20, groups = {"smoke", "regression"}, description = "Verify shopping cart link is clickable")
    @Description("Validate that shopping cart icon is clickable")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Shopping Cart")
    public void testShoppingCartLinkClickable() {
        softAssert.assertTrue(inventoryPage.isShoppingCartDisplayed(), SauceDemoMessages.SHOPPING_CART_DISPLAYED);
        softAssert.assertTrue(inventoryPage.isShoppingCartEnabled(), SauceDemoMessages.SHOPPING_CART_CLICKABLE);
        
        softAssert.assertAll();
    }

    // ==================== ADVANCED FILTER/SORT TESTS ====================

    @Test(priority = 21, groups = {"functional", "regression"}, description = "Verify sort option order in dropdown")
    @Description("Validate that sort options appear in correct order")
    @Severity(SeverityLevel.MINOR)
    @Story("Filter/Sort Dropdown")
    public void testSortDropdownOptionsOrder() {
        List<String> options = inventoryPage.getAllSortOptions();
        
        softAssert.assertEquals(options.get(0), SauceDemoConstants.SORT_NAME_ASC, 
            "First option should be Name (A to Z)");
        softAssert.assertEquals(options.get(1), SauceDemoConstants.SORT_NAME_DESC, 
            "Second option should be Name (Z to A)");
        softAssert.assertEquals(options.get(2), SauceDemoConstants.SORT_PRICE_LOW_HIGH, 
            "Third option should be Price (low to high)");
        softAssert.assertEquals(options.get(3), SauceDemoConstants.SORT_PRICE_HIGH_LOW, 
            "Fourth option should be Price (high to low)");
        
        softAssert.assertAll();
    }

    @Test(priority = 22, groups = {"functional", "regression"}, description = "Verify sort persists after adding item to cart")
    @Description("Validate that selected sort option remains after cart operations")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter/Sort Dropdown")
    public void testSortPersistsAfterCartOperation() {
        // Select a non-default sort option
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_HIGH_LOW);
        String sortBeforeCart = inventoryPage.getSelectedSortOption();
        
        // Add item to cart
        inventoryPage.addFirstItemToCart();
        
        // Verify sort is still the same
        String sortAfterCart = inventoryPage.getSelectedSortOption();
        softAssert.assertEquals(sortAfterCart, sortBeforeCart, 
            "Sort option should persist after adding item to cart");
        softAssert.assertEquals(sortAfterCart, SauceDemoConstants.SORT_PRICE_HIGH_LOW, 
            "Sort should still be Price (high to low)");
        
        softAssert.assertAll();
    }

    @Test(priority = 23, groups = {"functional", "regression"}, description = "Verify products reorder immediately when sort changes")
    @Description("Validate that product list updates immediately when sort option is changed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter/Sort Dropdown")
    public void testProductsReorderImmediately() {
        // Get first product with default sort (A to Z)
        String firstProductDefault = inventoryPage.getProductNameByIndex(0);
        
        // Change to Z to A
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_NAME_DESC);
        String firstProductReversed = inventoryPage.getProductNameByIndex(0);
        
        // Products should be different
        softAssert.assertNotEquals(firstProductDefault, firstProductReversed, 
            "First product should change when sort order changes");
        
        softAssert.assertAll();
    }

    @Test(priority = 24, groups = {"functional", "regression"}, description = "Verify sort by value using dropdown value attribute")
    @Description("Validate that sorting works using dropdown value attribute")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter/Sort Dropdown")
    public void testSortByValueAttribute() {
        // Sort using value attribute
        inventoryPage.selectSortByValue("za");
        
        String selectedOption = inventoryPage.getSelectedSortOption();
        softAssert.assertEquals(selectedOption, SauceDemoConstants.SORT_NAME_DESC, 
            "Sort by value 'za' should select Name (Z to A)");
        
        // Verify products are actually sorted Z to A
        List<String> productNames = inventoryPage.getAllProductNames();
        List<String> sortedNames = productNames.stream()
                .sorted((a, b) -> b.compareTo(a))
                .toList();
        softAssert.assertEquals(productNames, sortedNames, 
            "Products should be sorted Z to A");
        
        softAssert.assertAll();
    }

    @Test(priority = 25, groups = {"functional", "regression"}, description = "Verify all sort values work correctly")
    @Description("Validate that all dropdown values (az, za, lohi, hilo) function properly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter/Sort Dropdown")
    public void testAllSortValuesFunctional() {
        // Test az
        inventoryPage.selectSortByValue("az");
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_NAME_ASC);
        
        // Test za
        inventoryPage.selectSortByValue("za");
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_NAME_DESC);
        
        // Test lohi
        inventoryPage.selectSortByValue("lohi");
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_PRICE_LOW_HIGH);
        
        // Test hilo
        inventoryPage.selectSortByValue("hilo");
        softAssert.assertEquals(inventoryPage.getSelectedSortOption(), SauceDemoConstants.SORT_PRICE_HIGH_LOW);
        
        softAssert.assertAll();
    }

    @Test(priority = 26, groups = {"functional", "regression"}, description = "Verify sort affects all products equally")
    @Description("Validate that all 6 products are always displayed regardless of sort")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter/Sort Dropdown")
    public void testSortDoesNotFilterProducts() {
        // Count products with default sort
        int countDefault = inventoryPage.getProductCount();
        
        // Change sort and count again
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_HIGH_LOW);
        int countAfterSort = inventoryPage.getProductCount();
        
        softAssert.assertEquals(countDefault, SauceDemoConstants.PRODUCT_COUNT, 
            "Should have 6 products with default sort");
        softAssert.assertEquals(countAfterSort, SauceDemoConstants.PRODUCT_COUNT, 
            "Should still have 6 products after sort change");
        softAssert.assertEquals(countDefault, countAfterSort, 
            "Product count should not change when sorting");
        
        softAssert.assertAll();
    }

    @Test(priority = 27, groups = {"regression"}, description = "Verify sorting multiple times works correctly")
    @Description("Validate that changing sort multiple times maintains correct order")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter/Sort Dropdown")
    public void testMultipleSortChanges() {
        // Sort by name A-Z
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_NAME_ASC);
        List<String> namesAZ = inventoryPage.getAllProductNames();
        
        // Sort by price
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_LOW_HIGH);
        
        // Sort back to name A-Z
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_NAME_ASC);
        List<String> namesAZAgain = inventoryPage.getAllProductNames();
        
        softAssert.assertEquals(namesAZ, namesAZAgain, 
            "Product order should be identical when selecting same sort option twice");
        
        softAssert.assertAll();
    }

    @Test(priority = 28, groups = {"functional", "regression"}, description = "Verify price sorting with decimal values")
    @Description("Validate that price sorting correctly handles decimal values")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter/Sort Dropdown")
    public void testPriceSortingWithDecimals() {
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_PRICE_LOW_HIGH);
        
        List<String> prices = inventoryPage.getAllProductPrices();
        List<Double> priceValues = prices.stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .toList();
        
        // Verify ascending order
        for (int i = 0; i < priceValues.size() - 1; i++) {
            softAssert.assertTrue(priceValues.get(i) <= priceValues.get(i + 1), 
                String.format("Price at index %d ($%.2f) should be <= price at index %d ($%.2f)", 
                    i, priceValues.get(i), i+1, priceValues.get(i+1)));
        }
        
        softAssert.assertAll();
    }

    @Test(priority = 29, groups = {"functional", "regression"}, description = "Verify name sorting is case-insensitive")
    @Description("Validate that name sorting handles case properly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter/Sort Dropdown")
    public void testNameSortingCaseHandling() {
        inventoryPage.selectSortOption(SauceDemoConstants.SORT_NAME_ASC);
        
        List<String> productNames = inventoryPage.getAllProductNames();
        
        // Verify alphabetical order (case-insensitive)
        for (int i = 0; i < productNames.size() - 1; i++) {
            String current = productNames.get(i).toLowerCase();
            String next = productNames.get(i + 1).toLowerCase();
            softAssert.assertTrue(current.compareTo(next) <= 0, 
                String.format("'%s' should come before or equal to '%s' in alphabetical order", 
                    productNames.get(i), productNames.get(i + 1)));
        }
        
        softAssert.assertAll();
    }
}
