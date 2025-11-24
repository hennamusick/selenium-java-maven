package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.TestConstants;
import com.framework.utils.TestMessages;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowSwitchTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify Open Window button is displayed and enabled")
    public void testOpenWindowButtonVisibilityAndState() {
        softAssert.assertTrue(homePage.isOpenWindowButtonDisplayed(), 
            TestMessages.OPEN_WINDOW_BUTTON_DISPLAYED);
        softAssert.assertTrue(homePage.isOpenWindowButtonEnabled(), 
            TestMessages.OPEN_WINDOW_BUTTON_ENABLED);
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"smoke", "functional", "regression"}, description = "Verify clicking Open Window button opens new window")
    public void testOpenWindowButtonOpensNewWindow() {
        // Get the current window handle
        String parentWindowHandle = getParentWindowHandle();
        
        // Click the Open Window button
        homePage.clickOpenWindowButton();
        
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        
        softAssert.assertEquals(windowHandles.size(), 2, 
            TestMessages.TWO_WINDOWS_AFTER_OPENING);
        softAssert.assertTrue(windowHandles.contains(parentWindowHandle), 
            TestMessages.PARENT_WINDOW_HANDLE_EXISTS);
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify new window opens with correct URL")
    public void testNewWindowOpensCorrectUrl() {
        // Get parent window handle
        String parentWindowHandle = getParentWindowHandle();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        waitForNumberOfWindows(2);
        
        // Switch to the new window
        switchToChildWindow(parentWindowHandle);
        waitForPageToLoad();
        
        // Verify URL
        String actualUrl = driver.getCurrentUrl();
        softAssert.assertEquals(actualUrl, TestConstants.QA_CLICK_ACADEMY_URL, 
            TestMessages.NEW_WINDOW_OPENS_CORRECT_URL);
        
        // Close new window and switch back to parent
        closeCurrentWindowAndSwitchTo(parentWindowHandle);
        
        softAssert.assertAll();
    }

    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify new window title")
    public void testNewWindowTitle() {
        // Get parent window handle
        String parentWindowHandle = getParentWindowHandle();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        // Switch to new window
        switchToChildWindow(parentWindowHandle);
        waitForPageToLoad();
        
        // Verify title contains expected text
        String actualTitle = driver.getTitle();
        softAssert.assertFalse(actualTitle.isEmpty(), 
            TestMessages.NEW_WINDOW_HAS_TITLE);
        
        // Close new window and switch back
        closeCurrentWindowAndSwitchTo(parentWindowHandle);
        
        softAssert.assertAll();
    }

    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify switching back to parent window")
    public void testSwitchBackToParentWindow() {
        // Get parent window handle and URL
        String parentWindowHandle = driver.getWindowHandle();
        String parentUrl = driver.getCurrentUrl();
        
        // Click Open Window button
        homePage.clickOpenWindowButton();
        
        // Switch to new window and get child window handle
        String childWindowHandle = switchToChildWindow(parentWindowHandle);
        
        // Wait for new window
        waitForPageToLoad();
        
        // Switch back to parent window
        driver.switchTo().window(parentWindowHandle);
        
        // Verify we're back on the parent window
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, parentUrl, 
            TestMessages.BACK_ON_PARENT_WINDOW);
        
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
        
        // Switch to new window and get child window handle
        String childWindowHandle = switchToChildWindow(parentWindowHandle);
        
        // Switch back to parent
        driver.switchTo().window(parentWindowHandle);
        
        // Verify parent window is still interactive
        softAssert.assertTrue(homePage.isOpenWindowButtonDisplayed(), 
            TestMessages.OPEN_WINDOW_BUTTON_VISIBLE_IN_PARENT);
        softAssert.assertTrue(homePage.isOpenWindowButtonEnabled(), 
            TestMessages.OPEN_WINDOW_BUTTON_ENABLED_IN_PARENT);
        
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
        switchToChildWindow(parentWindowHandle);
        
        // Close child window
        driver.close();
        
        // Switch back to parent
        driver.switchTo().window(parentWindowHandle);
        
        // Verify parent window is still functional
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, parentUrl, 
            TestMessages.PARENT_WINDOW_AT_SAME_URL);
        softAssert.assertTrue(homePage.isOpenWindowButtonDisplayed(), 
            TestMessages.PARENT_WINDOW_ELEMENTS_ACCESSIBLE);
        
        // Verify only one window exists now
        Set<String> remainingWindows = driver.getWindowHandles();
        softAssert.assertEquals(remainingWindows.size(), 1, 
            TestMessages.ONLY_PARENT_WINDOW_REMAINS);
        
        softAssert.assertAll();
    }

    @Test(priority = 8, groups = {"regression"}, description = "Verify clicking Open Window button multiple times reuses same window")
    public void testMultipleWindowOpening() {
        // Get parent window handle
        String parentWindowHandle = driver.getWindowHandle();
        
        // Click Open Window button first time
        homePage.clickOpenWindowButton();
        
        waitForNumberOfWindows(2);
        
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
        
        waitForPageToLoad();
        
        // Check number of windows - should still be 2 (button reuses same window)
        Set<String> windowHandlesAfterSecondClick = driver.getWindowHandles();
        softAssert.assertEquals(windowHandlesAfterSecondClick.size(), 2, 
            TestMessages.TWO_WINDOWS_BUTTON_REUSES);
        
        // Verify the child window handle is the same
        String secondChildWindowHandle = null;
        for (String handle : windowHandlesAfterSecondClick) {
            if (!handle.equals(parentWindowHandle)) {
                secondChildWindowHandle = handle;
                break;
            }
        }
        
        softAssert.assertEquals(secondChildWindowHandle, firstChildWindowHandle,
            TestMessages.BUTTON_REUSES_SAME_WINDOW);
        
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
        
        waitForNumberOfWindows(2);
        
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        
        softAssert.assertEquals(windowHandles.size(), 2, 
            TestMessages.TWO_UNIQUE_WINDOW_HANDLES);
        
        // Get child window handle
        String childWindowHandle = null;
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                childWindowHandle = handle;
                break;
            }
        }
        
        softAssert.assertNotNull(childWindowHandle, 
            TestMessages.CHILD_WINDOW_HANDLE_NOT_NULL);
        softAssert.assertNotEquals(parentWindowHandle, childWindowHandle, 
            TestMessages.WINDOW_HANDLES_DIFFERENT);
        
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
        switchToChildWindow(parentWindowHandle);
        
        // Wait for page to load
        waitForPageToLoad();
        
        // Verify page contains expected content
        String pageSource = driver.getPageSource();
        softAssert.assertTrue(pageSource.contains(TestConstants.QA_CLICK_ACADEMY_TEXT_LOWER) || 
                            pageSource.contains(TestConstants.QA_CLICK_ACADEMY_TEXT_MIXED) ||
                            pageSource.contains(TestConstants.QA_CLICK_ACADEMY_TEXT_UPPER), 
            TestMessages.NEW_WINDOW_HAS_QA_ACADEMY_CONTENT);
        
        // Clean up
        closeCurrentWindowAndSwitchTo(parentWindowHandle);
        
        softAssert.assertAll();
    }
}
