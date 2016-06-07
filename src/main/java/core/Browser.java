package core;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import pom.ObjectRepMap;


public class Browser {

	private WebDriver driver;
	
	public static final String LOCATOR_ID = "ID";
	public static final String LOCATOR_LINKTEXT = "LinkText";
	public static final String LOCATOR_XPATH = "XPath";
	public static final String LOCATOR_NAME = "name";		
	public static final String LOCATOR_CLASSNAME= "classname";
	public static final String LOCATOR_PARTIALLINK = "partiallink";

	private ObjectRepMap objectRepMap = new ObjectRepMap();

	public Browser() throws IOException
	{
		objectRepMap.createObjectStore();
	}

	public void openURL(final String url){
		if(driver == null)
			driver = new FirefoxDriver();
		driver.get(url);

		maximiseBrowser();
	}

	@Deprecated
	private WebElement getElement(String locatorName, String value)

	{
		WebElement element = null;

		if(locatorName.equals(LOCATOR_LINKTEXT))
		{
			element = driver.findElement(By.linkText(value));
		}
		else if(locatorName.equals(LOCATOR_NAME)){
			element = driver.findElement(By.name(value));

		}else if (locatorName.equals(LOCATOR_PARTIALLINK))
		{
			element = driver.findElement(By.partialLinkText(value));

		}else if (locatorName.equals(LOCATOR_ID))
		{
			element =  driver.findElement(By.id(value));

		}else if (locatorName.equals(LOCATOR_CLASSNAME))
		{
			element = driver.findElement(By.className(value));

		}else if (locatorName.equals(LOCATOR_XPATH))
		{
			element = driver.findElement(By.xpath(value));

		}

		return (element);

	}
	
	@Deprecated
	public void click(String locatorName, String value) {
		//getElement(locatorName, value).click();
		WebElement element = getElement(locatorName,value);
		element.click();
	}

	@Deprecated
	public void enterText(String locatorName, String value, String textToEnter) {
		WebElement element = getElement(locatorName, value);
		element.clear(); // to clear pre values 
		element.sendKeys(textToEnter);
	}
	
	public void closeAllBroser () {
		driver.quit();
	}

	public void maximiseBrowser() {
		driver.manage().window().maximize();
	}
	
	public void click(final String logicalName){
		//WebElement element = driver.findElement(objectRepMap.getLocator(logicalName));
		WebElement element = getWebElementFromLogicalName(logicalName);
		element.click();
	}
	
	public void enterText(String logicalName, String textToenter) {
		WebElement element = getWebElementFromLogicalName(logicalName);
		element.clear();
		element.sendKeys(textToenter);
	}
	
	private WebElement getWebElementFromLogicalName(final String logicalName){
		return driver.findElement(objectRepMap.getLocator(logicalName));
	}
	
	public void updateObjectRepository(final String logicalName, final String locatorType, final String value){
		objectRepMap.setValueForKey(logicalName, locatorType+"=>"+value);
	}
	
	public void selectByLable(String logicalName,String label)
	{
		
		Select select= new Select(getWebElementFromLogicalName(logicalName));
		select.selectByVisibleText(label);
				
	}
	public void selectByIndex(String logicalName,int index)	{
		Select select = new Select(getWebElementFromLogicalName(logicalName));
		select.selectByIndex(index);
	}
	
	public void selectByValue(String logicalName,String value ) {
		Select select = new Select(getWebElementFromLogicalName(logicalName));
		select.selectByValue(value);
	}
	public String getSelectedValueInDropdown(String logicalName){
		Select select = new Select(getWebElementFromLogicalName(logicalName));
		String selectedValueIndropDown = select.getFirstSelectedOption().getText();
		return selectedValueIndropDown;
	}
	
	public boolean isTextPresentOnPage(String text){
		WebElement element = driver.findElement(By.tagName("body"));
		String webText = element.getText();
		if(webText.contains(text))
			return true;
		return false;
	}
	
	public String getElementText(String logicalName){
		WebElement element = getWebElementFromLogicalName(logicalName);
		String elementText = element.getText();
		return elementText;
	}
}

