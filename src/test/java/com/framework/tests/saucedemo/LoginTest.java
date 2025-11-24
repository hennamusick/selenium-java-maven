package com.framework.tests.saucedemo;

import com.framework.pages.saucedemo.InventoryPage;
import com.framework.pages.saucedemo.LoginPage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import com.framework.utils.saucedemo.SauceDemoConstants;
import com.framework.utils.saucedemo.SauceDemoMessages;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Login Test Class for SauceDemo
 * Contains all login-related test scenarios including:
 * - Valid login tests for all user types
 * - Invalid credential tests
 * - Edge case and security tests
 */
public class LoginTest extends BaseTest {

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

    // ==================== LOGIN PAGE ELEMENT TESTS ====================

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

    @Test(priority = 3, groups = {"smoke", "regression"}, description = "Verify login elements are enabled")
    public void testLoginElementsEnabled() {
        softAssert.assertTrue(loginPage.isUsernameFieldEnabled(), SauceDemoMessages.USERNAME_FIELD_ENABLED);
        softAssert.assertTrue(loginPage.isPasswordFieldEnabled(), SauceDemoMessages.PASSWORD_FIELD_ENABLED);
        softAssert.assertTrue(loginPage.isLoginButtonEnabled(), SauceDemoMessages.LOGIN_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"regression"}, description = "Verify login button text")
    public void testLoginButtonText() {
        String buttonText = loginPage.getLoginButtonText();
        softAssert.assertEquals(buttonText, SauceDemoConstants.LOGIN_BUTTON_TEXT, 
            SauceDemoMessages.LOGIN_BUTTON_TEXT_CORRECT);
        
        softAssert.assertAll();
    }

    // ==================== INPUT FIELD TESTS ====================

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

