package com.study.microservices.inventory.mapper;

import com.study.microservices.inventory.dto.ProductCsv;
import com.study.microservices.inventory.dto.ProductInventoryResponse;
import com.study.microservices.inventory.entity.ProductInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Random;

@Mapper(imports = {Random.class})
public interface ProductInventoryMapper {

    ProductInventoryMapper INSTANCE = Mappers.getMapper(ProductInventoryMapper.class);

    ProductInventoryResponse toResponse(ProductInventory productInventory);

    @Mapping(target = "quantity", expression = "java(new Random().nextLong(10000))")
    ProductInventory toProductInventoryEntity(ProductCsv productInventoryCsv);


}
