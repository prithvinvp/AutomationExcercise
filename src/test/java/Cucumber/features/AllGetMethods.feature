#Author: Prithvi NVP

@AllGetMethods
Feature: API Testing for All get Methods

@GetAllProducts
Scenario: Verify we get All the list of products when we hit the API
	 Given I get the "ApiBaseUrl"
	 When I send the endpoint "productsList"
	 Then I get the response as 200


