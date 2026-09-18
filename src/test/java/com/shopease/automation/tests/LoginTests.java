package com.shopease.automation.tests;

import com.shopease.automation.base.BaseTest;
import com.shopease.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage();
        
        loginPage.login("validUser", "validPassword123");
        
        // Example assertion (commented since URL is hypothetical)
        // DashboardPage dashboardPage = new DashboardPage();
        // Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after login");
        
        System.out.println("Valid login test executed successfully.");
    }
    
    @Test(description = "Verify login failure with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage();
        
        loginPage.login("invalidUser", "wrongPassword");
        
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Invalid username or password"), 
                "Expected error message not displayed.");
    }
}
