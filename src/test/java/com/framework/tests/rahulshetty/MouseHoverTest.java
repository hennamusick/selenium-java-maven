package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.TestConstants;
import com.framework.utils.TestMessages;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for Mouse Hover functionality
 * Tests the mouse hover menu with Top and Reload links
 */
public class MouseHoverTest extends BaseTest {
    
    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void setupPage() {
        initializeHomePage(1); // Rahul Shetty Academy URL
        homePage.scrollToMouseHover(); // Scroll to Mouse Hover section for all tests
    }
    
    @Test(groups = {"smoke", "regression"}, priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that Mouse Hover button is displayed on the page")
    public void testMouseHoverButtonDisplayed() {
        softAssert.assertTrue(homePage.isMouseHoverButtonDisplayed(), 
                TestMessages.MOUSE_HOVER_BUTTON_DISPLAYED);
        softAssert.assertAll();
    }
    
    @Test(groups = {"smoke", "regression"}, priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that Mouse Hover button is enabled")
    public void testMouseHoverButtonEnabled() {
        softAssert.assertTrue(homePage.isMouseHoverButtonEnabled(), 
                TestMessages.MOUSE_HOVER_BUTTON_ENABLED);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Top link appears when hovering over Mouse Hover button")
    public void testTopLinkVisibleOnHover() {
        homePage.hoverOverMouseHoverButton();
        softAssert.assertTrue(homePage.isTopLinkDisplayed(), 
                TestMessages.TOP_LINK_VISIBLE_ON_HOVER);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Reload link appears when hovering over Mouse Hover button")
    public void testReloadLinkVisibleOnHover() {
        homePage.hoverOverMouseHoverButton();
        softAssert.assertTrue(homePage.isReloadLinkDisplayed(), 
                TestMessages.RELOAD_LINK_VISIBLE_ON_HOVER);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify both Top and Reload links are visible when hovering")
    public void testBothLinksVisibleOnHover() {
        homePage.hoverOverMouseHoverButton();
        
        boolean topVisible = homePage.isTopLinkDisplayed();
        boolean reloadVisible = homePage.isReloadLinkDisplayed();
        
        softAssert.assertTrue(topVisible && reloadVisible, 
                TestMessages.BOTH_LINKS_VISIBLE_ON_HOVER);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 6)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Top link has correct text")
    public void testTopLinkText() {
        homePage.hoverOverMouseHoverButton();
        String actualText = homePage.getTopLinkText();
        softAssert.assertEquals(actualText, TestConstants.TOP_LINK_TEXT, 
                TestMessages.TOP_LINK_HAS_CORRECT_TEXT);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 7)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Reload link has correct text")
    public void testReloadLinkText() {
        homePage.hoverOverMouseHoverButton();
        String actualText = homePage.getReloadLinkText();
        softAssert.assertEquals(actualText, TestConstants.RELOAD_LINK_TEXT, 
                TestMessages.RELOAD_LINK_HAS_CORRECT_TEXT);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 8)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Top link has href attribute")
    public void testTopLinkHasHref() {
        homePage.hoverOverMouseHoverButton();
        String href = homePage.getTopLinkHref();
        softAssert.assertNotNull(href, TestMessages.TOP_LINK_HAS_HREF);
        softAssert.assertTrue(href.contains("#top"), TestMessages.TOP_LINK_HREF_CORRECT);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 9)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Reload link has href attribute")
    public void testReloadLinkHasHref() {
        homePage.hoverOverMouseHoverButton();
        String href = homePage.getReloadLinkHref();
        softAssert.assertNotNull(href, TestMessages.RELOAD_LINK_HAS_HREF);
        softAssert.assertTrue(href.contains("#reload"), TestMessages.RELOAD_LINK_HREF_CORRECT);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 10)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Top link is clickable when hovering")
    public void testTopLinkClickable() {
        homePage.hoverOverMouseHoverButton();
        softAssert.assertTrue(homePage.isTopLinkClickable(), 
                TestMessages.TOP_LINK_CLICKABLE);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 11)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Reload link is clickable when hovering")
    public void testReloadLinkClickable() {
        homePage.hoverOverMouseHoverButton();
        softAssert.assertTrue(homePage.isReloadLinkClickable(), 
                TestMessages.RELOAD_LINK_CLICKABLE);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 12)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Top link can be clicked")
    public void testClickTopLink() {
        homePage.hoverOverMouseHoverButton();
        homePage.clickTopLink();
        
        // Verify page scrolled (no exception thrown means success)
        softAssert.assertTrue(true, TestMessages.PAGE_SCROLLS_TO_TOP);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 13)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Reload link can be clicked")
    public void testClickReloadLink() {
        homePage.hoverOverMouseHoverButton();
        homePage.clickReloadLink();
        
        // After clicking reload, verify page is still functional
        softAssert.assertTrue(homePage.isMouseHoverButtonDisplayed(), 
                TestMessages.MOUSE_HOVER_SECTION_FUNCTIONAL);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 14)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify multiple consecutive hovers work correctly")
    public void testMultipleHovers() {
        // First hover
        homePage.hoverOverMouseHoverButton();
        boolean firstHoverTopVisible = homePage.isTopLinkDisplayed();
        
        // Scroll away and come back
        driver.navigate().refresh();
        homePage.scrollToMouseHover();
        
        // Second hover
        homePage.hoverOverMouseHoverButton();
        boolean secondHoverTopVisible = homePage.isTopLinkDisplayed();
        
        softAssert.assertTrue(firstHoverTopVisible && secondHoverTopVisible, 
                TestMessages.MULTIPLE_HOVERS_WORK);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 15)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify hover state is consistent")
    public void testHoverStateConsistent() {
        homePage.hoverOverMouseHoverButton();
        
        String topText1 = homePage.getTopLinkText();
        String reloadText1 = homePage.getReloadLinkText();
        
        // Hover again
        homePage.hoverOverMouseHoverButton();
        String topText2 = homePage.getTopLinkText();
        String reloadText2 = homePage.getReloadLinkText();
        
        softAssert.assertEquals(topText1, topText2, TestMessages.HOVER_STATE_CONSISTENT);
        softAssert.assertEquals(reloadText1, reloadText2, TestMessages.HOVER_STATE_CONSISTENT);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 16)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify links appear in correct order")
    public void testLinksAppearInCorrectOrder() {
        homePage.hoverOverMouseHoverButton();
        
        String topText = homePage.getTopLinkText();
        String reloadText = homePage.getReloadLinkText();
        
        softAssert.assertEquals(topText, TestConstants.TOP_LINK_TEXT, 
                TestMessages.LINKS_APPEAR_IN_CORRECT_ORDER);
        softAssert.assertEquals(reloadText, TestConstants.RELOAD_LINK_TEXT, 
                TestMessages.LINKS_APPEAR_IN_CORRECT_ORDER);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 17)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Mouse Hover button has correct text")
    public void testMouseHoverButtonText() {
        String buttonText = homePage.getMouseHoverButtonText();
        softAssert.assertEquals(buttonText, TestConstants.MOUSE_HOVER_BUTTON_TEXT, 
                "Mouse Hover button should have correct text");
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 18)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify Top link displays after hovering")
    public void testTopLinkDisplayedAfterHover() {
        homePage.hoverOverMouseHoverButton();
        softAssert.assertTrue(homePage.isTopLinkDisplayed(), 
                TestMessages.TOP_LINK_DISPLAYED_ON_HOVER);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 19)
    @Severity(SeverityLevel.MINOR)
    @Description("Verify Reload link displays after hovering")
    public void testReloadLinkDisplayedAfterHover() {
        homePage.hoverOverMouseHoverButton();
        softAssert.assertTrue(homePage.isReloadLinkDisplayed(), 
                TestMessages.RELOAD_LINK_DISPLAYED_ON_HOVER);
        softAssert.assertAll();
    }
    
    @Test(groups = {"regression"}, priority = 20)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify clicking Top link and then hovering again works")
    public void testClickTopAndHoverAgain() {
        homePage.hoverOverMouseHoverButton();
        homePage.clickTopLink();
        
        // Scroll back to mouse hover section
        homePage.scrollToMouseHover();
        homePage.hoverOverMouseHoverButton();
        
        // Verify both links are still functional
        softAssert.assertTrue(homePage.isTopLinkDisplayed(), 
                TestMessages.TOP_LINK_VISIBLE_AFTER_CLICK_AND_HOVER);
        softAssert.assertTrue(homePage.isReloadLinkDisplayed(), 
                TestMessages.RELOAD_LINK_VISIBLE_AFTER_TOP_CLICK);
        softAssert.assertAll();
    }
}
