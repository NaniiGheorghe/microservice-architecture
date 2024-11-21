package com.study.microservices.product.mapper;

import com.study.microservices.product.dto.ProductCatalogResponse;
import com.study.microservices.product.dto.ProductResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-21T11:21:03+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 23.0.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toResponse(ProductCatalogResponse catalogResponse, Long quantity) {
        if ( catalogResponse == null && quantity == null ) {
            return null;
        }

        String id = null;
        String sku = null;
        String title = null;
        String description = null;
        String listPrice = null;
        String salePrice = null;
        String category = null;
        String categoryTree = null;
        String averageProductRating = null;
        String productUrl = null;
        String productImageUrls = null;
        String brand = null;
        String totalNumberReviews = null;
        if ( catalogResponse != null ) {
            id = catalogResponse.id();
            sku = catalogResponse.sku();
            title = catalogResponse.title();
            description = catalogResponse.description();
            listPrice = catalogResponse.listPrice();
            salePrice = catalogResponse.salePrice();
            category = catalogResponse.category();
            categoryTree = catalogResponse.categoryTree();
            averageProductRating = catalogResponse.averageProductRating();
            productUrl = catalogResponse.productUrl();
            productImageUrls = catalogResponse.productImageUrls();
            brand = catalogResponse.brand();
            totalNumberReviews = catalogResponse.totalNumberReviews();
        }
        Long quantity1 = null;
        quantity1 = quantity;

        ProductResponse productResponse = new ProductResponse( id, sku, title, description, listPrice, salePrice, category, categoryTree, averageProductRating, productUrl, productImageUrls, brand, totalNumberReviews, quantity1 );

        return productResponse;
    }
}
