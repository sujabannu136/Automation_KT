package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.FacebookReusable;

public class FacebookLoginPage extends FacebookReusable
{
WebDriver driver;
	
	public FacebookLoginPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(id="email")
	private WebElement input_Username;
	
	@FindBy(id="pass")
	private WebElement input_Password;
	
	@FindBy(name="login")
	private WebElement button_Login;
	
	public void testFacebook() throws IOException
	{
		maximizeWindow();
		
		openFacebook(getValue("./files/LoginDetails.properties", "URL"));
		
		sendKeysToInputFields (input_Username, getValue("./files/LoginDetails.properties", "username"));
		
		sendKeysToInputFields (input_Password, getValue("./files/LoginDetails.properties", "password"));
		
		clickElement(button_Login);
		
	}

}
