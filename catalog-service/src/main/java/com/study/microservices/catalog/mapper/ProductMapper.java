package com.study.microservices.catalog.mapper;

import com.study.microservices.catalog.dto.ProductCsv;
import com.study.microservices.catalog.dto.ProductResponse;
import com.study.microservices.catalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductResponse toResponse(Product product);

    @Mapping(target = "id", source = "id")
    Product toProductEntity(ProductCsv productCsv);

}
