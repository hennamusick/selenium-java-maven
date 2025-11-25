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
}
