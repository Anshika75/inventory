package com.kcare.kcare.Product.controller.productController;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductSubpartResponse {

    private Integer id;
    private Integer productId;
    private String productName;

}