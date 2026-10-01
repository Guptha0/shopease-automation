package com.shopease.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    
    // Locators for demowebshop.tricentis.com
    private By cartItems = By.cssSelector(".cart-item-row .product a");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            if (item.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }
}
