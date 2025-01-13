package com.kcare.kcare.Product.controller.ProductController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kcare.kcare.common.PageResponse;
import com.kcare.kcare.common.Response;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService newProductService;

    @PostMapping("/createDynamicTable")
    public ResponseEntity<Response<ProductRequest>> createDynamicProductTable(
            @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(newProductService.createDynamicProductTable(productRequest));
    }

    @PostMapping(path = "/saveProduct", consumes = { "application/json",
            "multipart/form-data" })
    public ResponseEntity<Response<ProductRequest>> createProduct(@RequestBody ProductRequest productRequest,
            @RequestPart(name = "file", required = false) List<MultipartFile> file) {
        return ResponseEntity.ok(newProductService.createProduct(productRequest, file));
    }

    @GetMapping(path = "/getProduct/{productId}")
    public ResponseEntity<ProductResponse> getProductImageById(@PathVariable("productId") Integer productId) {
        ProductResponse productResponse = newProductService.getProductImageById(productId);
        return ResponseEntity.ok()
                .body(productResponse);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProduct(
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "6", required = false) int size) {
        return ResponseEntity.ok(newProductService.getAllProduct(productName, page,
                size));
    }

    // @PostMapping(path = "/saveProduct", consumes = { "application/json",
    // "multipart/form-data" })
    // public ResponseEntity<Response<ProductRequest>> createProduct(@RequestBody
    // ProductRequest productRequest,
    // @RequestPart(name = "file", required = false) List<MultipartFile> file) {
    // return ResponseEntity.ok(productService.createProduct(productRequest, file));
    // }

    // @GetMapping("/searchProduct")

    // public ResponseEntity<PageResponse<ProductResponse>> searchProduct(

    // )

}
