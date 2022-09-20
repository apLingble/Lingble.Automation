package framework.environment;

import framework.commonFunctions.CsvDataProvider;

import framework.commonFunctions.mapHelper;
import framework.core.Starter;
import framework.core.listener.ProjectProcessListener;
import framework.core.listener.ProjectReportListener;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.mapping.Table;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Environment initialization.
 * Please do not modify or if needed kindly communicate with the author
 * @author alexander.v.pangilinan
 * */
@Listeners({ProjectReportListener.class, ProjectProcessListener.class})
//@TestExecutionListeners({ ServletTestExecutionListener.class,
//        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class})
@SpringJUnitConfig(classes = Starter.class)
public class BaseTestCase extends AbstractTestNGSpringContextTests {
	protected static WebDriver driver;
	public WebDriverWait wait;
	public String testname;
	public String dateTime;
	public String browser;
	public static Logger log;
	public String runAsHeadless;
	public ITestContext iTestContext;
	private static Properties loadProp;

	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(framework.core.base.BaseTestCase.class);
	private static final String LOG_DATE_FORMAT = "yyyyMMdd";
	private static final String LOG_DATE_KEY_IN_PROP = "LOG_DATE";
	private static final String LOG_DATE_TIME_FORMAT = "yyyyMMdd_HHmmss";
	private static final String LOG_DATE_TIME_KEY_IN_PROP = "LOG_DATETIME";
	private static final String TASK_ID = "TASK_ID";


	private void loadPartnerConfiguration(String partner, String environment){
		Properties globalConfig = loadProp("config properties/"+mapHelper.mapProperties(partner.toLowerCase(),environment.toLowerCase())+".properties");
		Properties globalConfig1 = loadProp("config properties/LLStandards.properties");
		System.getProperties().putAll(globalConfig1);
		System.getProperties().putAll(globalConfig);
		setLoadedProp(globalConfig);
		setLogFileNameToProp();

	}

	@SuppressWarnings("static-access")
	@Parameters({ "browser", "runasheadless"})
//	@BeforeTest
	public void InitializeTestEnvironment(String partner, String environment) {
		loadPartnerConfiguration(partner,environment);
		BrowserFactory BrowserFactory = new BrowserFactory();
		this.dateTime = DateTimeFormatter.ofPattern("ddMMYYhhmm").format(ZonedDateTime.now());
		this.testname = "SFCC";//ctx.getCurrentXmlTest().getName();
		try {
			if (browser == null || browser.length() == 0) {
				String Scriptbrowser = "chrome";
				this.log = LogManager.getLogger(testname + " in " + Scriptbrowser);

			} else {
				this.log = LogManager.getLogger(testname +" "+ browser);

			}
		} catch (Exception e) {

		}
//		for (int y = 1; y <= 5; y++) {
//			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
//					+ "/target/surefire-reports" + getPastDateString(y);
//			Path directory = Paths.get(reportDirectory);
//			try {
//				deleteFolderAndItsContent(directory);
//			} catch (IOException e2) {
//			}
//		}

		try {
				if (browser == null || browser.length() == 0) {
					log.info("The test is running in local environment and triggered via script.");
					String DbrowserName = "chrome";
					this.driver = BrowserFactory.getDriver(DbrowserName, System.getProperty("RunAsHeadless"));
					this.browser = DbrowserName;
				} else {
					log.info("The test is running in local environment and triggered via test suite.");
					this.driver = BrowserFactory.getDriver(browser, System.getProperty("RunAsHeadless"));
					this.browser = browser;
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}


	public Date yesterday(int x) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -x);
		return cal.getTime();
	}

	public String getPastDateString(int x) {
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		return dateFormat.format(yesterday(x));
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
	private static void setLoadedProp(Properties loadProp) {
		BaseTestCase.loadProp = loadProp;
	}
	private static void setLogFileNameToProp() {
		BaseTestCase.loadProp.put(
				LOG_DATE_KEY_IN_PROP,
				new SimpleDateFormat(LOG_DATE_FORMAT).format(Calendar.getInstance().getTime())
		);
		BaseTestCase.loadProp.put(
				LOG_DATE_TIME_KEY_IN_PROP,
				new SimpleDateFormat(LOG_DATE_TIME_FORMAT).format(Calendar.getInstance().getTime())
		);
		BaseTestCase.loadProp.put(
				TASK_ID,
				new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
		);
	}
	private static Properties loadProp(String propFileName) {
		Properties prop = new Properties();
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
		URL globalConfigUrl = currentClassLoader.getResource(propFileName);

		try {
			prop.load(globalConfigUrl.openStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop;
	}

	@DataProvider(name = "DefaultProvider")
	public Iterator<Object[]> getDataProvider(ITestContext iTestContext, Method method) throws IOException {
		this.iTestContext = iTestContext;
		return new CsvDataProvider(method);
	}

//	@AfterTest
	public void TearDown(Scenario scenario) {
		log.info("Run completed.");
		driver.quit();
	}

	public static void screenshot(long ms)
	{

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./ScreenShots/"+ms+""+System.getProperty("partnerSite")+".png"));
			log.info("ScreenShot Taken");
		}
		catch (Exception e)
		{
			log.info("Exception while taking ScreenShot "+e.getMessage());
		}


	}


}