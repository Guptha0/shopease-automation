package com.shopease.automation.pages;

import com.shopease.automation.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for the ShopEase Registration Page.
 * Encapsulates all WebElements and user interactions required for the registration flow.
 * Implements a Fluent interface design pattern for method chaining.
 */
public class RegistrationPage {

    private WebDriver driver;

    // --- Form Element Locators ---
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

    // --- Validation and Success Message Locators ---
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
     * Constructor to initialize WebDriver and PageFactory elements.
     * @param driver the WebDriver instance
     */
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // --- Action Methods (with Explicit Waits) ---

    public RegistrationPage enterFullName(String fullName) {
        WebElement input = WaitUtils.waitForVisibility(fullNameInput);
        input.clear();
        input.sendKeys(fullName);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        WebElement input = WaitUtils.waitForVisibility(emailInput);
        input.clear();
        input.sendKeys(email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        WebElement input = WaitUtils.waitForVisibility(passwordInput);
        input.clear();
        input.sendKeys(password);
        return this;
    }

    public RegistrationPage enterConfirmPassword(String confirmPassword) {
        WebElement input = WaitUtils.waitForVisibility(confirmPasswordInput);
        input.clear();
        input.sendKeys(confirmPassword);
        return this;
    }

    public RegistrationPage clickTermsCheckbox() {
        WebElement checkbox = WaitUtils.waitForClickability(termsCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        return this;
    }

    public void clickRegisterButton() {
        WaitUtils.waitForClickability(registerButton).click();
    }

    // --- Validation Message Retrieval Methods ---

    public String getFullNameErrorMessage() {
        return WaitUtils.waitForVisibility(fullNameError).getText().trim();
    }

    public String getEmailErrorMessage() {
        return WaitUtils.waitForVisibility(emailError).getText().trim();
    }

    public String getPasswordErrorMessage() {
        return WaitUtils.waitForVisibility(passwordError).getText().trim();
    }

    public String getConfirmPasswordErrorMessage() {
        return WaitUtils.waitForVisibility(confirmPasswordError).getText().trim();
    }

    public String getRegistrationSuccessMessage() {
        return WaitUtils.waitForVisibility(registrationSuccessMessage).getText().trim();
    }
}
