# Selenium Java Maven Framework

A comprehensive Selenium WebDriver test automation framework using Java, Maven, TestNG, and the Page Object Model (POM) pattern with SoftAssert implementation.

## 🏗️ Framework Architecture

### Page Object Model (POM)

This framework implements the Page Object Model pattern with a robust base class structure:

#### **BasePage Class - The Foundation**

The `BasePage` is an **abstract class** that serves as the template for all page objects. Here's why each component is essential:

```java
public abstract class BasePage {
    protected WebDriver driver;
    protected Wait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;                   // 1️⃣
        this.wait = new Wait(driver);           // 2️⃣
        PageFactory.initElements(driver, this); // 3️⃣
        switchToDefaultContent();               // 4️⃣
    }
}
```

**Why Abstract?**
- ❌ Prevents direct instantiation (BasePage is a template, not a real page)
- ✅ Enforces inheritance - all page objects must extend it
- ✅ Centralizes common functionality
- ✅ Follows Template Method Pattern
- ✅ Ensures consistent structure across all page objects

**Constructor Breakdown:**

| Line | Purpose | Why It's Needed |
|------|---------|-----------------|
| **1️⃣ `this.driver = driver;`** | Stores WebDriver instance | Makes driver accessible to all methods in page classes |
| **2️⃣ `this.wait = new Wait(driver);`** | Creates Wait utility instance | Provides reusable explicit wait methods with 10-second default timeout |
| **3️⃣ `PageFactory.initElements(...)`** | Initializes all `@FindBy` elements | Connects WebElement annotations to actual DOM elements |
| **4️⃣ `switchToDefaultContent()`** | Resets to main frame | Ensures clean state, prevents iframe context issues |

**Benefits:**
- ✅ **DRY Principle** - Write initialization once, use everywhere
- ✅ **Consistency** - All pages have identical setup
- ✅ **Maintainability** - Change timeout/config in one place
- ✅ **Reliability** - Automatic frame reset prevents errors
- ✅ **Type Safety** - Polymorphism support for page objects

#### **Wait Utility Class - Centralized Wait Management**

The `Wait` class encapsulates all **explicit wait** and **FluentWait** functionality, promoting **separation of concerns** and **reusability**.

**What are Explicit Waits?**

Explicit waits are intelligent, targeted waits that poll for specific conditions before proceeding. Unlike implicit waits (which apply globally to all elements), explicit waits:
- ✅ Wait for specific conditions (visibility, clickability, presence)
- ✅ Apply to individual elements or scenarios
- ✅ Have configurable timeouts
- ✅ Provide clear exception messages when conditions aren't met
- ✅ Are more reliable than `Thread.sleep()` or implicit waits

**WebDriverWait vs FluentWait:**

| Feature | WebDriverWait | FluentWait |
|---------|--------------|------------|
| **Type** | Specialized implementation | Generic, flexible base class |
| **Polling Interval** | Fixed (500ms default) | Customizable |
| **Exception Handling** | Standard | Can ignore specific exceptions |
| **Use Case** | Common element waits | Advanced scenarios (page load, windows) |
| **Timeout** | Configurable | Configurable |

```java
public class Wait {
    private WebDriverWait wait;  // For standard explicit waits
    private WebDriver driver;    // For FluentWait scenarios

    public Wait(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Element Interaction Methods (with built-in explicit waits)
    public void clickElement(WebElement element) { ... }
    public void sendKeysToElement(WebElement element, String text) { ... }
    public void clearAndSendKeys(WebElement element, String text) { ... }

    // Element State Methods (with explicit wait verification)
    public boolean isElementDisplayed(WebElement element) { ... }
    public boolean isElementEnabled(WebElement element) { ... }
    public boolean isElementSelected(WebElement element) { ... }

    // Data Retrieval Methods (wait until element is visible)
    public String getElementText(WebElement element) { ... }
    public String getElementAttribute(WebElement element, String attribute) { ... }

    // Explicit Wait Methods (WebDriverWait-based)
    public void waitForElementToBeVisible(WebElement element) { ... }
    public void waitForElementToBeClickable(WebElement element) { ... }
    public void waitForFrameAndSwitch(String frameId) { ... }
    
    // FluentWait Methods - Advanced Polling for Complex Scenarios
    public void waitForPageLoad() { ... }
    public void waitForNumberOfWindows(int expectedWindows) { ... }
    public WebElement waitForElementToBeStable(WebElement element) { ... }
    public FluentWait<WebDriver> createFluentWait(long timeout, long polling) { ... }
}
```

