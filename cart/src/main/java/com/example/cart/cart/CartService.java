package com.example.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

@Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository=cartItemRepository;
    }



    public List<Cart> getCart(){
       return cartRepository.findAll();
    }

    public void addNewItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);


    }
}
