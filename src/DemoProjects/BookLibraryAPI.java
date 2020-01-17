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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookLibraryAPI {
	Properties properties= new Properties();
	@BeforeTest
	public void getURL() throws IOException
	{	
		FileInputStream fis= new FileInputStream("C:\\Users\\mohtej01\\eclipse-workspace\\TstAssure\\src\\files\\env.properties");
		properties.load(fis);
		properties.get("HOST");		
	}
	
	@Test(dataProvider= "BooksData")
	public void AddBookAndDelete(String isbn, String aisle) 
	{
		//Passing the URL from external file
		RestAssured.baseURI= properties.getProperty("HOST");
		
		// Pre-requisite condition for the test case
		Response res=given().
		header("Content-Type", "application/json").		
		body(payLoad.addBookPayLoad(isbn,aisle)).
		
		//Selection of the Action(GET/PUT/POST)
		when().
		post(resources.AddBook()).
		
		//Applying assertion for the response
		then().assertThat().
		statusCode(200).
		
		//Extracting the response for generating the specific id for further action
		extract().response();
		JsonPath js=ReusableMethods.rawtoJson(res);
		String id= js.get("ID");
		System.out.println(id); 
		 String del=given().body(payLoad.delData(id)).when().post("/Library/DeleteBook.php").getBody().asString();
		 System.out.println(del);
		
	}
	// For Dynamic Payloads we use @BeforeTest annotation
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"Boo1k143","12345"},{"B1ook243","56785"},{"B1ook344", "590"},{"B1ook444", "513"},{"B1ook543", "557"},{"B1ook643", "524"},{"Book1743", "568"}};
	}
}
	
