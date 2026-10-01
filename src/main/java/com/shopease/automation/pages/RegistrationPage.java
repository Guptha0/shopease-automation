package com.shopease.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    // Locators for demowebshop.tricentis.com
    private By regLink = By.linkText("Register");
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By email = By.id("Email");
    private By password = By.id("Password");
    private By confirmPassword = By.id("ConfirmPassword");
    private By submitBtn = By.id("register-button");
    private By successMsg = By.className("result"); // Class for "Your registration completed"

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToRegistration() {
        driver.findElement(regLink).click();
    }

    public void fillRegistrationForm(String fName, String lName, String mail, String pass) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmPassword).sendKeys(pass);
    }

    public void submitRegistration() {
        driver.findElement(submitBtn).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMsg).size() > 0;
    }
}
