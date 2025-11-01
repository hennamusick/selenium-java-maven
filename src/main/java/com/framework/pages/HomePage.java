package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void clickAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(alertButton)).click();
    }
    
    public void clickConfirmBox() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmBoxButton)).click();
    }
    
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys(name);
    }
    
    // Radio Button Methods
    public void clickRadio1() {
        wait.until(ExpectedConditions.elementToBeClickable(radio1Button)).click();
    }
    
    public void clickRadio2() {
        wait.until(ExpectedConditions.elementToBeClickable(radio2Button)).click();
    }
    
    public void clickRadio3() {
        wait.until(ExpectedConditions.elementToBeClickable(radio3Button)).click();
    }
    
    public boolean isRadio1Selected() {
        return radio1Button.isSelected();
    }
    
    public boolean isRadio2Selected() {
        return radio2Button.isSelected();
    }
    
    public boolean isRadio3Selected() {
        return radio3Button.isSelected();
    }
    
    public boolean isRadio1Enabled() {
        return radio1Button.isEnabled();
    }
    
    public boolean isRadio2Enabled() {
        return radio2Button.isEnabled();
    }
    
    public boolean isRadio3Enabled() {
        return radio3Button.isEnabled();
    }
    
    public boolean isRadio1Displayed() {
        return radio1Button.isDisplayed();
    }
    
    public boolean isRadio2Displayed() {
        return radio2Button.isDisplayed();
    }
    
    public boolean isRadio3Displayed() {
        return radio3Button.isDisplayed();
    }
}