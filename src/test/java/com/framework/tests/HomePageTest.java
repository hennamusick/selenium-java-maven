package com.framework.tests;

import com.framework.pages.HomePage;
import com.framework.utils.ConfigReader;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod
    public void setUpTest() throws InterruptedException {
        driver.get(ConfigReader.getProperty("baseUrl"));
        Thread.sleep(2000); // Wait for page to load
        homePage = new HomePage(driver);
    }

    @Test(description = "Verify alert functionality")
    public void testAlert() {
        homePage.clickAlert();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am alert box!");
        alert.accept();
    }

    @Test(description = "Verify confirm box functionality")
    public void testConfirmBox() {
        homePage.clickConfirmBox();
        Alert confirm = driver.switchTo().alert();
        Assert.assertEquals(confirm.getText(), "Please select an option: Ok or Cancel");
        confirm.accept();
    }

    @Test(description = "Verify prompt functionality")
    public void testPrompt() {
        homePage.clickPrompt();
        Alert prompt = driver.switchTo().alert();
        String testUser = "Test User";
        prompt.sendKeys(testUser);
        prompt.accept();
    }
}