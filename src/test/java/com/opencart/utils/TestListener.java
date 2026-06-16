package com.opencart.utils;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.opencart.base.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.getTest().log(Status.INFO,
                "Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String path = ScreenshotUtil.captureScreenshot(
                BaseTest.driver,
                result.getName()
        );

        ExtentManager.getTest().pass("Test Passed")
                .addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String path = ScreenshotUtil.captureScreenshot(
                BaseTest.driver,
                result.getName()
        );

        ExtentManager.getTest().fail(result.getThrowable())
                .addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flushReports();
    }
}