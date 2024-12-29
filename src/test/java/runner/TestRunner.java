package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue= {"stepDefinition","helper"},tags="@Reg",
plugin="json:target/jsonReports/test.json")
public class TestRunner {


//test case  comment for  conflict
	//comment for git conflict

		//add comment 
	//add test automation


}
