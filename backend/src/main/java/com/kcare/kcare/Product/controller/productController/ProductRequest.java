package com.kcare.kcare.Product.controller.productController;

import java.time.LocalDate;
import java.util.List;

import com.kcare.kcare.Product.Model.ProductAttribute;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequest {

    // ! product Details
    // private List<MultipartFile> images;
    @NotNull(message = "productName should not be null")
    private String productName;
    private String productCategory;
    private Integer buyingPrice;
    private Integer quantity;
    private Integer unit;
    private LocalDate expiryDate;
    private Integer thresholdValue;
    private Integer parentProductId;
    private List<ProductAttribute> productAttributes;

    // ! supplier Detail
    private String supplierName;
    private String phoneNumber;
    private String supplierCategory;
    private Integer sellingPrices;

}
