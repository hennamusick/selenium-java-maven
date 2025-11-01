package com.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_PATH = "src/main/resources/config/config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get base URL by index
     * @param index - the index of the base URL (0, 1, 2, etc.)
     * @return the base URL at the specified index
     */
    public static String getBaseUrl(int index) {
        return properties.getProperty("baseUrl." + index);
    }

    /**
     * Get all base URLs as an array
     * @return array of all configured base URLs
     */
    public static String[] getAllBaseUrls() {
        int count = 0;
        // Count how many baseUrls are configured
        while (properties.getProperty("baseUrl." + count) != null) {
            count++;
        }
        
        String[] urls = new String[count];
        for (int i = 0; i < count; i++) {
            urls[i] = properties.getProperty("baseUrl." + i);
        }
        return urls;
    }
}