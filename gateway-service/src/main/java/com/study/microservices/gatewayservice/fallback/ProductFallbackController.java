package com.study.microservices.gatewayservice.fallback;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductFallbackController {

    @GetMapping("/product-fallback")
    public ResponseEntity<String> getFallback() {
        return new ResponseEntity<>("Products currently not available", HttpStatusCode.valueOf(503));
    }

}
