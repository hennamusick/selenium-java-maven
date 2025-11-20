package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.TestConstants;
import com.framework.utils.TestMessages;
import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify alert functionality")
    public void testAlert() {
        homePage.enterName(TestConstants.TEST_USER_NAME);
        homePage.clickAlert();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains(TestConstants.TEST_USER_NAME), 
            TestMessages.ALERT_TEXT_CONTAINS_NAME);
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify confirm box functionality")
    public void testConfirmBox() {
        homePage.enterName(TestConstants.TEST_USER_NAME);
        homePage.clickConfirmBox();
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains(TestConstants.TEST_USER_NAME), 
            TestMessages.CONFIRM_TEXT_CONTAINS_NAME);
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"smoke", "regression"}, description = "Verify name field accepts input")
    public void testNameInput() {
        homePage.enterName(TestConstants.AUTOMATION_TEST_NAME);
        // Verify we can interact with the page successfully
        softAssert.assertNotNull(driver.getTitle(), TestMessages.PAGE_HAS_TITLE);
        
        softAssert.assertAll();
    }
}
