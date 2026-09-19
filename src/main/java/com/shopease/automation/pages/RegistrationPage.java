package com.shopease.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private WebDriver driver;

    // Form element locators
    @FindBy(id = "fullName")
    private WebElement fullNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(id = "termsCheckbox")
    private WebElement termsCheckbox;

    @FindBy(id = "registerBtn")
    private WebElement registerButton;

    // Error and validation message locators
    @FindBy(id = "fullNameError")
    private WebElement fullNameError;

    @FindBy(id = "emailError")
    private WebElement emailError;

    @FindBy(id = "passwordError")
    private WebElement passwordError;

    @FindBy(id = "confirmPasswordError")
    private WebElement confirmPasswordError;

    @FindBy(id = "registrationSuccess")
    private WebElement registrationSuccessMessage;

    /**
     * Constructor to initialize PageFactory elements.
     * @param driver the WebDriver instance
     */
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Action Methods

    public void enterFullName(String fullName) {
        fullNameInput.clear();
        fullNameInput.sendKeys(fullName);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public void clickTermsCheckbox() {
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    // Methods to retrieve validation/error messages

    public String getFullNameErrorMessage() {
        return fullNameError.getText();
    }

    public String getEmailErrorMessage() {
        return emailError.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordError.getText();
    }

    public String getConfirmPasswordErrorMessage() {
        return confirmPasswordError.getText();
    }

    public String getRegistrationSuccessMessage() {
        return registrationSuccessMessage.getText();
    }
}
