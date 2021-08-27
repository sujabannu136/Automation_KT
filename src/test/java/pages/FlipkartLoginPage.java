package pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.FlipkartReusable;

public class FlipkartLoginPage extends FlipkartReusable
{
	WebDriver driver;
	
	public FlipkartLoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="(//input[@type = 'text'])[2]")
	private WebElement input_Username;
	
	@FindBy(xpath="//input[@type = 'password']")
	private WebElement input_Password;
	
	@FindBy(xpath="//button[@class = '_2KpZ6l _2HKlqd _3AWRsL']")
	private WebElement button_Login;
	
	
	
	public void login() throws IOException, InterruptedException
	{
		String filePath = "./files/FlipkartData.properties";
		
		String url = getValue(filePath, "URL");
		openFlipkart(url);
		
		reportInfo("Flipkart is launched");
	
		String username = getValue(filePath, "username");
		sendKeys(input_Username, username);
		
		String password = getValue(filePath, "password");
		sendKeys(input_Password, password);
		
		clickElement(button_Login);
		
		reportPass("Flipkart Login is successfull");
	}

}
