package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
}