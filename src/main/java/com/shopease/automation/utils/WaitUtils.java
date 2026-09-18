package com.shopease.automation.utils;

import com.shopease.automation.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_EXPLICIT_TIMEOUT = 20;

    public static WebDriverWait getWait() {
        return new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(DEFAULT_EXPLICIT_TIMEOUT));
    }

    public static WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(timeoutInSeconds));
    }

    public static WebElement waitForVisibility(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickability(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean waitForInvisibility(WebElement element) {
        return getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
