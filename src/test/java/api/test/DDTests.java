package api.test;

import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.UpdateUser;
import api.payload.User;
import api.payload.UserAddress;
import api.utilities.DataProviders;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class DDTests {
	public static String userId;
	
	@Test(priority=1,dataProvider="LoginData", dataProviderClass=DataProviders.class)
	
	public void testGetAllUsers(String username, String password) throws InterruptedException
	{
		UserEndpoints.userName = username;
		UserEndpoints.password = password;
		  
		System.out.println("userEmail" + username);
		ValidatableResponse response=UserEndpoints.getAllUsers(username,password);
		//Assert.assertEquals(response.getStatusCode(),200);
		Thread.sleep(2000);
	}
	
	
	@Test(priority=2,dataProvider="Data", dataProviderClass=DataProviders.class)
	
	public void testPostUser(String firstName, String lastName, String contactNumber, 
	String email, String plotNumber, String street, String state, String country, String zipCode, String statusCode, String statusMessage ) throws InterruptedException
	{
		User userPayload=new User();
		
		userPayload.setUser_first_name(firstName);
		userPayload.setUser_last_name(lastName);
		userPayload.setUser_contact_number(Long.parseLong(contactNumber));
		userPayload.setUser_email_id(email);
		
		UserAddress userAddress = new UserAddress();
		
		userAddress.setPlotNumber(plotNumber);
		userAddress.setStreet(street);
		userAddress.setState(state);
		userAddress.setCountry(country);
		userAddress.setZipCode(Long.parseLong(zipCode));
		userPayload.setUserAddress(userAddress);
		
		Response response=UserEndpoints.createUser(userPayload);
		Thread.sleep(2000);
		//JSON Representation from Response Body
	    JsonPath jsnPath = response.jsonPath();
		userId = jsnPath.get("user_id").toString();
		System.out.println("User ID created " + userId);
		
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(),201);		
		
	}
	
	
	@Test(priority=3,dataProvider="UserNames", dataProviderClass=DataProviders.class)
	
	public void testGetUserByFirstName(String firstName) throws InterruptedException
	{
		Response response=UserEndpoints.getUserByFirstName(firstName);
		Thread.sleep(2000);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	

	@Test(priority=4,dataProvider="UpdateData", dataProviderClass=DataProviders.class)

	public void testPutUser(String firstName, String lastName, String contactNumber, 
		String email, String plotNumber, String street, String state, String country, String zipCode, String statusCode, String statusMessage )throws InterruptedException
	
	{
		UpdateUser userPayload = new UpdateUser();
		
		userPayload.setUser_first_name(firstName);
		userPayload.setUser_last_name(lastName);
		userPayload.setUser_contact_number(Long.parseLong(contactNumber));
		userPayload.setUser_email_id(email);
		
		UserAddress userAddress = new UserAddress();
		
		userAddress.setPlotNumber(plotNumber);
		userAddress.setStreet(street);
		userAddress.setState(state);
		userAddress.setCountry(country);
		userAddress.setZipCode(Long.parseLong(zipCode));
		userPayload.setUserAddress(userAddress);
		userPayload.setUser_id(userId);
	
		Response response=UserEndpoints.updateUser(Integer.parseInt(userId),userPayload);
		Thread.sleep(2000);
		Assert.assertEquals(response.getStatusCode(),200);
	
	}
	/*
	@Test(priority=5,dataProvider="UserNames", dataProviderClass=DataProviders.class)
	
	public void testDeleteUserByUserId(String userId)
	{
		
		Response response=UserEndpoints.deleteUserById(userId);
		System.out.println("User deleted by userId   " + userId);
		Assert.assertEquals(response.getStatusCode(),200);	
	}
	*/	

	
	
	@Test(priority=5,dataProvider="UserNames", dataProviderClass=DataProviders.class)
	
	public void testDeleteUserByFirstName(String firstName) throws InterruptedException
	{
		
		Response response=UserEndpoints.deleteUserByFirstName(firstName);
		System.out.println("User Created FirstName " + firstName);
		Assert.assertEquals(response.getStatusCode(),200);
		Thread.sleep(2000);
	}
	
	
}