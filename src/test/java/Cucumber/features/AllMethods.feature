#Author: Prithvi NVP

@AllGetMethods
Feature: API Testing for All get Methods

@GetAllProducts
Scenario: Verify we get All the list of products when we hit the API
	 Given I get the "ApiBaseUrl"
	 When I send the endpoint "productsList"
	 Then I send the method as "GET"
	 Then I get the response as 200

@GetAllBrands
Scenario: Verify we get All the list of Brands when we hit the API
		Given I get the "ApiBaseUrl"
	  When I send the endpoint "brandsList"
	  Then I send the method as "GET"
	  Then I get the response as 200

@UserAccountByDetail
Scenario: Verify we get the details of the User when searched with email
	 Given I get the "ApiBaseUrl"
	  When I send the endpoint "getUserDetailByEmail" 
	  Then I send the parameters with the following details for "GET" method
	  			| key   | value         |
	  			| email | test@test.com |
	   And I get the response as 200

@productsList
Scenario: Verify we get the request not supported when we hit the post method for productsList
   Given I get the "ApiBaseUrl"
	  When I send the endpoint "productsList"
	  Then I send the method as "POST"
	  Then I get the response as 405
	  
@productsList
Scenario: Verify we get the request not supported when we hit the put method for brandsList
   Given I get the "ApiBaseUrl"
	  When I send the endpoint "brandsList"
	  Then I send the method as "POST"
	  Then I get the response as 405
	  
@searchProduct
Scenario: Verify we get the Bad Request when we hit the post method for searchProduct
   Given I get the "ApiBaseUrl"
	  When I send the endpoint "searchProduct"
	  Then I send the method as "POST"
	  Then I get the response as 400
	  
Scenario: Verify login without the email parameter
   Given I get the "ApiBaseUrl"
    When I send the endpoint "verifyLogin"
    Then I send the parameters with the following details for "POST" method
	  			| key      | value         |
	  			| password | test@test.com |
	  Then I get the response as 400

Scenario: Verify delete to endpoint - verifyLogin
	 Given I get the "ApiBaseUrl"
	  When I send the endpoint "verifyLogin"
	  Then I send the method as "DELETE"
	  Then I get the response as 405
	 
    
    
	  
