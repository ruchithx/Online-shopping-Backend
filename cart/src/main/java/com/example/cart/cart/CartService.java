package com.example.cart.cart;

import com.example.cart.Client.ProductClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final ProductClient productClient;

@Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.cartItemRepository=cartItemRepository;
//    this.productClient = productClient;
    this.productClient = productClient;
}



    public List<Cart> getCart(){
       return cartRepository.findAll();
    }

    public Cart addNewItem(CartDTO cartItem) {
//        System.out.println(cartItem);
        Integer productId = cartItem.getProductId();
        Long productIdAsLong = Long.valueOf(productId);
      boolean ckeckQuantity=  productClient.checkProductExist(productIdAsLong,cartItem.getQuantity());
//
        System.out.println(ckeckQuantity);
      if(ckeckQuantity){

        Cart cart = new Cart();
        cart.setUserId(cartItem.getUserId());
        cart.setProductId(cartItem.getProductId());
        cart.setQuantity(cartItem.getQuantity());
        cart.setPrice(cartItem.getPrice());
        cart.setProductImage(cartItem.getProductImage());
        cartRepository.save(cart);
        return cart;
      }
      return null;
//        boolean exist= productClient.checkProductExist(Long.parseLong(cartItem.getProductId()), cartItem.getQuantity());
//        System.out.println(exist);
//        if(!exist){
//            throw new IllegalStateException("Product does not exist");
//        }
//
//
//        cartItemRepository.save(cartItem);


    }

    public void deleteItem(Integer cartId) {
    boolean exists= cartRepository.existsById(cartId);

    if(!exists){
        throw new IllegalStateException("Item does not exist");
    }
    cartRepository.deleteById(cartId);

    }


@Transactional
    public void updateItem(Integer cartId, Integer quantity) {
    Cart cartItem = cartRepository.findById(cartId)
            .orElseThrow(() -> new IllegalStateException("Item does not exist"));

    cartItem.setQuantity(quantity);
    cartRepository.save(cartItem);

}

    public List<Cart> getCartItems(Integer id) {
        return cartRepository.findByUserId(id);
    }


}
