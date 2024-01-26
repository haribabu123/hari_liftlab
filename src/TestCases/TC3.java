package TestCases;

import static Utilites.baseTest.test;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Utilites.baseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class TC3 extends baseTest {

	@Test
	public void getUpdatedPetId() {
		Response response = given().queryParam("Status", "Available").contentType(ContentType.JSON).param("Id", "11")
				.when().get(baseUrl + "/v2/pet/").then().extract().response();
		if (Assert.assertEquals(String.valueOf(response.getStatusCode()), "200")) {
			test.log(LogStatus.PASS, "response code 200, test passed");
			Assert.assertEquals("ExpectedName", response.jsonPath().getString("name"));

		} else {
			test.log(LogStatus.FAIL, "Test Failed");
		}

	}

}
