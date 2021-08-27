package api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utilities 
{
	public String getAddBody()
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public String getPutBody(String placeID)
	{
		return "{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public String getDeleteBody(String placeID)
	{
		return "{\r\n"
		+ "\r\n"
		+ "    \"place_id\": \""+placeID+"\"\r\n"
		+ "}";
		
	}
	
	public String getDatafromExcel(String filepath, String sheetname, String rowheader, String coloumnheader) throws IOException
	{
		File file=new File(filepath);
		FileInputStream fis= new FileInputStream(file);
		
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheet(sheetname);
		
		XSSFRow row= sheet.getRow(0);
		XSSFCell cell;
		
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
		
		cell = sheet.getRow(rownumber).getCell(coloumnnumber);
		String cellvalue = cell.getStringCellValue();
		return cellvalue;
	
	}
	
	public ExtentReports report;
	public  ExtentTest logger;
	
	@BeforeTest
	public void createReport()
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss_SSS");
		System.out.println("creating report");
		report = new ExtentReports("./reports/Google Maps API Automation Report " + dateFormat.format(date) + ".html");
		//report = new ExtentReports("./reports/APIReport.html");
		report.addSystemInfo("Username", "Sushma").addSystemInfo("OS", "Windows10").addSystemInfo("Browser", "Chrome").addSystemInfo("API", "Google Maps");
		
		report.loadConfig(new File("./files/extent-config.xml"));
	
	}

	public void reportInfo(String reportString)
	{
		logger.log(LogStatus.INFO, reportString);
	}
	
	public void reportPass(String reportString)
	{
		logger.log(LogStatus.PASS, reportString);
	}
	
	public void reportFail(String reportString)
	{
		logger.log(LogStatus.FAIL, reportString);
	}
	
	public void startTest(String testName)
	{
		logger = report.startTest(testName);

	}
	
	@AfterTest
	public void endReport()
	{
		report.flush();
	}

}
