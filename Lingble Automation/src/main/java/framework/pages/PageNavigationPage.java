package framework.pages;

import framework.commonFunctions.ConfigurationKeyEnum;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author alexander.v.pangilinan
 */

public class PageNavigationPage extends BasePageObject<PageNavigationPage>{

    public PageNavigationPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //-----> For selecting an item from product list page
    public By productName (String productName){
        return By.xpath("//a[@class='link' and contains(text(),\""+productName+"\")] | //a[contains(text(), \""+productName+"\")] | //h5[contains(text(),\""+productName+"\")]");
    }

    //-----> FOR NAVIGATING THROUGH PRODUCT CATEGORY
    public By product_Category(String ProductCategory){
        return By.xpath("((//li//*[normalize-space()='"+ProductCategory+"']))[1] |" +
                "(//li//*[contains(text(),'"+ProductCategory+"')])[1] |" +
                "(//li[starts-with(@class,'nav-item')]//*[normalize-space()='"+ProductCategory+"'])[1] |" +
                "(//li[starts-with(@class,'nav-item')]//a[contains(text(),'"+ProductCategory+"')])[1] |" +
                "//nav[@id='main-menu']//ancestor::div[@class='main-menu']//descendant::a[contains(text(),'"+ProductCategory+"')]");}
    public By product_Sub_Category(String ProductSubCategory){
        return By.xpath("//ul/li/a[contains(text(),'"+ProductSubCategory+"')] | //li//*[contains(text(),'"+ProductSubCategory+"')]");}
    public By product_Sub_Category2(String ProductSubCategory2){
        return By.xpath("//a[text()='"+ProductSubCategory2+"']");}

    //-----> FOR VALIDATING SOCIAL MEDIA LINKS
    protected By footer = By.xpath("//footer");
    protected By facebookLink = By.xpath("//*[contains(@class,'facebook')] | //a[@aria-label='Facebook'] | //img[contains(@alt, 'Facebook')]");
    public By facebookLink(int index) {
        return By.xpath("(//*[contains(@class,'facebook')])["+index+"] | (//a[@aria-label='Facebook'])["+index+"] | (//img[contains(@alt, 'Facebook')])["+index+"]");
    }
    protected By instagramLink = By.xpath("//i[contains(@class, 'instagram')] | //a[contains(@href,'instagram')] | //a[@aria-label='Insta'] | //img[contains(@alt, 'Instagram')]");
    public By instagramLink(int index){
        return By.xpath("(//i[contains(@class, 'instagram')])["+index+"] " +
                "| (//a[contains(@href,'instagram')])["+index+"] | (//a[@aria-label='Insta'])["+index+"] | (//img[contains(@alt, 'Instagram')])["+index+"]");
    }
    protected By twitterLink = By.xpath("//*[contains(@class, 'twitter')] | //i[@class='fa fa-twitter']");
    protected By weiboLink = By.xpath("//*[@id=\"footercontent\"]/div[1]/div[2]/div/div[1]/div/div/div/ul/li[3]/a/img");
    protected By youtubeLink = By.xpath("//span[contains(@class, 'youtube')] | //a[@class='fa fa-youtube'] | //a[contains(@class,'youtube')]");
    public By youtubeLink(int index) {
        return By.xpath("(//span[contains(@class, 'youtube')])[["+index+"]] | (//a[@class='fa fa-youtube'])["+index+"] | (//a[contains(@class,'youtube')])["+index+"]");}
    protected By pinterestLink = By.xpath("//a[contains(@class,'pinterest')]");



    //-----> FOR CHECKING PRICE AND IMAGES
    protected By nullImage = By.xpath("//img[@title='null'] | //img[@src='null']");
    protected By loadMore = By.xpath("//span[contains(text(),'Load')]//button | //span[contains(@class, 'refresh')] | //button[contains(text(), 'More')] | //i[@class='fa fa-angle-right']");
    protected By nullPrice = By.xpath("//span[@content='null'] | //span[@class='value' and @content='null']");
    protected By imageNumber = By.xpath("(//div[@class='slick-list draggable'])[2]//descendant::img" +
            "| //div[@class='carousel-inner']//descendant::img" +
            "| (//div[@class='slick-list draggable'])[2]//img");
    public By itemimage(int index) {
        return By.xpath("((//div[@class='slick-list draggable'])[2]//descendant::img)[" + index + "]" +
                "| (//div[@class='carousel-inner']//descendant::img)[" + index + "]" +
                "| ((//div[@class='slick-list draggable'])[2]//img)[" + index + "]");
    }
    protected By buttonDisabled = By.xpath("//i[@class='fa fa-angle-right']//parent::button");


