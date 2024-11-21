package com.study.microservices.catalog.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import com.study.microservices.catalog.dto.ProductCsv;
import com.study.microservices.catalog.entity.Product;
import com.study.microservices.catalog.mapper.ProductMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class ProductRepository {

    private static final String PRODUCT_CSV_FILE = "/datasource/jcpenney_com-ecommerce_sample.csv";
    private final ConcurrentHashMap<String, Product> productById = new ConcurrentHashMap<>();
    private Flux<Product> productFlux;
    private static final ProductMapper productMapper = ProductMapper.INSTANCE;

    @PostConstruct
    public void initProductList() throws FileNotFoundException{
        log.info("Initializing in memory product map.");
        String path = Objects.requireNonNull(getClass().getResource(PRODUCT_CSV_FILE)).getPath();
        new CsvToBeanBuilder<ProductCsv>(new FileReader(path))
                .withType(ProductCsv.class)
                .build()
                .parse()
                .forEach(productCsv -> {
                    productById.put(productCsv.getId(), productMapper.toProductEntity(productCsv));
                });
        productFlux = Flux.fromIterable(productById.values());
    }

    public Mono<Product> getProduct(String productId) {
        return Mono.just(productById.get(productId));
    }

    public Flux<Product> getProducts(String sku) {
        return productFlux.
                filter(product -> sku.equals(product.getSku()))
                .map(product -> {
                    sleep();
                    return product;
                });
    }

    public List<Product> getProductsList(String sku) {
        return productById.values().stream()
                .filter(product -> product.getSku().equals(sku))
                .map(product -> {
                    sleep();
                    return product;
                })
                .toList();
    }

    private static void sleep() {
        try {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName());
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
