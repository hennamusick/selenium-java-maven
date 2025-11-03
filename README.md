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
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;                                          // 1️⃣
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 2️⃣
        PageFactory.initElements(driver, this);                        // 3️⃣
        switchToDefaultContent();                                      // 4️⃣
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
| **2️⃣ `this.wait = new WebDriverWait(...)`** | Creates 10-second explicit wait | Centralizes wait configuration, prevents timeouts |
| **3️⃣ `PageFactory.initElements(...)`** | Initializes all `@FindBy` elements | Connects WebElement annotations to actual DOM elements |
| **4️⃣ `switchToDefaultContent()`** | Resets to main frame | Ensures clean state, prevents iframe context issues |

**Benefits:**
- ✅ **DRY Principle** - Write initialization once, use everywhere
- ✅ **Consistency** - All pages have identical setup
- ✅ **Maintainability** - Change timeout/config in one place
- ✅ **Reliability** - Automatic frame reset prevents errors
- ✅ **Type Safety** - Polymorphism support for page objects

#### **Example Page Object**

```java
public class HomePage extends BasePage {
    @FindBy(id = "alertbtn")
    private WebElement alertButton;
    
    public HomePage(WebDriver driver) {
        super(driver); // Calls BasePage constructor
    }
    
    public void clickAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(alertButton)).click();
    }
}
```

**How It Works:**
1. Test creates page object: `HomePage homePage = new HomePage(driver);`
2. HomePage calls `super(driver)` → BasePage constructor executes
3. Driver stored, wait initialized, PageFactory initializes @FindBy elements
4. Frame reset to default content
5. Page object ready to use with all elements initialized

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
│   │   │       └── LoggerUtil.java        # Logging utilities
│   │   └── resources/
│   │       ├── config/
│   │       │   └── config.properties      # Test configurations
│   │       └── logback.xml
│   └── test/
│       ├── java/com/framework/
│       │   ├── tests/
│       │   │   ├── HomePageTest.java      # HomePage tests
│       │   │   ├── RadioButtonTest.java   # Radio button tests
│       │   │   └── SauceDemoTest.java     # SauceDemo tests
│       │   └── utils/
│       │       ├── BaseTest.java          # Test base class with SoftAssert
│       │       └── TestListener.java      # TestNG listeners
│       └── resources/testng/
│           ├── testng.xml                 # Main test suite
│           ├── radiobutton-suite.xml      # Radio button suite
│           └── saucedemo-suite.xml        # SauceDemo suite
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
# Radio Button Tests
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/radiobutton-suite.xml

# SauceDemo Tests
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/saucedemo-suite.xml
```

### Run Specific Test Class
```bash
mvn test -Dtest=RadioButtonTest
mvn test -Dtest=HomePageTest
mvn test -Dtest=SauceDemoTest
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

## 📊 Test Results

All tests use SoftAssert and include detailed reporting:

```
Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## 🛠️ Technologies Used

- **Java 21**
- **Selenium WebDriver 4.15.0**
- **TestNG 7.8.0**
- **Maven 3.x**
- **WebDriverManager 5.6.2**
- **Page Object Model (POM)**
- **SoftAssert Pattern**

---

## 📚 Additional Resources

- [USAGE_EXAMPLES.md](USAGE_EXAMPLES.md) - Detailed usage examples
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
- [TestNG Documentation](https://testng.org/doc/documentation-main.html)

---

## 🤝 Contributing

This is a learning project. Feel free to explore and enhance the framework!