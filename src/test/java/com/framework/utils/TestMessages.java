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
    
    // ==================== HIDE/SHOW MESSAGES ====================
    public static final String HIDE_BUTTON_DISPLAYED = "Hide button should be displayed";
    public static final String SHOW_BUTTON_DISPLAYED = "Show button should be displayed";
    public static final String HIDE_BUTTON_ENABLED = "Hide button should be enabled";
    public static final String SHOW_BUTTON_ENABLED = "Show button should be enabled";
    public static final String TEXTBOX_DISPLAYED_INITIALLY = "Text box should be displayed initially";
    public static final String TEXTBOX_HIDDEN_AFTER_HIDE = "Text box should be hidden after clicking Hide button";
    public static final String TEXTBOX_VISIBLE_AFTER_SHOW = "Text box should be visible after clicking Show button";
    public static final String TEXTBOX_HIDDEN = "Text box should be hidden";
    public static final String TEXTBOX_VISIBLE = "Text box should be visible";
    public static final String TEXTBOX_ACCEPTS_INPUT = "Text box should accept input when visible";
    public static final String TEXTBOX_RETAINS_VALUE_AFTER_HIDE = "Text box should retain value after hide/show";
    public static final String HIDE_BUTTON_REMAINS_VISIBLE = "Hide button should remain visible";
    public static final String SHOW_BUTTON_REMAINS_VISIBLE = "Show button should remain visible";
    public static final String MULTIPLE_HIDE_CLICKS_WORK = "Clicking Hide button multiple times should work";
    public static final String MULTIPLE_SHOW_CLICKS_WORK = "Clicking Show button multiple times should work";
    public static final String TEXTBOX_STILL_HIDDEN = "Text box should still be hidden";
    public static final String TEXTBOX_STILL_VISIBLE = "Text box should still be visible";
    public static final String TEXTBOX_HIDDEN_BY_DEFAULT = "Text box visibility state";
    public static final String TEXTBOX_VISIBLE_BY_DEFAULT = "Text box should be visible by default";
    public static final String TEXTBOX_ENABLED_WHEN_VISIBLE = "Text box should be enabled when visible";
    public static final String TEXTBOX_VALUE_PERSISTS = "Text box value should persist after hide/show cycle";
    public static final String TEXTBOX_CLEARED_SUCCESSFULLY = "Text box should be cleared successfully";
    
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
    
    // ==================== WEB TABLE MESSAGES ====================
    public static final String WEB_TABLE_DISPLAYED = "Web table should be displayed on the page";
    public static final String TABLE_SHOULD_HAVE_3_COLUMNS = "Table should have 3 columns";
    public static final String TABLE_HEADERS_MATCH = "Table headers should match expected headers";
    public static final String TABLE_HAS_AT_LEAST_10_ROWS = "Table should have at least 10 rows (courses)";
    public static final String ALL_INSTRUCTORS_RAHUL_SHETTY = "All instructors should be Rahul Shetty";
    public static final String COURSE_EXISTS = "Course '%s' should exist in the table";
    public static final String SELENIUM_COURSE_PRICE = "Selenium Webdriver course price should be 30";
    public static final String SQL_COURSE_PRICE = "SQL course price should be 25";
    public static final String APPIUM_COURSE_PRICE = "Appium course price should be 30";
    public static final String WEBSECURITY_COURSE_PRICE = "WebSecurity course price should be 20";
    public static final String JMETER_COURSE_PRICE = "JMETER course price should be 25";
    public static final String REST_API_COURSE_PRICE = "REST API course price should be 35";
    public static final String QA_RESUME_COURSE_FREE = "QA Resume course should be free (price = 0)";
    public static final String PRICE_IS_NUMERIC = "Price '%s' is numeric";
    public static final String PRICE_NOT_VALID_NUMBER = "Price '%s' is not a valid number";
    public static final String RAHUL_SHETTY_AT_LEAST_10_COURSES = "Rahul Shetty should have at least 10 courses";
    public static final String SHOULD_HAVE_AT_LEAST_10_COURSES = "Should have at least 10 courses";
    public static final String SHOULD_INCLUDE_SELENIUM_COURSE = "Should include Selenium Webdriver course";
    public static final String SHOULD_INCLUDE_SQL_COURSE = "Should include SQL course";
    public static final String FIRST_ROW_INSTRUCTOR = "First row instructor should be Rahul Shetty";
    public static final String FIRST_ROW_COURSE = "First row should contain Selenium course";
    public static final String FIRST_ROW_PRICE = "First row price should be 30";
    public static final String TABLE_HAS_AT_LEAST_10_DATA_ROWS = "Table should have at least 10 rows of data";
    public static final String ROW_HAS_INSTRUCTOR_COLUMN = "Row should have Instructor column";
    public static final String ROW_HAS_COURSE_COLUMN = "Row should have Course column";
    public static final String ROW_HAS_PRICE_COLUMN = "Row should have Price column";
    public static final String INSTRUCTOR_NOT_NULL = "Instructor should not be null";
    public static final String COURSE_NOT_NULL = "Course should not be null";
    public static final String PRICE_NOT_NULL = "Price should not be null";
    public static final String INSTRUCTORS_MATCH_COURSES_COUNT = "Number of instructors should match number of courses";
    public static final String COURSES_MATCH_PRICES_COUNT = "Number of courses should match number of prices";
    public static final String SHOULD_HAVE_AT_LEAST_10_DATA_ROWS = "Should have at least 10 rows of data";
    
    // ==================== COURSE NAME CONSTANTS ====================
    public static final String COURSE_SELENIUM_JAVA = "Selenium Webdriver with Java Basics + Advanced + Interview Guide";
    public static final String COURSE_SQL_PRACTICAL = "Learn SQL in Practical + Database Testing from Scratch";
    public static final String COURSE_APPIUM_MOBILE = "Appium (Selenium) - Mobile Automation Testing from Scratch";
    public static final String COURSE_WEB_SECURITY = "WebSecurity Testing for Beginners-QA knowledge to next level";
    public static final String COURSE_JMETER = "Learn JMETER from Scratch - (Performance + Load) Testing Tool";
    public static final String COURSE_REST_API = "WebServices / REST API Testing with SoapUI";
    public static final String COURSE_QA_EXPERT = "QA Expert Course :Software Testing + Bugzilla + SQL + Agile";
    public static final String COURSE_SELENIUM_PYTHON = "Master Selenium Automation in simple Python Language";
    public static final String COURSE_ADVANCED_SELENIUM = "Advanced Selenium Framework Pageobject, TestNG, Maven, Jenkins,C";
    public static final String COURSE_QA_RESUME = "Write effective QA Resume that will turn to interview call";
    
    // ==================== FIXED HEADER TABLE MESSAGES ====================
    public static final String FIXED_TABLE_DISPLAYED = "Fixed header table should be displayed";
    public static final String FIXED_TABLE_HAS_4_COLUMNS = "Fixed table should have 4 columns";
    public static final String FIXED_TABLE_HEADERS_MATCH = "Fixed table headers should match expected values";
    public static final String FIXED_TABLE_ROW_COUNT = "Fixed table should have at least 5 rows";
    public static final String TOTAL_AMOUNT_DISPLAYED = "Total amount should be displayed";
    public static final String TOTAL_AMOUNT_CORRECT = "Total amount should be correctly calculated";
    public static final String CALCULATED_TOTAL_MATCHES = "Calculated total should match displayed total";
    public static final String PERSON_EXISTS_IN_TABLE = "Person %s should exist in the table";
    public static final String PERSON_POSITION_CORRECT = "Person %s should have position %s";
    public static final String PERSON_CITY_CORRECT = "Person %s should be from city %s";
    public static final String PERSON_AMOUNT_CORRECT = "Person %s should have amount %d";
    public static final String ALL_NAMES_PRESENT = "All expected names should be present in the table";
    public static final String ALL_POSITIONS_VALID = "All positions should be non-empty";
    public static final String ALL_CITIES_VALID = "All cities should be non-empty";
    public static final String ALL_AMOUNTS_POSITIVE = "All amounts should be positive numbers";
    public static final String ROW_DATA_COMPLETE = "Row %d should have complete data";
    public static final String NAME_NOT_NULL = "Name should not be null";
    public static final String POSITION_NOT_NULL = "Position should not be null";
    public static final String CITY_NOT_NULL = "City should not be null";
    public static final String AMOUNT_NOT_NULL = "Amount should not be null";
    public static final String AMOUNT_IS_NUMERIC = "Amount '%s' should be numeric";
    public static final String FIXED_TABLE_HEADER_NAME = "First header should be 'Name'";
    public static final String FIXED_TABLE_HEADER_POSITION = "Second header should be 'Position'";
    public static final String FIXED_TABLE_HEADER_CITY = "Third header should be 'City'";
    public static final String FIXED_TABLE_HEADER_AMOUNT = "Fourth header should be 'Amount'";
    public static final String NAMES_COUNT_MATCH_POSITIONS = "Names count should match positions count";
    public static final String POSITIONS_COUNT_MATCH_CITIES = "Positions count should match cities count";
    public static final String CITIES_COUNT_MATCH_AMOUNTS = "Cities count should match amounts count";
    public static final String SHOULD_HAVE_AT_LEAST_5_ROWS = "Should have at least 5 rows of data";
    public static final String ROW1_COL1_SHOULD_BE_ALEX = "Row 1, Column 1 should be Alex";
    public static final String ROW1_COL2_SHOULD_BE_ENGINEER = "Row 1, Column 2 should be Engineer";
    public static final String ROW1_COL3_SHOULD_BE_CHENNAI = "Row 1, Column 3 should be Chennai";
    public static final String ROW1_COL4_SHOULD_BE_28 = "Row 1, Column 4 should be 28";
    public static final String AT_LEAST_2_ENGINEERS = "There should be at least 2 Engineers (Alex and Jack)";
    public static final String CHENNAI_APPEARS_TWICE = "Chennai should appear at least twice (Alex and Ivory)";
    
    // ==================== MOUSE HOVER MESSAGES ====================
    public static final String MOUSE_HOVER_BUTTON_DISPLAYED = "Mouse Hover button should be displayed";
    public static final String MOUSE_HOVER_BUTTON_ENABLED = "Mouse Hover button should be enabled";
    public static final String TOP_LINK_VISIBLE_ON_HOVER = "Top link should be visible when hovering over Mouse Hover button";
    public static final String RELOAD_LINK_VISIBLE_ON_HOVER = "Reload link should be visible when hovering over Mouse Hover button";
    public static final String TOP_LINK_NOT_VISIBLE_BEFORE_HOVER = "Top link should not be visible before hovering";
    public static final String RELOAD_LINK_NOT_VISIBLE_BEFORE_HOVER = "Reload link should not be visible before hovering";
    public static final String TOP_LINK_CLICKABLE = "Top link should be clickable";
    public static final String RELOAD_LINK_CLICKABLE = "Reload link should be clickable";
    public static final String TOP_LINK_HAS_CORRECT_TEXT = "Top link should have text 'Top'";
    public static final String RELOAD_LINK_HAS_CORRECT_TEXT = "Reload link should have text 'Reload'";
    public static final String TOP_LINK_HAS_HREF = "Top link should have href attribute";
    public static final String RELOAD_LINK_HAS_HREF = "Reload link should have href attribute";
    public static final String LINKS_HIDDEN_AFTER_MOUSE_OUT = "Links should be hidden after mouse moves away";
    public static final String BOTH_LINKS_VISIBLE_ON_HOVER = "Both Top and Reload links should be visible on hover";
    public static final String HOVER_MENU_DISAPPEARS = "Hover menu should disappear when mouse moves away";
    public static final String TOP_LINK_HREF_CORRECT = "Top link href should be '#top'";
    public static final String RELOAD_LINK_HREF_CORRECT = "Reload link href should be '#reload'";
    public static final String PAGE_SCROLLS_TO_TOP = "Page should scroll to top after clicking Top link";
    public static final String MULTIPLE_HOVERS_WORK = "Multiple consecutive hovers should work correctly";
    public static final String HOVER_STATE_CONSISTENT = "Hover state should be consistent across multiple interactions";
    public static final String TOP_LINK_DISPLAYED_ON_HOVER = "Top link should be displayed when hovering";
    public static final String RELOAD_LINK_DISPLAYED_ON_HOVER = "Reload link should be displayed when hovering";
    public static final String LINKS_APPEAR_IN_CORRECT_ORDER = "Links should appear in correct order (Top, Reload)";
    public static final String MOUSE_HOVER_SECTION_FUNCTIONAL = "Mouse hover section should be functional";
    
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
