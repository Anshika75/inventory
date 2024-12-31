package com.kcare.kcare.Product.controller.productController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ProductRequest {

    private List<MultipartFile> images;
    private String productName;
    private String productCategory;
    private Integer buyingPrice;
    private Integer quantity;
    private Integer unit;
    private LocalDate expiryDate;
    private Integer thresholdValue;

    private String supplierName;
    private String phoneNumber;
    private String supplierCategory;
    private Integer sellingPrices;

}
