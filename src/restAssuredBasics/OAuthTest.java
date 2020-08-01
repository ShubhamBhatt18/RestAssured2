package restAssuredBasics;

import static io.restassured.RestAssured.given;



import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;



import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;



import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import io.restassured.response.ResponseBody;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;

public class OAuthTest {

	public static void main(String args[]) throws InterruptedException {

		// System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/driver/chromedriver");
		//
		// WebDriver driver=new ChromeDriver();
		// driver.get("https://accounts.google.com/signin/oauth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=fjdssd&o2v=2&as=XXdZDBX9Ag4ctWqHxBLbWA&flowName=GeneralOAuthFlow");
		//
		// driver.findElement(By.xpath("//input[@type='email']")).sendKeys("shubhambhatt63804@gmail.com");
		// driver.findElement(By.cssSelector("div[id='identifierNext']")).click();
		// Thread.sleep(4000);
		// driver.findElement(By.cssSelector("input[type='password']")).sendKeys("9897525769p@P");
		// driver.findElement(By.cssSelector("button[id='passwordNext']")).click();
		//
		// to capture the current browser url

		// String url=driver.getCurrentUrl();

		// due to gmail login issue as it asks to verify device we are manually hitting
		// the url and extracting the code from it

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2FzgHrxYc8tOLv2xzdmoDsrjMtWRF2tNetO4He6n95HS984g7JTkgd5NnkDBOe06rbrEn0mdQlZm9QtNr0PDCpfds&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";

		// extract the first index string.
		String partialCode = url.split("code=")[1];

		// extract the zeroth index string
		String codes = partialCode.split("&scope")[0];
		System.out.println(codes);

		String acessTokenResponse = given().urlEncodingEnabled(false).queryParam("code", codes)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		System.out.println("PLEASE LOOK-->****" + acessTokenResponse);

		JsonPath js = new JsonPath(acessTokenResponse);
		String AccessToken = js.getString("access_token");
//this will give course list of rahul shetty academy
		// String response=given().queryParam("access_token",AccessToken).expect()
		// .when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		//
		// System.out.println("FINAL RESPONSE--->"+response);

		GetCourse gc = given().queryParam("access_token", AccessToken).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

		int apiListSize = gc.getCourses().getApi().size();

		for (int i = 0; i < apiListSize; i++) {
			if (gc.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("Price of SoapUI-->"+gc.getCourses().getApi().get(i).getPrice());
			}
		}

	}
	
}
