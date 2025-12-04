package com.framework.utils.saucedemo;

/**
 * SauceDemo Test Constants - Centralized repository for SauceDemo test data
 * Includes login credentials, page titles, URLs, and error messages
 * 
 * Best Practice: Keep all SauceDemo-specific test values in one place for easy maintenance
 */
public final class SauceDemoConstants {
    
    // Private constructor prevents instantiation
    private SauceDemoConstants() {
        throw new UnsupportedOperationException("SauceDemoConstants is a utility class and cannot be instantiated");
    }
    
    // ==================== LOGIN CREDENTIALS ====================
    
    /**
     * SauceDemo Login Users
     */
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PERFORMANCE_USER = "performance_glitch_user";
    public static final String ERROR_USER = "error_user";
    public static final String VISUAL_USER = "visual_user";
    
    /**
     * SauceDemo Password (same for all users)
     */
    public static final String PASSWORD = "secret_sauce";
    
    // ==================== PAGE TITLES AND TEXT ====================
    
    /**
     * Browser page title
     */
    public static final String PAGE_TITLE = "Swag Labs";
    
    /**
     * Inventory page title
     */
    public static final String PRODUCTS_TITLE = "Products";
    
    /**
     * Cart page title
     */
    public static final String CART_PAGE_TITLE = "Your Cart";
    
    /**
     * Checkout step one page title
     */
    public static final String CHECKOUT_STEP_ONE_TITLE = "Checkout: Your Information";
    
    /**
     * Login button text
     */
    public static final String LOGIN_BUTTON_TEXT = "Login";
    
    /**
     * App logo text
     */
    public static final String APP_LOGO_TEXT = "Swag Labs";
    
    // ==================== URLS ====================
    
    /**
     * SauceDemo website domain
     */
    public static final String DOMAIN = "saucedemo.com";
    
    /**
     * Inventory page URL pattern
     */
    public static final String INVENTORY_URL = "inventory.html";
    
    /**
     * Shopping cart URL pattern
     */
    public static final String CART_URL = "cart.html";
    
    /**
     * Checkout step one URL pattern
     */
    public static final String CHECKOUT_STEP_ONE_URL = "checkout-step-one.html";
    
    /**
     * Checkout step two URL pattern
     */
    public static final String CHECKOUT_STEP_TWO_URL = "checkout-step-two.html";
    
    /**
     * Checkout complete URL pattern
     */
    public static final String CHECKOUT_COMPLETE_URL = "checkout-complete.html";
    
    // ==================== PRODUCT DATA ====================
    
    /**
     * Expected number of products on inventory page
     */
    public static final int PRODUCT_COUNT = 6;
    
    /**
     * Product names
     */
    public static final String PRODUCT_BACKPACK = "Sauce Labs Backpack";
    public static final String PRODUCT_BIKE_LIGHT = "Sauce Labs Bike Light";
    public static final String PRODUCT_BOLT_TSHIRT = "Sauce Labs Bolt T-Shirt";
    public static final String PRODUCT_FLEECE_JACKET = "Sauce Labs Fleece Jacket";
    public static final String PRODUCT_ONESIE = "Sauce Labs Onesie";
    public static final String PRODUCT_TSHIRT_RED = "Test.allTheThings() T-Shirt (Red)";
    
    // ==================== ERROR MESSAGES ====================
    
    /**
     * Login error messages
     */
    public static final String ERROR_LOCKED_USER = "Epic sadface: Sorry, this user has been locked out.";
    public static final String ERROR_INVALID_CREDENTIALS = "Epic sadface: Username and password do not match any user in this service";
    public static final String ERROR_USERNAME_REQUIRED = "Epic sadface: Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Epic sadface: Password is required";
    
    // ==================== SORT OPTIONS ====================
    
    /**
     * Sort dropdown options
     */
    public static final String SORT_NAME_ASC = "Name (A to Z)";
    public static final String SORT_NAME_DESC = "Name (Z to A)";
    public static final String SORT_PRICE_LOW_HIGH = "Price (low to high)";
    public static final String SORT_PRICE_HIGH_LOW = "Price (high to low)";
    
    // ==================== MENU OPTIONS ====================
    
    /**
     * Sidebar menu items
     */
    public static final String MENU_ALL_ITEMS = "All Items";
    public static final String MENU_ABOUT = "About";
    public static final String MENU_LOGOUT = "Logout";
    public static final String MENU_RESET_APP = "Reset App State";
    
    // ==================== CHECKOUT DATA ====================
    
    /**
     * Sample checkout information
     */
    public static final String CHECKOUT_FIRST_NAME = "John";
    public static final String CHECKOUT_LAST_NAME = "Doe";
    public static final String CHECKOUT_POSTAL_CODE = "12345";
    
    // ==================== BUTTON TEXT ====================
    
    /**
     * Cart page button text
     */
    public static final String CONTINUE_SHOPPING_BUTTON_TEXT = "Continue Shopping";
    public static final String CHECKOUT_BUTTON_TEXT = "Checkout";
    public static final String REMOVE_BUTTON_PREFIX = "Remove";
    
    /**
     * Checkout page button text
     */
    public static final String CONTINUE_BUTTON_TEXT = "Continue";
    public static final String CANCEL_BUTTON_TEXT = "Cancel";
    
    // ==================== ERROR MESSAGES ====================
    
    /**
     * Checkout form error messages
     */
    public static final String ERROR_FIRST_NAME_REQUIRED = "Error: First Name is required";
    public static final String ERROR_LAST_NAME_REQUIRED = "Error: Last Name is required";
    public static final String ERROR_POSTAL_CODE_REQUIRED = "Error: Postal Code is required";
}
