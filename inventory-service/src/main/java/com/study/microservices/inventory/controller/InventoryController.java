package com.study.microservices.inventory.controller;

import com.study.microservices.inventory.dto.ProductInventoryResponse;
import com.study.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class InventoryController {

    @Value("${demo.block}")
    private String block;

    private final InventoryService inventoryService;

    @GetMapping("internal/v1/products/inventory")
    public List<ProductInventoryResponse> getProductsInventory(@RequestParam("productIds") List<String> productIds) {
        if (Boolean.parseBoolean(block)) {
            throw new RuntimeException("Block execution");
        }
        return inventoryService.getProductInventory(productIds);
    }

}

