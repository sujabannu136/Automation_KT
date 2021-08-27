package api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddUpdateGet extends Utilities
{
	private String filePath = "./files/APIData.xlsx";
	private String placeID;
	
	@Test
	public void addPlace() throws IOException
	{
		startTest("Adding location details using POST method");
		String baseURI = getDatafromExcel(filePath, "Sheet1", "add", "BaseURI");
		String addKey =  getDatafromExcel(filePath, "Sheet1", "add", "key");
		String addValue = getDatafromExcel(filePath, "Sheet1", "add", "value");
		String addResource = getDatafromExcel(filePath, "Sheet1", "add", "Resource");
		
		reportInfo("Base URL : "+baseURI+"");
		reportInfo("key : "+addValue+"");
		reportInfo("Resource : "+addResource+"");
		reportInfo("Body : "+getAddBody()+"");
		
		RestAssured.baseURI = baseURI;
		System.out.println("Response of POST method : ");
		Response responseAdd =	given().log().all().queryParam(addKey, addValue).body(getAddBody()).
				when().post(addResource).
				then().log().all().assertThat().statusCode(200).extract().response();
		
		placeID = responseAdd.jsonPath().getString("place_id");
		
		String status = responseAdd.jsonPath().getString("status");
		if(status.equals("OK"))
		{
			reportPass("Location is added successfully");
			reportInfo(responseAdd.asString());
		}
		else
			reportFail("Adding location is failed");
		
	}
	
	@Test(priority = 1)
	public void updatePlace() throws IOException
	{
		startTest("Updating address using PUT method");
		String putKey =  getDatafromExcel(filePath, "Sheet1", "update", "key");
		String putValue = getDatafromExcel(filePath, "Sheet1", "update", "value");
		String putResource = getDatafromExcel(filePath, "Sheet1", "update", "Resource");
		
		
		reportInfo("key : "+putValue+"");
		reportInfo("Resource : "+putResource+"");
		reportInfo("Body : "+getPutBody(placeID)+"");
		
		System.out.println("Response of PUT method : ");
		Response responsePut = 
				given().queryParam(putKey, putValue).body(getPutBody(placeID)).
		when().put(putResource).
		then().log().all().assertThat().body("msg", equalTo("Address successfully updated")).extract().response();
		
		String msg = responsePut.jsonPath().getString("msg");
		if(msg.equals("Address successfully updated"))
		{
			reportPass(msg);
			reportInfo(responsePut.asString());
		}
		else
			reportFail(msg);
	}
	
	@Test(priority = 2)
	public void getPlace() throws IOException
	{
		startTest("Retrieve location details using GET method");
		String getKey =  getDatafromExcel(filePath, "Sheet1", "get", "key");
		String getValue = getDatafromExcel(filePath, "Sheet1", "get", "value");
		String getResource = getDatafromExcel(filePath, "Sheet1", "get", "Resource");
		
		reportInfo(""+getKey+" : "+getValue+"");
		reportInfo("Resource : "+getResource+"");
		
		System.out.println("Response of GET method : ");
		Response responseGet = given().queryParam(getKey, getValue).queryParam("place_id", placeID).
		when().get(getResource).
		then().log().all().extract().response();
		
		String address = responseGet.jsonPath().getString("address");
		if(responseGet.statusCode()==200)
		{
			reportPass("Location details are fetched successfully");
			reportInfo(responseGet.asString());
		}
		
		else
		{
			reportFail("Get operation failed, looks like place_id  doesn't exists");
		}
		
		System.out.println("Updated Address is : " + address);
		
	}
	
	@Test(priority = 3)
	public void deletePlace() throws IOException
	{
		startTest("Deleting location using DELETE method");
		String deleteKey =  getDatafromExcel(filePath, "Sheet1", "delete", "key");
		String deleteValue = getDatafromExcel(filePath, "Sheet1", "delete", "value");
		String deleteResource = getDatafromExcel(filePath, "Sheet1", "delete", "Resource");
		
		reportInfo("key : "+deleteValue+"");
		reportInfo("Resource : "+deleteResource+"");
		reportInfo("Body : "+getDeleteBody(placeID)+"");
		
		System.out.println("Response of DELETE method : ");
		Response responseDelete = given().queryParam(deleteKey, deleteValue).body(getDeleteBody(placeID)).
		when().delete(deleteResource).
		then().log().all().assertThat().statusCode(200).extract().response();
		
		String status = responseDelete.jsonPath().getString("status");
		if(status.equals("OK"))
		{
			reportPass("Location is deleted successfully");
			reportInfo(responseDelete.asString());
		}
		else
			reportFail("Delete operation failed, looks like the data doesn't exists");
		
		
	}
	
	
	@BeforeMethod
	public void endTest()
	{
		report.endTest(logger);
	}

}