    @Test(priority = 7, groups = {"regression"}, description = "Verify clear username field")
    public void testClearUsernameField() {
        loginPage.enterUsername("test_user");
        loginPage.clearUsername();
        
        String value = loginPage.getUsernameFieldValue();
        softAssert.assertTrue(value.isEmpty(), SauceDemoMessages.USERNAME_CLEARED);
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify clear password field")
    public void testClearPasswordField() {
        loginPage.enterPassword("test_password");
        loginPage.clearPassword();
        
        String value = loginPage.getPasswordFieldValue();
        softAssert.assertTrue(value.isEmpty(), SauceDemoMessages.PASSWORD_CLEARED);
        
        softAssert.assertAll();
    }

    // ==================== VALID LOGIN SCENARIOS - ALL USER TYPES ====================

    @Test(priority = 9, groups = {"smoke", "functional", "regression"}, description = "Verify successful login with standard user")
    public void testLoginWithStandardUser() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains(SauceDemoConstants.INVENTORY_URL), 
            SauceDemoMessages.LOGIN_SUCCESSFUL);
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), 
            SauceDemoMessages.INVENTORY_TITLE_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"functional", "regression"}, description = "Verify login fails with locked out user")
    public void testLoginWithLockedUser() {
        loginPage.login(SauceDemoConstants.LOCKED_USER, SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        softAssert.assertEquals(loginPage.getErrorMessageText(), SauceDemoConstants.ERROR_LOCKED_USER, 
            SauceDemoMessages.ERROR_MESSAGE_FOR_LOCKED_USER);
        
        // Verify still on login page
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertFalse(currentUrl.contains(SauceDemoConstants.INVENTORY_URL), 
            "Should remain on login page after failed login");
        
        softAssert.assertAll();
    }

    @Test(priority = 11, groups = {"functional", "regression"}, description = "Verify successful login with problem user")
    public void testLoginWithProblemUser() {
        loginPage.login(SauceDemoConstants.PROBLEM_USER, SauceDemoConstants.PASSWORD);
        
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains(SauceDemoConstants.INVENTORY_URL), 
            SauceDemoMessages.LOGIN_SUCCESSFUL);
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), 
            SauceDemoMessages.INVENTORY_TITLE_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 12, groups = {"functional", "regression"}, description = "Verify successful login with performance glitch user")
    public void testLoginWithPerformanceUser() {
        loginPage.login(SauceDemoConstants.PERFORMANCE_USER, SauceDemoConstants.PASSWORD);
        
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains(SauceDemoConstants.INVENTORY_URL), 
            SauceDemoMessages.LOGIN_SUCCESSFUL);
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), 
            SauceDemoMessages.INVENTORY_TITLE_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 13, groups = {"functional", "regression"}, description = "Verify successful login with error user")
    public void testLoginWithErrorUser() {
        loginPage.login(SauceDemoConstants.ERROR_USER, SauceDemoConstants.PASSWORD);
        
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains(SauceDemoConstants.INVENTORY_URL), 
            SauceDemoMessages.LOGIN_SUCCESSFUL);
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), 
            SauceDemoMessages.INVENTORY_TITLE_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 14, groups = {"functional", "regression"}, description = "Verify successful login with visual user")
    public void testLoginWithVisualUser() {
        loginPage.login(SauceDemoConstants.VISUAL_USER, SauceDemoConstants.PASSWORD);
        
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains(SauceDemoConstants.INVENTORY_URL), 
            SauceDemoMessages.LOGIN_SUCCESSFUL);
        softAssert.assertTrue(inventoryPage.isPageTitleDisplayed(), 
            SauceDemoMessages.INVENTORY_TITLE_DISPLAYED);
        
        softAssert.assertAll();
    }

    // ==================== INVALID CREDENTIALS TESTS ====================

    @Test(priority = 15, groups = {"functional", "regression"}, description = "Verify login fails with invalid username")
    public void testLoginWithInvalidUsername() {
        loginPage.login("invalid_user", SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        softAssert.assertEquals(loginPage.getErrorMessageText(), SauceDemoConstants.ERROR_INVALID_CREDENTIALS, 
            SauceDemoMessages.ERROR_MESSAGE_FOR_INVALID_CREDENTIALS);
        
        softAssert.assertAll();
    }

    @Test(priority = 16, groups = {"functional", "regression"}, description = "Verify login fails with invalid password")
    public void testLoginWithInvalidPassword() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, "wrong_password");
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        softAssert.assertEquals(loginPage.getErrorMessageText(), SauceDemoConstants.ERROR_INVALID_CREDENTIALS, 
            SauceDemoMessages.ERROR_MESSAGE_FOR_INVALID_CREDENTIALS);
        
        softAssert.assertAll();
    }

    @Test(priority = 17, groups = {"functional", "regression"}, description = "Verify login fails with empty username")
    public void testLoginWithEmptyUsername() {
        loginPage.login("", SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        softAssert.assertEquals(loginPage.getErrorMessageText(), SauceDemoConstants.ERROR_USERNAME_REQUIRED, 
            SauceDemoMessages.ERROR_MESSAGE_FOR_EMPTY_USERNAME);
        
        softAssert.assertAll();
    }

    @Test(priority = 18, groups = {"functional", "regression"}, description = "Verify login fails with empty password")
    public void testLoginWithEmptyPassword() {
        loginPage.login(SauceDemoConstants.STANDARD_USER, "");
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        softAssert.assertEquals(loginPage.getErrorMessageText(), SauceDemoConstants.ERROR_PASSWORD_REQUIRED, 
            SauceDemoMessages.ERROR_MESSAGE_FOR_EMPTY_PASSWORD);
        
        softAssert.assertAll();
    }

    @Test(priority = 19, groups = {"functional", "regression"}, description = "Verify login fails with both fields empty")
    public void testLoginWithEmptyCredentials() {
        loginPage.login("", "");
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        softAssert.assertEquals(loginPage.getErrorMessageText(), SauceDemoConstants.ERROR_USERNAME_REQUIRED, 
            SauceDemoMessages.ERROR_MESSAGE_FOR_EMPTY_USERNAME);
        
        softAssert.assertAll();
    }

    // ==================== EDGE CASE AND SECURITY TESTS ====================

    @Test(priority = 20, groups = {"regression"}, description = "Verify login fails with special characters in username")
    public void testLoginWithSpecialCharactersUsername() {
        loginPage.login("user@#$%", SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        softAssert.assertEquals(loginPage.getErrorMessageText(), SauceDemoConstants.ERROR_INVALID_CREDENTIALS, 
            SauceDemoMessages.ERROR_MESSAGE_FOR_INVALID_CREDENTIALS);
        
        softAssert.assertAll();
    }

    @Test(priority = 21, groups = {"regression"}, description = "Verify login fails with SQL injection attempt")
    public void testLoginWithSQLInjection() {
        loginPage.login("' OR '1'='1", "' OR '1'='1");
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 22, groups = {"regression"}, description = "Verify username is case sensitive")
    public void testLoginWithWrongCase() {
        loginPage.login("STANDARD_USER", SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        softAssert.assertEquals(loginPage.getErrorMessageText(), SauceDemoConstants.ERROR_INVALID_CREDENTIALS, 
            SauceDemoMessages.ERROR_MESSAGE_FOR_INVALID_CREDENTIALS);
        
        softAssert.assertAll();
    }

    @Test(priority = 23, groups = {"regression"}, description = "Verify login with extra spaces in username")
    public void testLoginWithSpacesInUsername() {
        loginPage.login(" " + SauceDemoConstants.STANDARD_USER + " ", SauceDemoConstants.PASSWORD);
        
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            SauceDemoMessages.ERROR_MESSAGE_DISPLAYED);
        
        softAssert.assertAll();
    }

    @Test(priority = 24, groups = {"regression"}, description = "Verify multiple failed login attempts")
    public void testMultipleFailedLoginAttempts() {
        // First attempt
        loginPage.login("invalid_user1", "wrong_pass1");
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should display after first failed attempt");
        
        // Second attempt
        loginPage.login("invalid_user2", "wrong_pass2");
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should display after second failed attempt");
        
        // Third attempt
        loginPage.login("invalid_user3", "wrong_pass3");
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should display after third failed attempt");
        
        softAssert.assertAll();
    }

    @Test(priority = 25, groups = {"regression"}, description = "Verify login after clearing invalid credentials")
    public void testLoginAfterClearingInvalidCredentials() {
        // Try with invalid credentials
        loginPage.login("invalid_user", "wrong_password");
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should be displayed for invalid credentials");
        
        // Clear and try with valid credentials
        loginPage.clearUsername();
        loginPage.clearPassword();
        loginPage.login(SauceDemoConstants.STANDARD_USER, SauceDemoConstants.PASSWORD);
        
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains(SauceDemoConstants.INVENTORY_URL), 
            SauceDemoMessages.LOGIN_SUCCESSFUL);
        
        softAssert.assertAll();
    }
}
