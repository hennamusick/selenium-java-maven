package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Page Object for Web Table Example
 * URL: https://rahulshettyacademy.com/AutomationPractice
 */
public class WebTablePage extends BasePage {

    @FindBy(css = "table[name='courses']")
    private WebElement courseTable;

    @FindBy(css = "table[name='courses'] tbody tr:first-child th")
    private List<WebElement> tableHeaders;

    @FindBy(css = "table[name='courses'] tbody tr")
    private List<WebElement> tableRows;

    public WebTablePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get all table headers
     */
    public List<String> getTableHeaders() {
        waitForElementToBeVisible(courseTable);
        List<String> headers = new ArrayList<>();
        for (WebElement header : tableHeaders) {
            headers.add(getElementText(header));
        }
        return headers;
    }

    /**
     * Get total number of rows in the table (excluding header)
     */
    public int getRowCount() {
        waitForElementToBeVisible(courseTable);
        return tableRows.size() - 1; // Subtract 1 for header row
    }

    /**
     * Get total number of columns in the table
     */
    public int getColumnCount() {
        waitForElementToBeVisible(courseTable);
        return tableHeaders.size();
    }

    /**
     * Get cell value by row and column index
     * @param rowIndex Row index (1-based, starting from first data row after header)
     * @param columnIndex Column index (1-based)
     */
    public String getCellValue(int rowIndex, int columnIndex) {
        waitForElementToBeVisible(courseTable);
        // Add 1 to rowIndex because row 1 in tbody is the header
        int actualRowIndex = rowIndex + 1;
        WebElement cell = driver.findElement(
            By.cssSelector("table[name='courses'] tbody tr:nth-child(" + actualRowIndex + ") td:nth-child(" + columnIndex + ")")
        );
        return getElementText(cell);
    }

    /**
     * Get all data from a specific row
     * @param rowIndex Row index (1-based)
     * @return Map with column name as key and cell value as value
     */
    public Map<String, String> getRowData(int rowIndex) {
        waitForElementToBeVisible(courseTable);
        Map<String, String> rowData = new HashMap<>();
        List<String> headers = getTableHeaders();
        
        for (int i = 0; i < headers.size(); i++) {
            String cellValue = getCellValue(rowIndex, i + 1);
            rowData.put(headers.get(i), cellValue);
        }
        return rowData;
    }

    /**
     * Get all data from a specific column
     * @param columnName Column header name
     */
    public List<String> getColumnData(String columnName) {
        waitForElementToBeVisible(courseTable);
        List<String> headers = getTableHeaders();
        int headerIndex = headers.indexOf(columnName);
        if (headerIndex == -1) {
            return new ArrayList<>(); // Column not found
        }
        int columnIndex = headerIndex + 1; // nth-child is 1-based
        
        List<String> columnData = new ArrayList<>();
        for (int i = 1; i <= getRowCount(); i++) {
            columnData.add(getCellValue(i, columnIndex));
        }
        return columnData;
    }

    /**
     * Get all instructors from the table
     */
    public List<String> getAllInstructors() {
        return getColumnData("Instructor");
    }

    /**
     * Get all course names from the table
     */
    public List<String> getAllCourses() {
        return getColumnData("Course");
    }

    /**
     * Get all prices from the table
     */
    public List<String> getAllPrices() {
        return getColumnData("Price");
    }

    /**
     * Find course price by course name
     * @param courseName Name of the course
     * @return Price of the course or null if not found
     */
    public String getPriceByCourse(String courseName) {
        waitForElementToBeVisible(courseTable);
        int rowCount = getRowCount();
        
        for (int i = 1; i <= rowCount; i++) {
            String course = getCellValue(i, 2); // Column 2 is Course (Instructor=1, Course=2, Price=3)
            if (course.trim().equalsIgnoreCase(courseName.trim())) {
                return getCellValue(i, 3); // Column 3 is Price
            }
        }
        return null;
    }

    /**
     * Get all courses by instructor
     * @param instructorName Name of the instructor
     */
    public List<String> getCoursesByInstructor(String instructorName) {
        waitForElementToBeVisible(courseTable);
        List<String> courses = new ArrayList<>();
        int rowCount = getRowCount();
        
        for (int i = 1; i <= rowCount; i++) {
            String instructor = getCellValue(i, 1); // Column 1 is Instructor
            if (instructor.trim().equalsIgnoreCase(instructorName.trim())) {
                courses.add(getCellValue(i, 2)); // Column 2 is Course
            }
        }
        return courses;
    }

    /**
     * Verify if a course exists in the table
     * @param courseName Name of the course
     */
    public boolean isCoursePresent(String courseName) {
        return getAllCourses().stream()
                .anyMatch(course -> course.trim().equalsIgnoreCase(courseName.trim()));
    }

    /**
     * Get the count of courses by specific instructor
     */
    public int getCourseCountByInstructor(String instructorName) {
        return (int) getAllInstructors().stream()
                .filter(instructor -> instructor.trim().equalsIgnoreCase(instructorName.trim()))
                .count();
    }

    /**
     * Get all table data as a list of maps
     */
    public List<Map<String, String>> getAllTableData() {
        waitForElementToBeVisible(courseTable);
        List<Map<String, String>> allData = new ArrayList<>();
        
        for (int i = 1; i <= getRowCount(); i++) {
            allData.add(getRowData(i));
        }
        return allData;
    }

    /**
     * Verify if the table is displayed
     */
    public boolean isTableDisplayed() {
        return isElementDisplayed(courseTable);
    }

    /**
     * Scroll to the table
     */
    public void scrollToTable() {
        waitForElementToBeVisible(courseTable);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", courseTable);
    }
}
