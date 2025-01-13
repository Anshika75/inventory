package com.kcare.kcare.Product.controller.ProductController;

import org.springframework.data.jpa.domain.Specification;

import com.kcare.kcare.Product.Model.Product;

public class ProductSpecification {

    public static Specification<Product> withProductName(String productName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productName"), productName);
    }

    public static Specification<Product> withProductNameStartingWith(String prefix) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productName"), prefix + "%");
    }

    public static Specification<Product> withProductNameContaining(String infix) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productName"), "%" + infix + "%");
    }

}
