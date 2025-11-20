package com.framework.tests.rahulshetty;

import com.framework.pages.HomePage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowSwitchTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() throws InterruptedException {
        // Using baseUrl.1 - Rahul Shetty Academy AutomationPractice
        driver.get(ConfigReader.getBaseUrl(1));
        Thread.sleep(2000);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify Open Window button is displayed and enabled")
    public void testOpenWindowButtonVisibilityAndState() {
        softAssert.assertTrue(homePage.isOpenWindowButtonDisplayed(), 
            "Open Window button should be displayed");
        softAssert.assertTrue(homePage.isOpenWindowButtonEnabled(), 
            "Open Window button should be enabled");
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "functional", "regression"}, description = "Verify clicking Open Window button opens new window")
    public void testOpenWindowButtonOpensNewWindow() {
        // Get the current window handle
        String parentWindowHandle = driver.getWindowHandle();
        
        // Click the Open Window button
        homePage.clickOpenWindowButton();
        
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        
        softAssert.assertEquals(windowHandles.size(), 2, 
            "There should be 2 windows after clicking Open Window button");
        softAssert.assertTrue(windowHandles.contains(parentWindowHandle), 
            "Parent window handle should still exist");
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify new window opens with correct URL")
    public void testNewWindowOpensCorrectUrl() {
        // Get parent window handle
        String parentWindowHandle = driver.getWindowHandle();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        // Switch to the new window
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        
        // Wait for new window to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify URL
        String actualUrl = driver.getCurrentUrl();
        softAssert.assertEquals(actualUrl, "https://www.qaclickacademy.com/", 
            "New window should open with QA Click Academy URL");
        
        // Close new window and switch back to parent
        driver.close();
        driver.switchTo().window(parentWindowHandle);
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify new window title")
    public void testNewWindowTitle() {
        // Get parent window handle
        String parentWindowHandle = driver.getWindowHandle();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        // Switch to new window
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        
        // Wait for page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify title contains expected text
        String actualTitle = driver.getTitle();
        softAssert.assertFalse(actualTitle.isEmpty(), 
            "New window should have a title");
        
        // Close new window and switch back
        driver.close();
        driver.switchTo().window(parentWindowHandle);
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify switching back to parent window")
    public void testSwitchBackToParentWindow() {
        // Get parent window handle and URL
        String parentWindowHandle = driver.getWindowHandle();
        String parentUrl = driver.getCurrentUrl();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        // Switch to new window
        String childWindowHandle = null;
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                childWindowHandle = handle;
                driver.switchTo().window(handle);
                break;
            }
        }
        
        // Wait for new window
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Switch back to parent window
        driver.switchTo().window(parentWindowHandle);
        
        // Verify we're back on the parent window
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, parentUrl, 
            "Should be back on the parent window");
        
        // Close child window
        driver.switchTo().window(childWindowHandle);
        driver.close();
        driver.switchTo().window(parentWindowHandle);
        
        softAssert.assertAll();
    }

    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify parent window remains interactive after opening child window")
    public void testParentWindowInteractiveAfterOpeningChild() {
        // Get parent window handle
        String parentWindowHandle = driver.getWindowHandle();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        // Switch to new window
        String childWindowHandle = null;
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                childWindowHandle = handle;
                driver.switchTo().window(handle);
                break;
            }
        }
        
        // Switch back to parent
        driver.switchTo().window(parentWindowHandle);
        
        // Verify parent window is still interactive
        softAssert.assertTrue(homePage.isOpenWindowButtonDisplayed(), 
            "Open Window button should still be displayed in parent window");
        softAssert.assertTrue(homePage.isOpenWindowButtonEnabled(), 
            "Open Window button should still be enabled in parent window");
        
        // Clean up - close child window
        driver.switchTo().window(childWindowHandle);
        driver.close();
        driver.switchTo().window(parentWindowHandle);
        
        softAssert.assertAll();
    }

    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify closing child window doesn't affect parent window")
    public void testClosingChildWindowDoesNotAffectParent() {
        // Get parent window handle
        String parentWindowHandle = driver.getWindowHandle();
        String parentUrl = driver.getCurrentUrl();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        // Switch to new window
        String childWindowHandle = null;
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                childWindowHandle = handle;
                driver.switchTo().window(handle);
                break;
            }
        }
        
        // Close child window
        driver.close();
        
        // Switch back to parent
        driver.switchTo().window(parentWindowHandle);
        
        // Verify parent window is still functional
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, parentUrl, 
            "Parent window should still be at the same URL");
        softAssert.assertTrue(homePage.isOpenWindowButtonDisplayed(), 
            "Parent window elements should still be accessible");
        
        // Verify only one window exists now
        Set<String> remainingWindows = driver.getWindowHandles();
        softAssert.assertEquals(remainingWindows.size(), 1, 
            "Only parent window should remain after closing child");
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify clicking Open Window button multiple times reuses same window")
    public void testMultipleWindowOpening() {
        // Get parent window handle
        String parentWindowHandle = driver.getWindowHandle();
        
        // Click Open Window button first time
        homePage.clickOpenWindowButton();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Get the child window handle
        Set<String> windowHandles = driver.getWindowHandles();
        String firstChildWindowHandle = null;
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                firstChildWindowHandle = handle;
                break;
            }
        }
        
        // Switch back to parent
        driver.switchTo().window(parentWindowHandle);
        
        // Click Open Window button second time
        homePage.clickOpenWindowButton();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Check number of windows - should still be 2 (button reuses same window)
        Set<String> windowHandlesAfterSecondClick = driver.getWindowHandles();
        softAssert.assertEquals(windowHandlesAfterSecondClick.size(), 2, 
            "There should be 2 windows (button reuses the same child window)");
        
        // Verify the child window handle is the same
        String secondChildWindowHandle = null;
        for (String handle : windowHandlesAfterSecondClick) {
            if (!handle.equals(parentWindowHandle)) {
                secondChildWindowHandle = handle;
                break;
            }
        }
        
        softAssert.assertEquals(secondChildWindowHandle, firstChildWindowHandle,
            "Clicking Open Window button multiple times should reuse the same window");
        
        // Close child window
        driver.switchTo().window(secondChildWindowHandle);
        driver.close();
        driver.switchTo().window(parentWindowHandle);
        
        softAssert.assertAll();
    }

    @Test(priority = 9, groups = {"functional", "regression"}, description = "Verify window handles are unique")
    public void testWindowHandlesAreUnique() {
        // Get parent window handle
        String parentWindowHandle = driver.getWindowHandle();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        
        softAssert.assertEquals(windowHandles.size(), 2, 
            "Should have 2 unique window handles");
        
        // Get child window handle
        String childWindowHandle = null;
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                childWindowHandle = handle;
                break;
            }
        }
        
        softAssert.assertNotNull(childWindowHandle, 
            "Child window handle should not be null");
        softAssert.assertNotEquals(parentWindowHandle, childWindowHandle, 
            "Parent and child window handles should be different");
        
        // Clean up
        driver.switchTo().window(childWindowHandle);
        driver.close();
        driver.switchTo().window(parentWindowHandle);
        
        softAssert.assertAll();
    }

    @Test(priority = 10, groups = {"functional", "regression"}, description = "Verify new window has expected content")
    public void testNewWindowHasExpectedContent() {
        // Get parent window handle
        String parentWindowHandle = driver.getWindowHandle();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        // Switch to new window
        String childWindowHandle = null;
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                childWindowHandle = handle;
                driver.switchTo().window(handle);
                break;
            }
        }
        
        // Wait for page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify page contains expected content
        String pageSource = driver.getPageSource();
        softAssert.assertTrue(pageSource.contains("qaclickacademy") || 
                            pageSource.contains("QA Click Academy") ||
                            pageSource.contains("CLICK ACADEMY"), 
            "New window should contain QA Click Academy content");
        
        // Clean up
        driver.close();
        driver.switchTo().window(parentWindowHandle);
        
        softAssert.assertAll();
    }
}
