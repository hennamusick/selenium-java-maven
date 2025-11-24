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
    
    // Switch Window Section
    @FindBy(id = "openwindow")
    private WebElement openWindowButton;
    
    // Switch Tab Section
    @FindBy(id = "opentab")
    private WebElement openTabButton;
    
    // Hide/Show Section
    @FindBy(id = "hide-textbox")
    private WebElement hideButton;
    
    @FindBy(id = "show-textbox")
    private WebElement showButton;
    
    @FindBy(id = "displayed-text")
    private WebElement displayedTextBox;
    
    // Web Table Fixed Header Section
    @FindBy(css = "div.tableFixHead")
    private WebElement fixedHeaderTableContainer;
    
    @FindBy(css = "div.tableFixHead table")
    private WebElement fixedHeaderTable;
    
    @FindBy(css = "div.tableFixHead table thead th")
    private java.util.List<WebElement> fixedTableHeaders;
    
    @FindBy(css = "div.tableFixHead table tbody tr")
    private java.util.List<WebElement> fixedTableRows;
    
    @FindBy(css = "div.totalAmount")
    private WebElement totalAmountElement;
    
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
    
    public void clearName() {
        nameInput.clear();
    }
    
    public String getNameValue() {
        return getElementAttribute(nameInput, "value");
    }
    
    public boolean isAlertButtonDisplayed() {
        return isElementDisplayed(alertButton);
    }
    
    public boolean isAlertButtonEnabled() {
        return isElementEnabled(alertButton);
    }
    
    public boolean isConfirmBoxButtonDisplayed() {
        return isElementDisplayed(confirmBoxButton);
    }
    
    public boolean isConfirmBoxButtonEnabled() {
        return isElementEnabled(confirmBoxButton);
    }
    
    public boolean isNameInputDisplayed() {
        return isElementDisplayed(nameInput);
    }
    
    public boolean isNameInputEnabled() {
        return isElementEnabled(nameInput);
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
    
    // Switch Window Methods
    public void clickOpenWindowButton() {
        clickElement(openWindowButton);
    }
    
    public boolean isOpenWindowButtonDisplayed() {
        return isElementDisplayed(openWindowButton);
    }
    
    public boolean isOpenWindowButtonEnabled() {
        return isElementEnabled(openWindowButton);
    }
    
    // Switch Tab Methods
    public void clickOpenTabButton() {
        clickElement(openTabButton);
    }
    
    public boolean isOpenTabButtonDisplayed() {
        return isElementDisplayed(openTabButton);
    }
    
    public boolean isOpenTabButtonEnabled() {
        return isElementEnabled(openTabButton);
    }
    
    // Hide/Show Methods
    public void scrollToHideShowSection() {
        scrollToElement(hideButton);
    }
    
    public void clickHideButton() {
        clickElement(hideButton);
    }
    
    public void clickShowButton() {
        clickElement(showButton);
    }
    
    public boolean isHideButtonDisplayed() {
        return isElementDisplayed(hideButton);
    }
    
    public boolean isShowButtonDisplayed() {
        return isElementDisplayed(showButton);
    }
    
    public boolean isHideButtonEnabled() {
        return isElementEnabled(hideButton);
    }
    
    public boolean isShowButtonEnabled() {
        return isElementEnabled(showButton);
    }
    
    public boolean isDisplayedTextBoxVisible() {
        return isElementDisplayed(displayedTextBox);
    }
    
    public boolean isDisplayedTextBoxEnabled() {
        return isElementEnabled(displayedTextBox);
    }
    
    public void enterTextInDisplayedTextBox(String text) {
        sendKeysToElement(displayedTextBox, text);
    }
    
    public String getDisplayedTextBoxValue() {
        return getElementAttribute(displayedTextBox, "value");
    }
    
    public void clearDisplayedTextBox() {
        displayedTextBox.clear();
    }
    
    // Fixed Header Table Methods
    public void scrollToFixedHeaderTable() {
        scrollToElement(fixedHeaderTableContainer);
    }
    
    public boolean isFixedHeaderTableDisplayed() {
        return isElementDisplayed(fixedHeaderTable);
    }
    
    public java.util.List<String> getFixedTableHeaders() {
        waitForElementToBeVisible(fixedHeaderTable);
        java.util.List<String> headers = new java.util.ArrayList<>();
        for (WebElement header : fixedTableHeaders) {
            headers.add(getElementText(header));
        }
        return headers;
    }
    
    public int getFixedTableRowCount() {
        waitForElementToBeVisible(fixedHeaderTable);
        return fixedTableRows.size();
    }
    
    public String getFixedTableCellValue(int rowIndex, int columnIndex) {
        waitForElementToBeVisible(fixedHeaderTable);
        WebElement cell = driver.findElement(
            org.openqa.selenium.By.cssSelector("div.tableFixHead table tbody tr:nth-child(" + rowIndex + ") td:nth-child(" + columnIndex + ")")
        );
        return getElementText(cell);
    }
    
    public java.util.Map<String, String> getFixedTableRowData(int rowIndex) {
        waitForElementToBeVisible(fixedHeaderTable);
        java.util.Map<String, String> rowData = new java.util.HashMap<>();
        java.util.List<String> headers = getFixedTableHeaders();
        
        for (int i = 0; i < headers.size(); i++) {
            String cellValue = getFixedTableCellValue(rowIndex, i + 1);
            rowData.put(headers.get(i), cellValue);
        }
        return rowData;
    }
    
    public java.util.List<String> getFixedTableColumnData(String columnName) {
        waitForElementToBeVisible(fixedHeaderTable);
        java.util.List<String> headers = getFixedTableHeaders();
        int headerIndex = headers.indexOf(columnName);
        if (headerIndex == -1) {
            return new java.util.ArrayList<>();
        }
        int columnIndex = headerIndex + 1;
        
        java.util.List<String> columnData = new java.util.ArrayList<>();
        for (int i = 1; i <= getFixedTableRowCount(); i++) {
            columnData.add(getFixedTableCellValue(i, columnIndex));
        }
        return columnData;
    }
    
    public java.util.List<Integer> getAllAmounts() {
        java.util.List<String> amountStrings = getFixedTableColumnData("Amount");
        java.util.List<Integer> amounts = new java.util.ArrayList<>();
        for (String amount : amountStrings) {
            amounts.add(Integer.parseInt(amount.trim()));
        }
        return amounts;
    }
    
    public int calculateTotalAmount() {
        java.util.List<Integer> amounts = getAllAmounts();
        return amounts.stream().mapToInt(Integer::intValue).sum();
    }
    
    public String getDisplayedTotalAmount() {
        waitForElementToBeVisible(totalAmountElement);
        String fullText = getElementText(totalAmountElement);
        // Extract number from "Total Amount Collected: 296"
        return fullText.replaceAll("[^0-9]", "");
    }
    
    public boolean isTotalAmountDisplayed() {
        return isElementDisplayed(totalAmountElement);
    }
    
    public java.util.List<String> getAllNames() {
        return getFixedTableColumnData("Name");
    }
    
    public java.util.List<String> getAllPositions() {
        return getFixedTableColumnData("Position");
    }
    
    public java.util.List<String> getAllCities() {
        return getFixedTableColumnData("City");
    }
    
    public boolean isPersonPresent(String name) {
        return getAllNames().stream()
                .anyMatch(n -> n.trim().equalsIgnoreCase(name.trim()));
    }
    
    public String getPositionByName(String name) {
        waitForElementToBeVisible(fixedHeaderTable);
        int rowCount = getFixedTableRowCount();
        
        for (int i = 1; i <= rowCount; i++) {
            String rowName = getFixedTableCellValue(i, 1);
            if (rowName.trim().equalsIgnoreCase(name.trim())) {
                return getFixedTableCellValue(i, 2);
            }
        }
        return null;
    }
    
    public String getCityByName(String name) {
        waitForElementToBeVisible(fixedHeaderTable);
        int rowCount = getFixedTableRowCount();
        
        for (int i = 1; i <= rowCount; i++) {
            String rowName = getFixedTableCellValue(i, 1);
            if (rowName.trim().equalsIgnoreCase(name.trim())) {
                return getFixedTableCellValue(i, 3);
            }
        }
        return null;
    }
    
    public int getAmountByName(String name) {
        waitForElementToBeVisible(fixedHeaderTable);
        int rowCount = getFixedTableRowCount();
        
        for (int i = 1; i <= rowCount; i++) {
            String rowName = getFixedTableCellValue(i, 1);
            if (rowName.trim().equalsIgnoreCase(name.trim())) {
                String amount = getFixedTableCellValue(i, 4);
                return Integer.parseInt(amount.trim());
            }
        }
        return -1;
    }
}