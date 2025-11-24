package com.framework.tests.rahulshetty;

import com.framework.pages.rahulshetty.WebTablePage;
import com.framework.utils.BaseTest;
import com.framework.utils.TestMessages;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Test class for Web Table validation
 * Tests the course table on Rahul Shetty Academy practice page
 */
@Epic("Web Table Automation")
@Feature("Table Validation")
public class WebTableTest extends BaseTest {

    private WebTablePage webTablePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpPage() {
        initializeHomePage(1); // Rahul Shetty Academy URL
        webTablePage = new WebTablePage(driver);
        webTablePage.scrollToTable();
    }

    @Test(description = "Verify table is displayed on the page")
    @Description("Validate that the Web Table Example is visible on the page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Table Display")
    public void testTableIsDisplayed() {
        softAssert.assertTrue(webTablePage.isTableDisplayed(), 
            TestMessages.WEB_TABLE_DISPLAYED);
        softAssert.assertAll();
    }

    @Test(description = "Verify table headers")
    @Description("Validate that the table has correct headers: Instructor, Course, Price")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Table Structure")
    public void testTableHeaders() {
        List<String> expectedHeaders = Arrays.asList("Instructor", "Course", "Price");
        List<String> actualHeaders = webTablePage.getTableHeaders();
        
        softAssert.assertEquals(actualHeaders.size(), 3, 
            TestMessages.TABLE_SHOULD_HAVE_3_COLUMNS);
        softAssert.assertEquals(actualHeaders, expectedHeaders, 
            TestMessages.TABLE_HEADERS_MATCH);
        softAssert.assertAll();
    }

    @Test(description = "Verify table row and column count")
    @Description("Validate the total number of rows and columns in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Structure")
    public void testTableDimensions() {
        int rowCount = webTablePage.getRowCount();
        int columnCount = webTablePage.getColumnCount();
        
        softAssert.assertEquals(columnCount, 3, 
            TestMessages.TABLE_SHOULD_HAVE_3_COLUMNS);
        softAssert.assertTrue(rowCount >= 10, 
            TestMessages.TABLE_HAS_AT_LEAST_10_ROWS);
        softAssert.assertAll();
    }

    @Test(description = "Verify all instructors are 'Rahul Shetty'")
    @Description("Validate that all courses are taught by Rahul Shetty")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Data Validation")
    public void testAllInstructorsAreRahulShetty() {
        List<String> instructors = webTablePage.getAllInstructors();
        
        for (String instructor : instructors) {
            softAssert.assertEquals(instructor.trim(), "Rahul Shetty", 
                TestMessages.ALL_INSTRUCTORS_RAHUL_SHETTY);
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify specific course exists")
    @Description("Validate that 'Selenium Webdriver with Java' course is present in the table")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Course Search")
    public void testSpecificCourseExists() {
        String courseName = TestMessages.COURSE_SELENIUM_JAVA;
        boolean courseExists = webTablePage.isCoursePresent(courseName);
        
        softAssert.assertTrue(courseExists, 
            TestMessages.format(TestMessages.COURSE_EXISTS, courseName));
        softAssert.assertAll();
    }

    @Test(description = "Verify course price")
    @Description("Validate the price of 'Selenium Webdriver with Java' course")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Price Validation")
    public void testSeleniumCoursePrice() {
        String courseName = TestMessages.COURSE_SELENIUM_JAVA;
        String actualPrice = webTablePage.getPriceByCourse(courseName);
        
        softAssert.assertEquals(actualPrice, "30", 
            TestMessages.SELENIUM_COURSE_PRICE);
        softAssert.assertAll();
    }

    @Test(description = "Verify multiple course prices")
    @Description("Validate prices of different courses in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Price Validation")
    public void testMultipleCoursesPrices() {
        // Verify Selenium course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse(TestMessages.COURSE_SELENIUM_JAVA), 
            "30", TestMessages.SELENIUM_COURSE_PRICE);
        
        // Verify SQL course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse(TestMessages.COURSE_SQL_PRACTICAL), 
            "25", TestMessages.SQL_COURSE_PRICE);
        
        // Verify Appium course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse(TestMessages.COURSE_APPIUM_MOBILE), 
            "30", TestMessages.APPIUM_COURSE_PRICE);
        
