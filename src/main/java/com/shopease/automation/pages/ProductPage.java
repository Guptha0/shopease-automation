package com.shopease.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;
    
    // Locators for demowebshop.tricentis.com
    private By firstProduct = By.cssSelector(".product-item .product-title a");
    private By addToCartBtn = By.cssSelector("input.add-to-cart-button");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }
}
