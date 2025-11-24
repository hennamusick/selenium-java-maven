package com.framework.tests.rahulshetty;

import com.framework.pages.rahulshetty.HomePage;
import com.framework.utils.BaseTest;
import com.framework.utils.rahulshetty.RahulShettyConstants;
import com.framework.utils.rahulshetty.RahulShettyMessages;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Test class for Fixed Header Table validation
 * Tests the fixed header table on Rahul Shetty Academy practice page
 */
@Epic("Web Table Automation")
@Feature("Fixed Header Table")
public class FixedHeaderTableTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpPage() {
        initializeHomePage(1); // Rahul Shetty Academy URL
        homePage = new HomePage(driver);
        homePage.scrollToFixedHeaderTable();
    }

    @Test(description = "Verify fixed header table is displayed", groups = {"smoke"})
    @Description("Validate that the Fixed Header Table is visible on the page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Table Display")
    public void testFixedTableIsDisplayed() {
        softAssert.assertTrue(homePage.isFixedHeaderTableDisplayed(), 
            RahulShettyMessages.FIXED_TABLE_DISPLAYED);
        softAssert.assertAll();
    }

    @Test(description = "Verify table headers are correct", groups = {"smoke"})
    @Description("Validate that the table has correct headers: Name, Position, City, Amount")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Table Structure")
    public void testTableHeaders() {
        List<String> expectedHeaders = Arrays.asList("Name", "Position", "City", "Amount");
        List<String> actualHeaders = homePage.getFixedTableHeaders();
        
        softAssert.assertEquals(actualHeaders.size(), 4, 
            RahulShettyMessages.FIXED_TABLE_HAS_4_COLUMNS);
        softAssert.assertEquals(actualHeaders, expectedHeaders, 
            RahulShettyMessages.FIXED_TABLE_HEADERS_MATCH);
        softAssert.assertEquals(actualHeaders.get(0), "Name", 
            RahulShettyMessages.FIXED_TABLE_HEADER_NAME);
        softAssert.assertEquals(actualHeaders.get(1), "Position", 
            RahulShettyMessages.FIXED_TABLE_HEADER_POSITION);
        softAssert.assertEquals(actualHeaders.get(2), "City", 
            RahulShettyMessages.FIXED_TABLE_HEADER_CITY);
        softAssert.assertEquals(actualHeaders.get(3), "Amount", 
            RahulShettyMessages.FIXED_TABLE_HEADER_AMOUNT);
        softAssert.assertAll();
    }

    @Test(description = "Verify table row count")
    @Description("Validate the total number of rows in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Structure")
    public void testTableRowCount() {
        int rowCount = homePage.getFixedTableRowCount();
        
        softAssert.assertTrue(rowCount >= 5, 
            RahulShettyMessages.FIXED_TABLE_ROW_COUNT);
        softAssert.assertAll();
    }

    @Test(description = "Verify total amount is displayed")
    @Description("Validate that the total amount collected is displayed below the table")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Total Calculation")
    public void testTotalAmountDisplayed() {
        softAssert.assertTrue(homePage.isTotalAmountDisplayed(), 
            RahulShettyMessages.TOTAL_AMOUNT_DISPLAYED);
        softAssert.assertAll();
    }

    @Test(description = "Verify total amount calculation is correct")
    @Description("Validate that the displayed total matches the sum of all amounts")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Total Calculation")
    public void testTotalAmountCalculation() {
        int calculatedTotal = homePage.calculateTotalAmount();
        String displayedTotal = homePage.getDisplayedTotalAmount();
        
        softAssert.assertEquals(calculatedTotal, RahulShettyConstants.EXPECTED_TOTAL_AMOUNT, 
            RahulShettyMessages.TOTAL_AMOUNT_CORRECT);
        softAssert.assertEquals(displayedTotal, String.valueOf(RahulShettyConstants.EXPECTED_TOTAL_AMOUNT), 
            RahulShettyMessages.CALCULATED_TOTAL_MATCHES);
        softAssert.assertAll();
    }

    @Test(description = "Verify Alex's data")
    @Description("Validate all data for Alex in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Person Data Validation")
    public void testAlexData() {
        softAssert.assertTrue(homePage.isPersonPresent(RahulShettyConstants.PERSON_ALEX), 
            RahulShettyMessages.format(RahulShettyMessages.PERSON_EXISTS_IN_TABLE, RahulShettyConstants.PERSON_ALEX));
        softAssert.assertEquals(homePage.getPositionByName(RahulShettyConstants.PERSON_ALEX), 
            RahulShettyConstants.POSITION_ENGINEER,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_POSITION_CORRECT, RahulShettyConstants.PERSON_ALEX, RahulShettyConstants.POSITION_ENGINEER));
        softAssert.assertEquals(homePage.getCityByName(RahulShettyConstants.PERSON_ALEX), 
            RahulShettyConstants.CITY_CHENNAI,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_CITY_CORRECT, RahulShettyConstants.PERSON_ALEX, RahulShettyConstants.CITY_CHENNAI));
        softAssert.assertEquals(homePage.getAmountByName(RahulShettyConstants.PERSON_ALEX), 
            RahulShettyConstants.AMOUNT_ALEX,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_AMOUNT_CORRECT, RahulShettyConstants.PERSON_ALEX, RahulShettyConstants.AMOUNT_ALEX));
        softAssert.assertAll();
    }

    @Test(description = "Verify Ben's data")
    @Description("Validate all data for Ben in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Person Data Validation")
    public void testBenData() {
        softAssert.assertTrue(homePage.isPersonPresent(RahulShettyConstants.PERSON_BEN), 
            RahulShettyMessages.format(RahulShettyMessages.PERSON_EXISTS_IN_TABLE, RahulShettyConstants.PERSON_BEN));
        softAssert.assertEquals(homePage.getPositionByName(RahulShettyConstants.PERSON_BEN), 
            RahulShettyConstants.POSITION_MECHANIC,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_POSITION_CORRECT, RahulShettyConstants.PERSON_BEN, RahulShettyConstants.POSITION_MECHANIC));
        softAssert.assertEquals(homePage.getCityByName(RahulShettyConstants.PERSON_BEN), 
            RahulShettyConstants.CITY_BENGALURU,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_CITY_CORRECT, RahulShettyConstants.PERSON_BEN, RahulShettyConstants.CITY_BENGALURU));
        softAssert.assertEquals(homePage.getAmountByName(RahulShettyConstants.PERSON_BEN), 
            RahulShettyConstants.AMOUNT_BEN,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_AMOUNT_CORRECT, RahulShettyConstants.PERSON_BEN, RahulShettyConstants.AMOUNT_BEN));
        softAssert.assertAll();
    }

    @Test(description = "Verify Dwayne's data")
    @Description("Validate all data for Dwayne in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Person Data Validation")
    public void testDwayneData() {
        softAssert.assertTrue(homePage.isPersonPresent(RahulShettyConstants.PERSON_DWAYNE), 
            RahulShettyMessages.format(RahulShettyMessages.PERSON_EXISTS_IN_TABLE, RahulShettyConstants.PERSON_DWAYNE));
        softAssert.assertEquals(homePage.getPositionByName(RahulShettyConstants.PERSON_DWAYNE), 
            RahulShettyConstants.POSITION_MANAGER,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_POSITION_CORRECT, RahulShettyConstants.PERSON_DWAYNE, RahulShettyConstants.POSITION_MANAGER));
        softAssert.assertEquals(homePage.getCityByName(RahulShettyConstants.PERSON_DWAYNE), 
            RahulShettyConstants.CITY_KOLKATA,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_CITY_CORRECT, RahulShettyConstants.PERSON_DWAYNE, RahulShettyConstants.CITY_KOLKATA));
        softAssert.assertEquals(homePage.getAmountByName(RahulShettyConstants.PERSON_DWAYNE), 
            RahulShettyConstants.AMOUNT_DWAYNE,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_AMOUNT_CORRECT, RahulShettyConstants.PERSON_DWAYNE, RahulShettyConstants.AMOUNT_DWAYNE));
        softAssert.assertAll();
    }

    @Test(description = "Verify Ivory's data")
    @Description("Validate all data for Ivory in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Person Data Validation")
    public void testIvoryData() {
        softAssert.assertTrue(homePage.isPersonPresent(RahulShettyConstants.PERSON_IVORY), 
            RahulShettyMessages.format(RahulShettyMessages.PERSON_EXISTS_IN_TABLE, RahulShettyConstants.PERSON_IVORY));
        softAssert.assertEquals(homePage.getPositionByName(RahulShettyConstants.PERSON_IVORY), 
            RahulShettyConstants.POSITION_RECEPTIONIST,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_POSITION_CORRECT, RahulShettyConstants.PERSON_IVORY, RahulShettyConstants.POSITION_RECEPTIONIST));
        softAssert.assertEquals(homePage.getCityByName(RahulShettyConstants.PERSON_IVORY), 
            RahulShettyConstants.CITY_CHENNAI,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_CITY_CORRECT, RahulShettyConstants.PERSON_IVORY, RahulShettyConstants.CITY_CHENNAI));
        softAssert.assertEquals(homePage.getAmountByName(RahulShettyConstants.PERSON_IVORY), 
            RahulShettyConstants.AMOUNT_IVORY,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_AMOUNT_CORRECT, RahulShettyConstants.PERSON_IVORY, RahulShettyConstants.AMOUNT_IVORY));
        softAssert.assertAll();
    }

    @Test(description = "Verify Jack's data")
    @Description("Validate all data for Jack in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Person Data Validation")
    public void testJackData() {
        softAssert.assertTrue(homePage.isPersonPresent(RahulShettyConstants.PERSON_JACK), 
            RahulShettyMessages.format(RahulShettyMessages.PERSON_EXISTS_IN_TABLE, RahulShettyConstants.PERSON_JACK));
        softAssert.assertEquals(homePage.getPositionByName(RahulShettyConstants.PERSON_JACK), 
            RahulShettyConstants.POSITION_ENGINEER,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_POSITION_CORRECT, RahulShettyConstants.PERSON_JACK, RahulShettyConstants.POSITION_ENGINEER));
        softAssert.assertEquals(homePage.getCityByName(RahulShettyConstants.PERSON_JACK), 
            RahulShettyConstants.CITY_PUNE,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_CITY_CORRECT, RahulShettyConstants.PERSON_JACK, RahulShettyConstants.CITY_PUNE));
        softAssert.assertEquals(homePage.getAmountByName(RahulShettyConstants.PERSON_JACK), 
            RahulShettyConstants.AMOUNT_JACK,
            RahulShettyMessages.format(RahulShettyMessages.PERSON_AMOUNT_CORRECT, RahulShettyConstants.PERSON_JACK, RahulShettyConstants.AMOUNT_JACK));
        softAssert.assertAll();
    }

    @Test(description = "Verify all expected names are present")
    @Description("Validate that all 5 expected people are in the table")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Data Completeness")
    public void testAllNamesPresent() {
        List<String> expectedNames = Arrays.asList(
            RahulShettyConstants.PERSON_ALEX,
            RahulShettyConstants.PERSON_BEN,
            RahulShettyConstants.PERSON_DWAYNE,
            RahulShettyConstants.PERSON_IVORY,
            RahulShettyConstants.PERSON_JACK
        );
        
        List<String> actualNames = homePage.getAllNames();
        
        for (String name : expectedNames) {
            softAssert.assertTrue(actualNames.contains(name), 
                RahulShettyMessages.format(RahulShettyMessages.PERSON_EXISTS_IN_TABLE, name));
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify all positions are valid")
    @Description("Validate that all position values are non-empty")
    @Severity(SeverityLevel.NORMAL)
    @Story("Data Integrity")
    public void testAllPositionsValid() {
        List<String> positions = homePage.getAllPositions();
        
        for (String position : positions) {
            softAssert.assertNotNull(position, RahulShettyMessages.POSITION_NOT_NULL);
            softAssert.assertFalse(position.trim().isEmpty(), 
                RahulShettyMessages.ALL_POSITIONS_VALID);
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify all cities are valid")
    @Description("Validate that all city values are non-empty")
    @Severity(SeverityLevel.NORMAL)
    @Story("Data Integrity")
    public void testAllCitiesValid() {
        List<String> cities = homePage.getAllCities();
        
        for (String city : cities) {
            softAssert.assertNotNull(city, RahulShettyMessages.CITY_NOT_NULL);
            softAssert.assertFalse(city.trim().isEmpty(), 
                RahulShettyMessages.ALL_CITIES_VALID);
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify all amounts are positive")
    @Description("Validate that all amount values are positive numbers")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Data Integrity")
    public void testAllAmountsPositive() {
        List<Integer> amounts = homePage.getAllAmounts();
        
        for (Integer amount : amounts) {
            softAssert.assertTrue(amount > 0, 
                RahulShettyMessages.ALL_AMOUNTS_POSITIVE);
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify all amounts are numeric")
    @Description("Validate that all amount values can be parsed as integers")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Data Integrity")
    public void testAllAmountsNumeric() {
        List<String> amountStrings = homePage.getFixedTableColumnData("Amount");
        
        for (String amount : amountStrings) {
            try {
                Integer.parseInt(amount.trim());
                softAssert.assertTrue(true, 
                    RahulShettyMessages.format(RahulShettyMessages.AMOUNT_IS_NUMERIC, amount));
            } catch (NumberFormatException e) {
                softAssert.fail(RahulShettyMessages.format(RahulShettyMessages.AMOUNT_IS_NUMERIC, amount));
            }
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify complete row data")
    @Description("Validate that each row has complete data for all columns")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Data Completeness")
    public void testCompleteRowData() {
        int rowCount = homePage.getFixedTableRowCount();
        
        for (int i = 1; i <= rowCount; i++) {
            Map<String, String> rowData = homePage.getFixedTableRowData(i);
            
            softAssert.assertNotNull(rowData.get("Name"), 
                RahulShettyMessages.NAME_NOT_NULL);
            softAssert.assertNotNull(rowData.get("Position"), 
                RahulShettyMessages.POSITION_NOT_NULL);
            softAssert.assertNotNull(rowData.get("City"), 
                RahulShettyMessages.CITY_NOT_NULL);
            softAssert.assertNotNull(rowData.get("Amount"), 
                RahulShettyMessages.AMOUNT_NOT_NULL);
            
            softAssert.assertFalse(rowData.get("Name").trim().isEmpty(), 
                RahulShettyMessages.format(RahulShettyMessages.ROW_DATA_COMPLETE, i));
            softAssert.assertFalse(rowData.get("Position").trim().isEmpty(), 
                RahulShettyMessages.format(RahulShettyMessages.ROW_DATA_COMPLETE, i));
            softAssert.assertFalse(rowData.get("City").trim().isEmpty(), 
                RahulShettyMessages.format(RahulShettyMessages.ROW_DATA_COMPLETE, i));
            softAssert.assertFalse(rowData.get("Amount").trim().isEmpty(), 
                RahulShettyMessages.format(RahulShettyMessages.ROW_DATA_COMPLETE, i));
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify column data extraction")
    @Description("Validate that we can extract complete column data")
    @Severity(SeverityLevel.NORMAL)
    @Story("Data Extraction")
    public void testColumnDataExtraction() {
        List<String> names = homePage.getAllNames();
        List<String> positions = homePage.getAllPositions();
        List<String> cities = homePage.getAllCities();
        List<Integer> amounts = homePage.getAllAmounts();
        
        softAssert.assertEquals(names.size(), positions.size(), 
            RahulShettyMessages.NAMES_COUNT_MATCH_POSITIONS);
        softAssert.assertEquals(positions.size(), cities.size(), 
            RahulShettyMessages.POSITIONS_COUNT_MATCH_CITIES);
        softAssert.assertEquals(cities.size(), amounts.size(), 
            RahulShettyMessages.CITIES_COUNT_MATCH_AMOUNTS);
        softAssert.assertTrue(names.size() >= 5, 
            RahulShettyMessages.SHOULD_HAVE_AT_LEAST_5_ROWS);
        softAssert.assertAll();
    }

    @Test(description = "Verify specific cell values")
    @Description("Validate specific cell values in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Cell Validation")
    public void testSpecificCellValues() {
        // Assuming Alex is in row 1
        softAssert.assertEquals(homePage.getFixedTableCellValue(1, 1), 
            RahulShettyConstants.PERSON_ALEX, RahulShettyMessages.ROW1_COL1_SHOULD_BE_ALEX);
        softAssert.assertEquals(homePage.getFixedTableCellValue(1, 2), 
            RahulShettyConstants.POSITION_ENGINEER, RahulShettyMessages.ROW1_COL2_SHOULD_BE_ENGINEER);
        softAssert.assertEquals(homePage.getFixedTableCellValue(1, 3), 
            RahulShettyConstants.CITY_CHENNAI, RahulShettyMessages.ROW1_COL3_SHOULD_BE_CHENNAI);
        softAssert.assertEquals(homePage.getFixedTableCellValue(1, 4), 
            String.valueOf(RahulShettyConstants.AMOUNT_ALEX), RahulShettyMessages.ROW1_COL4_SHOULD_BE_28);
        softAssert.assertAll();
    }

    @Test(description = "Verify table contains Engineers")
    @Description("Validate that there are Engineers in the table")
    @Severity(SeverityLevel.NORMAL)
    @Story("Position Validation")
    public void testTableContainsEngineers() {
        List<String> positions = homePage.getAllPositions();
        long engineerCount = positions.stream()
                .filter(p -> p.equals(RahulShettyConstants.POSITION_ENGINEER))
                .count();
        
        softAssert.assertTrue(engineerCount >= 2, 
            RahulShettyMessages.AT_LEAST_2_ENGINEERS);
        softAssert.assertAll();
    }

    @Test(description = "Verify Chennai city appears multiple times")
    @Description("Validate that Chennai appears for multiple people")
    @Severity(SeverityLevel.NORMAL)
    @Story("City Validation")
    public void testChennaiMultipleTimes() {
        List<String> cities = homePage.getAllCities();
        long chennaiCount = cities.stream()
                .filter(c -> c.equals(RahulShettyConstants.CITY_CHENNAI))
                .count();
        
        softAssert.assertTrue(chennaiCount >= 2, 
            RahulShettyMessages.CHENNAI_APPEARS_TWICE);
        softAssert.assertAll();
    }
}
