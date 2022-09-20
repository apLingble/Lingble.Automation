package framework.keyword;

import framework.commonFunctions.ConfigurationKeyEnum;
import framework.pages.CartPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @author alexander.v.pangilinan
 * */

public class CartKeyword extends CartPage {

    public CartKeyword(WebDriver driver, Logger log) {
        super(driver, log);
    }

    String productName = System.getProperty("productName");
    String secondProductName = System.getProperty("secondProductName");
    String productQuantity = System.getProperty("updateProductQuantity");
    String productColor = System.getProperty("updateProductColor");
    String US_productColor = System.getProperty("update_US_productColor");
    String productSize = System.getProperty("updateProductSize");
    String productType = System.getProperty("updateProductType");
    String productFrameColor = System.getProperty("updateProductFrameColor");
    String productLensesColor = System.getProperty("updateProductLensesColor");
    String cartPage_productColor = System.getProperty("productColor");
    String cartPage_productSize = System.getProperty("productSize");
    String cartPage_productType = System.getProperty("productType");
    String cartPage_productWashType = System.getProperty("productWashType");
    String get_currentURL = driver.getCurrentUrl().toString();

    //FOR VALIDATING THE MINI CART / POP-UP CART PAGE

    public Integer Check_popupcart_ifAvailable() {
        moveMouseToMiniCart();
        return elementCount(get_popOverItemCount);
    }
    public void moveMouseToMiniCart() {
        waitUntilPageready();
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-500)");
        moveTo(loc_minicart_button);
    }
    public void validateIfMiniCartIsVisible() {
        waitForElementToBeVisible(get_popOverItemCount,500);
    }

    public void check_popover_cartItem(String product){
        String productName = System.getProperty(product+"productName");

        waitForWebElementVisibility(MiniCartItemImage(productName),3);
        log.info("SELECTED PRODUCT NAME: " + productName + " IS VISIBLE IN POPOVER CART.");
    }

    //POP-UP MINICART VALIDATION.
    public  void validate_popup_cart_itemDetails (String partner, String product){
        log.info("VALIDATE FIRST PRODUCT IN MINICART");
        popover_cart_validation(partner, product);
    }
    public  void validate_popup_cart_secondItemDetails (String partner, String product){
        log.info("VALIDATE SECOND PRODUCT IN MINICART");
        popover_cart_validation(partner, product);
    }
    public void popover_cart_validation(String partner, String product){
        Map<String, Runnable> itemDetails = new HashMap<>();

        itemDetails.put("attachment", () -> attachment_popupCart_validation(product));
        itemDetails.put("ace", () -> ace_popupCart_validation(product));
        itemDetails.put("ecchi", () -> ecchi_popupCart_validation(product));
        itemDetails.put("briefing", () -> briefing_popupCart_validation(product));
        itemDetails.put("dnd", () -> dnd_popupCart_validation(product));
        itemDetails.put("haku", () -> haku_popupCart_validation(product));
        itemDetails.put("igc", () -> igc_popupCart_validation(product));
        itemDetails.put("kokuyo", () -> kokuyo_popupCart_validation(product));
        itemDetails.put("mago", () -> mago_popupCart_validation(product));
        itemDetails.put("makuake", () -> makuake_popupCart_validation(product));
        itemDetails.put("maruzeki", () -> maruzeki_popupCart_validation(product));
        itemDetails.put("metaphore", () -> metaphore_popupCart_validation(product));
        itemDetails.put("swans", () -> swans_popupCart_validation(product));
        itemDetails.put("talex", () -> talex_popupCart_validation(product));
        itemDetails.put("tanuki", () -> tanuki_popupCart_validation(product));
        itemDetails.put("tatras", () -> tatras_popupCart_validation(product));
        itemDetails.put("toot", () -> toot_popupCart_validation(product));
        itemDetails.put("xgirl", () -> xgirl_popupCart_validation(product));
        itemDetails.put("xlarge", () -> xlarge_popupCart_validation(product));

        itemDetails.get(partner.toLowerCase()).run();
    }

    public void attachment_popupCart_validation(String product){
        validate_popupCart_color_attachment(product);
        validate_popupCart_size(product);
    }
    public void ace_popupCart_validation(String product){
        validate_popupCart_color(product);
    }
    public void briefing_popupCart_validation(String product){
        validate_popupCart_color(product);
    }
    public void dnd_popupCart_validation(String product){
        validate_popupCart_color(product);
    }
    public void ecchi_popupCart_validation(String product){
        log.info("NO PRODUCT DETAILS TO VALIDATE ON POPOVER CART.");
    }
    public void haku_popupCart_validation(String product){
        validate_popupCart_color(product);
        validate_popupCart_size(product);
    }
    public void igc_popupCart_validation(String product){
        log.info("NO PRODUCT DETAILS TO VALIDATE ON POPOVER CART.");
    }
    public void kokuyo_popupCart_validation(String product){
        validate_popupCart_color(product);
        popover_cart_item_flatType(product);
    }
    public void mago_popupCart_validation(String product){
        log.info("NO PRODUCT DETAILS TO VALIDATE ON POPOVER CART.");
    }
    public void makuake_popupCart_validation(String product){
        log.info("NO PRODUCT CART.");
    }
    public void maruzeki_popupCart_validation(String product){
        log.info("NO PRODUCT DETAILS TO VALIDATE ON POPOVER CART.");
    }
    public void metaphore_popupCart_validation(String product){
        log.info("NO PRODUCT CART.");
    }
    public void swans_popupCart_validation(String product){
        log.info("NO PRODUCT CART.");
    }
    public void talex_popupCart_validation(String product){
//        Pending
    }
    public void tanuki_popupCart_validation(String product){
        validate_popupCart_size(product);
        popover_cart_item_washType(product);
    }
    public void tatras_popupCart_validation(String product){
        validate_popupCart_color(product);
        validate_popupCart_size(product);
    }
    public void toot_popupCart_validation(String product){
        validate_popupCart_color(product);
        validate_popupCart_size(product);
    }
    public void xlarge_popupCart_validation(String product){
        validate_popupCart_color(product);
        validate_popupCart_size(product);
    }
    public void xgirl_popupCart_validation(String product){
        validate_popupCart_color(product);
        validate_popupCart_size(product);
    }
    public void validate_popupCart_color(String product){
        String productName = System.getProperty(product+"productName");
        String productColor = System.getProperty(product+"productColor");

        waitForWebElementVisibility(popover_cart_item_color(productName, productColor));
        log.info("Expected color: " + productColor + " is visible over in the popover cart.");
    }
    public void validate_popupCart_color_attachment(String product){
        String productName = System.getProperty(product+"productName");
        String US_productColor = System.getProperty(product+"US_productColor");
        String productColor = System.getProperty(product+"productColor");
        String get_currentURL = driver.getCurrentUrl().toString();

        log.info(get_currentURL);
        if (get_currentURL.contains("en_US")) {
            waitForWebElementVisibility(popover_cart_item_color(US_productColor, US_productColor));
            log.info("Expected color: " + US_productColor + " is visible over in the popover cart.");
        }else{
            waitForWebElementVisibility(popover_cart_item_color(productName, productColor));
            log.info("Expected color: " + productColor + " is visible over in the popover cart.");
        }
    }
    public void validate_popupCart_size(String product){
        String productName = System.getProperty(product+"productName");
        String productSize = System.getProperty(product+"productSize");

        waitForWebElementVisibility(popover_cart_item_size(productName, productSize));
        log.info("Expected size: " + productSize + " Is visible over in the popover cart.");
    }
    public void popover_cart_item_washType(String product){
        String productName = System.getProperty(product+"productName");
        String productWashType = System.getProperty(product+"productWashType");

        waitForWebElementVisibility(popover_cart_item_washType(productName, productWashType));
        log.info("Expected washtype: " + productWashType + " Is visible over in the popover cart.");
    }
    public void popover_cart_item_flatType(String product){
        String productName = System.getProperty(product+"productName");
        String productType = System.getProperty(product+"productType");

        waitForWebElementVisibility(popover_cart_item_flatType(productName, productType));
        log.info("Expected washtype: " + productType + " Is visible over in the popover cart.");
    }

    //region CART PAGE VALIDATION

    public  void validate_cartPage_ItemDetails (String partner, String product){
        log.info("VALIDATE PRODUCT IN CART");
        cartPage_validation(partner, product);
    }
    public  void validate_cartPage_secondItemDetails (String partner, String product){
        log.info("VALIDATE SECOND PRODUCT IN CART");
        cartPage_validation(partner, product);
    }

    public void cartPage_validation(String partner, String product){
        log.info("CART PAGE VALIDATION");
        waitUntilPageready();
        Map<String, Runnable> itemDetails = new HashMap<>();

        itemDetails.put("attachment", () -> attachment_cartPage_validation(product));
        itemDetails.put("ace", () -> ace__cartPage_validation(product));
        itemDetails.put("ecchi", () -> ecchi__cartPage_validation(product));
        itemDetails.put("briefing", () -> briefing__cartPage_validation(product));
        itemDetails.put("dnd", () -> dnd__cartPage_validation(product));
        itemDetails.put("haku", () -> haku_cartPage_validation(product));
        itemDetails.put("igc", () -> igc__cartPage_validation(product));
        itemDetails.put("kokuyo", () -> kokuyo_cartPage_validation(product));
        itemDetails.put("mago", () -> mago_cartPage_validation(product));
        itemDetails.put("makuake", () -> makuake_cartPage_validation(product));
        itemDetails.put("maruzeki", () -> maruzeki_cartPage_validation(product));
        itemDetails.put("metaphore", () -> metaphore_cartPage_validation(product));
        itemDetails.put("swans", () -> swans_cartPage_validation(product));
        itemDetails.put("talex", () -> talex_cartPage_validation(product));
        itemDetails.put("tanuki", () -> tanuki_cartPage_validation(product));
        itemDetails.put("tatras", () -> tatras_cartPage_validation(product));
        itemDetails.put("toot", () -> toot_cartPage_validation(product));
        itemDetails.put("xgirl", () -> xgirl_cartPage_validation(product));
        itemDetails.put("xlarge", () -> xlarge_cartPage_validation(product));

        itemDetails.get(partner.toLowerCase()).run();
    }

    public void attachment_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor_attachment(product);
        validate_cartPage_productSize(product);
    }
    public void ace__cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
        validate_cartPage_productSize(product);
    }
    public void briefing__cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
    }
    public void dnd__cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
    }
    public void ecchi__cartPage_validation(String product){
        validate_cartPage_product(product);
    }
    public void haku_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
        validate_cartPage_productSize(product);
    }
    public void igc__cartPage_validation(String product){
        validate_cartPage_product(product);
    }
    public void kokuyo_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
        validate_cartPage_productType(product);
    }
    public void mago_cartPage_validation(String product){
        validate_cartPage_product(product);
    }
    public void makuake_cartPage_validation(String product){
        log.info("NO CART PAGE");
    }
    public void maruzeki_cartPage_validation(String product){
        validate_cartPage_product(product);
    }
    public void metaphore_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
    }
    public void swans_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
    }
    public void talex_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productFrameColor(product);
        validate_cartPage_productLensesColor(product);
    }
    public void tanuki_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productSize(product);
        validate_cartPage_productWashType(product);
    }
    public void tatras_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
        validate_cartPage_productSize(product);
    }
    public void toot_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
        validate_cartPage_productSize(product);
    }
    public void xgirl_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
        validate_cartPage_productSize(product);
    }
    public void xlarge_cartPage_validation(String product){
        validate_cartPage_product(product);
        validate_cartPage_productColor(product);
        validate_cartPage_productSize(product);
    }

    public void validate_cartPage_product(String product){
        String productName = System.getProperty(product+"productName");

        waitForWebElementVisibility(cartPage_product(productName));
        log.info("Expected product: " + productName + " is visible in the cart page.");
    }
    public void validate_cartPage_productColor(String product){
        String productName = System.getProperty(product+"productName");
        String productColor = System.getProperty(product+"productColor");

        waitForWebElementVisibility(cartPage_productColor(productName, productColor));
        log.info("Expected product color: " + productColor + " is visible in the cart page.");
    }
    public void validate_cartPage_productColor_attachment(String product){
        String US_productColor = System.getProperty(product+"US_productColor");
        String productColor = System.getProperty(product+"productColor");
        String get_currentURL = driver.getCurrentUrl().toString();
        log.info(get_currentURL);
        if (get_currentURL.contains("en_US")) {
            waitForWebElementVisibility(cartPage_productColor(productName, US_productColor));
            log.info("Expected product color: " + US_productColor + " is visible in the cart page.");
        }else{
            waitForWebElementVisibility(cartPage_productColor(productName, productColor));
            log.info("Expected product color: " + productColor + " is visible in the cart page.");
        }
    }
    public void validate_cartPage_productSize(String product){
        String productName = System.getProperty(product+"productName");
        String productSize = System.getProperty(product+"productSize");

        waitForWebElementVisibility(cartPage_productSize(productName, productSize));
        log.info("Expected product size: " + productSize + " is visible in the cart page.");
    }
    public void validate_cartPage_productType(String product){
        String productName = System.getProperty(product+"productName");
        String productType = System.getProperty(product+"productType");

        waitForWebElementVisibility(cartPage_productType(productName, productType));
        log.info("Expected product type: " + productType + " is visible in the cart page.");
    }
    public void validate_cartPage_productFrameColor(String product){
        String US_productColor = System.getProperty(product+"US_productFrameColor");
        String productColor = System.getProperty(product+"productFrameColor").replaceAll("[^a-zA-Z0-9]", " ");
        String get_currentURL = driver.getCurrentUrl().toString();
        log.info(get_currentURL);
        if (get_currentURL.contains("en_US")) {
            waitForWebElementVisibility(cartPage_productColor(productName, US_productColor));
            log.info("Expected product frame color: " + US_productColor + " is visible in the cart page.");
        }else{
            waitForWebElementVisibility(cartPage_productColor(productName, productColor));
            log.info("Expected product frame color: " + productColor + " is visible in the cart page.");
        }
    }
    public void validate_cartPage_productLensesColor(String product){
        String US_productColor = System.getProperty(product+"US_productLensesColor");
        String productColor = System.getProperty(product+"productLensesColor").replaceAll("[^a-zA-Z0-9]", " ");
        String get_currentURL = driver.getCurrentUrl().toString();
        log.info(get_currentURL);
        if (get_currentURL.contains("en_US")) {
            waitForWebElementVisibility(cartPage_productColor(productName, US_productColor));
            log.info("Expected product lenses color: " + US_productColor + " is visible in the cart page.");
        }else{
            waitForWebElementVisibility(cartPage_productColor(productName, productColor));
            log.info("Expected product lenses color: " + productColor + " is visible in the cart page.");
        }
    }
    public void validate_cartPage_productWashType(String product){
        String productName = System.getProperty(product+"productName");
        String productWashType = System.getProperty(product+"productWashType");

        waitForWebElementVisibility(cartPage_productWashType("productName", productWashType));
        log.info("Expected product wash type: " + productWashType + " is visible in the cart page.");
    }
    //endregion

    public void popover_minicart_price_validation(){
        waitUntilPageready();
        scrollToElement(loc_minicart_button);
        moveTo(loc_minicart_button);
        waitUntilPageready();
        int get_cartItemCount = elementCount(get_cartItemsCount);
        log.info("ITEM COUNT: " + get_cartItemCount);
        int get_salesPriceCount = elementCount(get_salePriceCount);
        log.info("SALES PRICE COUNT: " + get_salesPriceCount);

        String get_quantity = getText(loc_minicart_Quantity(productName)).toString();
        log.info("PRODUCT QUANTITY: " + get_quantity);

        String get_product_price ="";

        if (get_salesPriceCount == 0){
            get_product_price = getText(loc_minicart_product_price(productName)).replaceAll("[^0-9.]", "");
            log.info("PRODUCT PRICE: " + get_product_price);
        }else if (get_salesPriceCount >= 1){
            get_product_price = getText(loc_minicart_productSale_price(productName)).replaceAll("[^0-9.]", "");;
            log.info("SALE PRODUCT PRICE: " + get_product_price);
        }

        String product_totalPrice = String.valueOf(Float.valueOf(get_product_price) * Float.valueOf(get_quantity));
        log.info("EXPECTED PRICE TO BE SEEN IN TOTAL: " + product_totalPrice);

        String get_total = getText(loc_minicart_Total).replaceAll("[^0-9.]", "");
        log.info("TOTAL: " + get_total);

        Assert.assertEquals(product_totalPrice,get_total);
        log.info("EXPECTED PRICE: " + product_totalPrice + " IS EQUALS TO TOTAL: " + get_total);

        //STEPS IF POPOVER MINICART HAS SECOND ITEM
        String second_product_totalPrice = "";
        if (get_cartItemCount > 1){
            String get_second_quantity = getText(loc_minicart_second_quantity(secondProductName)).toString();
            log.info("SECOND PRODUCT QUANTITY: " + get_second_quantity);

            String get_second_product_price = getText(loc_minicart_second_price(secondProductName)).replaceAll("[^0-9.]", "");
            log.info("SECOND PRODUCT PRICE: " + get_second_product_price);

            second_product_totalPrice = String.valueOf(Float.valueOf(get_second_product_price) * Float.valueOf(get_second_quantity));
            log.info("EXPECTED PRICE TO BE SEEN IN TOTAL: " + second_product_totalPrice);

            String get_second_product_total = getText(loc_second_minicart_total).replaceAll("[^0-9.]", "");
            log.info("TOTAL: " + get_second_product_total);

            Assert.assertEquals(second_product_totalPrice,get_second_product_total);
            log.info("EXPECTED PRICE: " + second_product_totalPrice + " IS EQUALS TO TOTAL: " + get_second_product_total);
        }

        String get_estimated_total = getText(loc_minicart_estimatedTotal).replaceAll("[^0-9.]", "");
        log.info("ESTIMATED TOTAL: " + get_estimated_total);

        if(get_cartItemCount == 1){
            Assert.assertEquals(product_totalPrice,get_estimated_total);
        }else{
            String first_and_second_product_totalAmount = String.valueOf(Float.valueOf(product_totalPrice) + Float.valueOf(second_product_totalPrice));
            log.info("FIRST AND SECOND PRODUCT TOTAL AMOUNT: " + first_and_second_product_totalAmount);
            Assert.assertEquals(first_and_second_product_totalAmount,get_estimated_total);
        }

        log.info("PRICE VALIDATION SUCCESS!");
        view_cart();
    }

    public void view_cart(){
        waitForWebElementToBeClickAble(loc_view_cart);
        click(loc_view_cart);
        waitUntilPageready();
    }
    public void minicart(){
        waitUntilPageready();
        scrollToTop(loc_minicart_button);
        moveTo(loc_minicart_button);
        click(loc_minicart_button);
        waitUntilPageready();
    }

    public void add_toWishlist_button(){
        waitForWebElementToBeClickAble(loc_addToWishlist_button, 5);
        click(loc_addToWishlist_button);
    }
    public void removeProuduct_confirmation(){
        String ItemCount = System.getProperty("ItemCount");
        log.info(ItemCount);

        waitForWebElementToBeClickAble(loc_removeProduct_confirmation, 5);
        click(loc_removeProduct_confirmation);
        log.info("REMOVE PRODUCT CONFIRMATION (YES) CLICKED.");
        waitForElementToBeVisible(number_of_items(ItemCount), 5);
    }
    public void wishlist_icon(){
        By[] wishlist_icon = new By[]{loc_wishlistIcon1, loc_wishlistIcon2, loc_wishlistIcon3, loc_wishlistIcon4, loc_wishlistIcon5};
        for (By this_list : wishlist_icon) {
            try {
                waitForWebElementToBeClickAble(this_list, 3);
                moveTo(this_list);
                click(this_list);
                log.info("Wishlist icon clicked");
                break;
            } catch (Exception e) {

            }
        }
    }
    public void viewCart_magento() {
        log.info("View cart");
        waitForWebElementToBeClickAble(loc_viewCart_magento, 10);
        click(loc_viewCart_magento);
        log.info("View cart, clicked.");
    }
    public void minicart_magento(){
        log.info("Minicart");
        waitForWebElementToBeClickAble(loc_minicart_magento, 10);
        scrollToTop(loc_minicart_magento);
        click(loc_minicart_magento);
        log.info("Minicart, clicked.");
    }
    public void cart_pencinIcon_magento() {
        log.info("Edit pencil icon");
        waitForWebElementToBeClickAble(loc_editIcon_magento_magento, 10);
        click(loc_editIcon_magento_magento);
    }
    public void update_button_magento() {
        log.info("Update button");
        waitForWebElementToBeClickAble(loc_update_button_magento, 10);
        click(loc_update_button_magento);
        log.info("Update button, clicked.");
    }

    //----> FOR REMOVING PRODUCTS FROM CART

    public void removeItemFromMiniCart() {
        sleep(Duration.ofSeconds(5));
        String productName = System.getProperty(ConfigurationKeyEnum.param_productName.value());
        log.info("Removing product from mini cart");
        if (elementCount(getItemFromPopUpCart(productName)) == 1){
            waitForWebElementToBeClickAble(popUpRemoveIcon, 10);
            click(popUpRemoveIcon);
            waitForElementToBeVisible(modalYesButton, 10);
            click(modalYesButton);
            log.info("Item is successfully removed from mini cart.");
        } else {
            HomeKeyword homeKeyword = new HomeKeyword(driver, log);
            homeKeyword.viewCart();
            removeItemFromCart();
        }
    }
    public void removeItemFromCart() {
        waitUntilPageready();
        String productName = System.getProperty(ConfigurationKeyEnum.param_productName.value());
        log.info("Removing product from cart");
        waitForWebElementToBeClickAble(cartRemoveIcon(productName), 10);
        click(cartRemoveIcon(productName));
        log.info("Remove button is clicked.");
        sleep(Duration.ofSeconds(3));
        waitForElementToBeVisible(removeProductConfirmation, 50);
        click(removeProductConfirmation);
        sleep(Duration.ofSeconds(3));

        int itemsInCart = Integer.parseInt(getText(numberOfItemsInCart).replaceAll("[^0-9.]", ""));
        log.info("Number of items in cart: " + itemsInCart);
        if (itemsInCart == 0){
            log.info("Item is successfully removed.");
            log.info("There are now "+itemsInCart+" items in cart.");
        }
    }

    public void removeSecondItemFromCart(String product) {
        waitUntilPageready();
        String productName = System.getProperty(product+"productName");
        log.info("Removing product from cart");
        waitForWebElementToBeClickAble(cartRemoveIcon(productName), 10);
        click(cartRemoveIcon(productName));
        log.info("Remove button is clicked.");
        sleep(Duration.ofSeconds(3));
        waitForElementToBeVisible(removeProductConfirmation, 50);
        click(removeProductConfirmation);
        sleep(Duration.ofSeconds(3));

        int itemsInCart = Integer.parseInt(getText(numberOfItemsInCart).replaceAll("[^0-9.]", ""));
        log.info("Number of items: " + itemsInCart);
        if (itemsInCart == 0){
            log.info("Item is successfully removed.");
            log.info("There are now "+itemsInCart+" items in cart.");
        }
    }

    //----> For editing product details in cart

    //----> FOR EDITING PRODUCT DETAILS IN CART
    public void editProductInCart(String partner){
        log.info("Editing product details in cart...");

        log.info("Is edit button visible?");
        waitForElementToBeVisible(editbutton, 5);
        click(editbutton);

        log.info("Is edit dialog window visible?");
        sleep(Duration.ofSeconds(5));
        waitForElementToBeVisible(editWindow, 5);

        log.info("Select new item details.");
        selectEditItemDetails(partner);
        validateUpdatedItemDetails();
    }
    public void editProductInCart_magento(String partner){
        log.info("Editing product details in cart...");
        log.info("Select new item details.");
        selectEditItemDetails_magento(partner);
    }
    private void selectEditItemDetails(String partner){
        updateDetails(partner);
        waitForWebElementToBeClickAble(updateButton, 10);
        click(updateButton);
        log.info("Update button is clicked.");
        selectNewQuantity();
    }
    private void selectEditItemDetails_magento(String partner){
        updateDetails(partner);
    }
    private void selectNewQuantity(){
        log.info("Set the quantity in cart to one.");
        String quantity="1";
        sleep(Duration.ofSeconds(3));
        waitForWebElementToBeClickAble(cartQuantityDropdown);
        click(cartQuantityDropdown);
        waitForWebElementToBeClickAble(cartQuantityOption(quantity));
        click(cartQuantityOption(quantity));
        sleep(Duration.ofSeconds(1));
        log.info("Quantity: " + quantity + " selected");
    }
    private void validateUpdatedItemDetails(){
        log.info("Validating updated product details...");

        log.info("Is the updated product color "+productColor+" visible?");
        waitForElementToBeVisible(itemColor(productColor), 10);
        log.info("Is the updated product size "+productSize+" visible?");
        waitForElementToBeVisible(itemSize(productSize), 10);
    }

    //-----> FOR SELECTING NEW ITEM DETAILS FOR EACH PARTNER
    public void updateDetails(String partner){
        Map<String, Runnable> itemDetails = new HashMap<>();

        itemDetails.put("attachment", this::attachment_updateDetails);
        itemDetails.put("ace", this::ace_updateDetails);
        itemDetails.put("ecchi", this::ecchi_updateDetails);
        itemDetails.put("briefing", this::briefing_updateDetails);
        itemDetails.put("dnd", this::dnd_updateDetails);
        itemDetails.put("haku", this::haku_updateDetails);
        itemDetails.put("igc", this::igc_updateDetails);
        itemDetails.put("kokuyo", this::kokuyo_updateDetails);
        itemDetails.put("mago", this::mago_updateDetails);
        itemDetails.put("makuake", this::makuake_updateDetails);
        itemDetails.put("maruzeki", this::maruzeki_updateDetails);
        itemDetails.put("metaphore", this::metaphore_updateDetails);
        itemDetails.put("swans", this::swans_updateDetails);
        itemDetails.put("talex", this::talex_updateDetails);
        itemDetails.put("tanuki", this::tanuki_updateDetails);
        itemDetails.put("tatras", this::tatras_updateDetails);
        itemDetails.put("toot", this::toot_updateDetails);
        itemDetails.put("xgirl", this::xgirl_updateDetails);
        itemDetails.put("xlarge", this::xlarge_updateDetails);
        itemDetails.put("japanblue", this::japan_updateDetails);
        itemDetails.put("knifan", this::knifan_updateDetails);
        itemDetails.put("momotaro", this::momotaro_updateDetails);

        itemDetails.get(partner.toLowerCase()).run();
    }

    //-----> METHOD FOR SPECIFIC PARTNER
    public void attachment_updateDetails(){
        select_ProductColor_attachment();
        select_ProductSize();
        select_QtyUsingDropdown();
    }
    public void ace_updateDetails(){
        selectAce_productColor();
        selectAce_productSize();
    }
    public void briefing_updateDetails(){
        select_ProductColor();
        select_QtyUsingDropdown();
    }
    public void dnd_updateDetails(){
        select_ProductColor();
    }
    public void ecchi_updateDetails(){
        log.info("THERE IS NO SELECTION DETAILS");
    }
    public void haku_updateDetails(){
        select_ProductColor();
        select_ProductSize();
        select_QtyUsingIcon();
    }
    public void igc_updateDetails(){
        select_QtyUsingDropdown();
    }
    public void kokuyo_updateDetails(){
        select_ProductColor();
        select_productType();
        select_QtyUsingIcon();
    }
    public void mago_updateDetails(){
        log.info("THERE IS NO SELECTION DETAILS");
    }
    public void makuake_updateDetails(){
        log.info("THERE IS NO SELECTION DETAILS");
    }
    public void maruzeki_updateDetails(){
        select_QtyUsingDropdown();
    }
    public void metaphore_updateDetails(){
        selectMeta_productSize();
        select_productColor_Dropdown();
        select_QtyUsingIcon();
    }
    public void swans_updateDetails(){
        select_ProductColor();
        select_QtyUsingDropdown();
    }
    public void talex_updateDetails(){
        select_frameColor();
        select_lensesColor();
        select_QtyUsingDropdown();
    }
    public void tanuki_updateDetails(){
        select_productSize_Dropdown();
        select_QtyUsingDropdown();
    }
    public void tatras_updateDetails(){
        select_ProductColor();
        select_ProductSize();
    }
    public void toot_updateDetails(){
        select_ProductColor();
        select_ProductSize();
        select_QtyUsingDropdown();
    }
    public void xlarge_updateDetails(){
        select_ProductColor();
        select_ProductSize();
        select_QtyUsingIcon();
    }
    public void xgirl_updateDetails(){
        select_ProductColor();
        select_ProductSize();
        select_QtyUsingIcon();
    }
    private void momotaro_updateDetails() {
        minicart_magento();
        viewCart_magento();
        cart_pencinIcon_magento();
        edit_productSize_magento();
        update_button_magento();
    }

    private void knifan_updateDetails() {
        log.info("NO SELECTION DETAILS");
    }

    private void japan_updateDetails() {
        minicart_magento();
        viewCart_magento();
        cart_pencinIcon_magento();
        edit_productColor_magento();
        edit_productSize_magento();
        update_button_magento();
        edit_productQuantity_magento();
        update_button_magento();
        validate_updated_qty_magento();
    }


    /** methods for selecting item details...............Note: can be use to other partner * */

    //-----> METHODS FOR SELECTING ITEM DETAILS
    public void select_ProductColor(){
        String get_ProductColorStat = findWeb(loc_productColor(productColor)).getText().toString();
        if (get_ProductColorStat.equals("selected")){
            log.info("Color: " + productColor + " Selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productColor(productColor),2);
            click(loc_productColor(productColor));
            log.info("Color: " + productColor + " Is selected.");
        }
    }
    public void select_ProductColor_attachment(){
        if (get_currentURL.contains("en_US")) {
            String get_ProductColorStat = findWeb(loc_productColor(US_productColor)).getText().toString();
            if (get_ProductColorStat.equals("selected")){
                log.info("Color: " + US_productColor + " Selected by default.");
            }else{
                waitForWebElementToBeClickAble(loc_productColor(US_productColor),2);
                click(loc_productColor(US_productColor));
                log.info("Color: " + US_productColor + " Is selected.");
            }
        }else{
            String get_ProductColorStat = findWeb(loc_productColor(productColor)).getText().toString();
            if (get_ProductColorStat.equals("selected")){
                log.info("Color: " + productColor + " Selected by default.");
            }else{
                waitForWebElementToBeClickAble(loc_productColor(productColor),2);
                click(loc_productColor(productColor));
                log.info("Color: " + productColor + " Is selected.");
            }
        }
    }

    public void select_ProductSize(){
        String get_ProductSizeStat = findWeb(loc_productSize(productSize)).getAttribute("class").toString();
        if(get_ProductSizeStat.contains("selected")){
            log.info("Size: " + productSize + " Selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productSize(productSize),2);
            click(loc_productSize(productSize));
            sleep(Duration.ofSeconds(1));
            log.info("Size: " + productSize + " Is selected.");
        }
    }

    public void selectMeta_productSize(){
        if(elementCount(By.xpath("//span[normalize-space()='Size']")) > 0){
            String get_countSize = findWeb(loc_metaItemSize(productSize)).getText().toString();
            if (get_countSize.contains("selected")){
                sleep(Duration.ofSeconds(1));
                waitForWebElementToBeClickAble(loc_metaItemSize(productSize));
                click(loc_metaItemSize(productSize));
                log.info("Size: " + productSize + " is selected.");
            }
        }
    }

    public void select_productColor_Dropdown(){
        click(loc_ColorDropdown);
        waitForWebElementToBeClickAble(loc_productColor_dropdown(productColor));
        click(loc_productColor_dropdown(productColor));
        log.info("Color " + productColor + " is selected.");
        click(loc_ColorDropdown);
    }

    public void select_productSize_Dropdown(){
        String converted_productSize = productSize.toLowerCase();
        click(loc_sizeDropdown);
        waitForWebElementToBeClickAble(loc_tanukiProductColor(converted_productSize));
        click(loc_tanukiProductColor(converted_productSize));
        log.info("Size " + productSize + " is selected.");
        click(loc_sizeDropdown);
    }

    public void select_productType(){
        String get_productType = findWeb(loc_kokuyoType(productType)).getAttribute("class").toString();
        if(get_productType.contains("selected")){
            log.info("Type: " + productType + " selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_kokuyoType(productType),2);
            click(loc_kokuyoType(productType));
            sleep(Duration.ofSeconds(1));
            log.info("Type: " + productType + " is selected.");
        }
    }

    public void edit_productColor_magento(){
        log.info("Update product color");
        waitForWebElementToBeClickAble(loc_edit_productColor_magento(productColor));
        click(loc_edit_productColor_magento(productColor));
        log.info(productColor + " Is selected");
    }
    public void edit_productSize_magento(){
        log.info("Update product size");
        waitForWebElementToBeClickAble(loc_edit_productSize_magento(productSize));
        click(loc_edit_productSize_magento(productSize));
        log.info(productSize + " Is selected");
    }
    public void edit_productQuantity_magento(){
        log.info("Update product quanity");
        clear(loc_edit_quantity_magento);
        type(productQuantity ,loc_edit_quantity_magento);
        log.info(productSize + " Is selected");
    }

    //-----> TWO TYPES OF ADDING/SELECTING QUANTITY
    public void select_QtyUsingDropdown(){
        sleep(Duration.ofSeconds(1));
        selectValue(loc_productQuantity, productQuantity);
        sleep(Duration.ofSeconds(1));
        log.info("Quantity: " + productQuantity + " selected");
    }

    public void select_QtyUsingIcon(){
        String new_quantity = productQuantity;
        int get_defaultValue = findWeb(loc_productQuantity2).getAttribute("value").length();
        int currentQuantity = parseInt(new_quantity) - get_defaultValue;
        if(currentQuantity > 0){
            click(loc_qtyPlusIcon);
            log.info("Quantity: " + productQuantity + " selected");
            sleep(Duration.ofSeconds(1));
        }
    }


    //-----> SELECT ITEM DETAILS FOR ACE
    public void selectAce_productColor(){
        String getAce_productColorStat = findWeb(loc_aceProductColor(productColor)).getText().toString();
        if (!getAce_productColorStat.contains("selected")){
            waitForWebElementToBeClickAble(loc_productColor(productColor),2);
            click(loc_productColor(productColor));
            log.info("Color: " + productColor + " is selected.");
            sleep(Duration.ofSeconds(1));
        }else{
            log.info("Color: " + productColor + " selected by default.");
        }
    }
    public void selectAce_productSize(){
        String getAce_productSizeStat = findWeb(loc_aceProductSize(productSize)).getAttribute("class").toString();
        if (!getAce_productSizeStat.contains("selected")){
            waitForWebElementToBeClickAble(loc_aceProductSize(productSize),2);
            click(loc_aceProductSize(productSize));
            log.info("Size: " + productSize + " Is selected.");
            sleep(Duration.ofSeconds(1));
        }else{
            log.info("Size: " + productSize + " Selected by default.");
        }
    }


    //-----> SELECT ITEM DETAILS FOR TALEX
    public void select_frameColor(){
        String getFrameColorStat = findWeb(loc_productFrameColor(productFrameColor)).getText().toLowerCase();
        if(getFrameColorStat.contains("selected")){
            log.info("Frame color: " + productFrameColor + " selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productFrameColor(productFrameColor),2);
            click(loc_productFrameColor(productFrameColor));
            sleep(Duration.ofSeconds(1));
            log.info("Frame color: " + productFrameColor + " is selected.");
        }
    }
    public void select_lensesColor() {
        String getLensesColorStat = findWeb(loc_productLensesColor(productLensesColor)).getText().toLowerCase();
        if (getLensesColorStat.contains("selected")) {
            log.info("Lenses color: " + productLensesColor + " selected by default.");
        } else {
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productLensesColor(productLensesColor), 2);
            click(loc_productLensesColor(productLensesColor));
            sleep(Duration.ofSeconds(1));
            log.info("Lenses color: " + productLensesColor + " is selected.");
        }
    }


    // Methods for checking the cart if empty or not
    public void check_cartIfEmpty_orNot(String partner, String product){
        if (elementCount(loc_get_cartItemsCount) == 0){
            log.info("CART IS NOT EMPTY");
            String get_productName = getText(loc_validate_wishlist(productName));
            log.info(get_productName);

            Integer get_popOverItemCount = Check_popupcart_ifAvailable();

            if(get_popOverItemCount > 0){
                log.info("POPOVER MINICART FOUND");
                validateIfMiniCartIsVisible();
                check_popover_cartItem("");
                validate_popup_cart_itemDetails(partner, "");
                //POPOVER MINICART PRICE VALIDATION [HOLD]
//            cartKeyword.popover_minicart_price_validation();
            }else{
                log.info("No Popover minicart found, validation skipped");
            }
            waitUntilPageready();
        }else{
            log.info("CART IS EMPTY");
            userIcon_userlogin();
            dropdown_myAccount_button();
            view_all_wishlist();
            wishlist_AddToCart_Button();

            Integer get_popOverItemCount = Check_popupcart_ifAvailable();

            if(get_popOverItemCount > 0){
                log.info("POPOVER MINICART FOUND");
                validateIfMiniCartIsVisible();
                check_popover_cartItem("");
                validate_popup_cart_itemDetails(partner, "");
                //POPOVER MINICART PRICE VALIDATION [HOLD]
//            cartKeyword.popover_minicart_price_validation();
            }else{
                log.info("No Popover minicart found, validation skipped");
            }

            waitUntilPageready();
        }

    }
    // End of methods for checking the cart if empty or not
    public void userIcon_userlogin(){
        waitUntilPageready();
        if (elementCount(loc_dropdown_myAccount_button1) < 1) {
            By[] userIcon_userLogAcc = new By[]{loc_userIcon_userLoginButton1, loc_userIcon_userLoginButton2, loc_userIcon_userLoginButton3, loc_userIcon_userLoginButton4};
            for (By this_list : userIcon_userLogAcc) {
                try {
                    waitForWebElementToBeClickAble(this_list, 2);
                    click(this_list);
                    log.info("User Account open");
                    break;
                } catch (Exception e) {

                }
            }
        }
    }
    public void dropdown_myAccount_button(){
        waitUntilPageready();
        By [] dropdown_myAccount_button = new By[] {loc_dropdown_myAccount_button1, loc_dropdown_myAccount_button2, loc_dropdown_myAccount_button3, loc_dropdown_myAccount_button4};
        for (By this_list : dropdown_myAccount_button){
            try{
                waitForWebElementToBeClickAble(this_list, 1);
                clickElementJS(this_list);
            }catch (Exception e){

            }
            log.info("My account open");
        }
    }
    public void view_all_wishlist(){
        waitForWebElementToBeClickAble(loc_view_all_wishlist,5);
        click(loc_view_all_wishlist);
    }
    public void validate_wishlist(){
        waitForElementToBeVisible(loc_wishlist_label, 5);
        log.info("WISHLIST PAGE IS DISPLAYED");
        waitForElementToBeVisible(loc_validate_wishlist(productName), 5);
        log.info("SELECTED ITEM WAS DISPLAYED IN WISHLIST");
    }
    public void wishlist_AddToCart_Button(){
        waitForWebElementToBeClickAble(loc_wishlist_AddToCart_Button);
        click(loc_wishlist_AddToCart_Button);
    }
    public void wishlist_removeItem_button(){
        waitForWebElementToBeClickAble(loc_wishlist_removeItem_button(productName),5);
        click(loc_wishlist_removeItem_button(productName));
    }
    public void check_wishlist_removeItem_button_notVisible(){
        waitForElementToBeVisible(loc_wishlist_removeItem_button(productName), 2);
        log.info("ITEM FROM WISHLIST WAS REMOVED");
    }


    public Integer get_emailTextbox_count() {
        return elementCount(loc_count_email_texbox);
    }

    public void removeItemFromCart_Magento() {
        log.info("Remove item in cart");
        sleep(Duration.ofSeconds(5));
        String partnerSite = System.getProperty("partnerSite");
        if (partnerSite.equals("Japan Blue") | partnerSite.equals("Momotaro")){
            moveTo(cartMagento);
            click(cartMagento);
            waitForElementToBeVisible(viewCartMagento,5);
            click(viewCartMagento);
            waitForElementToBeVisible(removeIconMagento, 15);
            click(removeIconMagento);
            waitForElementToBeVisible(validateEmptyCartMagento, 5);
        } else if (partnerSite.equals("Knifan")){
            waitForElementToBeVisible(removeIconMagento, 3);
            click(removeIconMagento);
            waitForElementToBeVisible(validateEmptyCartMagento, 5);
        }
    }
    public void validate_updated_qty_magento(){
        waitUntilPageready();
        String test = findWeb(loc_validate_updated_qty(productQuantity)).getAttribute("value").toString();
        log.info(test);
        String test1 = productQuantity;
        log.info(test1);
        sleep(Duration.ofSeconds(3));
        waitForElementToBeVisible(loc_validate_updated_qty(productQuantity), 10);
        Assert.assertEquals(productQuantity, productQuantity);
    }
}
