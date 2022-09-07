package framework.environment;

/**
 * @author alexander.pangilinan
 * */
public enum mobileEnvConfigEnums {
    PLATFORM_NAME("PLATFORM_NAME"),
    PLATFORM_VERSION("PLATFORM_VERSION"),
    DEVICE_NAME("DEVICE_NAME"),
    APPIUM_HOST("APPIUM_HOST"),
    APPIUM_PORT("APPIUM_PORT"),
    APP_PORT("APP_PORT"),
    APP_HOST("APP_HOST");
    private final String value;

    mobileEnvConfigEnums(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static mobileEnvConfigEnums getByValue(String value){
        for(mobileEnvConfigEnums currentValue: values()){
            if(currentValue.value().equals(value)){
                return currentValue;
            }
        }
        throw new RuntimeException("Failed to find ConfigurationKeyEnum");
    }
}
