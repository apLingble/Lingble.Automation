package framework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author alexander.v.pangilinan
 * */

public class CartPage extends BasePageObject<CartPage>{

    public CartPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //FOR MINI CART / POP-UP CART PAGE

    //LOCATORS LIST FOR VALIDATE PRICE ON MINICART
    protected By get_cartItemsCount = By.xpath("//div[@class='product-summary']//div[@class='row']");
    protected By get_salePriceCount = By.xpath("//span[@class='sales']//ancestor::div[contains(@class, 'minicart')]");
    public By loc_minicart_product_price(String productName){
        return By.xpath("//div[@class='popover popover-bottom show']//img[contains(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value'] | " +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value']| " +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value']");
    }
    public By loc_minicart_productSale_price(String productName){
        return By.xpath("//img[contains(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='sales']");
    }
    public By loc_minicart_Quantity(String productName){
        return By.xpath("//div[@class='popover popover-bottom show']//img[contains(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected] |" +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected]" +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected]");
    }

    protected By loc_minicart_Total = By.xpath("//div[contains(@class,'total-price-amount')]");

    //LOCATORS FOR SECOND ITEM IN POP OVER MINICART
    public By loc_minicart_second_quantity (String secondProductName){
        return By.xpath("//img[contains(@title,\""+secondProductName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected] |" +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+secondProductName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected] |" +
                "//img[starts-with(@title,\""+secondProductName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected]");
    }
    public By loc_minicart_second_price(String second_productName){
        return By.xpath("//img[contains(@title,\""+second_productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value'] |" +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+second_productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value'] |" +
                "//img[starts-with(@title,\""+second_productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value']");
    }
    protected By loc_second_minicart_total = By.xpath("(//div[contains(@class,'total-price-amount')])[2]");
    protected By loc_minicart_estimatedTotal = By.xpath("//div[@class='popover popover-bottom show']//div[@class='row estimated-total']//p[@class='text-right sub-total'] |" +
            "//div[@class='row estimated-total']//p[@class='text-right sub-total']");
    protected By loc_view_cart = By.xpath("//div[@class='popover popover-bottom show']//a[@title='View Cart']");


    //LOCATORS FOR POPOVER CART VALIDATIONS (COLOR | SIZE | TYPE | FRAME | LENSES | ETC)
    protected By loc_minicart_button = By.xpath("(//div[contains(@class,'minicart')]//a[contains(@class,'minicart-link')])[1] |" +
            "//a[@class='minicart-link'] |" +
            "//a[@class='minicart-link']//img[@alt='mini cart Icon']");
    protected By get_popOverItemCount = By.xpath("//div[@class='popover popover-bottom show']");
    public By MiniCartItemImage(String ProductName){
        return By.xpath("//div[@class='popover popover-bottom show']//img[contains(@title,\""+ProductName+"\")] |" +
                " //div[@class='popover popover-bottom show']//img[contains(@title,\""+ProductName+"\")] | " +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+ProductName+"\")]");
    }
    public By popover_cart_item_color(String ProductName, String ProductColor){
        return By.xpath("//img[contains(@title,\""+ProductName+"\")]//parent::div//following-sibling::div//p[normalize-space()='Color: "+ProductColor+"'] |" +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+ProductName+"\")]//parent::div//following-sibling::div//p[normalize-space()='Color: "+ProductColor+"'] |" +
                "//img[starts-with(@title,\""+ProductName+"\")]//parent::div//following-sibling::div//p[normalize-space()='Color: "+ProductColor+"']");
    }
    public By popover_cart_item_size (String ProductName, String ProductSize){
        return By.xpath("//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+ProductName+"\")]//parent::div//following-sibling::div//p[normalize-space()='Size: "+ProductSize+"'] |" +
                "//img[starts-with(@title,\""+ProductName+"\")]//parent::div//following-sibling::div//p[normalize-space()='Size: "+ProductSize+"'] |" +
                "//img[contains(@title,\""+ProductName+"\")]//parent::div//following-sibling::div//p[normalize-space()='Size: "+ProductSize+"'] |" +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+ProductName+"\")]//parent::div//following-sibling::div//p[normalize-space()='Size: "+ProductSize+"']");
    }

    public By popover_cart_item_washType (String ProductName, String WashType){
        return By.xpath("//div[@class='popover popover-bottom show']//img[starts-with(@title,'"+ProductName+"')]//parent::div//following-sibling::div//p[normalize-space()='Wash Type: "+WashType+"'] |" +
                "//img[starts-with(@title,'"+ProductName+"')]//parent::div//following-sibling::div//p[normalize-space()='Wash Type: "+WashType+"']");
    }
    public By popover_cart_item_flatType (String ProductName, String ProductType){
        return By.xpath("//img[contains(@title,'"+ProductName+"')]//parent::div//following-sibling::div//p[normalize-space()='Type: "+ProductType+"'] |" +
                "//img[contains(@title,'"+ProductName+"')]//parent::div//following-sibling::div//p[normalize-space()='Size: "+ProductType+"']");
    }
    public By MiniCartItemQuantity(String productName){
        return By.xpath("//div[@class='popover popover-bottom show']//img[contains(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected] |" +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected]" +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//div[@class='quantity-form']//option[@selected]");
    }
    public By MiniCartItemUnitPrice(String productName){
        return By.xpath("//div[@class='popover popover-bottom show']//img[contains(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value'] | " +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value']| " +
                "//div[@class='popover popover-bottom show']//img[starts-with(@title,\""+productName+"\")]//parent::div//parent::div//following-sibling::div//span[@class='value']");
    }
    protected By MiniCartItemTotalPriceAmount = By.xpath("(//div[@class='popover popover-bottom show']//div[contains(@class,'total-price-amount')]) |" +
            "(//div[@class='popover popover-bottom show']//div[contains(@class,'line-item-total-price-amount')])");
    protected By PopOverCartTotalPrice = By.xpath("//div[@class='popover popover-bottom show']//div[@class='row estimated-total']//p[@class='text-right sub-total'] | //div[@class='row estimated-total']//p[@class='text-right sub-total']");


    //FOR CART PAGE
    public By CartPageItemImage(String productName){
        return By.xpath("//div[@class='cart-page']//img[contains(@title,\""+productName+"\")] |" +
                " //div[@class='cart-page']//img[contains(@title,\""+productName+"\")] | " +
                "//div[@class='cart-page']//img[starts-with(@title,\""+productName+"\")]");
    }
    public By CartPageItemQuantity(String productName){
        return By.xpath("//div[@class='container cart cart-page']//img[contains(@title,\""+productName+"\")]//ancestor::div//following-sibling::div[contains(@class,'product-card-footer')]//div[@class='quantity-form']//option[@selected] |" +
                "//div[@class='container cart cart-page']//img[starts-with(@title,\""+productName+"\")]//ancestor::div//following-sibling::div[contains(@class,'product-card-footer')]//div[@class='quantity-form']//option[@selected]");}
    protected By CartPageItemUnitPrice = By.xpath("//div[contains(@class,'cart-page')]//span[contains(@class,'value')]");
    protected By CartPageItemTotalPriceAmount = By.xpath("//div[contains(@class,'cart-page')]//div[contains(@class,'line-item-total-price-amount')]");
    public By cartPage_product(String productName){
        return By.xpath("//div[@class='container cart cart-page']//img[starts-with(@title,\""+productName+"\")] |" +
                "//div[@class='container cart cart-page']//div[contains(text(),\""+productName+"\")]");
    }
    public By cartPage_productColor(String productName, String productColor){
        return By.xpath("//p[contains(text(),'Color: "+productColor+"')] |" +
                "//div[@class='container cart cart-page']//img[starts-with(@title,\""+productName+"\")]//parent::div//following-sibling::div//p[normalize-space()='Color: "+productColor+"']");
    }
    public By cartPage_productSize(String productName, String productSize){
        return By.xpath("//div[@class='container cart cart-page']//img[starts-with(@title,'"+productName+"')]//parent::div//following-sibling::div//p[normalize-space()='Size: "+productSize+"'] |" +
                "//p[contains(text(),'Size: "+productSize+"')]");
    }
    public By cartPage_productType(String productName, String productType){
        return By.xpath("//div[@class='container cart cart-page']//img[starts-with(@title,'"+productName+"')]//parent::div//following-sibling::div//p[normalize-space()='Type: "+productType+"'] |" +
                "//p[contains(text(),'Type: "+productType+"')]");
    }
    public By cartPage_productFrameColor(String productName, String productFrameColor){
        return By.xpath("//p[normalize-space()='Frame Color: "+productFrameColor+"']");
    }
    public By cartPage_productLensesColor(String productName, String productlensesColor){
        return By.xpath("//p[normalize-space()='Lenses Color: "+productlensesColor+"']");
    }
    public By cartPage_productWashType(String productName, String pruductWashType){
        return By.xpath("//p[normalize-space()='Wash Type: "+pruductWashType+"'] |" +
                "//div[@class='container cart cart-page']//img[starts-with(@title,'"+productName+"')]//parent::div//following-sibling::div//p[normalize-space()='Wash Type: "+pruductWashType+"']");
    }

    //-----> FOR REMOVING PRODUCTS FROM CART
    protected By popUpRemoveIcon = By.xpath("//button[@data-target='#removeProductModal'] | //button[@class='remove-btn remove-product btn btn-light']");
    protected By modalYesButton = By.xpath("//button[@class='btn btn-primary cart-delete-confirmation-btn']");
    protected By removeProductConfirmation = By.xpath("//button[@class='btn btn-primary cart-delete-confirmation-btn'] | //*[@id='removeProductModal']/div/div/div[3]/button[2]");
    public By getItemFromPopUpCart(String productName){ return By.xpath("//img[contains(@title,\""+productName+"\")]//ancestor::div[contains(@class, 'popover popover-bottom')]");}
    public By cartRemoveIcon(String productName){ return By.xpath("//div[4]/button[contains(@data-name, \""+productName+"\")]");}
    public By numberOfItemsInCart = By.xpath("//h2[@class='number-of-items']");


    //-----> FOR EDITING PRODUCT DETAILS IN CART
    protected By editbutton = By.xpath("//a[@data-target='#editProductModal']");
    protected By editWindow = By.xpath("//div[@id='editProductModal']");
    protected By updateButton = By.xpath("//button[contains(@class, 'update')]");
    protected By cartQuantityOption (String value){ return By.xpath("//select//option[text()='"+value+"']"); }
    protected By cartQuantityDropdown = By.xpath("//div[@class='quantity-form'] | //select[@aria-label='quantity: 1']");
    public By itemColor(String value) {
        return By.xpath("//p[text()='Color: "+value+"']");
    }
    public By itemSize(String value) {
        return By.xpath("//p[text()='Size: "+value+"']");
    }


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
    protected By loc_inputQuantity = By.xpath("//input[@class='quantity-select']");

    //Added locators for add to cart from wishlist
    protected  By loc_get_cartItemsCount = By.xpath("//h2[starts-with(text(),'0 Items')]");
    public By loc_validate_wishlist (String ProductName){
        return By.xpath("//a[contains(text(),\""+ProductName+"\")] |" +
                "//div[normalize-space()=\""+ProductName+"\"] |" +
                "//div[contains(text(),\""+ProductName+"\")]");
    }
    protected By CheckoutButton = By.xpath("//a[normalize-space(@class)='btn btn-primary btn-block checkout-btn'] | //div[contains(@class,'totals')]//a[normalize-space(@class)='btn btn-primary btn-block checkout-btn']");

    //End of added locators for add to cart from wishlist
    protected By loc_userIcon_userLoginButton = By.xpath("//div[contains(@class,'hidden')]//following::a[@aria-label='Login to your account'] |" +
            "//a[@id='myaccount'] |" +
            "(//div[contains(@class,'user')])[1]");
    protected By loc_userIcon_userLoginButton1 = By.xpath("//div[contains(@class,'hidden')]//following::a[@aria-label='Login to your account']");
    protected By loc_userIcon_userLoginButton2 = By.xpath("//a[@id='myaccount']");
    protected By loc_userIcon_userLoginButton3 = By.xpath("(//div[contains(@class,'user')])[2]");
    protected By loc_userIcon_userLoginButton4 = By.xpath("(//div[contains(@class,'user')])[1]");
    protected By loc_dropdown_myAccount_button1 = By.xpath("(//div[@class='popover popover-bottom show']//a[contains(text(),'My Account')])[1]");
    protected By loc_dropdown_myAccount_button2 = By.xpath("(//a[@class='dropdown-link' and text()='My Account'])[1]");
    protected By loc_dropdown_myAccount_button3 = By.xpath("(//a[contains(text(),'My Account')])[1]");
    protected By loc_dropdown_myAccount_button4 = By.xpath("//a[@id='myaccount']//following::a[normalize-space()='My Account']");

    protected By loc_view_all_wishlist = By.xpath("//h2[normalize-space()='Wishlist']//following-sibling::a");
    protected By loc_wishlist_AddToCart_Button = By.xpath("(//input[@class='add-to-cart-url']//following-sibling::button)[1] |" +
            "//button[@class='add-to-cart btn btn-primary btn-block'] |" +
            "//button[normalize-space()='Add to Cart']");

    protected By loc_addToWishlist_button = By.xpath("//div[@class='product-edit product-move']//a[starts-with(@title,'Move to Wishlist')]");
    protected By loc_removeProduct_confirmation = By.xpath("//button[@class='btn btn-primary cart-delete-confirmation-btn']");
    public By number_of_items (String ItemCount){
        return By.xpath("//div[@class='container']//parent::div//following-sibling::div//h2[contains(text(),\""+ItemCount+"\")]");
    }
    public By loc_wishlist_removeItem_button (String productName){
        return By.xpath("//a[contains(text(),\""+productName+"\")]//following::span[@class='iconmoon-delete'] |" +
                "//div[contains(text(),\""+productName+"\")]//following-sibling::div//button[@aria-label='remove' and @class='remove-btn-lg remove-from-wishlist btn btn-light']");
    }
    protected By loc_wishlistIcon1 = By.xpath("(//i[@class='fa fa-heart'])[2]");
    protected By loc_wishlistIcon2 = By.xpath("//i[@class='fa fa-heart']");
    protected By loc_wishlistIcon3 = By.xpath("//*[starts-with(text(),'My Wishlist')] | //a[starts-with(text(),'MY WISHLIST')]");
    protected By loc_wishlistIcon4 = By.xpath("//a[contains(text(),'Wishlist')]");
    protected By loc_wishlistIcon5 = By.xpath("(//img[@class='action-icon'])[3]");
    protected By loc_count_email_texbox = By.xpath("//label[@for='login-form-email']//following-sibling::input[@name='loginEmail']");
    protected By loc_wishlist_label = By.xpath("//h1[@class='page-title' and text()='Wishlist']");


}
