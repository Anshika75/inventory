package com.kcare.kcare.Product.controller.productController;

import java.time.LocalDate;
import java.util.List;

import com.kcare.kcare.supplier.Model.Supplier;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
// @NoArgsConstructor

public class ProductResponse {

    private String productName;
    private String category;
    private Integer buyingPrice;
    private Integer quantity;
    private Integer unit;
    private LocalDate expiryDate;
    private Integer thresholdValue;
    private List<String> images;
    // private List<Supplier> suppliers;
    // private String supplierName;
    // private String supplierPhoneNumber;
    private List<Supplier> suppliers;

}
