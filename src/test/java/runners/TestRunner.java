package runners;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature/Revenue_calculator_test.feature"
        ,glue={"stepDefinition"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        publish = true
       //, tags = "@datavalidation"
)

public class TestRunner {
}
