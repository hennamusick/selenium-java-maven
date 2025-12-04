# Shopping Cart Page Test Suite

## Overview
Comprehensive test suite for the SauceDemo Shopping Cart page functionality covering cart display, item management, navigation, and calculations.

## Test Coverage

### 📦 **Total Tests**: 23 test cases
- **Smoke Tests**: 2
- **Functional Tests**: 21  
- **Regression Tests**: All 23

---

## Test Categories

### 1. Cart Page Display Tests (4 tests)
Tests basic cart page visibility and initial state.

| # | Test Name | Priority | Description |
|---|-----------|----------|-------------|
| 1 | `testCartPageTitleDisplay` | BLOCKER | Verifies "Your Cart" title is displayed |
| 2 | `testEmptyCartDisplay` | NORMAL | Verifies empty cart state when no items added |
| 3 | `testContinueShoppingButtonDisplay` | NORMAL | Verifies Continue Shopping button is present |
| 4 | `testCheckoutButtonDisplay` | CRITICAL | Verifies Checkout button is present |

### 2. Cart Items Display Tests (6 tests)
Tests how items are displayed in the cart.

| # | Test Name | Priority | Description |
|---|-----------|----------|-------------|
| 5 | `testSingleItemInCart` | CRITICAL | Verifies single item display after adding 1 product |
| 6 | `testMultipleItemsInCart` | CRITICAL | Verifies 3 items display correctly |
| 7 | `testCartItemNames` | NORMAL | Verifies correct product names are shown |
| 8 | `testCartItemPrices` | CRITICAL | Verifies prices display in correct format ($XX.XX) |
| 9 | `testCartItemQuantities` | NORMAL | Verifies quantity display for each item (always 1) |
| 10 | `testCartItemDescriptions` | MINOR | Verifies product descriptions are not empty |

### 3. Remove Item Tests (4 tests)
Tests item removal functionality.

| # | Test Name | Priority | Description |
|---|-----------|----------|-------------|
| 11 | `testRemoveButtonCount` | NORMAL | Verifies remove button count matches item count |
| 12 | `testRemoveSingleItemFromCart` | CRITICAL | Verifies single item can be removed |
| 13 | `testRemoveAllItemsFromCart` | CRITICAL | Verifies all items can be removed completely |
| 14 | `testRemoveSpecificItemByName` | NORMAL | Verifies specific item removal by index |

### 4. Cart Badge Tests (2 tests)
Tests shopping cart badge counter.

| # | Test Name | Priority | Description |
|---|-----------|----------|-------------|
| 15 | `testCartBadgeCount` | NORMAL | Verifies badge shows correct count (3 items) |
| 16 | `testCartBadgeUpdateAfterRemoval` | NORMAL | Verifies badge updates when item removed |

### 5. Navigation Tests (2 tests)
Tests cart navigation buttons.

| # | Test Name | Priority | Description |
|---|-----------|----------|-------------|
| 17 | `testContinueShoppingNavigation` | NORMAL | Verifies "Continue Shopping" goes back to inventory |
| 18 | `testCheckoutNavigation` | CRITICAL | Verifies "Checkout" navigates to checkout page |

### 6. Cart Calculations Tests (3 tests)
Tests price calculations.

| # | Test Name | Priority | Description |
|---|-----------|----------|-------------|
| 19 | `testSingleItemPriceCalculation` | CRITICAL | Verifies price calculation for 1 item |
| 20 | `testMultipleItemsPriceCalculation` | CRITICAL | Verifies total price for 3 items ($53.97) |
| 21 | `testPriceCalculationAfterRemoval` | NORMAL | Verifies price updates after removing item |

### 7. Cart Verification Tests (2 tests)
Tests cart state persistence and verification.

| # | Test Name | Priority | Description |
|---|-----------|----------|-------------|
| 22 | `testVerifyItemInCart` | NORMAL | Verifies specific item presence check works |
| 23 | `testCartStatePersistence` | NORMAL | Verifies cart state persists during navigation |

---

## Page Object: CartPage.java

