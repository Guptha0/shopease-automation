package com.shopease.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverFactory {
    
    // ThreadLocal to support parallel test execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initializes and returns the WebDriver instance based on the provided browser name.
     * Includes logic to run Chrome in headless mode for CI/CD environments.
     *
     * @param browser The name of the browser (e.g., "chrome")
     * @return WebDriver instance
     */
    public static WebDriver initDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            
            // Check if 'headless' system property is set to 'true' or if we are in CI environment
            String headlessProp = System.getProperty("headless");
            String ciEnv = System.getenv("CI");
            
            if ("true".equalsIgnoreCase(headlessProp) || "true".equalsIgnoreCase(ciEnv)) {
                // Configure ChromeOptions for stable headless execution in CI runners
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            
            driver.set(new ChromeDriver(options));
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        return getDriver();
    }

    /**
     * Returns the current ThreadLocal WebDriver instance.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits the WebDriver and removes the ThreadLocal instance.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
