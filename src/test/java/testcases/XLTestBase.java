package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import pages.XLUtilities;

public class XLTestBase 
{
	WebDriver driver;

	@BeforeTest
	public void openBrowser()
	{
	driver = XLUtilities.intializeDriver();
	}


}
