package restAssuredBasics;

import org.testng.Assert;

import files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	JsonPath js=new JsonPath(PayLoad.CoursePrice());
	
	//Print number of courses return by API
	int count =js.getInt("courses.size()");  //give the complete courses count
	System.out.println(count);
	
	//Print Purchase Amount
	int totalAmount=js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
	
	//Print title of first course
	String title=js.get("courses[0].title");
	System.out.println(title);
	
	//print all titles and respective prices
	
	for(int i=0;i<count;i++) {
		String courseTitle=js.get("courses["+i+"].title");
		
		
		System.out.println(courseTitle);
		int coursePrices=js.get("courses["+i+"].price");
		System.out.println(coursePrices	);
	}
	
	//Print number of copies sold by RPA
	for(int i=0;i<count;i++) {
		String courseTitle=js.get("courses["+i+"].title");
		
		if(courseTitle.equalsIgnoreCase("RPA")) {
			System.out.println("number of copies sold by RPA "+js.get("courses["+i+"].copies").toString());
			break;
		}
		
		
			
		}
	//
//	int sum=0;
//	int copies=0;
//	int price=0;
//	int total=0;
//	for(int i=0;i<count;i++) {
//			price=js.getInt("courses["+i+"].price");
//			copies=js.getInt("course["+i+"].copies");
//			total=price * copies;
////			sum=sum+total;
//			
//		
//	}
//	
}

}
