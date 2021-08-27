package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver;

	@FindBy(id="email")
	private WebElement input_Username;
	@FindBy(name="pass")
	private WebElement input_Password;
	@FindBy(xpath="//button[@name='login']")
	private WebElement btn_SignIN;

	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
public void loginIntoApplication()
{
	try 
	{
		input_Username.sendKeys("206589");
		input_Password.sendKeys("P@ssowrd");
		btn_SignIN.click();
	} 
	catch (Exception e) 
	{
		System.out.println("exception detials are ->   "+e.getMessage());
	}
}

}