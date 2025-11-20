# Web Table Validation - Test Automation Summary

## Overview
Successfully created a comprehensive automated test suite to validate the Web Table Example from Rahul Shetty Academy's practice page.

## What Was Tested

### Table from Image
The web table contains course information with the following structure:
- **Instructor**: Rahul Shetty
- **Course**: Various testing and automation courses
- **Price**: Course prices ranging from $0 to $35

### Total Courses Validated: 10
1. Selenium Webdriver with Java Basics + Advanced + Interview Guide - $30
2. Learn SQL in Practical + Database Testing from Scratch - $25
3. Appium (Selenium) - Mobile Automation Testing from Scratch - $30
4. WebSecurity Testing for Beginners-QA knowledge to next level - $20
5. Learn JMETER from Scratch - (Performance + Load) Testing Tool - $25
6. WebServices / REST API Testing with SoapUI - $35
7. QA Expert Course :Software Testing + Bugzilla + SQL + Agile - $25
8. Master Selenium Automation in simple Python Language - $25
9. Advanced Selenium Framework Pageobject, TestNG, Maven, Jenkins,C - $20
10. Write effective QA Resume that will turn to interview call - $0 (Free)

## Files Created

### 1. Page Object Model
**File**: `src/main/java/com/framework/pages/WebTablePage.java`
- Implements complete page object pattern for the web table
- Provides reusable methods for table operations:
  - `getTableHeaders()` - Get all column headers
  - `getRowCount()` - Get total number of data rows
  - `getColumnCount()` - Get total number of columns
  - `getCellValue(row, col)` - Get specific cell value
  - `getRowData(rowIndex)` - Get all data from a specific row
  - `getColumnData(columnName)` - Get all data from a specific column
  - `getAllInstructors()` - Get all instructors
  - `getAllCourses()` - Get all course names
  - `getAllPrices()` - Get all prices
  - `getPriceByCourse(courseName)` - Find price by course name
  - `getCoursesByInstructor(instructorName)` - Get all courses by instructor
  - `isCoursePresent(courseName)` - Check if a course exists
  - `getCourseCountByInstructor(instructorName)` - Count courses by instructor
  - `getAllTableData()` - Get complete table data
  - `isTableDisplayed()` - Verify table visibility
  - `scrollToTable()` - Scroll to table

### 2. Test Class
**File**: `src/test/java/com/framework/tests/rahulshetty/WebTableTest.java`
- 15 comprehensive test cases covering all aspects of the table
- Uses TestNG for test execution
- Uses Allure for reporting with proper annotations
- Uses SoftAssert for multiple assertions per test

### 3. TestNG Suite
**File**: `src/test/resources/testng/webtable-suite.xml`
- Dedicated test suite for running all web table tests

## Test Cases (15 Tests - All Passing ✅)

### 1. Table Display Tests
- ✅ **testTableIsDisplayed**: Verifies table is visible on the page
- ✅ **testTableHeaders**: Validates correct headers (Instructor, Course, Price)
- ✅ **testTableDimensions**: Verifies row and column counts

### 2. Data Validation Tests
- ✅ **testAllInstructorsAreRahulShetty**: Confirms all courses are by Rahul Shetty
- ✅ **testAllPricesAreNumeric**: Validates all price values are valid numbers
- ✅ **testColumnDataExtraction**: Verifies data extraction from columns

### 3. Course Search Tests
- ✅ **testSpecificCourseExists**: Checks if specific course exists
- ✅ **testMultipleCoursesExist**: Validates presence of all 10 expected courses
- ✅ **testCourseCountByInstructor**: Counts courses by instructor
- ✅ **testAllCoursesByInstructor**: Gets all courses by instructor

### 4. Price Validation Tests
- ✅ **testSeleniumCoursePrice**: Validates Selenium course price ($30)
- ✅ **testMultipleCoursesPrices**: Validates prices of 6 different courses
- ✅ **testFreeCourse**: Confirms QA Resume course is free ($0)

### 5. Row Data Tests
- ✅ **testSpecificRowData**: Validates all data in first row
- ✅ **testCompleteTableData**: Validates entire table structure and data

## Test Results
```
Tests run: 15
Failures: 0
Errors: 0
Skipped: 0
Success Rate: 100%
```

## How to Run the Tests

### Run all web table tests:
```bash
mvn test -Dtest=WebTableTest
```

### Run using TestNG suite:
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng/webtable-suite.xml
```

### Run specific test:
```bash
mvn test -Dtest=WebTableTest#testSeleniumCoursePrice
```

### Generate Allure Report:
```bash
mvn clean test -Dtest=WebTableTest
mvn allure:serve
```

## Key Features

### 1. Robust Element Location
- Uses CSS selectors optimized for the table structure
- Handles table without `<thead>` tag (headers in first `<tbody>` row)
- 1-based indexing for user-friendly row/column access

### 2. Reusable Methods
- Generic methods for any table operation
- Type-safe data extraction
- Flexible search and filter capabilities

### 3. Test Organization
- Clear test descriptions with @Description annotations
- Allure @Epic, @Feature, and @Story tags for categorization
- Severity levels for test prioritization
- SoftAsserts for comprehensive validation

### 4. Maintainability
- Page Object Model separates test logic from page structure
- Easy to extend for additional table operations
- Clean, documented code following best practices

## Technical Details

- **Framework**: Selenium WebDriver 4.15.0
- **Test Framework**: TestNG 7.8.0
- **Build Tool**: Maven
- **Java Version**: 21 (LTS)
- **Reporting**: Allure 2.25.0
- **Design Pattern**: Page Object Model
- **Wait Strategy**: Explicit waits with FluentWait

## Notes
- All tests validate against live data from: https://rahulshettyacademy.com/AutomationPractice
- Tests handle whitespace and case-insensitive matching for robust validation
- Table structure was analyzed to handle tbody-only structure (no thead)
- Tests are independent and can run in any order