**Wait Class Benefits:**
- ✅ **Explicit Waits** - Targeted, condition-based waiting (not global like implicit waits)
- ✅ **Single Responsibility** - Handles only wait logic
- ✅ **Reusability** - Can be used outside page objects if needed
- ✅ **Testability** - Easier to mock and unit test
- ✅ **Flexibility** - Supports custom timeout via constructor overload
- ✅ **Maintainability** - All wait logic centralized in one place
- ✅ **Error Handling** - Built-in exception handling for state checks
- ✅ **Intelligent Waiting** - FluentWait polls conditions instead of hard delays
- ✅ **Performance** - No wasted time waiting when conditions are already met
- ✅ **Clear Failures** - Timeout exceptions show expected condition vs actual state

#### **Example Page Object**

```java
public class HomePage extends BasePage {
    @FindBy(id = "alertbtn")
    private WebElement alertButton;
    
    @FindBy(css = "input[value='radio1']")
    private WebElement radio1Button;
    
    public HomePage(WebDriver driver) {
        super(driver); // Calls BasePage constructor
    }
    
    // Using Wait utility methods
    public void clickAlert() {
        clickElement(alertButton); // Inherited from BasePage
    }
    
    public void clickRadio1() {
        clickElement(radio1Button); // Wait utility handles explicit wait
    }
    
    public boolean isRadio1Selected() {
        return isElementSelected(radio1Button); // Inherited method
    }
}
```

**How It Works:**
1. Test creates page object: `HomePage homePage = new HomePage(driver);`
2. HomePage calls `super(driver)` → BasePage constructor executes
3. Driver stored, Wait utility initialized, PageFactory initializes @FindBy elements
4. Frame reset to default content
5. Page object ready to use with all elements initialized
6. Methods use inherited wait utilities (`clickElement`, `isElementSelected`, etc.)
7. Wait class handles all explicit waits transparently

---

## 🧪 Test Framework Features

### SoftAssert Implementation

All tests use **SoftAssert** instead of hard assertions:

```java
public class RadioButtonTest extends BaseTest {
    @Test
    public void testRadioButtons() {
        softAssert.assertTrue(condition1, "Message 1");
        softAssert.assertTrue(condition2, "Message 2");
        softAssert.assertTrue(condition3, "Message 3");
        softAssert.assertAll(); // Reports all failures at once
    }
}
```

**Benefits:**
- ✅ Tests continue execution after assertion failures
- ✅ Comprehensive failure reports showing all issues
- ✅ Better test coverage - validates all conditions
- ✅ Saves debugging time - see all failures in one run

### FluentWait - Intelligent Waiting

The framework uses **FluentWait** instead of `Thread.sleep()` for reliable, adaptive waiting:

```java
// Page Load Wait - Polls document.readyState until complete
waitForPageToLoad();

// Window Count Wait - Waits for expected number of windows
waitForNumberOfWindows(2);

// Element Stability - Handles stale elements during DOM updates
WebElement stableElement = wait.waitForElementToBeStable(element);
```

**FluentWait vs Thread.sleep:**

| Aspect | Thread.sleep() ❌ | FluentWait ✅ |
|--------|-------------------|---------------|
| **Speed** | Always waits full duration (e.g., 2000ms) | Returns immediately when condition met |
| **Reliability** | Fixed delay, may be too short or too long | Polls until condition met or timeout |
| **Adaptability** | Same on all systems | Adapts to system speed |
| **Resource Usage** | Blocks thread unnecessarily | Efficient polling with intervals |
| **Maintenance** | Hard-coded delays throughout code | Centralized wait logic |

**Example - Page Load Wait:**
```java
// Before: Hard-coded 2-second wait
Thread.sleep(2000); // Always waits 2 seconds, even if page loads in 200ms

// After: Intelligent wait
waitForPageToLoad(); // Returns as soon as document.readyState === "complete"
```

**Implementation Details:**
- **Default Timeout:** 10-30 seconds (configurable)
- **Polling Interval:** 500ms (checks condition twice per second)
- **Exception Handling:** Ignores transient exceptions (NoSuchElementException, StaleElementReferenceException)
- **Document Ready Check:** Uses JavascriptExecutor to poll `document.readyState`

**Benefits:**
- ✅ **Faster Test Execution** - No wasted waiting time
- ✅ **More Reliable** - Adapts to varying page load speeds
- ✅ **Self-Healing** - Handles timing issues automatically
- ✅ **Better Debugging** - Clear timeout exceptions with expected conditions
- ✅ **Cleaner Code** - No try-catch blocks for InterruptedException

---

## 📁 Project Structure

