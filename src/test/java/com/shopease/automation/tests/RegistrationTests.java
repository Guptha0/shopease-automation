package com.shopease.automation.tests;

import com.shopease.automation.base.BaseTest;
import com.shopease.automation.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    @Test(description = "Verify user can register with valid details")
    public void verifyUserRegistration() {
        RegistrationPage regPage = new RegistrationPage(getDriver());
        regPage.navigateToRegistration();
        regPage.fillRegistrationForm("Test", "User", "testuser" + System.currentTimeMillis() + "@shopease.com", "SecureP@ss123");
        regPage.submitRegistration();
        
        Assert.assertTrue(regPage.isSuccessMessageDisplayed(), "Registration success message should be displayed.");
    }
}
