package com.example.cart.cart;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {
    public List<Cart> getCart(){
        return List.of(
                new Cart(
                        1L,
                        (long) 1D,
                        LocalDateTime.of(2024, 11, 15, 10, 30, 45),
                        LocalDateTime.of(2024, 11, 15, 10, 30, 45)










                )
        );
    }
}
