package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.FlipkartHomePage;
import pages.FlipkartLoginPage;
import pages.FlipkartMobilesPage;
import utilities.FlipkartReusable;

public class FlipkartTest
{
	WebDriver driver;
	
	@BeforeTest
	public void open()
	{
		driver = FlipkartReusable.intializeDriver();
	}
	
	@Test
	public void login() throws IOException, InterruptedException
	{
		FlipkartLoginPage lp = new FlipkartLoginPage(driver);
		FlipkartReusable.startTest("Flipkart login test");
		lp.login();
		
	}
	
	@Test(dependsOnMethods = "login")
	public void select() throws InterruptedException
	{
		this.driver =  FlipkartReusable.getDriver();
		FlipkartHomePage hp = new FlipkartHomePage(driver);
		FlipkartReusable.startTest("Flipkart selecting mobiles test");
		hp.clickMobiles();
		
		this.driver =  FlipkartReusable.getDriver();
		FlipkartMobilesPage mp = new FlipkartMobilesPage(driver);
		mp.getMobiles();
	}
	
	
	@AfterMethod 
	public void setTestResult(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			FlipkartReusable.logger.log(LogStatus.FAIL, result.getName());
			FlipkartReusable.logger.log(LogStatus.FAIL, result.getThrowable());
			FlipkartReusable.logger.log(LogStatus.FAIL, FlipkartReusable.logger.addScreenCapture(FlipkartReusable.takeScreenshotOnFailure()));
		}
		
		FlipkartReusable.report.endTest(FlipkartReusable.logger);
	}
	
	@AfterTest
	public void close()
	{
		FlipkartReusable.report.flush();
		driver.close();
	}

}
