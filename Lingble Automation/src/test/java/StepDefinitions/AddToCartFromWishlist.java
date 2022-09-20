package StepDefinitions;

import framework.commonFunctions.commonHelper;
import framework.environment.BaseStepsDefinitions;
import framework.keyword.CheckOutKeyword;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author Alexander Pangilinan
 * */

public class AddToCartFromWishlist extends BaseStepsDefinitions {

    @Before
    public void before(Scenario scenario) {
        this.scenDesc = scenario.getName();
    }

    @After
    public void after(Scenario scenario){
        TearDown(scenario);
    }
    @AfterStep
    public void afterStep(Scenario scenario) throws Throwable {
        commonHelper.takeScreenshot(scenario);
    }
    @And("Open login account")
    public void openLoginAccount() {
        cartKeyword.userIcon_userlogin();
    }
    @Then("validate account landing page")
    public void validateAccountLandingPage() {
        loginKeyword.waitForLogInCreateAccountPage();
    }
    @And("Login user account {string} {string}")
    public void loginUserAccount(String countryCode, String password) {
        loginKeyword.Account_enterUser_email(countryCode);
        loginKeyword.Account_enterUser_password(password);
    }
    @Then("Proceed to login")
    public void proceedToLogin() {
        loginKeyword.proceedToLogIn();
    }
    @And("click minicart")
    public void clickMinicart() {
        cartKeyword.minicart();
    }

    @Then("Move mouse to minicart then click")
    public void moveMouseToMinicartThenClick() {
        cartKeyword.minicart();
    }

    @Then("Check cart if empty or not {string}")
    public void checkCartIfEmptyOrNot(String partner) {
        cartKeyword.check_cartIfEmpty_orNot(partner, "");
    }

    @And("Enter shipping details {string}")
    public void enterShippingDetails(String countryCode) {
        checkOutKeyword.enterShippingDetails(countryCode);
    }

    @Then("Validate order summary")
    public void validateOrderSummary() {
        checkOutKeyword.validateOrderSummary();
    }

    @And("Enter checkout billing details {string} {string}")
    public void enterCheckoutBillingDetails(String paymentType, String countryCode) {
        checkOutKeyword.checkoutBillingDetails(paymentType, countryCode);
    }

    @Then("Validate order receipt")
    public void validateOrderReceipt() {
        checkOutKeyword.validateOrderReceipt();
    }

    @And("validate order number in LL")
    public void validateOrderNumberInLL() {
        String order_number =  checkOutKeyword.get_orderNumber();
        log.info("THIS IS THE CREATED ORDER NUMBER " + order_number);
        apiRequestKeyword.validateOrderNumberInLL(order_number);
    }

    @Then("Add click add to wishlist button")
    public void addClickAddToWishlistButton() {
        cartKeyword.add_toWishlist_button();
    }

    @And("Click remove to cart confirmation")
    public void clickRemoveToCartConfirmation() {
        cartKeyword.removeProuduct_confirmation();
    }

    @Then("Open wishlist")
    public void openWishlist() {
        cartKeyword.wishlist_icon();
        validateWishlistItem();
    }

    @Then("Check if partner needs to login to remove wishlist item {string} {string}")
    public void checkIfPartnerNeedsToLoginToRemoveWishlistItem(String countryCode, String password) {
        int emailTextbox_count = cartKeyword.get_emailTextbox_count();
        if(emailTextbox_count > 0){
            validateAccountLandingPage();
            loginUserAccount(countryCode, password);
            proceedToLogin();
            cartKeyword.view_all_wishlist();
            cartKeyword.validate_wishlist();
            cartKeyword.wishlist_removeItem_button();
            cartKeyword.check_wishlist_removeItem_button_notVisible();
        }
    }

    @And("Validate wishlist item")
    public void validateWishlistItem() {
        cartKeyword.validate_wishlist();
    }
}
