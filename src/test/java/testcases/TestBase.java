package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import pages.Utilities;

public class TestBase 
{
	WebDriver driver;
	@BeforeTest
	public void openBrowser()
	{		
		Utilities utils = new Utilities();
		utils.intializeDriver();
	}
}
