package com.kcare.kcare.Product.controller.ProductAttributeController;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductAttributeRequest {

    private Integer productId;
    private String attributeName;
    private String attributeValue;

}
