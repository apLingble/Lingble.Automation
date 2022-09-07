package framework.commonFunctions;

/**
 * Configuration keys enum in properties.
 * @author alexander.v.pangilinan
 */
public enum ConfigurationKeyEnum {

    url("url"),
    password("password"),
    username("username"),
    email("email"),
    lingbleLinkEmail("lingbleLinkEmail"),
    mobile("mobile"),
    partnerSite("partnerSite"),
    lingbleLinkURL("lingbleLinkURL"),
    lingbleLinkPassword("lingbleLinkPassword"),
    param_productCategory("productCategory"),
    param_productSubCategory("productSubCategory"),
    param_productSubCategory2("productSubCategory2"),
    param_productName("productName"),
    param_ProductColor("productColor"),
    param_productSize("productSize"),
    param_productQuantity("productQuantity");
//    countryCode("country");




    private final String value;

    ConfigurationKeyEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ConfigurationKeyEnum getByValue(String value){
        for(ConfigurationKeyEnum currentValue: values()){
            if(currentValue.value().equals(value)){
                return currentValue;
            }
        }
        throw new RuntimeException("Failed to find ConfigurationKeyEnum");
    }
}
