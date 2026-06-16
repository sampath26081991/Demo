package com.opencart.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.opencart.Config.FrameworkConstants;
import com.opencart.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ExtentLogger {

    public static void pass(org.openqa.selenium.WebDriver driver, String message) {

        String path = capture(driver, message);

        try {
            BaseTest.test.pass(message,
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            BaseTest.test.pass(message);
        }
    }

    public static void fail(org.openqa.selenium.WebDriver driver, String message) {

        String path = capture(driver, message);

        try {
            BaseTest.test.fail(message,
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (Exception e) {
            BaseTest.test.fail(message);
        }
    }

    private static String capture(org.openqa.selenium.WebDriver driver, String name) {

        String path = FrameworkConstants.SCREENSHOT_PATH
                + name.replace(" ", "_")
                + System.currentTimeMillis()
                + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}