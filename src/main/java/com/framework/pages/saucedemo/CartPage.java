package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Cart Page Object Model for SauceDemo
 * Represents the shopping cart page with cart items and checkout functionality
 */
public class CartPage extends BasePage {

    // Page Title
    @FindBy(className = "title")
    private WebElement pageTitle;

    // Cart Items
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_desc")
    private List<WebElement> itemDescriptions;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(className = "cart_quantity")
    private List<WebElement> itemQuantities;

    @FindBy(css = "button[id^='remove-']")
    private List<WebElement> removeButtons;

    // Navigation Buttons
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    // Shopping Cart Badge
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Page Title Methods
    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(pageTitle);
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    // Cart Items Methods
    public int getCartItemCount() {
        return cartItems.size();
    }

    public boolean areCartItemsDisplayed() {
        return !cartItems.isEmpty();
    }

    public List<String> getAllItemNames() {
        return itemNames.stream()
                .map(this::getElementText)
                .toList();
    }

    public List<String> getAllItemDescriptions() {
        return itemDescriptions.stream()
                .map(this::getElementText)
                .toList();
    }

    public List<String> getAllItemPrices() {
        return itemPrices.stream()
                .map(this::getElementText)
                .toList();
    }

    public List<String> getAllItemQuantities() {
        return itemQuantities.stream()
                .map(this::getElementText)
                .toList();
    }

    public String getItemNameByIndex(int index) {
        if (index >= 0 && index < itemNames.size()) {
            return getElementText(itemNames.get(index));
        }
        return "";
    }

    public String getItemPriceByIndex(int index) {
        if (index >= 0 && index < itemPrices.size()) {
            return getElementText(itemPrices.get(index));
        }
        return "";
    }

    public String getItemQuantityByIndex(int index) {
        if (index >= 0 && index < itemQuantities.size()) {
            return getElementText(itemQuantities.get(index));
        }
        return "";
    }

    // Remove Button Methods
    public int getRemoveButtonCount() {
        return removeButtons.size();
    }

    public boolean areRemoveButtonsDisplayed() {
        return !removeButtons.isEmpty() && removeButtons.stream().allMatch(this::isElementDisplayed);
    }

    public void removeItemByIndex(int index) {
        if (index >= 0 && index < removeButtons.size()) {
            clickElement(removeButtons.get(index));
        }
    }

    public void removeAllItems() {
        while (!removeButtons.isEmpty()) {
            clickElement(removeButtons.get(0));
            // Wait briefly for the DOM to update
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Navigation Button Methods
    public boolean isContinueShoppingButtonDisplayed() {
        return isElementDisplayed(continueShoppingButton);
    }

    public boolean isContinueShoppingButtonEnabled() {
        return isElementEnabled(continueShoppingButton);
    }

    public void clickContinueShopping() {
        clickElement(continueShoppingButton);
    }

    public boolean isCheckoutButtonDisplayed() {
        return isElementDisplayed(checkoutButton);
    }

    public boolean isCheckoutButtonEnabled() {
        return isElementEnabled(checkoutButton);
    }

    public void clickCheckout() {
        clickElement(checkoutButton);
    }

    // Cart Badge Methods
    public boolean isCartBadgeDisplayed() {
        try {
            return isElementDisplayed(cartBadge);
        } catch (Exception e) {
            return false;
        }
    }

    public String getCartBadgeCount() {
        if (isCartBadgeDisplayed()) {
            return getElementText(cartBadge);
        }
        return "0";
    }

    // Cart Validation Methods
    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public boolean verifyItemInCart(String itemName) {
        return getAllItemNames().stream()
                .anyMatch(name -> name.equalsIgnoreCase(itemName));
    }

    public double calculateTotalPrice() {
        return getAllItemPrices().stream()
                .mapToDouble(price -> Double.parseDouble(price.replace("$", "")))
                .sum();
    }
}
