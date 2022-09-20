package framework.keyword;

import framework.commonFunctions.ConfigurationKeyEnum;
import framework.pages.PageNavigationPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.parseInt;

/**
 * @author alexander.v.pangilinan
 */

public class PageNavigationKeyword extends PageNavigationPage {

    public PageNavigationKeyword(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //region -----> Validate Price and Img for each category
    public void ValidatePriceImgEachCategory(){
        log.info("Validating price and image for each category...");
        String partnersite = System.getProperty("partnerSite");
        if (partnersite.equals("Tatras") | partnersite.equals("haku-clothing") | partnersite.equals("Kokuyo")){
            TaHaKoCategory();
        } else if (partnersite.equals("Xlarge") | partnersite.equals("Talex")) {
            XlargeTalexCategory();
        } else if (partnersite.equals("ecchiTOKYO")) {
            ecchiCategory();
        } else {
            checkCategory();
        }
    }

    private void ecchiCategory(){
        log.info("Checking Women's Category");
        click(womenCategory);
        int categoryWomen = elementCount(ecchiWomenCategory);
        log.info("Number of Categories: "+categoryWomen);
        for (int x = 1; x <= categoryWomen; x++) {
            String Cat = getText(ecchiWomenCategory(x));
            log.info("Category: "+Cat);
            click(ecchiWomenCategory(x));
            validatePriceAndImages();
            sleep(Duration.ofSeconds(3));
        }
        click(menCategory);
        log.info("Checking Men's Category");
        int categoryMen = elementCount(ecchiMenCategory);
        log.info("Number of Categories: "+categoryMen);
        for (int x = 1; x <= categoryMen; x++) {
            String Cat = getText(ecchiMenCategory(x));
            log.info("Category: "+Cat);
            click(ecchiMenCategory(x));
            validatePriceAndImages();
            sleep(Duration.ofSeconds(3));
        }
    }
    private void TaHaKoCategory(){
        int category = elementCount(tatrasHakuCategory);
        log.info("Number of Categories: "+category);
        for (int x = 1; x <= category; x++) {
            String Cat = getText(tatrasHakuCategory(x));
            log.info("Category: "+Cat);
            click(tatrasHakuCategory(x));
			validatePriceAndImages();
            sleep(Duration.ofSeconds(3));
        }
    }
    public void XlargeTalexCategory(){
        int category = elementCount(xlargeCategory);
        log.info("Number of Categories: "+category);
        for (int x = 1; x <= category; x++) {
            String Cat = getText(xlargeCategory(x));
            log.info("Category: "+Cat);
            moveTo(xlargeCategory(x));
            click(xlargeSubCategory(x));
            validatePriceAndImages();
            sleep(Duration.ofSeconds(3));
        }
    }
    private void checkCategory(){
        int category1 = elementCount(countCategory);
        int category2 = elementCount(countCategory2);
        int category3 = elementCount(countCategory3);
        if (category1>=1){
            selectCategory1();
        } else if (category2>=1){
            selectCategory2();
        } else if (category3>=1) {
            selectCategory3();
        }
    }
    private void selectCategory3() {
        int category3 = elementCount(countCategory3);
        log.info("Number of Categories: "+category3);
        for (int x = 1; x <= category3; x++) {
            String Cat = getText(category3(x));
            log.info("Category: "+Cat);
            click(category3(x));
            validatePriceAndImages();
            sleep(Duration.ofSeconds(3));
        }
    }
    private void selectCategory2() {
        int category2 = elementCount(countCategory2);
        log.info("Number of Categories: "+category2);
        for (int x = 1; x <= category2; x++) {
            String Cat = getText(category2(x));
            log.info("Category: "+Cat);
            click(category2(x));
            validatePriceAndImages();
            sleep(Duration.ofSeconds(3));
        }
    }
    private void selectCategory1() {
        int category1 = elementCount(countCategory);
        log.info("Number of Categories: "+category1);
        for (int x = 1; x <= category1; x++) {
            String Cat = getText(category(x));
            log.info("Category: "+Cat);
            click(category(x));
            validatePriceAndImages();
            sleep(Duration.ofSeconds(3));
        }
    }

    //endregion


    //region -----> Filtering products
    String colorToFilter = System.getProperty("colorToFilter");
    String brandToFilter= System.getProperty("brandToFilter");
    String priceRangeToFilter=System.getProperty("priceRangeToFilter");

    public void applyFilter(String partner){
        moveTo(header);
        Map<String, Runnable> itemDetails = new HashMap<>();

        itemDetails.put("attachment", this::attachment_filter);
        itemDetails.put("ace", this::ace_filter);
        itemDetails.put("ecchi", this::ecchi_filter);
        itemDetails.put("briefing", this::briefing_filter);
        itemDetails.put("dnd", this::dnd_filter);
        itemDetails.put("haku", this::haku_filter);
        itemDetails.put("igc", this::igc_filter);
        itemDetails.put("kokuyo", this::kokuyo_filter);
        itemDetails.put("mago", this::mago_filter);
        itemDetails.put("makuake", this::makuake_filter);
        itemDetails.put("maruzeki", this::maruzeki_filter);
        itemDetails.put("metaphore", this::metaphore_filter);
        itemDetails.put("swans", this::swans_filter);
        itemDetails.put("talex", this::talex_filter);
        itemDetails.put("tanuki", this::tanuki_filter);
        itemDetails.put("tatras", this::tatras_filter);
        itemDetails.put("toot", this::toot_filter);
        itemDetails.put("xgirl", this::xgirl_filter);
        itemDetails.put("xlarge", this::xlarge_filter);

        itemDetails.get(partner.toLowerCase()).run();
    }

    //----> Methods per site
    public void xlarge_filter() {
        filter_Color2();
        filter_ValidateColor2();
    }
    private void xgirl_filter() {
        filter_Color2();
        filter_ValidateColor2();
    }
    private void toot_filter() {
        filter_Color2();
        click(applyFilter);
        sleep(Duration.ofSeconds(3));
        filter_ValidateColor2();
    }
    private void tatras_filter() {
        log.info("Filtering price...");
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");
        click(filterButton);
        filter_Price_Range();
        click(applyFilter);
        sleep(Duration.ofSeconds(3));
        filter_PriceRangeValidation();
    }
    private void tanuki_filter() {
        log.info("Filtering price...");
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,250)", "");
        filter_Price_Range();
        filter_PriceRangeValidation();
    }
    private void talex_filter() {
        filter_Color2();
        filter_ValidateColor2();
    }
    private void swans_filter() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0, 700)", "");
        filter_Price_Range();
        filter_PriceRangeValidation();
    }
    private void ecchi_filter() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");
        filter_Brand();
    }
    private void haku_filter() {
        log.info("Filtering price...");
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");
        moveTo(filter_PriceDropdown);
        click(filter_PriceDropdown);
        filter_Price_Range();
        filter_PriceRangeValidation();
    }
    private void mago_filter() {
        log.info("Filtering price...");
        moveTo(filter_PriceDropdown);
        click(filter_PriceDropdown);
        filter_Price_Range();
        filter_PriceRangeValidation();
    }
    private void maruzeki_filter() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");
        filter_Color2();
        filter_ValidateColor2();
    }
    private void metaphore_filter() {
        moveTo(filterButton);
        click(filterButton);
        filter_Price_Range();
        filter_PriceRangeValidation();
    }
    private void igc_filter(){ log.info("This site has no filter option."); }
    private void makuake_filter() { log.info("This site has no filter option."); }
    private void kokuyo_filter() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");
        filter_Price_Slider();
    }
    private void dnd_filter() { filter_Price_Slider(); }
    private void ace_filter() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");
        filter_Price_Slider(); }
    private void attachment_filter() { filter_Color(); }
    private void briefing_filter(){
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");
        filter_Price_Slider(); }



    //----> METHODS FOR FILTERING
    private void filter_Price_Range() {
        moveTo(filter_PriceRange(priceRangeToFilter));
        clickElementJS(filter_PriceRange(priceRangeToFilter));
        sleep(Duration.ofSeconds(8));
    }
    private void filter_PriceRangeValidation(){
        int productCount = elementCount(filter_productCard);
        float firstPriceValue = Float.parseFloat(getAttribute(filter_firstProduct, "content"));
        float lastPriceValue = Float.parseFloat(getAttribute(filter_lastProduct(productCount), "content"));
        log.info("First Product Price = "+firstPriceValue+"");
        log.info("Last Product Price = "+lastPriceValue+"");

        int minimumPriceRange = parseInt(System.getProperty("minimumPriceRange"));
        int maximumPriceRange = parseInt(System.getProperty("maximumPriceRange"));
        log.info("Minimum Price Range = "+minimumPriceRange+"");
        log.info("Maximum Price Range = "+maximumPriceRange+"");

        if (firstPriceValue>=minimumPriceRange && lastPriceValue<=maximumPriceRange){
            log.info("Filtered Products are validated.");
        } else throw new Error("Error validating filtered price!");
    }
    private void filter_Brand() {
        log.info("Filtering brand...");
        moveTo(filterButton);
        click(filterButton);
        moveTo(filter_BrandButton);
        moveTo(filter_BrandOption(brandToFilter));
        click(filter_BrandOption(brandToFilter));
        click(applyFilter);
        sleep(Duration.ofSeconds(3));

        //-----> Brand Filter Validation
        String validateBrand = getAttribute(validateBrandProduct, "data-gtm");
        if (validateBrand.contains(brandToFilter)){
            log.info("Filtered Products are validated.!");
        } else throw new Error("Error validating filtered price!");
    }
    private void filter_Price_Slider(){
        log.info("Filtering price...");
        moveTo(filter_PriceDropdown);
        click(filter_PriceDropdown);

        //-----> Perform to filter price via slider option
        WebElement leftSlider = driver.findElement(By.xpath("//div[@id='price-slider-range']//child::span[1]"));
        WebElement rightSlider = driver.findElement(By.xpath("//div[@id='price-slider-range']//child::span[2]"));
        Actions sliderAction = new Actions(driver);
        sliderAction.dragAndDropBy(leftSlider, 20, 0).perform();
        sliderAction.dragAndDropBy(rightSlider, -30, 0).perform();
        sleep(Duration.ofSeconds(5));

        //-----> Price Filter Validations
        log.info("Validating filtered price");
        int productCount = elementCount(filter_productCard);
        float firstPriceValue = Float.parseFloat(getAttribute(filter_firstProduct, "content"));
        float lastPriceValue = Float.parseFloat(getAttribute(filter_lastProduct(productCount), "content"));
        log.info("First Product Price = "+firstPriceValue+"");
        log.info("Last Product Price = "+lastPriceValue+"");

        if (driver.findElement(priceSlider).isDisplayed()){
            log.info("Slider is visible");
        } else {
            click(filter_PriceDropdown);
        }

        float minimumPriceRange = Float.parseFloat(getText(amountStart).replaceAll("[^0-9.]", ""));
        float maximumPriceRange = Float.parseFloat(getText(amountEnd).replaceAll("[^0-9.]", ""));
        log.info("Minimum Price Range = "+minimumPriceRange+"");
        log.info("Maximum Price Range = "+maximumPriceRange+"");

        if (firstPriceValue>=minimumPriceRange && lastPriceValue<=maximumPriceRange){
            log.info("Filtered Products are validated.");
        } else throw new Error("Error validating filtered price!");
    }
    private void filter_Color() {
        log.info("Filtering color..."+colorToFilter+"");
        moveTo(filterButton);
        click(filterButton);

        //-----> Set color to filter
        waitForElementToBeVisible(filter_ColorOption(colorToFilter), 20);
        moveTo(filter_ColorOption(colorToFilter));
        click(filter_ColorOption(colorToFilter));
        sleep(Duration.ofSeconds(5));

        //-----> Color Filter Validations
        String colorToLower = colorToFilter.toLowerCase();
        int checkFilteredProducts = elementCount(validateFilter_Color(colorToLower));
        int countProducts = elementCount(filter_productCard);
        if (checkFilteredProducts==countProducts){
            log.info("Filtered Products are validated.");
        } else throw new Error("Error validating filtered products");
    }
    private void filter_Color2() {
        log.info("Filtering color..."+colorToFilter+"");
        moveTo(filterColorButton);
        click(filterColorButton);

        //-----> Set color to filter
        waitForElementToBeVisible(filter_ColorOption(colorToFilter), 20);
        moveTo(filter_ColorOption(colorToFilter));
        clickElementJS(filter_ColorOption(colorToFilter));
        sleep(Duration.ofSeconds(5));
    }
    private void filter_ValidateColor2(){
        //-----> Color Filter Validations
        int checkFilteredProducts = elementCount(validateFilter_Color2(colorToFilter));
        int countProducts = elementCount(filter_productCard);
        log.info("Number of filtered products: "+checkFilteredProducts);
        log.info("Number of products in the list: "+countProducts);
        if (checkFilteredProducts==countProducts){
            log.info("Filtered Products are validated.");
        } else throw new Error("Error validating filtered products");
    }
    //endregion

    //region -----> Sorting products
    String partnersite = System.getProperty("partnerSite");
    String sortOption = System.getProperty("sortOption");
    public void productSorting(){
        if (partnersite.equals("IGC")){
            log.info("This site has no filter option");
        } else if (partnersite.equals("Makuake")){
            sortByDaysLeft();
            sortByDaysLeftValidation();
        }else {
            log.info(sortOption);
            sortByPrice();
            sortByPriceValidation();
        }
    }

    private void sortByPrice() {
        moveTo(header);
        moveTo(sortButton);
        click(sortButton);
        log.info("Sort Button clicked...");
        sleep(Duration.ofSeconds(5));
        waitForElementToBeVisible(sortOption(sortOption), 20);
        click(sortOption(sortOption));
        sleep(Duration.ofSeconds(5));
    }

    public void sortByPriceValidation_Sale(){
        int productCount = elementCount(productCard);
        float firstPriceValue = Float.parseFloat(getAttribute(firstPriceSale, "content"));
        float lastPriceValue = Float.parseFloat(getAttribute(lastPriceSale(productCount), "content"));
        log.info("First Product Price = "+firstPriceValue+"");
        log.info("Last Product Price = "+lastPriceValue+"");

        if (sortOption.equals("Price Low")){
            if(firstPriceValue<=lastPriceValue){
                log.info("The first product is less than the last product.");
                log.info("Price sorting Low to High is validated.");
            } else throw new Error("Error validating price sorting. The first product is not less than the last product.");

        } else if (sortOption.equals("Price High")){
            if (firstPriceValue>=lastPriceValue){
                log.info("The first product is greater than the last product.");
                log.info("Price sorting High to Low is validated.");
            } else throw new Error("Error validating price sorting. The first product is not greater than the last product.");
        }
    }
    private void sortByPriceValidation(){
        int productCount = elementCount(productCard);
        float firstPriceValue = Float.parseFloat(getAttribute(firstProduct, "content"));
        float lastPriceValue = Float.parseFloat(getAttribute(lastProduct(productCount), "content"));
        log.info("First Product Price = "+firstPriceValue+"");
        log.info("Last Product Price = "+lastPriceValue+"");

        if (sortOption.equals("Price Low")){
            if(firstPriceValue<=lastPriceValue){
                log.info("The first product is less than the last product.");
                log.info("Price sorting Low to High is validated.");
            } else throw new Error("Error validating price sorting. The first product is not less than the last product.");

        } else if (sortOption.equals("Price High")){
            if (firstPriceValue>=lastPriceValue){
                log.info("The first product is greater than the last product.");
                log.info("Price sorting High to Low is validated.");
            } else throw new Error("Error validating price sorting. The first product is not greater than the last product.");
        }
    }

    private void sortByDaysLeftValidation() {
        int productCount = elementCount(productCard);
        int firstProductDaysCount = Integer.parseInt(getText(firstProductDays).replaceAll("[^0-9.]", ""));
        int lastProductDaysCount = Integer.parseInt(getText(lastProductDays(productCount)).replaceAll("[^0-9.]", ""));
        log.info("First product days count: "+firstProductDaysCount+" Days Left");
        log.info("Last product days count: "+lastProductDaysCount+" Days Left");

        if (firstProductDaysCount>=lastProductDaysCount){
            log.info("Sorting by Days left is validated.");
        } else throw new Error("Error validating sorting by days left");
    }

    private void sortByDaysLeft() {
        moveTo(sortButton);
        click(sortButton);
        log.info("Sort Button clicked...");
        sleep(Duration.ofSeconds(5));
        waitForElementToBeVisible(sortOption(sortOption), 20);
        click(sortOption(sortOption));
        sleep(Duration.ofSeconds(5));
    }

    //endregion

    //region -----> Select an item from product list.
    public void selectProduct() {
        String productName = System.getProperty("productName");
        moveTo(productName(productName));
        clickElementJS(productName(productName));
        log.info(productName+" is selected.");
    }
    //endregion

    //region -----> For navigation in category tabs.
    public void FnavigateToCategoryTabs(){
        sleep(Duration.ofSeconds(3));
        moveTo(header);
        String[] strArr = new String[]{};
        strArr = System.getProperty("productCategory").split(">");
        log.info(strArr.length);

        if (strArr.length == 1) {
            click(product_Category(strArr[0]));
            log.info("Category "+strArr[0]+" is open.");
            waitUntilPageready();
        }else if (strArr.length == 2) {
            moveTo(product_Category(strArr[0]));
            waitForElementToBeVisible(product_Category(strArr[1]), 30);
            sleep(Duration.ofSeconds(5));
            click(product_Sub_Category(strArr[1]));
            log.info("Sub-category "+strArr[1]+" is open.");
            waitUntilPageready();
        }else if (strArr.length == 3){
            moveTo(product_Category(strArr[0]));
            moveTo(product_Sub_Category(strArr[1]));
            moveTo(product_Sub_Category2(strArr[2]));
            click(product_Sub_Category2(strArr[2]));
            log.info("Sub-category 2"+strArr[2]+" is open.");
            waitUntilPageready();
        }
    }
    //endregion

    //region -----> For validating product images.

    //-----> For validating image in products details.
    public void validateImageInProductDetails() {
        int countImage = elementCount(imageNumber);
        log.info("Item images count: "+countImage+"");

        for (int i = 1; i <= getElement(imageNumber).size(); i++) {
            String src = findWeb(itemimage(i)).getAttribute("src");
            URL url = null;
            int code = 0;

            try {
                url = new URL(src);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                code = connection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int responseCode = code;
            if (responseCode != 200) {
                throw new Error("Broken image found.");
            } else {
                log.info("The image is not broken. Status code "+responseCode+"");
            }
        }
    }

    //-----> For validating price contents
    public void validatePriceAndImages() {
        log.info("Checking price and image contents if null...");
        for (int x = 0; x <= 12; x++) {
            sleep(Duration.ofSeconds(10));
            moveTo(footer);
            if (elementCount(nullPrice) >= 1 || elementCount(nullImage) >= 1) {
                if (elementCount(nullPrice) >= 1) {
                    throw new Error("Error! Null content on price found!");
                } else if (elementCount(nullImage) >= 1) {
                    throw new Error("Error! Broken image found!");
                }
            } else {
                if (elementCount(buttonDisabled) >= 1) {
                    boolean isbuttonDisabled = getAttribute(buttonDisabled, "class").contains("disabled");
                    if (elementCount(loadMore) == 1 && !isbuttonDisabled) {
                        log.info("Passed. Loading more products...");
                        sleep(Duration.ofSeconds(10));
                        moveTo(loadMore);
                        waitForWebElementToBeClickAble(loadMore, 10);
                        click(loadMore);
                    } else {
                        log.info("All products has price contents and no broken images. Continuing the test...");
                        break;
                    }
                } else {
                    if (elementCount(loadMore) >= 1 && driver.findElement(loadMore).isDisplayed()) {
                        log.info("Passed. Loading more products...");
                        sleep(Duration.ofSeconds(10));
                        moveTo(loadMore);
                        waitForWebElementToBeClickAble(loadMore, 10);
                        click(loadMore);
                    } else {
                        log.info("All products has price contents and no broken images. Continuing the test...");
                        break;
                    }
                }

            }
        }
    }


    //-----> Load more (Loop)
    public void loadMoreLoop(){
        log.info("Performs load more action.");
        while (elementCount(loadMore)==1){
            sleep(Duration.ofSeconds(5));
            moveTo(loadMore);
            waitForWebElementToBeClickAble(loadMore, 10);
            click(loadMore);
            if (elementCount(loadMore)!=1){
                break;
            }
        }
    }

    //endregion -----> For validating product images.

    //region -----> For Validating Social Media Links.

    //-----> Validating social media links by site.
    public void validateSocialMediaLinks(String partner){
        log.info("Validating Social Media Links:");
        switch (partner) {
            case "ace":
            case "xgirl":
                validateFacebook();
                validateInstagram();
                break;
            case "briefing":
            case "xlarge":
            case "xetaphore":
            case "makuake":
            case "mago":
                validateFacebook();
                validateInstagram();
                validateTwitter();
                break;
            case "dnd":
            case "toot":
            case "igc":
                validateInstagramNavBack();
                break;
            case "ecchi":
                validateInstagramNavBack();
                validateTwitterNavBack();
                break;
            case "haku":
                validateInstagram();
                break;
            case "maruzeki":
                validateFacebook();
                validateInstagram();
                validateWeibo();
                break;
            case "swans":
            case "talex":
                validateInstagram();
                validateFacebook();
                validateYoutube();
                break;
            case "tanuki":
                validateFacebook();
                validateInstagram();
                validateTwitter();
                validatePinterest();
                break;
            case "tatras":
                validateFacebookNavBack();
                validateInstagramNavBack();
                validateYoutubeNavBack();
                break;
            default:
                log.info("This partner site has no social Media Links.");
                break;
        }
        moveTo(header);
    }

    //-----> Methods for each site.
    private void validatePinterest() {
        String mainWindow=driver.getWindowHandle();
        moveTo(footer);
        waitForWebElementToBeClickAble(pinterestLink, 30);
        click(pinterestLink);
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        sleep(Duration.ofSeconds(3));
        if (getTitle().contains("Pinterest")){
            log.info("Pinterest link is validated.");
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        else {
            throw new Error("Social Media Site not found.");
        }
    }
    private void validateYoutube() {
        String mainWindow=driver.getWindowHandle();
        moveTo(footer);
        for (int i = 1; i <= getElement(youtubeLink).size(); i++) {
            try {
                waitForElementToBeVisible(youtubeLink(i), 30);
                click(youtubeLink(i));
                ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(newTab.get(1));
                sleep(Duration.ofSeconds(3));
                if (getTitle().contains("YouTube")){
                    log.info("Youtube link is validated.");
                    driver.close();
                    driver.switchTo().window(mainWindow);
                }
                else {
                    throw new Error("Social Media Site not found.");
                }
            } catch (Exception ignored) {

            }
        }
    }
    private void validateWeibo() {
        String mainWindow=driver.getWindowHandle();
        moveTo(footer);
        waitForWebElementToBeClickAble(weiboLink, 30);
        click(weiboLink);
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        sleep(Duration.ofSeconds(10));
        if (getTitle().contains("微博")){
            log.info("Twitter link is validated.");
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        else {
            throw new Error("Social Media Site not found.");
        }
    }
    private void validateTwitter() {
        String mainWindow=driver.getWindowHandle();
        moveTo(footer);
        waitForWebElementToBeClickAble(twitterLink, 30);
        click(twitterLink);
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        sleep(Duration.ofSeconds(3));
        if (getTitle().contains("Twitter")){
            log.info("Twitter link is validated.");
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        else {
            throw new Error("Social Media Site not found.");
        }
    }
    public void validateFacebook() {
        String mainWindow=driver.getWindowHandle();
        moveTo(footer);
        for (int i = 1; i <= getElement(facebookLink).size(); i++) {
            try {
                waitForElementToBeVisible(facebookLink(i), 30);
                click(facebookLink(i));
                ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(newTab.get(1));
                sleep(Duration.ofSeconds(3));
                if(getTitle().contains("Facebook")){
                    log.info("Facebook link is validated.");
                    driver.close();
                    driver.switchTo().window(mainWindow);
                }
                else {
                    throw new Error("Social Media Site not found.");
                }
            } catch (Exception ignored) {

            }
        }
    }
    public void validateInstagram() {
        String mainWindow = driver.getWindowHandle();
        moveTo(footer);
        for (int i = 1; i <= getElement(instagramLink).size(); i++) {
            try {
                waitForElementToBeVisible(instagramLink(i), 30);
                click(instagramLink(i));
                ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(newTab.get(1));
                sleep(Duration.ofSeconds(3));
                if (getTitle().contains("Instagram")) {
                    log.info("Instagram Link is validated");
                    driver.close();
                    driver.switchTo().window(mainWindow);
                } else {
                    throw new Error("Social Media Site not found.");
                }
            } catch (Exception ignored) {

            }
        }
    }
    private void validateFacebookNavBack(){
        moveTo(footer);
        waitForWebElementToBeClickAble(facebookLink, 30);
        click(facebookLink);
        sleep(Duration.ofSeconds(3));
        if(getTitle().contains("Facebook")){
            log.info("Facebook link is validated.");
            driver.navigate().back();
        }
        else {
            throw new Error("Social Media Site not found.");
        }
    }
    private void validateInstagramNavBack(){
        moveTo(footer);
        waitForWebElementToBeClickAble(instagramLink, 30);
        click(instagramLink);
        sleep(Duration.ofSeconds(3));
        if(getTitle().contains("Instagram")){
            log.info("Instagram link is validated.");
            HomeKeyword homeKeyword = new HomeKeyword(driver, log);
            homeKeyword.openPartnerSite();
        }
        else {
            throw new Error("Social Media Site not found.");
        }
    }
    private void validateTwitterNavBack(){
        moveTo(footer);
        waitForWebElementToBeClickAble(twitterLink, 30);
        click(twitterLink);
        sleep(Duration.ofSeconds(3));
        if(getTitle().contains("Twitter")){
            log.info("Twitter link is validated.");
            driver.navigate().back();
        }
        else {
            throw new Error("Social Media Site not found.");
        }
    }
    private void validateYoutubeNavBack(){
        moveTo(footer);
        waitForWebElementToBeClickAble(youtubeLink, 30);
        click(youtubeLink);
        sleep(Duration.ofSeconds(3));
        if(getTitle().contains("YouTube")){
            log.info("Youtube link is validated.");
            driver.navigate().back();
        }
        else {
            throw new Error("Social Media Site not found.");
        }
    }


    //endregion -----> For Validating Social Media Links.

    //region -----> For navigating through item details

    public void navigateToItemDetails(String partner){
        Map<String, Runnable> itemDetails = new HashMap<>();

        itemDetails.put("attachment", this::attachment_itemDetails);
        itemDetails.put("ace", this::ace_itemDetails);
        itemDetails.put("ecchi", this::ecchi_itemDetails);
        itemDetails.put("briefing", this::briefing_itemDetails);
        itemDetails.put("dnd", this::dnd_itemDetails);
        itemDetails.put("haku", this::haku_itemDetails);
        itemDetails.put("igc", this::igc_itemDetails);
        itemDetails.put("kokuyo", this::kokuyo_itemDetails);
        itemDetails.put("mago", this::mago_itemDetails);
        itemDetails.put("makuake", this::makuake_itemDetails);
        itemDetails.put("maruzeki", this::maruzeki_itemDetails);
        itemDetails.put("metaphore", this::metaphore_itemDetails);
        itemDetails.put("swans", this::swans_itemDetails);
        itemDetails.put("talex", this::talex_itemDetails);
        itemDetails.put("tanuki", this::tanuki_itemDetails);
        itemDetails.put("tatras", this::tatras_itemDetails);
        itemDetails.put("toot", this::toot_itemDetails);
        itemDetails.put("xgirl", this::xgirl_itemDetails);
        itemDetails.put("xlarge", this::xlarge_itemDetails);

        itemDetails.get(partner.toLowerCase()).run();
    }

    //-----> METHOD FOR SPECIFIC PARTNER
    public void attachment_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductSize();
        select_ProductSize2();
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void ace_itemDetails(){
        selectAce_productColor();
        selectAce_productColor2();
        selectAce_productSize();
        selectAce_productSize2();
    }
    public void briefing_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductColor3();
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void dnd_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductColor3();
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void ecchi_itemDetails(){
        select_QtyUsingIcon();
        select_Qty2UsingIcon();
    }
    public void haku_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductColor3();
        select_ProductSize();
        select_ProductSize2();
        select_QtyUsingIcon();
        select_Qty2UsingIcon();
    }
    public void igc_itemDetails(){
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void kokuyo_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductColor3();
        select_productType();
        select_productType2();
        select_QtyUsingIcon();
        select_Qty2UsingIcon();
    }
    public void mago_itemDetails(){
        log.info("THERE IS NO SELECTION DETAILS");
    }
    public void makuake_itemDetails(){
        log.info("THERE IS NO SELECTION DETAILS");
    }
    public void maruzeki_itemDetails(){
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void metaphore_itemDetails(){
        selectMeta_productSize();
        selectMeta_productSize2();
        select_productColor_Dropdown();
        select_productColor2_Dropdown();
        select_QtyUsingIcon();
        select_Qty2UsingIcon();
    }
    public void swans_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductColor3();
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void talex_itemDetails(){
        select_frameColor();
        select_lensesColor();
        select_lensesColor2();
        select_lensesColor3();
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void tanuki_itemDetails(){
        select_productSize_Dropdown();
        select_productSize2_Dropdown();
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void tatras_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductSize();
        select_ProductSize2();
    }
    public void toot_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductColor3();
        select_ProductSize();
        select_ProductSize2();
        select_QtyUsingDropdown();
        select_Qty2UsingDropdown();
    }
    public void xlarge_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductSize();
        select_ProductSize2();
        select_QtyUsingIcon();
        select_Qty2UsingIcon();
    }
    public void xgirl_itemDetails(){
        select_ProductColor();
        select_ProductColor2();
        select_ProductSize();
        select_ProductSize2();
        select_QtyUsingIcon();
        select_Qty2UsingIcon();
    }


    //-----> METHODS FOR NAVIGATING THROUGH ITEM DETAILS

    //---->PARAMETERS
    String productType = System.getProperty("productType");
    String productType2 = System.getProperty("productType2");
    String productFrameColor = System.getProperty("productFrameColor");
    String productLensesColor = System.getProperty("productLensesColor");
    String productLensesColor2 = System.getProperty("productColor2");
    String productLensesColor3 = System.getProperty("productColor3");
    String productColor = System.getProperty("productColor");
    String productColor2 = System.getProperty("productColor2");
    String productColor3 = System.getProperty("productColor3");
    String productSize = System.getProperty("productSize");
    String productSize2 = System.getProperty("productSize2");
    String productQuantity = System.getProperty("productQuantity");
    String productQuantity2 = System.getProperty("productQuantity2");


    //region -----> FOR NAVIGATION IN COLOR (BUTTON)

    public void select_ProductColor(){
        String get_ProductColorStat = findWeb(loc_productColor(productColor)).getText().toString();
        if (get_ProductColorStat.equals("selected")){
            log.info("Color: " + productColor + " Selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForElementToBeVisible(loc_productColor(productColor),2);
            click(loc_productColor(productColor));
            sleep(Duration.ofSeconds(3));
            log.info("Color: " + productColor + " Is selected.");
        }
    }
    public void select_ProductColor2(){
        String get_ProductColorStat = findWeb(loc_productColor(productColor2)).getText().toString();
        if (get_ProductColorStat.equals("selected")){
            log.info("Color: " + productColor2 + " Selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForElementToBeVisible(loc_productColor(productColor2),2);
            click(loc_productColor(productColor2));
            sleep(Duration.ofSeconds(3));
            log.info("Color: " + productColor2 + " Is selected.");
        }
    }
    public void select_ProductColor3(){
        String get_ProductColorStat = findWeb(loc_productColor(productColor3)).getText().toString();
        if (get_ProductColorStat.equals("selected")){
            log.info("Color: " + productColor3 + " Selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForElementToBeVisible(loc_productColor(productColor3),2);
            click(loc_productColor(productColor3));
            sleep(Duration.ofSeconds(3));
            log.info("Color: " + productColor3 + " Is selected.");
        }
    }
    //endregion

    //region -----> FOR NAVIGATION IN COLOR (DROPDOWN)
    public void select_productColor_Dropdown(){
        click(loc_ColorDropdown);
        click(loc_productColor_dropdown(productColor));
        log.info("Color " + productColor + " is selected.");
        sleep(Duration.ofSeconds(5));
        click(loc_ColorDropdown);
    }
    public void select_productColor2_Dropdown(){
        click(loc_ColorDropdown);
        click(loc_productColor_dropdown(productColor2));
        log.info("Color " + productColor2 + " is selected.");
        sleep(Duration.ofSeconds(5));
        click(loc_ColorDropdown);
    }
    public void select_productColor3_Dropdown(){
        click(loc_ColorDropdown);
        click(loc_productColor_dropdown(productColor3));
        log.info("Color " + productColor3 + " is selected.");
        sleep(Duration.ofSeconds(5));
        click(loc_ColorDropdown);
    }
    //endregion -----> FOR NAVIGATION IN COLOR (DROPDOWN)

    //region -----> FOR NAVIGATION IN SIZES (BUTTON)
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
    public void select_ProductSize2(){
        String get_ProductSizeStat = findWeb(loc_productSize(productSize2)).getAttribute("class").toString();
        if(get_ProductSizeStat.contains("selected")){
            log.info("Size: " + productSize2 + " Selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productSize(productSize2),2);
            click(loc_productSize(productSize2));
            sleep(Duration.ofSeconds(1));
            log.info("Size: " + productSize2 + " Is selected.");
        }
    }
    //endregion

    //region -----> FOR NAVIGATION IN SIZES (DROPDOWN)
    public void select_productSize_Dropdown(){
        String converted_productSize = productSize.toLowerCase();
        click(loc_sizeDropdown);
        click(loc_tanukiProductColor(converted_productSize));
        log.info("Size " + converted_productSize + " is selected.");
        click(loc_sizeDropdown);
    }
    public void select_productSize2_Dropdown(){
        String converted_productSize = productSize2.toLowerCase();
        click(loc_sizeDropdown);
        click(loc_tanukiProductColor(converted_productSize));
        log.info("Size " + converted_productSize + " is selected.");
        click(loc_sizeDropdown);
    }
    //endregion

    //region -----> FOR NAVIGATION IN QUANTITY (DROPDOWN)
    public void select_QtyUsingDropdown(){
        sleep(Duration.ofSeconds(1));
        selectValue(loc_productQuantity, productQuantity);
        sleep(Duration.ofSeconds(1));
        log.info("Quantity: " + productQuantity + " selected");
    }
    public void select_Qty2UsingDropdown(){
        sleep(Duration.ofSeconds(1));
        selectValue(loc_productQuantity, productQuantity2);
        sleep(Duration.ofSeconds(1));
        log.info("Quantity: " + productQuantity2 + " selected");
    }
    //endregion

    //region -----> FOR NAVIGATION IN QUANTITY (ICON)
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
    public void select_Qty2UsingIcon(){
        String new_quantity = productQuantity2;
        int get_defaultValue = findWeb(loc_productQuantity2).getAttribute("value").length();
        int currentQuantity = parseInt(new_quantity) - get_defaultValue;
        if(currentQuantity > 0){
            click(loc_qtyPlusIcon);
            log.info("Quantity: " + productQuantity2 + " selected");
            sleep(Duration.ofSeconds(1));
        }
    }
    //endregion

    //region -----> METAPHORE NAVIGATION IN PRODUCT SIZE
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

    public void selectMeta_productSize2(){
        if(elementCount(By.xpath("//span[normalize-space()='Size']")) > 0){
            String get_countSize = findWeb(loc_metaItemSize(productSize2)).getText().toString();
            if (get_countSize.contains("selected")){
                sleep(Duration.ofSeconds(1));
                waitForWebElementToBeClickAble(loc_metaItemSize(productSize2));
                click(loc_metaItemSize(productSize2));
                log.info("Size: " + productSize2 + " is selected.");
            }
        }
    }

    //endregion

    //region -----> FOR NAVIGATION IN PRODUCT TYPE
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
    public void select_productType2(){
        String get_productType = findWeb(loc_kokuyoType(productType2)).getAttribute("class").toString();
        if(get_productType.contains("selected")){
            log.info("Type: " + productType2 + " selected by default.");
        }else{
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_kokuyoType(productType2),2);
            click(loc_kokuyoType(productType2));
            sleep(Duration.ofSeconds(1));
            log.info("Type: " + productType2 + " is selected.");
        }
    }
    //endregion

    //region -----> NAVIGATE THROUGH ITEM DETAILS FOR ACE
    public void selectAce_productColor(){
        String getAce_productColorStat = findWeb(loc_aceProductColor(productColor)).getText().toString();
        if (!getAce_productColorStat.contains("selected")){
            waitForElementToBeVisible(loc_productColor(productColor),2);
            click(loc_productColor(productColor));
            log.info("Color: " + productColor + " is selected.");
            sleep(Duration.ofSeconds(1));
        }else{
            log.info("Color: " + productColor + " selected by default.");
        }
    }
    public void selectAce_productColor2(){
        String getAce_productColorStat = findWeb(loc_aceProductColor(productColor2)).getText().toString();
        if (!getAce_productColorStat.contains("selected")){
            waitForElementToBeVisible(loc_productColor(productColor2),2);
            click(loc_productColor(productColor2));
            log.info("Color: " + productColor2 + " is selected.");
            sleep(Duration.ofSeconds(1));
        }else{
            log.info("Color: " + productColor2 + " selected by default.");
        }
    }
    public void selectAce_productSize(){
        String getAce_productSizeStat = findWeb(loc_aceProductSize(productSize)).getAttribute("class").toString();
        if (!getAce_productSizeStat.contains("selected")){
            waitForElementToBeVisible(loc_aceProductSize(productSize),2);
            click(loc_aceProductSize(productSize));
            log.info("Size: " + productSize + " Is selected.");
            sleep(Duration.ofSeconds(1));
        }else{
            log.info("Size: " + productSize + " Selected by default.");
        }
    }
    public void selectAce_productSize2(){
        String getAce_productSizeStat = findWeb(loc_aceProductSize(productSize2)).getAttribute("class").toString();
        if (!getAce_productSizeStat.contains("selected")){
            waitForElementToBeVisible(loc_aceProductSize(productSize2),2);
            click(loc_aceProductSize(productSize2));
            log.info("Size: " + productSize2 + " Is selected.");
            sleep(Duration.ofSeconds(1));
        }else{
            log.info("Size: " + productSize2 + " Selected by default.");
        }
    }
    //endregion

    //region -----> NAVIGATE THROUGH ITEM DETAILS FOR TALEX
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
            sleep(Duration.ofSeconds(5));
            log.info("Lenses color: " + productLensesColor + " is selected.");
        }
    }
    public void select_lensesColor2() {
        String getLensesColorStat = findWeb(loc_productLensesColor(productLensesColor2)).getText().toLowerCase();
        if (getLensesColorStat.contains("selected")) {
            log.info("Lenses color: " + productLensesColor2 + " selected by default.");
        } else {
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productLensesColor(productLensesColor2), 2);
            click(loc_productLensesColor(productLensesColor2));
            sleep(Duration.ofSeconds(5));
            log.info("Lenses color: " + productLensesColor2 + " is selected.");
        }
    }
    public void select_lensesColor3() {
        String getLensesColorStat = findWeb(loc_productLensesColor(productLensesColor3)).getText().toLowerCase();
        if (getLensesColorStat.contains("selected")) {
            log.info("Lenses color: " + productLensesColor3 + " selected by default.");
        } else {
            sleep(Duration.ofSeconds(1));
            waitForWebElementToBeClickAble(loc_productLensesColor(productLensesColor3), 2);
            click(loc_productLensesColor(productLensesColor3));
            sleep(Duration.ofSeconds(5));
            log.info("Lenses color: " + productLensesColor3 + " is selected.");
        }
    }

    //endregion

    //endregion -----> For navigating through item details

}
