package api;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

public class AddGoogleMaps extends Utilities
{
	@Test
	public void AddLocation() throws FileNotFoundException
	{
		 RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String response =given().queryParam("key", "qaclick123").body(getAddBody())	 
			.when().post("/maps/api/place/add/json").
			then().extract().asString();
		
		System.out.println(response);
	}

}
