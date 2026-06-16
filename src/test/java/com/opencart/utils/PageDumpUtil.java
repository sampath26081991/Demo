package com.opencart.utils;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageDumpUtil {

    public static String savePageSource(WebDriver driver, String prefix) {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String folder = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "pages" + File.separator;
            Files.createDirectories(Path.of(folder));
            String filePath = folder + prefix + "_" + timeStamp + ".html";
            String src = driver.getPageSource();
            Files.writeString(Path.of(filePath), src, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("[PageDumpUtil] Saved page source: " + filePath);
            return filePath;
        } catch (Exception e) {
            System.out.println("[PageDumpUtil] Failed to save page source: " + e.getMessage());
            return null;
        }
    }
}

