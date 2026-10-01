package com.shopease.automation.tests;

import com.shopease.automation.base.BaseTest;
import com.shopease.automation.pages.CartPage;
import com.shopease.automation.pages.HomePage;
import com.shopease.automation.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductSearchCartTests extends BaseTest {

    @Test(description = "Verify searching for a product and adding it to the cart")
    public void verifySearchAndAddToCart() {
        HomePage homePage = new HomePage(getDriver());
        String searchItem = "Laptop"; // Changed from Wireless Headphones to Laptop for Tricentis
        
        homePage.searchForProduct(searchItem);
        
        ProductPage productPage = new ProductPage(getDriver());
        productPage.selectFirstProduct();
        productPage.addToCart();
        
        CartPage cartPage = homePage.navigateToCart();
        Assert.assertTrue(cartPage.isProductInCart(searchItem), "Product should be present in the cart.");
    }
}
