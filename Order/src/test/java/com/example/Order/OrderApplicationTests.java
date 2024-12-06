package com.example.Order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
                "userId": 1,
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

//	@Test
//	void getOrderByIdTest() throws Exception {
//		setup();
//
//		mockMvc.perform(get("/api/v1/orders/1"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value(1))
//				.andExpect(jsonPath("$.userId").value(1))
//				.andExpect(jsonPath("$.status").value("Completed"))
//				.andExpect(jsonPath("$.id").isNumber()) // Validate ID is a number
//				.andExpect(jsonPath("$.userId").isNumber()) // Validate userId is a number
//				.andExpect(jsonPath("$.status").isString()) // Validate status is a string
//				.andExpect(jsonPath("$.totalPrice").isNumber()) // Validate totalPrice is a number
//				.andExpect(jsonPath("$.address").isString()); // Validate address is a string
//	}

	@Test
	void updateOrderStatusTest() throws Exception {
		setup();

		String updatePayload = """
            {
                "status": "Completed"
            }
        """;

		mockMvc.perform(put("/api/v1/admin/orders/6")
						.contentType(MediaType.APPLICATION_JSON)
						.content(updatePayload))
				.andExpect(status().isOk())
				.andExpect(content().string("Order Updated Successfully"));
	}

//	@Test
//	void getOrdersByUserIdTest() throws Exception {
//		setup();
//
//		mockMvc.perform(get("/api/v1/orders")
//						.param("userId", "1"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$").isArray()) // Ensure the response is an array
//				.andExpect(jsonPath("$[0].id").isNumber()) // Validate the first order's ID is a number
//				.andExpect(jsonPath("$[0].userId").isNumber()) // Validate userId
//				.andExpect(jsonPath("$[0].status").isString()) // Validate status
//				.andExpect(jsonPath("$[0].totalPrice").isNumber()) // Validate totalPrice
//				.andExpect(jsonPath("$[0].address").isString()); // Validate address
//	}

	@Test
	void getPastOrdersTest() throws Exception {
		setup();

		mockMvc.perform(get("/api/v1/orders/past")
						.param("userId", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].id").isNumber())
				.andExpect(jsonPath("$[0].userId").isNumber())
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
				.andExpect(jsonPath("$[0].userId").isNumber())
				.andExpect(jsonPath("$[0].status").isString())
				.andExpect(jsonPath("$[0].totalPrice").isNumber())
				.andExpect(jsonPath("$[0].address").isString());
	}
}