```
selenium-e2e/
├── src/
│   ├── main/
│   │   ├── java/com/framework/
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java          # Abstract base for all pages
│   │   │   │   ├── HomePage.java          # HomePage implementation
│   │   │   │   └── WebTablePage.java      # Web table page object ⭐ NEW
│   │   │   └── utils/
│   │   │       ├── ConfigReader.java      # Configuration management
│   │   │       ├── DriverManager.java     # WebDriver lifecycle
│   │   │       ├── LoggerUtil.java        # Logging utilities
│   │   │       └── Wait.java              # Explicit wait utility class
│   │   └── resources/
│   │       ├── config/
│   │       │   └── config.properties      # Test configurations
│   │       └── logback.xml
│   └── test/
│       ├── java/com/framework/
│       │   ├── tests/
│       │   │   └── rahulshetty/
│       │   │       ├── MouseHoverTest.java     # Mouse hover tests (20 tests) ⭐ NEW
│       │   │       ├── WebTableTest.java       # Web table validation (20 tests)
│       │   │       ├── AlertTest.java          # Alert/confirm box tests (20 tests)
│       │   │       ├── TabSwitchTest.java      # Tab switching tests (12 tests)
│       │   │       ├── WindowSwitchTest.java   # Window switching tests (10 tests)
│       │   │       ├── CheckboxTest.java       # Checkbox tests (12 tests)
│       │   │       ├── DropdownTest.java       # Dropdown tests (8 tests)
│       │   │       ├── SearchBarTest.java      # Autocomplete tests (7 tests)
│       │   │       ├── RadioButtonTest.java    # Radio button tests (6 tests)
│       │   │       └── HomePageTest.java       # Basic page tests (3 tests)
│       │   └── utils/
│       │       ├── BaseTest.java          # Test base class with SoftAssert
│       │       ├── TestMessages.java      # Centralized test messages ⭐ UPDATED
│       │       └── TestListener.java      # TestNG listeners
│       └── resources/testng/
│           ├── testng.xml                 # Master suite (all tests) ⭐ NEW
│           ├── webtable-suite.xml         # Web table tests (20 tests) ⭐ NEW
│           ├── alert-suite.xml            # Alert tests (20 tests)
│           ├── smoke-suite.xml            # Smoke tests (14+ tests)
│           ├── functional-suite.xml       # Functional tests (50+ tests)
│           ├── regression-suite.xml       # Regression tests (60+ tests)
│           └── saucedemo-suite.xml        # SauceDemo tests (disabled)
├── WEB_TABLE_VALIDATION_SUMMARY.md        # Web table test documentation ⭐ NEW
└── pom.xml
```

---

## 🚀 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
# Master Suite (runs all tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/testng.xml

# Mouse Hover Tests (20 mouse hover interaction tests) ⭐ NEW
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/mousehover-suite.xml

# Web Table Tests (20 comprehensive table tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/webtable-suite.xml

# Alert Tests (20 alert and confirm box tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/alert-suite.xml

# Smoke Tests (14+ critical tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/smoke-suite.xml

# Functional Tests (50+ detailed tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/functional-suite.xml

# Regression Tests (60+ comprehensive tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/regression-suite.xml

# SauceDemo Tests (currently disabled)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/saucedemo-suite.xml
```

### Run Specific Test Class
```bash
mvn test -Dtest=MouseHoverTest    # 20 mouse hover tests ⭐ NEW
mvn test -Dtest=WebTableTest      # 20 web table validation tests
mvn test -Dtest=AlertTest         # 20 alert and confirm box tests
mvn test -Dtest=TabSwitchTest     # 12 tab switching tests
mvn test -Dtest=WindowSwitchTest  # 10 window handling tests
mvn test -Dtest=CheckboxTest      # 12 checkbox tests
mvn test -Dtest=DropdownTest      # 8 dropdown tests
mvn test -Dtest=SearchBarTest     # 7 autocomplete tests
mvn test -Dtest=RadioButtonTest   # 6 radio button tests
mvn test -Dtest=HomePageTest      # 3 basic page tests
```

---

## 📝 Configuration

The framework supports multiple base URLs configured in `config.properties`:

```properties
# Browser Configuration
browser=chrome
headless=false

