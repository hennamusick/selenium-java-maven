package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {
    // Alert Section - Locators for Rahul Shetty Academy AutomationPractice
    
    // Radio Buttons
    @FindBy(css = "input[value='radio1']")
    private WebElement radio1Button;
    
    @FindBy(css = "input[value='radio2']")
    private WebElement radio2Button;
    
    @FindBy(css = "input[value='radio3']")
    private WebElement radio3Button;
    
    @FindBy(id = "alertbtn")
    private WebElement alertButton;
    
    @FindBy(id = "confirmbtn")
    private WebElement confirmBoxButton;
    
    @FindBy(css = "input[id='name']")
    private WebElement nameInput;
    
    // Suggestion/Autocomplete Section
    @FindBy(id = "autocomplete")
    private WebElement autocompleteInput;
    
    @FindBy(css = "ul[id='ui-id-1'] li")
    private java.util.List<WebElement> suggestionList;
    
    // Dropdown Section
    @FindBy(id = "dropdown-class-example")
    private WebElement dropdownElement;
    
    // Checkbox Section
    @FindBy(id = "checkBoxOption1")
    private WebElement checkbox1;
    
    @FindBy(id = "checkBoxOption2")
    private WebElement checkbox2;
    
    @FindBy(id = "checkBoxOption3")
    private WebElement checkbox3;
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void clickAlert() {
        clickElement(alertButton);
    }
    
    public void clickConfirmBox() {
        clickElement(confirmBoxButton);
    }
    
    public void enterName(String name) {
        sendKeysToElement(nameInput, name);
    }
    
    // Radio Button Methods
    public void clickRadio1() {
        clickElement(radio1Button);
    }
    
    public void clickRadio2() {
        clickElement(radio2Button);
    }
    
    public void clickRadio3() {
        clickElement(radio3Button);
    }
    
    public boolean isRadio1Selected() {
        return isElementSelected(radio1Button);
    }
    
    public boolean isRadio2Selected() {
        return isElementSelected(radio2Button);
    }
    
    public boolean isRadio3Selected() {
        return isElementSelected(radio3Button);
    }
    
    public boolean isRadio1Enabled() {
        return isElementEnabled(radio1Button);
    }
    
    public boolean isRadio2Enabled() {
        return isElementEnabled(radio2Button);
    }
    
    public boolean isRadio3Enabled() {
        return isElementEnabled(radio3Button);
    }
    
    public boolean isRadio1Displayed() {
        return isElementDisplayed(radio1Button);
    }
    
    public boolean isRadio2Displayed() {
        return isElementDisplayed(radio2Button);
    }
    
    public boolean isRadio3Displayed() {
        return isElementDisplayed(radio3Button);
    }
    
    // Autocomplete/Suggestion Methods
    public void enterCountryInAutocomplete(String country) {
        sendKeysToElement(autocompleteInput, country);
    }
    
    public void clearAutocomplete() {
        autocompleteInput.clear();
    }
    
    public boolean isSuggestionListDisplayed() {
        try {
            waitForElementToBeVisible(suggestionList.get(0));
            return suggestionList.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getSuggestionCount() {
        try {
            waitForElementToBeVisible(suggestionList.get(0));
            return suggestionList.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public void selectSuggestionByText(String text) {
        waitForElementToBeVisible(suggestionList.get(0));
        for (WebElement suggestion : suggestionList) {
            if (getElementText(suggestion).equalsIgnoreCase(text)) {
                clickElement(suggestion);
                break;
            }
        }
    }
    
    public void selectSuggestionByIndex(int index) {
        waitForElementToBeVisible(suggestionList.get(0));
        if (index < suggestionList.size()) {
            clickElement(suggestionList.get(index));
        }
    }
    
    public String getAutocompleteValue() {
        return getElementAttribute(autocompleteInput, "value");
    }
    
    public java.util.List<String> getAllSuggestions() {
        java.util.List<String> suggestions = new java.util.ArrayList<>();
        try {
            waitForElementToBeVisible(suggestionList.get(0));
            for (WebElement suggestion : suggestionList) {
                suggestions.add(getElementText(suggestion));
            }
        } catch (Exception e) {
            // Return empty list if no suggestions found
        }
        return suggestions;
    }
    
    // Dropdown Methods
    public void selectDropdownByVisibleText(String text) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(text);
    }
    
    public void selectDropdownByValue(String value) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }
    
    public void selectDropdownByIndex(int index) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(index);
    }
    
    public String getSelectedDropdownText() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption().getText();
    }
    
    public String getSelectedDropdownValue() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption().getAttribute("value");
    }
    
    public java.util.List<String> getAllDropdownOptions() {
        Select dropdown = new Select(dropdownElement);
        java.util.List<String> options = new java.util.ArrayList<>();
        for (WebElement option : dropdown.getOptions()) {
            options.add(option.getText());
        }
        return options;
    }
    
    public boolean isDropdownDisplayed() {
        return isElementDisplayed(dropdownElement);
    }
    
    // Checkbox Methods
    public void clickCheckbox1() {
        clickElement(checkbox1);
    }
    
    public void clickCheckbox2() {
        clickElement(checkbox2);
    }
    
    public void clickCheckbox3() {
        clickElement(checkbox3);
    }
    
    public void checkCheckbox1() {
        if (!isCheckbox1Selected()) {
            clickElement(checkbox1);
        }
    }
    
    public void checkCheckbox2() {
        if (!isCheckbox2Selected()) {
            clickElement(checkbox2);
        }
    }
    
    public void checkCheckbox3() {
        if (!isCheckbox3Selected()) {
            clickElement(checkbox3);
        }
    }
    
    public void uncheckCheckbox1() {
        if (isCheckbox1Selected()) {
            clickElement(checkbox1);
        }
    }
    
    public void uncheckCheckbox2() {
        if (isCheckbox2Selected()) {
            clickElement(checkbox2);
        }
    }
    
    public void uncheckCheckbox3() {
        if (isCheckbox3Selected()) {
            clickElement(checkbox3);
        }
    }
    
    public boolean isCheckbox1Selected() {
        return isElementSelected(checkbox1);
    }
    
    public boolean isCheckbox2Selected() {
        return isElementSelected(checkbox2);
    }
    
    public boolean isCheckbox3Selected() {
        return isElementSelected(checkbox3);
    }
    
    public boolean isCheckbox1Displayed() {
        return isElementDisplayed(checkbox1);
    }
    
    public boolean isCheckbox2Displayed() {
        return isElementDisplayed(checkbox2);
    }
    
    public boolean isCheckbox3Displayed() {
        return isElementDisplayed(checkbox3);
    }
    
    public boolean isCheckbox1Enabled() {
        return isElementEnabled(checkbox1);
    }
    
    public boolean isCheckbox2Enabled() {
        return isElementEnabled(checkbox2);
    }
    
    public boolean isCheckbox3Enabled() {
        return isElementEnabled(checkbox3);
    }
}