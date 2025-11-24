package com.framework.tests.saucedemo;

import com.framework.pages.saucedemo.InventoryPage;
import com.framework.pages.saucedemo.LoginPage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import com.framework.utils.saucedemo.SauceDemoConstants;
import com.framework.utils.saucedemo.SauceDemoMessages;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        // Using baseUrl.2 - https://www.saucedemo.com
        driver.get(ConfigReader.getBaseUrl(2));
        waitForPageToLoad();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify SauceDemo login page loads successfully")
    public void testLoginPageLoads() {
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains(SauceDemoConstants.DOMAIN), 
            "Expected URL to contain '" + SauceDemoConstants.DOMAIN + "' but got: " + currentUrl);
        
        // Verify login elements are present using page object
        softAssert.assertTrue(loginPage.isUsernameFieldDisplayed(), SauceDemoMessages.USERNAME_FIELD_DISPLAYED);
        softAssert.assertTrue(loginPage.isPasswordFieldDisplayed(), SauceDemoMessages.PASSWORD_FIELD_DISPLAYED);
        softAssert.assertTrue(loginPage.isLoginButtonDisplayed(), SauceDemoMessages.LOGIN_BUTTON_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "regression"}, description = "Verify page title")
    public void testPageTitle() {
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, SauceDemoConstants.PAGE_TITLE, 
            SauceDemoMessages.PAGE_TITLE_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"smoke", "functional", "regression"}, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        // Login using page object
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        // Verify we're on the inventory page after login
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains(SauceDemoConstants.INVENTORY_URL), 
            SauceDemoMessages.LOGIN_SUCCESSFUL);
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"smoke", "regression"}, description = "Verify login elements are enabled")
    public void testLoginElementsEnabled() {
        softAssert.assertTrue(loginPage.isUsernameFieldEnabled(), SauceDemoMessages.USERNAME_FIELD_ENABLED);
        softAssert.assertTrue(loginPage.isPasswordFieldEnabled(), SauceDemoMessages.PASSWORD_FIELD_ENABLED);
        softAssert.assertTrue(loginPage.isLoginButtonEnabled(), SauceDemoMessages.LOGIN_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"regression"}, description = "Verify username field accepts input")
    public void testUsernameFieldAcceptsInput() {
        loginPage.enterUsername("test_user");
        String enteredValue = loginPage.getUsernameFieldValue();
        
        softAssert.assertEquals(enteredValue, "test_user", SauceDemoMessages.USERNAME_ACCEPTED);
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"regression"}, description = "Verify password field accepts input")
    public void testPasswordFieldAcceptsInput() {
        loginPage.enterPassword("test_password");
        String enteredValue = loginPage.getPasswordFieldValue();
        
        softAssert.assertEquals(enteredValue, "test_password", SauceDemoMessages.PASSWORD_ACCEPTED);
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify inventory page after successful login")
    public void testInventoryPageAfterLogin() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), SauceDemoMessages.INVENTORY_TITLE_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getPageTitle(), SauceDemoConstants.PRODUCTS_TITLE, 
            SauceDemoMessages.INVENTORY_TITLE_CORRECT);
        softAssert.assertTrue(inventoryPage.isAppLogoDisplayed(), SauceDemoMessages.APP_LOGO_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"functional", "regression"}, description = "Verify inventory page elements")
    public void testInventoryPageElements() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(inventoryPage.isMenuButtonDisplayed(), SauceDemoMessages.MENU_BUTTON_DISPLAYED);
        softAssert.assertTrue(inventoryPage.isShoppingCartDisplayed(), SauceDemoMessages.SHOPPING_CART_DISPLAYED);
        softAssert.assertTrue(inventoryPage.isSortDropdownDisplayed(), SauceDemoMessages.SORT_DROPDOWN_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 9, groups = {"regression"}, description = "Verify inventory items are displayed")
    public void testInventoryItemsDisplayed() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        int itemCount = inventoryPage.getInventoryItemCount();
        softAssert.assertTrue(itemCount > 0, SauceDemoMessages.INVENTORY_ITEMS_DISPLAYED);
        softAssert.assertEquals(itemCount, SauceDemoConstants.PRODUCT_COUNT, 
            SauceDemoMessages.INVENTORY_COUNT_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"regression"}, description = "Verify product names are displayed")
    public void testProductNamesDisplayed() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        var productNames = inventoryPage.getAllProductNames();
        softAssert.assertFalse(productNames.isEmpty(), SauceDemoMessages.PRODUCT_NAMES_DISPLAYED);
        softAssert.assertEquals(productNames.size(), SauceDemoConstants.PRODUCT_COUNT, 
            SauceDemoMessages.PRODUCT_NAMES_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"regression"}, description = "Verify login button text")
    public void testLoginButtonText() {
        String buttonText = loginPage.getLoginButtonText();
        softAssert.assertEquals(buttonText, SauceDemoConstants.LOGIN_BUTTON_TEXT, 
            SauceDemoMessages.LOGIN_BUTTON_TEXT_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"regression"}, description = "Verify clear username field")
    public void testClearUsernameField() {
        loginPage.enterUsername("test_user");
        loginPage.clearUsername();
        
        String value = loginPage.getUsernameFieldValue();
        softAssert.assertTrue(value.isEmpty(), SauceDemoMessages.USERNAME_CLEARED);
        
        softAssert.assertAll();
    }

    @Test(priority = 13, groups = {"regression"}, description = "Verify clear password field")
    public void testClearPasswordField() {
        loginPage.enterPassword("test_password");
        loginPage.clearPassword();
        
        String value = loginPage.getPasswordFieldValue();
        softAssert.assertTrue(value.isEmpty(), SauceDemoMessages.PASSWORD_CLEARED);
        
        softAssert.assertAll();
    }
}
