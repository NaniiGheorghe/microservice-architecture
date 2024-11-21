package com.study.microservices.inventory.mapper;

import com.study.microservices.inventory.dto.ProductCsv;
import com.study.microservices.inventory.dto.ProductInventoryResponse;
import com.study.microservices.inventory.entity.ProductInventory;
import java.util.Random;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-21T11:20:27+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 23.0.1 (Oracle Corporation)"
)
public class ProductInventoryMapperImpl implements ProductInventoryMapper {

    @Override
    public ProductInventoryResponse toResponse(ProductInventory productInventory) {
        if ( productInventory == null ) {
            return null;
        }

        String id = null;
        Long quantity = null;

        id = productInventory.id();
        quantity = productInventory.quantity();

        ProductInventoryResponse productInventoryResponse = new ProductInventoryResponse( id, quantity );

        return productInventoryResponse;
    }

    @Override
    public ProductInventory toProductInventoryEntity(ProductCsv productInventoryCsv) {
        if ( productInventoryCsv == null ) {
            return null;
        }

        String id = null;

        id = productInventoryCsv.getId();

        Long quantity = new Random().nextLong(10000);

        ProductInventory productInventory = new ProductInventory( id, quantity );

        return productInventory;
    }
}
