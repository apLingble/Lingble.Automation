package framework.environment;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class driverSessionMap {
    private static Map<String, String> Session;

    public static void mapDriverSession(String driverType, String driverSession){
        if(Session==null){
            Session = new HashMap<>();
        }
        Session.put(driverType,driverSession);
    }
    public static String getDriverSession(String driverType){
        return Session.get(driverType.trim().toLowerCase());
    }

}
