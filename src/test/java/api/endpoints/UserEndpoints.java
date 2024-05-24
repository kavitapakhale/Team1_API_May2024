package api.endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.payload.UpdateUser;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class UserEndpoints {
	public static String userName;
	public static String password;

	public static Response createUser(User payload)
	{
		
		//3rd party utility
		ObjectMapper Obj = new ObjectMapper();
		try {
			 
            // Getting organisation object as a json string
            String jsonStr = Obj.writeValueAsString(payload);
 
            // Displaying JSON String on console
            System.out.println(jsonStr);
        }
 
        // Catch block to handle exceptions
        catch (IOException e) {
 
            // Display exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
	
		Response response=
		given()
			.auth().basic(UserEndpoints.userName, UserEndpoints.password)	
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		 .when()
		 	.post(Routes.post_url);
		 			
		 	return response;
	}
	
	
	
	
	public static ValidatableResponse getAllUsers(String username, String password)
	{
		String path=System.getProperty("user.dir")+"//testData//getAllUsersSchema.json";
		System.out.println(path);
		
		ValidatableResponse response=
		given()
			.auth().basic(UserEndpoints.userName, UserEndpoints.password)			
			.when()
		 	 .get(Routes.get_all_users_url)
		 	 .then()
		 	 	.assertThat()
		 	 	.statusCode(200).and();
		 	 //.body(matchesJsonSchemaInClasspath("testData\\getAllUsersSchema.json"));
		 	return response;
	}
	
	
	public static Response getUserByFirstName(String firstName)
	{
		Response response=
		given()
		.auth().basic(UserEndpoints.userName, UserEndpoints.password)	
			.pathParam("userFirstName", firstName)
		 .when()
		 	.get(Routes.get_url_firstname);
		 	
		 	return response;
	}
	
	public static Response readUser(int userId)
	{
		Response response=
		given()
			.auth().basic(UserEndpoints.userName, UserEndpoints.password)	
			.pathParam("userId", userId)
		 .when()
		 	.get(Routes.get_url_id);
		 	
		 	return response;
	}
	
	
	public static Response updateUser(int userId, UpdateUser payload)
	{
				//3rd party utility
				ObjectMapper Obj = new ObjectMapper();
				try {
					 
		            // Getting organisation object as a json string
		            String jsonStr = Obj.writeValueAsString(payload);
		 
		            // Displaying JSON String on console
		            System.out.println(jsonStr);
		        }
		 
		        // Catch block to handle exceptions
		        catch (IOException e) {
		 
		            // Display exception along with line number
		            // using printStackTrace() method
		            e.printStackTrace();
		        }
			
		Response response=
		given()
			.auth().basic(UserEndpoints.userName, UserEndpoints.password)	
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("userId", userId)
			.body(payload)
		 .when()
		 	.put(Routes.update_url);
		 	
		 	return response;
	}
	
	
	
	public static Response deleteUserByFirstName(String userFirstName)
	{
		Response response=
		given()
		.auth().basic(UserEndpoints.userName, UserEndpoints.password)	
			.pathParam("userFirstName", userFirstName)
		 .when()
		 	.delete(Routes.delete_url_firstname);
		 	
		 	return response;
	}
		
	public static Response deleteUserById(String userId)
	{
		System.out.println(userId);
		Response response=
		given()
		.auth().basic(UserEndpoints.userName, UserEndpoints.password)	
			.pathParam("UserId",userId)
		 .when()
		 	.delete(Routes.delete_url_id);
		 	
		 	return response;
	}
}
