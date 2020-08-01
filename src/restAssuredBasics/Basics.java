package restAssuredBasics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers.*;
import org.testng.Assert;

import files.PayLoad;

import static org.hamcrest.Matchers.equalTo;
public class Basics {

	public static void main(String args[]) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//check the body and server of response
		//Assertion to check the value of header and body
		//extract JSON into string
		
	String response=	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(PayLoad.AddPlace()).when().post("/maps/api/place/add/json")
	.then().log().all().assertThat().
	statusCode(200).body("scope", equalTo("APP")).header("server","Apache/2.4.18 (Ubuntu)")
	.extract().response().asString();
	
	System.out.println(response);
	
	//JsonPath--class used to take string as input and convert it into JSON.
	
	JsonPath js=new JsonPath(response);
	
	String place_id=js.getString("place_id");
	
	System.out.println("******************************************************");
	System.out.println("placeId value= "+place_id);
	
		
		//Add a place-->update place with New Address-->Get Place to validate if New Address is present in response
		
		
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("{\n" + 
			"\"place_id\":\""+place_id+"\",\n" + 
			"\"address\":\"70 winter walk, USA\",\n" + 
			"\"key\":\"qaclick123\"\n" + 
			"}")
	.when().put("maps/api/place/update/json")
	.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//Get a place
	
	String getPlaceResponse=given().log().all().queryParam("key","qaclick123").queryParam("place_id", place_id)
	.when().get("maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=new JsonPath(getPlaceResponse);
	String getAddress=js1.getString("address");
	System.out.println("Address get from Get request "+getAddress);
	
	Assert.assertEquals(getAddress,"70 winter walk, USA");
	}
	
	
	
}
