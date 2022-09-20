package framework.keyword;

import framework.commonFunctions.ConfigurationKeyEnum;
import framework.pages.LingbleLinkPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.Objects;

/**
 * @author alexander.v.pangilinan
 */

public class LingbleLinkKeyword extends LingbleLinkPage {

    public LingbleLinkKeyword(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openLingbleLinkPage(){
        log.info("Navigate through LingbleLink URL");
        String url = System.getProperty(ConfigurationKeyEnum.lingbleLinkURL.value());
        log.info("Open " + url + " Environment.");
        getPage(url);
        waitUntilPageready();
    }

    public void waitUntilLingblePageready(){
        log.info("Wait for LingbleLink to load.");
        waitForElementToBeVisible(lingbleLinkLogo, 10);
    }

    public void enterLLEmail(){
        log.info("Enter email.");
        waitForElementToBeVisible(LLEmailLogInField, 20);
        String LLemail = System.getProperty(ConfigurationKeyEnum.lingbleLinkEmail.value());
        type(LLemail, LLEmailLogInField);
    }

    public void enterLLPassword(){
        log.info("Enter Password.");
        waitForElementToBeVisible(LLPasswordLogInField, 20);
        String LLpassword = System.getProperty(ConfigurationKeyEnum.lingbleLinkPassword.value());
        type(LLpassword, LLPasswordLogInField);
    }

    public void proceedToLoginLL(){
        log.info("Click log in button.");
        waitForWebElementToBeClickAble(lingbleLogInButton, 20);
        click(lingbleLogInButton);
    }

    public void waitForLLPageToBeReady() {
        log.info("Wait for LL Page to be ready.");
        waitForElementToBeVisible(lingbleLinkLogo, 50);
    }

    public void selectOrderListMenu(){
        waitForElementToBeVisible(orderMenuButton, 50);
        click(orderMenuButton);
        log.info("Order list menu is selected.");
        waitForElementToBeVisible(orderlistLabel, 50);
    }

    public void selectPartnerSite(){
        String partnersite = System.getProperty(ConfigurationKeyEnum.partnerSite.value());
        log.info("Select partnersite "+partnersite+".");
        waitForElementToBeVisible(kebabMenu, 30);
        click(kebabMenu);
        sleep(Duration.ofSeconds(5));
        moveTo(setPartnerSite(partnersite));
        waitForElementToBeVisible(partnerSiteList, 20);
        click(setPartnerSite(partnersite));
        sleep(Duration.ofSeconds(5));
    }

    public void validateSelectedPartnerSite(){
        String partnersite= System.getProperty(ConfigurationKeyEnum.partnerSite.value());
        waitForElementToBeVisible(setPartnerSiteLabel(partnersite), 20);
        log.info("Selected partner site "+partnersite+" is displayed.");
        sleep(Duration.ofSeconds(3));
    }

    /**    public void checkForSomethingWentWrongMessage(){
     log.info("Check for 'Something went wrong message'");
     int i = elementCount(somethingWentWrong);
     if (i<1){
     log.info("Count Elements: "+i+".");
     }
     }
     **/
    public void setOrderListFilter(String orderNumber){
        log.info("Set filters for order number "+orderNumber);
        clickFilterButton();
        validateFilterWindow();
        typeOrderNumber(orderNumber);
        typeCustomerID();
        selectFilterType();
        setPaymentStatus();
        setAdditionalFilter();
        selectPaidStatus();
        clickAddFilter();
        clickApplyFilter();
    }

    public void clickFirstOrderNumber() {
        waitForWebElementToBeClickAble(orderNumberInList, 20);
        if(elementCount(orderNumberInList)!=1){
            log.info("Order number is not found.");
            throw new RuntimeException("There's no oder number to be selected!");
        }
        else{
            waitForWebElementToBeClickAble(orderNumberInList, 20);
            if(elementCount(customerIDAlexander)>0){
                log.info("Customer ID Alexander is verified.");
                click(orderNumberInList);
                log.info("Order Number is clicked");
                sleep(Duration.ofSeconds(5));
            }
            else {
                log.info("Customer Alexander is not found.");
                throw new RuntimeException("Customer Alexander is not visible!");
            }

        }
    }

    public void validateOrderNumber(String order_number) {
        log.info("Validating order number");
        int count = order_number.length() + 8;
        String selectedOrderNumber = getText(getOrderNumber).substring(8,count);
        log.info("Order number (API): " + order_number + "");
        log.info("Selected order number: " +selectedOrderNumber+ "");
        if (selectedOrderNumber.equals(order_number)){
            log.info("Order number is successfully validated!");
        } else throw new Error("Order number validation error!");
    }

    public void doFulfillmentFlow() {
        if(elementCount(orderStatus)!=1 && elementCount(fulfillmentStatus)!=1){
            log.info("Performs the fulfillment flow");
            goToFulfillmentTab();
            setPickedStatus();
            setPrepareDocsStatus();
            setPrintAndPackStatus();
            setPrintedStatus();
            setShippedOrderStatus();
        }
        else {
            log.info("Order Status is already completed and Fulfillment Status is already fulfilled.");
        }
    }


    //Set Order list filter.
    private void clickFilterButton(){
        waitForElementToBeVisible(filterButton, 20);
        click(filterButton);
        sleep(Duration.ofSeconds(3));
        if (elementCount(filterWindow)!=1){
            log.info("Filter window is not displayed.");
        }
        log.info("Filter button is clicked.");

    }
    private void validateFilterWindow(){
        waitForElementToBeVisible(filterWindow, 5);
        log.info("Filter window is displayed.");
    }
    private void typeOrderNumber(String orderNumber){
        waitForElementToBeVisible(orderNumberInput,10);
        click(orderNumberInput);
        type(orderNumber, orderNumberInput);
        log.info("Order number is typed successfully.");
    }
    private void typeCustomerID(){
        waitForElementToBeVisible(customerIdFilter, 10);
        if (elementCount(customerIdFilter)!=1){
            log.info("Customer ID input is found");
        }
        click(customerIdFilter);
        type("Alexander", customerIdFilter);
        log.info("String successfully typed.");
    }
    private void selectFilterType(){
        waitForElementToBeVisible(filtertype, 10);
        if (elementCount(filtertype)!=1){
            log.info("Filter Type Element is not found");
        }
        click(filtertype);
        sleep(Duration.ofSeconds(5));
        log.info("Filter type button is clicked");
    }
    private void setPaymentStatus(){
        waitForElementToBeVisible(paymentStatusFilter, 10);
        if (elementCount(paymentStatusFilter)!=1){
            log.info("Payment status filter is not found.");
        }
        click(paymentStatusFilter);
        log.info("Payment Status option is clicked.");
    }
    private void setAdditionalFilter(){
        waitForElementToBeVisible(additionalFilterDropdown, 10);
        if (elementCount(additionalFilterDropdown)!=1){
            log.info("Additional Filter Dropdown is not found.");
        }
        click(additionalFilterDropdown);
        log.info("Additional Filter Dropdown is clicked.");
    }
    private void selectPaidStatus(){
        waitForElementToBeVisible(paidFilterOption, 10);
        if (elementCount(paidFilterOption)!=1){
            log.info("Paid filter button is not found.");
        }
        click(paidFilterOption);
        log.info("Paid Filter is selected.");
    }
    private void clickAddFilter(){
        waitForElementToBeVisible(addFilter, 10);
        if (elementCount(addFilter)!=1){
            log.info("Add filter button is not found.");
        }
        click(addFilter);
        log.info("Add Filter is clicked.");
    }
    private void clickApplyFilter(){
        waitForElementToBeVisible(applyFilter, 10);
        if (elementCount(applyFilter)!=1){
            log.info("Apply Filter button is not found.");
        }
        click(applyFilter);
        log.info("Apply Filter button is clicked.");
        sleep(Duration.ofSeconds(5));
    }

    //For Fulfillment Flow
    private void goToFulfillmentTab(){
        waitForWebElementToBeClickAble(fulfillmentTab, 10);
        click(fulfillmentTab);
        waitForWebElementToBeClickAble(checkbox, 10);
        click(checkbox);
        log.info("Fulfillment Tab and checkbox is clicked.");
    }
    private void setPickedStatus() {
        click(bulkActions);
        if (elementCount(pickedOption)!=1){
            log.info("Picked option is not found.");
        }
        click(pickedOption);
        log.info("Picked option is clicked.");
        waitForElementToBeVisible(pickedStatus, 10);
    }
    private void setPrepareDocsStatus() {
        click(bulkActions);
        if (elementCount(prepareDocsOption)!=1){
            log.info("Prepare Docs option is not found.");
        }
        click(prepareDocsOption);
        log.info("Prepare Docs option is clicked.");
        waitForElementToBeVisible(prepareDocsStatus, 10);
    }
    private void setPrintAndPackStatus() {
        click(bulkActions);
        if (elementCount(printAndPackOption)!=1){
            log.info("Print and pack option is not found.");
        }
        click(printAndPackOption);
        log.info("Print and pack option is clicked.");
        waitForElementToBeVisible(printAndPackWindow, 10);
        sleep(Duration.ofSeconds(5));
        waitForWebElementToBeClickAble(selectAllOption);
        waitForWebElementToBeClickAble(packingSlipOption);
        waitForWebElementToBeClickAble(commercialInvoiceOption);
        waitForWebElementToBeClickAble(shippingLabelOption);
        waitForWebElementToBeClickAble(printButton);
        click(closePrintWindow);
        log.info("Print Window is closed.");
    }
    private void setPrintedStatus() {
        if (elementCount(setAsPrintedOption)!=1){
            log.info("Set as printed option is not found.");
        }
        click(setAsPrintedOption);
        log.info("Set as printed option is clicked.");
        waitForElementToBeVisible(docsPrintedStatus, 10);
    }
    private void setShippedOrderStatus() {
        click(bulkActions);
        if (elementCount(shipOption)!=1){
            log.info("Ship option is not found.");
        }
        click(shipOption);
        log.info("Ship option is clicked.");
        waitForElementToBeVisible(shippedStatus, 10);
    }

    //Validate Order and Fulfillment Status
    public void validateOrderStatus(){
        sleep(Duration.ofSeconds(3));
        waitForElementToBeVisible(orderStatus,10);
        if (elementCount(orderStatus)!=1){
            log.info("Order Status not completed!");
            throw new RuntimeException("Order Status should be completed!");
        }
        else
            log.info("Order Status is completed.");
    }
    public void validateFulfillmentStatus(){
        sleep(Duration.ofSeconds(3));
        waitForElementToBeVisible(fulfillmentStatus, 10);
        if (elementCount(fulfillmentStatus)!=1){
            log.info("Fulfillment Status not completed!");
            throw new RuntimeException("Order should be fulfilled!");
        }
        else
            log.info("Fulfillment Status is fulfilled.");
    }

    //For Validating Payment Status
    public void validatePaymentStatus() {
        countNotPaidStatus();
        validatePaidStatus();
    }
    private void validatePaidStatus() {
        sleep(Duration.ofSeconds(5));
        if (elementCount(paidStatus)!=1){
            log.info("Payment Status is not paid!");
            throw new RuntimeException("Payment Status Should be paid!");
        }
        else log.info("Payment Status is paid.");
    }
    private void ifPaymentIsNotPaid() {
        waitForWebElementToBeClickAble(fulfillmentTab, 10);
        click(fulfillmentTab);
        sleep(Duration.ofSeconds(3));
        waitForElementToBeVisible(meatballsMenu, 10);
        click(meatballsMenu);
        waitForWebElementToBeClickAble(markAsPaidOption, 10);
        click(markAsPaidOption);
        waitForElementToBeVisible(setAsPaid, 10);
        waitForWebElementToBeClickAble(yesButton, 10);
        click(yesButton);
    }
    private void countNotPaidStatus() {
        if (elementCount(notPaidStatus)!=0 && elementCount(countSwansMetaphore)!=0){
            log.info("NOT PAID status was found.");
            ifPaymentIsNotPaid();
        }
    }

    //For checking no location assigned in inventory source.
    public void checkNoLocationAssigned(){
        waitForWebElementToBeClickAble(fulfillmentTab, 10);
        click(fulfillmentTab);
        sleep(Duration.ofSeconds(3));
        if(elementCount(inventorySource)!=1){
            log.info("Inventory source has location assigned.");
        }
        else {
            log.info("Inventory source has No Location Assigned.");
            driver.navigate().refresh();
            waitForLLPageToBeReady();
            waitForWebElementToBeClickAble(fulfillmentTab, 10);
            click(fulfillmentTab);
            sleep(Duration.ofSeconds(3));
            if(Objects.equals(getText(inventorySource), "No Location Assigned ")){
                log.info("Inventory source has location assigned.");
            }
            else log.info("Inventory source has No Location Assigned.");
            throw new RuntimeException("Inventory Source should have location assigned!");
        }
    }

}
