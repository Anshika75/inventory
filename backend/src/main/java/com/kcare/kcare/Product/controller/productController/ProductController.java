package com.kcare.kcare.Product.controller.productController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
// @Tag(name = "Product Controller")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/saveProduct", consumes = "multipart/form-data")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductRequest productRequest) {

        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping(path = "/getProduct/{productId}")
    public ResponseEntity<ProductResponse> getProductImageById(@PathVariable("productId") Integer productId) {

        ProductResponse productResponse = productService.getProductImageById(productId);

        return ResponseEntity.ok()
                .body(productResponse);

    }

}
