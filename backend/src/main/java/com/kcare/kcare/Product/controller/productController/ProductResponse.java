package com.kcare.kcare.Product.controller.ProductController;

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
    private String productDescription;
    private String productCategory; // asset/cosumable
    private Double buyingPrice; // ! sgst + cgst or igst
    private Integer quantity;
    private LocalDate expiryDate;
    private Integer thresholdValue;
    // private boolean isContainSubpart;
    private Integer sgstTaxPercent;
    private Double totalsgstTaxtAmount;
    private Integer cgstTaxPercent;
    private Double totalcgstTaxAmount;

    private Integer igstTaxPercent;
    private Double totaligstTaxAmount;
    private Double taxableAmount;

    private List<String> images;
    private List<Supplier> suppliers;
    private List<ProductSubpartResponse> productSubparts;
    private List<ProductAttribute> productAttributes;

}
