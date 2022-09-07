package framework.environment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Selection of browsers
 * Please do not modify or if needed kindly communicate with the author
 * @author alexander.v.pangilinan
 * */
public class BrowserFactory extends BaseTestCase {
	WebDriver driver = null;
	
	public WebDriver getDriver(String browser, String runAsHeadless) {

		switch (browser.toLowerCase()) {
		case "chrome":
			runChrome(runAsHeadless);
			break;
		case "firefox":
			log.info("Not defined.");
			break;
		case "ie":
			log.info("Not defined.");
			break;
		default:
			log.info("No browser was selected!!!");
			break;
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver runChrome(String runAsHeadless){
		WebDriverManager.chromedriver().setup();
		//Classic run with headless browser
			if (runAsHeadless.equalsIgnoreCase("yes")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--window-size=1325x744");
//				chromeOptions.addArguments("--start-fullscreen");
				driver = new ChromeDriver(chromeOptions);
//				driver.manage().window().fullscreen();
				log.info("The Automation is now running on windows platform using chrome browser with headless configuration.");
			} else {
				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.addArguments("--start-fullscreen");
				driver = new ChromeDriver(chromeOptions);
				driver.manage().window().maximize();
				log.info("The Automation is now running on windows platform using chrome browser.");
			}

		return driver;
	}


}
