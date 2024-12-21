package com.product.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}





	@Test
	void shouldAddProduct() {
		String jsonBody = "{\n" +
				"    \"productName\": \"caret\",\n" +
				"    \"hotDeals\": false,\n" +
				"    \"bestSeller\": true,\n" +
				"    \"productDescription\": \"this is fruit\",\n" +
				"    \"productPrice\": 400,\n" +
				"    \"SKU\": 40,\n" +
				"    \"isDiscount\": true,\n" +
				"    \"discount\": 10.0,\n" +
				"    \"quantityInStock\": 40,\n" +
				"    \"status\": true,\n" +
				"    \"categoryId\": 502,\n" +
				"    \"brandId\": 502,\n" +
				"    \"mediaUrl\": \"https://res.cloudinary.com/dpk9utvby/image/upload/v1707639801/cld-sample-4.jpg\"\n" +
				"}";

		var response = RestAssured.given()
				.contentType("application/json")
				.body(jsonBody)
				.when()
				.post("/api/v1/admin/product/addproduct");

		System.out.println("Response: " + response.asString());

		response.then()
				.statusCode(201);
	}

	@Test
	void shouldAddBrand() {
		String jsonBody = "{\n" +
				"    \"brandName\": \"apple\"\n" +
				"}";

		var response = RestAssured.given()
				.contentType("application/json")
				.body(jsonBody)
				.when()
				.post("/api/v1/admin/product/addbrand");

		System.out.println("Response: " + response.asString());

		response.then()
				.statusCode(201);
	}

	@Test
	void shouldAddCategory() {
		String jsonBody = "{\n" +
				"    \"categoryName\": \"juwalary\",\n" +
				"    \"categoryDescription\": \"juwalary items\"\n" +
				"}";

		var response = RestAssured.given()
				.contentType("application/json")
				.body(jsonBody)
				.when()
				.post("/api/v1/admin/product/addcategory");

		// Log the response
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(201);  // Ensure the status code is 200 (OK)
	}

	@Test
	void shouldReturnListOfCategories() {
		String baseUrl = "http://localhost:8083/api/v1/product/getcategories";

		// Send GET request to the endpoint
		var response = RestAssured.given()
				.contentType("application/json")
				.when()
				.get(baseUrl);

		// Log the response for debugging
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200)  // Ensure the status code is 200 (OK)
				.body("$", Matchers.notNullValue())  // Ensure the response is not null
				.body("$.size()", Matchers.greaterThan(0))  // Ensure there is at least one category in the list
				.body("[0].categoryId", Matchers.notNullValue())  // Validate the first category's 'categoryId' is not null
				.body("[0].categoryName", Matchers.notNullValue())  // Validate the first category's 'categoryName' is not null
				.body("[0].categoryDescription", Matchers.notNullValue())  // Validate the first category's 'categoryDescription' is not null
				.body("[0].categoryId", Matchers.instanceOf(Integer.class))  // Ensure 'categoryId' is an integer
				.body("[0].categoryName", Matchers.instanceOf(String.class))  // Ensure 'categoryName' is a string
				.body("[0].categoryDescription", Matchers.instanceOf(String.class));  // Ensure 'categoryDescription' is a string
	}


	@Test
	void shouldAddImageToProduct() {
		String jsonBody = "{\n" +
				"    \"productId\": 602,\n" +
				"    \"mediaUrl\": \"https://res.cloudinary.com/dpk9utvby/image/upload/v1732350105/organization/n8vznpbavclukknqphzg.png\"\n" +
				"}";

		var response = RestAssured.given()
				.contentType("application/json")
				.body(jsonBody)
				.when()
				.post("/api/v1/admin/product/addimage");

		// Log the response
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(201);  // Ensure the status code is 200 (OK)
	}

	@Test
	void shouldReturnAllBrands() {
		var response = RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/api/v1/product/getbrands");

		// Log the response
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200)  // Ensure the status code is 200 (OK)

				// Validate the response contains a list of brands
				.body("$", Matchers.notNullValue())  // Ensure response body is not null
				.body("$", Matchers.not(Matchers.empty()));  // Ensure response is not empty
	}

	@Test
	void shouldReturnAllCategories() {
		var response = RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/api/v1/product/getcategories");

		// Log the response
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200)  // Ensure the status code is 200 (OK)

				// Validate the response contains a list of categories
				.body("$", Matchers.notNullValue())  // Ensure response body is not null
				.body("$", Matchers.not(Matchers.empty()));  // Ensure response is not empty
	}

	@Test
	void shouldReturnProductById() {
		int productId = 602;  // Example product ID to fetch
		String baseUrl = "http://localhost:8083/api/v1/product/getproductbyid/" + productId;

		// Send GET request to the endpoint
		var response = RestAssured.given()
				.contentType("application/json")
				.when()
				.get(baseUrl);

		// Log the response for debugging
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200)  // Ensure the status code is 200 (OK)
				.body("productId", Matchers.equalTo(productId))  // Validate that 'productId' matches the input
				.body("productName", Matchers.notNullValue())  // Ensure 'productName' is not null
				.body("productDescription", Matchers.notNullValue())  // Ensure 'productDescription' is not null
				.body("productPrice", Matchers.greaterThanOrEqualTo((float) 0.0))  // Ensure 'productPrice' is non-negative
				.body("isDiscount", Matchers.is(true))  // Validate 'isDiscount' is true
				.body("discount", Matchers.greaterThanOrEqualTo((float) 0.0))  // Ensure 'discount' is non-negative
				.body("quantityInStock", Matchers.greaterThanOrEqualTo(0))  // Ensure 'quantityInStock' is non-negative
				.body("status", Matchers.is(true))  // Validate 'status' is true
				.body("hotDeals", Matchers.is(false))  // Validate 'hotDeals' is false
				.body("bestSeller", Matchers.is(true))  // Validate 'bestSeller' is true
				.body("createdAt", Matchers.notNullValue())  // Ensure 'createdAt' is not null
				.body("updatedAt", Matchers.notNullValue())  // Ensure 'updatedAt' is not null
				.body("mediaUrl", Matchers.startsWith("https://"))  // Validate 'mediaUrl' is a valid URL
				.body("category.categoryId", Matchers.equalTo(452))  // Validate 'categoryId' in the nested 'category' object
				.body("category.categoryName", Matchers.notNullValue())  // Ensure 'categoryName' is not null
				.body("category.categoryDescription", Matchers.notNullValue())  // Ensure 'categoryDescription' is not null
				.body("brand.brandId", Matchers.equalTo(502))  // Validate 'brandId' in the nested 'brand' object
				.body("brand.brandName", Matchers.notNullValue())  // Ensure 'brandName' is not null
				.body("sku", Matchers.nullValue());  // Validate 'sku' is null
	}

	@Test
	void shouldReturnListOfProducts() {
		String baseUrl = "/api/v1/product/getproducts";

		// Send GET request to the endpoint
		var response = RestAssured.given()
				.contentType("application/json")
				.when()
				.get(baseUrl);

		// Log the response for debugging
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200)  // Ensure the status code is 200 (OK)
				.body("$", Matchers.notNullValue())  // Ensure the response is not null
				.body("$.size()", Matchers.greaterThan(0))  // Ensure there is at least one product in the list
				.body("[0].productId", Matchers.notNullValue())  // Validate the first product's 'productId' is not null
				.body("[0].productName", Matchers.notNullValue())  // Ensure the first product's 'productName' is not null
				.body("[0].productPrice", Matchers.greaterThanOrEqualTo((float) 0.0))  // Ensure the first product's price is non-negative
				.body("[0].category.categoryId", Matchers.notNullValue())  // Validate the nested 'categoryId' in 'category' is not null
				.body("[0].category.categoryName", Matchers.notNullValue())  // Ensure 'categoryName' in 'category' is not null
				.body("[0].brand.brandId", Matchers.notNullValue())  // Validate the nested 'brandId' in 'brand' is not null
				.body("[0].brand.brandName", Matchers.notNullValue())  // Ensure 'brandName' in 'brand' is not null
				.body("[0].mediaUrl", Matchers.startsWith("https://"))  // Ensure the first product's 'mediaUrl' is a valid URL
				.body("[0].quantityInStock", Matchers.greaterThanOrEqualTo(0))  // Ensure stock quantity is non-negative
				.body("[0].createdAt", Matchers.notNullValue())  // Ensure 'createdAt' is not null
				.body("[0].updatedAt", Matchers.notNullValue());  // Ensure 'updatedAt' is not null
	}


	@Test
	void shouldCheckProductQuantity() {
		// Define the API base URL and test parameters
		int productId = 602;
		int quantity = 1;
		String baseUrl = "http://localhost:8083/api/v1/product/checkquantity/" + productId + "/" + quantity;

		// Send GET request to the endpoint
		var response = RestAssured.given()
				.contentType("application/json")
				.when()
				.get(baseUrl);

		// Log the response for debugging
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200)  // Ensure the status code is 200 (OK)
				.body(Matchers.anyOf(  // Check if the response is either "true" or "false"
						Matchers.equalTo("true"),
						Matchers.equalTo("false")
				));
	}

	@Test
	void shouldDecreaseProductQuantity() {
		// Define the API base URL
		String baseUrl = "http://localhost:8083/api/v1/product/discresquantity";

		// Create the request body

		String jsonBody = "{\n" +
				"    \"productId\":602,\n" +
				"    \"quantity\": 40\n" +
				"}";

		// Send PATCH request to the endpoint
		var response = RestAssured.given()
				.contentType("application/json")
				.body(jsonBody)  // Include the request body
				.when()
				.patch(baseUrl);

		// Log the response for debugging
		System.out.println("Response: " + response.asString());

		// Validate the response
		response.then()
				.statusCode(200) ; // Ensure the status code is 200 (OK)

	}



}
