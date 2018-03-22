package api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue={"api"},
                 features = {"src/test/java/api/resources/Tests.feature"})
public class RunCukeTests
{
}