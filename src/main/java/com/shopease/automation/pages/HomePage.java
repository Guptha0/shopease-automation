package com.shopease.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    
    private By searchBox = By.name("q");
    private By searchBtn = By.id("search-btn");
    private By cartIcon = By.id("cart-icon");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBtn).click();
    }

    public CartPage navigateToCart() {
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }
}
