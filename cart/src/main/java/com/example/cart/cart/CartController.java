package com.example.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartController(CartService cartService, CartItemRepository cartItemRepository) {
        this.cartService = cartService;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping
    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping("/add")
    public void addItem(@RequestBody CartItem cartItem){
        cartService.addNewItem(cartItem);


    }
    @DeleteMapping("/delete/{itemId}")
    public void deleteItem(@PathVariable("itemId") Integer itemId){
        cartService.deleteItem(itemId);

    }

    @PutMapping("/update/{itemId}")

    public ResponseEntity<String> updateItem(@PathVariable("itemId") Integer itemId, @RequestParam(required = false) Integer quantity){
        cartService.updateItem(itemId,quantity);
        return ResponseEntity.ok("Item updated successfully");

    }



}
