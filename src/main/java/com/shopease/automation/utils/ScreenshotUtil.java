package com.shopease.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String testName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        
        String destinationPath = System.getProperty("user.dir") + "/reports/screenshots/" + testName + "_" + dateName + ".png";
        File finalDestination = new File(destinationPath);
        
        try {
            // Ensure directory exists
            finalDestination.getParentFile().mkdirs();
            Files.copy(source.toPath(), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationPath;
    }
}
