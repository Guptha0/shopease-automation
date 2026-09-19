package com.shopease.automation.tests;

import com.shopease.automation.base.BaseTest;
import com.shopease.automation.pages.RegistrationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * Test class for validating user registration flows.
 * Extends BaseTest to leverage central WebDriver initialization and teardown.
 */
public class RegistrationTests extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RegistrationTests.class);
    private RegistrationPage registrationPage;

    @BeforeMethod
    public void setupTest() {
        logger.info("Initializing RegistrationPage objects and navigating to Registration URL.");
        
        // BaseTest exposes getDriver() and handles global setups like implicit waits and maximization
        String registerUrl = "https://www.shopease.com/register"; // Can be moved to config.properties
        getDriver().get(registerUrl);
        
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test(description = "Happy Path: Verify successful registration with valid, unique data")
    public void testSuccessfulRegistration() {
        logger.info("Starting test: testSuccessfulRegistration");
        
        // Generate a random unique email to prevent user-already-exists collisions
        String uniqueEmail = "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        logger.info("Generated unique email for test: " + uniqueEmail);
        
        // Utilizing fluent interface pattern implemented in RegistrationPage
        registrationPage.enterFullName("John Architect")
                        .enterEmail(uniqueEmail)
                        .enterPassword("Str0ngP@ssw0rd!")
                        .enterConfirmPassword("Str0ngP@ssw0rd!")
                        .clickTermsCheckbox()
                        .clickRegisterButton();

        // Validate success message (Implicitly handled by WaitUtils within the page object)
        String successMsg = registrationPage.getRegistrationSuccessMessage();
        logger.info("Registration success message captured: " + successMsg);
        
        Assert.assertTrue(successMsg.contains("Registration successful"), 
            "The expected success message was not displayed upon valid registration.");
    }

    @Test(description = "Negative Scenario: Verify validation message for invalid email format")
    public void testInvalidEmailFormat() {
        logger.info("Starting test: testInvalidEmailFormat");
        
        registrationPage.enterFullName("Jane Doe")
                        .enterEmail("invalid.email.com") // Invalid format: Missing '@'
                        .enterPassword("SecurePass123!")
                        .enterConfirmPassword("SecurePass123!")
                        .clickTermsCheckbox()
                        .clickRegisterButton();

        String emailErrorMsg = registrationPage.getEmailErrorMessage();
        logger.info("Captured email validation error: " + emailErrorMsg);
        
        Assert.assertEquals(emailErrorMsg, "Please enter a valid email address.", 
            "Email format validation failed or displayed incorrect message.");
    }

    @Test(description = "Negative Scenario: Verify validation message when passwords do not match")
    public void testPasswordMismatch() {
        logger.info("Starting test: testPasswordMismatch");
        
        registrationPage.enterFullName("Jack Architect")
                        .enterEmail("jack.architect@example.com")
                        .enterPassword("SecurePass123!")
                        .enterConfirmPassword("DifferentPass456!") // Mismatched confirmation
                        .clickTermsCheckbox()
                        .clickRegisterButton();

        String mismatchErrorMsg = registrationPage.getConfirmPasswordErrorMessage();
        logger.info("Captured password mismatch error: " + mismatchErrorMsg);
        
        Assert.assertEquals(mismatchErrorMsg, "Passwords do not match.", 
            "Password mismatch validation failed or displayed incorrect message.");
    }
}
