package framework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author alexander.v.pangilinan
 * */
public class ProfilePage extends BasePageObject<ProfilePage> {

	public ProfilePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	protected By ProfileTitle = By.xpath("//h4[normalize-space()='Profile'] | //h2[normalize-space()='Profile']");
	protected By UserEmailLabel = By.xpath("//dt[normalize-space()='Email']//following-sibling::dd");
}
