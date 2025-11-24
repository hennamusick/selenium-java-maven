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
 * SauceDemo Test Class
 * Contains tests for inventory page and product functionality
 */
@Epic("SauceDemo E-Commerce")
@Feature("Inventory and Products")
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
    @Description("Validate that the SauceDemo login page loads successfully with all required elements")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Page Loading")
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
    @Description("Validate that the login page has correct title 'Swag Labs'")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Page Loading")
    public void testPageTitle() {
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, SauceDemoConstants.PAGE_TITLE, 
            SauceDemoMessages.PAGE_TITLE_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"smoke", "functional", "regression"}, description = "Verify successful login with valid credentials")
    @Description("Validate that user can successfully login with valid credentials and reach inventory page")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Login Workflow")
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
    @Description("Validate that username field, password field, and login button are all enabled")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login Page Elements")
    public void testLoginElementsEnabled() {
        softAssert.assertTrue(loginPage.isUsernameFieldEnabled(), SauceDemoMessages.USERNAME_FIELD_ENABLED);
        softAssert.assertTrue(loginPage.isPasswordFieldEnabled(), SauceDemoMessages.PASSWORD_FIELD_ENABLED);
        softAssert.assertTrue(loginPage.isLoginButtonEnabled(), SauceDemoMessages.LOGIN_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"regression"}, description = "Verify username field accepts input")
    @Description("Validate that username field accepts and retains text input")
    @Severity(SeverityLevel.NORMAL)
    @Story("Input Field Validation")
    public void testUsernameFieldAcceptsInput() {
        loginPage.enterUsername("test_user");
        String enteredValue = loginPage.getUsernameFieldValue();
        
        softAssert.assertEquals(enteredValue, "test_user", SauceDemoMessages.USERNAME_ACCEPTED);
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"regression"}, description = "Verify password field accepts input")
    @Description("Validate that password field accepts and retains text input")
    @Severity(SeverityLevel.NORMAL)
    @Story("Input Field Validation")
    public void testPasswordFieldAcceptsInput() {
        loginPage.enterPassword("test_password");
        String enteredValue = loginPage.getPasswordFieldValue();
        
        softAssert.assertEquals(enteredValue, "test_password", SauceDemoMessages.PASSWORD_ACCEPTED);
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify inventory page after successful login")
    @Description("Validate that inventory page displays correctly after successful login with title and logo")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Inventory Page Display")
    public void testInventoryPageAfterLogin() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), SauceDemoMessages.INVENTORY_TITLE_DISPLAYED);
        softAssert.assertEquals(inventoryPage.getPageTitle(), SauceDemoConstants.PRODUCTS_TITLE, 
            SauceDemoMessages.INVENTORY_TITLE_CORRECT);
        softAssert.assertTrue(inventoryPage.isAppLogoDisplayed(), SauceDemoMessages.APP_LOGO_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"functional", "regression"}, description = "Verify inventory page elements")
    @Description("Validate that all key inventory page elements are displayed (menu, cart, sort dropdown)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Inventory Page Display")
    public void testInventoryPageElements() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(inventoryPage.isMenuButtonDisplayed(), SauceDemoMessages.MENU_BUTTON_DISPLAYED);
        softAssert.assertTrue(inventoryPage.isShoppingCartDisplayed(), SauceDemoMessages.SHOPPING_CART_DISPLAYED);
        softAssert.assertTrue(inventoryPage.isSortDropdownDisplayed(), SauceDemoMessages.SORT_DROPDOWN_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 9, groups = {"regression"}, description = "Verify inventory items are displayed")
    @Description("Validate that correct number of inventory items are displayed on the page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Display")
    public void testInventoryItemsDisplayed() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        int itemCount = inventoryPage.getInventoryItemCount();
        softAssert.assertTrue(itemCount > 0, SauceDemoMessages.INVENTORY_ITEMS_DISPLAYED);
        softAssert.assertEquals(itemCount, SauceDemoConstants.PRODUCT_COUNT, 
            SauceDemoMessages.INVENTORY_COUNT_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"regression"}, description = "Verify product names are displayed")
    @Description("Validate that all product names are displayed and count matches expected products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product Display")
    public void testProductNamesDisplayed() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        var productNames = inventoryPage.getAllProductNames();
        softAssert.assertFalse(productNames.isEmpty(), SauceDemoMessages.PRODUCT_NAMES_DISPLAYED);
        softAssert.assertEquals(productNames.size(), SauceDemoConstants.PRODUCT_COUNT, 
            SauceDemoMessages.PRODUCT_NAMES_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"regression"}, description = "Verify login button text")
    @Description("Validate that the login button displays correct text 'Login'")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login Page Elements")
    public void testLoginButtonText() {
        String buttonText = loginPage.getLoginButtonText();
        softAssert.assertEquals(buttonText, SauceDemoConstants.LOGIN_BUTTON_TEXT, 
            SauceDemoMessages.LOGIN_BUTTON_TEXT_CORRECT);
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"regression"}, description = "Verify clear username field")
    @Description("Validate that username field can be cleared after entering text")
    @Severity(SeverityLevel.NORMAL)
    @Story("Input Field Validation")
    public void testClearUsernameField() {
        loginPage.enterUsername("test_user");
        loginPage.clearUsername();
        
        String value = loginPage.getUsernameFieldValue();
        softAssert.assertTrue(value.isEmpty(), SauceDemoMessages.USERNAME_CLEARED);
        
        softAssert.assertAll();
    }

    @Test(priority = 13, groups = {"regression"}, description = "Verify clear password field")
    @Description("Validate that password field can be cleared after entering text")
    @Severity(SeverityLevel.NORMAL)
    @Story("Input Field Validation")
    public void testClearPasswordField() {
        loginPage.enterPassword("test_password");
        loginPage.clearPassword();
        
        String value = loginPage.getPasswordFieldValue();
        softAssert.assertTrue(value.isEmpty(), SauceDemoMessages.PASSWORD_CLEARED);
        
        softAssert.assertAll();
    }
}
