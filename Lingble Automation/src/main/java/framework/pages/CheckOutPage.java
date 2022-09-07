package framework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePageObject<CheckOutPage>{
    protected CheckOutPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    protected By count_loc_addNewButton = By.xpath("//a[contains(@class,'btn-add-new') and normalize-space()='Add New']");
    protected By loc_addNewButton = By.xpath("//a[contains(@class,'btn-add-new') and normalize-space()='Add New'] | //a[normalize-space()='Add New']");
    protected By cartCheckoutButton = By.xpath("//a[normalize-space(@class)='btn btn-primary btn-block checkout-btn'] | //div[contains(@class,'totals')]//a[normalize-space(@class)='btn btn-primary btn-block checkout-btn']");
    protected By loc_guestCheckoutButton = By.xpath("//a[@class='btn btn-block btn-primary checkout-as-guest']");
    protected By loc_loginEmail = By.xpath("//input[@name='loginEmail'] | //label[@for='login-form-email']//following-sibling::input[@name='loginEmail']");
    protected By loc_loginPassword = By.xpath("//input[@name='loginPassword']");
    protected By loc_loginButton = By.xpath("//button[contains(text(), 'Login')] | //button[. = 'Login']");
    protected By loc_shippingLabel = By.xpath("//h2[contains(text(),'Shipping')]");
    protected By loc_firstName = By.id("shippingFirstNamedefault");
    protected By loc_lastName = By.id("shippingLastNamedefault");
    protected By loc_shippingAddress = By.id("shippingAddressOnedefault");
    public By setCountry(String country) { return By.xpath("//li[contains(text(),'"+country+"')]"); }
    //    protected By countryDropDown = By.id("select2-shippingCountrydefault-container");
    protected By loc_countryDropDown = By.xpath("//select[@name='dwfrm_shipping_shippingAddress_addressFields_country'] | //select[@id='shippingCountrydefault']");
    protected By loc_stateDropDown = By.id("shippingStateSelect");
    public By loc_setState(String state) { return By.xpath("//select[@id='shippingStateSelect']//option[@id='"+state+"']"); }
    protected By loc_shippingCity = By.id("shippingAddressCitydefault");
    protected By loc_shippingZipcode = By.id("shippingZipCodedefault");
    protected By loc_phoneNumber = By.id("shippingPhoneNumberdefault");
    protected By loc_nextPaymentButton = By.xpath("//button[@value='submit-shipping']");
    protected By loc_payment_method_text = By.xpath("//label[normalize-space()='Payment Method']");
    protected By loc_billingEmail = By.xpath("//input[@id='email' and @name='dwfrm_billing_contactInfoFields_email'] | //input[@name='dwfrm_billing_contactInfoFields_email']");
//    public By loc_paymentRadioButton(String paymentMethod){
//        return By.xpath("//label[contains(text(),'"+paymentMethod+"')]//preceding-sibling::input");
//    }
    public By loc_paymentRadioButton(String paymentMethod){
        return By.xpath("//li[@class='paymentMethod']//input[@value='"+paymentMethod+"']");
    }
    protected By loc_amexDinnersDiscover_radio_button = By.xpath("//input[@value='stripe_card']");
    protected By loc_visaMaster_radio_button = By.xpath("//input[@value='paypal_card']");
    protected By loc_jcb_radio_button = By.xpath("//input[@value='scheme']");

    //    public By loc_paymentRadioButton(String paymentMethod){
//        return By.xpath("//img[contains(@src,'"+paymentMethod+"')]");
//    }
    protected By loc_ccIframeElement = By.xpath("//iframe[@title='Iframe for secured card data input field']");
    protected By loc_selectedCountry = By.xpath("//span[@class='country-name']");
    protected By ageCheck = By.xpath("(//input[@id='age-check'])[1]");


    //CREDIT CARD SET DETAILS FOR JCB (IFRAME & LOCATORS)
        //IFRAME'S
        protected By loc_JCB_iframe_creditCardtextbox = By.xpath("//span[normalize-space()='Card number']//following-sibling::span//iframe[@title='Iframe for secured card data input field']");
        protected By loc_JCB_iframe_expiryDate = By.xpath("//span[normalize-space()='Expiry date']//following-sibling::span//iframe[@title='Iframe for secured card data input field']");
        protected By loc_JCB_iframe_CVC_CVV = By.xpath("//span[normalize-space()='CVC / CVV']//following-sibling::span//iframe[@title='Iframe for secured card data input field']");

        //TEXTBOX LOCATORS
        protected By loc_JCB_CreditCardNumber = By.xpath("//input[@id='encryptedCardNumber'] | //input[@aria-label='Card number']");
        protected By loc_JCB_CreditExpiryDate = By.xpath("//input[@id='encryptedExpiryDate'] | //input[@aria-label='Expiry date']");
        protected By loc_JCB_CreditCVC_CVV = By.xpath("//input[@id='encryptedSecurityCode'] | //input[@aria-label='Security code']");


    //CREDIT CARD SET DETAILS FOR AMEX/DINERS/DISCOVER (IFRAME & LOCATORS)
        //IFRAME'S
        protected By loc_AMEX_iframe_creditWithZipcode = By.xpath("//iframe[@title='Secure card payment input frame']");

        //TEXTBOX LOCATORS
        protected By loc_AMEX_CreditCardNumber = By.xpath("//input[@name='cardnumber']");
        protected By loc_AMEX_CreditExpiryDate = By.xpath("//input[@name='exp-date']");
        protected By loc_AMEX_CreditCVC_CVV = By.xpath("//input[@name='cvc']");
        protected By loc_AMEX_CreditZipcode = By.xpath("//input[@name='postal']");

    //CREDIT CARD SET DETAILS FOR VISA/MASTER (IFRAME & LOCATORS)
        //IFRAME'S
        protected By loc_VISA_MASTER_iframe_creditCardtextbox = By.xpath("//div[@id='card-number']//iframe[@id='braintree-hosted-field-number']");
        protected By loc_VISA_MASTER_iframe_expiryDate = By.xpath("//div[@id='expiration-date']//iframe[@id='braintree-hosted-field-expirationDate']");
        protected By loc_VISA_MASTER_iframe_CVC_CVV = By.xpath("//div[@id='cvv']//iframe[@id='braintree-hosted-field-cvv']");

        //TEXTBOX LOCATORS
        protected By loc_VISA_MASTER_CreditCardNumber = By.xpath("//input[@id='credit-card-number'] | //input[@name='Card number']");
        protected By loc_VISA_MASTER_CreditExpiryDate = By.xpath("//input[@id='expiration'] | //input[@name='expiration']");
        protected By loc_VISA_MASTER_CreditCVC_CVV = By.xpath("//input[@id='cvv'] | //input[@name='cvv']");


    protected By loc_nextPlaceOrder = By.xpath("//button[normalize-space()='Next: Place Order'] | //button[@value='submit-payment']");
    protected By placeOrderButton = By.xpath("//button[contains(@class,'place-order')]");

    //----------> Order Summary Validation (Price Validation)
    public By orderSummaryItemImg(String productName){
        return By.xpath("//div[@class='item-image']//img[@class='product-image'] " +
                "| //div[@class='card order-product-summary']//img[contains(@title, \""+productName+"\")] " +
                "| //div[@class='card order-product-summary']//img[starts-with(@title, \""+productName+"\")]");}
    protected By orderSummarySalesTax = By.xpath("//span[normalize-space()='Sales Tax']//ancestor::div//following-sibling::div//span[@class='tax-total'] " +
            "| //span[@class='tax-total']");
    protected By orderSummarySubtotal = By.xpath("//span[normalize-space()='Subtotal']//ancestor::div//following-sibling::div//span[@class='sub-total'] " +
            "| //span[@class='sub-total']");
    protected By orderSummaryShippingCost = By.xpath("//span[normalize-space()='Shipping']//ancestor::div//following-sibling::div//span[@class='shipping-total-cost'] " +
            "| //span[@class='shipping-total-cost']");
    protected By orderSummaryDiscountTotal = By.xpath("//span[@class='order-discount-total']");
    protected By orderSummaryTotalCost = By.xpath("//span[@class='grand-total-sum'] " +
            "| //span[normalize-space()='Total']//ancestor::div//following-sibling::div//span[@class='grand-total-sum']");

    //-----> Shipping Landing Page
    protected By shipping_fname = By.xpath("//span[@class='firstName']");
    protected By shipping_lname = By.xpath("//span[@class='lastName']");
    protected By shipping_Address = By.xpath("//div[@class='address1']");
    protected By shipping_City = By.xpath("//span[@class='city']");
    protected By shipping_PostCode = By.xpath("//span[@class='postalCode']");
    protected By shipping_Country = By.xpath("//div[@class='country']");
    protected By shipping_PhoneNumber = By.xpath("//div[@class='phone']");



    /***/
    protected By paypalRadioButton = By.xpath("//input[@id='rb_paypal']");
    protected By paypalRadioButtonLabel = By.xpath("//label[@id='lb_paypal']");
    protected By paypalIframe = By.xpath("//iframe[@title='PayPal']");
    protected By paypalButton = By.xpath("//div[@role='link' and @aria-label='PayPal']");
    protected By paypalEmailTextField = By.xpath("//input[@name='login_email']");
    protected By paypalPasswordTextField = By.xpath("//input[@name='login_password']");
    protected By paypalNextButton = By.xpath("//button[@id='btnNext']");
    protected By paypalSubmitButton = By.xpath("//button[@id='btnLogin']");
    protected By paypalPaymentAccountRadioButton = By.xpath("//span[contains(text(),'CREDIT UNION 1')]//ancestor::label/span");
    protected By paypalPayNowButton = By.xpath("//button[@id='payment-submit-btn']");
    protected By orderSummaryTitle = By.xpath("//h2[contains(text(),'Order Summary')]");
    /***/



    //-----> Order Receipt Page
    protected By OR_ThankYouText = By.xpath("//h2[. = 'Thank you for your order.'] | " +
            "//h2[normalize-space()= 'Thank you!'] | " +
            "//h2[normalize-space()= 'Thank you for your order.']");
    protected By OR_OrderNumber = By.xpath("//span[@class='summary-section-label order-number-label']//following-sibling::span");
}
