package com.framework.utils;

/**
 * Centralized test assertion messages for all test classes.
 * Provides consistent, reusable messages across the test suite.
 */
public class TestMessages {
    
    // ==================== COMMON MESSAGES ====================
    public static final String ELEMENT_SHOULD_BE_DISPLAYED = "%s should be displayed on the page";
    public static final String ELEMENT_SHOULD_BE_ENABLED = "%s should be enabled";
    public static final String ELEMENT_SHOULD_BE_DISABLED = "%s should be disabled";
    public static final String ELEMENT_SHOULD_BE_SELECTED = "%s should be selected";
    public static final String ELEMENT_SHOULD_NOT_BE_SELECTED = "%s should not be selected";
    
    // ==================== TAB SWITCHING MESSAGES ====================
    public static final String OPEN_TAB_BUTTON_DISPLAYED = "Open Tab button should be displayed on the page";
    public static final String OPEN_TAB_BUTTON_ENABLED = "Open Tab button should be enabled";
    public static final String OPEN_TAB_OPENS_ONE_NEW_TAB = "Clicking Open Tab button should open exactly one new tab";
    public static final String NEW_TAB_OPENS_CORRECT_URL = "New tab should open QA Click Academy website";
    public static final String NEW_TAB_HAS_TITLE = "New tab should have a title";
    public static final String TAB_TITLE_NOT_EMPTY = "Tab title should not be empty";
    public static final String BACK_ON_ORIGINAL_TAB = "Should be back on the original tab with same URL";
    public static final String ORIGINAL_TAB_ON_PRACTICE_PAGE = "Original tab should still be on AutomationPractice page";
    public static final String OPEN_TAB_BUTTON_VISIBLE_ON_PARENT = "Open Tab button should still be visible on parent tab";
    public static final String PARENT_TAB_ELEMENTS_INTERACTIVE = "Parent tab elements should still be interactive";
    public static final String PARENT_TAB_FUNCTIONAL_AFTER_CLOSE = "Parent tab should remain functional after closing child tab";
    public static final String ONLY_PARENT_TAB_REMAINS = "Only parent tab should remain open";
    public static final String SHOULD_HAVE_N_TABS_AFTER_FIRST = "Should have 2 tabs after first click";
    public static final String SHOULD_HAVE_N_TABS_AFTER_SECOND = "Should have 3 tabs after second click";
    public static final String SHOULD_HAVE_UNIQUE_TAB_HANDLES = "Should have exactly 2 unique tab handles";
    public static final String TAB_HANDLES_SHOULD_BE_UNIQUE = "Tab handles should be unique";
    public static final String NEW_TAB_HAS_PAGE_CONTENT = "New tab should have page content";
    public static final String NEW_TAB_HAS_SUBSTANTIAL_CONTENT = "New tab should have substantial content";
    public static final String FIRST_CHILD_TAB_IS_QA_ACADEMY = "First child tab should be QA Click Academy";
    public static final String SECOND_CHILD_TAB_IS_QA_ACADEMY = "Second child tab should be QA Click Academy";
    public static final String ABLE_TO_SWITCH_BACK_TO_PARENT = "Should be able to switch back to parent tab";
    public static final String SHOULD_HAVE_THREE_TABS_OPEN = "Should have 3 tabs open";
    public static final String SHOULD_HAVE_TWO_TABS_AFTER_CLOSING = "Should have 2 tabs after closing one";
    
    // ==================== WINDOW SWITCHING MESSAGES ====================
    public static final String OPEN_WINDOW_BUTTON_DISPLAYED = "Open Window button should be displayed on the page";
    public static final String OPEN_WINDOW_BUTTON_ENABLED = "Open Window button should be enabled";
    public static final String OPEN_WINDOW_OPENS_ONE_NEW_WINDOW = "Clicking Open Window button should open exactly one new window";
    public static final String NEW_WINDOW_OPENS_CORRECT_URL = "New window should open QA Click Academy website";
    public static final String NEW_WINDOW_HAS_TITLE = "New window should have a title";
    public static final String WINDOW_TITLE_NOT_EMPTY = "Window title should not be empty";
    public static final String PARENT_WINDOW_FUNCTIONAL_AFTER_CLOSE = "Parent window should remain functional after closing child window";
    public static final String NEW_WINDOW_HAS_PAGE_CONTENT = "New window should have page content";
    
