package DemoProjects;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.Properties;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import files.resources;
import files.payLoad;
import files.ReusableMethods;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class GooglePlaceAPI {
	
	Properties properties= new Properties();
	@BeforeTest
	public void getData() throws IOException
	{	
		FileInputStream fis= new FileInputStream("C:\\Users\\mohtej01\\eclipse-workspace\\TstAssure\\src\\files\\env.properties");
		properties.load(fis);
		properties.get("HOST");		
	}
	
	@Test
	public void GrabAndDeleteDataTestCase() {
		
		//Adding the place
		RestAssured.baseURI= properties.getProperty("HOST");
		Response res=given().
		queryParam("Key", properties.getProperty("KEY")).
		body(payLoad.postData()).and().log().ifValidationFails().
		
		when().
		post(resources.AddPlace()).then().assertThat().
		statusCode(200).and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK")).
		
		//Extracting the response
		extract().response();
		//Storing into the variable and displaying in console
		JsonPath js=ReusableMethods.rawtoJson(res);
		String placeIDValue= js.get("place_id");
		System.out.println(placeIDValue); 
		//Take the value of placeid and delete it	
		
		given().
		queryParam("Key", properties.getProperty("KEY")).
		body("{"+
			"\"place_id\": \""+placeIDValue+"\""+
		"}").
		
		when().
		post(resources.DeletePlace()).then().assertThat().
		statusCode(200).and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK"));
	}

}
