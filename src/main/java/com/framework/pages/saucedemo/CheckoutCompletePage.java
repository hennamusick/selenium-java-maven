package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the Checkout Complete (Confirmation) page shown after successful order completion
 * Displays order confirmation message, success icon, and navigation back to home
 * 
 * @author Framework
 * @version 1.0
 */
public class CheckoutCompletePage extends BasePage {

    // Page title
    @FindBy(className = "title")
    private WebElement pageTitle;

    // Success icon/checkmark
    @FindBy(className = "pony_express")
    private WebElement successIcon;

    // Confirmation header message
    @FindBy(css = ".complete-header")
    private WebElement confirmationHeader;

    // Confirmation message text
    @FindBy(css = ".complete-text")
    private WebElement confirmationMessage;

    // Back Home button
    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    /**
     * Constructor to initialize CheckoutCompletePage
     * 
     * @param driver WebDriver instance
     */
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ==================== PAGE TITLE ====================

    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(pageTitle);
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    // ==================== SUCCESS ICON ====================

    public boolean isSuccessIconDisplayed() {
        return isElementDisplayed(successIcon);
    }

    // ==================== CONFIRMATION HEADER ====================

    public boolean isConfirmationHeaderDisplayed() {
        return isElementDisplayed(confirmationHeader);
    }

    public String getConfirmationHeader() {
        return getElementText(confirmationHeader);
    }

    /**
     * Verify confirmation header contains success text
     * 
     * @return true if header indicates successful order
     */
    public boolean isConfirmationHeaderCorrect() {
        String header = getConfirmationHeader().toLowerCase();
        return header.contains("complete") || header.contains("success") || header.contains("thank");
    }

    // ==================== CONFIRMATION MESSAGE ====================

    public boolean isConfirmationMessageDisplayed() {
        return isElementDisplayed(confirmationMessage);
    }

    public String getConfirmationMessage() {
        return getElementText(confirmationMessage);
    }

    /**
     * Verify confirmation message contains expected content about order dispatch
     * 
     * @return true if message indicates order has been dispatched
     */
    public boolean isConfirmationMessageCorrect() {
        String message = getConfirmationMessage().toLowerCase();
        return message.contains("order") && (message.contains("dispatch") || message.contains("deliver"));
    }

    // ==================== BACK HOME BUTTON ====================

    public boolean isBackHomeButtonDisplayed() {
        return isElementDisplayed(backHomeButton);
    }

    public boolean isBackHomeButtonEnabled() {
        return isElementEnabled(backHomeButton);
    }

    public String getBackHomeButtonText() {
        return getElementText(backHomeButton);
    }

    public void clickBackHome() {
        clickElement(backHomeButton);
    }

    // ==================== PAGE VERIFICATION ====================

    /**
     * Verify all elements of order confirmation are displayed
     * 
     * @return true if all confirmation elements present
     */
    public boolean isOrderConfirmationComplete() {
        return isPageTitleDisplayed() &&
               isSuccessIconDisplayed() &&
               isConfirmationHeaderDisplayed() &&
               isConfirmationMessageDisplayed() &&
               isBackHomeButtonDisplayed();
    }

    /**
     * Verify order completion page shows successful state
     * 
     * @return true if page indicates successful order completion
     */
    public boolean isOrderSuccessful() {
        return isConfirmationHeaderCorrect() &&
               isConfirmationMessageCorrect() &&
               isBackHomeButtonEnabled();
    }

    /**
     * Get current page URL
     * 
     * @return Current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Verify page URL contains checkout complete path
     * 
     * @return true if on checkout complete page
     */
    public boolean isOnCheckoutCompletePage() {
        return getCurrentUrl().contains("checkout-complete");
    }

    /**
     * Navigate back to inventory/home page
     */
    public void navigateBackHome() {
        clickBackHome();
    }
}
