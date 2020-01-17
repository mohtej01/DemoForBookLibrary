package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static XmlPath rawtoXML(Response r)
	{
		String res= r.asString();
		XmlPath x= new XmlPath(res);
		return x;
		
	}

	public static JsonPath rawtoJson(Response r)
	{
		String res= r.asString();
		JsonPath x= new JsonPath(res);
		return x;
		
	}
}
