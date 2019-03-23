package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) //Con que voy a correr mis test porque estamos usando cucumber con serenity
@CucumberOptions(features = "src/test/resources/features", glue = "steps") // El camino de las features
public class RunnerTest1 {

}