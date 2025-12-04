package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the Checkout Step One (Your Information) page where users enter shipping details
 * 
 * @author Framework
 * @version 1.0
 */
public class CheckoutStepOnePage extends BasePage {

    // Page title
    @FindBy(className = "title")
    private WebElement pageTitle;

    // Form fields
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    // Buttons
    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    // Error message
    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "error-button")
    private WebElement errorCloseButton;

    // Cart badge
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    /**
     * Constructor to initialize the CheckoutStepOnePage
     * 
     * @param driver WebDriver instance
     */
    public CheckoutStepOnePage(WebDriver driver) {
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

    // ==================== FORM FIELDS ====================

    public boolean isFirstNameFieldDisplayed() {
        return isElementDisplayed(firstNameField);
    }

    public boolean isLastNameFieldDisplayed() {
        return isElementDisplayed(lastNameField);
    }

    public boolean isPostalCodeFieldDisplayed() {
        return isElementDisplayed(postalCodeField);
    }

    public void enterFirstName(String firstName) {
        sendKeysToElement(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeysToElement(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        sendKeysToElement(postalCodeField, postalCode);
    }

    public void clearFirstName() {
        firstNameField.clear();
    }

    public void clearLastName() {
        lastNameField.clear();
    }

    public void clearPostalCode() {
        postalCodeField.clear();
    }

    public String getFirstNameValue() {
        return getElementAttribute(firstNameField, "value");
    }

    public String getLastNameValue() {
        return getElementAttribute(lastNameField, "value");
    }

    public String getPostalCodeValue() {
        return getElementAttribute(postalCodeField, "value");
    }

    public String getFirstNamePlaceholder() {
        return getElementAttribute(firstNameField, "placeholder");
    }

    public String getLastNamePlaceholder() {
        return getElementAttribute(lastNameField, "placeholder");
    }

    public String getPostalCodePlaceholder() {
        return getElementAttribute(postalCodeField, "placeholder");
    }

    public boolean isFirstNameFieldEnabled() {
        return isElementEnabled(firstNameField);
    }

    public boolean isLastNameFieldEnabled() {
        return isElementEnabled(lastNameField);
    }

    public boolean isPostalCodeFieldEnabled() {
        return isElementEnabled(postalCodeField);
    }

    // ==================== BUTTONS ====================

    public boolean isContinueButtonDisplayed() {
        return isElementDisplayed(continueButton);
    }

    public boolean isCancelButtonDisplayed() {
        return isElementDisplayed(cancelButton);
    }

    public boolean isContinueButtonEnabled() {
        return isElementEnabled(continueButton);
    }

    public boolean isCancelButtonEnabled() {
        return isElementEnabled(cancelButton);
    }

    public String getContinueButtonText() {
        return getElementText(continueButton);
    }

    public String getCancelButtonText() {
        return getElementText(cancelButton);
    }

    public void clickContinue() {
        clickElement(continueButton);
    }

    public void clickCancel() {
        clickElement(cancelButton);
    }

    // ==================== ERROR MESSAGE ====================

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    public boolean isErrorCloseButtonDisplayed() {
        return isElementDisplayed(errorCloseButton);
    }

    public void clickErrorCloseButton() {
        clickElement(errorCloseButton);
    }

    // ==================== CART BADGE ====================

    public boolean isCartBadgeDisplayed() {
        return isElementDisplayed(cartBadge);
    }

    public String getCartBadgeCount() {
        return getElementText(cartBadge);
    }

    // ==================== FORM SUBMISSION ====================

    /**
     * Fill out the checkout form with all required information
     * 
     * @param firstName First name
     * @param lastName Last name
     * @param postalCode Postal/ZIP code
     */
    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    /**
     * Complete checkout by filling form and clicking continue
     * 
     * @param firstName First name
     * @param lastName Last name
     * @param postalCode Postal/ZIP code
     */
    public void completeCheckoutStepOne(String firstName, String lastName, String postalCode) {
        fillCheckoutInformation(firstName, lastName, postalCode);
        clickContinue();
    }

    /**
     * Check if all form fields are empty
     * 
     * @return true if all fields are empty
     */
    public boolean areAllFieldsEmpty() {
        return getFirstNameValue().isEmpty() && 
               getLastNameValue().isEmpty() && 
               getPostalCodeValue().isEmpty();
    }

    /**
     * Check if all form fields are filled
     * 
     * @return true if all fields have values
     */
    public boolean areAllFieldsFilled() {
        return !getFirstNameValue().isEmpty() && 
               !getLastNameValue().isEmpty() && 
               !getPostalCodeValue().isEmpty();
    }

    /**
     * Clear all form fields
     */
    public void clearAllFields() {
        clearFirstName();
        clearLastName();
        clearPostalCode();
    }
}
