package Cucumber.StepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Utils.Configs.Core;
import com.Utils.Reports.Log;

import io.cucumber.datatable.DataTable;
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
    private static String endPoint;
    private static RequestSpecification httpRequest;
    private static Map<String, String> params = new HashMap<>();
    private static RequestSpecification request = RestAssured.given();

    public static  Map<String, String> getParams() {
		return params;
	}

	@SuppressWarnings("static-access")
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	@Given("I get the {string}")
	public static void iGetTheBaseUrl(String baseUrl) throws IOException {
		BaseUrl = Core.readProperty(baseUrl);
		Log.message("Base url is: " + BaseUrl);
	}
	
	@When("I send the endpoint {string}")
	public static void iSendTheEndpoint(String endpoint) {
		try {
			Log.message("end point is: " + endpoint);
			setHttpRequest(request);
			endPoint = endpoint;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Then("I send the method as {string}")
	public static void iSendTheMethodAs(String method) {
		try {
			switch(method.toUpperCase()) {
				case "GET" : response = request.get(BaseUrl + endPoint); break;
				case "POST" : response = request.post(BaseUrl + endPoint); break;
				case "PATCH" : response = request.patch(BaseUrl + endPoint); break;
				case "PUT" : response = request.put(BaseUrl + endPoint); break;
				case "DELETE" : response = request.delete(BaseUrl + endPoint); break;
				default: throw new IllegalArgumentException("Invalid HTTP method: " + method);
				
			}
			
			Log.message(response.asString());
		}catch(Exception e) {
			e.printStackTrace();
			Log.message(e.getMessage());
			throw e;
		}
	}
	
	@Then("I get the response as {int}")
	public static void iGetTheResponseAs(int responseCode) {
		Log.message(Integer.toString(response.jsonPath().getInt("responseCode")));
		Log.assertThat(Integer.toString(response.jsonPath().getInt("responseCode")), Integer.toString(responseCode));
	}

	public static RequestSpecification getHttpRequest() {
		return httpRequest;
	}

	public static void setHttpRequest(RequestSpecification httpRequest) {
		StepDefinitions.httpRequest = httpRequest;
	}
	
	@Then("I send the parameters with the following details for {string} method")
	public static void iSendTheParametersWithTheFollowingDetails(String method, DataTable dataTable) {
		try {
			List<Map<String, String>> paramList = dataTable.asMaps(String.class, String.class);

	        for (Map<String, String> row : paramList) {
	            getParams().put(row.get("key"), row.get("value"));
	        }
	        
	        for (Map.Entry<String, String> entry : params.entrySet()) {
	            request.queryParam(entry.getKey(), entry.getValue());
	        }
			
	       switch(method.toUpperCase()) {
	       		case "GET" : response = request.get(BaseUrl + endPoint); break;
	       		case "POST" : response = request.post(BaseUrl + endPoint); break;
	       		case "PATCH" : response = request.patch(BaseUrl + endPoint); break;
	       		case "PUT" : response = request.put(BaseUrl + endPoint); break;
	       		case "DELETE" : response = request.delete(BaseUrl + endPoint); break;
	       		default: throw new IllegalArgumentException("Invalid HTTP method: " + method);
			}
					
		}catch(Exception e) {
			e.printStackTrace();
			Log.message(e.getMessage());
			throw e;
		}

	}
}
