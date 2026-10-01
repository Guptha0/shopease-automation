package com.shopease.automation.base;

import com.shopease.automation.utils.ConfigReader;
import com.shopease.automation.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    // ThreadLocal ensures thread-safe WebDriver execution for parallel testing
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browser = ConfigReader.getProperty("browser", "chrome");
        
        // Use the DriverFactory to get the driver (this applies the headless logic!)
        WebDriver webDriver = com.shopease.automation.utils.DriverFactory.initDriver(browser);
        
        webDriver.get(ConfigReader.getProperty("app.url"));
        
        // The DriverFactory already manages ThreadLocal, but since BaseTest has its own,
        // we update it here just to be safe with existing references.
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return com.shopease.automation.utils.DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Automatically capture screenshot on failure
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(getDriver(), result.getName());
        }
        
        com.shopease.automation.utils.DriverFactory.quitDriver();
        driver.remove();
    }
}
