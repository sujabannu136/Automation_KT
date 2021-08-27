package api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PUTGoogleMaps 
{
	@Test
	public void UpdatePlace()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response =
				given().queryParam("key", "qaclick123").body("{\r\n"
						+ "\"place_id\":\"6c24fcc5f773dbcc61a51288c255e245\",\r\n"
						+ "\"address\":\"70 winter walk, USA\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "").
		when().put("/maps/api/place/update/json").
		then().extract().asString();
		
		System.out.println(response);
	}

}
