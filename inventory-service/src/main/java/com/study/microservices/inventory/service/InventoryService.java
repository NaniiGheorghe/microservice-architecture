package com.study.microservices.inventory.service;

import com.study.microservices.inventory.dto.ProductInventoryResponse;
import com.study.microservices.inventory.mapper.ProductInventoryMapper;
import com.study.microservices.inventory.repository.ProductInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductInventoryRepository productRepository;
    private static final ProductInventoryMapper productInventoryMapper = ProductInventoryMapper.INSTANCE;

    public List<ProductInventoryResponse> getProductInventory(List<String> productIds) {
        return productRepository.getProductsInventory(productIds).stream()
                .map(productInventoryMapper::toResponse)
                .toList();
    }
}
