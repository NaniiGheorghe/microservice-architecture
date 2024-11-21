package com.study.microservices.inventory.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import com.study.microservices.inventory.dto.ProductCsv;
import com.study.microservices.inventory.entity.ProductInventory;
import com.study.microservices.inventory.mapper.ProductInventoryMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class ProductInventoryRepository {

    private static final String PRODUCT_CSV_FILE = "/datasource/jcpenney_com-ecommerce_sample.csv";
    private final ConcurrentHashMap<String, ProductInventory> productById = new ConcurrentHashMap<>();
    private static final ProductInventoryMapper productInventoryMapper = ProductInventoryMapper.INSTANCE;

    @PostConstruct
    public void initProductList() throws FileNotFoundException {
        log.info("Initializing in memory product inventory map.");
        String path = Objects.requireNonNull(getClass().getResource(PRODUCT_CSV_FILE)).getPath();
        new CsvToBeanBuilder<ProductCsv>(new FileReader(path))
                .withType(ProductCsv.class)
                .build()
                .parse()
                .forEach(product -> productById.put(product.getId(), productInventoryMapper.toProductInventoryEntity(product)));
    }


    public List<ProductInventory> getProductsInventory(List<String> productIds) {
        return productById.entrySet().stream()
                .filter(entry -> productIds.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .toList();
    }
}
