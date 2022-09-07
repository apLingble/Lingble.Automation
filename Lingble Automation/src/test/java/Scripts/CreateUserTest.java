package Scripts;

import framework.environment.BaseTestCase;
import framework.keyword.HomeKeyword;
import framework.keyword.LoginKeyword;
import framework.keyword.ProfileKeyword;
import org.testng.annotations.Test;
/**
 * @author Alexander Pangilinan
 * */

public class CreateUserTest extends BaseTestCase {
    private LoginKeyword loginKeyword;
    private HomeKeyword homeKeyword;
    private ProfileKeyword profileKeyword;

    @Test(dataProvider = "DefaultProvider")
    public void userCreation(String tcID, String description,String firstName, String lastName, String phone,
                                String userEmail, String confirmUserEmail, String userPassword, String confirmUserPassword){
        homeKeyword = new HomeKeyword(driver,log);
        loginKeyword = new LoginKeyword(driver,log);
        profileKeyword = new ProfileKeyword(driver,log);
        homeKeyword.openPartnerSite();
        homeKeyword.waitForHomepage();
        homeKeyword.acceptCookies();
        homeKeyword.openLogInCreateAccountPage();
        loginKeyword.waitForLogInCreateAccountPage();
        loginKeyword.openCreateAccountTab();
        loginKeyword.enterFirstName(firstName);
        loginKeyword.enterLastName(lastName);
        loginKeyword.enterPhone(phone);
        loginKeyword.enterEmail(userEmail);
        loginKeyword.enterConfirmEmail();
        loginKeyword.enterPassword(userPassword);
        loginKeyword.enterConfirmPassword(confirmUserPassword);
//        loginKeyword.proceedToAccountCreation();
//        profileKeyword.waitForLogInCreateAccountPage();
//        profileKeyword.validateUserEmail(userEmail);

    }

}
