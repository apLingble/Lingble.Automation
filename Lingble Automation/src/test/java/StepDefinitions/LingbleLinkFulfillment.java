package StepDefinitions;

import framework.environment.BaseStepsDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author Alexander Pangilinan
 * */

public class LingbleLinkFulfillment extends BaseStepsDefinitions {
    @Before
    public void before(Scenario scenario) {
        this.scenDesc = scenario.getName();
    }

    @After
    public void after(Scenario scenario){
        TearDown(scenario);
    }

    @Given("Open Lingble Link {string} LoginPage.")
    public void openLingbleLinkLoginPage(String partner){
        baseStepsDefinitions(partner,"stg");
        lingbleLinkKeyword.openLingbleLinkPage();
        lingbleLinkKeyword.waitUntilLingblePageready();
    }

    @When("User enters valid credentials.")
    public void userEntersValidCredentials(){
        lingbleLinkKeyword.enterLLEmail();
        lingbleLinkKeyword.enterLLPassword();
    }

    @And("User clicks log in button.")
    public void userClicksLogInButton(){
        lingbleLinkKeyword.proceedToLoginLL();
    }

    @Then("Wait for LL Page to be ready.")
    public void waitForLLPageToBeReady() {
        lingbleLinkKeyword.waitForLLPageToBeReady();
    }

    @And("Select Order List Menu.")
    public void selectOrderListMenu(){
        lingbleLinkKeyword.selectOrderListMenu();
    }
    @And("User selects Partner Site.")
    public void userSelectPartnerSite(){
        lingbleLinkKeyword.selectPartnerSite();
    }
    @Then("validate Selected Partner Site.")
    public void validateSelectedPartnerSite(){
        lingbleLinkKeyword.validateSelectedPartnerSite();
        //lingbleLinkKeyword.checkForSomethingWentWrongMessage();
    }

    @And("User sets the order list filter.")
    public void setOrderListFilter(){
        lingbleLinkKeyword.setOrderListFilter(apiRequestKeyword.getOrderNumberFulfillment());
    }

    @And("Validate order number.")
    public void validateOrderNumber(){
        lingbleLinkKeyword.validateOrderNumber(apiRequestKeyword.getOrderNumberFulfillment());
    }

    @Then("Get first order number in the list.")
    public void getFirstOrderNumberInTheList() {
        lingbleLinkKeyword.clickFirstOrderNumber();
    }

    @And("Validate the payment status.")
    public void validateThePaymentStatus() {
        lingbleLinkKeyword.validatePaymentStatus();
    }

    @Then("Check for No Location Assigned.")
    public void checkForNoLocationAssigned() {
        lingbleLinkKeyword.checkNoLocationAssigned();
    }

    @Then("User performs the fulfillment flow.")
    public void userPerformsTheFulfillmentFlow() {
        lingbleLinkKeyword.doFulfillmentFlow();
    }


    @And("Validate if order status is completed.")
    public void validateIfOrderStatusIsCompleted() {
        lingbleLinkKeyword.validateOrderStatus();
    }

    @And("Validate if fulfillment status is fulfilled.")
    public void validateIfFulfillmentStatusIsFulfilled() {
        lingbleLinkKeyword.validateFulfillmentStatus();
    }
}