package framework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author alexander.v.pangilinan
 * */
public class LogInPage extends BasePageObject<LogInPage> {

	public LogInPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	protected By LogInTitle = By.xpath("//h1[@class='page-title' and normalize-space()='Login']");
	public static By LogInTab = By.xpath("//a[@id='login-tab']");
	protected By LogInTabActive = By.xpath("//a[@id='login-tab' and @class='nav-link active']");
	protected By CreateAccountTab = By.xpath("//a[@id='register-tab']");
	protected By CreateAccountTabActive = By.xpath("//a[@id='register-tab' and @class='nav-link active']");

	protected By Spinner = By.xpath("//div[contains(@class,'spinner')]");

	public By LogInPageTextFields(String fieldName){
		By element = By.xpath("//label[normalize-space()='"+fieldName+"']//following-sibling::input");
		return element;
	}
	protected By EmailLogInField = By.xpath("//input[@id='login-form-email']");
	protected By PasswordLogInField = By.xpath("//input[@id='login-form-password']");
	protected By LogInButton = By.xpath("//button[@type='submit' and normalize-space()='Login']");
	protected By EmailCreateAccountField = By.xpath("//input[@id='registration-form-email']");
	protected By PasswordCreateAccountField = By.xpath("//input[@id='registration-form-password']");
	protected By ConfirmPasswordCreateAccountField = By.xpath("//input[@id='registration-form-password-confirm']");
	protected By CreateAccountButton = By.xpath("//button[@type='submit' and normalize-space()='Create Account']");
	protected By myAccount = By.xpath("//i[@class='fa fa-user'] | //div[contains(@class, 'user')]");

}
