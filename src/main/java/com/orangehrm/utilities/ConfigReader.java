package com.orangehrm.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicitWait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicitWait"));
    }
}