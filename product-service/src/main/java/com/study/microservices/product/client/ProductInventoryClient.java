package com.study.microservices.product.client;

import com.study.microservices.product.dto.ProductInventoryResponse;
import com.study.microservices.product.service.ProductNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "inventory-service", url = "http://localhost:8091")
public interface ProductInventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "internal/v1/products/inventory")
    List<ProductInventoryResponse> getProductsInventory(@RequestParam("productIds") List<String> productIds);

    default ProductInventoryResponse getProductInventory(String productId){
        return getProductsInventory(List.of(productId)).stream()
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

}
