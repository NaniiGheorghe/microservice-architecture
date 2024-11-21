package com.study.microservices.catalog.service;

import com.study.microservices.catalog.dto.ProductResponse;
import com.study.microservices.catalog.entity.Product;
import com.study.microservices.catalog.mapper.ProductMapper;
import com.study.microservices.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final ProductRepository productRepository;
    private static final ProductMapper productMapper = ProductMapper.INSTANCE;

    public Mono<ProductResponse> getProduct(String productId) {
        return productRepository.getProduct(productId)
                .map(productMapper::toResponse);
    }

    public Flux<ProductResponse> getProducts(String sku) {
        return productRepository.getProducts(sku)
                .map(productMapper::toResponse);
    }

    public List<ProductResponse> getProductsList(String sku) {
        return productRepository.getProductsList(sku).stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
