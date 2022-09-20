package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


/**
 * @author Alexander Pangilinan
 */

@CucumberOptions(features={"src/test/java/Features"}
					,glue={"StepDefinitions"}
					,plugin = {"pretty", "html:target/cucumber-reports/lingble_Automation/cucumber-pretty","json:target/json-cucumber-reports/lingble_Automation/lingbleAutomation.json",
		"testng:target/testng-cucumber-reports/lingble_Automation/lingbleAutomation.xml"}
					,tags = "@AccountManagement"
		)
public class RunTest extends AbstractTestNGCucumberTests {
	@BeforeClass
	public static void before() {
		System.out.println("Before - "+System.currentTimeMillis());
	}

	@AfterClass
	public static void after() {
		System.out.println("After - "+System.currentTimeMillis());
	}
}
