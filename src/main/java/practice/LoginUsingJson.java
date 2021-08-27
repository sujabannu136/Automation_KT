package practice;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginUsingJson
{
	WebDriver driver;
	
	@BeforeMethod
	public void setUp ()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closing()
	{
		driver.close();
	}
	
	@Test(dataProvider ="testData")
	public void login(String username, String password) 
	{
		driver.findElement(By.id( "txtUsername")).sendKeys(username);
		
		driver.findElement(By.id( "txtPassword")).sendKeys(password);
		
		driver.findElement(By.id( "btnLogin")).click();		
	}
	
	 @DataProvider(name = "testData")
	public Object[][] readJson() throws IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();
		FileReader file = new FileReader( "MultiUserDetails.json");
		
		JSONObject jsonObj = (JSONObject) jsonParser.parse(file);
		JSONArray jsonArrObj = (JSONArray) jsonObj.get("loginDetails");
		
		Object [][] obj = new Object[jsonArrObj.size()][2];
		
		for(int i=0; i<jsonArrObj.size(); i++)
		{
			JSONObject userObj = (JSONObject) jsonArrObj.get(i);
			
			String username = (String) userObj.get("username");
			String password = (String) userObj.get("password");
			
			obj[i][0] = username;
			obj[i][1] = password;
			
		}
		
		return obj;
}

}
