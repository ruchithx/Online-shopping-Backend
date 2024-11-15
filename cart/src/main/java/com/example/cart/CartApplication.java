package com.example.cart;

import com.example.cart.cart.Cart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.awt.PageAttributes.MediaType.C1;

@SpringBootApplication

public class CartApplication {



	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}


}