    // ==================== RADIO BUTTON MESSAGES ====================
    public static final String RADIO1_DISPLAYED = "Radio1 should be displayed";
    public static final String RADIO1_ENABLED = "Radio1 should be enabled";
    public static final String RADIO1_SELECTED = "Radio1 should be selected";
    public static final String RADIO1_NOT_SELECTED = "Radio1 should not be selected";
    public static final String RADIO1_DESELECTED = "Radio1 should be deselected after clicking another radio";
    public static final String RADIO1_REMAINS_SELECTED = "Radio1 should remain selected after re-clicking";
    public static final String RADIO2_SELECTED = "Radio2 should be selected";
    public static final String RADIO2_NOT_SELECTED = "Radio2 should not be selected";
    public static final String RADIO3_SELECTED = "Radio3 should be selected";
    public static final String RADIO3_NOT_SELECTED = "Radio3 should not be selected";
    public static final String ONLY_ONE_RADIO_SELECTED = "Only one radio button should be selected at a time";
    
    // ==================== CHECKBOX MESSAGES ====================
    public static final String CHECKBOX1_DISPLAYED = "Checkbox1 should be displayed";
    public static final String CHECKBOX1_ENABLED = "Checkbox1 should be enabled";
    public static final String CHECKBOX1_SELECTED = "Checkbox1 should be selected";
    public static final String CHECKBOX1_NOT_SELECTED = "Checkbox1 should not be selected";
    public static final String CHECKBOX1_UNCHECKED = "Checkbox1 should be unchecked";
    public static final String CHECKBOX2_SELECTED = "Checkbox2 should be selected";
    public static final String CHECKBOX2_NOT_SELECTED = "Checkbox2 should not be selected";
    public static final String CHECKBOX3_SELECTED = "Checkbox3 should be selected";
    public static final String CHECKBOX3_NOT_SELECTED = "Checkbox3 should not be selected";
    public static final String MULTIPLE_CHECKBOXES_CAN_BE_SELECTED = "Multiple checkboxes can be selected simultaneously";
    public static final String CHECKBOXES_INDEPENDENT = "Checkboxes should work independently";
    
    // ==================== DROPDOWN MESSAGES ====================
    public static final String DROPDOWN_DISPLAYED = "Dropdown should be displayed";
    public static final String DROPDOWN_ENABLED = "Dropdown should be enabled";
    public static final String DROPDOWN_SELECTED_OPTION = "Dropdown should display selected option";
    public static final String DROPDOWN_HAS_ALL_OPTIONS = "Dropdown should contain all expected options";
    public static final String DROPDOWN_DEFAULT_OPTION = "Dropdown should have default 'Select' option";
    
    // ==================== AUTOCOMPLETE MESSAGES ====================
    public static final String SUGGESTIONS_APPEAR = "Suggestions should appear when typing";
    public static final String SUGGESTIONS_COUNT_GREATER_THAN_ZERO = "Suggestion count should be greater than 0";
    public static final String SUGGESTION_SELECTED_BY_TEXT = "Selected suggestion should match entered text";
    public static final String SUGGESTION_SELECTED_BY_INDEX = "Selected suggestion should be from the list";
    public static final String AUTOCOMPLETE_VALUE_MATCHES = "Autocomplete value should match selected suggestion";
    public static final String SUGGESTIONS_CONTAIN_SEARCH_TEXT = "Suggestions should contain the search text";
    public static final String AUTOCOMPLETE_CASE_INSENSITIVE = "Autocomplete should be case insensitive";
    
    // ==================== ALERT MESSAGES ====================
    public static final String ALERT_PRESENT = "Alert should be present";
    public static final String ALERT_TEXT_MATCHES = "Alert text should match expected message";
    public static final String CONFIRM_BOX_PRESENT = "Confirm box should be present";
    
    // Private constructor to prevent instantiation
    private TestMessages() {
        throw new AssertionError("TestMessages class should not be instantiated");
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Formats a message with the provided arguments.
     * Useful for parameterized messages.
     * 
     * @param message The message template with placeholders
     * @param args Arguments to replace placeholders
     * @return Formatted message
     */
    public static String format(String message, Object... args) {
        return String.format(message, args);
    }
}
