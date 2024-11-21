package com.study.microservices.catalog.mapper;

import com.study.microservices.catalog.dto.ProductCsv;
import com.study.microservices.catalog.dto.ProductResponse;
import com.study.microservices.catalog.entity.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-21T11:32:54+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 23.0.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
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

        id = product.getId();
        sku = product.getSku();
        title = product.getTitle();
        description = product.getDescription();
        listPrice = product.getListPrice();
        salePrice = product.getSalePrice();
        category = product.getCategory();
        categoryTree = product.getCategoryTree();
        averageProductRating = product.getAverageProductRating();
        productUrl = product.getProductUrl();
        productImageUrls = product.getProductImageUrls();
        brand = product.getBrand();
        totalNumberReviews = product.getTotalNumberReviews();

        ProductResponse productResponse = new ProductResponse( id, sku, title, description, listPrice, salePrice, category, categoryTree, averageProductRating, productUrl, productImageUrls, brand, totalNumberReviews );

        return productResponse;
    }

    @Override
    public Product toProductEntity(ProductCsv productCsv) {
        if ( productCsv == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productCsv.getId() );
        product.setSku( productCsv.getSku() );
        product.setTitle( productCsv.getTitle() );
        product.setDescription( productCsv.getDescription() );
        product.setListPrice( productCsv.getListPrice() );
        product.setSalePrice( productCsv.getSalePrice() );
        product.setCategory( productCsv.getCategory() );
        product.setCategoryTree( productCsv.getCategoryTree() );
        product.setAverageProductRating( productCsv.getAverageProductRating() );
        product.setProductUrl( productCsv.getProductUrl() );
        product.setProductImageUrls( productCsv.getProductImageUrls() );
        product.setBrand( productCsv.getBrand() );
        product.setTotalNumberReviews( productCsv.getTotalNumberReviews() );

        return product;
    }
}
