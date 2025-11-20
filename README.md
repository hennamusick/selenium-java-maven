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
│   │   │   │   └── HomePage.java          # HomePage implementation
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
│       │   │       ├── HomePageTest.java       # HomePage tests (alerts, inputs)
│       │   │       ├── RadioButtonTest.java    # Radio button tests (6 tests)
│       │   │       ├── SearchBarTest.java      # Autocomplete/search tests (7 tests)
│       │   │       ├── DropdownTest.java       # Dropdown tests (8 tests)
│       │   │       ├── CheckboxTest.java       # Checkbox tests (12 tests)
│       │   │       └── WindowSwitchTest.java   # Window switching tests (10 tests)
│       │   └── utils/
│       │       ├── BaseTest.java          # Test base class with SoftAssert
│       │       └── TestListener.java      # TestNG listeners
│       └── resources/testng/
│           ├── smoke-suite.xml            # Smoke tests (11 tests)
│           ├── functional-suite.xml       # Functional tests (35 tests)
│           ├── regression-suite.xml       # Regression tests (43 tests)
│           └── saucedemo-suite.xml        # SauceDemo tests (disabled)
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
# Smoke Tests (11 critical tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/smoke-suite.xml

# Functional Tests (35 detailed tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/functional-suite.xml

# Regression Tests (43 comprehensive tests)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/regression-suite.xml

# SauceDemo Tests (currently disabled)
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/saucedemo-suite.xml
```

### Run Specific Test Class
```bash
mvn test -Dtest=HomePageTest
mvn test -Dtest=RadioButtonTest
mvn test -Dtest=SearchBarTest
mvn test -Dtest=DropdownTest
mvn test -Dtest=CheckboxTest
mvn test -Dtest=WindowSwitchTest
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

## 📊 Test Results

All tests use SoftAssert and include detailed reporting with organized test suites:

```
Tests run: 46, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Test Breakdown by Class

| Test Class | Test Count | Focus Area |
|------------|-----------|------------|
| **HomePageTest** | 3 | Alert, Confirm Box, Name Input |
| **RadioButtonTest** | 6 | Radio button selection and state |
| **SearchBarTest** | 7 | Autocomplete/suggestion functionality |
| **DropdownTest** | 8 | Dropdown selection methods |
| **CheckboxTest** | 12 | Checkbox interactions and state |
| **WindowSwitchTest** | 10 | Window/popup handling |
| **Total Active Tests** | **46** | Rahul Shetty Academy Practice |

### Test Suite Organization

| Suite | Test Count | Purpose | Execution Time |
|-------|-----------|---------|----------------|
| **smoke-suite.xml** | 11 | Critical path tests | ~2-3 min |
| **functional-suite.xml** | 35 | Detailed feature tests | ~8-10 min |
| **regression-suite.xml** | 43 | Comprehensive coverage | ~10-12 min |
| **saucedemo-suite.xml** | 3 (disabled) | SauceDemo tests | N/A |

### Test Groups

All tests are tagged with priority-based groups:
- **smoke**: Critical, high-priority tests (11 tests)
- **functional**: Detailed feature validation (35 tests)  
- **regression**: Comprehensive test coverage (43 tests)

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

- [ALLURE_REPORTS.md](ALLURE_REPORTS.md) - Complete Allure Reports guide
- [USAGE_EXAMPLES.md](USAGE_EXAMPLES.md) - Detailed usage examples
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [TestNG Documentation](https://testng.org/doc/documentation-main.html)
- [Allure Reports](https://docs.qameta.io/allure/)

---

## 🤝 Contributing

This is a learning project. Feel free to explore and enhance the framework!