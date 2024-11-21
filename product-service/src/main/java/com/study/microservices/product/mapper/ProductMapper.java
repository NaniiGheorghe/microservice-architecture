package com.study.microservices.product.mapper;

import com.study.microservices.product.dto.ProductCatalogResponse;
import com.study.microservices.product.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper()
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse toResponse(ProductCatalogResponse catalogResponse, Long quantity);

}
