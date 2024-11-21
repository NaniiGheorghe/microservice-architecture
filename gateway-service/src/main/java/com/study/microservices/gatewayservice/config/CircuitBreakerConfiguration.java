package com.study.microservices.gatewayservice.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addRequestHeader;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;


@Configuration
public class CircuitBreakerConfiguration {

    @Value("${PRODUCT_SERVICE_URL:http://localhost:8092}")
    private String productServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> routeConfig() {
        return route("product-route")
                .route(path("/product-service/**"), http(productServiceUrl))
                .filter(rewritePath("/product-service/?(?<segment>.*)", "/$\\{segment}"))
                .filter(addRequestHeader("X-Gateway","gateway-service"))
                .filter(circuitBreaker(config -> config
                        .setId("productService")
                        .setFallbackUri(URI.create("forward:/product-fallback"))
                        .setStatusCodes("503"))
                )
                .build();
    }
}
