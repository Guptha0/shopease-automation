package com.shopease.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;
    
    private By firstProduct = By.cssSelector(".product-list .item:first-child");
    private By addToCartBtn = By.id("add-to-cart");

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
