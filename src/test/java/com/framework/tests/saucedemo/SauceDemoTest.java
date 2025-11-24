package com.framework.tests.saucedemo;

import com.framework.pages.saucedemo.InventoryPage;
import com.framework.pages.saucedemo.LoginPage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import com.framework.utils.TestConstants;
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
        softAssert.assertTrue(currentUrl.contains(TestConstants.SAUCEDEMO_DOMAIN), 
            "Expected URL to contain '" + TestConstants.SAUCEDEMO_DOMAIN + "' but got: " + currentUrl);
        
        // Verify login elements are present using page object
        softAssert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be visible");
        softAssert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be visible");
        softAssert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible");
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "regression"}, description = "Verify page title")
    public void testPageTitle() {
        String pageTitle = driver.getTitle();
        softAssert.assertEquals(pageTitle, "Swag Labs", 
            "Expected page title to be 'Swag Labs'");
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"smoke", "functional", "regression"}, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        // Login using page object
        loginPage.login("standard_user", "secret_sauce");
        
        // Verify we're on the inventory page after login
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("inventory.html"), 
            "Expected to be redirected to inventory page after successful login");
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"smoke", "regression"}, description = "Verify login elements are enabled")
    public void testLoginElementsEnabled() {
        softAssert.assertTrue(loginPage.isUsernameFieldEnabled(), "Username field should be enabled");
        softAssert.assertTrue(loginPage.isPasswordFieldEnabled(), "Password field should be enabled");
        softAssert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled");
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"regression"}, description = "Verify username field accepts input")
    public void testUsernameFieldAcceptsInput() {
        loginPage.enterUsername("test_user");
        String enteredValue = loginPage.getUsernameFieldValue();
        
        softAssert.assertEquals(enteredValue, "test_user", "Username field should contain entered value");
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"regression"}, description = "Verify password field accepts input")
    public void testPasswordFieldAcceptsInput() {
        loginPage.enterPassword("test_password");
        String enteredValue = loginPage.getPasswordFieldValue();
        
        softAssert.assertEquals(enteredValue, "test_password", "Password field should contain entered value");
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify inventory page after successful login")
    public void testInventoryPageAfterLogin() {
        loginPage.login("standard_user", "secret_sauce");
        
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), "Page title should be displayed");
        softAssert.assertEquals(inventoryPage.getPageTitle(), "Products", "Page title should be 'Products'");
        softAssert.assertTrue(inventoryPage.isAppLogoDisplayed(), "App logo should be displayed");
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"functional", "regression"}, description = "Verify inventory page elements")
    public void testInventoryPageElements() {
        loginPage.login("standard_user", "secret_sauce");
        
        softAssert.assertTrue(inventoryPage.isMenuButtonDisplayed(), "Menu button should be displayed");
        softAssert.assertTrue(inventoryPage.isShoppingCartDisplayed(), "Shopping cart should be displayed");
        softAssert.assertTrue(inventoryPage.isSortDropdownDisplayed(), "Sort dropdown should be displayed");
        
        softAssert.assertAll();
    }

    @Test(priority = 9, groups = {"regression"}, description = "Verify inventory items are displayed")
    public void testInventoryItemsDisplayed() {
        loginPage.login("standard_user", "secret_sauce");
        
        int itemCount = inventoryPage.getInventoryItemCount();
        softAssert.assertTrue(itemCount > 0, "Should have at least one inventory item");
        softAssert.assertEquals(itemCount, 6, "Should have 6 inventory items");
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"regression"}, description = "Verify product names are displayed")
    public void testProductNamesDisplayed() {
        loginPage.login("standard_user", "secret_sauce");
        
        var productNames = inventoryPage.getAllProductNames();
        softAssert.assertFalse(productNames.isEmpty(), "Should have product names");
        softAssert.assertEquals(productNames.size(), 6, "Should have 6 product names");
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"regression"}, description = "Verify login button text")
    public void testLoginButtonText() {
        String buttonText = loginPage.getLoginButtonText();
        softAssert.assertEquals(buttonText, "Login", "Login button should have text 'Login'");
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"regression"}, description = "Verify clear username field")
    public void testClearUsernameField() {
        loginPage.enterUsername("test_user");
        loginPage.clearUsername();
        
        String value = loginPage.getUsernameFieldValue();
        softAssert.assertTrue(value.isEmpty(), "Username field should be empty after clear");
        
        softAssert.assertAll();
    }

    @Test(priority = 13, groups = {"regression"}, description = "Verify clear password field")
    public void testClearPasswordField() {
        loginPage.enterPassword("test_password");
        loginPage.clearPassword();
        
        String value = loginPage.getPasswordFieldValue();
        softAssert.assertTrue(value.isEmpty(), "Password field should be empty after clear");
        
        softAssert.assertAll();
    }
}
