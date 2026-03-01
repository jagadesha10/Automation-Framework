package com.ui.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.Browser.Utility.BrowserUtility;
import com.ui.Browser.Utility.ExtentReporterUtility;
import com.ui.Test.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener {
    Logger logger = LogManager.getLogger(this.getClass());
    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public void onStart(ITestContext context) {
        logger.info("Test suite started");
        ExtentReporterUtility.setupSparkReporter("report.html");
//        ExtentReporterUtility.setupSparkReporter("report.html");
    }

    public void onTestStart(ITestResult result) {

        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
//    extentTest = extentReports.createTest(result.getMethod().getMethodName());
        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " " + " PASSED ");
        ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
    }

    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " " + "FAILED");
        logger.error(result.getThrowable().getMessage());
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");

        Object testclass =result.getInstance();

        BrowserUtility browserUtility=((TestBase)testclass).getInstance();
        logger.info("capturing screenshot");

        String screenshotPath =browserUtility.takeScreenshot(result.getMethod().getMethodName());
        logger.info("Attaching screenshot");

        ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
    }

    public void onTestSkipped(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " " + "SKIPPED");
        ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");

    }

    public void onFinish(ITestContext context) {
        logger.info(" Test Suite Completed");
        ExtentReporterUtility.flushReport();
    }
}
