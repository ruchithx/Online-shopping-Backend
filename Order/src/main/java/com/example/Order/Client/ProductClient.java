package com.example.Order.Client;

import com.example.Order.dto.ProductDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface ProductClient {

    @PostExchange("http://product-service:8083/api/v1/product/getproductsbyids")
    List<ProductDTO> getProductById(@RequestBody int[] id);
}
