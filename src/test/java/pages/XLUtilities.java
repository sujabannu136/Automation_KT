package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class XLUtilities 
{
	static WebDriver driver;

	XLUtilities(WebDriver driver)
	{
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}



	public static WebDriver intializeDriver()
	{
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	driver = new ChromeDriver();
	return driver;
	}
	
	public void tabsprint(List<WebElement> tabs)
	{
		System.out.println(tabs.size());

		for (WebElement webElement : tabs)
		{
		System.out.println(webElement.getText());
	
		}
	}

	public void maximizeWindow()
	{
		driver.manage().window().maximize();
	}

	public void enterText(WebElement element, String value)
	{
		element.sendKeys(value);
	}
	public void clickElement(WebElement element)
	{
		element.click();
	}
	
	public void openAbhibus()
	{
		try 
		{
			driver.get("https://www.abhibus.com/");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} 
		catch (Exception e)
		{
			System.out.println("Exception detials are => "+e.getMessage());
		}
	}
	
	
	
	public String getDatafromExcel(String filepath, String sheetname, String rowheader, String coloumnheader) throws IOException
	{
		File file=new File(filepath);
		FileInputStream fis= new FileInputStream(file);
		
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheet(sheetname);
		
		XSSFRow row= sheet.getRow(0);
		XSSFCell cell;
		
		System.out.println("First cell number = " + row.getFirstCellNum());
		System.out.println("Last cell number = " + row.getLastCellNum());
		
		int rowcount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		int coloumncount= row.getLastCellNum() - row.getFirstCellNum();
		int rownumber=0;
		int coloumnnumber=0;
		
		int j=0;
		while(j<coloumncount)
		{
			cell =sheet.getRow(0).getCell(j);
			String str = cell.getStringCellValue();
			if(str.equals(coloumnheader))
			{
				coloumnnumber=j;
				j=coloumncount;
			}
			j++;
		}
	
		int i=0;
		rowcount++;
		while(i<rowcount)
		{
			cell =sheet.getRow(i).getCell(0);
			String str = cell.getStringCellValue();
			
			if(str.equals(rowheader))
			{
				rownumber=i;
				i=rowcount;
			}
			i++;
		}
		
		System.out.println("rownumber = " + rownumber );
		System.out.println("columnnumber = " + coloumnnumber);
	
		cell =sheet.getRow(rownumber).getCell(coloumnnumber);
		String cellvalue=cell.getStringCellValue();
		return cellvalue;
	
	}


}
