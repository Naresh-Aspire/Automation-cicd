package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="LearnSelnium.stepdefination",
monochrome=true,tags ="@errorValidation",plugin= {"html:target/cucumber.html"})//
public class TestNGtestrunner extends AbstractTestNGCucumberTests{

}
