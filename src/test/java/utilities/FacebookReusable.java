package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class FacebookReusable 
{
static WebDriver driver;
	
	protected FacebookReusable(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static WebDriver intializeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
	
		 return driver;
	}
	
	public void maximizeWindow()
	{
		driver.manage().window().maximize();
	}
	
	public void openFacebook(String url)
	{
		try 
		{
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception detials : " + e.getMessage());
		}
	}
	
	public void sendKeysToInputFields(WebElement element, String value)
	{
		element.sendKeys(value);
	}
	
	public void clickElement(WebElement element)
	{
		element.click();
		
	}
	
	public String getValue(String fileName, String key) throws IOException
	{
		
		FileInputStream readFile = new FileInputStream(fileName);
		
		Properties prop = new Properties();
		prop.load(readFile);
		String value = prop.getProperty(key);
		
		readFile.close();
		
		return value;
	}
	
}







