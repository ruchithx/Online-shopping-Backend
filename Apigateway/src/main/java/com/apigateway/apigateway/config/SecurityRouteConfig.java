package com.apigateway.apigateway.config;

import com.apigateway.apigateway.enums.UserRole;
import org.springframework.http.HttpMethod;

import java.util.Map;


public class SecurityRouteConfig {

    // Define free or public routes
    public static final String[] FREE_RESOURCE_URLS = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/aggregate/**",
    };

    // Define partially secured routes
    public static final Map<String, HttpMethod[]> PARTIALLY_SECURED_ROUTES = Map.of(
            "/api/v1/product/getproducts", new HttpMethod[]{HttpMethod.GET}
       //    "/api/v1/admin/product/addcategory", new HttpMethod[]{HttpMethod.POST}
    );

    // Define secured routes with their required roles
    public static final Map<String, String[]> SECURED_GET_ROUTES = Map.of(
            "/api/v1/product/**", new String[]{UserRole.CUSTOMER.toString(), UserRole.ADMIN.toString()}

    );

    public static final Map<String, String[]> SECURED_POST_ROUTES = Map.of(
            "/api/v1/admin/product/**", new String[]{UserRole.ADMIN.toString(), UserRole.CUSTOMER.toString()},
            "/api/v1/inventory/**", new String[]{UserRole.ADMIN.toString()}
    );

    public static final Map<String, String[]> SECURED_PUT_ROUTES = Map.of(
            "/api/v1/products/**", new String[]{UserRole.ADMIN.toString()}
    );

    public static final Map<String, String[]> SECURED_DELETE_ROUTES = Map.of(
            "/api/v1/products/**", new String[]{UserRole.ADMIN.toString()}
    );
}
