package com.example.cart.cart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:3000") // Allow requests from frontend
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow cookies or credentials
    }


}

//
//public ProductResponce createProduct(ProductRequest productRequest){
//
//    Origin productBySkuCode = productRepository.findBySkuCode(productRequest.skuCode());
//    if(productBySkuCode != null){
//        log.error("Product Already Exists!");
//        throw new RuntimeException("Product Already Exists!");
//    }
//
//    if(!inventoryClient.addProductToInventory(productRequest.skuCode())){
//        throw new RuntimeException("Product adding failed!");
//    };
//
//    Product product = Product.builder()
//            .name(productRequest.name())
//            .skuCode(productRequest.skuCode())
//            .category(productRequest.category())
//            .brand(productRequest.brand())
//            .description(productRequest.description())
//            .image(productRequest.image())
//            .price(productRequest.price())
//            .build();
//    productRepository.save(product);
//    log.info("Product Created Successfully!");
//    return new ProductResponce(
//            product.getId(),
//            product.getName(),
//            product.getSkuCode(),
//            product.getCategory(),
//            product.getBrand(),
//            product.getDescription(),
//            product.getImage(),
//            product.getPrice());
//
//    }