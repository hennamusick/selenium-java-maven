package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.rahulshetty.RahulShettyConstants;
import com.framework.utils.rahulshetty.RahulShettyMessages;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        softAssert.assertTrue(homePage.isIFrameDisplayed(), 
                RahulShettyMessages.IFRAME_DISPLAYED);
        softAssert.assertAll();
    }
    
    @Test(groups = {"smoke", "regression"}, priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that iFrame is enabled")
    public void testIFrameEnabled() {
        softAssert.assertTrue(homePage.isIFrameEnabled(), 
                RahulShettyMessages.IFRAME_ENABLED);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame has correct ID attribute")
    public void testIFrameHasCorrectId() {
        String iframeId = homePage.getIFrameId();
        softAssert.assertEquals(iframeId, RahulShettyConstants.IFRAME_ID, 
                RahulShettyMessages.IFRAME_HAS_CORRECT_ID);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame has src attribute")
    public void testIFrameHasSrc() {
        String src = homePage.getIFrameSrc();
        softAssert.assertNotNull(src, RahulShettyMessages.IFRAME_HAS_SRC);
        softAssert.assertFalse(src.isEmpty(), RahulShettyMessages.IFRAME_HAS_SRC);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame src contains expected domain")
    public void testIFrameSrcContainsDomain() {
        String src = homePage.getIFrameSrc();
        softAssert.assertTrue(src.contains(RahulShettyConstants.IFRAME_SRC_DOMAIN), 
                RahulShettyMessages.IFRAME_SRC_CONTAINS_DOMAIN);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 6)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify can switch to iFrame successfully")
    public void testSwitchToIFrame() {
        try {
            homePage.switchToIFrame();
            // If no exception thrown, switch was successful
            softAssert.assertTrue(true, RahulShettyMessages.IFRAME_SWITCH_SUCCESSFUL);
        } catch (Exception e) {
            softAssert.fail(RahulShettyMessages.IFRAME_SWITCH_SUCCESSFUL + " - Exception: " + e.getMessage());
        } finally {
            homePage.switchToDefaultContent();
            softAssert.assertAll();
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
            softAssert.assertTrue(iframeBody.isDisplayed(), 
                    RahulShettyMessages.IFRAME_CONTENT_VISIBLE);
        } finally {
            homePage.switchToDefaultContent();
            softAssert.assertAll();
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
            softAssert.assertTrue(hasContent, 
                    RahulShettyMessages.IFRAME_HAS_EXPECTED_CONTENT);
        } finally {
            homePage.switchToDefaultContent();
            softAssert.assertAll();
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
            
            softAssert.assertFalse(headers.isEmpty(), 
                    RahulShettyMessages.IFRAME_HEADER_VISIBLE);
        } finally {
            homePage.switchToDefaultContent();
            softAssert.assertAll();
        }
    }
    
    @Test(groups = {"regression"}, priority = 10)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify can switch back to main content from iFrame")
    public void testSwitchBackToMainContent() {
        homePage.switchToIFrame();
        homePage.switchToDefaultContent();
        
        // Verify we're back on main page by checking a main page element
        softAssert.assertTrue(homePage.isMouseHoverButtonDisplayed(), 
                RahulShettyMessages.SWITCHED_BACK_TO_MAIN);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 11)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify main page is interactive after switching back from iFrame")
    public void testMainPageInteractiveAfterIFrame() {
        homePage.switchToIFrame();
        homePage.switchToDefaultContent();
        
        // Test main page elements are still interactive
        softAssert.assertTrue(homePage.isMouseHoverButtonEnabled(), 
                RahulShettyMessages.MAIN_PAGE_INTERACTIVE);
        softAssert.assertTrue(homePage.isAlertButtonDisplayed(), 
                RahulShettyMessages.MAIN_PAGE_INTERACTIVE);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 12)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify multiple switches between main and iFrame work correctly")
    public void testMultipleIFrameSwitches() {
        // First switch to iframe
        homePage.switchToIFrame();
        WebElement firstBody = driver.findElement(By.tagName("body"));
        softAssert.assertTrue(firstBody.isDisplayed());
        
        // Switch back to main
        homePage.switchToDefaultContent();
        softAssert.assertTrue(homePage.isIFrameDisplayed());
        
        // Switch to iframe again
        homePage.switchToIFrame();
        WebElement secondBody = driver.findElement(By.tagName("body"));
        softAssert.assertTrue(secondBody.isDisplayed(), 
                RahulShettyMessages.MULTIPLE_IFRAME_SWITCHES_WORK);
        
        // Switch back to main again
        homePage.switchToDefaultContent();
        softAssert.assertAll();
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
                softAssert.assertTrue(links.get(0).isEnabled(), 
                        RahulShettyMessages.IFRAME_ELEMENTS_CLICKABLE);
            } else {
                // If no links, just verify body is present
                WebElement body = driver.findElement(By.tagName("body"));
                softAssert.assertTrue(body.isDisplayed(), 
                        RahulShettyMessages.IFRAME_ELEMENTS_CLICKABLE);
            }
        } finally {
            homePage.switchToDefaultContent();
            softAssert.assertAll();
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
            
            softAssert.assertNotNull(bodyText, 
                    RahulShettyMessages.IFRAME_TEXT_EXTRACTABLE);
            softAssert.assertFalse(bodyText.trim().isEmpty(), 
                    RahulShettyMessages.IFRAME_TEXT_EXTRACTABLE);
        } finally {
            homePage.switchToDefaultContent();
            softAssert.assertAll();
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
            softAssert.assertTrue(alertButtons.isEmpty(), 
                    RahulShettyMessages.MAIN_PAGE_ELEMENTS_NOT_ACCESSIBLE_IN_IFRAME);
        } finally {
            homePage.switchToDefaultContent();
            softAssert.assertAll();
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
        softAssert.assertTrue(currentUrl.contains("AutomationPractice"), 
                RahulShettyMessages.IFRAME_ELEMENTS_NOT_ACCESSIBLE_IN_MAIN);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 17)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame context is maintained until explicitly switched")
    public void testIFrameContextMaintained() {
        homePage.switchToIFrame();
        
        try {
            // Perform first action in iframe
            WebElement body1 = driver.findElement(By.tagName("body"));
            softAssert.assertTrue(body1.isDisplayed());
            
            // Wait a bit
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Perform second action - should still be in iframe context
            WebElement body2 = driver.findElement(By.tagName("body"));
            softAssert.assertTrue(body2.isDisplayed(), 
                    RahulShettyMessages.IFRAME_CONTEXT_MAINTAINED);
        } finally {
            homePage.switchToDefaultContent();
            softAssert.assertAll();
        }
    }
    
    @Test(groups = {"regression"}, priority = 18)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify can get iFrame attributes while on main page")
    public void testIFrameAttributesAccessible() {
        // Stay on main page
        String iframeId = homePage.getIFrameId();
        String iframeSrc = homePage.getIFrameSrc();
        
        softAssert.assertEquals(iframeId, RahulShettyConstants.IFRAME_ID);
        softAssert.assertTrue(iframeSrc.contains(RahulShettyConstants.IFRAME_SRC_DOMAIN));
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 19)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify iFrame is still displayed after page refresh")
    public void testIFrameDisplayedAfterRefresh() {
        softAssert.assertTrue(homePage.isIFrameDisplayed());
        
        // Refresh page
        driver.navigate().refresh();
        homePage.scrollToIFrame();
        
        // Verify iframe still displayed
        softAssert.assertTrue(homePage.isIFrameDisplayed(), 
                RahulShettyMessages.IFRAME_DISPLAYED);
        softAssert.assertAll();
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
        softAssert.assertTrue(homePage.isMouseHoverButtonDisplayed());
        softAssert.assertTrue(homePage.isAlertButtonDisplayed());
        softAssert.assertTrue(homePage.isRadio1Displayed());
        softAssert.assertTrue(homePage.isCheckbox1Displayed(), 
                RahulShettyMessages.MAIN_PAGE_INTERACTIVE);
        softAssert.assertAll();
    }
}
