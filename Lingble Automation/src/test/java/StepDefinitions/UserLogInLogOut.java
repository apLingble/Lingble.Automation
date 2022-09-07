package StepDefinitions;

import framework.environment.BaseStepsDefinitions;

import framework.keyword.ApiRequestKeyword;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alexander Pangilinan
 * */

public class UserLogInLogOut extends BaseStepsDefinitions {

    @Before
    public void before(Scenario scenario) {
        this.scenDesc = scenario.getName();
    }

    @After
    public void after(Scenario scenario){
        TearDown(scenario);
    }

    @And("User has valid credentials")
    public void Userhasvalidcredentials()
    {
        homeKeyword.openPartnerSite();
        homeKeyword.closeNewsletterModal();
        homeKeyword.acceptCookies();
    }
    @When("User enters email and password")
    public void Userentersemailandpassword() {
        loginKeyword.openLogInTab();
        loginKeyword.setUserEmail();
        loginKeyword.setUserPassword();
    }
    @Then("Click wishlist button")
    public void Clickwishlistbutton()
    {
        homeKeyword.clickWishlistButton();
    }
    @And("User click login button")
    public void Userclickloginbutton()
    {
        loginKeyword.proceedToLogIn();
    }
    @And("User must be able to proceed to profile page")
    public void Usermustbeabletoproceedtoprofilepage()
    {
        profileKeyword.waitForLogInCreateAccountPage();
        profileKeyword.validateUserEmail();
    }
    @And("User must be able to validate {string}")
    public void userMustBeAbleToValidate(String email)
    {
        profileKeyword.validateUserEmail(email);
    }
    @Then("User must be able to log out")
    public void userMustBeAbleToLogOut()
    {
        loginKeyword.openAccountPage();
        homeKeyword.logOutUser();
    }
    @And("User close newsletter modal")
    public void UserCloseNewsletterModal()
    {
        homeKeyword.closeNewsletterModal();
    }
    @Then("User must be able to select on age consent")
    public void userMustBeAbleToSelectOnAgeConsent()
    {
        homeKeyword.ageConsent();
    }

    @And("User click wishlist")
    public void userClickWishlist() {
        homeKeyword.clickWishlistButton();
    }

    @Given("User has {string} url of selected {string}")
    public void userHasUrlOfSelected(String partner, String environment) {
        baseStepsDefinitions(partner,environment);
    }

    @And("Set ship to location to {string} and {string}")
    public void setShipToLocationToAnd(String country_code, String country_name) {
        homeKeyword.setCountry(country_code,country_name);
    }
}
