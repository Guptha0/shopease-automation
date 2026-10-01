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

        WebDriver webDriver;
        switch (browser.toLowerCase()) {
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            case "chrome":
            default:
                webDriver = new ChromeDriver();
                break;
        }

        webDriver.manage().window().maximize();
        
        long implicitWait = Long.parseLong(ConfigReader.getProperty("implicit.wait", "10"));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        
        webDriver.get(ConfigReader.getProperty("app.url"));
        
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Automatically capture screenshot on failure
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(getDriver(), result.getName());
        }
        
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
