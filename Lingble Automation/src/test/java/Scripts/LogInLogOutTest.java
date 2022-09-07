package Scripts;

import framework.environment.BaseTestCase;
import framework.keyword.HomeKeyword;
import framework.keyword.LoginKeyword;
import framework.keyword.ProfileKeyword;

import org.testng.annotations.Test;

/**
 * @author Alexander Pangilinan
 * */

public class LogInLogOutTest extends BaseTestCase {
    private LoginKeyword loginKeyword;
    private HomeKeyword homeKeyword;
    private ProfileKeyword profileKeyword;

    @Test(dataProvider = "DefaultProvider")
    public void userLogInLogOut(String tcID, String description, String userEmail){
        homeKeyword = new HomeKeyword(driver,log);
        loginKeyword = new LoginKeyword(driver,log);
        profileKeyword = new ProfileKeyword(driver,log);
        homeKeyword.openPartnerSite();
        homeKeyword.waitForHomepage();
        homeKeyword.acceptCookies();
        homeKeyword.minimizeChat();
        homeKeyword.openLogInCreateAccountPage();
        loginKeyword.waitForLogInCreateAccountPage();
        loginKeyword.openLogInTab();
        loginKeyword.setUserEmail();
        loginKeyword.setUserPassword();
        loginKeyword.proceedToLogIn();
        profileKeyword.waitForLogInCreateAccountPage();
        profileKeyword.validateUserEmail(userEmail);
        homeKeyword.logOutUser();
    }

}
