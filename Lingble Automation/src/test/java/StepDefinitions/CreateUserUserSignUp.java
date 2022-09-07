package StepDefinitions;

import framework.commonFunctions.commonHelper;
import framework.environment.BaseStepsDefinitions;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author Alexander Pangilinan
 * */

public class CreateUserUserSignUp extends BaseStepsDefinitions {

    @Before
    public void before(Scenario scenario) {
        this.scenDesc = scenario.getName();
    }

    @After
    public void after(Scenario scenario){
        TearDown(scenario);
    }
    @AfterStep
    public void afterStep() throws Throwable {
        commonHelper.takeScreenshot();
    }
    @When("User navigate to account creation details")
    public void userNavigateToAccountCreationDetails() {
        homeKeyword.openLogInCreateAccountPage();
        loginKeyword.waitForLogInCreateAccountPage();
        loginKeyword.openCreateAccountTab();
    }

    @Then("User enter firstname")
    public void userEnterFirstname() {
        loginKeyword.enterFirstName(System.getProperty("UC_firstName"));
    }

    @And("User enter lastname")
    public void userEnterLastname() {
        loginKeyword.enterLastName(System.getProperty("UC_lastName"));
    }

    @And("User enter phone")
    public void userEnterPhone() {
        loginKeyword.enterPhone(System.getProperty("UC_userPhone"));

    }

    @And("User enter email")
    public void userEnterEmail() {
        loginKeyword.enterEmail("qa+"+ commonHelper.generateTime("ddMMyyyyhhmmss")+"@lingble.com");
    }

    @And("User enter confirmEmail")
    public void userEnterConfirmEmail() {
        loginKeyword.enterConfirmEmail();
    }

    @And("User enter password")
    public void userEnterPassword() {
        loginKeyword.enterPassword(System.getProperty("UC_userPassword"));
    }

    @And("User enter confirmPassword")
    public void userEnterConfirmPassword() {
        loginKeyword.enterConfirmPassword(System.getProperty("UC_confirmUserPassword"));
    }

    @And("User click create account button")
    public void userClickCreateAccountButton() {
        loginKeyword.proceedToAccountCreation();
    }


}
