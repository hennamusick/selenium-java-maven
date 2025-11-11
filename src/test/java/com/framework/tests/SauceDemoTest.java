package com.framework.tests;

import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() throws InterruptedException {
        // Using baseUrl.2 - https://www.saucedemo.com
        driver.get(ConfigReader.getBaseUrl(2));
        Thread.sleep(2000); // Wait for page to load
    }

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify SauceDemo login page loads successfully")
    public void testLoginPageLoads() {
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("saucedemo.com"), 
            "Expected URL to contain 'saucedemo.com' but got: " + currentUrl);
        
        // Verify login elements are present
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        
        softAssert.assertTrue(usernameField.isDisplayed(), "Username field should be visible");
        softAssert.assertTrue(passwordField.isDisplayed(), "Password field should be visible");
        softAssert.assertTrue(loginButton.isDisplayed(), "Login button should be visible");
        
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
        // Standard user credentials from SauceDemo
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        // Verify we're on the inventory page after login
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("inventory.html"), 
            "Expected to be redirected to inventory page after successful login");
        
        softAssert.assertAll();
    }
}
