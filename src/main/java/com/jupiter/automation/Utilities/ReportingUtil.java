package com.jupiter.automation.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportingUtil
{
    public static ExtentReports reporterInit()
    {
        String testReportPath = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(testReportPath);
        reporter.config().setReportName("Jupiter App Test Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Squad", "Jupiter Web Automation Team");
        return extentReports;

    }
}
