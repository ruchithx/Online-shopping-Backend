package com.example.cart.cart.config;

import com.example.cart.client.ProductClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
    @Value("${product.url}")
    private String productUrl;
    @Bean
    public ProductClient productClient() {
        RestClient restClient =RestClient.builder().baseUrl(productUrl).build();

        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory= HttpServiceProxyFactory.builderFor(restClientAdapter).build();


        return httpServiceProxyFactory.createClient(ProductClient.class );
    }



}
