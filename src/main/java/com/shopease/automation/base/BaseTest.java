package com.shopease.automation.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected Properties config;

    @BeforeMethod
    public void setUp() {
        loadConfig();
        String browser = config.getProperty("browser", "chrome").toLowerCase();
        
        WebDriver webDriver;
        switch (browser) {
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

        driver.set(webDriver);
        
        int implicitWait = Integer.parseInt(config.getProperty("timeout.implicit", "10"));
        int pageLoadWait = Integer.parseInt(config.getProperty("timeout.pageLoad", "30"));
        
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWait));
        getDriver().manage().window().maximize();
        
        getDriver().get(config.getProperty("base.url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
        
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    private void loadConfig() {
        config = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties");
        }
    }

    private void takeScreenshot(String testName) {
        if (getDriver() != null) {
            File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshots/" + testName + "_" + System.currentTimeMillis() + ".png");
            try {
                dest.getParentFile().mkdirs();
                Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