    //-----> FOR SELECTING ITEM DETAILS
    public By loc_productColor(String ProductColor){
        return By.xpath("//button[@aria-label='Select Color "+ProductColor+"'] |" +
                "//button[starts-with(@class,'color-attribute') and @data-label='"+ProductColor+"'] |" +
                "//button[@data-label='"+ProductColor+"'] |" +
                "//button[starts-with(@class,'color-attribute') and @aria-label='Select Color "+ProductColor+"']");}
    public By loc_productColor_dropdown(String ProductColor){
        return By.xpath("//option[@data-attr-value='"+ProductColor+"']");}
    public By loc_productSize(String ProductSize){
        return By.xpath("//ul[@id='size-list']//li[normalize-space()='"+ProductSize+"'] |" +
                "//button[@title='Select "+ProductSize+"']");}

    public By loc_aceProductSize (String ProductSize){ return By.xpath("(//button[@data-original-title='"+ProductSize+"'])[2] | //button[@data-original-title='"+ProductSize+"']"); }
    public By loc_aceProductColor (String ProductColor){ return By.xpath("//button[contains(@class,'btn btn-link') and @aria-label='Select Color "+ProductColor+"']");}
    public By loc_kokuyoType(String ProductType){
        return By.xpath("//button[@aria-label='Select Type "+ProductType+"'] |" +
                "//button[@title='"+ProductType+"']");}

    public By loc_metaItemSize (String ProductSize){
        return By.xpath("//span[@id='"+ProductSize+"']");
    }
    protected By loc_sizeDropdown = By.xpath("//select[@id='size-1'] | //select[@class='custom-select form-control select-size']");
    protected By loc_ColorDropdown = By.xpath("//select[contains(@class,'select-color') and contains(@id,'color')]");
    public By loc_tanukiProductColor(String ProductSize){
        return By.xpath("//option[@data-attr-value='"+ProductSize+"']");}
    public By loc_productLensesColor(String LensesColor){
        return By.xpath("//button[@aria-describedby='"+LensesColor+"']");}
    public By loc_productFrameColor(String FrameColor){
        return By.xpath("//button[@aria-describedby='"+FrameColor+"']");}
    protected By loc_productQuantity = By.xpath("//select [@id='quantity-1'] | //*[@id='quantity-1']");
    protected By loc_productQuantity2 = By.xpath("//input [@class='quantity-select']");
    protected By loc_qtyPlusIcon = By.xpath("//button[starts-with(@class,'qty-btn increase')] | //span[starts-with(@class,'icon-plus')]");
    protected By header = By.xpath("//header");

    //----> FOR SORTING
    protected By sortButton = By.xpath("//div[@class='grid-header row']//descendant::span[contains(@aria-labelledby, 'sort')]" +
            "| //a[@id='openSortMenu'] | //div[@class='sort-order-wrapper'] " +
            "| //div[@class='sort-dropdown']//descendant::span[contains(@aria-labelledby,'sort-order')]" +
            "| //div[@class='grid-header row']//descendant::select[@class='custom-select'] | //div[@class='plp-sort']" +
            "| //div[@class='row grid-header align-items-center']//descendant::select[@class='custom-select']" +
            "| //div[@class='text-right fs-md-16 lh-md-20 text-uppercase text-black-50']//child::select[@name='sort-order']" +
            "| //div[@class='sort-dropdown']//descendant::a | //div[@class='row grid-header']//descendant::select[@name='sort-order']" +
            "| //div[@aria-label='Sort By']//span | //div[@class='dropdown-sort'] | //div[@class='filter-options']//child::select[@name='sort-order']");

