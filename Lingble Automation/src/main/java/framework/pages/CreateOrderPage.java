package framework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author alexander.v.pangilinan
 * */
public class CreateOrderPage extends BasePageObject<CreateOrderPage>{
    protected CreateOrderPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //LOCATORS LIST FOR SEARCH PROCESS (CLICK SUGGESTION ON SEARCH ENGINE)
    protected By loc_countSearhIcon = By.xpath("(//div[contains(@class,'search')])[1]");
    protected By loc_searchIcon = By.xpath("(//div[starts-with(@class,'site-search')])[1] | (//img[contains(@alt,'Search')])[2]");
    protected By loc_searchEngine = By.xpath("//form//input[@name='q' and @type='text']");
    protected By loc_searchProductName = By.xpath("//input[@class='form-control search-field' and @name='q'] | //input[@name='q']");

    public By loc_suggestionOption1(String productName){
        return By.xpath("(//div[@class='suggestions container']//parent::a[@class='product']//img[starts-with(@alt,\""+productName+"\")])[1]");
    }
    public By loc_suggestionOption2(String productName){
        return By.xpath("(//mark[text()=\""+productName+"\"]//parent::a[@class='name stretched-link'])[1]");
    }
    public By loc_suggestionOption3(String productName){
        return By.xpath("//img[starts-with(@alt,\""+productName+"\")]");
    }
    public By loc_suggestionOption4(String productName){
        return By.xpath("//img[contains(@alt,\""+productName+"\")]");
    }

    //LOCATORS LIST FOR SEARCH PROCESS (ENTER ON SEARCH ENGINE)
    protected By loc_closeNoticeDismiss = By.xpath("//button[@aria-label='Close'] | //span[@class='licon licon-x'] | //i[@class='meigee-close']");
    public By loc_productLink (String productName){
        return By.xpath("//a[normalize-space()=\""+productName+"\"] |" +
                "//a[contains(text(),\""+productName+"\")] |" +
                "//a[contains(text(),\"'+productName+'\")]");
    }
    public By loc_selectedProduct (String productName){
        return By.xpath("//span[contains(text(),'"+productName+"')] |" +
                "//h1[contains(text(),'"+productName+"')] |" +
                "//h1[@class='product-name' and text()='"+productName+"']//ancestor::div[@class='row hidden-sm-down d-flex align-items-center pdp-title-desktop'] |" +
                "//h1[contains(text(),'"+productName+"')]");
    }
    protected By loc_searchIconMagento = By.xpath("//input[@id='search_470'] |" +
            "//div[@class='search-button']//following-sibling::i[@class='ionicons-search'] |" +
            "//i[starts-with(@class,'fa fa-search')] |" +
            "//div[@class='search']");

    //LOCATORS LIST FOR ADDING ITEM/PRODUCT TO CART
    protected By loc_AddToCartButton = By.xpath("(//input[@class='add-to-cart-url']//following-sibling::button)[1] | //button[@class='add-to-cart btn btn-primary btn-block'] | //button[normalize-space()='Add to Cart']");

    protected By imgCarousel = By.xpath("//div[@id='pdpCarousel-main']");
    protected By closeImg = By.xpath("//div[@id='pdpCarousel-main']//descendant::span");
    protected By preOrderButton = By.xpath("//div[@class='preorder-wrapper']/button[@class='btn btn-block btn-preorder' and text()='Pre-Order']");
    protected By optionButton = By.xpath("//h3[@class='product-name' and contains(text(), 'Option A')]//following::button[contains(text(),'Pre-order')][1]");
}
