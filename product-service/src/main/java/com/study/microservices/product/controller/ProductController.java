package com.study.microservices.product.controller;

import com.study.microservices.product.dto.ProductResponse;
import com.study.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("v1/products")
    public List<ProductResponse> getProduct(@RequestParam("skus") List<String> skus) {
        return productService.getProducts(skus);
    }

    @GetMapping("v1/products/{productId}")
    public ProductResponse getProduct(@PathVariable("productId") String productId) {
        return productService.getProduct(productId);
    }

}

