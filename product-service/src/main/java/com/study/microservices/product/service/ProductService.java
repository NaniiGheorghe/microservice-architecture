package com.study.microservices.product.service;

import com.study.microservices.product.client.ProductCatalogClient;
import com.study.microservices.product.client.ProductInventoryClient;
import com.study.microservices.product.dto.ProductCatalogResponse;
import com.study.microservices.product.dto.ProductInventoryResponse;
import com.study.microservices.product.dto.ProductResponse;
import com.study.microservices.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductCatalogClient productCatalogClient;
    private final ProductInventoryClient productInventoryClient;
    private static final ProductMapper productMapper = ProductMapper.INSTANCE;

    public List<ProductResponse> getProducts(List<String> skus) {
        var products = productCatalogClient.getProducts(skus);
        var productIds = getProductIds(products);
        var quantityByProductId = getQuantityByProductId(productIds);
        return products.stream()
                .map(product -> productMapper.toResponse(product, quantityByProductId.get(product.id())))
                .toList();
    }

    private static List<String> getProductIds(List<ProductCatalogResponse> products) {
        return products.stream()
                .map(ProductCatalogResponse::id)
                .toList();
    }

    private Map<String, Long> getQuantityByProductId(List<String> productIds) {
        return productInventoryClient.getProductsInventory(productIds).stream()
                .filter(productInventory -> productInventory.quantity() > 0)
                .collect(Collectors.toMap(ProductInventoryResponse::id, ProductInventoryResponse::quantity));
    }

    @SneakyThrows
    public ProductResponse getProduct(String productId) {
        var productCatalogFeature = CompletableFuture.supplyAsync(() -> productCatalogClient.getProduct(productId));
        var productInventoryFeature = CompletableFuture.supplyAsync(() -> productInventoryClient.getProductInventory(productId));
        var productFeature = CompletableFuture.allOf(productCatalogFeature, productInventoryFeature);
        productFeature.get();
        return productMapper.toResponse(productCatalogFeature.get(), productInventoryFeature.get().quantity());
    }
}
