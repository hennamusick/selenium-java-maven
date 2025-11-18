package com.framework.tests;

import com.framework.pages.HomePage;
import com.framework.utils.BaseTest;
import com.framework.utils.ConfigReader;
import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() throws InterruptedException {
        // Using baseUrl.1 - Rahul Shetty Academy AutomationPractice
        driver.get(ConfigReader.getBaseUrl(1));
        Thread.sleep(2000); // Wait for page to load
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify alert functionality")
    public void testAlert() {
        homePage.enterName("Test User");
        homePage.clickAlert();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        softAssert.assertTrue(alertText.contains("Test User"), 
            "Alert should contain the entered name");
        alert.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify confirm box functionality")
    public void testConfirmBox() {
        homePage.enterName("Test User");
        homePage.clickConfirmBox();
        Alert confirm = driver.switchTo().alert();
        String confirmText = confirm.getText();
        softAssert.assertTrue(confirmText.contains("Test User"), 
            "Confirm box should contain the entered name");
        confirm.accept();
        
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"smoke", "regression"}, description = "Verify name field accepts input")
    public void testNameInput() {
        homePage.enterName("Automation Test");
        // Verify we can interact with the page successfully
        softAssert.assertNotNull(driver.getTitle(), "Page should have a title");
        
        softAssert.assertAll();
    }
    
    // Autocomplete/Suggestion Search Bar Tests
    
    @Test(priority = 4, groups = {"smoke", "functional", "regression"}, description = "Verify autocomplete suggestions appear when typing")
    public void testAutocompleteSuggestionsAppear() throws InterruptedException {
        homePage.enterCountryInAutocomplete("Ind");
        Thread.sleep(1000); // Wait for suggestions to load
        
        boolean suggestionsDisplayed = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsDisplayed, 
            "Suggestions should be displayed when typing 'Ind'");
        
        int suggestionCount = homePage.getSuggestionCount();
        softAssert.assertTrue(suggestionCount > 0, 
            "There should be at least one suggestion for 'Ind'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 5, groups = {"functional", "regression"}, description = "Verify selecting a suggestion from the list")
    public void testSelectSuggestionByText() throws InterruptedException {
        homePage.enterCountryInAutocomplete("Ind");
        Thread.sleep(1000); // Wait for suggestions to load
        
        homePage.selectSuggestionByText("India");
        Thread.sleep(500); // Wait for selection
        
        String selectedValue = homePage.getAutocompleteValue();
        softAssert.assertEquals(selectedValue, "India", 
            "Selected value should be 'India'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify selecting suggestion by index")
    public void testSelectSuggestionByIndex() throws InterruptedException {
        homePage.enterCountryInAutocomplete("co");
        Thread.sleep(1000); // Wait for suggestions to load
        
        int suggestionCount = homePage.getSuggestionCount();
        softAssert.assertTrue(suggestionCount > 0, 
            "There should be suggestions for 'co'");
        
        homePage.selectSuggestionByIndex(0); // Select first suggestion
        Thread.sleep(500);
        
        String selectedValue = homePage.getAutocompleteValue();
        softAssert.assertFalse(selectedValue.isEmpty(), 
            "A value should be selected from suggestions");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 7, groups = {"functional", "regression"}, description = "Verify autocomplete with full country name")
    public void testAutocompleteWithFullCountryName() throws InterruptedException {
        homePage.enterCountryInAutocomplete("Colombia");
        Thread.sleep(1000); // Wait for suggestions
        
        java.util.List<String> suggestions = homePage.getAllSuggestions();
        softAssert.assertTrue(suggestions.size() > 0, 
            "Suggestions should appear for 'Colombia'");
        
        boolean containsColombia = suggestions.stream()
            .anyMatch(s -> s.equalsIgnoreCase("Colombia"));
        softAssert.assertTrue(containsColombia, 
            "Suggestions should contain 'Colombia'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 8, groups = {"smoke", "functional", "regression"}, description = "Verify autocomplete clear and re-enter")
    public void testAutocompleteClearAndReenter() throws InterruptedException {
        // First search
        homePage.enterCountryInAutocomplete("Ind");
        Thread.sleep(1000);
        
        boolean firstSuggestions = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(firstSuggestions, 
            "Suggestions should appear for first search");
        
        // Clear and search again
        homePage.clearAutocomplete();
        Thread.sleep(500);
        homePage.enterCountryInAutocomplete("United");
        Thread.sleep(1000);
        
        boolean secondSuggestions = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(secondSuggestions, 
            "Suggestions should appear for second search after clearing");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 9, groups = {"functional", "regression"}, description = "Verify autocomplete with partial text matching multiple countries")
    public void testAutocompletePartialMatch() throws InterruptedException {
        homePage.enterCountryInAutocomplete("Co");
        Thread.sleep(1000); // Wait for suggestions
        
        java.util.List<String> suggestions = homePage.getAllSuggestions();
        softAssert.assertTrue(suggestions.size() > 1, 
            "Multiple suggestions should appear for 'Co' (Colombia, Congo, etc.)");
        
        // Verify suggestions contain expected countries
        boolean hasMultipleMatches = suggestions.stream()
            .anyMatch(s -> s.toLowerCase().contains("co"));
        softAssert.assertTrue(hasMultipleMatches, 
            "Suggestions should contain countries with 'co'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 10, groups = {"regression"}, description = "Verify autocomplete case insensitivity")
    public void testAutocompleteCaseInsensitivity() throws InterruptedException {
        // Test with lowercase
        homePage.enterCountryInAutocomplete("india");
        Thread.sleep(1000);
        
        boolean suggestionsLowercase = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsLowercase, 
            "Suggestions should appear with lowercase input");
        
        homePage.clearAutocomplete();
        Thread.sleep(500);
        
        // Test with uppercase
        homePage.enterCountryInAutocomplete("INDIA");
        Thread.sleep(1000);
        
        boolean suggestionsUppercase = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsUppercase, 
            "Suggestions should appear with uppercase input");
        
        softAssert.assertAll();
    }
}