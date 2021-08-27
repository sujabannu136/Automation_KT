package api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteGoogleMaps 
{
	@Test
	public void DeletePlace()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").body("{\r\n"
				+ " \"place_id\":\"a77e1921c74f8020f328985ccad14b43\"\r\n"
				+ "}\r\n"
				+ "").
		when().delete("/maps/api/place/delete/json").
		then().extract().asString();
		
		System.out.println(response);
	}

}
