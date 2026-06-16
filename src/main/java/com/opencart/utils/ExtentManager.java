package com.opencart.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getExtent() {

        if (extent == null) {

            ExtentSparkReporter spark = new ExtentSparkReporter(
                    System.getProperty("user.dir") + "/reports/ExtentReport.html"
            );

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void flushReports() {
        getExtent().flush();
    }
}