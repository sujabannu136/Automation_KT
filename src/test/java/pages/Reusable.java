package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class Reusable 
{
	static WebDriver driver;
	
	Reusable(WebDriver driver)
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
	
	public void openIndiaTimes()
	{
		try 
		{
			driver.get("https://www.indiatimes.com/");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception detials are => "+e.getMessage());
		}
	}
	
	
	public void clickElement(WebElement element)
	{
		element.click();
	}
	
	public void printList(List<WebElement> list)
	{
		for (WebElement i : list) 
		{
			System.out.println(" ");
			System.out.println(i.getText());
				
		}
			
	}
	

}
