package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utilities 
{
	WebDriver driver;
	public void intializeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https:facebook.com/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

}