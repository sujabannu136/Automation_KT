package utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportsClass 
{
	static ExtentReports extent;
	
	public static ExtentReports customReport(String reportName)
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss_SSS");
		
		extent = new ExtentReports("./reports/" + reportName + "_" + dateFormat.format(date) + ".html");
		extent.addSystemInfo("Username", "Sushma").addSystemInfo("OS", "Windows10").addSystemInfo("Browser", "Chrome").addSystemInfo("Application", "Flipkart");
		
		extent.loadConfig(new File("./files/extent-config.xml"));	
		return extent;
	}

}
