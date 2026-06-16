package com.opencart.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String name) {

        try {
            String timeStamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

            String fileName = name + "_" + timeStamp + ".png";

            String folderPath = System.getProperty("user.dir") + "/screenshots/";

            String filePath = folderPath + fileName;

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File dest = new File(filePath);

            dest.getParentFile().mkdirs();

            Files.copy(src.toPath(), dest.toPath());

            System.out.println("📸 Screenshot saved: " + filePath);

            return filePath;

        } catch (Exception e) {
            System.out.println("❌ Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}