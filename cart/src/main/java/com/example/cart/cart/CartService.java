package com.example.cart.cart;

import jakarta.transaction.Transactional;
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

    public void deleteItem(Integer itemId) {
    boolean exists= cartItemRepository.existsById(Long.valueOf(itemId));

    if(!exists){
        throw new IllegalStateException("Item does not exist");
    }
    cartItemRepository.deleteById(Long.valueOf(itemId));

    }


@Transactional
    public void updateItem(Integer itemId, Double quantity) {
    CartItem cartItem = cartItemRepository.findById(Long.valueOf(itemId))
            .orElseThrow(() -> new IllegalStateException("Item does not exist"));

    cartItem.setQuantity(quantity);

    }
}
