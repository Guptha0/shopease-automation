package com.shopease.automation.pages;

import com.shopease.automation.base.BaseTest;
import com.shopease.automation.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "loginBtn")
    private WebElement loginButton;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(BaseTest.getDriver(), this);
    }

    public LoginPage enterUsername(String username) {
        WaitUtils.waitForVisibility(usernameInput).clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        WaitUtils.waitForVisibility(passwordInput).clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        WaitUtils.waitForClickability(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return WaitUtils.waitForVisibility(errorMessage).getText();
    }
}
