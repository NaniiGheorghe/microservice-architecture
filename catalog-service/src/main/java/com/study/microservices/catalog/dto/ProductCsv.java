package com.study.microservices.catalog.dto;


import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ProductCsv {

    @CsvBindByName(column = "uniq_id")
    private String id;
    @CsvBindByName(column = "sku")
    private String sku;
    @CsvBindByName(column = "name_title")
    private String title;
    @CsvBindByName(column = "description")
    private String description;
    @CsvBindByName(column = "list_price")
    private String listPrice;
    @CsvBindByName(column = "sale_price")
    private String salePrice;
    @CsvBindByName(column = "category")
    private String category;
    @CsvBindByName(column = "category_tree")
    private String categoryTree;
    @CsvBindByName(column = "average_product_rating")
    private String averageProductRating;
    @CsvBindByName(column = "product_url")
    private String productUrl;
    @CsvBindByName(column = "product_image_urls")
    private String productImageUrls;
    @CsvBindByName(column = "brand")
    private String brand;
    @CsvBindByName(column = "total_number_reviews")
    private String totalNumberReviews;

}
