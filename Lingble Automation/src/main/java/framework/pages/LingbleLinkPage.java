package framework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author alexander.v.pangilinan
 * */

public class LingbleLinkPage extends BasePageObject<LingbleLinkPage>{

    protected LingbleLinkPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    protected By getOrderNumber = By.xpath("//h5[contains(@class,'gutterBottom')]");
    protected By LLPasswordLogInField = By.xpath("//input[@id='password']");
    protected By LLEmailLogInField = By.xpath("//input[@id='email']");
    protected By lingbleLinkLogo = By.xpath("//span[contains(text(),'lingble')]");
    protected By lingbleLogInButton = By.xpath("//span[text()='Login']");
    protected By orderMenuButton = By.xpath("//a[@href='#/order']");
    protected By orderlistLabel= By.xpath("//span[contains(text(), 'Order List')]");
    protected By kebabMenu = By.xpath("//div[@class='MuiToolbar-root MuiToolbar-dense']//child::div[1]//button");
    protected By partnerSiteList = By.xpath("//div[@class='MuiPaper-root MuiMenu-paper MuiPopover-paper MuiPaper-elevation8 MuiPaper-rounded']");
    public By setPartnerSite(String value){
        By element = By.xpath("//li[contains(text(),'"+value+"')]");
        return element;
    }
    public By setPartnerSiteLabel(String value){
        By element = By.xpath("//span[normalize-space()='"+value+"']");
        return element;
    }

    //For Validating inventory sources
    protected By inventorySource = By.xpath("//td[4]/div/div/b[contains(text(), 'No Location Assigned')]");

    //For Validating Payment Status
    protected By notPaidStatus = By.xpath("//b[text()='NotPaid']");
    protected By countSwansMetaphore = By.xpath("//span[text()='Swans'] | //span[text()='Metaphore']");
    protected By meatballsMenu = By.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-textSizeSmall MuiButton-sizeSmall']");
    protected By markAsPaidOption = By.xpath("//a[@class='MuiTypography-root MuiLink-root MuiLink-underlineHover MuiTypography-colorPrimary']");
    protected By setAsPaid = By.xpath("//div[@id='form-dialog-title']");
    protected By yesButton = By.xpath("//span[contains(text(),'Yes')]");
    protected By paidStatus = By.xpath("//b[text()='Paid']");

    //For Order List Filter
    protected By filterButton = By.xpath("//button[@label='Filters']");
    protected By filterWindow = By.xpath("//div[@class='MuiPaper-root MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthMd MuiPaper-elevation24 MuiPaper-rounded']");
    protected By filtertype = By.xpath("//div[@id='additional_filters_option_select']");
    protected By customerIdFilter = By.xpath("//input[@id='first_name last_name']");
    protected By paymentStatusFilter = By.xpath("//li[@data-value='payment_status']");
    protected By additionalFilterDropdown = By.xpath("//input[@placeholder='select one or more']");
    protected By paidFilterOption = By.xpath("//span[text()='Paid']//ancestor::li");
    protected By addFilter = By.xpath("//span[text()='Add']");
    protected By applyFilter = By.xpath("//button[@label='Apply Filter' or @label='Apply Filters']");
    protected By customerIDAlexander = By.xpath("//tr[1]//td[3]//child::span[contains(text(),'alexander') or contains(text(),'Alexander')]");
    protected By orderNumberInList = By.xpath("//tr[1]//span//child::a");
    protected By orderNumberInput = By.xpath("//input[@id='order_number']");

    //For Fulfillment Flow
    protected By fulfillmentTab = By.xpath("//span[@class='MuiTab-wrapper' and text()='Fulfillment'] " +
            "| //span[contains(text(),'Fulfillment')]//parent::button");
    protected By checkbox = By.xpath("(//span[contains(text(),'Product')]//preceding::input[@aria-label='select all']//parent::span[@class='MuiIconButton-label'])[1]");
    protected By bulkActions = By.xpath("//span[contains(text(),'Bulk Actions')]");
    protected By pickedOption = By.xpath("//button[contains(text(),'Pick')]");
    protected By pickedStatus = By.xpath("//span[contains(text(),'Picked')]");
    protected By prepareDocsOption = By.xpath("//button[contains(text(),'Prepare Docs')]");
    protected By prepareDocsStatus = By.xpath("//span[contains(text(),'Docs Prepared')]");
    protected By printAndPackOption = By.xpath("//button[contains(text(),'Print & Pack')]");
    protected By printAndPackWindow = By.xpath("//h6[contains(text(),'Print Documents')]");
    protected By selectAllOption = By.xpath("//h6[contains(text(),'Select All')]");
    protected By packingSlipOption = By.xpath("//h6[contains(text(),'Packing Slip')]");
    protected By commercialInvoiceOption = By.xpath("//h6[contains(text(),'Commercial Invoice')]");
    protected By shippingLabelOption = By.xpath("//h6[contains(text(),'Shipping Label')]");
    protected By printButton = By.xpath("//span[contains(text(),'Print')]");
    protected By closePrintWindow = By.xpath("//button[@aria-label='close']");
    protected By setAsPrintedOption = By.xpath("//button[contains(text(),'Set as Printed')]");
    protected By docsPrintedStatus = By.xpath("//span[contains(text(),'Docs Printed')]");
    protected By shipOption = By.xpath("//button[@id='shipped']");
    protected By shippedStatus = By.xpath("//span[contains(text(),'Shipped')]");
    protected By orderStatus = By.xpath("//b[text()='Completed']");
    protected By fulfillmentStatus = By.xpath("//b[text()='Shipped']");

    protected By somethingWentWrong = By.xpath("//");



}
