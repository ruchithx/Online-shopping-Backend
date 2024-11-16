package com.example.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping(path="api/v1/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getCart(){
       return cartService.getCart();
    }

    @PostMapping("/add")
    public void addItem(@RequestBody CartItem cartItem){
        cartService.addNewItem(cartItem);


    }

}
