package restAssuredBasics;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {

	@Test(dataProvider="getData")
	public void ADddBook(String myisbn,String myaisle) {
		RestAssured.baseURI="http://216.10.245.166";
		
		String response=given().header("Content-Type","application/json").body(PayLoad.addBook(myisbn,myaisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		
	    String id=js.get("ID");
	    System.out.println(id);
	    
		
	}
	
	@DataProvider
	public Object getData() {
		Object[][] data=new Object[3][2];
		
		data[0][0]="hhjhj";
		data[0][1]="223445678";
		
		data[1][0]="cnmmvdjklf";
		data[1][1]="asa2432435";
		
		data[2][0]="fiehfeinka";
		data[2][1]="7849839des";
		
		return data;
		
	}
}
