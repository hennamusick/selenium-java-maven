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
     * QA Click Academy text variations for content verification
     */
    public static final String QA_CLICK_ACADEMY_TEXT_LOWER = "qaclickacademy";
    public static final String QA_CLICK_ACADEMY_TEXT_MIXED = "QA Click Academy";
    public static final String QA_CLICK_ACADEMY_TEXT_UPPER = "CLICK ACADEMY";
    
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
    
    /**
     * Sample name for alert tests
     */
    public static final String SAMPLE_NAME_SELENIUM = "Selenium Automation";
    
    /**
     * Sample name for confirm box tests
     */
    public static final String SAMPLE_NAME_CONFIRM = "Confirm Test";
    
    /**
     * New name for testing re-entry after alert
     */
    public static final String NEW_NAME = "New Name";
    
    /**
     * First alert test name
     */
    public static final String FIRST_ALERT_NAME = "First Alert";
    
    /**
     * Second alert test name
     */
    public static final String SECOND_ALERT_NAME = "Second Alert";
    
    /**
     * Accept test name for confirm box
     */
    public static final String ACCEPT_TEST_NAME = "Accept Test";
    
    /**
     * Dismiss test name for confirm box
     */
    public static final String DISMISS_TEST_NAME = "Dismiss Test";
    
    /**
     * Interactive test name
     */
    public static final String INTERACTIVE_TEST_NAME = "Interactive Test";
    
    /**
     * After confirm test name
     */
    public static final String AFTER_CONFIRM_NAME = "After Confirm";
    
    /**
     * First confirm test name
     */
    public static final String FIRST_CONFIRM_NAME = "First Confirm";
    
    /**
     * Second confirm test name
     */
    public static final String SECOND_CONFIRM_NAME = "Second Confirm";
    
    /**
     * Test input name
     */
    public static final String TEST_INPUT_NAME = "Test Input";
    
    /**
     * Clear test name
     */
    public static final String CLEAR_TEST_NAME = "Clear Me";
    
    /**
     * Hide/Show test text
     */
    public static final String HIDE_SHOW_TEST_TEXT = "Test Text";
    public static final String HIDE_SHOW_SAMPLE_TEXT = "Sample Text";
    public static final String HIDE_SHOW_NEW_TEXT = "New Text After Show";
    
    /**
     * Special characters test name
     */
    public static final String SPECIAL_CHARS_NAME = "Test@User#2025!";
    
    /**
     * Special characters for confirm box test
     */
    public static final String CONFIRM_SPECIAL_CHARS_NAME = "Confirm@Test#2025!";
    
    /**
     * Long name for testing text handling
     */
    public static final String LONG_NAME = "This is a very long name to test how the alert handles extended text input";
    
    // ==================== Dropdown Options ====================
    
    public static final String DROPDOWN_OPTION1 = "Option1";
    public static final String DROPDOWN_OPTION2 = "Option2";
    public static final String DROPDOWN_OPTION3 = "Option3";
    
    // ==================== Country Names (Autocomplete) ====================
    
    public static final String COUNTRY_INDIA = "India";
    public static final String COUNTRY_INDIA_PREFIX = "Ind";
    public static final String COUNTRY_UNITED_STATES = "United States";
    public static final String COUNTRY_UNITED_STATES_PREFIX = "Uni";
    public static final String COUNTRY_COLOMBIA = "Colombia";
    public static final String COUNTRY_CO_PREFIX = "Co";
    public static final String COUNTRY_CO_PREFIX_LOWER = "co";
    
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
    
    // ==================== Fixed Header Table Data ====================
    
    /**
     * Expected table data from the Web Table Fixed Header
     */
    public static final String PERSON_ALEX = "Alex";
    public static final String PERSON_BEN = "Ben";
    public static final String PERSON_DWAYNE = "Dwayne";
    public static final String PERSON_IVORY = "Ivory";
    public static final String PERSON_JACK = "Jack";
    
    public static final String POSITION_ENGINEER = "Engineer";
    public static final String POSITION_MECHANIC = "Mechanic";
    public static final String POSITION_MANAGER = "Manager";
    public static final String POSITION_RECEPTIONIST = "Receptionist";
    
    public static final String CITY_CHENNAI = "Chennai";
    public static final String CITY_BENGALURU = "Bengaluru";
    public static final String CITY_KOLKATA = "Kolkata";
    public static final String CITY_PUNE = "Pune";
    
    public static final int AMOUNT_ALEX = 28;
    public static final int AMOUNT_BEN = 23;
    public static final int AMOUNT_DWAYNE = 48;
    public static final int AMOUNT_IVORY = 18;
    public static final int AMOUNT_JACK = 32;
    
    public static final int EXPECTED_TOTAL_AMOUNT = 296;
}
