package com.example.cart;


import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.assertj.core.api.BDDAssumptions;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;





@SpringBootTest
class CartApplicationTests {
//	@BeforeClass
//	public static void setup() {
//		// Register the plain text parser
//		RestAssured.registerParser("text/plain", Parser.TEXT);
//	}

//	@Test
//	void shouldGetCartItems() {
//		// Make the GET request to retrieve cart items
//		String baseUrl = "http://localhost:8082/api/v1/cart";
//
//		var response = RestAssured.given()
//				.contentType("application/json")
//				.when()
//				.get(baseUrl + "/{userId}", 1);
//
//		// Log the response
//		System.out.println("Response: " + response.asString());
//
//		// Validate the response
//		response.then()
//				.statusCode(200) // Ensure the status code is 200 (OK)
//				.body("[0].cartId", Matchers.notNullValue())
//				.body("[0].userId", Matchers.notNullValue())
//				.body("[0].productId", Matchers.notNullValue())
//				.body("[0].quantity", Matchers.greaterThan(0))
//				.body("[0].price", Matchers.greaterThan((float)0.0))
//				.body("[0].productImage", Matchers.notNullValue())
//				.body("[0].productName", Matchers.notNullValue())
//				.body("[0].createdAt", Matchers.matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*"))
//				.body("[0].updatedAt", Matchers.matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*"));
//	}

//	@Test
//	void shouldAddItemToCart() {
//
//		String jsonBody = "{\n" +
//				"    \"productId\": 123,\n" +
//				"    \"productName\": \"Smartphone\",\n" +
//				"    \"price\": 999.99,\n" +
//				"    \"quantity\": 2,\n" +
//				"    \"userId\": 1,\n" +
//				"    \"productImage\": \"https://example.com/product-image.jpg\"\n" +
//				"}";
//
//		var response = RestAssured.given()
//				.contentType("application/json")
//				.body(jsonBody)
//				.when()
//				.post("http://localhost:8082/api/v1/cart/add");
//
//		// Log the response
//		System.out.println("Response: " + response.asString());
//
//		// Validate the response
//		response.then()
//				.statusCode(201);
//
//	}
//


//	@Test
//	void shouldUpdateItemQuantityInCart() {
//		// Define the API base URL
//		String baseUrl = "http://localhost:8082/api/v1/cart/update/5"; // Replace with dynamic cartId if needed
//
//		// Create the request body
//		String jsonBody = "{\n" +
//				"    \"quantity\": 3\n" +
//				"}";
//
//		// Send PUT request to the endpoint
//		var response = RestAssured.given()
//				.contentType("application/json")
//				.body(jsonBody)  // Include the request body
//				.when()
//				.patch(baseUrl);
//
//		// Log the response for debugging
//		System.out.println("Response: " + response.asString());
//
//		// Validate the response
//		response.then()
//				.statusCode(200)  // Ensure the status code is 200 (OK)
//				.contentType("text/plain") // Specify that the response content is plain text
//				.body(equalTo("Item updated successfully")); // Validate the plain text response
//	}

//	@Test
//	void shouldDeleteCart() {
//		// Define the API base URL and cartId
////		String baseUrl = "http://localhost:8082/api/v1/cart/delete";
//		int cartId = 8; // Replace with a valid cart ID
//
//		// Send DELETE request with path parameter
//		var response = RestAssured.given()
//				.contentType("application/json")
//				.when()
//				.delete("http://localhost:8082/api/v1/cart/delete/" + cartId); // Add cartId as a path parameter
//
//		// Log the response for debugging
//		System.out.println("Response: " + response.asString());
//
//		// Validate the response
//		response.then()
//				.statusCode(200);
//
//	}
//
//

}
