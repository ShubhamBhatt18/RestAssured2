package restAssuredBasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void courseSum() {
		JsonPath js=new JsonPath(PayLoad.CoursePrice());
		int count =js.getInt("courses.size()");
		
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		
		int sum=0;
		
		for(int i=0;i<count;i++) {
				int price=js.getInt("courses["+i+"].price");
				int copies=js.getInt("courses["+i+"].copies");
				int total=price * copies;
				sum=sum+total;
				System.out.println(sum);
				
			
		}
		Assert.assertEquals(totalAmount, sum);
	}
	
}