# Application URLs - Access by index: baseUrl.0, baseUrl.1, etc.
baseUrl.0=https://testautomationpractice.blogspot.com
baseUrl.1=https://rahulshettyacademy.com/AutomationPractice
baseUrl.2=https://www.saucedemo.com
baseUrl.3=https://the-internet.herokuapp.com
```

**Usage in Tests:**
```java
driver.get(ConfigReader.getBaseUrl(1)); // Loads baseUrl.1
```

---

## 🧩 Test Examples

### Radio Button Verification Best Practices

The framework includes comprehensive radio button tests demonstrating:

1. **Visibility & State Verification**
   ```java
   softAssert.assertTrue(homePage.isRadio1Displayed(), "Radio1 should be displayed");
   softAssert.assertTrue(homePage.isRadio1Enabled(), "Radio1 should be enabled");
   ```

2. **Selection Verification**
   ```java
   homePage.clickRadio1();
   softAssert.assertTrue(homePage.isRadio1Selected(), "Radio1 should be selected");
   ```

3. **Mutual Exclusivity Testing**
   ```java
   homePage.clickRadio1();
   softAssert.assertTrue(homePage.isRadio1Selected());
   softAssert.assertFalse(homePage.isRadio2Selected());
   softAssert.assertFalse(homePage.isRadio3Selected());
   ```

4. **Switching Behavior**
   ```java
   homePage.clickRadio1();
   homePage.clickRadio2();
   softAssert.assertFalse(homePage.isRadio1Selected(), "Radio1 deselected");
   softAssert.assertTrue(homePage.isRadio2Selected(), "Radio2 selected");
   ```

---

### WebTableTest - Web Table Validation Tests (20 Tests) ⭐ NEW

Comprehensive test suite for validating web table structure, data extraction, and search functionality on Rahul Shetty Academy practice page:

#### Table Structure Tests (3 Tests)
1. **testTableIsDisplayed** - Verifies web table is visible on the page
2. **testTableHeaders** - Validates correct headers (Instructor, Course, Price)
3. **testTableDimensions** - Tests row and column count (3 columns, 10+ rows)

#### Data Validation Tests (3 Tests)
4. **testAllInstructorsAreRahulShetty** - Verifies all courses taught by Rahul Shetty
5. **testAllPricesAreNumeric** - Validates all price values are valid numbers
6. **testCompleteTableData** - Comprehensive validation of all table data

#### Course Search Tests (3 Tests)
7. **testSpecificCourseExists** - Tests searching for specific course (Selenium Java)
8. **testMultipleCoursesExist** - Validates presence of 10 expected courses
9. **testAllCoursesByInstructor** - Gets and validates all courses by instructor

#### Price Validation Tests (4 Tests)
10. **testSeleniumCoursePrice** - Verifies Selenium course price ($30)
11. **testMultipleCoursesPrices** - Tests 6 different course prices
12. **testFreeCourse** - Validates QA Resume course is free (price = 0)
13. **testCourseCountByInstructor** - Verifies Rahul Shetty has 10+ courses

#### Row Data Tests (2 Tests)
14. **testSpecificRowData** - Validates all data in first table row
15. **testColumnDataExtraction** - Tests extracting complete column data

#### Debug Tests (2 Tests)
16. **testDebugTableStructure** - Inspects HTML structure for troubleshooting
17. **testPrintAllCourses** - Prints all courses and prices to console

```java
// Verify table is displayed
boolean isDisplayed = webTablePage.isTableDisplayed();

// Get table headers
List<String> headers = webTablePage.getTableHeaders();
// Returns: ["Instructor", "Course", "Price"]

// Get row and column count
int rowCount = webTablePage.getRowCount();      // 10+
int columnCount = webTablePage.getColumnCount(); // 3

// Get all instructors
List<String> instructors = webTablePage.getAllInstructors();

// Check if course exists
boolean exists = webTablePage.isCoursePresent("Selenium Webdriver with Java...");

// Get price by course name
String price = webTablePage.getPriceByCourse("Learn SQL in Practical...");
// Returns: "25"

// Get all prices
List<String> prices = webTablePage.getAllPrices();

// Get courses by instructor
List<String> courses = webTablePage.getCoursesByInstructor("Rahul Shetty");

// Get specific row data
Map<String, String> rowData = webTablePage.getRowData(1);
// Returns: {"Instructor": "Rahul Shetty", "Course": "Selenium...", "Price": "30"}

