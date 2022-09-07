package framework.keyword;


import framework.commonFunctions.ConfigurationKeyEnum;
import framework.pages.LogInPage;
import framework.pages.ProfilePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import javax.validation.constraints.AssertTrue;
import java.util.Locale;

/**
 * @author alexander.v.pangilinan
 */
public class ProfileKeyword extends ProfilePage {

    public ProfileKeyword(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void waitForLogInCreateAccountPage() {
        log.info("Wait for Profile page.");
        waitUntilPageready();
        waitForElementToBeVisible(ProfileTitle, 30);
    }

    public void validateUserEmail() {
        log.info("Validate user Email");
        assert (LoginKeyword.getGeneratedEmail().equals(getText(UserEmailLabel).toLowerCase()));
    }

    public void validateUserEmail(String Email) {
        log.info("Validate user Email");
        assert (Email.equals(getText(UserEmailLabel).toLowerCase()));
    }
}



