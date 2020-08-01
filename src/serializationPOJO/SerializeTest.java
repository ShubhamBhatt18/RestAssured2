package serializationPOJO;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

public class SerializeTest {

	public static void main(String[] args) {
	
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	AddPlace addP=new AddPlace();
	addP.setAccuracy(50);
	addP.setName("Frontline house");
	addP.setPhone_number("(+91) 983 893 3937");
	addP.setAddress("29, side layout, cohen 09");
	addP.setWebsite("ttps://rahulshettyacademy.com");
	addP.setLanguage("French-IN");
	
	List<String> typeList=new ArrayList<String>();
	typeList.add("shoe park");
	typeList.add("shop");
	addP.setTypes(typeList);
	 
	Location loc=new Location();
	addP.setLocation(loc);
	loc.setLat(-38.383494);
	loc.setLng(33.427362	);
	
	String response=given()
	.queryParam("key","qaclick123")
	.log().all().body(addP)
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	System.out.println("Response--->"+response);
	
	}
}
