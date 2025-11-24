package com.framework.utils;

import com.framework.pages.rahulshetty.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.getDriver();
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    /**
     * Wait for page to load completely using FluentWait
     * Replaces Thread.sleep() for page load scenarios
     */
    protected void waitForPageToLoad() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState")
                .equals("complete"));
    }

    /**
     * Wait for a specific number of windows
     * Useful in window switching scenarios
     */
    protected void waitForNumberOfWindows(int expectedWindows) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        
        wait.until(d -> d.getWindowHandles().size() == expectedWindows);
    }
    
    /**
     * Initialize HomePage with specified base URL index
     * Common setup for most test classes
     * 
     * @param baseUrlIndex The index of the base URL from config.properties
     *                     0 = testautomationpractice.blogspot.com
     *                     1 = rahulshettyacademy.com/AutomationPractice
     *                     2 = saucedemo.com
     *                     3 = the-internet.herokuapp.com
     */
    protected void initializeHomePage(int baseUrlIndex) {
        driver.get(ConfigReader.getBaseUrl(baseUrlIndex));
        waitForPageToLoad();
        homePage = new HomePage(driver);
    }
    
    /**
     * Initialize HomePage with default URL (index 1 - Rahul Shetty Academy)
     * Most tests use this URL
     */
    protected void initializeHomePage() {
        initializeHomePage(1); // Default to baseUrl.1
    }
    
    // ==================== Window/Tab Management Utilities ====================
    
    /**
     * Get the parent (first opened) window handle
     * Useful for storing the original window before opening new windows/tabs
     * 
     * @return The parent window handle
     */
    protected String getParentWindowHandle() {
        return driver.getWindowHandle();
    }
    
    /**
     * Close current window/tab and switch to the specified window handle
     * 
     * @param targetHandle The window handle to switch to after closing current window
     */
    protected void closeCurrentWindowAndSwitchTo(String targetHandle) {
        driver.close();
        driver.switchTo().window(targetHandle);
    }
    
    /**
     * Close all child windows/tabs and switch back to parent window
     * Useful for cleanup after tests that open multiple windows/tabs
     * 
     * @param parentHandle The parent window handle to switch back to
     */
    protected void closeAllChildWindowsAndSwitchToParent(String parentHandle) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(parentHandle);
    }
    
    /**
     * Switch to child window (the first non-parent window)
     * 
     * @param parentHandle The parent window handle to exclude
     * @return The child window handle that was switched to, or null if no child window found
     */
    protected String switchToChildWindow(String parentHandle) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                return handle;
            }
        }
        return null;
    }
}