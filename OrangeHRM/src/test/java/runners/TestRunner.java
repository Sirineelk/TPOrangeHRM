package runners;

import configuration.Hooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "steps",
                "utils",
                "configuration"
        },
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true,
        tags = "@supressionUser" //pour rajouter plusieurs tags il faut mettre un or entre eux
)
public class TestRunner extends Hooks {
}
