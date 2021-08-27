package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.FacebookLoginPage;
import utilities.FacebookReusable;

public class FacebookTestCase 
{
WebDriver driver;
	
	@BeforeTest
	public void openBrowser()
	{
		driver = FacebookReusable.intializeDriver();
	}
	
	@Test
	public void login() throws IOException
	{
		FacebookLoginPage Olp = new FacebookLoginPage(driver);
		Olp.testFacebook();
		
	}
	
	@AfterTest
	public void close()
	{		
		driver.close();
	}	

}
