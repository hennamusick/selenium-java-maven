# Allure Reports Integration Guide

This framework includes **Allure Reports** for comprehensive test reporting with beautiful HTML reports, charts, and detailed test execution information.

## 📊 What is Allure Reports?

Allure Reports provides:
- ✅ Beautiful, interactive HTML reports
- ✅ Test execution timeline
- ✅ Screenshots on test failure
- ✅ Detailed test steps and logs
- ✅ Categorized test results (passed, failed, skipped)
- ✅ Graphs and charts for test trends
- ✅ Retries and flaky tests tracking
- ✅ Easy integration with CI/CD

## 🚀 Quick Start

### Prerequisites

Install Allure command-line tool:

**macOS:**
```bash
brew install allure
```

**Windows:**
```bash
scoop install allure
```

**Linux:**
```bash
sudo apt-add-repository ppa:qameta/allure
sudo apt-get update
sudo apt-get install allure
```

Or download from: https://github.com/allure-framework/allure2/releases

### Verify Installation
```bash
allure --version
```

## 📝 Generating Reports

### Step 1: Run Tests
Run your tests as usual. Allure results will be automatically generated in `target/allure-results/`.

```bash
# Run all tests
mvn clean test

# Run specific suite
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng/smoke-suite.xml

# Run specific test class
mvn clean test -Dtest=HomePageTest
```

### Step 2: Generate and View Report

**Option A: Generate and Open Report (Recommended)**
```bash
# Generate report and open in browser
mvn allure:serve
```
This command:
- Generates the report from `target/allure-results/`
- Starts a local web server
- Opens the report in your default browser
- Server runs on `http://localhost:port`

**Option B: Generate Static Report**
```bash
# Generate report to target/site/allure-maven-plugin/
mvn allure:report

# Open the generated report manually
open target/site/allure-maven-plugin/index.html
```

## 📂 Report Structure

After running tests, you'll see:

```
target/
├── allure-results/              # Raw test results (JSON files)
│   ├── *-result.json           # Test execution results
│   ├── *-container.json        # Test containers/suites
│   └── *-attachment.png        # Screenshots on failure
└── site/
    └── allure-maven-plugin/    # Generated HTML report
        └── index.html          # Main report page
```

## 🎨 Report Features

### Overview Page
- **Total tests**: Passed, failed, skipped counts
- **Success rate**: Percentage of passed tests
- **Duration**: Total execution time
- **Trend charts**: Test results over time

### Suites
- Tests organized by test suites
- Expandable test hierarchy
- Smoke, Functional, Regression suites

### Graphs
- **Status**: Pie chart of test statuses
- **Severity**: Test distribution by priority
- **Duration**: Test execution time breakdown

### Timeline
- Visual timeline of test execution
- Shows parallel test execution
- Helps identify bottlenecks

### Behaviors
- Tests grouped by features
- User stories mapping
- Requirements coverage

## 🔧 Integration Details

### Maven Configuration

The framework is configured with:

**pom.xml:**
```xml
<properties>
    <allure.version>2.25.0</allure.version>
    <aspectj.version>1.9.20.1</aspectj.version>
</properties>

<dependencies>
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-testng</artifactId>
        <version>${allure.version}</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-maven</artifactId>
            <version>2.12.0</version>
        </plugin>
    </plugins>
</build>
```

### Listener Configuration

All test suites use `AllureListener` for automatic screenshot capture on failure:

**TestNG Suite XML:**
```xml
<suite name="Test Suite">
    <listeners>
        <listener class-name="com.framework.utils.AllureListener"/>
    </listeners>
    ...
</suite>
```

### AllureListener Features

- ✅ **Auto Screenshot**: Captures screenshot on test failure
- ✅ **Logs**: Integrates with LoggerUtil
- ✅ **Test Status**: Tracks pass/fail/skip status
- ✅ **Attachments**: Adds screenshots to report

## 📸 Screenshots

Screenshots are automatically captured when tests fail and attached to the Allure report. The framework uses:

```java
@Attachment(value = "Screenshot on Failure", type = "image/png")
public byte[] saveScreenshot(WebDriver driver) {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
}
```

## 🎯 Best Practices

### 1. Clean Results Before Test Run
```bash
# Remove old results before new test run
rm -rf target/allure-results/
mvn clean test
```

### 2. Historical Trends
To track trends over time, keep the `allure-results/history` folder:
```bash
# After generating report, copy history for next run
cp -r target/site/allure-maven-plugin/history target/allure-results/
```

### 3. CI/CD Integration
For GitHub Actions, Jenkins, or other CI tools:
```bash
# Generate report in CI
mvn clean test
mvn allure:report

# Publish the report
# (Use appropriate CI plugin to publish HTML reports)
```

### 4. Environment Information
Add environment info by creating `environment.properties`:
```properties
Browser=Chrome
URL=https://rahulshettyacademy.com/AutomationPractice
Java.Version=21
Selenium.Version=4.15.0
```

## 🔍 Viewing Reports

### Local Development
```bash
# Quick view - generates and opens report
mvn allure:serve
```

### Continuous Integration
```bash
# Generate static report for artifact publishing
mvn allure:report

# Report available at: target/site/allure-maven-plugin/index.html
```

## 📚 Additional Resources

- [Allure Documentation](https://docs.qameta.io/allure/)
- [Allure TestNG](https://docs.qameta.io/allure/#_testng)
- [Allure Maven Plugin](https://docs.qameta.io/allure/#_maven)
- [GitHub - Allure Framework](https://github.com/allure-framework)

## 🎭 Report Categories

Tests in this framework are organized by:

| Category | Count | Description |
|----------|-------|-------------|
| **Smoke** | 11 tests | Critical path tests |
| **Functional** | 35 tests | Feature validation |
| **Regression** | 43 tests | Full coverage |

All categories are visible in the Allure report under "Behaviors" or "Suites" tabs.

## 🐛 Troubleshooting

### Issue: Allure command not found
```bash
# Verify installation
allure --version

# If not installed, install via brew/scoop
brew install allure  # macOS
```

### Issue: No results generated
```bash
# Check if tests ran successfully
ls -la target/allure-results/

# Ensure AspectJ weaver is loaded
mvn clean test -X  # Debug mode
```

### Issue: Report shows old results
```bash
# Clean old results before running tests
mvn clean test
```

## 💡 Pro Tips

1. **View report after every test run**: Use `mvn allure:serve` for instant feedback
2. **Keep history**: Copy history folder to track trends
3. **Add descriptions**: Use `@Description` annotation on test methods
4. **Categorize tests**: Use `@Epic`, `@Feature`, `@Story` for better organization
5. **Link to issues**: Configure issue tracker links in `allure.properties`

---

**Happy Testing with Allure Reports! 🎉**
