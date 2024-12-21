package com.example.cart.Client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProductClient {

    @GetExchange("http://localhost:8083/api/v1/product/checkquantity/{id}/{quantity}")
    boolean checkProductExist(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity);
}
