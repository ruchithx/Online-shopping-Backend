package com.example.cart.Client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;


public interface ProductClient {

    @GetExchange("http://localhost:8083/api/v1/product/checkquantity/{id}/{quantity}")
     boolean checkProductExist(@RequestParam Long id, @RequestParam Integer quantity);
}
