package com.example.cart.cart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class CartConfig {
    @Bean
    CommandLineRunner commandLineRunner(CartRepository cartRepository, CartItemRepository CartItemRepository, CartItemRepository cartItemRepository) {
        return args -> {
           Cart cart1= new Cart(
                    1,
                    (long) 1D,
                    LocalDateTime.of(2024, 11, 15, 10, 30, 45),
                    LocalDateTime.of(2024, 11, 15, 10, 30, 45)


            );
            Cart cart2= new Cart(
                    2,
                    (long) 2D,
                    LocalDateTime.of(2024, 11, 16, 11, 30, 45),
                    LocalDateTime.of(2024, 11, 16, 12, 30, 45)


            );
            cartRepository.saveAll(
                    List.of(cart1, cart2)
            );

            CartItem cartItem1 = new CartItem(
                   1,
                    1,
                    "1P",
                    "Cream Soda",
                    "Elephant House Cteam Soda 1L",
                    1.0,
                    400.0,
                    0.1




                    );
            cartItemRepository.saveAll(
                    List.of(cartItem1)
            );

        };
    }

}
