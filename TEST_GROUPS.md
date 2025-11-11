# Test Groups and Priority Documentation

This document describes the test grouping strategy and priority numbers used in the framework.

## Test Groups

Tests are organized into three main groups:

### 1. **smoke** - Critical Path Tests
Quick tests that verify critical functionality. Run these first to catch showstopper issues.
- **Purpose**: Verify basic functionality works
- **Execution Time**: Fast (minimal tests)
- **Run Frequency**: Every build, before deployments

### 2. **functional** - Feature Tests
Tests that verify specific features and user workflows work correctly.
- **Purpose**: Verify detailed feature behavior
- **Execution Time**: Medium
- **Run Frequency**: Daily builds, pre-release

### 3. **regression** - Comprehensive Tests
All tests to ensure nothing broke. Most tests belong to this group.
- **Purpose**: Catch regressions across all features
- **Execution Time**: Longest
- **Run Frequency**: Nightly builds, release cycles

## Test Priority Numbers

Priority determines test execution order within a test class (lower number = higher priority).

| Priority | When to Use | Example |
|----------|-------------|---------|
| **1** | Prerequisites, smoke tests, critical validations | Page loads, element visibility |
| **2-3** | Primary feature tests | Core functionality validation |
| **4-5** | Secondary feature tests | Extended functionality |
| **6+** | Edge cases, complex scenarios | State verification, complex workflows |

## Multiple Groups

Tests can belong to multiple groups. Common combinations:

- `groups = {"smoke", "regression"}` - Critical test that's part of regression suite
- `groups = {"functional", "regression"}` - Feature test in regression suite  
- `groups = {"smoke", "functional", "regression"}` - Important test across all suites

## Test Organization by Class

### HomePageTest
| Test Method | Priority | Groups | Description |
|------------|----------|--------|-------------|
| `testAlert()` | 1 | smoke, functional, regression | Alert functionality |
| `testConfirmBox()` | 2 | functional, regression | Confirm box functionality |
| `testNameInput()` | 3 | smoke, regression | Input field validation |

### RadioButtonTest
| Test Method | Priority | Groups | Description |
|------------|----------|--------|-------------|
| `testRadioButtonsVisibilityAndState()` | 1 | smoke, regression | Element visibility & state |
| `testRadio1Selection()` | 2 | functional, regression | Radio1 selection & exclusivity |
| `testRadio2Selection()` | 3 | functional, regression | Radio2 selection & exclusivity |
| `testRadio3Selection()` | 4 | functional, regression | Radio3 selection & exclusivity |
| `testRadioButtonSwitching()` | 5 | functional, regression | Switching behavior |
| `testDefaultRadioButtonState()` | 6 | smoke, functional, regression | Default state verification |

### SauceDemoTest
| Test Method | Priority | Groups | Description |
|------------|----------|--------|-------------|
| `testLoginPageLoads()` | 1 | smoke, regression | Page load validation |
| `testPageTitle()` | 2 | smoke, regression | Title verification |
| `testSuccessfulLogin()` | 3 | smoke, functional, regression | Login flow |

## Running Tests by Group

### Smoke Tests Only
```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/smoke-suite.xml
```
**Expected:** 7 tests (fastest execution)

### Functional Tests Only
```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/functional-suite.xml
```
**Expected:** 8 tests

### Regression Tests (All Tests)
```bash
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng/regression-suite.xml
```
**Expected:** 12 tests (complete suite)

### All Tests (Default)
```bash
mvn test
```
**Expected:** 13 tests (including utility tests)

## Best Practices

### Assigning Priority
1. **Start with 1** for setup/smoke tests
2. **Increment by 1** for each logical test step
3. **Leave gaps** (e.g., 1, 3, 5) for future tests
4. **Group related tests** with similar priorities

### Assigning Groups
1. **smoke**: Only critical path tests (< 30% of tests)
2. **functional**: Feature-specific tests (~ 50% of tests)
3. **regression**: All tests ensuring stability (~ 90% of tests)
4. **Multiple groups**: When test serves multiple purposes

### Group Configuration (`alwaysRun = true`)
All `@BeforeMethod` and `@AfterMethod` annotations use `alwaysRun = true` to ensure setup/teardown runs regardless of group filters:

```java
@BeforeMethod(alwaysRun = true)
public void setUp() {
    // Setup code
}

@AfterMethod(alwaysRun = true)
public void tearDown() {
    // Cleanup code
}
```

## Benefits

✅ **Flexible Test Execution** - Run subsets based on time/requirements  
✅ **Clear Organization** - Easy to understand test purpose  
✅ **Predictable Order** - Priority ensures consistent execution  
✅ **CI/CD Integration** - Different suites for different stages  
✅ **Faster Feedback** - Smoke tests catch critical issues quickly
