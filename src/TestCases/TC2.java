package TestCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import test.java.platform.api.Payloads;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilites.baseTest;

import static Utilites.baseTest.test;
import static io.restassured.RestAssured.given;

public class TC2 extends baseTest {

	@BeforeTest
	public static String updatebody() {
		return "{\r\n" + "    	  \"Pet\": {\r\n" + "    	    \"id\": 11,\r\n" + "    	    \"Category\": {\r\n"
				+ "    	      \"id\": 11,\r\n" + "    	      \"name\": \"hari\"\r\n" + "    	    },\r\n"
				+ "    	    \"name\": \"hari\",\r\n" + "    	    \"photoUrls\": {\r\n"
				+ "    	      \"photoUrl\": \"string\"\r\n" + "    	    },\r\n" + "    	    \"tags\": {\r\n"
				+ "    	      \"Tag\": {\r\n" + "    	        \"id\": 11,\r\n"
				+ "    	        \"name\": \"hari\"\r\n" + "    	      }\r\n" + "    	    },\r\n"
				+ "    	    \"status\": \"available\"\r\n" + "    	  }}";
	}

	@Test
	public void putRequest() {
		Response response = given().header("Content-type", "application/json").and().body(updatebody()).when()
				.put(baseUrl + "/v2/pet/11").then().extract().response();

		if (Assert.assertEquals(String.valueOf(response.getStatusCode()), "201")) {
			test.log(LogStatus.PASS, "response code 201, test passed");
			Assert.assertEquals(200, response.statusCode());
			Assert.assertEquals("expectedName", response.jsonPath().getString("name"));
			Assert.assertEquals("Expectedid", response.jsonPath().getString("id"));

		} else {
			test.log(LogStatus.FAIL, "Test Failed");
		}

	}
}
