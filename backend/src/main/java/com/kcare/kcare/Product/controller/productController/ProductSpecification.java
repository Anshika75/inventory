package com.kcare.kcare.Product.controller.productController;

import org.springframework.data.jpa.domain.Specification;

import com.kcare.kcare.Product.Model.Product;

public class ProductSpecification {

    public static Specification<Product> withProductName(String productName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productName"), productName);
    }

}