// Get all table data
List<Map<String, String>> allData = webTablePage.getAllTableData();
```

**Key Features:**
- ✅ Page Object implementation (WebTablePage.java) with 215 lines of reusable logic
- ✅ Centralized test messages and course names in TestMessages class
- ✅ 31 assertion message constants for consistency
- ✅ 10 course name constants for reusability
- ✅ Tests all table operations: search, filter, extraction, validation
- ✅ Comprehensive data integrity checks
- ✅ Debug utilities for troubleshooting table issues
- ✅ Integration with Allure reporting (@Epic, @Feature, @Story)
- ✅ Detailed documentation in WEB_TABLE_VALIDATION_SUMMARY.md

**Benefits:**
- 🎯 Single source of truth for test data (messages and course names)
- 🔧 Easy maintenance when table structure changes
- 📊 Comprehensive test coverage for table operations
- 🧪 Reusable page object for future table tests
- 📝 Clear, descriptive test names and assertions

---

### MouseHoverTest - Mouse Hover Interaction Tests (20 Tests) ⭐ NEW

Comprehensive test suite for validating mouse hover functionality with dropdown menu interactions on Rahul Shetty Academy practice page:

#### Button State Tests (2 Tests - Smoke)
1. **testMouseHoverButtonDisplayed** - Verifies mouse hover button is visible on page
2. **testMouseHoverButtonEnabled** - Validates button is enabled and interactive

#### Link Visibility Tests (3 Tests)
3. **testTopLinkVisibleOnHover** - Tests "Top" link appears when hovering
4. **testReloadLinkVisibleOnHover** - Tests "Reload" link appears when hovering
5. **testBothLinksVisibleOnHover** - Validates both links visible simultaneously on hover

#### Link Text Validation Tests (2 Tests)
6. **testTopLinkText** - Verifies "Top" link has correct text
7. **testReloadLinkText** - Verifies "Reload" link has correct text

#### Link Attributes Tests (2 Tests)
8. **testTopLinkHasHref** - Validates Top link has href="#top"
9. **testReloadLinkHasHref** - Validates Reload link has href="#reload"

#### Clickability Tests (2 Tests)
10. **testTopLinkClickable** - Tests Top link is clickable when hovering
11. **testReloadLinkClickable** - Tests Reload link is clickable when hovering

#### Click Action Tests (2 Tests)
12. **testClickTopLink** - Verifies clicking Top link scrolls page to top
13. **testClickReloadLink** - Tests clicking Reload link refreshes page functionality

#### State Consistency Tests (4 Tests)
14. **testMultipleHovers** - Tests multiple consecutive hovers work correctly
15. **testHoverStateConsistent** - Validates hover state consistency across multiple hovers
16. **testLinksAppearInCorrectOrder** - Verifies links appear in expected order
17. **testClickTopAndHoverAgain** - Tests clicking Top and hovering again works

#### Additional Tests (3 Tests)
18. **testMouseHoverButtonText** - Validates button has correct text "Mouse Hover"
19. **testTopLinkDisplayedAfterHover** - Verifies Top link visibility after hover
20. **testReloadLinkDisplayedAfterHover** - Verifies Reload link visibility after hover

```java
// Scroll to Mouse Hover section
homePage.scrollToMouseHover();

// Check button state
boolean isDisplayed = homePage.isMouseHoverButtonDisplayed();
boolean isEnabled = homePage.isMouseHoverButtonEnabled();
String buttonText = homePage.getMouseHoverButtonText(); // "Mouse Hover"

// Hover over button to reveal links
homePage.hoverOverMouseHoverButton();

// Check link visibility
boolean topVisible = homePage.isTopLinkDisplayed();
boolean reloadVisible = homePage.isReloadLinkDisplayed();

// Get link text
String topText = homePage.getTopLinkText();       // "Top"
String reloadText = homePage.getReloadLinkText(); // "Reload"

// Get link attributes
String topHref = homePage.getTopLinkHref();       // "#top"
String reloadHref = homePage.getReloadLinkHref(); // "#reload"

// Check if links are clickable
boolean topClickable = homePage.isTopLinkClickable();
boolean reloadClickable = homePage.isReloadLinkClickable();

// Click links
homePage.clickTopLink();    // Scrolls to top of page
homePage.clickReloadLink(); // Reloads page
```

**Key Features:**
- ✅ Actions API integration for mouse hover interactions
- ✅ Centralized scroll logic in @BeforeMethod (eliminates 20 duplicate calls)
- ✅ 26 assertion message constants in TestMessages
- ✅ 3 test data constants in TestConstants (TOP_LINK_TEXT, RELOAD_LINK_TEXT, MOUSE_HOVER_BUTTON_TEXT)
- ✅ Comprehensive validation: visibility, text, href, clickability, and functionality
- ✅ State consistency tests for multiple hover interactions
- ✅ Integration with smoke and regression test suites
- ✅ Uses explicit waits for reliable hover interactions

**Benefits:**
- 🎯 Validates complex mouse hover interactions with dropdown menus
- 🔧 Reusable page object methods for hover functionality
- 📊 Comprehensive test coverage for hover states and link interactions
- 🧪 Tests both link visibility and actual click functionality
- 📝 Clear separation of smoke (2) and regression (18) tests

---

### SearchBarTest - Autocomplete/Suggestion Tests (7 Tests)

Comprehensive autocomplete functionality testing:

1. **testAutocompleteSuggestionsAppear** - Verifies suggestions display when typing
2. **testSelectSuggestionByText** - Tests selecting suggestion by exact text match
3. **testSelectSuggestionByIndex** - Tests selecting suggestion by position
4. **testAutocompleteWithFullCountryName** - Validates full text search
5. **testAutocompleteClearAndReenter** - Tests clearing and re-entering text
6. **testAutocompletePartialMatch** - Verifies partial text matching
7. **testAutocompleteCaseInsensitivity** - Tests case-insensitive search

```java
// Enter text and get suggestions
homePage.enterCountryInAutocomplete("Ind");
boolean hasSuggestions = homePage.isSuggestionListDisplayed();
int count = homePage.getSuggestionCount();

