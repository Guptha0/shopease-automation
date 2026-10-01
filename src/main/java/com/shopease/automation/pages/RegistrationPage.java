package com.shopease.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    private By regLink = By.linkText("Register");
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("email");
    private By password = By.id("password");
    private By submitBtn = By.id("register-submit");
    private By successMsg = By.className("success-message");

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
    }

    public void submitRegistration() {
        driver.findElement(submitBtn).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMsg).size() > 0;
    }
}
