package com.study.microservices.product.dto;

public record ProductCatalogResponse(String id, String sku, String title, String description, String listPrice,
                                     String salePrice, String category, String categoryTree,
                                     String averageProductRating, String productUrl, String productImageUrls,
                                     String brand, String totalNumberReviews) {
}