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
    public static final String OPEN_WINDOW_BUTTON_DISPLAYED = "Open Window button should be displayed";
    public static final String OPEN_WINDOW_BUTTON_ENABLED = "Open Window button should be enabled";
    public static final String TWO_WINDOWS_AFTER_OPENING = "There should be 2 windows after clicking Open Window button";
    public static final String PARENT_WINDOW_HANDLE_EXISTS = "Parent window handle should still exist";
    public static final String NEW_WINDOW_OPENS_CORRECT_URL = "New window should open with QA Click Academy URL";
    public static final String NEW_WINDOW_HAS_TITLE = "New window should have a title";
    public static final String BACK_ON_PARENT_WINDOW = "Should be back on the parent window";
    public static final String OPEN_WINDOW_BUTTON_VISIBLE_IN_PARENT = "Open Window button should still be displayed in parent window";
    public static final String OPEN_WINDOW_BUTTON_ENABLED_IN_PARENT = "Open Window button should still be enabled in parent window";
    public static final String PARENT_WINDOW_AT_SAME_URL = "Parent window should still be at the same URL";
    public static final String PARENT_WINDOW_ELEMENTS_ACCESSIBLE = "Parent window elements should still be accessible";
    public static final String ONLY_PARENT_WINDOW_REMAINS = "Only parent window should remain after closing child";
    public static final String TWO_WINDOWS_BUTTON_REUSES = "There should be 2 windows (button reuses the same child window)";
    public static final String BUTTON_REUSES_SAME_WINDOW = "Clicking Open Window button multiple times should reuse the same window";
    public static final String TWO_UNIQUE_WINDOW_HANDLES = "Should have 2 unique window handles";
    public static final String CHILD_WINDOW_HANDLE_NOT_NULL = "Child window handle should not be null";
    public static final String WINDOW_HANDLES_DIFFERENT = "Parent and child window handles should be different";
    public static final String NEW_WINDOW_HAS_QA_ACADEMY_CONTENT = "New window should contain QA Click Academy content";
    
    // ==================== RADIO BUTTON MESSAGES ====================
    public static final String RADIO1_DISPLAYED = "Radio1 should be displayed";
    public static final String RADIO2_DISPLAYED = "Radio2 should be displayed";
    public static final String RADIO3_DISPLAYED = "Radio3 should be displayed";
    public static final String RADIO1_ENABLED = "Radio1 should be enabled";
    public static final String RADIO2_ENABLED = "Radio2 should be enabled";
    public static final String RADIO3_ENABLED = "Radio3 should be enabled";
    public static final String RADIO1_SELECTED = "Radio1 should be selected";
    public static final String RADIO1_NOT_SELECTED = "Radio1 should not be selected";
    public static final String RADIO1_DESELECTED = "Radio1 should be deselected after clicking another radio";
    public static final String RADIO1_REMAINS_SELECTED = "Radio1 should remain selected after re-clicking";
    public static final String RADIO2_SELECTED = "Radio2 should be selected";
    public static final String RADIO2_NOT_SELECTED = "Radio2 should not be selected";
    public static final String RADIO3_SELECTED = "Radio3 should be selected";
    public static final String RADIO3_NOT_SELECTED = "Radio3 should not be selected";
    public static final String ONLY_ONE_RADIO_SELECTED = "Only one radio button should be selected at a time";
    public static final String RADIO1_SELECTED_AFTER_CLICK = "Radio1 should be selected after clicking";
    public static final String RADIO2_NOT_SELECTED_WHEN_RADIO1_SELECTED = "Radio2 should NOT be selected when Radio1 is selected";
    public static final String RADIO3_NOT_SELECTED_WHEN_RADIO1_SELECTED = "Radio3 should NOT be selected when Radio1 is selected";
    public static final String RADIO2_SELECTED_AFTER_CLICK = "Radio2 should be selected after clicking";
    public static final String RADIO1_NOT_SELECTED_WHEN_RADIO2_SELECTED = "Radio1 should NOT be selected when Radio2 is selected";
    public static final String RADIO3_NOT_SELECTED_WHEN_RADIO2_SELECTED = "Radio3 should NOT be selected when Radio2 is selected";
    public static final String RADIO3_SELECTED_AFTER_CLICK = "Radio3 should be selected after clicking";
    public static final String RADIO1_NOT_SELECTED_WHEN_RADIO3_SELECTED = "Radio1 should NOT be selected when Radio3 is selected";
    public static final String RADIO2_NOT_SELECTED_WHEN_RADIO3_SELECTED = "Radio2 should NOT be selected when Radio3 is selected";
    public static final String RADIO1_DESELECTED_AFTER_RADIO2 = "Radio1 should be deselected after switching to Radio2";
    public static final String RADIO2_DESELECTED_AFTER_RADIO3 = "Radio2 should be deselected after switching to Radio3";
    public static final String RADIO3_DESELECTED_AFTER_RADIO1 = "Radio3 should be deselected after switching to Radio1";
    public static final String RADIO1_SELECTED_AGAIN = "Radio1 should be selected again";
    public static final String MAX_ONE_RADIO_SELECTED_DEFAULT = "At most one radio button should be selected by default";
    
    // ==================== CHECKBOX MESSAGES ====================
    public static final String CHECKBOX1_DISPLAYED = "Checkbox1 should be displayed";
    public static final String CHECKBOX2_DISPLAYED = "Checkbox2 should be displayed";
    public static final String CHECKBOX3_DISPLAYED = "Checkbox3 should be displayed";
    public static final String CHECKBOX1_ENABLED = "Checkbox1 should be enabled";
    public static final String CHECKBOX2_ENABLED = "Checkbox2 should be enabled";
    public static final String CHECKBOX3_ENABLED = "Checkbox3 should be enabled";
    public static final String CHECKBOX1_SELECTED = "Checkbox1 should be selected";
    public static final String CHECKBOX1_NOT_SELECTED = "Checkbox1 should not be selected";
    public static final String CHECKBOX1_UNCHECKED = "Checkbox1 should be unchecked";
    public static final String CHECKBOX2_SELECTED = "Checkbox2 should be selected";
    public static final String CHECKBOX2_NOT_SELECTED = "Checkbox2 should not be selected";
    public static final String CHECKBOX3_SELECTED = "Checkbox3 should be selected";
    public static final String CHECKBOX3_NOT_SELECTED = "Checkbox3 should not be selected";
    public static final String MULTIPLE_CHECKBOXES_CAN_BE_SELECTED = "Multiple checkboxes can be selected simultaneously";
    public static final String CHECKBOXES_INDEPENDENT = "Checkboxes should work independently";
    public static final String CHECKBOX1_UNCHECKED_DEFAULT = "Checkbox1 should be unchecked by default";
    public static final String CHECKBOX2_UNCHECKED_DEFAULT = "Checkbox2 should be unchecked by default";
    public static final String CHECKBOX3_UNCHECKED_DEFAULT = "Checkbox3 should be unchecked by default";
    public static final String CHECKBOX1_CHECKED_AFTER_CLICK = "Checkbox1 should be checked after clicking";
    public static final String CHECKBOX2_CHECKED_AFTER_CLICK = "Checkbox2 should be checked after clicking";
    public static final String CHECKBOX3_CHECKED_AFTER_CLICK = "Checkbox3 should be checked after clicking";
    public static final String CHECKBOX1_UNCHECKED_AFTER_CLICK = "Checkbox1 should be unchecked after clicking again";
    public static final String CHECKBOX1_CHECKED_FIRST_CLICK = "Checkbox1 should be checked on first click";
    public static final String CHECKBOX1_UNCHECKED_SECOND_CLICK = "Checkbox1 should be unchecked on second click";
    public static final String CHECKBOX1_CHECKED_THIRD_CLICK = "Checkbox1 should be checked again on third click";
    public static final String CHECKBOX2_UNCHECKED = "Checkbox2 should be unchecked";
    public static final String CHECKBOX3_UNCHECKED = "Checkbox3 should be unchecked";
    public static final String CHECKBOX1_REMAINS_CHECKED = "Checkbox1 should remain checked";
    public static final String CHECKBOX2_REMAINS_CHECKED = "Checkbox2 should remain checked";
    public static final String CHECKBOX3_REMAINS_CHECKED = "Checkbox3 should remain checked";
    public static final String CHECKBOX1_REMAINS_CHECKED_AGAIN = "Checkbox1 should remain checked after calling checkCheckbox1 again";
    public static final String CHECKBOX1_REMAINS_UNCHECKED_AGAIN = "Checkbox1 should remain unchecked after calling uncheckCheckbox1 again";

    // ==================== DROPDOWN MESSAGES ====================
    public static final String DROPDOWN_DISPLAYED = "Dropdown should be displayed on the page";
    public static final String DROPDOWN_ENABLED = "Dropdown should be enabled";
    public static final String DROPDOWN_SELECTED_OPTION = "Dropdown should display selected option";
    public static final String DROPDOWN_HAS_ALL_OPTIONS = "Dropdown should contain all expected options";
    public static final String DROPDOWN_DEFAULT_OPTION = "Dropdown should have default 'Select' option";
    public static final String DROPDOWN_SELECTED_TEXT_OPTION1 = "Selected dropdown text should be 'Option1'";
    public static final String DROPDOWN_SELECTED_TEXT_OPTION2 = "Selected dropdown text should be 'Option2'";
    public static final String DROPDOWN_SELECTED_TEXT_OPTION3 = "Selected dropdown text should be 'Option3'";
    public static final String DROPDOWN_CONTAINS_SELECT = "Dropdown should contain 'Select' option";
    public static final String DROPDOWN_CONTAINS_OPTION1 = "Dropdown should contain 'Option1'";
    public static final String DROPDOWN_CONTAINS_OPTION2 = "Dropdown should contain 'Option2'";
    public static final String DROPDOWN_CONTAINS_OPTION3 = "Dropdown should contain 'Option3'";
    public static final String DROPDOWN_INDEX_1_IS_OPTION1 = "Selecting index 1 should select 'Option1'";
    public static final String DROPDOWN_VALUE_OPTION1 = "Selected dropdown value should be 'option1'";
    public static final String DROPDOWN_HAS_OPTIONS = "Dropdown should have options";
    public static final String DROPDOWN_FIRST_OPTION_SELECT = "First option should be 'Select'";

    // ==================== AUTOCOMPLETE MESSAGES ====================
    public static final String SUGGESTIONS_APPEAR = "Suggestions should appear when typing";
    public static final String SUGGESTIONS_COUNT_GREATER_THAN_ZERO = "Suggestion count should be greater than 0";
    public static final String SUGGESTION_SELECTED_BY_TEXT = "Selected suggestion should match entered text";
    public static final String SUGGESTION_SELECTED_BY_INDEX = "Selected suggestion should be from the list";
    public static final String AUTOCOMPLETE_VALUE_MATCHES = "Autocomplete value should match selected suggestion";
    public static final String SUGGESTIONS_CONTAIN_SEARCH_TEXT = "Suggestions should contain the search text";
    public static final String AUTOCOMPLETE_CASE_INSENSITIVE = "Autocomplete should be case insensitive";
    public static final String SUGGESTIONS_DISPLAYED_IND = "Suggestions should be displayed when typing 'Ind'";
    public static final String SUGGESTION_COUNT_GT_ZERO_IND = "There should be at least one suggestion for 'Ind'";
    public static final String SELECTED_VALUE_INDIA = "Selected value should be 'India'";
    public static final String SUGGESTIONS_FOR_CO = "There should be suggestions for 'co'";
    public static final String VALUE_SELECTED_FROM_SUGGESTIONS = "A value should be selected from suggestions";
    public static final String SUGGESTIONS_FOR_COLOMBIA = "Suggestions should appear for 'Colombia'";
    public static final String SUGGESTIONS_CONTAIN_COLOMBIA = "Suggestions should contain 'Colombia'";
    public static final String SUGGESTIONS_APPEAR_FIRST_SEARCH = "Suggestions should appear for first search";
    public static final String SUGGESTIONS_APPEAR_SECOND_SEARCH = "Suggestions should appear for second search after clearing";
    public static final String MULTIPLE_SUGGESTIONS_FOR_CO = "Multiple suggestions should appear for 'Co' (Colombia, Congo, etc.)";
    public static final String SUGGESTIONS_CONTAIN_CO = "Suggestions should contain countries with 'co'";
    public static final String SUGGESTIONS_LOWERCASE = "Suggestions should appear with lowercase input";
    public static final String SUGGESTIONS_UPPERCASE = "Suggestions should appear with uppercase input";
    
    // ==================== ALERT MESSAGES ====================
    public static final String ALERT_BUTTON_DISPLAYED = "Alert button should be displayed";
    public static final String ALERT_BUTTON_ENABLED = "Alert button should be enabled";
    public static final String CONFIRM_BUTTON_DISPLAYED = "Confirm button should be displayed";
    public static final String CONFIRM_BUTTON_ENABLED = "Confirm button should be enabled";
    public static final String NAME_INPUT_DISPLAYED = "Name input field should be displayed";
    public static final String NAME_INPUT_ENABLED = "Name input field should be enabled";
    public static final String ALERT_PRESENT = "Alert should be present after clicking Alert button";
    public static final String ALERT_TEXT_CONTAINS_NAME = "Alert text should contain the entered name";
    public static final String ALERT_TEXT_MATCHES = "Alert text should match expected message";
    public static final String ALERT_TEXT_NOT_EMPTY = "Alert text should not be empty";
    public static final String CONFIRM_BOX_PRESENT = "Confirm box should be present after clicking Confirm button";
    public static final String CONFIRM_TEXT_CONTAINS_NAME = "Confirm box text should contain the entered name";
    public static final String CONFIRM_TEXT_NOT_EMPTY = "Confirm box text should not be empty";
    public static final String ALERT_ACCEPTED = "Alert should be accepted successfully";
    public static final String CONFIRM_ACCEPTED = "Confirm box should be accepted successfully";
    public static final String CONFIRM_DISMISSED = "Confirm box should be dismissed successfully";
    public static final String PAGE_INTERACTIVE_AFTER_ALERT = "Page should remain interactive after accepting alert";
    public static final String PAGE_INTERACTIVE_AFTER_CONFIRM = "Page should remain interactive after handling confirm box";
    public static final String NAME_INPUT_ACCEPTS_TEXT = "Name input should accept text";
    public static final String ALERT_WITH_EMPTY_NAME = "Alert should display even with empty name field";
    public static final String CONFIRM_WITH_EMPTY_NAME = "Confirm box should display even with empty name field";
    public static final String PAGE_HAS_TITLE = "Page should have a title";
    public static final String ALERT_CONTAINS_EXACT_NAME = "Alert should contain the exact name entered";
    public static final String ALERT_STARTS_WITH_HELLO = "Alert message should start with 'Hello'";
    public static final String ALERT_DISPLAY_MESSAGE_EMPTY_NAME = "Alert should display message even with empty name";
    public static final String NAME_INPUT_DISPLAYED_AFTER_ALERT = "Name input should still be displayed after alert";
    public static final String ENTER_TEXT_AFTER_ALERT = "Should be able to enter new text after handling alert";
    public static final String FIRST_ALERT_CONTAINS_TEXT = "First alert should contain 'First Alert'";
    public static final String SECOND_ALERT_CONTAINS_TEXT = "Second alert should contain 'Second Alert'";
    public static final String ALERT_DISPLAYS_SPECIAL_CHARS = "Alert should display special characters correctly";
    public static final String ALERT_DISPLAYS_LONG_NAMES = "Alert should display long names correctly";
    public static final String CONFIRM_BOX_CONTAINS_NAME = "Confirm box should contain the entered name";
    public static final String CONFIRM_STARTS_WITH_HELLO = "Confirm message should start with 'Hello'";
    public static final String CONFIRM_DISPLAYS_ENTERED_NAME = "Confirm box should display entered name";
    public static final String NO_ALERT_AFTER_ACCEPTING = "No alert should be present after accepting confirm box";
    public static final String CONFIRM_DISPLAYS_MESSAGE_EMPTY_NAME = "Confirm box should display message even with empty name";
    public static final String NAME_INPUT_DISPLAYED_AFTER_CONFIRM = "Name input should still be displayed after confirm box";
    public static final String ENTER_TEXT_AFTER_CONFIRM = "Should be able to enter new text after handling confirm box";
    public static final String FIRST_CONFIRM_CONTAINS_TEXT = "First confirm should contain 'First Confirm'";
    public static final String SECOND_CONFIRM_CONTAINS_TEXT = "Second confirm should contain 'Second Confirm'";
    public static final String CONFIRM_DISPLAYS_SPECIAL_CHARS = "Confirm box should display special characters correctly";
    public static final String NAME_SHOULD_BE_ENTERED = "Name should be entered";
    public static final String NAME_INPUT_EMPTY_AFTER_CLEAR = "Name input should be empty after clearing";
    
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
