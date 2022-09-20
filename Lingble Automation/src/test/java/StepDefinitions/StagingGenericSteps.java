package StepDefinitions;

import framework.commonFunctions.SLLChecker;
import framework.environment.BaseStepsDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Pangilinan
 * */

public class StagingGenericSteps extends BaseStepsDefinitions {

    @Before
    public void before(Scenario scenario) {
        this.scenDesc = scenario.getName();
    }
    @After
    public void after(Scenario scenario){
        TearDown(scenario);
    }
    @Given("This user has {string} url of selected {string}")
    public void thisUserHasUrlOfSelected(String partner, String environment) {
        baseStepsDefinitions(partner,environment);
    }
    @Then("Navigate to ApplicationURL")
    public void userOpenPartnersite() {
        homeKeyword.openPartnerSite();
    }
    @Then("Navigate to ApplicationURL 2")
    public void userOpenPartnersiteURL2() {
        homeKeyword.openPartnerSiteURL2();
    }
    @Then("Navigate to ApplicationURL 3")
    public void userOpenPartnersiteURL3() {
        homeKeyword.openPartnerSiteURL3();
    }
    @Then("Navigate to ApplicationURL 4")
    public void userOpenPartnersiteURL4() {
        homeKeyword.openPartnerSiteURL4();
    }
    @And("Validate protocol and subdomain")
    public void validateProtocolAndSubdomain() {
        homeKeyword.validateProtocol();
        homeKeyword.validateSubdomain();
    }
    @And("Get the url then validate if it is contains demandware")
    public void getTheUrlThenValidateIfItIsContainsDemandware() {
        homeKeyword.urlValidateIfItIsContainsDemandware();
    }
    @When("Age consent for ecchiTokyo showed click yes")
    public void ageConsentForEcchiTokyoShowedClickYes() {
        homeKeyword.ageConsent();
    }

    @And("Validate newsletter form if working")
    public void validateNewsletterFormIfWorking() {
        homeKeyword.newsletterValidation();
    }
    @And("Close newsletter form")
    public void closeNewsletterForm() {
        homeKeyword.closeNewsletterModal();
    }
    @And("Accept cookies")
    public void acceptCookies() {
        homeKeyword.acceptCookies();
    }

    @When("Chat widget open minimize it")
    public void chatWidgetOpenMinimizeIt() {
        homeKeyword.minimizeChat();
    }
    @Then("Set to desire country {string} {string}")
    public void setToDesireCountry(String country_code, String country_name) {
        homeKeyword.setCountry(country_code,country_name);
    }
    @Then("Navigate to category tabs")
    public void navigateToCategoryTabs() {
        pageNavigationKeyword.FnavigateToCategoryTabs();
    }
    @Then("Search for product name {string}")
    public void searchForProductName(String partner) {
        CreateOrderKeyword.searchProduct(partner, "");
    }
    @And("Search for second product name {string}")
    public void searchForSecondProductName(String partner) {
        CreateOrderKeyword.searchProduct(partner, "second_");
    }

    @Then("Select for item details {string}")
    public void selectForItemDetails(String partner) {
        itemDetailsKeyword.selectItemDetails(partner, "");
    }
    @Then("Select for second item details {string}")
    public void selectForSecondItemDetails(String partner) {
        itemDetailsKeyword.selectItemDetails(partner, "second_");
    }

    @And("Add to cart")
    public void addToCart() {
        CreateOrderKeyword.AddToCartButton();
    }

    @Then("validate minicart item {string}")
    public void validateMinicartItem(String partner) {
        Integer get_popOverItemCount =  cartKeyword.Check_popupcart_ifAvailable();

        if(get_popOverItemCount > 0){
            log.info("POPOVER MINICART FOUND");
            cartKeyword.validateIfMiniCartIsVisible();
            cartKeyword.check_popover_cartItem("");
            cartKeyword.validate_popup_cart_itemDetails(partner, "");
            //POPOVER MINICART PRICE VALIDATION [HOLD]
//            cartKeyword.popover_minicart_price_validation();
        }else{
           log.info("No Popover minicart found, validation skipped");
        }
    }
    @Then("validate minicart second item {string}")
    public void validateMinicartSecondItem(String partner) {
        Integer get_popOverItemCount =  cartKeyword.Check_popupcart_ifAvailable();
        if(get_popOverItemCount > 0){
            log.info("POPOVER MINICART FOUND");
            cartKeyword.validateIfMiniCartIsVisible();
            cartKeyword.check_popover_cartItem("second_");
            cartKeyword.validate_popup_cart_secondItemDetails(partner, "second_");
            //POPOVER MINICART PRICE VALIDATION [HOLD]
//            cartKeyword.popover_minicart_price_validation();
        }else{
            log.info("NO POPOVER MINICART FOUND, VALIDATION SKIPPED");
        }
    }
    @Then("validate cart page item {string}")
    public void validateCartPageItem(String partner) {
        cartKeyword.minicart();
        cartKeyword.validate_cartPage_ItemDetails(partner, "");
    }
    @Then("validate cart page second item {string}")
    public void validateCartPageSecondItem(String partner) {
        cartKeyword.minicart();
        cartKeyword.validate_cartPage_secondItemDetails(partner, "second_");
    }
    @Then("click checkout")
    public void clickCheckout() {
        homeKeyword.viewCart();
        homeKeyword.clickCheckoutButton();
    }