        // Verify WebSecurity course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse(TestMessages.COURSE_WEB_SECURITY), 
            "20", TestMessages.WEBSECURITY_COURSE_PRICE);
        
        // Verify JMETER course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse(TestMessages.COURSE_JMETER), 
            "25", TestMessages.JMETER_COURSE_PRICE);
        
        // Verify REST API course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse(TestMessages.COURSE_REST_API), 
            "35", TestMessages.REST_API_COURSE_PRICE);
        
        softAssert.assertAll();
    }

    @Test(description = "Verify free course")
    @Description("Validate that the QA Resume course is free (price = 0)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Price Validation")
    public void testFreeCourse() {
        String courseName = TestMessages.COURSE_QA_RESUME;
        String price = webTablePage.getPriceByCourse(courseName);
        
        softAssert.assertEquals(price, "0", 
            TestMessages.QA_RESUME_COURSE_FREE);
        softAssert.assertAll();
    }

    @Test(description = "Verify all prices are numeric")
    @Description("Validate that all price values in the table are valid numbers")
    @Severity(SeverityLevel.NORMAL)
    @Story("Data Integrity")
    public void testAllPricesAreNumeric() {
        List<String> prices = webTablePage.getAllPrices();
        
        for (String price : prices) {
            try {
                Integer.parseInt(price.trim());
                softAssert.assertTrue(true, TestMessages.format(TestMessages.PRICE_IS_NUMERIC, price));
            } catch (NumberFormatException e) {
                softAssert.fail(TestMessages.format(TestMessages.PRICE_NOT_VALID_NUMBER, price));
            }
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify courses count by instructor")
    @Description("Validate the number of courses offered by Rahul Shetty")
    @Severity(SeverityLevel.NORMAL)
    @Story("Course Count")
    public void testCourseCountByInstructor() {
        int courseCount = webTablePage.getCourseCountByInstructor("Rahul Shetty");
        
        softAssert.assertTrue(courseCount >= 10, 
            TestMessages.RAHUL_SHETTY_AT_LEAST_10_COURSES);
        softAssert.assertAll();
    }

    @Test(description = "Verify all courses by instructor")
    @Description("Get and validate all courses taught by Rahul Shetty")
    @Severity(SeverityLevel.NORMAL)
    @Story("Course Search")
    public void testAllCoursesByInstructor() {
        List<String> courses = webTablePage.getCoursesByInstructor("Rahul Shetty");
        
        softAssert.assertTrue(courses.size() >= 10, 
            TestMessages.SHOULD_HAVE_AT_LEAST_10_COURSES);
        softAssert.assertTrue(
            courses.contains(TestMessages.COURSE_SELENIUM_JAVA),
            TestMessages.SHOULD_INCLUDE_SELENIUM_COURSE);
        softAssert.assertTrue(
            courses.contains(TestMessages.COURSE_SQL_PRACTICAL),
            TestMessages.SHOULD_INCLUDE_SQL_COURSE);
        softAssert.assertAll();
    }

    @Test(description = "Verify specific row data")
    @Description("Validate all data in the first row of the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Row Data Validation")
    public void testSpecificRowData() {
        Map<String, String> rowData = webTablePage.getRowData(1);
        
        softAssert.assertEquals(rowData.get("Instructor"), "Rahul Shetty", 
            TestMessages.FIRST_ROW_INSTRUCTOR);
        softAssert.assertEquals(rowData.get("Course"), 
            TestMessages.COURSE_SELENIUM_JAVA,
            TestMessages.FIRST_ROW_COURSE);
        softAssert.assertEquals(rowData.get("Price"), "30", 
            TestMessages.FIRST_ROW_PRICE);
        softAssert.assertAll();
    }

    @Test(description = "Verify complete table data")
    @Description("Validate all data in the entire table")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Complete Table Validation")
    public void testCompleteTableData() {
        List<Map<String, String>> allData = webTablePage.getAllTableData();
        
        // Verify we have data
        softAssert.assertTrue(allData.size() >= 10, 
            TestMessages.TABLE_HAS_AT_LEAST_10_DATA_ROWS);
        
        // Verify each row has all required columns
        for (Map<String, String> row : allData) {
            softAssert.assertTrue(row.containsKey("Instructor"), 
                TestMessages.ROW_HAS_INSTRUCTOR_COLUMN);
            softAssert.assertTrue(row.containsKey("Course"), 
                TestMessages.ROW_HAS_COURSE_COLUMN);
            softAssert.assertTrue(row.containsKey("Price"), 
                TestMessages.ROW_HAS_PRICE_COLUMN);
            
            // Verify no null or empty values
            softAssert.assertNotNull(row.get("Instructor"), 
                TestMessages.INSTRUCTOR_NOT_NULL);
            softAssert.assertNotNull(row.get("Course"), 
                TestMessages.COURSE_NOT_NULL);
            softAssert.assertNotNull(row.get("Price"), 
                TestMessages.PRICE_NOT_NULL);
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify specific courses exist")
    @Description("Validate that multiple expected courses are present in the table")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Course Search")
    public void testMultipleCoursesExist() {
        List<String> expectedCourses = Arrays.asList(
            TestMessages.COURSE_SELENIUM_JAVA,
            TestMessages.COURSE_SQL_PRACTICAL,
            TestMessages.COURSE_APPIUM_MOBILE,
            TestMessages.COURSE_WEB_SECURITY,
            TestMessages.COURSE_JMETER,
            TestMessages.COURSE_REST_API,
            TestMessages.COURSE_QA_EXPERT,
            TestMessages.COURSE_SELENIUM_PYTHON,
            TestMessages.COURSE_ADVANCED_SELENIUM,
            TestMessages.COURSE_QA_RESUME
        );
        
        for (String course : expectedCourses) {
            softAssert.assertTrue(webTablePage.isCoursePresent(course), 
                TestMessages.format(TestMessages.COURSE_EXISTS, course));
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify column data extraction")
    @Description("Validate that we can extract complete column data")
    @Severity(SeverityLevel.NORMAL)
    @Story("Data Extraction")
    public void testColumnDataExtraction() {
        List<String> instructors = webTablePage.getAllInstructors();
        List<String> courses = webTablePage.getAllCourses();
        List<String> prices = webTablePage.getAllPrices();
        
        softAssert.assertEquals(instructors.size(), courses.size(), 
            TestMessages.INSTRUCTORS_MATCH_COURSES_COUNT);
        softAssert.assertEquals(courses.size(), prices.size(), 
            TestMessages.COURSES_MATCH_PRICES_COUNT);
        softAssert.assertTrue(instructors.size() >= 10, 
            TestMessages.SHOULD_HAVE_AT_LEAST_10_DATA_ROWS);
        softAssert.assertAll();
    }

    @Test(description = "Debug table structure")
    @Description("Debug test to inspect the HTML structure of the table")
    @Severity(SeverityLevel.MINOR)
    @Story("Debug")
    public void testDebugTableStructure() {
        // Find all tables on the page
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        System.out.println("Total tables found: " + tables.size());
        
        // Check for table with name attribute
        List<WebElement> tablesWithName = driver.findElements(By.cssSelector("table[name]"));
        System.out.println("Tables with name attribute: " + tablesWithName.size());
        
        for (WebElement table : tablesWithName) {
            String name = table.getAttribute("name");
            System.out.println("Table name: " + name);
        }
        
        // Try to find the Web Table Example section
        List<WebElement> legendElements = driver.findElements(By.tagName("legend"));
        for (WebElement legend : legendElements) {
            System.out.println("Legend text: " + legend.getText());
        }
        
        // Find the correct table by looking for one with course data
        for (int i = 0; i < tables.size(); i++) {
            WebElement table = tables.get(i);
            String text = table.getText();
            if (text.contains("Instructor") && text.contains("Course") && text.contains("Price")) {
                System.out.println("\n=== Found the Course Table (Index: " + i + ") ===");
                System.out.println("Table HTML (first 500 chars): " + table.getAttribute("outerHTML").substring(0, Math.min(500, table.getAttribute("outerHTML").length())));
                
                // Get table headers
                List<WebElement> headers = table.findElements(By.cssSelector("thead tr th"));
                if (headers.isEmpty()) {
                    headers = table.findElements(By.cssSelector("tr th"));
                }
                if (headers.isEmpty()) {
                    headers = table.findElements(By.cssSelector("tbody tr:first-child td"));
                }
                System.out.println("Header elements found: " + headers.size());
                for (WebElement header : headers) {
                    System.out.println("Header: " + header.getText());
                }
                
                // Get all rows
                List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
                if (rows.isEmpty()) {
                    rows = table.findElements(By.tagName("tr"));
                }
                System.out.println("Total rows: " + rows.size());
                
                if (!rows.isEmpty()) {
                    System.out.println("First row text: " + rows.get(0).getText());
                }
            }
        }
    }

    @Test(description = "Print all courses and prices")
    @Description("Print all course names and their prices to console")
    @Severity(SeverityLevel.MINOR)
    @Story("Debug")
    public void testPrintAllCourses() {
        List<String> courses = webTablePage.getAllCourses();
        System.out.println("\n=== All Courses in Table ===");
        System.out.println("Total courses: " + courses.size());
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". '" + courses.get(i) + "'");
        }
        
        List<String> prices = webTablePage.getAllPrices();
        System.out.println("\n=== All Prices ===");
        for (int i = 0; i < prices.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i) + " -> $" + prices.get(i));
        }
    }
}
