package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchBarTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    // Autocomplete/Suggestion Search Bar Tests
    
    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify autocomplete suggestions appear when typing")
    public void testAutocompleteSuggestionsAppear() {
        homePage.enterCountryInAutocomplete("Ind");
        
        boolean suggestionsDisplayed = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsDisplayed, 
            "Suggestions should be displayed when typing 'Ind'");
        
        int suggestionCount = homePage.getSuggestionCount();
        softAssert.assertTrue(suggestionCount > 0, 
            "There should be at least one suggestion for 'Ind'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify selecting a suggestion from the list")
    public void testSelectSuggestionByText() {
        homePage.enterCountryInAutocomplete("Ind");
        homePage.selectSuggestionByText("India");
        
        String selectedValue = homePage.getAutocompleteValue();
        softAssert.assertEquals(selectedValue, "India", 
            "Selected value should be 'India'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify selecting suggestion by index")
    public void testSelectSuggestionByIndex() {
        homePage.enterCountryInAutocomplete("co");
        
        int suggestionCount = homePage.getSuggestionCount();
        softAssert.assertTrue(suggestionCount > 0, 
            "There should be suggestions for 'co'");
        
        homePage.selectSuggestionByIndex(0); // Select first suggestion
        
        String selectedValue = homePage.getAutocompleteValue();
        softAssert.assertFalse(selectedValue.isEmpty(), 
            "A value should be selected from suggestions");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify autocomplete with full country name")
    public void testAutocompleteWithFullCountryName() {
        homePage.enterCountryInAutocomplete("Colombia");
        
        java.util.List<String> suggestions = homePage.getAllSuggestions();
        softAssert.assertTrue(suggestions.size() > 0, 
            "Suggestions should appear for 'Colombia'");
        
        boolean containsColombia = suggestions.stream()
            .anyMatch(s -> s.equalsIgnoreCase("Colombia"));
        softAssert.assertTrue(containsColombia, 
            "Suggestions should contain 'Colombia'");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 5, groups = {"smoke", "functional", "regression"}, description = "Verify autocomplete clear and re-enter")
    public void testAutocompleteClearAndReenter() throws InterruptedException {
        // First search
        homePage.enterCountryInAutocomplete("Ind");
        
        boolean firstSuggestions = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(firstSuggestions, 
            "Suggestions should appear for first search");
        
        // Clear and search again
        homePage.clearAutocomplete();
        homePage.enterCountryInAutocomplete("United");
        
        boolean secondSuggestions = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(secondSuggestions, 
            "Suggestions should appear for second search after clearing");
        
        softAssert.assertAll();
    }
    
    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify autocomplete with partial text matching multiple countries")
    public void testAutocompletePartialMatch() {
        homePage.enterCountryInAutocomplete("Co");
        
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
    
    @Test(priority = 7, groups = {"regression"}, description = "Verify autocomplete case insensitivity")
    public void testAutocompleteCaseInsensitivity() throws InterruptedException {
        // Test with lowercase
        homePage.enterCountryInAutocomplete("india");
        
        boolean suggestionsLowercase = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsLowercase, 
            "Suggestions should appear with lowercase input");
        
        homePage.clearAutocomplete();
        
        // Test with uppercase
        homePage.enterCountryInAutocomplete("INDIA");
        
        boolean suggestionsUppercase = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsUppercase, 
            "Suggestions should appear with uppercase input");
        
        softAssert.assertAll();
    }
}
