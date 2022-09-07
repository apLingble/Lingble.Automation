package framework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author alexander.v.pangilinan
 * */

public class ItemDetailsPage extends BasePageObject<ItemDetailsPage> {
    public ItemDetailsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //LOCATORS FOR SELECTING ITEM DETAILS

    //COLOR
    public By loc_productColor(String ProductColor){
        return By.xpath("//button[@aria-label='Select Color "+ProductColor+"'] |" +
                "//button[starts-with(@class,'color-attribute') and @data-label='"+ProductColor+"'] |" +
                "//button[@data-label='"+ProductColor+"'] |" +
                "//button[starts-with(@class,'color-attribute') and @aria-label='Select Color "+ProductColor+"']");}
    public By loc_productColor_dropdown(String ProductColor){
        return By.xpath("//option[@data-attr-value='"+ProductColor+"']");}
    protected By loc_ColorDropdown = By.xpath("//select[contains(@class,'select-color') and contains(@id,'color')]");
    public By loc_tanukiProductSize(String ProductSize){
        return By.xpath("//option[@data-attr-value='"+ProductSize+"']");}

    //SIZE
    public By loc_productSize(String ProductSize){
        return By.xpath("//ul[@id='size-list']//li[normalize-space()='"+ProductSize+"'] |" +
                "//button[@title='Select "+ProductSize+"']");}
    protected By loc_sizeDropdown = By.xpath("//select[@id='size-1'] | //select[@class='custom-select form-control select-size']");

    //QUANTITY
    protected By loc_productQuantity = By.xpath("//select [@id='quantity-1'] | //*[@id='quantity-1']");
    protected By loc_productQuantity2 = By.xpath("//input [@class='quantity-select']");
    protected By loc_qtyPlusIcon = By.xpath("//button[starts-with(@class,'qty-btn increase')] | //span[starts-with(@class,'icon-plus')]");
    protected By loc_inputQuantity = By.xpath("//input[@class='quantity-select']");

    //SPECIFIC SELECTING ITEM DETAILS LOCATOR FOR KOKUYO
    public By loc_kokuyoType(String ProductType){
        return By.xpath("//button[@aria-label='Select Type "+ProductType+"'] |" +
                "//button[@title='"+ProductType+"']");}

    //SPECIFIC SELECTING ITEM DETAILS LOCATOR FOR ACE
    protected By loc_aceProductColor (String productSize) {
        return By.xpath("(//button[starts-with(@class,'btn btn-link') and @aria-label='Select Color "+productSize+"'])[1]");
    }

    public By loc_aceProductSize (String productSize){
        return By.xpath("(//button[@data-original-title='"+productSize+"'])[2] |" +
                "//button[@data-original-title='"+productSize+"']");
    }

    //SPECIFIC SELECTING ITEM DETAILS LOCATOR FOR METAPHORE
    public By loc_metaItemSize (String ProductSize){
        return By.xpath("//span[@id='"+ProductSize+"']");
    }

    //SPECIFIC SELECTING ITEM DETAILS LOCATOR FOR TALEX
    public By loc_productLensesColor(String LensesColor){
        return By.xpath("//button[@aria-describedby='"+LensesColor+"']");}
    public By loc_productFrameColor(String FrameColor){
        return By.xpath("//button[@aria-describedby='"+FrameColor+"']");}

}