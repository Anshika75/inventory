package com.kcare.kcare.Product.controller.productController;

import java.time.LocalDate;
import java.util.List;

import com.kcare.kcare.Product.Model.ProductAttribute;
import com.kcare.kcare.supplier.Model.Supplier;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ProductResponse {

    private Integer productId;
    private String productName;
    private String category;
    private Integer buyingPrice;
    private Integer quantity;
    private Integer unit;
    private LocalDate expiryDate;
    private Integer thresholdValue;
    private List<String> images;
    private List<Supplier> suppliers;
    private List<ProductSubpartResponse> productSubparts;
    private List<ProductAttribute> productAttributes;

}
