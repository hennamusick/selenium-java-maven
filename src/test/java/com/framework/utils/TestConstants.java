package com.framework.utils;

/**
 * Test Constants - Centralized repository for all test data constants
 * Includes URLs, text values, credentials, and other reusable test data
 * 
 * Best Practice: Keep all hard-coded test values in one place for easy maintenance
 */
public final class TestConstants {
    
    // Private constructor prevents instantiation
    private TestConstants() {
        throw new UnsupportedOperationException("TestConstants is a utility class and cannot be instantiated");
    }
    
    // ==================== URLs ====================
    
    /**
     * QA Click Academy URL - Opens in new window/tab when clicking Open Window/Tab buttons
     */
    public static final String QA_CLICK_ACADEMY_URL = "https://www.qaclickacademy.com/";
    
    /**
     * Rahul Shetty Academy Automation Practice page
     */
    public static final String AUTOMATION_PRACTICE_PAGE = "AutomationPractice";
    
    /**
     * SauceDemo website domain
     */
    public static final String SAUCEDEMO_DOMAIN = "saucedemo.com";
    
    // ==================== Test Data ====================
    
    /**
     * Standard test user name
     */
    public static final String TEST_USER_NAME = "Test User";
    
    /**
     * Standard automation test name
     */
    public static final String AUTOMATION_TEST_NAME = "Automation Test";
    
    // ==================== Dropdown Options ====================
    
    public static final String DROPDOWN_OPTION1 = "Option1";
    public static final String DROPDOWN_OPTION2 = "Option2";
    public static final String DROPDOWN_OPTION3 = "Option3";
    
    // ==================== Country Names (Autocomplete) ====================
    
    public static final String COUNTRY_INDIA = "India";
    public static final String COUNTRY_INDIA_PREFIX = "Ind";
    public static final String COUNTRY_UNITED_STATES = "United States";
    public static final String COUNTRY_UNITED_STATES_PREFIX = "Uni";
    
    // ==================== Numeric Constants ====================
    
    /**
     * Minimum expected page content length (bytes)
     */
    public static final int MIN_PAGE_CONTENT_LENGTH = 100;
    
    /**
     * Expected number of windows/tabs
     */
    public static final int SINGLE_WINDOW = 1;
    public static final int TWO_WINDOWS = 2;
    public static final int THREE_WINDOWS = 3;
}