// Select by text match
homePage.selectSuggestionByText("India");

// Select by index
homePage.selectSuggestionByIndex(0);

// Get all suggestions
List<String> allSuggestions = homePage.getAllSuggestions();

// Verify selected value
String value = homePage.getAutocompleteValue();
```

---

### DropdownTest - Dropdown Selection Tests (8 Tests)

Validates dropdown selection functionality:

1. **testDropdownDisplayed** - Verifies dropdown is visible and enabled
2. **testSelectOption1** - Tests selecting "Option1" by visible text
3. **testSelectOption2** - Tests selecting "Option2" by visible text
4. **testSelectOption3** - Tests selecting "Option3" by visible text
5. **testDropdownHasAllOptions** - Verifies all expected options are present
6. **testSelectDropdownByIndex** - Tests selecting option by index position
7. **testSelectDropdownByValue** - Tests selecting option by value attribute
8. **testDefaultSelectOption** - Verifies default "Select" option state

```java
// Select by visible text
homePage.selectDropdownOption("Option1");

// Select by index
homePage.selectDropdownByIndex(2);

// Select by value
homePage.selectDropdownByValue("option2");

// Get selected option
String selected = homePage.getSelectedDropdownOption();

// Get all options
List<String> options = homePage.getAllDropdownOptions();
```

---

### CheckboxTest - Checkbox Interaction Tests (12 Tests)

Comprehensive checkbox testing covering all interaction patterns:

1. **testCheckboxesVisibilityAndState** - Verifies checkboxes are displayed and enabled
2. **testDefaultUncheckedState** - Validates default unchecked state
3. **testCheckCheckbox1/2/3** - Tests checking individual checkboxes
4. **testUncheckCheckbox1** - Tests unchecking functionality
5. **testMultipleCheckboxSelection** - Validates multiple selections
6. **testCheckboxToggleBehavior** - Tests toggle on/off behavior
7. **testIndependentCheckboxBehavior** - Verifies checkboxes work independently
8. **testSelectiveUnchecking** - Tests selective uncheck operations
9. **testSafeCheckMethods** - Validates idempotent check methods
10. **testSafeUncheckMethods** - Validates idempotent uncheck methods

```java
// Click to toggle
homePage.clickCheckbox1();

// Safe check (idempotent - won't uncheck if already checked)
homePage.checkCheckbox1();

// Safe uncheck (idempotent - won't check if already unchecked)
homePage.uncheckCheckbox1();

// Check state
boolean isSelected = homePage.isCheckbox1Selected();
boolean isDisplayed = homePage.isCheckbox1Displayed();
boolean isEnabled = homePage.isCheckbox1Enabled();
```

---

### WindowSwitchTest - Window Handling Tests (10 Tests)

Tests window switching and popup handling:

1. **testOpenWindowButtonVisibilityAndState** - Verifies button is displayed and enabled
2. **testOpenWindowButtonOpensNewWindow** - Tests opening new window
3. **testNewWindowOpensCorrectUrl** - Verifies new window URL (https://www.qaclickacademy.com/)
4. **testNewWindowTitle** - Validates new window has proper title
5. **testSwitchBackToParentWindow** - Tests switching back to parent window
6. **testParentWindowInteractiveAfterOpeningChild** - Verifies parent remains interactive
7. **testClosingChildWindowDoesNotAffectParent** - Tests parent unaffected when child closes
8. **testMultipleWindowOpening** - Validates button reuses same window on multiple clicks
9. **testWindowHandlesAreUnique** - Verifies window handles are unique
10. **testNewWindowHasExpectedContent** - Validates new window content

```java
// Click button to open new window
homePage.clickOpenWindowButton();

// Get window handles
String parentHandle = driver.getWindowHandle();
Set<String> allHandles = driver.getWindowHandles();

// Switch to child window
for (String handle : allHandles) {
    if (!handle.equals(parentHandle)) {
        driver.switchTo().window(handle);
        break;
    }
}

