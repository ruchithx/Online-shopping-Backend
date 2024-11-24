package com.example.cart.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface ProductClient
{
    @GetExchange("/api/product")
boolean isInStock (@RequestParam int productId, @RequestParam int quantity);
}
