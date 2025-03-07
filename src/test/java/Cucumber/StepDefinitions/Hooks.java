package Cucumber.StepDefinitions;

import io.cucumber.java.BeforeAll;

import java.io.IOException;

import com.Utils.Configs.Core;
import com.Utils.Reports.Log;

import io.cucumber.java.AfterAll;


public class Hooks {

    @BeforeAll
    public static void beforeAllTests() throws IOException {
        Log.initReport("GetAPICallsReport", Core.readProperty("tester"), Core.readProperty("environment"));
    }

    @AfterAll
    public static void afterAllTests() {
        Log.generateReport();
    }
}

