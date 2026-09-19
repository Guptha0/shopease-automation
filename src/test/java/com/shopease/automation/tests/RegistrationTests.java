package com.shopease.automation.tests;

import com.shopease.automation.pages.RegistrationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationTests {

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(RegistrationTests.class);

    @BeforeMethod
    public void setUp() {
        logger.info("Setting up WebDriver for test execution...");
        
        // Initialize WebDriver (assuming chromedriver is in path or using a driver manager)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Initialize explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // Navigate to the ShopEase registration URL
        // In a real project, this URL would typically come from a properties file
        String registerUrl = "https://www.shopease.com/register"; 
        driver.get(registerUrl);
        logger.info("Navigated to Registration Page: " + registerUrl);
        
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    public void testSuccessfulRegistration() {
        logger.info("Executing test: testSuccessfulRegistration");
        
        // Use a unique email to ensure the test can run repeatedly without collision
        String uniqueEmail = "user_" + System.currentTimeMillis() + "@example.com";
        
        registrationPage.enterFullName("John Doe");
        registrationPage.enterEmail(uniqueEmail);
        registrationPage.enterPassword("SecurePass123!");
        registrationPage.enterConfirmPassword("SecurePass123!");
        registrationPage.clickTermsCheckbox();
        
        logger.info("Filled registration form. Submitting...");
        registrationPage.clickRegisterButton();

        // Wait for the URL to change or for a success element to be visible
        wait.until(ExpectedConditions.urlContains("dashboard")); // Example condition
        
        // Example validation for a successful registration message
        // wait.until(ExpectedConditions.visibilityOf(registrationSuccessMessageElement));
        // String successMsg = registrationPage.getRegistrationSuccessMessage();
        // Assert.assertTrue(successMsg.contains("Registration successful"), "Success message is not correct.");
        
        logger.info("testSuccessfulRegistration completed successfully.");
    }

    @Test
    public void testInvalidEmailValidation() {
        logger.info("Executing test: testInvalidEmailValidation");
        
        registrationPage.enterFullName("Jane Doe");
        registrationPage.enterEmail("invalid-email-format");
        registrationPage.enterPassword("SecurePass123!");
        registrationPage.enterConfirmPassword("SecurePass123!");
        registrationPage.clickTermsCheckbox();
        registrationPage.clickRegisterButton();

        // Validate the inline error message for the email field
        String emailErrorMsg = registrationPage.getEmailErrorMessage();
        logger.info("Captured email validation error: " + emailErrorMsg);
        
        Assert.assertEquals(emailErrorMsg, "Please enter a valid email address.", "Incorrect email validation message displayed.");
        
        logger.info("testInvalidEmailValidation completed successfully.");
    }

    @Test
    public void testPasswordMismatch() {
        logger.info("Executing test: testPasswordMismatch");
        
        registrationPage.enterFullName("Jack Doe");
        registrationPage.enterEmail("jack.doe@example.com");
        registrationPage.enterPassword("SecurePass123!");
        registrationPage.enterConfirmPassword("MismatchPass456!");
        registrationPage.clickTermsCheckbox();
        registrationPage.clickRegisterButton();

        // Validate the validation prompt for mismatched passwords
        String mismatchErrorMsg = registrationPage.getConfirmPasswordErrorMessage();
        logger.info("Captured password mismatch error: " + mismatchErrorMsg);
        
        Assert.assertEquals(mismatchErrorMsg, "Passwords do not match.", "Incorrect password mismatch validation message displayed.");
        
        logger.info("testPasswordMismatch completed successfully.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Tearing down WebDriver...");
            driver.quit();
        }
    }
}
