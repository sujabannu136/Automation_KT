package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.FlipkartReusable;

public class FlipkartHomePage extends FlipkartReusable
{
	public FlipkartHomePage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath="(//div[@class = 'eFQ30H'])[3]/a")
	private WebElement link_mobiles;
	
	public void clickMobiles() throws InterruptedException
	{
		
		//Thread.sleep(3000);
		
		reportInfo("trying to click Mobiles link");
		clickElement(link_mobiles);
		
		
	}

}
