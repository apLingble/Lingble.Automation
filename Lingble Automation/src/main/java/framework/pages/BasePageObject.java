package framework.pages;


import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Please do not modify, Kindly let the author know if you need changes.
 * @author alexander.v.pangilinan
 * */

public class BasePageObject<T> {
	public WebDriver driver;
	public Logger log = null;
	public WebDriverWait wait;
	public Select select;
	public Actions action;
	private String filenamepath;

	protected final String java_script =
    		"var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe" +
    		"ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun" +
    		"ction(format,data){this.items[format]=data;this.types.append(for" +
    		"mat);},getData:function(format){return this.items[format];},clea" +
    		"rData:function(format){}};var emit=function(event,target){var ev" +
    		"t=document.createEvent('Event');evt.initEvent(event,true,false);" +
    		"evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('" +
    		"dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit(" +
    		"'drop',tgt);emit('dragend',src);";

	protected BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
		this.wait = new WebDriverWait(driver, 100);
	}
	
	@SuppressWarnings("unchecked")
	protected T getPage(String url) {
		driver.get(url);
		return (T) this;
	}
	public void clickElementJS(By element) {
		WebElement webElement = driver.findElement(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
	}
	public  void switchToDefaultContent(){
		driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(driver.getWindowHandles().size() - 1));
		sleep(Duration.ofSeconds(3));
	}
	public void waitHeaderTitle(String headerTitle, int wait){
		for (int x = 0; x <= wait; x++) {
			if (getTitle().equals(headerTitle)) {
				break;
			}
		}
	}

	public void switchTab(String headerTitle) {
		String currentHandle = null;
		for (int t=0; t<60; t++) {
			try {
				final Set<String> handles = driver.getWindowHandles();
				if (handles.size() > 1) {
					currentHandle = driver.getWindowHandle();
				}
				if (currentHandle != null) {
					for (final String handle : handles) {
						driver.switchTo().window(handle);
						waitUntilPageready();
						if (driver.getTitle().equals(headerTitle) && !currentHandle.equals(handle)) {
							break;
						}
					}
				} else {
					for (final String handle : handles) {
						driver.switchTo().window(handle);
						waitUntilPageready();
						if (driver.getTitle().equals(headerTitle)) {
							break;
						}
					}
				}
			} catch (Exception e) {
				log.info("Trying to switch to window " + headerTitle);
			}
		}

	}
	public void switchTabs(String header){
		driver.switchTo().window(header);
		waitUntilPageready();
	}
	public void switchToIframe(By element){
		log.info("Switch to frame "+driver.findElement(element).getAttribute("title"));
		driver.switchTo().frame(driver.findElement(element));
	}
	public void switchToDefaultFrame(){
		log.info("Switch to default frame.");
		driver.switchTo().defaultContent();
	}
	public void closeTabs(String header){
		driver.switchTo().window(header);
		waitUntilPageready();
		driver.close();
	}
	public void closeTab(String headerTitle) {
		String currentHandle = null;
		try {
			final Set<String> handles = driver.getWindowHandles();
			if (handles.size() > 1) {
				currentHandle = driver.getWindowHandle();
			}
			if (currentHandle != null) {
				for (final String handle : handles) {
					driver.switchTo().window(handle);
					waitUntilPageready();
					if (driver.getTitle().equals(headerTitle) && !currentHandle.equals(handle)) {
						driver.close();
						break;
					}
				}
			} else {
				for (final String handle : handles) {
					driver.switchTo().window(handle);
					waitUntilPageready();
					if (driver.getTitle().equals(headerTitle)) {
						driver.close();
						break;
					}
				}
			}
		} catch (Exception e) {
			log.info("Switching tabs failed");
		}
	}
	public void switchToMainTab(){
		driver.switchTo().window(null);
		waitUntilPageready();
	}

	protected void moveTo(By element) {
		action = new Actions(driver);
		action.moveToElement(findWeb(element)).build().perform();
	}
	
	protected void actionDragAndDrop(By fromElement, By toElement) throws InterruptedException {
		action = new Actions(driver);
		int xto = findWeb(toElement).getLocation().x;
		int yto = findWeb(toElement).getLocation().y;
		action.clickAndHold(findWeb(fromElement)).moveByOffset(xto, yto).perform();
		action.release().build().perform();
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
	protected void dragSourceToTargetObjectLocation(By sourceObject, By targetObject) {
		WebElement LocatorFrom = driver.findElement(sourceObject);
	    WebElement LocatorTo = driver.findElement(targetObject);
	    String xto=Integer.toString(LocatorTo.getLocation().x);
	    String yto=Integer.toString(LocatorTo.getLocation().y);
	    ((JavascriptExecutor)driver).executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
	    "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
	    LocatorFrom,xto,yto);
		
	}
	protected void dragSourceToTargetObject(By sourceObject, By targetObject) {
		WebElement LocatorFrom = driver.findElement(sourceObject);
	    WebElement LocatorTo = driver.findElement(targetObject);
	    ((JavascriptExecutor) driver).executeScript(java_script, LocatorFrom, LocatorTo);
	}
	protected void waitUntilPageready() {
		while(waitForJStoLoad() != true){
			log.info("Waiting for page to be ready");
		}
	}
	private boolean waitForJStoLoad() {

		WebDriverWait wait = new WebDriverWait(driver, 100);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsDriver().executeScript("return jQuery.active") == 0);
				}
				catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsDriver().executeScript("return document.readyState")
						.toString().equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
		
	protected void waitForElementToBeInvisible(By Element, int waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				if (getElement(Element).size() == 0) {
					log.info("The element is now hidden.");
					break;
				}
			} catch (Exception e) {
			}
		}
	}
	protected Boolean checkElementIfInvisible(By Element, int waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				if (getElement(Element).size() == 0) {
					log.info("The element is now hidden.");
					return true;
				}
			} catch (Exception e) {
			}
		}
		return false;
	}
	public void  waitForElementToBeVisible(By Element, int waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				if (getElement(Element).size() != 0) {
					log.info("The element is now visible");
					break;
				}
			} catch (Exception e) {
				log.info(e);
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
	

	protected void waitForPresenceOfText(By element, String expectedText, Integer waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				String text = getText(element).trim();
				log.info(text);
				if (elementCount(element) != 0) {
					if (text.equals(expectedText)) {
						log.info("The text " + text + " is displayed");
						break;
					}
				}
			} catch (Exception e) {
			}
		}

	}
	protected boolean waitForLabelToBeVisible(By element, String expectedLabel, Integer waitingCount) {
		for (int x = 0; x <= waitingCount; x++) {
			try {
				String text = getText(element).trim();
				if (elementCount(element) != 0) {
					if (text.equals(expectedLabel)) {
						log.info("The Label " + text + " is displayed");
						return true;
					}
				}
			} catch (Exception e) {
			}
		}
		return false;
	}
	public void selectRadioButton(By element) {
		for (int t=0; t<3;t++) {
			clickElementJS(element);
			if (findWeb(element).isSelected()) {
				break;
			}
		}
	}
	protected void typeKeyBoardKeys(Keys enter, By element) {
		findWeb(element).sendKeys(enter);
	}
	protected void typeKeyBoardChordKeys(CharSequence enter, By element) {
		findWeb(element).sendKeys(enter);
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
	protected void dragAndDropElement(WebElement elementToBeDrag, WebElement droppingPoint) {
		((JavascriptExecutor)driver).executeScript(java_script, elementToBeDrag, droppingPoint);
	}

	public void scrollToElement(By element){
		WebElement webElement = driver.findElement(element);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
	}
	public void scrollToText(By element,String textToLocate){
		for(int y=1; y<=5; y++){
			scrollToElement(element);
			if(waitForLabelToBeVisible(element,textToLocate,2)==true){
				break;
			}
		}
	}
	public void scrollToTop(By element){
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, 0);");
	}

	protected void uploadFilesByID(String fileLocation, String elementID) {
		jsDriver().executeScript("var e = document.getElementById('"+elementID+"');" +
				"e.style.left='';" +
				"e.style.position='static';" +
				"e.style.display='block';");

		WebElement element = driver.findElement(By.id(elementID));
		element.sendKeys(fileLocation);
		sleep(Duration.ofMillis(500L));
	}
	protected void uploadFile(String fileLocation, By element) {
		type(fileLocation,element);
		sleep(Duration.ofMillis(500L));
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
				log.info(e);
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
				log.info(e);
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
				log.info(e);
			}
			attempts ++;
		}
				
	}
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeInSeconds ) {
		timeInSeconds = timeInSeconds != null ? timeInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(condition);
	}
	
	protected String getTitle() {
		return driver.getTitle();
	}
	
	protected String getText(By element) {
		return findWeb(element).getText();
	}
	protected String getAttribute(By element, String attr) {
		return findWeb(element).getAttribute(attr);
	}
	protected void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

	protected JavascriptExecutor jsDriver() {
		return (JavascriptExecutor) driver;
	}
	 
	protected void validateTableData(String filepath, int rowNumber, By element) {
		 String csvFile = System.getProperty("user.dir")+filepath;

	        try {
	        	BufferedReader reader = new BufferedReader(new FileReader(csvFile));
	        	List<String> lines = new ArrayList<>();
	        	String line = null;
	        	while ((line = reader.readLine()) != null) {
	        	    lines.add(line);
	        	}
	        	String RowData = lines.get(rowNumber);
	        	String[] row = RowData.split(",");
	        	for(int x=0;x<=row.length;x++) {
	        		log.info("Country id="+row[x]);
	        	}

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
	protected void verifyObjectExist(By element, int wait){
		for (int x = 0; x <= wait; x++) {
			if (elementCount(element) != 0) {
				log.info("The "+element+" exist!!!");
				break;
			}else if (x==wait){
				moveTo(element);
				log.error("The "+element+" does not exist!!!");
			}
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}// try
		catch (Exception e) {
			return false;
		}// catch
	}
	protected void dismissAlert() {
		if (isAlertPresent()) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}

	protected void takeNormalScreenshot(String screenShotFileName) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		SimpleDateFormat scformat = new SimpleDateFormat("_MM_dd_yyyy");
		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/resources/Enterprise/TestData/Screenshots/Screenshots"+scformat.format(calendar.getTime());
		Path directory = Paths.get(reportDirectory);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			File destFile = new File((String) reportDirectory + "/" +screenShotFileName + "_"+ formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			String filePath = destFile.toString();
			String path = "<img src=\""+filePath+"\" height='800' width='1200' alt=\"\"\"/\" / >";
			Reporter.log(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
