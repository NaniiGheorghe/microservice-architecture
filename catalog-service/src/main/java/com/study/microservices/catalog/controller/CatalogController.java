package com.study.microservices.catalog.controller;

import com.study.microservices.catalog.dto.ProductResponse;
import com.study.microservices.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CatalogController {

    @Value("${demo.block}")
    private String block;
    private final CatalogService catalogService;

    @GetMapping("internal/v1/products/{productId}")
    public Mono<ProductResponse> getProduct(@PathVariable("productId") String productId) {
        if (Boolean.parseBoolean(block)) {
            throw new RuntimeException("Execution is blocked");
        }
        return catalogService.getProduct(productId);
    }

    @GetMapping("internal/v1/products")
    public Flux<ProductResponse> getProducts(@RequestParam("sku") String sku) {
        return catalogService.getProducts(sku);
    }

    @GetMapping("internal/v1/products/list")
    public List<ProductResponse> getProductsList(@RequestParam("sku") String sku) {
        return catalogService.getProductsList(sku);
    }

}

