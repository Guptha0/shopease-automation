package com.shopease.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.shopease.automation.base.BaseTest;
import com.shopease.automation.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        sparkReporter.config().setDocumentTitle("ShopEase Automation Report");
        sparkReporter.config().setReportName("Week 3 End-to-End Test Execution");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "ShopEase E-Commerce");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        
        try {
            // Attach screenshot to report
            String screenshotPath = ScreenshotUtil.takeScreenshot(BaseTest.getDriver(), result.getName());
            test.get().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
