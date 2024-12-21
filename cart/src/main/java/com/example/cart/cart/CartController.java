package com.example.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    public List<Cart> getCartItems(@PathVariable String id) {
        return cartService.getCartItems(id);
    }

    @PostMapping("/add")
    public Cart addItem(@RequestBody CartDTO cart){
    System.out.println( "asdfghjkl");
        System.out.println("Product Name: " + cart.getProductName()); // Debug log



//        cartService.addNewItem(cart);
        return cartService.addNewItem(cart);

    }
    @DeleteMapping("/delete/{cartId}")
    public void deleteItem(@PathVariable("cartId") Integer cartId){
        cartService.deleteItem(cartId);

    }

    @PatchMapping("/update/{cartId}")
    public ResponseEntity<String> updateItem(@PathVariable("cartId") Integer cartId, @RequestParam(required = false) Integer quantity){
        cartService.updateItem(cartId,quantity);
        return ResponseEntity.ok("Item updated successfully");

    }
    @DeleteMapping("/delete/all/{userId}")
    public ResponseEntity<String> deleteAllCartItemsByUserId(@PathVariable String userId) {
        try {
            cartService.deleteAllByUserId(userId);
            return ResponseEntity.ok("All cart items for user " + userId + " have been deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete cart items for user " + userId);
        }
    }




}
