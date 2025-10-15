package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {
    // Alert Section
    @FindBy(xpath = "//button[contains(., 'Click Me')]")
    private WebElement alertButton;
    
    @FindBy(xpath = "//button[contains(., 'Click Me')]")
    private WebElement confirmBoxButton;
    
    @FindBy(xpath = "//button[contains(., 'Click Me')]")
    private WebElement promptButton;
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void clickAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(alertButton)).click();
    }
    
    public void clickConfirmBox() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmBoxButton)).click();
    }
    
    public void clickPrompt() {
        wait.until(ExpectedConditions.elementToBeClickable(promptButton)).click();
    }
}