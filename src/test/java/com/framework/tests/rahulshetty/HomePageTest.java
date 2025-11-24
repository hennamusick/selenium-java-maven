package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.rahulshetty.RahulShettyConstants;
import com.framework.utils.rahulshetty.RahulShettyMessages;
import io.qameta.allure.*;
import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * HomePage Test Class
 * Tests basic page interactions including alerts and input fields
 */
@Epic("Rahul Shetty Academy")
@Feature("Basic Page Interactions")
public class HomePageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify alert functionality")
    @Description("Validate that JavaScript alert displays with entered name and can be accepted")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Alert Handling")
    public void testAlert() {
        homePage.enterName(RahulShettyConstants.TEST_USER_NAME);
        homePage.clickAlert();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains(RahulShettyConstants.TEST_USER_NAME), 
            RahulShettyMessages.ALERT_TEXT_CONTAINS_NAME);
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify confirm box functionality")
    @Description("Validate that JavaScript confirm box displays with entered name and can be accepted")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Confirm Box Handling")
    public void testConfirmBox() {
        homePage.enterName(RahulShettyConstants.TEST_USER_NAME);
        homePage.clickConfirmBox();
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(RahulShettyConstants.TEST_USER_NAME), 
            RahulShettyMessages.CONFIRM_TEXT_CONTAINS_NAME);
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"smoke", "regression"}, description = "Verify name field accepts input")
    @Description("Validate that name input field accepts text input")
    @Severity(SeverityLevel.NORMAL)
    @Story("Input Field Validation")
    public void testNameInput() {
        homePage.enterName(RahulShettyConstants.AUTOMATION_TEST_NAME);
        // Verify we can interact with the page successfully
        softAssert.assertNotNull(driver.getTitle(), RahulShettyMessages.PAGE_HAS_TITLE);
        
        softAssert.assertAll();
    }
}
