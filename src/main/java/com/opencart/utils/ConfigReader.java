package com.opencart.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}