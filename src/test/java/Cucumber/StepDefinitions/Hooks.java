package Cucumber.StepDefinitions;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import java.io.IOException;

import com.Utils.Configs.Core;
import com.Utils.Reports.Log;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;


public class Hooks {

    @BeforeAll
    public static void beforeAllTests() throws IOException {
        Log.initReport("APIReport", Core.readProperty("tester"), Core.readProperty("environment"));
    }

    @AfterAll
    public static void afterAllTests() {
        Log.generateReport();
    }
    
    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioTitle = scenario.getName(); 
        Log.startTest(scenarioTitle);
    }
}

