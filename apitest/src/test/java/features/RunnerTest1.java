package features;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class) //Con que voy a correr mis test porque estamos usando cucumber con serenity
@CucumberOptions(features = "src/test/resources/features", glue = "steps") // El camino de las features
public class RunnerTest1 {

}