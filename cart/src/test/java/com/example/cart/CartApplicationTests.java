package com.example.cart;


import io.restassured.RestAssured;
import org.assertj.core.api.BDDAssumptions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class CartApplicationTests {

	@Test
	void shouldGetCartItems() {
		// Make the GET request to retrieve cart items
		String baseUrl = "http://localhost:8082/api/v1/cart";

		var response = RestAssured.given()
				.contentType("application/json")
				.when()
				.get(baseUrl + "/{userId}", 1);

		// Log the response
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200) // Ensure the status code is 200 (OK)
				.body("[0].cartId", Matchers.notNullValue())
				.body("[0].userId", Matchers.notNullValue())
				.body("[0].productId", Matchers.notNullValue())
				.body("[0].quantity", Matchers.greaterThan(0))
				.body("[0].price", Matchers.greaterThan(0.0))
				.body("[0].productImage", Matchers.notNullValue())
				.body("[0].productName", Matchers.notNullValue())
				.body("[0].createdAt", Matchers.matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*"))
				.body("[0].updatedAt", Matchers.matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*"));
	}

	@Test
	void shouldAddItemToCart() {
		String baseUrl = "http://localhost:8082/api/v1/cart/add";
		String jsonBody = "{\n" +
				"    \"productId\": 123,\n" +
				"    \"productName\": \"Smartphone\",\n" +
				"    \"price\": 999.99,\n" +
				"    \"quantity\": 2,\n" +
				"    \"userId\": 1,\n" +
				"    \"productImage\": \"https://example.com/product-image.jpg\"\n" +
				"}";

		var response = RestAssured.given()
				.contentType("application/json")
				.body(jsonBody)
				.when()
				.post(baseUrl);

		// Log the response
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(201);  // Ensure the status code is 201 (Created)
//				.body("message", equalTo("Item added to cart successfully")) // Validate response message
//				.body("cartItem.cartId", notNullValue()) // Ensure cartId is present
//				.body("cartItem.productId", equalTo(123)) // Validate productId
//				.body("cartItem.productName", equalTo("Smartphone")) // Validate productName
//				.body("cartItem.price", equalTo(999.99f)) // Validate price
//				.body("cartItem.quantity", equalTo(2)) // Validate quantity
//				.body("cartItem.userId", equalTo(1)) // Validate userId
//				.body("cartItem.productImage", equalTo("https://example.com/product-image.jpg")); // Validate productImage
	}
	@Test
	void shouldUpdateCartItemQuantityUsingPathAndQueryParams() {
		// Define the API base URL and path parameter
		String baseUrl = "http://localhost:8082/api/v1/cart/update";
		String cartId = "1"; // Replace with a valid cart ID

		// Define the query parameter
		int quantity = 5;

		// Send PUT request with path parameter and query parameter
		var response = RestAssured.given()
				.contentType("application/json")
				.queryParam("quantity", Math.max(1, quantity)) // Set the query parameter
				.when()
				.put(baseUrl + "/" + cartId); // Add cartId as a path parameter

		// Log the response for debugging
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200) // Ensure the status code is 200 (OK)
				.body("cartId", equalTo(cartId)) // Validate cartId in the response
				.body("quantity", equalTo(Math.max(1, quantity))); // Validate updated quantity
	}


	@Test
	void shouldDeleteCart() {
		// Define the API base URL and cartId
//		String baseUrl = "http://localhost:8082/api/v1/cart/delete";
		String cartId = "1"; // Replace with a valid cart ID

		// Send DELETE request with path parameter
		var response = RestAssured.given()
				.contentType("application/json")
				.when()
				.delete("http://localhost:8082/api/v1/cart/delete/" + cartId); // Add cartId as a path parameter

		// Log the response for debugging
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200) // Ensure the status code is 200 (OK)
				.body("cartId", equalTo(cartId)) // Validate the cartId in the response
				.body("message", equalTo("Cart deleted successfully")); // Validate the success message
	}



}
