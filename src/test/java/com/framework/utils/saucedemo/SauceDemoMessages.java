package com.framework.utils.saucedemo;

/**
 * SauceDemo Test Messages - Centralized assertion messages for SauceDemo tests
 * Provides consistent, reusable messages for all SauceDemo test assertions
 */
public final class SauceDemoMessages {
    
    // Private constructor prevents instantiation
    private SauceDemoMessages() {
        throw new UnsupportedOperationException("SauceDemoMessages is a utility class and cannot be instantiated");
    }
    
    // ==================== LOGIN PAGE MESSAGES ====================
    
    public static final String USERNAME_FIELD_DISPLAYED = "Username field should be displayed";
    public static final String PASSWORD_FIELD_DISPLAYED = "Password field should be displayed";
    public static final String LOGIN_BUTTON_DISPLAYED = "Login button should be displayed";
    public static final String USERNAME_FIELD_ENABLED = "Username field should be enabled";
    public static final String PASSWORD_FIELD_ENABLED = "Password field should be enabled";
    public static final String LOGIN_BUTTON_ENABLED = "Login button should be enabled";
    public static final String LOGIN_SUCCESSFUL = "Login should be successful and redirect to inventory page";
    public static final String USERNAME_ACCEPTED = "Username field should accept input";
    public static final String PASSWORD_ACCEPTED = "Password field should accept input";
    public static final String USERNAME_CLEARED = "Username field should be empty after clear";
    public static final String PASSWORD_CLEARED = "Password field should be empty after clear";
    public static final String ERROR_MESSAGE_DISPLAYED = "Error message should be displayed for invalid login";
    public static final String LOGIN_BUTTON_TEXT_CORRECT = "Login button should have correct text";
    public static final String PAGE_TITLE_CORRECT = "Page title should be correct";
    public static final String ERROR_MESSAGE_FOR_LOCKED_USER = "Error message should be displayed for locked out user";
    public static final String ERROR_MESSAGE_FOR_INVALID_CREDENTIALS = "Error message should match invalid credentials error";
    public static final String ERROR_MESSAGE_FOR_EMPTY_USERNAME = "Error message should be displayed for empty username";
    public static final String ERROR_MESSAGE_FOR_EMPTY_PASSWORD = "Error message should be displayed for empty password";
    
    // ==================== INVENTORY PAGE MESSAGES ====================
    
    public static final String INVENTORY_TITLE_DISPLAYED = "Inventory page title should be displayed";
    public static final String INVENTORY_TITLE_CORRECT = "Inventory page title should be 'Products'";
    public static final String APP_LOGO_DISPLAYED = "App logo should be displayed";
    public static final String APP_LOGO_TEXT_CORRECT = "App logo should have correct text";
    public static final String MENU_BUTTON_DISPLAYED = "Menu button should be displayed";
    public static final String MENU_BUTTON_ENABLED = "Menu button should be enabled";
    public static final String SHOPPING_CART_DISPLAYED = "Shopping cart should be displayed";
    public static final String SHOPPING_CART_ENABLED = "Shopping cart should be enabled";
    public static final String SORT_DROPDOWN_DISPLAYED = "Sort dropdown should be displayed";
    public static final String INVENTORY_ITEMS_DISPLAYED = "Inventory items should be displayed";
    public static final String INVENTORY_COUNT_CORRECT = "Should have correct number of inventory items";
    public static final String PRODUCT_NAMES_DISPLAYED = "Product names should be displayed";
    public static final String PRODUCT_PRICES_DISPLAYED = "Product prices should be displayed";
    public static final String ADD_TO_CART_BUTTONS_DISPLAYED = "Add to cart buttons should be displayed";
    public static final String REMOVE_BUTTONS_DISPLAYED = "Remove buttons should be displayed after adding items";
    
    // ==================== CART MESSAGES ====================
    
    public static final String CART_BADGE_UPDATED = "Cart badge should be updated after adding item";
    public static final String CART_BADGE_DISPLAYS_COUNT = "Cart badge should display correct item count";
    public static final String ITEM_ADDED_TO_CART = "Item should be added to cart";
    public static final String ITEM_REMOVED_FROM_CART = "Item should be removed from cart";
    public static final String CART_BADGE_NOT_DISPLAYED_WHEN_EMPTY = "Cart badge should not be displayed when cart is empty";
    public static final String MULTIPLE_ITEMS_ADDED = "Multiple items should be added to cart";
    public static final String ALL_ITEMS_REMOVED = "All items should be removed from cart";
    
    // ==================== PRODUCT MESSAGES ====================
    
    public static final String PRODUCT_NAME_DISPLAYED = "Product name should be displayed";
    public static final String PRODUCT_PRICE_DISPLAYED = "Product price should be displayed";
    public static final String PRODUCT_IMAGE_DISPLAYED = "Product image should be displayed";
    public static final String PRODUCT_DESCRIPTION_DISPLAYED = "Product description should be displayed";
    public static final String PRODUCT_CLICKABLE = "Product should be clickable";
    public static final String PRODUCT_DETAILS_CORRECT = "Product details should be correct";
    
    // ==================== NAVIGATION MESSAGES ====================
    
    public static final String REDIRECTED_TO_INVENTORY = "Should be redirected to inventory page";
    public static final String REDIRECTED_TO_CART = "Should be redirected to cart page";
    public static final String REDIRECTED_TO_CHECKOUT = "Should be redirected to checkout page";
    public static final String REDIRECTED_TO_LOGIN = "Should be redirected to login page after logout";
    public static final String URL_CONTAINS_INVENTORY = "URL should contain inventory page pattern";
    public static final String URL_CONTAINS_CART = "URL should contain cart page pattern";
    
    // ==================== GENERAL MESSAGES ====================
    
    public static final String PAGE_LOADED_SUCCESSFULLY = "Page should load successfully";
    public static final String ELEMENT_VISIBLE = "Element should be visible";
    public static final String ELEMENT_ENABLED = "Element should be enabled";
    public static final String ELEMENT_CLICKABLE = "Element should be clickable";
    public static final String TEXT_MATCHES_EXPECTED = "Text should match expected value";
    public static final String COUNT_MATCHES_EXPECTED = "Count should match expected value";
    public static final String VALUE_NOT_EMPTY = "Value should not be empty";
    public static final String LIST_NOT_EMPTY = "List should not be empty";
    
    /**
     * Helper method to format messages with arguments
     * Usage: SauceDemoMessages.format("Expected %s but got %s", expected, actual)
     */
    public static String format(String message, Object... args) {
        return String.format(message, args);
    }
}
