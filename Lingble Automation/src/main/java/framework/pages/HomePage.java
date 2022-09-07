package framework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author alexander.v.pangilinan
 * */
public class HomePage extends BasePageObject<HomePage> {

    protected HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    protected By AgreeButton= By.xpath("//button[normalize-space()='Agree']");
    protected By DisagreeButton = By.xpath("//button[@title='Disagree' and @class='reject btn btn-outline-dark btn-action btn-sm']");
    protected By XButton = By.xpath("//button[@class='accept btn btn-action']");
    protected By CustomerCareLabel = By.xpath("//h2[normalize-space()='Customer Care']");
    protected By CCMinimizeChatButton = By.xpath("//div[@class='titlebar-action-container']/div[@class='icon']");
    protected By HomePageLogo = By.xpath("//a[@class='logo-home']");
    protected By LogInToAccountButton = By.xpath("//a[@aria-label='Login to your account'] | //a[@aria-label='Account'] | //a[contains(@href,'Login-Show')] | //a[@aria-label='Login / Sign up'] | //a[@aria-label='Log In'] | //a[@aria-label='Login']");
    protected By LogInToAccountButton(int index){
        return By.xpath("(//a[@aria-label='Login to your account'])["+index+"] | (//a[@aria-label='Account'])["+index+"] | (//a[@aria-label='Login / Sign up'])["+index+"] | (//a[contains(@href,'Login-Show')])["+index+"] | (//a[@aria-label='Log In'])["+index+"] | (//a[@aria-label='Login'])["+index+"]");
    }
    protected By LogOutButton = By.xpath("//ul[@aria-label='My account navigation']/li[2]/a " +
            "| //div/ul[@aria-label='My account navigation']/li[2]/a" +
            "| //*[@id=\"mainHeader\"]/nav/div/div/div[3]/div[3]/div/a[4]" +
            "| //a[contains(text(), 'Logout')]");

    protected By LogOutButton(int index) {
        return By.xpath("(//a[contains(text(), 'Logout')])[" + index + "]");
    }
    protected By UserNameHeaderLabel = By.xpath("//a[@aria-label='Login to your account']//span");
    protected By CustomerCareIframe = By.xpath("//iframe[@id='hfc-frame']");
    protected By CloseNewsLetter = By.xpath("//div[@id='newsletterForm']//parent::div[@class='modal-content']//following-sibling::button[contains(@class,'close')]");
    protected By newsLetterEmail = By.xpath("//input[@id='newsletter-form-email']");
//    protected By newsLetterBrandImage = By.xpath("//div[@class='newsletter-main-image']//img[contains(@src,'"+url+"']");
    public By newsLetterBrandImage(String url){
        return By.xpath("//div[@class='newsletter-main-image']//img[contains(@src,'"+url+"']");
    }
    protected By subscribeButton = By.xpath("//div[@class='login-form-nav']//parent::form[@id='dwfrm_newsletter']//following-sibling::button[@class='btn btn-block btn-primary text-uppercase']");
    protected By ClickWishlistButton = By.xpath("//a[@class='minicart-link']");
    protected By ageConsentbutton = By.xpath("//button[@class='explicit-consent-yes']");
    public By countryDropdown1 = By.xpath("//div[@class='dropdown-menu dropdown-country-selector']");
    public By countryDropdown2 = By.xpath("//span/*[starts-with(@class,'flag-icon flag-icon-squared')]");
    public By countryDropdown3 = By.xpath("//span[@class='country-name']");
    public By countryDropdown4 = By.xpath("//div[@class='header']//a[@class='current-country dropdown-toggle']");
    public By selectCountry(String CountryCode, int index){
        return By.xpath("(//a[@data-country='"+CountryCode+"'])["+index+"]");
    }
    public By get_selectedCountryStatus(String CountryCode){
        return By.xpath("//a[@data-country='"+CountryCode+"']");
    }
    public By selectedCountryName(String CountryName, int index){
        return By.xpath("(//span[contains(text(),'"+CountryName+"')])["+index+"]");
    }
    public By selectedCountryCode(String CountryCode, int index){
        return By.xpath("(//nav[contains(@data-url,'en_"+CountryCode+"')])["+index+"]");
    }
    public By Dropdown1(int index){
        return By.xpath("(//a[@class='current-country dropdown-toggle'])["+index+"]");
    }
    public By Dropdown2 = By.xpath("(//div[@class='country-selector dropdown']//descendant::span[@class='country-icon'])[1]");
    public By Dropdown3 (int index){
        return By.xpath("(//span[@id='dropdownCountrySelector'])["+index+"]");
    }
    public By Dropdown4 = By.xpath("//div[@class='header']//a[@class='current-country dropdown-toggle']");
    public By Dropdown5 (int index){
        return By.xpath("(//a[@class='current-country dropdown-toggle']//span[@class='country-name'])["+index+"]");
    }

    protected By CartButton = By.xpath("//div[contains(@class,'minicart')]//a[contains(@class,'minicart-link')]");
    protected By CheckoutButton = By.xpath("//a[normalize-space(@class)='btn btn-primary btn-block checkout-btn'] | //div[contains(@class,'totals')]//a[normalize-space(@class)='btn btn-primary btn-block checkout-btn']");
}