// Verify and close child window
String url = driver.getCurrentUrl();
driver.close();
driver.switchTo().window(parentHandle);
```

---

### AlertTest - Alert and Confirm Box Tests (20 Tests) ⭐ NEW

Comprehensive test suite for JavaScript alert and confirm box functionality with detailed message verification:

#### Alert Button Tests (8 Tests)
1. **testAlertButtonVisibilityAndState** - Verifies alert button is displayed and enabled
2. **testAlertWithEnteredName** - Tests alert popup with user-entered name
3. **testAlertMessageFormat** - Validates alert message format (starts with "Hello")
4. **testAlertWithEmptyName** - Tests alert displays even with empty name field
5. **testPageInteractiveAfterAlert** - Verifies page remains interactive after accepting alert
6. **testMultipleAlertTriggers** - Tests triggering alerts multiple times with different names
7. **testAlertWithSpecialCharacters** - Validates alert handles special characters (@, #, !)
8. **testAlertWithLongName** - Tests alert with very long name input

#### Confirm Box Tests (9 Tests)
9. **testConfirmButtonVisibilityAndState** - Verifies confirm button is displayed and enabled
10. **testConfirmBoxWithEnteredName** - Tests confirm box with user-entered name
11. **testConfirmBoxMessageFormat** - Validates confirm message format (starts with "Hello")
12. **testAcceptConfirmBox** - Tests accepting confirm box (OK button)
13. **testDismissConfirmBox** - Tests dismissing confirm box (Cancel button)
14. **testConfirmBoxWithEmptyName** - Tests confirm box displays even with empty name
15. **testPageInteractiveAfterConfirmBox** - Verifies page remains interactive after handling confirm
16. **testMultipleConfirmBoxTriggers** - Tests multiple confirm boxes with accept/dismiss actions
17. **testConfirmBoxWithSpecialCharacters** - Validates confirm box handles special characters

#### Name Input Field Tests (3 Tests)
18. **testNameInputFieldVisibilityAndState** - Verifies name input is displayed and enabled
19. **testNameInputAcceptsText** - Tests name input field accepts text input
20. **testNameInputCanBeCleared** - Tests clearing the name input field

```java
// Enter name and trigger alert
homePage.enterName("Test User");
homePage.clickAlert();

// Switch to alert and verify message
Alert alert = driver.switchTo().alert();
String alertText = alert.getText();
softAssert.assertTrue(alertText.contains("Test User"));
softAssert.assertTrue(alertText.startsWith("Hello"));

// Accept alert
alert.accept();

// Trigger confirm box
homePage.enterName("Confirm Test");
homePage.clickConfirmBox();

// Handle confirm box - Accept or Dismiss
Alert confirm = driver.switchTo().alert();
String confirmText = confirm.getText();
softAssert.assertTrue(confirmText.contains("Confirm Test"));

// Option 1: Accept (OK button)
confirm.accept();

// Option 2: Dismiss (Cancel button)
confirm.dismiss();

// Verify page is still interactive
softAssert.assertTrue(homePage.isAlertButtonDisplayed());

// Helper method to check alert presence
private boolean isAlertPresent() {
    try {
        driver.switchTo().alert();
        return true;
    } catch (NoAlertPresentException e) {
        return false;
    }
}
```

**Key Features:**
- ✅ Tests alert and confirm box popup messages
- ✅ Verifies message contains entered name
- ✅ Validates message format (starts with "Hello")
- ✅ Tests both accept and dismiss actions on confirm boxes
- ✅ Verifies page remains interactive after handling popups
- ✅ Tests edge cases: empty name, special characters, long text
- ✅ Uses TestConstants for consistent test data
- ✅ Uses TestMessages for standardized assertions
- ✅ Includes helper method for alert presence detection

---

### TabSwitchTest - Tab Switching Tests (12 Tests)

Tests tab opening and navigation functionality:

1. **testOpenTabButtonVisibilityAndState** - Verifies tab button is displayed and enabled
2. **testOpenTabButtonOpensNewTab** - Tests clicking button opens exactly one new tab
3. **testNewTabOpensCorrectUrl** - Verifies new tab opens QA Click Academy URL
4. **testNewTabTitle** - Validates new tab has expected title
5. **testSwitchBackToParentTab** - Tests switching back to original tab
6. **testParentTabInteractiveAfterOpeningChild** - Verifies parent tab remains interactive
7. **testClosingChildTabDoesNotAffectParent** - Tests parent unaffected when child closes
8. **testMultipleTabOpening** - Tests opening multiple tabs sequentially
9. **testTabHandlesAreUnique** - Verifies tab handles are unique identifiers
10. **testNewTabHasExpectedContent** - Validates new tab has substantial page content
11. **testSwitchingBetweenMultipleTabs** - Tests switching between 3+ tabs
12. **testTabCountAfterClosingOneTab** - Verifies tab count after closing child tab

```java
// Get parent tab handle
String originalTab = getParentWindowHandle();

