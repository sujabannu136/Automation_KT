package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.Reusable;

public class TestCase 
{
	WebDriver driver;
	
	@BeforeTest
	public void openBrowser()
	{
		driver = Reusable.intializeDriver();
	}
	
	@Test
	public void loginandLogout()
	{
		HomePage hp = new HomePage(driver);
		hp.testIndiaTimes();
	}
	
	@AfterTest
	public void close()
	{
		driver.close();
	}

}