### Methods Implemented (30+)
- **Display**: `isPageTitleDisplayed()`, `getPageTitle()`
- **Cart Items**: `getCartItemCount()`, `isCartEmpty()`, `getAllItemNames()`, `getAllItemPrices()`, `getAllItemQuantities()`, `getAllItemDescriptions()`
- **Item Access**: `getItemNameByIndex()`, `getItemPriceByIndex()`, `getItemQuantityByIndex()`
- **Remove**: `getRemoveButtonCount()`, `areRemoveButtonsDisplayed()`, `removeItemByIndex()`, `removeAllItems()`
- **Navigation**: `clickContinueShopping()`, `clickCheckout()`
- **Badge**: `isCartBadgeDisplayed()`, `getCartBadgeCount()`
- **Validation**: `verifyItemInCart()`, `calculateTotalPrice()`

### Web Elements
```java
@FindBy(className = "cart_item") List<WebElement> cartItems
@FindBy(className = "inventory_item_name") List<WebElement> itemNames
@FindBy(className = "inventory_item_desc") List<WebElement> itemDescriptions
@FindBy(className = "inventory_item_price") List<WebElement> itemPrices
@FindBy(className = "cart_quantity") List<WebElement> itemQuantities
@FindBy(css = "button[id^='remove-']") List<WebElement> removeButtons
@FindBy(id = "continue-shopping") WebElement continueShoppingButton
@FindBy(id = "checkout") WebElement checkoutButton
@FindBy(className = "shopping_cart_badge") WebElement cartBadge
```

---

## Test Execution

### Run All Cart Tests
```bash
mvn test -Dtest=CartPageTest
```

### Run by Test Groups
```bash
# Smoke tests only
mvn test -Dtest=CartPageTest -Dgroups=smoke

# Functional tests only
mvn test -Dtest=CartPageTest -Dgroups=functional

# Regression suite
mvn test -Dtest=CartPageTest -Dgroups=regression
```

### Run Specific Test
```bash
mvn test -Dtest=CartPageTest#testMultipleItemsInCart
```

---

## Test Data (from user screenshot)

### Products in Cart
1. **Sauce Labs Backpack** - $29.99
2. **Sauce Labs Bolt T-Shirt** - $15.99
3. **Sauce Labs Onesie** - $7.99

**Total**: $53.97  
**Cart Badge**: 3 items

---

## Constants Added

### SauceDemoConstants.java
```java
public static final String CART_PAGE_TITLE = "Your Cart";
public static final String CONTINUE_SHOPPING_BUTTON_TEXT = "Continue Shopping";
public static final String CHECKOUT_BUTTON_TEXT = "Checkout";
public static final String REMOVE_BUTTON_PREFIX = "Remove";
```

---

## Annotations Used
- `@Epic("SauceDemo E-Commerce")`
- `@Feature("Shopping Cart")`
- `@Story(...)` - Groups related tests
- `@Description(...)` - Clear test purpose
- `@Severity(...)` - BLOCKER, CRITICAL, NORMAL, MINOR

---

## Test Patterns

### Setup
```java
@BeforeMethod(alwaysRun = true)
public void setUp() {
    // Login before each test
    driver.get(ConfigReader.getBaseUrl(2));
    loginPage.login(STANDARD_USER, PASSWORD);
}
```

### Common Pattern
1. Add items to cart from inventory page
2. Navigate to cart: `inventoryPage.clickShoppingCart()`
3. Perform assertions on cart page
4. Use SoftAssert for multiple assertions

---

## Dependencies
- **TestNG** - Test framework
- **Allure** - Reporting
- **Selenium WebDriver** - Browser automation
- **Page Object Model** - Design pattern

---

## Notes
- All tests use `standard_user` account
- Tests are independent and can run in any order
- Tests clean up by logging out after each run (via `BaseTest`)
- Cart badge may not display when cart is empty
- SauceDemo doesn't support quantities > 1 per item

---

## Future Enhancements
- Add tests for locked user scenario
- Add tests for problem user (items don't add)
- Add visual regression tests for cart UI
- Add performance tests for large cart sizes
- Add accessibility tests (screen reader support)
