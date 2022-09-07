package framework.keyword;

import framework.commonFunctions.ConfigurationKeyEnum;
import framework.pages.CreateOrderPage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @author alexander.v.pangilinan
 * */
public class CreateOrderKeyword extends CreateOrderPage {

    public CreateOrderKeyword(WebDriver driver, Logger log) {super(driver, log); }

    public void searchProduct(String partner, String product) {
        waitUntilPageready();
        String partnerSite = System.getProperty("partnerSite");
        String productName = System.getProperty(product+"productName");

        if(partnerSite.equals("Talex")){
//            ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,-500)");
            waitForWebElementToBeClickAble(loc_searchIconMagento,5);
            click(loc_searchIconMagento);
            type(productName ,loc_searchProductName);
            typeKeyBoardKeys(Keys.ENTER, loc_searchProductName);
            waitForWebElementToBeClickAble(loc_productLink(productName), 5);
            click(loc_productLink(productName));
            log.info(productName + " Is selected");
            waitForElementToBeVisible(loc_selectedProduct(productName),5);
        }else {
            if(elementCount(loc_countSearhIcon) > 0){
                scrollToElement(loc_searchIcon);
                click(loc_searchIcon);
            }
            scrollToElement(loc_searchEngine);
            waitForWebElementToBeClickAble(loc_searchEngine, 5);
            click(loc_searchEngine);
            type(productName, loc_searchProductName);

            By[] suggestion_optionList = new By[]{loc_suggestionOption1(productName), loc_suggestionOption2(productName),
                    loc_suggestionOption3(productName), loc_suggestionOption4(productName)};
            for (By loc_suggestionsOption : suggestion_optionList) {
                try {
                    waitForWebElementToBeClickAble(loc_suggestionsOption, 2);
                    click(loc_suggestionsOption);
                } catch (Exception e) {
                }
            }
            log.info("Product: " + productName + " is Selected");
        }
    }

     public void AddToCartButton(){
        moveTo(loc_AddToCartButton);
        waitForWebElementToBeClickAble(loc_AddToCartButton, 3);
        click(loc_AddToCartButton);
        log.info("Add button clicked.");
        waitUntilPageready();

    }


}

