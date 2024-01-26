package TestCases;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilites.baseTest;

import static io.restassured.RestAssured.given;

public class TC1 extends baseTest {

	@Test
	public void getRequest() {

		Response response = given().queryParam("Status", "Available").when().contentType(ContentType.JSON)
				.get(baseUrl + "/v2/pet/").then().extract().response();
		int statusCode = response.getStatusCode();

		if (Assert.assertEquals(String.valueOf(response.getStatusCode()), "200"))

		{
			test.log(test.PASS, "response code 200, test passed");
			String expectedname = "xyz";
			JSONObject responseObject = new org.json.JSONObject(response.body().asString());
			// JSONObject object1 = responseObject.getJSONObject("name");
			JSONArray jsonArray = responseObject.getJSONArray("name");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				if (jsonObject.get("name").equals(expectedname)) {
					String name = jsonObject.get("name").toString();
					System.out.println(name);
				}

			}
		} else {
			test.log(LogStatus.FAIL, "Test Failed");
		}

	}

}
