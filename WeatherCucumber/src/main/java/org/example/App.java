package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Hello world!
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = "org.example.stepDefinition", features = "src/main/java/org/example/feature", tags = "@excel",
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class App 
{

}
