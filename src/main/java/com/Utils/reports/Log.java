package com.Utils.reports;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Utils.configs.Core;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.IOException;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;

    /**
     * Initialize Extent Report with a fixed report name (overwrite mode)
     * @param reportName
     * @throws IOException 
     */
    public static void initReport(String reportName, String testerName, String environment) throws IOException {
        String reportsDir = System.getProperty("user.dir") + "/src/main/resources/Reports/";
        reportPath = reportsDir + reportName + ".html";
        Core.createFile(reportsDir, reportPath);
        // Now create a new fresh report
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", testerName);
        extent.setSystemInfo("Environment", environment);

        logger.info("📊📉 Extent Report Initialized: " + reportPath);
    }

    /**
     * Start a new test case in the report
     * @param testName
     */
    public void startTest(String testName) {
        test = extent.createTest(testName);
        logger.info("📝 Test Started: " + testName);
    }

    /**
     * End the current test case
     * @param testName
     */
    public void endTest(String testName) {
        logger.info("🛑 Test Ended: " + testName + "\n\n");
    }

    /**
     * Log Info Message
     * @param message
     */
    public void message(String message) {
        logger.info(message);
        if (test != null) {
            test.info(message);
        }
    }

    /**
     * Log Pass Message
     * @param message
     */
    public void pass(String message) {
        logger.info("✅ " + message);
        if (test != null) {
            test.pass(message);
        }
    }

    /**
     * Log Fail Message
     * @param message
     */
    public void fail(String message) {
        logger.error("❌ " + message);
        if (test != null) {
            test.fail(message);
        }
    }

    /**
     * Generate the final report
     */
    public static void generateReport() {
        if (extent != null) {
            extent.flush();
            logger.info("📄 Extent Report Generated: " + reportPath);
        }
    }
    
    public void assertThat(String actual, String expected) {
        if (actual.equals(expected)) {logger.info("✅ Assertion Passed - Expected: [" + expected + "], Actual: [" + actual + "]");
        } else {
            logger.error("❌ Assertion Failed - Expected: [" + expected + "], Actual: [" + actual + "]");
            throw new IllegalStateException("Assertion Failed - Expected: [" + expected + "], Actual: [" + actual + "]");
        }
    }

}