// Click button to open new tab
homePage.clickOpenTabButton();
waitForNumberOfWindows(2);

// Switch to new tab
switchToChildWindow(originalTab);
String newTabUrl = driver.getCurrentUrl();

// Cleanup - close child tabs and switch back to parent
closeAllChildWindowsAndSwitchToParent(originalTab);
```

---

## 📊 Test Results

All tests use SoftAssert and include detailed reporting with organized test suites:

```
Tests run: 121, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Test Breakdown by Class

| Test Class | Test Count | Focus Area |
|------------|-----------|------------|
| **MouseHoverTest** ⭐ NEW | 20 | Mouse hover interactions, Top/Reload link validation |
| **AlertTest** | 20 | Alert popups, Confirm boxes, and message verification |
| **WebTableTest** | 20 | Web table validation, data extraction, and search |
| **TabSwitchTest** | 12 | Tab switching and navigation |
| **WindowSwitchTest** | 10 | Window/popup handling |
| **CheckboxTest** | 12 | Checkbox interactions and state |
| **DropdownTest** | 8 | Dropdown selection methods |
| **SearchBarTest** | 7 | Autocomplete/suggestion functionality |
| **RadioButtonTest** | 6 | Radio button selection and state |
| **HomePageTest** | 3 | Basic page interactions |
| **SauceDemoTest** | 3 | SauceDemo login page tests |
| **Total Tests** | **121** | Comprehensive UI Test Coverage |

### Test Suite Organization

| Suite | Test Count | Purpose | Execution Time |
|-------|-----------|---------|----------------|
| **mousehover-suite.xml** ⭐ NEW | 20 | Mouse hover interaction tests | ~3-4 min |
| **webtable-suite.xml** | 20 | Web table validation tests | ~3-4 min |
| **alert-suite.xml** | 20 | Alert & confirm box tests | ~3-4 min |
| **smoke-suite.xml** | 16+ | Critical path tests | ~2-3 min |
| **functional-suite.xml** | 50+ | Detailed feature tests | ~10-12 min |
| **regression-suite.xml** | 80+ | Comprehensive coverage | ~15-18 min |
| **testng.xml** | All | Master suite (runs all tests) | ~18-22 min |
| **saucedemo-suite.xml** | 3 (disabled) | SauceDemo tests | N/A |

### Test Groups

All tests are tagged with priority-based groups:
- **smoke**: Critical, high-priority tests (13 tests)
- **functional**: Detailed feature validation (35 tests)  
- **regression**: Comprehensive test coverage (63 tests)

---

## 🛠️ Technologies Used

- **Java 21**
- **Selenium WebDriver 4.15.0**
- **TestNG 7.8.0**
- **Maven 3.x**
- **WebDriverManager 5.6.2**
- **Allure Reports 2.25.0** - Beautiful HTML test reports
- **Page Object Model (POM)**
- **SoftAssert Pattern**

---

## 📊 Allure Reports

This framework includes **Allure Reports** for comprehensive test reporting with beautiful visualizations.

### Quick Start

1. **Run tests** (Allure results auto-generated):
   ```bash
   mvn clean test
   ```

2. **View report**:
   ```bash
   mvn allure:serve
   ```

The report opens automatically in your browser with:
- ✅ Test execution dashboard with charts
- ✅ Screenshots on test failures
- ✅ Detailed test steps and logs
- ✅ Timeline view of test execution
- ✅ Historical trends (if history is preserved)

### Installation

**macOS:**
```bash
brew install allure
```

**Windows:**
```bash
scoop install allure
```

For detailed Allure setup and features, see [ALLURE_REPORTS.md](ALLURE_REPORTS.md)

---

## 📚 Additional Resources

- [WEB_TABLE_VALIDATION_SUMMARY.md](WEB_TABLE_VALIDATION_SUMMARY.md) - Web table test documentation ⭐ NEW
- [ALLURE_REPORTS.md](ALLURE_REPORTS.md) - Complete Allure Reports guide
- [USAGE_EXAMPLES.md](USAGE_EXAMPLES.md) - Detailed usage examples
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [TestNG Documentation](https://testng.org/doc/documentation-main.html)
- [Allure Reports](https://docs.qameta.io/allure/)

---

## 🤝 Contributing

This is a learning project. Feel free to explore and enhance the framework!