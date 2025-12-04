# Product Filter/Sort Test Suite

## Overview
Comprehensive test suite for the SauceDemo product filter/sort dropdown functionality covering dropdown UI, sort options, behavior, and edge cases.

## Test Coverage Summary

### 📊 **Total Filter/Sort Tests**: 20 tests (original 8 + new 12)
- **Smoke Tests**: 1
- **Functional Tests**: 19
- **Regression Tests**: All 20

---

## Original Tests (Tests 5-12)

### 1. Dropdown Display & Default State (2 tests)
| Priority | Test Name | Description |
|----------|-----------|-------------|
| 5 | `testSortDropdownDisplayedAndEnabled` | Verifies dropdown is visible and enabled |
| 6 | `testDefaultSortOption` | Verifies default is "Name (A to Z)" |

### 2. Sort Options Availability (1 test)
| Priority | Test Name | Description |
|----------|-----------|-------------|
| 7 | `testAllSortOptionsAvailable` | Verifies all 4 sort options exist |

### 3. Basic Sort Functionality (4 tests)
| Priority | Test Name | Description |
|----------|-----------|-------------|
| 8 | `testSortByNameAtoZ` | Sorts products alphabetically A→Z |
| 9 | `testSortByNameZtoA` | Sorts products alphabetically Z→A |
| 10 | `testSortByPriceLowToHigh` | Sorts by price ascending ($7.99→$49.99) |
| 11 | `testSortByPriceHighToLow` | Sorts by price descending ($49.99→$7.99) |

### 4. Sort Switching (1 test)
| Priority | Test Name | Description |
|----------|-----------|-------------|
| 12 | `testSwitchingSortOptions` | Tests switching between all 4 options |

---

## New Advanced Tests (Tests 21-32)

### 5. Dropdown UI Validation (2 tests)
| Priority | Test Name | Severity | Description |
|----------|-----------|----------|-------------|
| 21 | `testSortDropdownOptionsCount` | NORMAL | Verifies exactly 4 options in dropdown |
| 22 | `testSortDropdownOptionsOrder` | MINOR | Verifies options appear in correct order |

**Expected Order**:
1. Name (A to Z)
2. Name (Z to A)
3. Price (low to high)
4. Price (high to low)

### 6. Sort Persistence Tests (1 test)
| Priority | Test Name | Severity | Description |
|----------|-----------|----------|-------------|
| 23 | `testSortPersistsAfterCartOperation` | NORMAL | Verifies sort remains after adding to cart |

### 7. Real-time Behavior Tests (1 test)
| Priority | Test Name | Severity | Description |
|----------|-----------|----------|-------------|
| 24 | `testProductsReorderImmediately` | CRITICAL | Verifies products reorder instantly on sort change |

### 8. Sort by Value Attribute Tests (2 tests)
| Priority | Test Name | Severity | Description |
|----------|-----------|----------|-------------|
| 25 | `testSortByValueAttribute` | NORMAL | Tests sorting using value="za" |
| 26 | `testAllSortValuesFunctional` | CRITICAL | Tests all values (az, za, lohi, hilo) |

**Dropdown Values**:
- `az` → Name (A to Z)
- `za` → Name (Z to A)
- `lohi` → Price (low to high)
- `hilo` → Price (high to low)

### 9. Product Count Consistency Tests (1 test)
| Priority | Test Name | Severity | Description |
|----------|-----------|----------|-------------|
| 27 | `testSortDoesNotFilterProducts` | CRITICAL | Ensures all 6 products remain visible regardless of sort |

### 10. Multiple Sort Changes Tests (1 test)
| Priority | Test Name | Severity | Description |
|----------|-----------|----------|-------------|
| 28 | `testMultipleSortChanges` | NORMAL | Verifies sorting multiple times maintains correct order |

### 11. Decimal Price Handling Tests (1 test)
| Priority | Test Name | Severity | Description |
|----------|-----------|----------|-------------|
| 29 | `testPriceSortingWithDecimals` | CRITICAL | Validates correct decimal comparison ($7.99 < $15.99 < $29.99) |

### 12. Case-Insensitive Sorting Tests (1 test)
| Priority | Test Name | Severity | Description |
|----------|-----------|----------|-------------|
| 30 | `testNameSortingCaseHandling` | NORMAL | Ensures case-insensitive alphabetical sorting |

---

## New Method Added to InventoryPage

```java
public int getProductCount() {
    return inventoryItems.size();
}
```

---

## Test Execution

### Run All Filter/Sort Tests
```bash
# Run tests 5-30 (all sort/filter tests)
mvn test -Dtest=InventoryPageTest#test*Sort* -Dtest=InventoryPageTest#test*Dropdown*
```

### Run by Priority
```bash
# Smoke tests
mvn test -Dtest=InventoryPageTest -Dgroups=smoke

# All functional tests
mvn test -Dtest=InventoryPageTest -Dgroups=functional

# Full regression
mvn test -Dtest=InventoryPageTest -Dgroups=regression
```

