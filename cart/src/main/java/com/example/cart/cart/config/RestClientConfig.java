package com.example.cart.cart.config;

import com.example.cart.Client.ProductClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("http://localhost:8083")
    private String productUrl;

    @Bean
    public ProductClient productClient(){
//        return new ProductClient();
        RestClient restClient = RestClient.builder().baseUrl(productUrl).build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory =  HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return  httpServiceProxyFactory.createClient(ProductClient.class);

    }
}
