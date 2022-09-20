package framework.keyword;

import framework.pages.ItemDetailsPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @author alexander.v.pangilinan
 * */

public class ItemDetailsKeyword extends ItemDetailsPage {
    public ItemDetailsKeyword(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void selectItemDetails(String partner, String product){
        hashmap_itemDetails(partner,product);
    }

    public void hashmap_itemDetails(String partner,String product){
        Map<String, Runnable> itemDetails = new HashMap<>();

        itemDetails.put("attachment", () -> attachment_itemDetails(product));
        itemDetails.put("ace", () -> ace_itemDetails(product));
        itemDetails.put("ecchi", () -> ecchi_itemDetails(product));
        itemDetails.put("briefing", () -> briefing_itemDetails(product));
        itemDetails.put("dnd", () -> dnd_itemDetails(product));
        itemDetails.put("haku", () -> haku_itemDetails(product));
        itemDetails.put("igc", () -> igc_itemDetails(product));
        itemDetails.put("kokuyo", () -> kokuyo_itemDetails(product));
        itemDetails.put("mago", () -> mago_itemDetails(product));
        itemDetails.put("makuake", () -> makuake_itemDetails(product));
        itemDetails.put("maruzeki", () -> maruzeki_itemDetails(product));
        itemDetails.put("metaphore", () -> metaphore_itemDetails(product));
        itemDetails.put("swans", () -> swans_itemDetails(product));
        itemDetails.put("talex", () -> talex_itemDetails(product));
        itemDetails.put("tanuki", () -> tanuki_itemDetails(product));
        itemDetails.put("tatras", () -> tatras_itemDetails(product));
        itemDetails.put("toot", () -> toot_itemDetails(product));
        itemDetails.put("xgirl", () -> xgirl_itemDetails(product));
        itemDetails.put("xlarge", () -> xlarge_itemDetails(product));
        itemDetails.put("japanblue", () -> japan_itemDetails(product));
        itemDetails.put("knifan", () -> knifan_itemDetails(product));
        itemDetails.put("momotaro", () -> momotaro_itemDetails(product));

        itemDetails.get(partner.toLowerCase()).run();
    }

    private void momotaro_itemDetails(String product) {
        select_ProductColor_Momotaro(product);
        select_ProductSize(product);
    }

    private void knifan_itemDetails(String product) {
        log.info("NO SELECTION DETAILS");
    }

    private void japan_itemDetails(String product) {
        select_ProductColor(product);
        select_ProductSize(product);
    }

    public void attachment_itemDetails(String product){
        select_ProductColor_attachment(product);
        select_ProductSize(product);
        select_QtyUsingDropdown(product);
    }
    public void ace_itemDetails(String product){
        selectAce_productSize(product);
        selectAce_productColor(product);
    }
    public void briefing_itemDetails(String product){
        select_ProductColor(product);
        select_QtyUsingDropdown(product);
    }
    public void dnd_itemDetails(String product){
        select_ProductColor(product);
        select_QtyUsingDropdown(product);
    }
    public void ecchi_itemDetails(String product){
        select_QtyUsingIcon(product);
    }
    public void haku_itemDetails(String product){
        select_ProductColor(product);
        select_ProductSize(product);
        select_QtyUsingIcon(product);
    }
    public void igc_itemDetails(String product){
        select_QtyUsingDropdown(product);
    }
    public void kokuyo_itemDetails(String product){
        select_ProductColor(product);
        select_productType(product);
        select_QtyUsingIcon(product);
    }
    public void mago_itemDetails(String product){
        log.info("NO SELECTION DETAILS");
    }
    public void makuake_itemDetails(String product){
        log.info("NO SELECTION DETAILS");
    }
    public void maruzeki_itemDetails(String product){
        select_QtyUsingDropdown(product);
    }
    public void metaphore_itemDetails(String product){
        selectMeta_productSize(product);
        select_productColor_Dropdown(product);
        select_QtyUsingIcon(product);
    }
    public void swans_itemDetails(String product){
        select_ProductColor(product);
        select_QtyUsingDropdown(product);
    }
    public void talex_itemDetails(String product){
        select_frameColor(product);
        select_lensesColor(product);
        select_QtyUsingDropdown(product);
    }
    public void tanuki_itemDetails(String product){
        select_productSize_Dropdown(product);
        select_QtyUsingDropdown(product);
    }
    public void tatras_itemDetails(String product){
        select_ProductColor(product);
        select_ProductSize(product);
    }
    public void toot_itemDetails(String product){
        select_ProductColor(product);
        select_ProductSize(product);
        select_QtyUsingDropdown(product);
    }
    public void xlarge_itemDetails(String product){
        select_ProductColor(product);
        select_ProductSize(product);
        select_QtyUsingIcon(product);
    }
    public void xgirl_itemDetails(String product){
        select_ProductColor(product);
        select_ProductSize(product);
        select_QtyUsingIcon(product);
    }

    public void select_ProductColor_Momotaro(String product){
        String productColor = System.getProperty(product+"productColor");
        if (elementCount(colorSelected)==1){
            log.info("Color: " + productColor + " Selected by default.");
        }else {
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productColor(productColor), 2);
            click(loc_productColor(productColor));
            log.info("Color: " + productColor + " Is selected.");
        }
    }
    public void select_ProductColor(String product){
        String productColor = System.getProperty(product+"productColor");

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
    public void select_ProductColor_attachment(String product){
        String US_productColor = System.getProperty(product+"US_productColor");
        String productColor = System.getProperty(product+"productColor");
        String get_currentURL = driver.getCurrentUrl().toString();

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
    public void select_ProductSize(String product){
        String productSize = System.getProperty(product+"productSize");

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
    public void selectMeta_productSize(String product){
        String productSize = System.getProperty(product+"productSize");

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
    public void select_productColor_Dropdown(String product){
        String productColor = System.getProperty(product+"productColor");

        click(loc_ColorDropdown);
        waitForWebElementToBeClickAble(loc_productColor_dropdown(productColor),3);
        click(loc_productColor_dropdown(productColor));
        log.info("Color " + productColor + " is selected.");
        click(loc_ColorDropdown);
    }
    public void select_productSize_Dropdown(String product){
        String productSize = System.getProperty(product+"productSize");

        String converted_productSize = productSize.toLowerCase();
        waitForWebElementToBeClickAble(loc_sizeDropdown);
        click(loc_sizeDropdown);
        waitForWebElementToBeClickAble(loc_tanukiProductSize(converted_productSize),3);
        click(loc_tanukiProductSize(converted_productSize));
        log.info("Size " + productSize + " is selected.");
        click(loc_sizeDropdown);
    }
    public void select_productType(String product){
        String productType = System.getProperty(product+"productType");

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

    //TWO TYPES OF SELECTING/ADDING QUANTITY
    public void select_QtyUsingDropdown(String product){
        String productQuantity = System.getProperty("productQuantity");

        sleep(Duration.ofSeconds(1));
        selectValue(loc_productQuantity, productQuantity);
        sleep(Duration.ofSeconds(1));
        log.info("Quantity: " + productQuantity + " selected");
    }
    public void select_QtyUsingIcon(String product){
        String productQuantity = System.getProperty("productQuantity");

        sleep(Duration.ofSeconds(1));
        String new_quantity = productQuantity;
        int get_defaultValue = findWeb(loc_productQuantity2).getAttribute("value").length();
        int currentQuantity = parseInt(new_quantity) - get_defaultValue;
        if(currentQuantity > 0){
            moveTo(loc_qtyPlusIcon);
            waitForWebElementToBeClickAble(loc_qtyPlusIcon);
            click(loc_qtyPlusIcon);
            log.info("Quantity: " + productQuantity + " selected");
            sleep(Duration.ofSeconds(1));
        }
    }
    //SELECT ITEM DETAILS FOR ACE
    public void selectAce_productColor(String product){
        String productColor = System.getProperty(product+"productColor");

        String getAce_productColorStat = findWeb(loc_aceProductColor(productColor)).getText().toString();
        log.info(getAce_productColorStat);

        if (!getAce_productColorStat.contains("selected")){
            waitForWebElementToBeClickAble(loc_productColor(productColor),2);
            click(loc_productColor(productColor));
            log.info("Color: " + productColor + " Is selected.");
            sleep(Duration.ofSeconds(1));
        }else{
            log.info("Color: " + productColor + " Selected by default.");
        }
    }
    public void selectAce_productSize(String product){
        String productSize = System.getProperty(product+"productSize");

        String getAce_productSizeStat = findWeb(loc_aceProductSize(productSize)).getAttribute("class").toString();
        log.info(getAce_productSizeStat);
        if (!getAce_productSizeStat.contains("selected")){
            waitForWebElementToBeClickAble(loc_aceProductSize(productSize),2);
            click(loc_aceProductSize(productSize));
            log.info("Size: " + productSize + " Is selected.");
            sleep(Duration.ofSeconds(1));
        }else{
            log.info("Size: " + productSize + " Selected by default.");
        }
    }
    //SELECT ITEM DETAILS FOR TALEX
    public void select_frameColor(String product){
        String productFrameColor = System.getProperty(product+"productFrameColor");

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
    public void select_lensesColor(String product) {
        String productLensesColor = System.getProperty("productLensesColor");
        String getLensesColorStat = findWeb(loc_productLensesColor(productLensesColor)).getText().toLowerCase();
        if (getLensesColorStat.contains("selected")) {
            log.info("Lenses color: " + productLensesColor + " Selected by default.");
        } else {
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productLensesColor(productLensesColor), 2);
            click(loc_productLensesColor(productLensesColor));
            sleep(Duration.ofSeconds(1));
            log.info("Lenses color: " + productLensesColor + " Is selected.");
        }
    }
}