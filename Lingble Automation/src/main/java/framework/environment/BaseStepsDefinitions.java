package framework.environment;

import framework.keyword.*;
import framework.keyword.CartKeyword;
import framework.keyword.ItemDetailsKeyword;


public class BaseStepsDefinitions extends BaseTestCase {
    public static LoginKeyword loginKeyword;
    public static HomeKeyword homeKeyword;
    public static ProfileKeyword profileKeyword;
    public static PageNavigationKeyword pageNavigationKeyword;
    public static CartKeyword cartKeyword;
    public static LingbleLinkKeyword lingbleLinkKeyword;
    public static ApiRequestKeyword apiRequestKeyword;
    public static CreateOrderKeyword CreateOrderKeyword;
    public static CheckOutKeyword checkOutKeyword;
    public static ItemDetailsKeyword itemDetailsKeyword;

    public String scenDesc;
    static {
        if(driver != null) {
            homeKeyword = new HomeKeyword(driver, log);
            loginKeyword = new LoginKeyword(driver, log);
            profileKeyword = new ProfileKeyword(driver, log);
            cartKeyword = new CartKeyword(driver, log);
            lingbleLinkKeyword = new LingbleLinkKeyword(driver, log);
            apiRequestKeyword = new ApiRequestKeyword();
            pageNavigationKeyword = new PageNavigationKeyword(driver, log);
            CreateOrderKeyword = new CreateOrderKeyword(driver, log);
            checkOutKeyword = new CheckOutKeyword(driver, log);
            itemDetailsKeyword = new ItemDetailsKeyword(driver,log);
        }
    }
    public void baseStepsDefinitions(String partner, String environment){
        InitializeTestEnvironment(partner, environment);
        this.homeKeyword = new HomeKeyword(driver, log);
        this.loginKeyword = new LoginKeyword(driver, log);
        this.profileKeyword = new ProfileKeyword(driver, log);
        this.cartKeyword = new CartKeyword(driver, log);
        this.lingbleLinkKeyword = new LingbleLinkKeyword(driver, log);
        this.apiRequestKeyword = new ApiRequestKeyword();
        this.pageNavigationKeyword = new PageNavigationKeyword(driver, log);
        this.CreateOrderKeyword = new CreateOrderKeyword(driver, log);
        this.checkOutKeyword = new CheckOutKeyword(driver,log);
        this.itemDetailsKeyword = new ItemDetailsKeyword(driver,log);
    }

}