    @And("Login | Guest | Create customer Account checkout {string} {string}")
    public void loginGuestCreateCustomerAccountCheckout(String paymentType, String country_code) {
        checkOutKeyword.paymentProcess(paymentType,country_code);
        String order_number =  checkOutKeyword.get_orderNumber();
        apiRequestKeyword.validateOrderNumberInLL(order_number);
//        validateOrdernumberIn_lingbleLink();
    }

//    public void validateOrdernumberIn_lingbleLink(){
//        String order_number =  checkOutKeyword.get_orderNumber();
//        apiRequestKeyword.validateOrderNumberInLL(order_number);
//    }

    @Then("Open Lingble Link login page")
    public void openLingbleLinkLoginPage() {
        lingbleLinkKeyword.openLingbleLinkPage();
        lingbleLinkKeyword.enterLLEmail();
        lingbleLinkKeyword.enterLLPassword();
        lingbleLinkKeyword.proceedToLoginLL();
        lingbleLinkKeyword.waitForLLPageToBeReady();
    }

    //-----> For removing items from cart
    @Then("Remove item from cart")
    public void removeItemFromCart(){
        cartKeyword.removeItemFromCart();
    }
    @Then("Remove second item from cart")
    public void removeSecondItemFromCart(){
        cartKeyword.removeSecondItemFromCart("second_");
    }
    @Then("Remove item from minicart")
    public void removeItemFromMinicart(){
        cartKeyword.removeItemFromMiniCart();
    }

    //-----> For Editing products in cart
    @And("Edit products in cart {string}")
    public void editCart(String partner){
        cartKeyword.editProductInCart(partner);
    }
    @Then("Edit products in cart page magento{string}")
    public void editProductsInCartPageMagento(String partner) {
        cartKeyword.editProductInCart_magento(partner);
    }

    //----> For navigating through the site
    @And("Validate {string} Social Media Links")
    public void validateSocialMediaLinks(String partner) {
        pageNavigationKeyword.validateSocialMediaLinks(partner);
    }
    @And("Validate product prices and images")
    public void validateProductPricesAndImages() {
        pageNavigationKeyword.validatePriceAndImages();
    }
    @And("Validate product image from product details")
    public void validateProductImageFromProductDetails() {
        pageNavigationKeyword.validateImageInProductDetails();
    }
    @And("Navigate through item details {string}")
    public void navigateThroughItemDetails(String partner) {
        pageNavigationKeyword.navigateToItemDetails(partner);
    }
    @And("Select an item from the product list")
    public void selectAnItemFromTheProductList() {
        pageNavigationKeyword.selectProduct();
    }
    @And("Sort Products")
    public void sortProducts() {
        pageNavigationKeyword.productSorting();
    }
    @And("Filter Products {string}")
    public void filterProducts(String partner) {
        pageNavigationKeyword.applyFilter(partner);
    }
    @And("Add to cart for makuake")
    public void addToCartForMakuake(){
        CreateOrderKeyword.AddToCartMakuake();
    }

    @Then("Validate DNS Certificate validity")
    public void validateDNSCertificateValidity() {
        String[] envToCheck = {"prod","dev"};
        for (String env: envToCheck){
            SLLChecker.validateDNSExpiration(env);
        }

    }

    @And("Remove item from cart - Magento")
    public void removeItemFromCartMagento() {
        cartKeyword.removeItemFromCart_Magento();
    }

    @And("Allow cookies")
    public void allowCookies() {
        homeKeyword.allowCookies();
    }

    @And("Close newsletter form - Magento")
    public void closeNewsletterFormMagento() {
        homeKeyword.closeNewsLetter_magento();
    }

    @And("Validate product images and price for each categories.")
    public void validateProductImagesAndPriceForCategories(){
        pageNavigationKeyword.ValidatePriceImgEachCategory();
    }
}


