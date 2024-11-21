package com.study.microservices.catalog.entity;

import lombok.Data;

@Data
public final class Product {

    private String id;
    private String sku;
    private String title;
    private String description;
    private String listPrice;
    private String salePrice;
    private String category;
    private String categoryTree;
    private String averageProductRating;
    private String productUrl;
    private String productImageUrls;
    private String brand;
    private String totalNumberReviews;

}
