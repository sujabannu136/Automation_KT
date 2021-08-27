package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Reusable
{
	WebDriver driver;
	
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(id="nav-menu-link")
	private WebElement button_Menu;
	
	@FindBy(xpath="(//a[@role = 'button'])[3]")
	private WebElement button_Expand;
	
	@FindBy(linkText="World")
	private WebElement link_World;
	
	@FindBy(xpath="(//div[@class='youmay-like'])[1]//div[@class = 'card-detail']/a")
	private List<WebElement> text_TopStories;
	
	public void testIndiaTimes()
	{
		maximizeWindow();
		openIndiaTimes();
		
		clickElement(button_Menu);
		clickElement(button_Expand);
		clickElement(link_World);
		printList(text_TopStories);
		
		
	}

}
