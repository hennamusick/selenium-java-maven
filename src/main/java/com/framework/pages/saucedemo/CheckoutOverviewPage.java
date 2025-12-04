package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Represents the Checkout Overview (Step Two) page where users review their order
 * Displays cart items with QTY, description, and price before final confirmation
 * 
 * @author Framework
 * @version 1.0
 */
public class CheckoutOverviewPage extends BasePage {

    // Page title
    @FindBy(className = "title")
    private WebElement pageTitle;

    // Cart items
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".cart_item_label .inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(css = ".cart_item_label .inventory_item_desc")
    private List<WebElement> itemDescriptions;

    @FindBy(css = ".cart_item_label .inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(css = ".cart_quantity")
    private List<WebElement> itemQuantities;

    // Order summary section
    @FindBy(css = ".summary_info")
    private WebElement summaryInfo;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(css = ".summary_tax_label")
    private WebElement taxLabel;

    @FindBy(css = ".summary_total_label")
    private WebElement totalLabel;

    // Buttons
    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    // Cart badge
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    /**
     * Constructor to initialize CheckoutOverviewPage
     * 
     * @param driver WebDriver instance
     */
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ==================== PAGE TITLE ====================

    public boolean isPageTitleDisplayed() {
        return isElementDisplayed(pageTitle);
    }

    public String getPageTitle() {
        return getElementText(pageTitle);
    }

    // ==================== CART ITEMS ====================

    public int getCartItemCount() {
        return cartItems.size();
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
        return getElementText(itemNames.get(index));
    }

    public String getItemDescriptionByIndex(int index) {
        return getElementText(itemDescriptions.get(index));
    }

    public String getItemPriceByIndex(int index) {
        return getElementText(itemPrices.get(index));
    }

    public String getItemQuantityByIndex(int index) {
        return getElementText(itemQuantities.get(index));
    }

    public boolean isItemDisplayed(int index) {
        return isElementDisplayed(cartItems.get(index));
    }

    // ==================== ORDER SUMMARY ====================

    public boolean isSummaryInfoDisplayed() {
        return isElementDisplayed(summaryInfo);
    }

    public String getSubtotalLabel() {
        return getElementText(subtotalLabel);
    }

    public double getSubtotal() {
        String subtotalText = getSubtotalLabel();
        return extractPrice(subtotalText);
    }

    public String getTaxLabel() {
        return getElementText(taxLabel);
    }

    public double getTax() {
        String taxText = getTaxLabel();
        return extractPrice(taxText);
    }

    public String getTotalLabel() {
        return getElementText(totalLabel);
    }

    public double getTotal() {
        String totalText = getTotalLabel();
        return extractPrice(totalText);
    }

    /**
     * Extract price value from text (e.g., "Total: $25.99" -> 25.99)
     * 
     * @param priceText Text containing price
     * @return Double value of price
     */
    private double extractPrice(String priceText) {
        String priceOnly = priceText.replaceAll("[^0-9.]", "");
        return priceOnly.isEmpty() ? 0.0 : Double.parseDouble(priceOnly);
    }

    /**
     * Verify subtotal calculation matches sum of item prices
     * 
     * @return true if subtotal is correct
     */
    public boolean isSubtotalCorrect() {
        double calculatedSubtotal = getAllItemPrices().stream()
                .mapToDouble(this::extractPrice)
                .sum();
        return Math.abs(calculatedSubtotal - getSubtotal()) < 0.01;
    }

    /**
     * Verify total calculation (subtotal + tax)
     * 
     * @return true if total is correct
     */
    public boolean isTotalCorrect() {
        double calculatedTotal = getSubtotal() + getTax();
        return Math.abs(calculatedTotal - getTotal()) < 0.01;
    }

    // ==================== BUTTONS ====================

    public boolean isFinishButtonDisplayed() {
        return isElementDisplayed(finishButton);
    }

    public boolean isCancelButtonDisplayed() {
        return isElementDisplayed(cancelButton);
    }

    public boolean isFinishButtonEnabled() {
        return isElementEnabled(finishButton);
    }

    public boolean isCancelButtonEnabled() {
        return isElementEnabled(cancelButton);
    }

    public String getFinishButtonText() {
        return getElementText(finishButton);
    }

    public String getCancelButtonText() {
        return getElementText(cancelButton);
    }

    public void clickFinish() {
        clickElement(finishButton);
    }

    public void clickCancel() {
        clickElement(cancelButton);
    }

    // ==================== CART BADGE ====================

    public boolean isCartBadgeDisplayed() {
        return isElementDisplayed(cartBadge);
    }

    public String getCartBadgeCount() {
        return getElementText(cartBadge);
    }

    // ==================== UTILITY METHODS ====================

    /**
     * Verify all items are displayed with complete information
     * 
     * @return true if all items have name, description, price, and quantity
     */
    public boolean areAllItemsCompletelyDisplayed() {
        return !getAllItemNames().isEmpty() &&
               !getAllItemDescriptions().isEmpty() &&
               !getAllItemPrices().isEmpty() &&
               !getAllItemQuantities().isEmpty() &&
               getAllItemNames().size() == getCartItemCount();
    }

    /**
     * Calculate total price of all items (quantity * price)
     * 
     * @return Total price before tax
     */
    public double calculateItemsTotal() {
        double total = 0;
        for (int i = 0; i < getCartItemCount(); i++) {
            int qty = Integer.parseInt(getItemQuantityByIndex(i));
            double price = extractPrice(getItemPriceByIndex(i));
            total += qty * price;
        }
        return total;
    }
}
