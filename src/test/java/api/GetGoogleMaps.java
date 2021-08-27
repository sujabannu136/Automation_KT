package api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetGoogleMaps 
{
	@Test
	public void GetPlace()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = given().queryParam("key", "qaclick123").queryParam("place_id","3dd4c4d00ace8a52ffcf3e5b2edeeae0").
		when().get("/maps/api/place/get/json").
		then().log().all().assertThat().statusCode(200).body("name", equalTo("Frontline house"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response();
		
		System.out.println("Address : " + response.jsonPath().getString("address"));
		System.out.println("Phone Number : " + response.jsonPath().getString("phone_number"));
		
		//System.out.println(response);
	}

}
