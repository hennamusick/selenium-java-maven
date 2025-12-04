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
    
    // ==================== SORT/FILTER MESSAGES ====================
    
    public static final String SORT_DROPDOWN_DISPLAYED = "Sort dropdown should be displayed";
    public static final String SORT_DROPDOWN_ENABLED = "Sort dropdown should be enabled";
    public static final String DEFAULT_SORT_CORRECT = "Default sort should be 'Name (A to Z)'";
    public static final String SORT_OPTIONS_COUNT_CORRECT = "Should have 4 sort options";
    public static final String SORT_OPTION_NAME_ASC_AVAILABLE = "Should contain 'Name (A to Z)'";
    public static final String SORT_OPTION_NAME_DESC_AVAILABLE = "Should contain 'Name (Z to A)'";
    public static final String SORT_OPTION_PRICE_LOW_HIGH_AVAILABLE = "Should contain 'Price (low to high)'";
    public static final String SORT_OPTION_PRICE_HIGH_LOW_AVAILABLE = "Should contain 'Price (high to low)'";
    public static final String PRODUCTS_SORTED_A_TO_Z = "Products should be sorted A to Z";
    public static final String PRODUCTS_SORTED_Z_TO_A = "Products should be sorted Z to A";
    public static final String PRODUCTS_SORTED_PRICE_LOW_HIGH = "Products should be sorted by price low to high";
    public static final String PRODUCTS_SORTED_PRICE_HIGH_LOW = "Products should be sorted by price high to low";
    public static final String SELECTED_SORT_OPTION_CORRECT = "Selected sort option should be correct";
    
    // ==================== CART PAGE MESSAGES ====================
    
    public static final String CART_PAGE_TITLE_DISPLAYED = "Cart page title should be displayed";
    public static final String CART_PAGE_TITLE_CORRECT = "Cart page title should be 'Your Cart'";
    public static final String CART_EMPTY_WHEN_NO_ITEMS = "Cart should be empty when no items are added";
    public static final String CART_SHOULD_HAVE_ZERO_ITEMS = "Cart should have 0 items";
    public static final String CONTINUE_SHOPPING_BUTTON_DISPLAYED = "Continue Shopping button should be displayed";
    public static final String CHECKOUT_BUTTON_DISPLAYED = "Checkout button should be displayed";
    public static final String CART_CONTAINS_ONE_ITEM = "Cart should contain 1 item";
    public static final String CART_CONTAINS_THREE_ITEMS = "Cart should contain 3 items";
    public static final String CART_DISPLAY_THREE_PRICES = "Cart should display 3 item prices";
    public static final String PRICE_STARTS_WITH_DOLLAR = "Price should start with $";
    public static final String CART_DISPLAY_QUANTITIES_TWO = "Cart should display quantities for 2 items";
    public static final String ITEM_QUANTITY_ONE = "Each item should have quantity of 1";
    public static final String CART_DISPLAY_ONE_DESCRIPTION = "Cart should display 1 item description";
    public static final String ITEM_DESCRIPTION_NOT_EMPTY = "Item description should not be empty";
    public static final String REMOVE_BUTTON_COUNT_MATCH = "Remove button count should match item count";
    public static final String CART_ONE_LESS_AFTER_REMOVAL = "Cart should have one less item after removal";
    public static final String CART_NOT_EMPTY_BEFORE_REMOVAL = "Cart should not be empty before removing items";
    public static final String CART_EMPTY_AFTER_REMOVING_ALL = "Cart should be empty after removing all items";
    public static final String CART_ITEM_COUNT_ZERO = "Cart item count should be 0";
    public static final String CONTINUE_SHOPPING_NAVIGATE_INVENTORY = "Should navigate back to inventory page";
    public static final String CHECKOUT_NAVIGATE_TO_CHECKOUT = "Should navigate to checkout page";
    public static final String TOTAL_PRICE_GREATER_THAN_ZERO = "Total price should be greater than 0";
    public static final String TOTAL_PRICE_REASONABLE_THREE_ITEMS = "Total price should be reasonable for 3 items";
    
    // ==================== CART BADGE MESSAGES ====================
    
    public static final String CART_BADGE_DISPLAYED = "Cart badge should be displayed";
    public static final String CART_BADGE_SHOWS_ONE = "Cart badge should show 1 item";
    public static final String CART_BADGE_SHOWS_THREE = "Cart badge should show 3 items";
    public static final String CART_BADGE_SHOWS_SIX = "Cart badge should show 6 items";
    public static final String CART_BADGE_NOT_DISPLAYED = "Cart badge should not be displayed";
    public static final String CART_BADGE_STILL_DISPLAYED = "Cart badge should still be displayed";
    public static final String CART_BADGE_COUNT_UPDATED = "Cart badge count should be updated";
    
    // ==================== BUTTON STATE MESSAGES ====================
    
    public static final String ADD_TO_CART_BUTTONS_COUNT_CORRECT = "Should have correct number of 'Add to cart' buttons";
    public static final String REMOVE_BUTTONS_COUNT_CORRECT = "Should have correct number of 'Remove' buttons";
    public static final String ADD_BUTTON_BECOMES_REMOVE = "'Add to cart' button should become 'Remove' button";
    public static final String REMOVE_BUTTON_BECOMES_ADD = "'Remove' button should become 'Add to cart' button";
    public static final String ALL_ADD_BUTTONS_PRESENT = "All 'Add to cart' buttons should be present";
    public static final String ALL_REMOVE_BUTTONS_PRESENT = "All 'Remove' buttons should be present";
    public static final String SHOPPING_CART_CLICKABLE = "Shopping cart should be enabled/clickable";
    
    // ==================== CART PERSISTENCE MESSAGES ====================
    
    public static final String CART_PERSISTS_AFTER_SORT = "Cart should persist after sorting";
    public static final String CART_COUNT_UNCHANGED = "Cart count should remain unchanged";
    public static final String CART_ITEMS_REMAIN = "Cart items should remain after operation";
    
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
    
    // ==================== CHECKOUT OVERVIEW MESSAGES ====================
    
    public static final String OVERVIEW_PAGE_TITLE_DISPLAYED = "Overview page title should be displayed";
    public static final String OVERVIEW_PAGE_TITLE_CORRECT = "Page title should be 'Checkout: Overview'";
    public static final String DISPLAY_ALL_THREE_ITEMS = "Should display all 3 items added to cart";
    public static final String ALL_ITEMS_HAVE_DETAILS = "All items should have name, description, price, and quantity";
    public static final String ITEM_NAMES_DISPLAYED = "Item names should be displayed";
    public static final String DISPLAY_THREE_ITEM_NAMES = "Should display 3 item names";
    public static final String FIRST_ITEM_BACKPACK = "First item should be Backpack";
    public static final String ITEM_DESCRIPTIONS_DISPLAYED = "Item descriptions should be displayed";
    public static final String DISPLAY_THREE_DESCRIPTIONS = "Should display 3 item descriptions";
    public static final String DESCRIPTION_HAS_CONTENT = "Each description should have content";
    public static final String DISPLAY_THREE_ITEM_PRICES = "Should display 3 item prices";
    public static final String DISPLAY_THREE_QUANTITIES = "Should display 3 item quantities";
    public static final String EACH_ITEM_QUANTITY_ONE = "Each item quantity should be 1";
    public static final String ORDER_SUMMARY_DISPLAYED = "Order summary section should be displayed";
    public static final String DISPLAY_SUBTOTAL_LABEL = "Should display subtotal label";
    public static final String SUBTOTAL_CORRECT_FORMAT = "Subtotal should include price in correct format";
    public static final String SUBTOTAL_GREATER_THAN_ZERO = "Subtotal should be greater than 0";
    public static final String DISPLAY_TAX_LABEL = "Should display tax label";
    public static final String TAX_CORRECT_FORMAT = "Tax should include price in correct format";
    public static final String TAX_GREATER_THAN_ZERO = "Tax should be greater than 0";
    public static final String DISPLAY_TOTAL_LABEL = "Should display total label";
    public static final String TOTAL_GREATER_THAN_ZERO = "Total should be greater than 0";
    public static final String ITEM_NAME_NOT_EMPTY = "Item name should not be empty";
    public static final String ITEM_DESCRIPTION_NOT_EMPTY_CHECKOUT = "Item description should not be empty";
    public static final String ITEM_PRICE_FORMAT_INVALID = "Item price format invalid";
    public static final String ITEM_QUANTITY_ONE_TEXT = "Item quantity should be 1";
    public static final String PRICE_SHOULD_BE_POSITIVE = "Price should be positive";
    public static final String TAX_PERCENTAGE_REASONABLE = "Tax percentage should be reasonable (between 5-10%)";
    public static final String QUANTITY_SHOULD_BE_POSITIVE = "Quantity should be positive";
    
    // ==================== CHECKOUT STEP ONE MESSAGES ====================
    
    public static final String CHECKOUT_PAGE_TITLE_DISPLAYED = "Checkout page title should be displayed";
    public static final String CHECKOUT_PAGE_TITLE_CORRECT = "Page title should be 'Checkout: Your Information'";
    public static final String FIRST_NAME_FIELD_DISPLAYED = "First Name field should be displayed";
    public static final String LAST_NAME_FIELD_DISPLAYED = "Last Name field should be displayed";
    public static final String POSTAL_CODE_FIELD_DISPLAYED = "Postal Code field should be displayed";
    public static final String FIRST_NAME_PLACEHOLDER_CORRECT = "First Name placeholder should be correct";
    public static final String LAST_NAME_PLACEHOLDER_CORRECT = "Last Name placeholder should be correct";
    public static final String POSTAL_CODE_PLACEHOLDER_CORRECT = "Postal Code placeholder should be correct";
    public static final String FIRST_NAME_FIELD_ENABLED = "First Name field should be enabled";
    public static final String LAST_NAME_FIELD_ENABLED = "Last Name field should be enabled";
    public static final String POSTAL_CODE_FIELD_ENABLED = "Postal Code field should be enabled";
    public static final String CONTINUE_BUTTON_DISPLAYED = "Continue button should be displayed";
    public static final String CANCEL_BUTTON_DISPLAYED = "Cancel button should be displayed";
    public static final String CONTINUE_BUTTON_TEXT_CORRECT = "Continue button text should be 'Continue'";
    public static final String CANCEL_BUTTON_TEXT_CORRECT = "Cancel button text should be 'Cancel'";
    public static final String CART_BADGE_VISIBLE = "Cart badge should be visible";
    public static final String CART_BADGE_SHOW_ONE_ITEM = "Cart badge should show 1 item";
    public static final String ALL_FIELDS_FILLED = "All fields should be filled";
    public static final String ALL_FIELDS_EMPTY_AFTER_CLEAR = "All fields should be empty after clearing";
    public static final String ERROR_SHOULD_BE_DISPLAYED = "Error should be displayed";
    public static final String CANCEL_NAVIGATE_TO_CART = "Should navigate back to cart page";
    public static final String CONTINUE_BUTTON_ENABLED = "Continue button should be enabled";
    public static final String CANCEL_BUTTON_ENABLED = "Cancel button should be enabled";
    
    // ==================== CHECKOUT CONFIRMATION MESSAGES ====================
    
    public static final String CONFIRMATION_PAGE_TITLE_DISPLAYED = "Confirmation page title should be displayed";
    public static final String CONFIRMATION_PAGE_TITLE_CORRECT = "Page title should be 'Checkout: Complete!'";
    public static final String ON_CHECKOUT_COMPLETE_PAGE = "Should be on checkout complete page";
    public static final String URL_CONTAINS_CHECKOUT_COMPLETE = "URL should contain 'checkout-complete'";
    public static final String SUCCESS_ICON_DISPLAYED = "Success icon should be displayed";
    public static final String CONFIRMATION_HEADER_DISPLAYED = "Confirmation header should be displayed";
    public static final String CONFIRMATION_HEADER_NOT_EMPTY = "Confirmation header should contain text";
    public static final String CONFIRMATION_MESSAGE_DISPLAYED = "Confirmation message should be displayed";
    public static final String CONFIRMATION_MESSAGE_NOT_EMPTY = "Confirmation message should contain text";
    public static final String CONFIRMATION_HEADER_INDICATES_SUCCESS = "Header should indicate successful completion";
    public static final String CONFIRMATION_HEADER_VALIDATION_PASSED = "Confirmation header validation should pass";
    public static final String CONFIRMATION_MESSAGE_MENTIONS_ORDER = "Message should mention 'order'";
    public static final String CONFIRMATION_MESSAGE_INDICATES_DELIVERY = "Message should indicate delivery/dispatch";
    public static final String CONFIRMATION_MESSAGE_VALIDATION_PASSED = "Confirmation message validation should pass";
    public static final String ORDER_MARKED_SUCCESSFUL = "Order should be marked as successful";
    public static final String BACK_HOME_BUTTON_DISPLAYED = "Back Home button should be displayed";
    public static final String BACK_HOME_BUTTON_ENABLED = "Back Home button should be enabled";
    public static final String BACK_HOME_BUTTON_TEXT_CORRECT = "Back Home button text should be 'Back Home'";
    public static final String ALL_CONFIRMATION_ELEMENTS_DISPLAYED = "All order confirmation elements should be displayed";
    public static final String NAVIGATE_TO_INVENTORY = "Should navigate back to inventory page";
    public static final String CONFIRMATION_HEADER_MEANINGFUL = "Confirmation header should have meaningful content";
    public static final String CONFIRMATION_MESSAGE_MEANINGFUL = "Confirmation message should have meaningful content";
    public static final String MESSAGE_NO_REPEATED_PUNCTUATION = "Message should not have repeated punctuation";
    public static final String MESSAGE_PROPER_SPACING = "Message should contain proper spacing";
    public static final String SUCCESS_ICON_VISIBLE = "Success icon should be clearly visible";
    public static final String ORDER_COMPLETION_IS_FINAL_STEP = "Order completion page should be final step";
    public static final String ORDER_SUCCESSFUL_STATUS = "Order should be successful";
    public static final String ALL_CONFIRMATION_ELEMENTS_PRESENT = "All confirmation elements should be present";
    public static final String ON_CHECKOUT_COMPLETE_PAGE_TITLE = "Should be on checkout complete page";
    public static final String PAGE_TITLE_NOT_NULL = "Page title should not be null";
    public static final String CAN_RETURN_TO_HOME = "Should be able to return to home";
    public static final String MESSAGE_INDICATES_DELIVERY_METHOD = "Should indicate how order will be delivered";
    
    // ==================== VALIDATION KEYWORDS ====================
    
    public static final String KEYWORD_COMPLETE = "complete";
    public static final String KEYWORD_THANK = "thank";
    public static final String KEYWORD_ORDER = "order";
    public static final String KEYWORD_DISPATCH = "dispatch";
    public static final String KEYWORD_DELIVER = "deliver";
    public static final String KEYWORD_ARRIVE = "arrive";
    
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
