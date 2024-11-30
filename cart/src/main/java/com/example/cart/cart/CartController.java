package com.example.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="api/v1/cart/")
public class CartController {
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartController(CartService cartService, CartItemRepository cartItemRepository) {
        this.cartService = cartService;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/{id}")
    public List<Cart> getCartItems(@PathVariable Long id) {
        return cartService.getCartItems(id);
    }

    @PostMapping("/add")
    public Cart addItem(@RequestBody CartDTO cart){
    System.out.println( "asdfghjkl");
        System.out.println(cart);
//        cartService.addNewItem(cart);
        return cartService.addNewItem(cart);

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