### Run Specific Test
```bash
mvn test -Dtest=InventoryPageTest#testSortByValueAttribute
```

---

## Filter Dropdown Specifications

### Visual Representation
```
┌────────────────────────────────┐
│  🔽 Name (Z to A)             │  ← Dropdown button
└────────────────────────────────┘

When clicked:
┌────────────────────────────────┐
│  Name (A to Z)          [az]   │
│  Name (Z to A)          [za]   │ ← Selected
│  Price (low to high)    [lohi] │
│  Price (high to low)    [hilo] │
└────────────────────────────────┘
```

### Filter Element Locator
```java
@FindBy(className = "product_sort_container")
private WebElement sortDropdown;
```

### Sort Constants (from SauceDemoConstants.java)
```java
public static final String SORT_NAME_ASC = "Name (A to Z)";
public static final String SORT_NAME_DESC = "Name (Z to A)";
public static final String SORT_PRICE_LOW_HIGH = "Price (low to high)";
public static final String SORT_PRICE_HIGH_LOW = "Price (high to low)";
```

---

## Test Scenarios Covered

### ✅ Dropdown UI
- Visibility on page load
- Enabled state
- Option count (exactly 4)
- Option order validation

### ✅ Sort Functionality
- Name sorting (A→Z, Z→A)
- Price sorting (Low→High, High→Low)
- Decimal price handling
- Case-insensitive name sorting

### ✅ Sort Behavior
- Immediate product reordering
- Persistence after cart operations
- Multiple sort changes
- Product count consistency (no filtering)

### ✅ Technical Tests
- Sort by visible text
- Sort by value attribute
- All 4 values work correctly
- Selected option reflection

---

## Edge Cases Tested

1. **Decimal Precision**: Validates $7.99 < $15.99 < $29.99 (not string comparison)
2. **Case Sensitivity**: "Sauce Labs Backpack" vs "Test.allTheThings()" sorted correctly
3. **State Persistence**: Sort selection maintained during cart operations
4. **Idempotency**: Selecting same sort twice produces identical results
5. **No Filtering**: All 6 products always visible (sort ≠ filter)

---

## Product Data Used in Tests

| Product Name | Price |
|--------------|-------|
| Sauce Labs Backpack | $29.99 |
| Sauce Labs Bike Light | $9.99 |
| Sauce Labs Bolt T-Shirt | $15.99 |
| Sauce Labs Fleece Jacket | $49.99 |
| Sauce Labs Onesie | $7.99 |
| Test.allTheThings() T-Shirt (Red) | $15.99 |

**Total Products**: 6 (constant across all sorts)

---

## Test Assertions

### Soft Assertions
All tests use `SoftAssert` to:
- Continue execution after first failure
- Report all failures at once
- Improve test debugging

### Assertion Types
- **Equality**: `assertEquals(actual, expected, message)`
- **Boolean**: `assertTrue(condition, message)` / `assertFalse(condition, message)`
- **Inequality**: `assertNotEquals(actual, notExpected, message)`
- **Collections**: List order validation
- **Numeric**: Decimal comparison

---

## Messages Used (from SauceDemoMessages.java)
- `SORT_DROPDOWN_DISPLAYED`
- `SORT_DROPDOWN_ENABLED`
- `DEFAULT_SORT_CORRECT`
- `SORT_OPTIONS_COUNT_CORRECT`
- `PRODUCTS_SORTED_A_TO_Z`
- `PRODUCTS_SORTED_Z_TO_A`
- `PRODUCTS_SORTED_PRICE_LOW_HIGH`
- `PRODUCTS_SORTED_PRICE_HIGH_LOW`
- `SELECTED_SORT_OPTION_CORRECT`

---

## Integration with Allure Reports

All tests include:
- `@Epic("SauceDemo E-Commerce")`
- `@Feature("Inventory Page")`
- `@Story("Sort Functionality")` or `@Story("Filter/Sort Dropdown")`
- `@Description(...)` - Detailed test purpose
- `@Severity(...)` - BLOCKER, CRITICAL, NORMAL, MINOR

---

## Future Enhancements

### Potential Additional Tests
- [ ] Sort performance with 100+ products
- [ ] Sort accessibility (keyboard navigation)
- [ ] Sort state after page refresh
- [ ] Sort with special characters in names
- [ ] Sort dropdown hover states
- [ ] Mobile responsive sort dropdown
- [ ] Sort dropdown focus states
- [ ] Verify dropdown closes after selection

### Known Limitations
- SauceDemo has only 6 products (limited sort testing)
- No multi-level sorting (e.g., price then name)
- No custom sort options
- No filter functionality (only sort)

---

## Summary

This test suite provides comprehensive coverage of the product filter/sort dropdown with:
- **20 total tests** (8 existing + 12 new)
- **4 sort options** fully validated
- **Edge cases** thoroughly tested
- **Real-world scenarios** covered
- **Allure reporting** integrated

All tests follow best practices with clear descriptions, appropriate severity levels, and comprehensive assertions.
