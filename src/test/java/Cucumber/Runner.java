package Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/Cucumber/features", 
    glue = "src/test/java/Cucumber/StepDefinitions" 
)
public class Runner {
}

