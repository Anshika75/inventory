package com.kcare.kcare.Product.controller.productController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kcare.kcare.common.PageResponse;
import com.kcare.kcare.common.Response;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
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
    public ResponseEntity<Response<ProductRequest>> createProduct(@RequestBody ProductRequest productRequest,
            @RequestPart(name = "file", required = false) List<MultipartFile> file) {
        return ResponseEntity.ok(productService.createProduct(productRequest, file));
    }

    @GetMapping(path = "/getProduct/{productId}")
    public ResponseEntity<ProductResponse> getProductImageById(@PathVariable("productId") Integer productId) {

        ProductResponse productResponse = productService.getProductImageById(productId);
        return ResponseEntity.ok()
                .body(productResponse);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProduct(
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "6", required = false) int size) {

        return ResponseEntity.ok(productService.getAllProduct(productName, page,
                size));
    }

    @PostMapping(path = "/createDynamicTable", consumes = "application/json")
    public ResponseEntity<Response<ProductRequest>> createDynamicProductTable(
            @RequestBody @Valid ProductRequest productRequest) {

        return ResponseEntity.ok(productService.createDynamicProductTable(productRequest));
    }

}
