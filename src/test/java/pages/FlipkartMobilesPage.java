package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.FlipkartReusable;

public class FlipkartMobilesPage extends FlipkartReusable
{

	public FlipkartMobilesPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="(//div[@class = '_24_Dny'])[1]")
	private WebElement checkbox_6GB;
	
	@FindBy(xpath="(//div[@class = '_24_Dny'])[11]")
	private WebElement checkbox_Samsung;
	
	@FindBy(xpath="//div[@class = '_4rR01T']")
	private List<WebElement> text_title;
	
	public void getMobiles() throws InterruptedException
	{
		
		clickElement(checkbox_6GB);
		reportInfo("checkbox 6GB is selected");
		
		clickElement(checkbox_Samsung);
		reportInfo("checkbox SAMSUNG is selected");
		
		Thread.sleep(3000);
		
		printList(text_title);
		reportPass("List of mobiles are successfully printed");
		
	}

}
