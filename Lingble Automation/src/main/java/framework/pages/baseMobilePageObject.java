package framework.pages;


import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Fail.fail;

/**
 * Please do not modify, Kindly let the author know if you need changes.
 * @author alexander.pangilinan
 * */
public class baseMobilePageObject<T> {
	public AndroidDriver driver;
	public Logger log = null;
	public WebDriverWait wait;
	public Select select;
	public Actions action;


	protected baseMobilePageObject(AndroidDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
		this.wait = new WebDriverWait(driver, 100);
	}
	
	@SuppressWarnings("unchecked")
	protected T getPage(String url) {
		driver.get(url);
		return (T) this;
	}
	protected void activateApp(String appPackage) {
		driver.activateApp(appPackage);
	}
	protected void refreshApp() {
		driver.runAppInBackground(Duration.ofMillis(1000));
	}

	protected void terminateApp(String appPackage){
		driver.terminateApp(appPackage);
	}
	protected void clearApp(String appPackage){
		driver.resetApp();
	}
	protected void moveTo(By element) {
		action = new Actions(driver);
		action.moveToElement(findWeb(element)).build().perform();
	}

	protected boolean checkRadiobuttonStatus(By element) {
		if(findWeb(element).isSelected()) {
			return true;
		}
		return false;
	}
	protected void waitForBusyIndicator() {
		By Element = By.xpath("//div[@class='busy-indicator active']");
		for (int x = 0; x <= 6000; x++) {
			try {
				if (getElement(Element).size() == 0) {
					log.info("Busy indicator is now hidden.");
					break;
				}
			} catch (Exception e) {
			}
		}
	}
	
	protected void waitForEndPointBusyIndicator() {
		By Element = By.xpath("//div[@class='app-spinner']");
		for (int x = 0; x <= 6000; x++) {
			try {
				if (getElement(Element).size() == 0) {
					log.info("Busy indicator is now hidden.");
					break;
				}
			} catch (Exception e) {
			}
		}
		
	}
	
	protected int elementCount(By element) {
		List<WebElement> elementCount = driver.findElements(element);
		return elementCount.size();
	}

	protected List<WebElement> getElement(By element) {
		List<WebElement> elements = driver.findElements(element);
		return elements;
	}
		
	protected void waitForElementToBeInvisible(By Element, int waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				if (getElement(Element).size() == 0) {
					log.info("Busy indicator is now hidden.");
					break;
				}
			} catch (Exception e) {
			}
		}
	}
	protected void waitForElementToBeVisible(By Element, int waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				if (getElement(Element).size() != 0) {
					log.info("The "+Element+" is now visible");
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	protected void waitForElementPresence(By element, Integer waitingCount, Integer timeInSeconds) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				waitForPresenceOfWebElement(element, timeInSeconds);
				if (elementCount(element) != 0) {
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	protected void waitForPresenceOfElemAndClick(By element, Integer waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				if (getElement(element).size() != 0) {
					getElement(element).get(0).click();
					log.info("The element is presented and clicked.");
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	protected void waitForPresenceOfText(By element, String expectedText, Integer waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				List<WebElement> elementCount = driver.findElements(element);
				String text = elementCount.get(0).getText().trim();
				if (elementCount.size() != 0) {
					if (text.equals(expectedText)) {
						log.info("The text " + text + " is displayed");
						break;
					}
				}
			} catch (Exception e) {
			}
		}

	}

	protected void waitForPresenceOfTextAndClick(By element, String expectedText, Integer waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				String text = getElement(element).get(0).getText().trim();
				if (getElement(element).size() != 0) {
					if (text.equals(expectedText)) {
						log.info("The text " + text + " is displayed");
						getElement(element).get(0).click();
						break;
					}
				}
			} catch (Exception e) {
			}
		}

	}
	protected void type(String text, By element) {
		findWeb(element).sendKeys(text);
	}
	
	protected void click(By element) {
		findWeb(element).click();	
	}	
	protected WebElement setElement(By element) {
		return findWeb(element);
	}
	protected void clear(By element) {
		findWeb(element).clear();
	}
	
	protected void selectLinkText(String value) {
		findWeb(By.linkText(value)).click();
	}

	protected void selectValue(By element, String value) {
		select = new Select(driver.findElement(element));
		select.selectByValue(value);
	}
	protected void selectVisibleText(By element, String value) {
		select = new Select(driver.findElement(element));
		select.selectByVisibleText(value);
	}

	
	protected WebElement findWeb(By element) {
		return driver.findElement(element);
	}
	
	protected void waitForWebElementVisibility(By locator, Integer... timeInSeconds) {
		int attempts = 0;
		while (attempts < 3) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeInSeconds.length > 0 ? timeInSeconds[0] : null);
				break;	
			} catch(StaleElementReferenceException e) {
				System.out.println(e);
			}
			attempts ++;
		}
				
	}
	protected void waitForWebElementToBeClickAble(By locator, Integer... timeInSeconds) {
		int attempts = 0;
		while (attempts < 3) {
			try {
				waitFor(ExpectedConditions.elementToBeClickable(locator), timeInSeconds.length > 0 ? timeInSeconds[0] : null);
				break;	
			} catch(StaleElementReferenceException e) {
				System.out.println(e);
			}
			attempts ++;
		}
				
	}
	protected void waitForPresenceOfWebElement(By locator, Integer... timeInSeconds) {
		int attempts = 0;
		while (attempts < 3) {
			try {
				waitFor(ExpectedConditions.presenceOfElementLocated(locator), timeInSeconds.length > 0 ? timeInSeconds[0] : null);
				break;	
			} catch(StaleElementReferenceException e) {
				System.out.println(e);
			}
			attempts ++;
		}
				
	}
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeInSeconds ) {
		timeInSeconds = timeInSeconds != null ? timeInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(condition);
	}

	protected String getText(By element) {
		return findWeb(element).getText();
	}


	protected void verifyObjectExist(By element, int wait){
		for (int x = 0; x <= wait; x++) {
			if (elementCount(element) != 0) {
				log.info("The "+element+" exist!!!");
				break;
			}else if (x==wait){
				fail("The "+element+" does not exist!!!");
			}
		}
	}
	protected void verifyObjectExist(By element, int wait, String message){
		for (int x = 0; x <= wait; x++) {
			if (elementCount(element) != 0) {
				log.info(message);
				break;
			}else if (x==wait){
				fail("The "+element+" does not exist!!!");
			}
		}
	}

}
