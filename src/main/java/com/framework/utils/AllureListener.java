package com.framework.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtil.info("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtil.info("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtil.error("Test Failed: " + result.getMethod().getMethodName());
        
        // Capture screenshot on failure
        WebDriver driver = DriverManager.getDriver();
        
        if (driver != null) {
            saveScreenshot(driver);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtil.info("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        LoggerUtil.info("Starting test suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtil.info("Finished test suite: " + context.getName());
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            LoggerUtil.error("Failed to capture screenshot: " + e.getMessage());
            return new byte[0];
        }
    }
}