    public By sortOption(String sortOption) {
        return By.xpath("//option[contains(text(),'"+sortOption+"')] | //span[contains(@title, '"+sortOption+"')]" +
                "| //div[contains(text(), '"+sortOption+"')]");
    }
    protected By productCard = By.xpath("//div[@class='row product-grid']//div//child::div[@class='product'] | //div[@class='product product-card card'] | //div[@class='product']");
    protected By firstProduct = By.xpath("(//div[@class='row product-grid']//child::div//descendant::span[@class='value'])[1]" +
            "| (//div[@class='product']//descendant::span[@class='value'])[1]");
    public By lastProduct(int index) {
        return By.xpath("(//div[@class='row product-grid']//child::div//descendant::span[@class='value'])["+index+"]" +
                "| (//div[@class='tab-content col-12']//child::div//descendant::span[@class='value'])["+index+"]" +
                "| (//div[@class='product']//descendant::span[@class='value'])["+index+"]");
    }
    protected By firstProductDays = By.xpath("//div[@class='row product-grid']//div[1]//descendant::div[@class='countdown']");
    public By lastProductDays(int index) {
        return By.xpath("//div[@class='row product-grid']//div["+index+"]//descendant::div[@class='countdown']");
    }
    protected By countSale = By.xpath("//span[@class='strike-through list']");
    protected By firstPriceSale = By.xpath("(//span[@class='strike-through list']//span[@class='value'])[1]");
    public By lastPriceSale(int index){
        return By.xpath("//div["+index+"]//descendant::span/span[@class='value']");
    }

    //-----> For Filtering
    protected By filterButton = By.xpath("//*[@id=\"product-search-results\"]/div[1]/div[2]/a | //a[@id='openFilter'] | //button[@class='text-uppercase lh-md-20 mr-md-2 btn filter-results text-black-50'] | //button[contains(@class, 'filter')]");
    protected By filterColorButton = By.xpath("//button[@aria-controls='refinement-color'] | //a[contains(@class,'filter')]");
    public By filter_ColorOption(String value){
        return By.xpath("//button//span[normalize-space()='"+value+"']" +
                "| //button//span[contains(text(),'"+value+"')]//preceding-sibling::span");
    }
    public By validateFilter_Color(String value){
        return By.xpath("(//div[contains(@class,'tab-content')])[1]//div[contains(@class,'product')]//parent::div[@class='tile-body']//following-sibling::a[contains(@href,'color="+value+"')]");
    }
    public By validateFilter_Color2(String value){
        return By.xpath("//div[contains(@class,'product-grid')]//descendant::script[contains(text(),'"+value+"')][1]");
    }
    protected By filter_PriceDropdown = By.xpath("//button//*[starts-with(text(),'Price')] | //*[@aria-label='Price'] | (//*[normalize-space()='Price'])[3] | //div[@id='ByPrice']");
    protected By filter_productCard = By.xpath("//div[contains(@class,'product-grid')]//div//child::div[@class='product'] | //div[@class='product product-card card']");
    protected By filter_firstProduct = By.xpath("(//div[@class='row product-grid']//child::div//descendant::span[@class='value'])[1]" +
            "| (//div[@class='product']//descendant::span[@class='value'])[1]");
    public By filter_lastProduct(int index) {
        return By.xpath("(//div[@class='row product-grid']//child::div//descendant::span[@class='value'])["+index+"]" +
                "| (//div[@class='tab-content col-12']//child::div//descendant::span[@class='value'])["+index+"]" +
                "| (//div[@class='product']//descendant::span[@class='value'])["+index+"]");
    }
    protected By priceSlider = By.xpath("//div[@id='price-slider-range']");
    protected By amountStart = By.xpath("//span[@id='amountStart']");
    protected By amountEnd = By.xpath("//span[@id='amountEnd']");
    protected By filter_BrandButton = By.xpath(".//span[text()='Brand']");
    public By filter_BrandOption(String value){
        return By.xpath("//div[@class='refinements active']//descendant::span[contains(text(), '"+value+"')]");
    }
    protected By applyFilter = By.xpath("//*[normalize-space()='Apply'] | //span[text()='Apply'] | //button[contains(text(),'Save')]");
    protected By validateBrandProduct = By.xpath("//div[@class='product']");
    public By filter_PriceRange(String value){
        return By.xpath("//div[@id='ByPrice']//descendant::button//span[contains(text(), '"+value+"')] | .//button//span[contains(text(),'"+value+"')]//preceding-sibling::span");
    }
}
