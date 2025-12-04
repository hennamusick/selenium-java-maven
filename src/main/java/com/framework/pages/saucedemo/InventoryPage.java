package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object for SauceDemo Inventory/Products Page
 * URL: https://www.saucedemo.com/inventory.html
 */
public class InventoryPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "app_logo")
    private WebElement appLogo;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(css = "button[id^='add-to-cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(css = "button[id^='remove']")
    private List<WebElement> removeButtons;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Visibility checks
    public boolean isPageTitleDisplayed() {
        return pageTitle.isDisplayed();
    }

    public boolean isAppLogoDisplayed() {
        return appLogo.isDisplayed();
    }

    public boolean isMenuButtonDisplayed() {
        return menuButton.isDisplayed();
    }

    public boolean isShoppingCartDisplayed() {
        return shoppingCartLink.isDisplayed();
    }

    public boolean isSortDropdownDisplayed() {
        return sortDropdown.isDisplayed();
    }

    // Getters
    public String getPageTitle() {
        return pageTitle.getText();
    }

    public String getAppLogoText() {
        return appLogo.getText();
    }

    public int getInventoryItemCount() {
        return inventoryItems.size();
    }

    public List<String> getAllProductNames() {
        return productNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getAllProductPrices() {
        return productPrices.stream()
                .map(WebElement::getText)
                .toList();
    }

    public String getProductNameByIndex(int index) {
        if (index >= 0 && index < productNames.size()) {
            return productNames.get(index).getText();
        }
        return "";
    }

    public boolean isCartBadgeDisplayed() {
        try {
            return cartBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCartBadgeCount() {
        return cartBadge.getText();
    }

    // Actions
    public void clickMenuButton() {
        menuButton.click();
    }

    public void clickShoppingCart() {
        shoppingCartLink.click();
    }

    public void addFirstItemToCart() {
        if (!addToCartButtons.isEmpty()) {
            addToCartButtons.get(0).click();
        }
    }

    public void addItemToCartByIndex(int index) {
        if (index >= 0 && index < addToCartButtons.size()) {
            addToCartButtons.get(index).click();
        }
    }

    public void removeFirstItemFromCart() {
        if (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();
        }
    }

    public int getAddToCartButtonCount() {
        return addToCartButtons.size();
    }

    public int getRemoveButtonCount() {
        return removeButtons.size();
    }

    // State checks
    public boolean isMenuButtonEnabled() {
        return menuButton.isEnabled();
    }

    public boolean isShoppingCartEnabled() {
        return shoppingCartLink.isEnabled();
    }

    // Sort/Filter functionality
    public void selectSortOption(String option) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }

    public void selectSortByValue(String value) {
        Select select = new Select(sortDropdown);
        select.selectByValue(value);
    }

    public String getSelectedSortOption() {
        Select select = new Select(sortDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public List<String> getAllSortOptions() {
        Select select = new Select(sortDropdown);
        return select.getOptions().stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isSortDropdownEnabled() {
        return sortDropdown.isEnabled();
    }

    public int getProductCount() {
        return inventoryItems.size();
    }

    // Cart management
    public void addAllItemsToCart() {
        for (WebElement button : addToCartButtons) {
            button.click();
        }
    }

    public void removeItemFromCartByIndex(int index) {
        if (index >= 0 && index < removeButtons.size()) {
            removeButtons.get(index).click();
        }
    }

    public void removeAllItemsFromCart() {
        // Create a copy to avoid ConcurrentModificationException
        int count = removeButtons.size();
        for (int i = 0; i < count; i++) {
            if (!removeButtons.isEmpty()) {
                removeButtons.get(0).click();
            }
        }
    }
}
