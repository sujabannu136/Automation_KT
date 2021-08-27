package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FlipkartReusable 
{
	static WebDriver driver;
	
	public static ExtentReports report = ExtentReportsClass.customReport("FlipkartReport");
	public static ExtentTest logger;
	
	public static void reportInfo(String reportString)
	{
		logger.log(LogStatus.INFO, reportString);
	}
	
	public static void reportPass(String reportString)
	{
		logger.log(LogStatus.PASS, reportString);
	}
	
	public static void startTest(String testName)
	{
		logger = report.startTest(testName);

	}
	
	
	  public static String takeScreenshotOnFailure() throws IOException 
	  {
		  
	  System.out.println("capturing screenshot");
	  TakesScreenshot takeScreenshot =(TakesScreenshot)driver;
	  File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
	  
	  String screenshotPath = "./screenshots/FlipkartMobileFailure_new.png";
	  File destFile = new File(screenshotPath);
	  
	  System.out.println("adding screenshot to reports");
	  FileUtils.copyFile(sourceFile, destFile);
	  
	  return destFile.getAbsolutePath();
	  
	  }
	 

	protected FlipkartReusable(WebDriver driver)
	{
		FlipkartReusable.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static WebDriver intializeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	
		 return driver;
	}
	
	public static WebDriver getDriver()
	{
		return FlipkartReusable.driver;
	}
	
	public void waitUntilClickable(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void openFlipkart(String url)
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
	
	public String getValue(String filePath, String key) throws IOException
	{
		
		FileInputStream readFile = new FileInputStream(filePath);
		
		Properties prop = new Properties();
		prop.load(readFile);
		String value = prop.getProperty(key);
		
		readFile.close();
		
		return value;
	}
	
	public void clickElement(WebElement element)
	{
		element.click();
	}
	
	public void sendKeys(WebElement element, String value)
	{
		element.sendKeys(value);
	}
	
	public void printList(List<WebElement> list)
	{
		
		try {
		for (WebElement i : list) 
		{
			System.out.println(i.getText());
		}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
			
	}
	
	

}
