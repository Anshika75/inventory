package com.kcare.kcare.Product.controller.ProductAttributeController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/productAttribute")
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    @PostMapping("/addProuduct")
    public ResponseEntity<?> addAttriubte(@RequestBody ProductAttributeRequest productAttributeRequest) {

        return ResponseEntity.ok(productAttributeService.addAttriubte(productAttributeRequest));
    }

}
