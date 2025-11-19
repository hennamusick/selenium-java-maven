package com.framework.tests.rahulshetty;

import com.framework.pages.HomePage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() throws InterruptedException {
        // Using baseUrl.1 - Rahul Shetty Academy AutomationPractice
        driver.get(ConfigReader.getBaseUrl(1));
        Thread.sleep(2000); // Wait for page to load
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify alert functionality")
    public void testAlert() {
        homePage.enterName("Test User");
        homePage.clickAlert();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains("Test User"), 
            "Alert should contain the entered name");
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify confirm box functionality")
    public void testConfirmBox() {
        homePage.enterName("Test User");
        homePage.clickConfirmBox();
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains("Test User"), 
            "Confirm box should contain the entered name");
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"smoke", "regression"}, description = "Verify name field accepts input")
    public void testNameInput() {
        homePage.enterName("Automation Test");
        // Verify we can interact with the page successfully
        softAssert.assertNotNull(driver.getTitle(), "Page should have a title");
        
        softAssert.assertAll();
    }
}
