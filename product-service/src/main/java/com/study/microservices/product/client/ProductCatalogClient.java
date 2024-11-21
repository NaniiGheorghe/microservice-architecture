package com.study.microservices.product.client;

import com.study.microservices.product.dto.ProductCatalogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "catalog-service", url = "http://localhost:8090")
public interface ProductCatalogClient {

    @RequestMapping(method = RequestMethod.GET, value = "internal/v1/products/{productId}")
    ProductCatalogResponse getProduct(@PathVariable("productId") String productId);

    @RequestMapping(method = RequestMethod.GET, value = "internal/v1/products")
    List<ProductCatalogResponse> getProducts(@RequestParam("sku") List<String> sku);

}
