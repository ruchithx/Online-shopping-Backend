package com.example.Order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class OrderApplicationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void createOrderTest() throws Exception {
		setup();

		String orderPayload = """
            {
                "userId": "a05f11e5-6872-40b2-8c6f-0e2f1c3a0d07",
                "totalPrice": 100.0,
                "status": "Pending",
                "address": "123 Main Street",
                "orderItems": [
                    {"quantity": 2, "productId": 101},
                    {"quantity": 1, "productId": 102}
                ]
            }
        """;

		mockMvc.perform(post("/api/v1/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(orderPayload))
				.andExpect(status().isCreated())
				.andExpect(content().string("Order Created Successfully"));
	}






	@Test
	void getOrderByIdTest() throws Exception {
		this.setup();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.userId").value("a05f11e5-6872-40b2-8c6f-0e2f1c3a0d07"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(360.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Completed"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").value("2024-12-06T10:20:16.505421")) // Updated createdAt
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Main Street"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].id").value(1)) // Updated order item ID
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].orderId").value(1)) // Updated order ID
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].productId").value(102))
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].quantity").value(1.0))
				// Validate product details inside orderItems
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].product.productId").value(102))
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].product.productPrice").value(400.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].product.isDiscount").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].product.discount").value(10.0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].product.productName").value("caret"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderItems[0].product.mediaUrl").value("https://res.cloudinary.com/dpk9utvby/image/upload/v1707639801/cld-sample-4.jpg"));
	}

	





	@Test
	void updateOrderStatusTest() throws Exception {
		setup();

		String updatePayload = """
            {
                "status": "Completed"
            }
        """;

		mockMvc.perform(put("/api/v1/admin/orders/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(updatePayload))
				.andExpect(status().isOk())
				.andExpect(content().string("Order Updated Successfully"));
	}

	@Test
	void getOrdersByUserIdTest() throws Exception {
		setup();

		mockMvc.perform(get("/api/v1/orders")
						.param("userId", "a05f11e5-6872-40b2-8c6f-0e2f1c3a0d07"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()) // Ensure the response is an array
				.andExpect(jsonPath("$[0].id").isNumber()) // Validate the first order's ID is a number
				.andExpect(jsonPath("$[0].userId").isString()) // Validate userId
				.andExpect(jsonPath("$[0].status").isString()) // Validate status
				.andExpect(jsonPath("$[0].totalPrice").isNumber()) // Validate totalPrice
				.andExpect(jsonPath("$[0].address").isString()); // Validate address
	}

	@Test
	void getPastOrdersTest() throws Exception {
		setup();

		mockMvc.perform(get("/api/v1/orders/past")
						.param("userId", "a05f11e5-6872-40b2-8c6f-0e2f1c3a0d07"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].id").isNumber())
				.andExpect(jsonPath("$[0].userId").isString())
				.andExpect(jsonPath("$[0].status").isString())
				.andExpect(jsonPath("$[0].totalPrice").isNumber())
				.andExpect(jsonPath("$[0].address").isString());
	}

	@Test
	void getAllOrdersTest() throws Exception {
		setup();

		mockMvc.perform(get("/api/v1/admin/orders"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].id").isNumber())
				.andExpect(jsonPath("$[0].userId").isString())
				.andExpect(jsonPath("$[0].status").isString())
				.andExpect(jsonPath("$[0].totalPrice").isNumber())
				.andExpect(jsonPath("$[0].address").isString());
	}
}
