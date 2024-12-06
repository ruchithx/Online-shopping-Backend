package com.apigateway.apigateway.Routes;


import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@CrossOrigin(origins = "http://localhost:3000")
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return GatewayRouterFunctions.route("product_service").route(RequestPredicates.path("/api/v1/product/**"), HandlerFunctions.http("http://localhost:8083")).build();

    }

    @Bean
    public RouterFunction<ServerResponse> productServiceAdminRoute(){
        return GatewayRouterFunctions.route("product_service_admin").route(RequestPredicates.path("/api/v1/admin/product/**"), HandlerFunctions.http("http://localhost:8083")).build();

    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return GatewayRouterFunctions.route("order_service").route(RequestPredicates.path("/api/v1/orders/**"), HandlerFunctions.http("http://localhost:8081")).build();
    }

    public RouterFunction<ServerResponse> orderAdminServiceRoute(){
        return GatewayRouterFunctions.route("order_service_admin").route(RequestPredicates.path("/api/v1/admin/orders/**"), HandlerFunctions.http("http://localhost:8081")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> cartServiceRoute(){
        return GatewayRouterFunctions.route("cart_service").route(RequestPredicates.path("/api/v1/cart/**"), HandlerFunctions.http("http://localhost:8082")).build();
    }




    }
