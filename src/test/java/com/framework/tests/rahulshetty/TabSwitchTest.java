package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.TestConstants;
import com.framework.utils.TestMessages;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Set;

public class TabSwitchTest extends BaseTest {
    
    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }
    
    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify Open Tab button is visible and enabled")
    public void testOpenTabButtonVisibilityAndState() {
        softAssert.assertTrue(homePage.isOpenTabButtonDisplayed(), 
            TestMessages.OPEN_TAB_BUTTON_DISPLAYED);
        softAssert.assertTrue(homePage.isOpenTabButtonEnabled(), 
            TestMessages.OPEN_TAB_BUTTON_ENABLED);
    }
    
    @Test(priority = 2, groups = {"smoke", "functional", "regression"}, description = "Verify clicking Open Tab button opens new tab")
    public void testOpenTabButtonOpensNewTab() {
        String originalTab = getParentWindowHandle();
        int initialTabCount = driver.getWindowHandles().size();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        int finalTabCount = driver.getWindowHandles().size();
        softAssert.assertEquals(finalTabCount, initialTabCount + 1, 
            TestMessages.OPEN_TAB_OPENS_ONE_NEW_TAB);
        
        // Cleanup - close new tab and switch back
        closeAllChildWindowsAndSwitchToParent(originalTab);
    }
    
    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify new tab opens with correct URL")
    public void testNewTabOpensCorrectUrl() {
        String originalTab = getParentWindowHandle();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        // Switch to new tab
        switchToChildWindow(originalTab);
        waitForPageToLoad();
        
        String newTabUrl = driver.getCurrentUrl();
        softAssert.assertEquals(newTabUrl, TestConstants.QA_CLICK_ACADEMY_URL, 
            TestMessages.NEW_TAB_OPENS_CORRECT_URL);
        
        // Cleanup
        closeCurrentWindowAndSwitchTo(originalTab);
    }
    
    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify new tab has expected title")
    public void testNewTabTitle() {
        String originalTab = getParentWindowHandle();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        // Switch to new tab
        switchToChildWindow(originalTab);
        
        waitForPageToLoad();
        String newTabTitle = driver.getTitle();
        softAssert.assertFalse(newTabTitle.isEmpty(), 
            TestMessages.NEW_TAB_HAS_TITLE);
        softAssert.assertTrue(newTabTitle.length() > 0, 
            TestMessages.TAB_TITLE_NOT_EMPTY);
        
        // Cleanup
        closeCurrentWindowAndSwitchTo(originalTab);
    }
    
    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify switching back to parent tab")
    public void testSwitchBackToParentTab() {
        String originalTab = driver.getWindowHandle();
        String originalUrl = driver.getCurrentUrl();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        // Switch to new tab
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        
        waitForPageToLoad();
        
        // Switch back to original tab
        driver.switchTo().window(originalTab);
        waitForPageToLoad();
        
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, originalUrl, 
            TestMessages.BACK_ON_ORIGINAL_TAB);
        softAssert.assertTrue(currentUrl.contains(TestConstants.AUTOMATION_PRACTICE_PAGE), 
            TestMessages.ORIGINAL_TAB_ON_PRACTICE_PAGE);
        
        // Cleanup - close new tab
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalTab);
    }
    
    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify parent tab remains interactive after opening new tab")
    public void testParentTabInteractiveAfterOpeningChild() {
        String originalTab = getParentWindowHandle();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        // Switch back to parent tab
        driver.switchTo().window(originalTab);
        waitForPageToLoad();
        
        // Verify parent tab is still interactive
        softAssert.assertTrue(homePage.isOpenTabButtonDisplayed(), 
            TestMessages.OPEN_TAB_BUTTON_VISIBLE_ON_PARENT);
        softAssert.assertTrue(homePage.isRadio1Displayed(), 
            TestMessages.PARENT_TAB_ELEMENTS_INTERACTIVE);
        
        // Cleanup
        closeAllChildWindowsAndSwitchToParent(originalTab);
    }
    
    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify closing child tab does not affect parent tab")
    public void testClosingChildTabDoesNotAffectParent() {
        String originalTab = getParentWindowHandle();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        // Switch to new tab and close it
        switchToChildWindow(originalTab);
        closeCurrentWindowAndSwitchTo(originalTab);
        waitForPageToLoad();
        
        // Verify parent tab is still functional
        softAssert.assertTrue(homePage.isOpenTabButtonDisplayed(), 
            TestMessages.PARENT_TAB_FUNCTIONAL_AFTER_CLOSE);
        int tabCount = driver.getWindowHandles().size();
        softAssert.assertEquals(tabCount, TestConstants.SINGLE_WINDOW, 
            TestMessages.ONLY_PARENT_TAB_REMAINS);
    }
    
    @Test(priority = 8, groups = {"functional", "regression"}, description = "Verify opening multiple tabs sequentially")
    public void testMultipleTabOpening() {
        String originalTab = getParentWindowHandle();
        
        // Open first tab
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        int tabCountAfterFirst = driver.getWindowHandles().size();
        softAssert.assertEquals(tabCountAfterFirst, 2, 
            TestMessages.SHOULD_HAVE_N_TABS_AFTER_FIRST);
        
        // Switch back to parent and open another tab
        driver.switchTo().window(originalTab);
        waitForPageToLoad();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(3);
        
        int tabCountAfterSecond = driver.getWindowHandles().size();
        softAssert.assertEquals(tabCountAfterSecond, 3, 
            TestMessages.SHOULD_HAVE_N_TABS_AFTER_SECOND);
        
        // Cleanup - close all child tabs
        closeAllChildWindowsAndSwitchToParent(originalTab);
    }
    
    @Test(priority = 9, groups = {"functional", "regression"}, description = "Verify tab handles are unique")
    public void testTabHandlesAreUnique() {
        String originalTab = getParentWindowHandle();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        Set<String> allHandles = driver.getWindowHandles();
        softAssert.assertEquals(allHandles.size(), 2, 
            TestMessages.SHOULD_HAVE_UNIQUE_TAB_HANDLES);
        
        // Verify handles are different
        ArrayList<String> handleList = new ArrayList<>(allHandles);
        softAssert.assertNotEquals(handleList.get(0), handleList.get(1), 
            TestMessages.TAB_HANDLES_SHOULD_BE_UNIQUE);
        
        // Cleanup
        closeAllChildWindowsAndSwitchToParent(originalTab);
    }
    
    @Test(priority = 10, groups = {"functional", "regression"}, description = "Verify new tab has expected content")
    public void testNewTabHasExpectedContent() {
        String originalTab = getParentWindowHandle();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        // Switch to new tab
        switchToChildWindow(originalTab);
        waitForPageToLoad();
        
        String pageSource = driver.getPageSource();
        softAssert.assertFalse(pageSource.isEmpty(), 
            TestMessages.NEW_TAB_HAS_PAGE_CONTENT);
        softAssert.assertTrue(pageSource.length() > TestConstants.MIN_PAGE_CONTENT_LENGTH, 
            TestMessages.NEW_TAB_HAS_SUBSTANTIAL_CONTENT);
        
        // Cleanup
        closeCurrentWindowAndSwitchTo(originalTab);
    }
    
    @Test(priority = 11, groups = {"functional", "regression"}, description = "Verify switching between multiple tabs")
    public void testSwitchingBetweenMultipleTabs() {
        String originalTab = driver.getWindowHandle();
        String originalUrl = driver.getCurrentUrl();
        
        // Open first tab
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        // Get first child tab handle
        String firstChildTab = "";
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                firstChildTab = handle;
                break;
            }
        }
        
        // Switch back and open second tab
        driver.switchTo().window(originalTab);
        waitForPageToLoad();
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(3);
        
        // Get second child tab handle
        String secondChildTab = "";
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab) && !handle.equals(firstChildTab)) {
                secondChildTab = handle;
                break;
            }
        }
        
        // Switch to first child tab
        driver.switchTo().window(firstChildTab);
        waitForPageToLoad();
        String firstTabUrl = driver.getCurrentUrl();
        softAssert.assertEquals(firstTabUrl, TestConstants.QA_CLICK_ACADEMY_URL, 
            TestMessages.FIRST_CHILD_TAB_IS_QA_ACADEMY);
        
        // Switch to second child tab
        driver.switchTo().window(secondChildTab);
        waitForPageToLoad();
        String secondTabUrl = driver.getCurrentUrl();
        softAssert.assertEquals(secondTabUrl, TestConstants.QA_CLICK_ACADEMY_URL, 
            TestMessages.SECOND_CHILD_TAB_IS_QA_ACADEMY);
        
        // Switch back to parent tab
        driver.switchTo().window(originalTab);
        waitForPageToLoad();
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertEquals(currentUrl, originalUrl, 
            TestMessages.ABLE_TO_SWITCH_BACK_TO_PARENT);
        
        // Cleanup
        closeAllChildWindowsAndSwitchToParent(originalTab);
    }
    
    @Test(priority = 12, groups = {"functional", "regression"}, description = "Verify tab count after closing one of multiple tabs")
    public void testTabCountAfterClosingOneTab() {
        String originalTab = getParentWindowHandle();
        
        // Open two tabs
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(2);
        
        driver.switchTo().window(originalTab);
        waitForPageToLoad();
        
        homePage.clickOpenTabButton();
        waitForNumberOfWindows(3);
        
        softAssert.assertEquals(driver.getWindowHandles().size(), 3, 
            TestMessages.SHOULD_HAVE_THREE_TABS_OPEN);
        
        // Close one child tab
        switchToChildWindow(originalTab);
        closeCurrentWindowAndSwitchTo(originalTab);
        
        int remainingTabs = driver.getWindowHandles().size();
        softAssert.assertEquals(remainingTabs, 2, 
            TestMessages.SHOULD_HAVE_TWO_TABS_AFTER_CLOSING);
        
        // Cleanup
        closeAllChildWindowsAndSwitchToParent(originalTab);
    }
}
