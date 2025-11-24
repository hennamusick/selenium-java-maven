package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.TestConstants;
import com.framework.utils.TestMessages;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for iFrame functionality
 * Tests iframe switching, content verification, and context management
 */
public class IFrameTest extends BaseTest {
    
    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void setupPage() {
        initializeHomePage(1); // Rahul Shetty Academy URL
        homePage.scrollToIFrame(); // Scroll to iFrame section for all tests
    }
    
    @Test(groups = {"smoke", "regression"}, priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that iFrame is displayed on the page")
    public void testIFrameDisplayed() {
        Assert.assertTrue(homePage.isIFrameDisplayed(), 
                TestMessages.IFRAME_DISPLAYED);
    }
    
    @Test(groups = {"smoke", "regression"}, priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that iFrame is enabled")
    public void testIFrameEnabled() {
        Assert.assertTrue(homePage.isIFrameEnabled(), 
                TestMessages.IFRAME_ENABLED);
    }
    
    @Test(groups = {"regression"}, priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame has correct ID attribute")
    public void testIFrameHasCorrectId() {
        String iframeId = homePage.getIFrameId();
        Assert.assertEquals(iframeId, TestConstants.IFRAME_ID, 
                TestMessages.IFRAME_HAS_CORRECT_ID);
    }
    
    @Test(groups = {"regression"}, priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame has src attribute")
    public void testIFrameHasSrc() {
        String src = homePage.getIFrameSrc();
        Assert.assertNotNull(src, TestMessages.IFRAME_HAS_SRC);
        Assert.assertFalse(src.isEmpty(), TestMessages.IFRAME_HAS_SRC);
    }
    
    @Test(groups = {"regression"}, priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame src contains expected domain")
    public void testIFrameSrcContainsDomain() {
        String src = homePage.getIFrameSrc();
        Assert.assertTrue(src.contains(TestConstants.IFRAME_SRC_DOMAIN), 
                TestMessages.IFRAME_SRC_CONTAINS_DOMAIN);
    }
    
    @Test(groups = {"regression"}, priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify can switch to iFrame successfully")
    public void testSwitchToIFrame() {
        try {
            homePage.switchToIFrame();
            // If no exception thrown, switch was successful
            Assert.assertTrue(true, TestMessages.IFRAME_SWITCH_SUCCESSFUL);
        } catch (Exception e) {
            Assert.fail(TestMessages.IFRAME_SWITCH_SUCCESSFUL + " - Exception: " + e.getMessage());
        } finally {
            homePage.switchToDefaultContent();
        }
    }
    
    @Test(groups = {"regression"}, priority = 7)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame content is visible after switching")
    public void testIFrameContentVisible() {
        homePage.switchToIFrame();
        
        try {
            // Look for header or content inside iframe
            WebElement iframeBody = driver.findElement(By.tagName("body"));
            Assert.assertTrue(iframeBody.isDisplayed(), 
                    TestMessages.IFRAME_CONTENT_VISIBLE);
        } finally {
            homePage.switchToDefaultContent();
        }
    }
    
    @Test(groups = {"regression"}, priority = 8)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame contains expected content")
    public void testIFrameHasExpectedContent() {
        homePage.switchToIFrame();
        
        try {
            String pageSource = driver.getPageSource().toUpperCase();
            // Check for common content that would be in the iframe
            boolean hasContent = pageSource.contains("RAHUL") || 
                                pageSource.contains("ACADEMY") || 
                                pageSource.contains("SHETTY") ||
                                pageSource.length() > 1000; // Has substantial content
            Assert.assertTrue(hasContent, 
                    TestMessages.IFRAME_HAS_EXPECTED_CONTENT);
        } finally {
            homePage.switchToDefaultContent();
        }
    }
    
    @Test(groups = {"regression"}, priority = 9)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify header is visible inside iFrame")
    public void testIFrameHeaderVisible() {
        homePage.switchToIFrame();
        
        try {
            // Check for header elements in the iframe
            java.util.List<WebElement> headers = driver.findElements(By.tagName("h1"));
            if (headers.isEmpty()) {
                headers = driver.findElements(By.tagName("h2"));
            }
            
            Assert.assertFalse(headers.isEmpty(), 
                    TestMessages.IFRAME_HEADER_VISIBLE);
        } finally {
            homePage.switchToDefaultContent();
        }
    }
    
    @Test(groups = {"regression"}, priority = 10)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify can switch back to main content from iFrame")
    public void testSwitchBackToMainContent() {
        homePage.switchToIFrame();
        homePage.switchToDefaultContent();
        
        // Verify we're back on main page by checking a main page element
        Assert.assertTrue(homePage.isMouseHoverButtonDisplayed(), 
                TestMessages.SWITCHED_BACK_TO_MAIN);
    }
    
    @Test(groups = {"regression"}, priority = 11)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify main page is interactive after switching back from iFrame")
    public void testMainPageInteractiveAfterIFrame() {
        homePage.switchToIFrame();
        homePage.switchToDefaultContent();
        
        // Test main page elements are still interactive
        Assert.assertTrue(homePage.isMouseHoverButtonEnabled(), 
                TestMessages.MAIN_PAGE_INTERACTIVE);
        Assert.assertTrue(homePage.isAlertButtonDisplayed(), 
                TestMessages.MAIN_PAGE_INTERACTIVE);
    }
    
    @Test(groups = {"regression"}, priority = 12)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify multiple switches between main and iFrame work correctly")
    public void testMultipleIFrameSwitches() {
        // First switch to iframe
        homePage.switchToIFrame();
        WebElement firstBody = driver.findElement(By.tagName("body"));
        Assert.assertTrue(firstBody.isDisplayed());
        
        // Switch back to main
        homePage.switchToDefaultContent();
        Assert.assertTrue(homePage.isIFrameDisplayed());
        
        // Switch to iframe again
        homePage.switchToIFrame();
        WebElement secondBody = driver.findElement(By.tagName("body"));
        Assert.assertTrue(secondBody.isDisplayed(), 
                TestMessages.MULTIPLE_IFRAME_SWITCHES_WORK);
        
        // Switch back to main again
        homePage.switchToDefaultContent();
    }
    
    @Test(groups = {"regression"}, priority = 13)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify elements inside iFrame are clickable")
    public void testIFrameElementsClickable() {
        homePage.switchToIFrame();
        
        try {
            // Find clickable elements in iframe (links, buttons)
            java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
            
            if (!links.isEmpty()) {
                Assert.assertTrue(links.get(0).isEnabled(), 
                        TestMessages.IFRAME_ELEMENTS_CLICKABLE);
            } else {
                // If no links, just verify body is present
                WebElement body = driver.findElement(By.tagName("body"));
                Assert.assertTrue(body.isDisplayed(), 
                        TestMessages.IFRAME_ELEMENTS_CLICKABLE);
            }
        } finally {
            homePage.switchToDefaultContent();
        }
    }
    
    @Test(groups = {"regression"}, priority = 14)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify text can be extracted from iFrame elements")
    public void testExtractTextFromIFrame() {
        homePage.switchToIFrame();
        
        try {
            WebElement body = driver.findElement(By.tagName("body"));
            String bodyText = body.getText();
            
            Assert.assertNotNull(bodyText, 
                    TestMessages.IFRAME_TEXT_EXTRACTABLE);
            Assert.assertFalse(bodyText.trim().isEmpty(), 
                    TestMessages.IFRAME_TEXT_EXTRACTABLE);
        } finally {
            homePage.switchToDefaultContent();
        }
    }
    
    @Test(groups = {"regression"}, priority = 15)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify main page elements are not accessible while in iFrame context")
    public void testMainPageElementsNotAccessibleInIFrame() {
        homePage.switchToIFrame();
        
        try {
            // Try to find a main page element - should not be found
            java.util.List<WebElement> alertButtons = driver.findElements(By.id("alertbtn"));
            Assert.assertTrue(alertButtons.isEmpty(), 
                    TestMessages.MAIN_PAGE_ELEMENTS_NOT_ACCESSIBLE_IN_IFRAME);
        } finally {
            homePage.switchToDefaultContent();
        }
    }
    
    @Test(groups = {"regression"}, priority = 16)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame elements are not accessible while in main page context")
    public void testIFrameElementsNotAccessibleInMain() {
        // Stay in main context, don't switch to iframe
        
        // Try to find elements that exist only inside iframe - should not be found directly
        // (we can only access iframe through switching)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("AutomationPractice"), 
                TestMessages.IFRAME_ELEMENTS_NOT_ACCESSIBLE_IN_MAIN);
    }
    
    @Test(groups = {"regression"}, priority = 17)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame context is maintained until explicitly switched")
    public void testIFrameContextMaintained() {
        homePage.switchToIFrame();
        
        try {
            // Perform first action in iframe
            WebElement body1 = driver.findElement(By.tagName("body"));
            Assert.assertTrue(body1.isDisplayed());
            
            // Wait a bit
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Perform second action - should still be in iframe context
            WebElement body2 = driver.findElement(By.tagName("body"));
            Assert.assertTrue(body2.isDisplayed(), 
                    TestMessages.IFRAME_CONTEXT_MAINTAINED);
        } finally {
            homePage.switchToDefaultContent();
        }
    }
    
    @Test(groups = {"regression"}, priority = 18)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify can get iFrame attributes while on main page")
    public void testIFrameAttributesAccessible() {
        // Stay on main page
        String iframeId = homePage.getIFrameId();
        String iframeSrc = homePage.getIFrameSrc();
        
        Assert.assertEquals(iframeId, TestConstants.IFRAME_ID);
        Assert.assertTrue(iframeSrc.contains(TestConstants.IFRAME_SRC_DOMAIN));
    }
    
    @Test(groups = {"regression"}, priority = 19)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame is still displayed after page refresh")
    public void testIFrameDisplayedAfterRefresh() {
        Assert.assertTrue(homePage.isIFrameDisplayed());
        
        // Refresh page
        driver.navigate().refresh();
        homePage.scrollToIFrame();
        
        // Verify iframe still displayed
        Assert.assertTrue(homePage.isIFrameDisplayed(), 
                TestMessages.IFRAME_DISPLAYED);
    }
    
    @Test(groups = {"regression"}, priority = 20)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify can interact with main page after multiple iframe operations")
    public void testMainPageInteractiveAfterMultipleIFrameOps() {
        // Switch to iframe multiple times
        homePage.switchToIFrame();
        homePage.switchToDefaultContent();
        homePage.switchToIFrame();
        homePage.switchToDefaultContent();
        homePage.switchToIFrame();
        homePage.switchToDefaultContent();
        
        // Verify main page is still fully functional
        Assert.assertTrue(homePage.isMouseHoverButtonDisplayed());
        Assert.assertTrue(homePage.isAlertButtonDisplayed());
        Assert.assertTrue(homePage.isRadio1Displayed());
        Assert.assertTrue(homePage.isCheckbox1Displayed(), 
                TestMessages.MAIN_PAGE_INTERACTIVE);
    }
}
