package com.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;

    /**
     * Initialize Extent Report with a fixed report name (overwrite mode)
     * @param reportName
     */
    public static void initReport(String reportName, String testerName, String environment) {
        String reportsDir = System.getProperty("user.dir") + "/src/main/resources/Reports/";
        File dir = new File(reportsDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Create Reports folder if it doesn't exist
        }

        // Fixed report name
        reportPath = reportsDir + reportName + ".html";

        // ✅ Force delete existing report (if it exists)
        File reportFile = new File(reportPath);
        if (reportFile.exists()) {
            if (reportFile.delete()) {
                logger.info("🗑 Old report deleted: " + reportPath);
            } else {
                logger.error("⚠ Failed to delete old report. Check file permissions.");
            }
        }

        // Now create a new fresh report
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", testerName);
        extent.setSystemInfo("Environment", environment);

        logger.info("✅ Extent Report Initialized: " + reportPath);
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
    public void testCaseInfo(String message) {
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
}



