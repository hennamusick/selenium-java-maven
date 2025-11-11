package com.framework.pages;

import com.framework.utils.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected Wait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
        PageFactory.initElements(driver, this);
        switchToDefaultContent(); // Always start from the main frame
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void switchToFrame(String frameId) {
        wait.waitForFrameAndSwitch(frameId);
    }

    protected void switchToFrame(WebElement frameElement) {
        wait.waitForFrameAndSwitch(frameElement);
    }

    // Common Explicit Wait Methods - Delegating to Wait class
    protected void clickElement(WebElement element) {
        wait.clickElement(element);
    }

    protected void sendKeysToElement(WebElement element, String text) {
        wait.sendKeysToElement(element, text);
    }

    protected void clearAndSendKeys(WebElement element, String text) {
        wait.clearAndSendKeys(element, text);
    }

    protected boolean isElementDisplayed(WebElement element) {
        return wait.isElementDisplayed(element);
    }

    protected boolean isElementEnabled(WebElement element) {
        return wait.isElementEnabled(element);
    }

    protected boolean isElementSelected(WebElement element) {
        return wait.isElementSelected(element);
    }

    protected String getElementText(WebElement element) {
        return wait.getElementText(element);
    }

    protected String getElementAttribute(WebElement element, String attribute) {
        return wait.getElementAttribute(element, attribute);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.waitForElementToBeVisible(element);
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.waitForElementToBeClickable(element);
    }
}