package framework.keyword;

import framework.commonFunctions.ConfigurationKeyEnum;
import framework.pages.HomePage;
import framework.pages.LogInPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author alexander.v.pangilinan
 */
public class HomeKeyword extends HomePage {

    public HomeKeyword(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPartnerSite() {
        String url = System.getProperty(ConfigurationKeyEnum.url.value());
        log.info("Open " + url + " Environment.");
        getPage(url);
    }
    public void openPartnerSiteURL2() {
        String url = System.getProperty("url2");
        log.info("Open " + url + " Environment.");
        getPage(url);
        waitUntilPageready();
    }
    public void openPartnerSiteURL3() {
        String url = System.getProperty("url3");
        log.info("Open " + url + " Environment.");
        getPage(url);
        waitUntilPageready();
    }
    public void openPartnerSiteURL4() {
        String url = System.getProperty("url4");
        log.info("Open " + url + " Environment.");
        getPage(url);
        waitUntilPageready();
    }

    public void validateProtocol() {
        if (driver.getCurrentUrl().startsWith("https")){
            log.info("The Site URL Protocol has been verified.");
        } else throw new Error("Error verifying the site URL protocol.");
    }

    public void validateSubdomain() {
        if (driver.getCurrentUrl().contains("//www.")){
            log.info("The Site URL Subdomain has been verified.");
        } else throw new Error("Error verifying the site URL subdomain.");
    }

    public void urlValidateIfItIsContainsDemandware() {
        String url = System.getProperty(ConfigurationKeyEnum.url.value());
        log.info(url);
        if (url.contains("demandware.net")) {
            log.info("URL has contains demandware.net");
        } else {
            throw new Error("You're on production environment!!");
        }

    }

    public void waitForHomepage() {
        log.info("Wait for home page to load.");
        waitForElementToBeVisible(HomePageLogo, 30);
    }

    public void acceptCookies() {
        try {
            log.info("Accept cookies.");
            if (elementCount(AgreeButton) > 0){
                waitForWebElementToBeClickAble(AgreeButton, 5);
                scrollToElement(AgreeButton);
                click(AgreeButton);
                log.info("Cookies accepted.");
                sleep(Duration.ofSeconds(1));
            }
        } catch (Exception e) {

        }
    }
    public void allowCookies(){
        try {
            log.info("Allow cookies.");
            if (elementCount(loc_allowCookies) > 0){
                waitForWebElementToBeClickAble(loc_allowCookies, 5);
                scrollToElement(loc_allowCookies);
                click(loc_allowCookies);
                log.info("Cookies accepted.");
                sleep(Duration.ofSeconds(1));
            }
        } catch (Exception e) {

        }
    }
    public void closeNewsLetter_magento(){
        waitUntilPageready();
        log.info("Close newsletter");
        if (elementCount(loc_popup_box) > 0){
            waitForWebElementToBeClickAble(loc_popup_box, 10);
            click(loc_popup_box);
        }
        if (elementCount(loc_closeNewsletter_magento) > 0){
            waitForWebElementToBeClickAble(loc_closeNewsletter_magento, 10);
            click(loc_closeNewsletter_magento);
            log.info("Newsletter is closed");
        }
    }

//    public void minimizeChat() {
//        log.info("Minimize chat box.");
//        try {
//            for (int i = 0; i < 3; i++) {
//                waitForElementToBeVisible(CustomerCareIframe, 6000);
//                switchToIframe(CustomerCareIframe);
//                sleep(Duration.ofSeconds(5));
//                waitForPresenceOfText(CustomerCareLabel, "Customer Care", 5000);
//                for (int x = 0; x < 3; x++) {
//                    if (checkElementIfInvisible(CustomerCareLabel, 1000) == true) {
//                        break;
//                    }
//                    sleep(Duration.ofSeconds(5));
//                    click(CCMinimizeChatButton);
//                    waitForElementToBeInvisible(CustomerCareLabel, 1000);
//                }
//                if (elementCount(CustomerCareLabel) == 0) {
//                    log.info("The customer care chat box is now hidden.");
//                    switchToDefaultFrame();
//                    break;
//                }
//                switchToDefaultFrame();
//            }
//        } catch (Exception e) {
//            log.info("The customer chat box did not appear.");
//        }
//    }
    public void minimizeChat() {
        log.info("Minimize chat box.");
        try {
            for (int i = 0; i < 3; i++) {
                waitForElementToBeVisible(CustomerCareIframe, 10);
                switchToIframe(CustomerCareIframe);
                sleep(Duration.ofSeconds(5));
                waitForPresenceOfText(CCMinimizeChatButton, "Customer Care", 5);
                for (int x = 0; x < 3; x++) {
                    if (checkElementIfInvisible(CCMinimizeChatButton, 10) == true) {
                        break;
                    }
                    click(CCMinimizeChatButton);
                    waitForElementToBeInvisible(CCMinimizeChatButton, 5);
                }
                if (elementCount(CCMinimizeChatButton) == 0) {
                    log.info("The customer care chat box is now hidden.");
                    switchToDefaultFrame();
                    break;
                }
                switchToDefaultFrame();
            }
        } catch (Exception e) {
            log.info("The customer chat box did not appear.");
        }
    }
    public void openLogInCreateAccountPage() {
        log.info("Open LogIn | Create Account page.");
        for (int i = 1; i <= getElement(LogInToAccountButton).size(); i++) {
            try {
                waitForElementToBeVisible(LogInToAccountButton(i), 30);
                click(LogInToAccountButton(i));
                waitForElementToBeVisible(LogInPage.LogInTab, 5);
                if (getElement(LogInPage.LogInTab).size() > 0) {
                    log.info("LogIn | Create Account page is now open.");
                    break;
                }
            } catch (Exception e) {

            }
            log.error("Unable to open create account.");
        }
    }

    public void clickWishlistButton() {
        log.info("Click wishlist button");
        waitForWebElementToBeClickAble(ClickWishlistButton, 5000);
        click(ClickWishlistButton);
    }

    public void moveMouseToCart() {
        log.info("User must be able move or hover to cart");
        moveTo(CartButton);
        waitForWebElementToBeClickAble(CartButton, 10);
    }

    public void viewCart() {
        log.info("View cart");
        sleep(Duration.ofMillis(1500));
        moveTo(CartButton);
        waitForWebElementToBeClickAble(CartButton, 10);
        click(CartButton);
    }

    public void logOutUser() {
        log.info("Logging out the account...");
        for (int i = 1; i <= getElement(LogOutButton).size(); i++) {
            try {
                waitForWebElementToBeClickAble(LogOutButton(i), 30);
                click(LogOutButton(i));
                waitForHomepage();
                log.info("Logged out successfully!");
            } catch (Exception e) {

            }
        }
    }

    public void closeNewsletterModal() {
        try {
            log.info("Close newsletter.");
            waitForWebElementToBeClickAble(CloseNewsLetter, 25);
            for (int x = 0; x < 5; x++) {
                waitForWebElementToBeClickAble(CloseNewsLetter, 5);
                click(CloseNewsLetter);
                waitForElementToBeInvisible(CloseNewsLetter, 5);
                if (elementCount(CloseNewsLetter) == 0) {
                    log.info("Newsletter is now closed.");
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    public void newsletterValidation() {
        String[] strArr = new String[]{};
        strArr = System.getProperty("url").split(">");
        waitForElementToBeVisible(newsLetterBrandImage(strArr[0]), 20);
        if(elementCount(newsLetterEmail)>0){
            type("qa+1@lingble.com", newsLetterEmail);
            scrollToElement(subscribeButton);
            sleep(Duration.ofSeconds(2));
            waitForWebElementToBeClickAble(subscribeButton);
            click(subscribeButton);
            sleep(Duration.ofSeconds(4));
        }
    }

    public void ageConsent() {
        try {
            log.info("Age consent.");
            List<WebElement> countAgeConsent = driver.findElements(ageConsentbutton);
            if (countAgeConsent.size() > 0) {
                waitForWebElementToBeClickAble(ageConsentbutton, 10);
                click(ageConsentbutton);
                log.info("'Yes' was cliked.");
                sleep(Duration.ofSeconds(2));
            }
        } catch (Exception e) {

        }
    }

    public void setCountry(String countryCode, String countryName) {
        log.info("Set Ship to Location to " + countryName);
        waitUntilPageready();
        int dropdownCount = 0;
        int dropdownSize = 0;
        By[] CDlist = new By[]{countryDropdown1, countryDropdown2, countryDropdown3, countryDropdown4};
        for (By by : CDlist) {
            try {
                dropdownSize = elementCount(by);
                if (dropdownSize > dropdownCount) {
                    dropdownCount = dropdownSize;
                }
            } catch (Exception e) {

            }
        }
        if (dropdownCount != 0) {
            outerloop:
            for (int i = 1; i <= dropdownCount; i++) {
                By[] DropdownCDlist = new By[]{Dropdown1(i), Dropdown2, Dropdown3(i), Dropdown4, Dropdown5(i)};
                for (By by : DropdownCDlist) {
                    try {
                        waitForWebElementToBeClickAble(by, 2);
                        scrollToElement(by);
                        sleep(Duration.ofSeconds(1));
                        clickElementJS(by);
                        moveTo(by);
                        waitForWebElementToBeClickAble(selectCountry(countryCode, i), 2);
                        if (elementCount(selectCountry(countryCode, i)) != 0) {
                            log.info("Country dropdown is expanded.");
                            break outerloop;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        //Added this condition that if country was already selected then skip selecting.
        if (elementCount(get_selectedCountryStatus(countryCode)) > 0){
            String partnerSite = System.getProperty("partnerSite");
            if (partnerSite.equals("Tatras")){
                selectLinkText(countryCode);
            }else {
                selectLinkText(countryName);
            }
        }
        waitUntilPageready();
        String get_currentURL = driver.getCurrentUrl().toString();

        if (get_currentURL.contains("en_"+countryCode)) {
            log.info("URL has contains: " + "en_" + countryCode + " Base on country selected");
        } else {
            throw new Error("URL not contains " + countryCode + " Base on selected country");
        }
    }

    public void clickCheckoutButton() {
        log.info("Click Checkout Button");
        moveTo(CheckoutButton);
        waitForElementToBeVisible(CheckoutButton, 180);
        click(CheckoutButton);
    }
}
