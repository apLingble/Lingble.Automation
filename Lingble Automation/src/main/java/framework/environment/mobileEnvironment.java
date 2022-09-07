package framework.environment;

import framework.commonFunctions.CsvDataProvider;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Properties;

/**
 * Environment initialization.
 * Please do not modify or if needed kindly communicate with the author
 * @author alexander.pangilinan
 * */

public class mobileEnvironment {
	public static AndroidDriver driver;
	public WebDriverWait wait;
	public String testname;
	public String dateTime;
	public static Logger log;
	public ITestContext iTestContext;
	private static URL url;
	private static DesiredCapabilities capabilities;
	private static Properties loadedProp;
	private static String appName;
	private static String appHosT;
	private static String appPorT;

	@SuppressWarnings("static-access")
	@Parameters({ "appHost","appPort"})
	@BeforeClass
	public void InitializeTestEnvironment(ITestContext ctx,@Optional String appHost,@Optional String appPort) {
		loadConfiguration();
		this.dateTime = DateTimeFormatter.ofPattern("ddMMyyhhmm").format(ZonedDateTime.now());
		this.testname = ctx.getCurrentXmlTest().getName();
		try {
			if (appName == null || appName.length() == 0) {
				appName = "root";
				log = LogManager.getLogger(testname + " in " + appName);

			} else {
				log = LogManager.getLogger(testname + " in " + appName);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
				if ((appHost == null || appHost.length() == 0)&&(appPort == null || appPort.length() == 0)) {
					log.info("The test is running in local environment and triggered via script.");
					appHosT= getPropertyValue(mobileEnvConfigEnums.APP_HOST.value());
					appPorT = getPropertyValue(mobileEnvConfigEnums.APP_PORT.value());
					setDriver(appHosT,appPorT);
				} else {
					log.info("The test is running in local environment and triggered via test suite.");
					setDriver(appHost,appPort);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}
	private static String getPropertyValue(String key){
		return System.getProperty(key);
	}
	private static void loadConfiguration() {
		Properties globalConfig = loadProp("config properties/config.properties");
		System.getProperties().putAll(globalConfig);
	}
	public static void setDriver(String appHost, String appPort){
		try {
			url = new URL("http://"+getPropertyValue(mobileEnvConfigEnums.APPIUM_HOST.value())+":"+getPropertyValue(mobileEnvConfigEnums.APPIUM_PORT.value())+"/wd/hub");
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,getPropertyValue(mobileEnvConfigEnums.PLATFORM_NAME.value()));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getPropertyValue(mobileEnvConfigEnums.PLATFORM_VERSION.value()));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,getPropertyValue(mobileEnvConfigEnums.DEVICE_NAME.value()));
			capabilities.setCapability(MobileCapabilityType.UDID,appHost+":"+appPort);
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60);
			driver = new AndroidDriver<MobileElement>(url,capabilities);
			log.info("The Automation is now running on android platform.");

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void setAppName(String appname){
		appName = appname;
	}

	public static WebDriver getDriver() {
		return driver;
	}
	public static Logger getlog() {
		return log;
	}
	public WebDriverWait getwait() {
		return this.wait;
	}


	public static void deleteFolderAndItsContent(final Path folder) throws IOException {
		Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				if (exc != null) {
					throw exc;
				}
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	private static Properties loadProp(String propFileName) {
		Properties prop = new Properties();
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
		URL globalConfigUrl = currentClassLoader.getResource(propFileName);

		try {
			prop.load(globalConfigUrl.openStream());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return prop;
	}

	@DataProvider(name = "DefaultProvider")
	public Iterator<Object[]> getDataProvider(ITestContext iTestContext, Method method) throws IOException {
		this.iTestContext = iTestContext;
		return new CsvDataProvider(method);
	}

	@AfterClass
	public void TearDown() {
		log.info("Run completed.");
		driver.quit();
	}


}