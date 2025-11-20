package com.framework.tests.rahulshetty;

import com.framework.pages.WebTablePage;
import com.framework.utils.BaseTest;
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
            "Web table should be displayed on the page");
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
            "Table should have 3 columns");
        softAssert.assertEquals(actualHeaders, expectedHeaders, 
            "Table headers should match expected headers");
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
            "Table should have 3 columns");
        softAssert.assertTrue(rowCount >= 10, 
            "Table should have at least 10 rows (courses)");
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
                "All instructors should be Rahul Shetty");
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify specific course exists")
    @Description("Validate that 'Selenium Webdriver with Java' course is present in the table")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Course Search")
    public void testSpecificCourseExists() {
        String courseName = "Selenium Webdriver with Java Basics + Advanced + Interview Guide";
        boolean courseExists = webTablePage.isCoursePresent(courseName);
        
        softAssert.assertTrue(courseExists, 
            "Course '" + courseName + "' should exist in the table");
        softAssert.assertAll();
    }

    @Test(description = "Verify course price")
    @Description("Validate the price of 'Selenium Webdriver with Java' course")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Price Validation")
    public void testSeleniumCoursePrice() {
        String courseName = "Selenium Webdriver with Java Basics + Advanced + Interview Guide";
        String actualPrice = webTablePage.getPriceByCourse(courseName);
        
        softAssert.assertEquals(actualPrice, "30", 
            "Selenium Webdriver course price should be 30");
        softAssert.assertAll();
    }

    @Test(description = "Verify multiple course prices")
    @Description("Validate prices of different courses in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Price Validation")
    public void testMultipleCoursesPrices() {
        // Verify Selenium course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse("Selenium Webdriver with Java Basics + Advanced + Interview Guide"), 
            "30", "Selenium course price should be 30");
        
        // Verify SQL course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse("Learn SQL in Practical + Database Testing from Scratch"), 
            "25", "SQL course price should be 25");
        
        // Verify Appium course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse("Appium (Selenium) - Mobile Automation Testing from Scratch"), 
            "30", "Appium course price should be 30");
        
        // Verify WebSecurity course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse("WebSecurity Testing for Beginners-QA knowledge to next level"), 
            "20", "WebSecurity course price should be 20");
        
        // Verify JMETER course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse("Learn JMETER from Scratch - (Performance + Load) Testing Tool"), 
            "25", "JMETER course price should be 25");
        
        // Verify REST API course
        softAssert.assertEquals(
            webTablePage.getPriceByCourse("WebServices / REST API Testing with SoapUI"), 
            "35", "REST API course price should be 35");
        
        softAssert.assertAll();
    }

    @Test(description = "Verify free course")
    @Description("Validate that the QA Resume course is free (price = 0)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Price Validation")
    public void testFreeCourse() {
        String courseName = "Write effective QA Resume that will turn to interview call";
        String price = webTablePage.getPriceByCourse(courseName);
        
        softAssert.assertEquals(price, "0", 
            "QA Resume course should be free (price = 0)");
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
                softAssert.assertTrue(true, "Price '" + price + "' is numeric");
            } catch (NumberFormatException e) {
                softAssert.fail("Price '" + price + "' is not a valid number");
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
            "Rahul Shetty should have at least 10 courses");
        softAssert.assertAll();
    }

    @Test(description = "Verify all courses by instructor")
    @Description("Get and validate all courses taught by Rahul Shetty")
    @Severity(SeverityLevel.NORMAL)
    @Story("Course Search")
    public void testAllCoursesByInstructor() {
        List<String> courses = webTablePage.getCoursesByInstructor("Rahul Shetty");
        
        softAssert.assertTrue(courses.size() >= 10, 
            "Should have at least 10 courses");
        softAssert.assertTrue(
            courses.contains("Selenium Webdriver with Java Basics + Advanced + Interview Guide"),
            "Should include Selenium Webdriver course");
        softAssert.assertTrue(
            courses.contains("Learn SQL in Practical + Database Testing from Scratch"),
            "Should include SQL course");
        softAssert.assertAll();
    }

    @Test(description = "Verify specific row data")
    @Description("Validate all data in the first row of the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Row Data Validation")
    public void testSpecificRowData() {
        Map<String, String> rowData = webTablePage.getRowData(1);
        
        softAssert.assertEquals(rowData.get("Instructor"), "Rahul Shetty", 
            "First row instructor should be Rahul Shetty");
        softAssert.assertEquals(rowData.get("Course"), 
            "Selenium Webdriver with Java Basics + Advanced + Interview Guide",
            "First row should contain Selenium course");
        softAssert.assertEquals(rowData.get("Price"), "30", 
            "First row price should be 30");
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
            "Table should have at least 10 rows of data");
        
        // Verify each row has all required columns
        for (Map<String, String> row : allData) {
            softAssert.assertTrue(row.containsKey("Instructor"), 
                "Row should have Instructor column");
            softAssert.assertTrue(row.containsKey("Course"), 
                "Row should have Course column");
            softAssert.assertTrue(row.containsKey("Price"), 
                "Row should have Price column");
            
            // Verify no null or empty values
            softAssert.assertNotNull(row.get("Instructor"), 
                "Instructor should not be null");
            softAssert.assertNotNull(row.get("Course"), 
                "Course should not be null");
            softAssert.assertNotNull(row.get("Price"), 
                "Price should not be null");
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify specific courses exist")
    @Description("Validate that multiple expected courses are present in the table")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Course Search")
    public void testMultipleCoursesExist() {
        List<String> expectedCourses = Arrays.asList(
            "Selenium Webdriver with Java Basics + Advanced + Interview Guide",
            "Learn SQL in Practical + Database Testing from Scratch",
            "Appium (Selenium) - Mobile Automation Testing from Scratch",
            "WebSecurity Testing for Beginners-QA knowledge to next level",
            "Learn JMETER from Scratch - (Performance + Load) Testing Tool",
            "WebServices / REST API Testing with SoapUI",
            "QA Expert Course :Software Testing + Bugzilla + SQL + Agile",
            "Master Selenium Automation in simple Python Language",
            "Advanced Selenium Framework Pageobject, TestNG, Maven, Jenkins,C",
            "Write effective QA Resume that will turn to interview call"
        );
        
        for (String course : expectedCourses) {
            softAssert.assertTrue(webTablePage.isCoursePresent(course), 
                "Course '" + course + "' should exist in the table");
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
            "Number of instructors should match number of courses");
        softAssert.assertEquals(courses.size(), prices.size(), 
            "Number of courses should match number of prices");
        softAssert.assertTrue(instructors.size() >= 10, 
            "Should have at least 10 rows of data");
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
