package com.shopease.automation.tests;

import com.shopease.automation.base.BaseTest;
import com.shopease.automation.pages.LoginPage;
import com.shopease.automation.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void verifyValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername(ConfigReader.getProperty("valid.username"));
        loginPage.enterPassword(ConfigReader.getProperty("valid.password"));
        loginPage.clickLogin();
        
        Assert.assertTrue(loginPage.isDashboardDisplayed(), "Dashboard should be displayed after valid login.");
    }

    @Test(description = "Verify login fails with invalid credentials")
    public void verifyInvalidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername("invaliduser");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();
        
        Assert.assertFalse(loginPage.isDashboardDisplayed(), "Dashboard should NOT be displayed after invalid login.");
    }
}
