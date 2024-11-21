package com.study.microservices.inventory.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ProductCsv {

    @CsvBindByName(column = "uniq_id")
    private String id;

}
