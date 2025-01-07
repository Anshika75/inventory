package com.kcare.kcare.Product.controller.ProductSubPartController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcare.kcare.Product.controller.ProductAttributeController.ProductAttributeRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productSubPart")
@RequiredArgsConstructor
public class ProductSubpartController {

    private final ProductSubpartService productSubpartService;

    @PostMapping("/saves")
    public ResponseEntity<?> addAttribute(@RequestBody ProductAttributeRequest productAttributeRequest) {
        return ResponseEntity.ok(productSubpartService.);
    }

}
