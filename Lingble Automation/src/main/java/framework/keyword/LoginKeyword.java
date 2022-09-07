package framework.keyword;



import framework.commonFunctions.ConfigurationKeyEnum;
import framework.commonFunctions.commonHelper;
import framework.pages.LogInPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;

import java.time.Duration;
import java.util.HashMap;

/**
 * @author alexander.v.pangilinan
 * */
public class LoginKeyword extends LogInPage {

    public LoginKeyword(WebDriver driver, Logger log) {
        super(driver, log);
    }
    private static String email;

    public void openAccountPage(){
        log.info("Open Account Page.");
        waitForElementToBeVisible(myAccount, 20);
        click(myAccount);
    }

    public void waitForLogInCreateAccountPage(){
        log.info("Wait for Log In | Create Account page.");
        waitUntilPageready();
        waitForElementToBeVisible(LogInTab,30);
    }

    public void openLogInTab(){
        log.info("Open Log In tab.");
        for(int t=0; t<3; t++){
            click(LogInTab);
            waitForElementToBeVisible(EmailLogInField, 300);
            if (elementCount(EmailLogInField)!=0){
                break;
            }
        }
    }

    public void proceedToLogIn(){
        log.info("Proceed to login.");
        click(LogInButton);
//        sleep(Duration.ofSeconds(10000));
    }

    public void setUserEmail() {
        log.info("Set email.");
        String email = System.getProperty(ConfigurationKeyEnum.email.value());
        type(email, EmailLogInField);
    }

    public void setUserPassword() {
        log.info("Set password.");
        String password = System.getProperty(ConfigurationKeyEnum.password.value());
        type(password, PasswordLogInField);
    }

    public void Account_enterUser_email(String countryCode){
        String country_userEmail = System.getProperty(countryCode + "_userEmail");

        log.info("Enter user email");
        waitForWebElementToBeClickAble(EmailLogInField, 10);
        clear(EmailLogInField);
        type(country_userEmail, EmailLogInField);
    }
    public void Account_enterUser_password(String password){
        log.info(password);

        log.info("Enter user password");
        clear(PasswordLogInField);
        type(password, PasswordLogInField);
    }

    public void enterFirstName(String value) {
        log.info("Enter firstname "+value+".");
        type(value, LogInPageTextFields("First Name"));
    }

    public void enterLastName(String value) {
        log.info("Enter lastname "+value+".");
        type(value, LogInPageTextFields("Last Name"));
    }

    public void enterPhone(String value) {
        log.info("Enter Phone "+value+".");
        type(value, LogInPageTextFields("Phone"));
    }
    public void enterEmail(String value) {
        this.email = value;
        log.info("Enter Email "+email+".");
        type(email, EmailCreateAccountField);
    }
    public void enterConfirmEmail() {
        log.info("Enter Confirm Email "+email+".");
        type(email, LogInPageTextFields("Confirm Email"));
    }
    public void enterPassword(String value) {
        log.info("Enter Password "+value+".");
        type(value, PasswordCreateAccountField);
    }
    public void enterConfirmPassword(String value) {
        log.info("Enter Confirm Password "+value+".");
        type(value, ConfirmPasswordCreateAccountField);
    }

    public void proceedToAccountCreation(){
        log.info("Proceed to account creation.");
        click(CreateAccountButton);
    }
    public void openCreateAccountTab(){
        log.info("Open create account tab.");
        for(int t=0; t<3; t++){
            click(CreateAccountTab);
            waitForElementToBeVisible(LogInPageTextFields("First Name"), 300);
            if (elementCount(LogInPageTextFields("First Name"))!=0){
                break;
            }
        }
    }
    public static String getGeneratedEmail(){
        return email;
    }
}
