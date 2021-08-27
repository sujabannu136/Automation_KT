package pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class XLloginPage extends XLUtilities 
{

	public XLloginPage(WebDriver driver)
	{
	super(driver);

	}


	WebDriver driver;

	@FindBy(xpath="//a[@id='navbarDropdown1']")
	private WebElement drpdwn;
	@FindBy(xpath="(//a[@class='dropdown-item'])[1]")
	private WebElement PrintBooking;
	@FindBy(xpath="//input[@id='ticket_num']")
	private WebElement TicketNo;
	@FindBy(xpath="//input[@id='phonenum']")
	private WebElement Phonenum;
	@FindBy(xpath="//input[@value='Retrieve ']")
	private WebElement retrieve;
	@FindBy(xpath="//ul[@class='main-nav-pills nav nav-pills mb-3 justify-content-center']/li")
	private List<WebElement> tabs;


	public void openApplication()
	{
	try {
	maximizeWindow();
	openAbhibus();
	tabsprint(tabs);

	clickElement(drpdwn);
	clickElement(PrintBooking);

	String ticketno= getDatafromExcel("./files/Data.xlsx","Sheet2","TC_1","Username");

	enterText(TicketNo, ticketno);
	String phoneno= getDatafromExcel("./files/Data.xlsx","Sheet2","TC_1","Password");
	enterText(Phonenum, phoneno);

	clickElement(retrieve);

	}
	catch (Exception e) {
	System.out.println("exception detials are ->"+e.getMessage());
	}

	}


}
