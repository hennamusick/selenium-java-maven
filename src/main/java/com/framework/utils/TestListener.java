package com.framework.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        LoggerUtil.error("Test Failed: " + result.getMethod().getMethodName());
        // Add screenshot capture logic if needed
    }

    @Override
    public void onStart(ITestContext context) {
        LoggerUtil.info("Starting test suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtil.info("Finished test suite: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtil.info("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtil.info("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtil.info("Test skipped: " + result.getMethod().getMethodName());
    }
}