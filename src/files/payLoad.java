package files;

public class payLoad {
	
	public static String postData()
	{
		String addPlace="{"+
				"\"location\": {"+
				"\"lat\": -38.383494,"+
				"\"lng\": 33.427362"+
			"},"+
			"\"accuracy\": 50,"+
			"\"name\": \"Mohit House\","+
			"\"phone_number\": \"9090909090\","+
			"\"address\": \"shastri nagar\","+
			"\"types\": [\"shop\",\"shoe park\"],"+
			"\"website\": \"http//google.com\","+
			"\"language\": \"FRENCH-IN\""+
		"}" ;
		return addPlace;
	}
	
public static String addBookPayLoad(String isbn,String aisle)
{
	String addb= "{\r\n" + 
			"\r\n" + 
			"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
			"\"isbn\":\""+isbn+"\",\r\n" + 
			"\"aisle\":\""+aisle+"\",\r\n" + 
			"\"author\":\"John foe\"\r\n" + 
			"}\r\n" + 
			"";
	return addb;
}

public static String delData(String id)
{
	String book= "{\r\n" + 
			" \r\n" + 
			"\"ID\" : \""+id+"\"\r\n" + 
			" \r\n" + 
			"} \r\n" + 
			"";
	return book;
}
}
