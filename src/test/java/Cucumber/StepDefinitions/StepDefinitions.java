package Cucumber.StepDefinitions;

import java.io.IOException;

import com.Utils.Configs.Core;
import com.Utils.Reports.Log;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class StepDefinitions {
	
	public static String BaseUrl;
	public static String allProductsListUrl;
    private static Response response;
    private static RequestSpecification httpRequest;

	@Given("I get the {string}")
	public static void iGetTheBaseUrl(String baseUrl) throws IOException {
		Log.startTest("Verify we get All the list of products when we hit the API");
		//GetStepDefinitionsHandler.getUrl(baseUrl);
		RestAssured.baseURI = Core.readProperty(baseUrl);
		Log.message("Base url is: " + baseUrl);
	}
	
	@When("I send the endpoint {string}")
	public static void iSendTheEndpoint(String endPoint) {
		try {
			Log.message("end point is: " + endPoint);
			httpRequest = RestAssured.given();
			response = RestAssured.get(endPoint);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Then("I get the response as {int}")
	public static void iGetTheResponseAs(int responseCode) {
		Log.assertThat(Integer.toString(response.statusCode()), "200");
	}
}
