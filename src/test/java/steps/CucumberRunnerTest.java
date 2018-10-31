package steps;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json"},
        tags = {"not @ignore"}
)

public class CucumberRunnerTest {
    // Этот класс всегда должен быть пустым ! Имя класса обязательно должно закачиваться словом Test !
}
