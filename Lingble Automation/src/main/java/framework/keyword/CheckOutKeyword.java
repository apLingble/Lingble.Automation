package framework.keyword;

import framework.commonFunctions.ConfigurationKeyEnum;
import framework.pages.CheckOutPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CheckOutKeyword extends CheckOutPage {

    public CheckOutKeyword(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final DecimalFormat df = new DecimalFormat("0.00");

    public void paymentProcess(@Optional String paymentType, String country_code){
        log.info("PERFORMS THE PAYMENT PROCESS.");
        String checkoutType = System.getProperty(country_code+"_checkoutType");

        waitUntilPageready();
        if (checkoutType.equals("guest")){
            log.info("CHECKOUT TYPE: GUEST");
            waitForWebElementToBeClickAble(loc_guestCheckoutButton, 5);
            click(loc_guestCheckoutButton);
            checkoutShippingDetails(country_code);
            validateOrderSummary();
            checkoutBillingDetails(paymentType,country_code);
            //validateShippingLandingPage(country_code);
            validateOrderReceipt();
        } else if (checkoutType.equals("returning")) {
            log.info("CHECKOUT TYPE: RETURNING");
            loginAccount(country_code);
            next_payment_button();
            validateOrderSummary();
            checkoutBillingDetails(paymentType,country_code);
            validateOrderReceipt();
        }
    }
    public void loginAccount(String country_code){
        enterLoginAccount(country_code);
    }
    public void checkoutShippingDetails(String countryCode) {
        enterShippingDetails(countryCode);
    }
    public void checkoutBillingDetails(String paymentType, String countryCode) {
        waitUntilPageready();
        String userBillingEmail = System.getProperty("userBillingEmail");
        log.info(countryCode+"_paymentMethod");
        log.info(countryCode);
        String paymentMethod = paymentType.length()!=0 || paymentType!=null?mapPayType(paymentType):mapPayType(System.getProperty(countryCode+"_paymentMethod"));
        String creditCardNumber = System.getProperty(countryCode+"_creditCardNumber");

        waitForWebElementToBeClickAble(loc_billingEmail, 25);
        clear(loc_billingEmail);
        type(userBillingEmail, loc_billingEmail);
        waitForPresenceOfText(loc_billingEmail, userBillingEmail, 5);
        log.info(userBillingEmail);
        log.info(paymentMethod);
        paymentMethod(paymentMethod,countryCode);
    }
    public String mapPayType(String paymentType){
        log.info("MAP_PAY_TYPE");
        Map<String, String> paymentMap = new HashMap<>();
        paymentMap.put("amex/diners/discover","AMEX/DINERS/DISCOVER");
        paymentMap.put("visa/master","VISA/MASTER");
        paymentMap.put("jcb","JCB");
        paymentMap.put("paypal","PayPal");
        return paymentMap.get(paymentType.toLowerCase());
    }
    public void paymentMethod(String paymentType,String countryCode){
        log.info("PAYMEMT_METHOD");
        Map<String, Runnable> paymentMap = new HashMap<>();
        paymentMap.put("amex/diners/discover",()->creditCard(paymentType,countryCode));
        paymentMap.put("visa/master",()->creditCard(paymentType,countryCode));
        paymentMap.put("jcb",()->creditCard(paymentType,countryCode));
        paymentMap.put("paypal",()->paypal());
        paymentMap.get(paymentType.toLowerCase()).run();
    }
    private void creditCard(String paymentMethod,String countryCode){
        waitUntilPageready();
        String creditCard_number = System.getProperty(countryCode+"_creditCardNumber");
        String visa_master_creditCard_number = System.getProperty(countryCode+"_creditCardNumber2");
        String creditCard_CVC_CVV = System.getProperty(countryCode+"_CVC");
        String creditCard_zipcode = System.getProperty("creditCardZipCode");
        String creditCard_expiryDate = System.getProperty("creditCardExpiryDate");

        log.info("Selected payment method: CREDIT CARD");
        switch (paymentMethod){
            case "AMEX/DINERS/DISCOVER":
                waitForWebElementToBeClickAble(loc_amexDinnersDiscover_radio_button, 25);
                scrollToElement(loc_amexDinnersDiscover_radio_button);
                waitForWebElementToBeClickAble(loc_amexDinnersDiscover_radio_button, 5);
                clickElementJS(loc_amexDinnersDiscover_radio_button);
                break;
            case "VISA/MASTER":
                waitForWebElementToBeClickAble(loc_visaMaster_radio_button, 25);
                scrollToElement(loc_visaMaster_radio_button);
                waitForWebElementToBeClickAble(loc_visaMaster_radio_button, 5);
                clickElementJS(loc_visaMaster_radio_button);
                break;
            case "JCB":
                waitForWebElementToBeClickAble(loc_jcb_radio_button, 25);
                scrollToElement(loc_jcb_radio_button);
                waitForWebElementToBeClickAble(loc_jcb_radio_button, 5);
                clickElementJS(loc_jcb_radio_button);
                break;
        }
        switch (paymentMethod) {
            case "AMEX/DINERS/DISCOVER":
                log.info(paymentMethod);
                log.info("Stripe Payment");
                log.info("AMEX/DINERS/DISCOVER");

                log.info("CC card");
                switchToIframe(loc_AMEX_iframe_creditWithZipcode);
                waitForWebElementToBeClickAble(loc_AMEX_CreditCardNumber, 3);
                type(creditCard_number, loc_AMEX_CreditCardNumber);
                log.info("CC ex. date");
                waitForWebElementToBeClickAble(loc_AMEX_CreditExpiryDate, 3);
                type(creditCard_expiryDate, loc_AMEX_CreditExpiryDate);

                log.info("CC cvc");
                waitForWebElementToBeClickAble(loc_AMEX_CreditCVC_CVV, 3);
                type(creditCard_CVC_CVV, loc_AMEX_CreditCVC_CVV);
                log.info("CC zipcode");
                waitForWebElementToBeClickAble(loc_AMEX_CreditZipcode, 3);
                type(creditCard_zipcode, loc_AMEX_CreditZipcode);

                switchToDefaultFrame();
                sleep(Duration.ofSeconds(2));
                break;
            case "VISA/MASTER":
                log.info("ADYEN PAYMENT | VISA/MASTER");
                switchToIframe(loc_VISA_MASTER_iframe_creditCardtextbox);
                waitForWebElementToBeClickAble(loc_VISA_MASTER_CreditCardNumber, 3);
                type(visa_master_creditCard_number, loc_VISA_MASTER_CreditCardNumber);
                switchToDefaultFrame();

                switchToIframe(loc_VISA_MASTER_iframe_expiryDate);
                waitForWebElementToBeClickAble(loc_VISA_MASTER_CreditExpiryDate, 3);
                type(System.getProperty("creditCardExpiryDate"), loc_VISA_MASTER_CreditExpiryDate);
                switchToDefaultFrame();

                switchToIframe(loc_VISA_MASTER_iframe_CVC_CVV);
                waitForWebElementToBeClickAble(loc_VISA_MASTER_CreditCVC_CVV, 3);
                type(System.getProperty("CVC_CVV"), loc_VISA_MASTER_CreditCVC_CVV);
                switchToDefaultFrame();
                break;
            case "JCB":
                log.info("ADYEN PAYMENT | JCB");
                switchToIframe(loc_JCB_iframe_creditCardtextbox);
                waitForWebElementToBeClickAble(loc_JCB_CreditCardNumber, 3);
                type(creditCard_number, loc_JCB_CreditCardNumber);
                switchToDefaultFrame();

                switchToIframe(loc_JCB_iframe_expiryDate);
                waitForWebElementToBeClickAble(loc_JCB_CreditExpiryDate, 3);
                type(System.getProperty("creditCardExpiryDate"), loc_JCB_CreditExpiryDate);
                switchToDefaultFrame();

                switchToIframe(loc_JCB_iframe_CVC_CVV);
                waitForWebElementToBeClickAble(loc_JCB_CreditCVC_CVV, 3);
                type(System.getProperty("CVC_CVV"), loc_JCB_CreditCVC_CVV);
                switchToDefaultFrame();
                break;
            default:
                break;
        }
        next_placeOrder();
        place_orderButton();
    }

    private void paypal(){
        waitUntilPageready();
        waitForWebElementToBeClickAble(paypalRadioButton, 25);
        scrollToElement(paypalRadioButton);
        selectRadioButton(paypalRadioButton);
        waitForElementToBeVisible(paypalIframe,10);
        switchToIframe(paypalIframe);
        waitForWebElementToBeClickAble(paypalButton);
        click(paypalButton);
        switchTab("Log in to your PayPal account");
        clear(paypalEmailTextField);
        type(System.getProperty("paypalEmail"),paypalEmailTextField);
        if(getElement(paypalNextButton).size()!=0){
            waitForWebElementToBeClickAble(paypalNextButton);
            click(paypalNextButton);
        }
        waitUntilPageready();
        clear(paypalPasswordTextField);
        type(System.getProperty("paypalPassword"),paypalPasswordTextField);
        waitForWebElementToBeClickAble(paypalSubmitButton);
        click(paypalSubmitButton);
        waitUntilPageready();
        waitUntilPageready();
        waitForElementToBeVisible(paypalPaymentAccountRadioButton, 10);
        selectRadioButton(paypalPaymentAccountRadioButton);
        log.info("Union Credit is selected.");
        waitUntilPageready();
        for (int i=0; i<=5; i++) {
            if(waitForLabelToBeVisible(paypalPayNowButton, "Pay Now", 10) == true){
                break;
            }
            sleep(Duration.ofSeconds(2));
            scrollToElement(paypalPayNowButton);
        }
        sleep(Duration.ofSeconds(5));
        waitForWebElementToBeClickAble(paypalPayNowButton);
        clickElementJS(paypalPayNowButton);
        sleep(Duration.ofSeconds(5));
        checkPayPalSuccessfulPayment(60);
        switchToDefaultContent();
        waitUntilPageready();
        assert waitForLabelToBeVisible(orderSummaryTitle,"Order Summary",60);
    }
    public void checkPayPalSuccessfulPayment(int timeCount){
        for(int t=0; t<timeCount; t++) {
            try {
                if (getElement(paypalEmailTextField).size() != 0) {
                    clear(paypalEmailTextField);
                    type("paypal_test_us@lingble.com", paypalEmailTextField);
                    if (getElement(paypalNextButton).size() != 0) {
                        waitForWebElementToBeClickAble(paypalNextButton);
                        click(paypalNextButton);
                    }
                    clear(paypalPasswordTextField);
                    type("z<84iMT+", paypalPasswordTextField);
                    waitForWebElementToBeClickAble(paypalSubmitButton);
                    click(paypalSubmitButton);
                    waitUntilPageready();
                    waitForElementToBeVisible(paypalPaymentAccountRadioButton, 10);
                    selectRadioButton(paypalPaymentAccountRadioButton);
                    scrollToElement(paypalPayNowButton);
                    waitForWebElementToBeClickAble(paypalPayNowButton);
                    click(paypalPayNowButton);
                }
            } catch (Exception e) {
                if(e.toString().contains("NoSuchWindowException")){
                    break;
                }
            }
        }
        log.info("Paypal payment was successful.");
    }

    public void validateOrderReceipt() {
        waitUntilPageready();
        waitForElementToBeVisible(OR_ThankYouText, 30);
        if(elementCount(OR_ThankYouText) == 1){
            log.info("Thank you for your order Heading is VISIBLE");
        }else{
            log.info("THANK YOU HEADING IS NOT FOUND");
        }

        if(elementCount(OR_OrderNumber) == 1){
            log.info("Order Number label is VISIBLE");
        }else{
            log.info("ORDER NUMBER IS NOT FOUND FOUND");
        }
    }

    public String get_orderNumber(){
        return getText(OR_OrderNumber);
    }

    public void enterLoginAccount(String country_code){
        user_email(country_code);
        user_password();
        login_button();
    }
    public void enterShippingDetails(String countryCode){
        add_newButton();

        log.info("ENTER USER SHIPPING DETAILS");
        waitForElementToBeVisible(loc_shippingLabel, 10);
        select_country(countryCode);
        select_state(countryCode);
        shipping_firstName();
        shipping_lastName();
        shipping_address(countryCode);
        shipping_city(countryCode);
        shipping_zipcode(countryCode);
        shipping_phoneNumber(countryCode);

        next_payment_button();
    }

    public void next_payment_button(){
        waitUntilPageready();
        waitForWebElementToBeClickAble(loc_nextPaymentButton, 25);
        if (elementCount(ageCheck) > 0){
            age_check();
        }
        click(loc_nextPaymentButton);
        log.info("Next payment button clicked");
        sleep(Duration.ofSeconds(3));
    }

    public void validateOrderSummary(){
        log.info("VALIDATE ORDER SUMMARY");
        String productName = System.getProperty(ConfigurationKeyEnum.param_productName.value());
        log.info("Validate order summary item image if visible " +productName);
        waitForElementToBeVisible(orderSummaryItemImg(productName), 300);

        //-----> Validate Order Summary Total Price
        log.info("Validate order summary SUBTOTAL if visible");
        waitForElementToBeVisible(orderSummarySubtotal, 300);
        log.info("Validate order summary SHIPPING COST if visible");
        waitForElementToBeVisible(orderSummaryShippingCost, 300);
        log.info("Validate order summary TOTAL COST if visible");
        waitForElementToBeVisible(orderSummaryTotalCost, 300);

        //-----> Evaluate Order Summary Total Price
        log.info("Evaluate order summary total price");
        double subtotal = Double.parseDouble((getText(orderSummarySubtotal).replaceAll("[^0-9.]", "")));
        double shippingCost = Double.parseDouble((getText(orderSummaryShippingCost).replaceAll("[^0-9.]", "")));
        double totalPrice = Double.parseDouble(getText(orderSummaryTotalCost).replaceAll("[^0-9.]", ""));
        int countTax = elementCount(orderSummarySalesTax);
        int countDiscount = elementCount(orderSummaryDiscountTotal);

        log.info("Item Subtotal: "+subtotal);
        log.info("Shipping Cost: "+shippingCost);

        if (countTax > 1){
            int taxPrice = Integer.parseInt(getText(orderSummarySalesTax).replaceAll("[^0-9.]", ""));
            log.info("Tax Price: "+taxPrice);
            double orderSummaryTotalPrice = subtotal+shippingCost+taxPrice;
            if (orderSummaryTotalPrice==totalPrice){
                log.info("Evaluation: "+subtotal+" + "+shippingCost+" + "+taxPrice+" = "+orderSummaryTotalPrice+"");
                log.info("Given total: "+totalPrice+" | Evaluation Result: "+orderSummaryTotalPrice+"");
                log.info("Order summary total price is correct!");
            } else throw new Error("Error! Evaluated total price is incorrect!");
        } else if (countDiscount > 1){
            int discountTotalPrice = Integer.parseInt(getText(orderSummaryDiscountTotal).replaceAll("[^0-9.]", ""));
            log.info("Discount: "+discountTotalPrice);
            double orderSummaryTotalPrice = subtotal+shippingCost*discountTotalPrice;
            if (orderSummaryTotalPrice==totalPrice){
                log.info("Evaluation: "+subtotal+" + "+shippingCost+" * "+discountTotalPrice+" = "+orderSummaryTotalPrice+"");
                log.info("Given total: "+totalPrice+" | Evaluation Result: "+orderSummaryTotalPrice+"");
                log.info("Order summary total price is correct!");
            } else throw new Error("Error! Evaluated total price is incorrect!");
        } else {
            double orderSummaryTotalPrice = Double.parseDouble(df.format(subtotal+shippingCost));
            if (orderSummaryTotalPrice==totalPrice){
                log.info("Evaluation: "+subtotal+" + "+shippingCost+" = "+orderSummaryTotalPrice+"");
                log.info("Given Total Price: "+totalPrice+" | Evaluation Result: "+orderSummaryTotalPrice+"");
                log.info("Order summary total price is correct!");
            } else throw new Error("Error! Evaluated total price is incorrect!");
        }
    }

    private void validateShippingLandingPage(String countryCode){
        String firstname = System.getProperty("firstName");
        String lastname = System.getProperty("lastName");
        String address = System.getProperty(countryCode+"_address1");
        String city = System.getProperty(countryCode+"_city");
        String zipcode = System.getProperty(countryCode+"_zipCode");
        String number = System.getProperty(countryCode+"_phoneNumber");
        String country = System.getProperty(countryCode+"_countryName");

        //get text from element for validation.
        String getFirstname = getText(shipping_fname);
        String getLastname = getText(shipping_lname);
        String getAddress = getText(shipping_Address);
        String getCity = getText(shipping_City);
        String getZipCode = getText(shipping_PostCode);
        String getNumber = getText(shipping_PhoneNumber);
        String getCountry = getText(shipping_Country);

        //Validate order shipping address
        log.info("Validate customer name:");
        if (getFirstname.equals(firstname) && getLastname.equals(lastname)){
            log.info("Is the customer full name "+firstname+" "+lastname+" visible?");
            waitForPresenceOfText(shipping_fname, firstname, 2);
            waitForPresenceOfText(shipping_lname, lastname, 2);
        } else throw new Error("Error! Customer name is incorrect!");

        log.info("Validate shipping address:");
        if (getAddress.equals(address)){
            log.info("Is the shipping address '"+address+"' visible?");
            waitForPresenceOfText(shipping_Address,address, 2);
        } else throw new Error("Error! Shipping address is incorrect!");

        log.info("Validate shipping city:");
        if (getCity.equals(city)){
            log.info("Is the shipping city '"+city+"' visible?");
            waitForPresenceOfText(shipping_City, city, 2);
        } else throw new Error("Error! Shipping city is incorrect!");

        log.info("Validate shipping postal code:");
        if (getZipCode.equals(zipcode)){
            log.info("Is the shipping postal code '"+zipcode+"' visible?");
            waitForPresenceOfText(shipping_PostCode, zipcode, 2);
        } else throw new Error("Error! Shipping postal code is incorrect!");

        log.info("Validate shipping phone number:");
        if (getNumber.equals(number)){
            log.info("Is the shipping phone number '"+number+"' visible?");
            waitForPresenceOfText(shipping_PhoneNumber, number, 2);
        } else throw new Error("Error! Shipping phone number is incorrect!");

        log.info("Validate shipping country:");
        if (getCountry.equals(country)){
            log.info("Is the shipping country '"+country+"' visible?");
            waitForPresenceOfText(shipping_Country, country, 2);
        } else throw new Error("Error! Shipping country is incorrect!");
    }

    //ON CALL METHODS

    //SHIPPING DETAILS
    public void add_newButton(){
        waitUntilPageready();
        By[] add_new_button = new By[]{loc_addNewButton};
        for (By this_list : add_new_button) {
            try {
                waitForWebElementToBeClickAble(this_list, 3);
                click(this_list);
                log.info("Add new button clicked");
                break;
            } catch (Exception e) {
            }
        }
    }
    public void select_country(String countryCode){
        String country = System.getProperty(countryCode+"_country");
        selectValue(loc_countryDropDown, country);
        log.info(country + " selected");
    }
    public void select_state(String countryCode){
        String state = System.getProperty(countryCode+"_state");

        waitForElementToBeVisible(loc_stateDropDown, 30);
        selectValue(loc_stateDropDown, state);
        log.info(state + " selected");
    }
    public void shipping_firstName(){
        String firstname = System.getProperty("firstName");

        clear(loc_firstName);
        type(firstname, loc_firstName);
    }
    public void shipping_lastName(){
        String lastname = System.getProperty("lastName");

        clear(loc_lastName);
        type(lastname, loc_lastName);
    }
    public void shipping_address(String countryCode){
        String address = System.getProperty(countryCode+"_address1");

        clear(loc_shippingAddress);
        type(address, loc_shippingAddress);
    }
    public void shipping_city(String countryCode){
        String city = System.getProperty(countryCode+"_city");

        clear(loc_shippingCity);
        type(city, loc_shippingCity);
    }
    public void shipping_zipcode(String countryCode){
        String zipcode = System.getProperty(countryCode+"_zipCode");

        clear(loc_shippingZipcode);
        type(zipcode, loc_shippingZipcode);
    }
    public void shipping_phoneNumber(String countryCode){
        String number = System.getProperty(countryCode+"_phoneNumber");

        clear(loc_phoneNumber);
        type(number, loc_phoneNumber);
    }
    public void age_check(){
        moveTo(loc_nextPaymentButton);
        waitForWebElementToBeClickAble(ageCheck, 5);
        click(ageCheck);
    }

    //ENTER LOGIN ACCOUNT METHODS
    public void user_email(String countryCode){
        String email = System.getProperty(countryCode+"_userEmail");
        waitForWebElementToBeClickAble(loc_loginEmail, 20);
        clear(loc_loginEmail);
        type(email, loc_loginEmail);
    }
    public void user_password(){
        String password = System.getProperty("userPassword");

        waitForWebElementToBeClickAble(loc_loginPassword, 3);
        clear(loc_loginPassword);
        type(password, loc_loginPassword);
    }
    public void login_button(){
        waitForWebElementToBeClickAble(loc_loginButton);
        click(loc_loginButton);
    }
    public void next_placeOrder(){
        try {
            for (int x = 0; x < 5; x++) {
                waitForWebElementToBeClickAble(loc_nextPlaceOrder, 25);
                moveTo(loc_nextPlaceOrder);
                click(loc_nextPlaceOrder);
                waitForElementToBeInvisible(loc_nextPlaceOrder, 5);
                if (elementCount(loc_nextPlaceOrder) == 0) {
                    log.info("Next place order button cliked.");
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
    public void place_orderButton(){
        waitUntilPageready();
        waitForWebElementToBeClickAble(placeOrderButton, 25);
        moveTo(placeOrderButton);
        click(placeOrderButton);
        log.info("Place order button cliked.");
    }
}