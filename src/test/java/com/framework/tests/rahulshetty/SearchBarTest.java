package com.framework.tests.rahulshetty;

import com.framework.utils.BaseTest;
import com.framework.utils.rahulshetty.RahulShettyConstants;
import com.framework.utils.rahulshetty.RahulShettyMessages;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for autocomplete/suggestion search bar functionality on Rahul Shetty Academy practice page.
 */
@Epic("Rahul Shetty Academy")
@Feature("Autocomplete Search Bar")
public class SearchBarTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {
        initializeHomePage(); // Uses default baseUrl.1 - Rahul Shetty Academy
    }

    // Autocomplete/Suggestion Search Bar Tests
    
    @Description("Validate that autocomplete suggestions appear when typing country name prefix")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Autocomplete Display")
    @Test(priority = 1, groups = {"smoke", "functional", "regression"}, description = "Verify autocomplete suggestions appear when typing")
    public void testAutocompleteSuggestionsAppear() {
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_INDIA_PREFIX);
        
        boolean suggestionsDisplayed = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsDisplayed, 
            RahulShettyMessages.SUGGESTIONS_DISPLAYED_IND);
        
        int suggestionCount = homePage.getSuggestionCount();
        softAssert.assertTrue(suggestionCount > 0, 
            RahulShettyMessages.SUGGESTION_COUNT_GT_ZERO_IND);
        
        softAssert.assertAll();
    }
    
    @Description("Validate that user can select a specific suggestion from the autocomplete list by text")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Autocomplete Selection")
    @Test(priority = 2, groups = {"functional", "regression"}, description = "Verify selecting a suggestion from the list")
    public void testSelectSuggestionByText() {
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_INDIA_PREFIX);
        homePage.selectSuggestionByText(RahulShettyConstants.COUNTRY_INDIA);
        
        String selectedValue = homePage.getAutocompleteValue();
        softAssert.assertEquals(selectedValue, RahulShettyConstants.COUNTRY_INDIA, 
            RahulShettyMessages.SELECTED_VALUE_INDIA);
        
        softAssert.assertAll();
    }
    
    @Description("Validate that user can select a suggestion from autocomplete list by index position")
    @Severity(SeverityLevel.NORMAL)
    @Story("Autocomplete Selection")
    @Test(priority = 3, groups = {"functional", "regression"}, description = "Verify selecting suggestion by index")
    public void testSelectSuggestionByIndex() {
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_CO_PREFIX_LOWER);
        
        int suggestionCount = homePage.getSuggestionCount();
        softAssert.assertTrue(suggestionCount > 0, 
            RahulShettyMessages.SUGGESTIONS_FOR_CO);
        
        homePage.selectSuggestionByIndex(0); // Select first suggestion
        
        String selectedValue = homePage.getAutocompleteValue();
        softAssert.assertFalse(selectedValue.isEmpty(), 
            RahulShettyMessages.VALUE_SELECTED_FROM_SUGGESTIONS);
        
        softAssert.assertAll();
    }
    
    @Description("Validate that autocomplete works correctly when full country name is entered")
    @Severity(SeverityLevel.NORMAL)
    @Story("Autocomplete Search")
    @Test(priority = 4, groups = {"functional", "regression"}, description = "Verify autocomplete with full country name")
    public void testAutocompleteWithFullCountryName() {
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_COLOMBIA);
        
        java.util.List<String> suggestions = homePage.getAllSuggestions();
        softAssert.assertTrue(suggestions.size() > 0, 
            RahulShettyMessages.SUGGESTIONS_FOR_COLOMBIA);
        
        boolean containsColombia = suggestions.stream()
            .anyMatch(s -> s.equalsIgnoreCase(RahulShettyConstants.COUNTRY_COLOMBIA));
        softAssert.assertTrue(containsColombia, 
            RahulShettyMessages.SUGGESTIONS_CONTAIN_COLOMBIA);
        
        softAssert.assertAll();
    }
    
    @Description("Validate that autocomplete field can be cleared and new search can be performed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Autocomplete Field Management")
    @Test(priority = 5, groups = {"smoke", "functional", "regression"}, description = "Verify autocomplete clear and re-enter")
    public void testAutocompleteClearAndReenter() throws InterruptedException {
        // First search
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_INDIA_PREFIX);
        
        boolean firstSuggestions = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(firstSuggestions, 
            RahulShettyMessages.SUGGESTIONS_APPEAR_FIRST_SEARCH);
        
        // Clear and search again
        homePage.clearAutocomplete();
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_UNITED_STATES_PREFIX);
        
        boolean secondSuggestions = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(secondSuggestions, 
            RahulShettyMessages.SUGGESTIONS_APPEAR_SECOND_SEARCH);
        
        softAssert.assertAll();
    }
    
    @Description("Validate that autocomplete returns multiple matching suggestions for partial text input")
    @Severity(SeverityLevel.NORMAL)
    @Story("Autocomplete Search")
    @Test(priority = 6, groups = {"functional", "regression"}, description = "Verify autocomplete with partial text matching multiple countries")
    public void testAutocompletePartialMatch() {
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_CO_PREFIX);
        
        java.util.List<String> suggestions = homePage.getAllSuggestions();
        softAssert.assertTrue(suggestions.size() > 1, 
            RahulShettyMessages.MULTIPLE_SUGGESTIONS_FOR_CO);
        
        // Verify suggestions contain expected countries
        boolean hasMultipleMatches = suggestions.stream()
            .anyMatch(s -> s.toLowerCase().contains(RahulShettyConstants.COUNTRY_CO_PREFIX_LOWER));
        softAssert.assertTrue(hasMultipleMatches, 
            RahulShettyMessages.SUGGESTIONS_CONTAIN_CO);
        
        softAssert.assertAll();
    }
    
    @Description("Validate that autocomplete search is case insensitive (works with both uppercase and lowercase input)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Autocomplete Search")
    @Test(priority = 7, groups = {"regression"}, description = "Verify autocomplete case insensitivity")
    public void testAutocompleteCaseInsensitivity() throws InterruptedException {
        // Test with lowercase
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_INDIA.toLowerCase());
        
        boolean suggestionsLowercase = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsLowercase, 
            RahulShettyMessages.SUGGESTIONS_LOWERCASE);
        
        homePage.clearAutocomplete();
        
        // Test with uppercase
        homePage.enterCountryInAutocomplete(RahulShettyConstants.COUNTRY_INDIA.toUpperCase());
        
        boolean suggestionsUppercase = homePage.isSuggestionListDisplayed();
        softAssert.assertTrue(suggestionsUppercase, 
            RahulShettyMessages.SUGGESTIONS_UPPERCASE);
        
        softAssert.assertAll();
    }
}
