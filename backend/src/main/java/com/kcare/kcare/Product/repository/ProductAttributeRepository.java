package com.kcare.kcare.Product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcare.kcare.Product.Model.ProductAttribute;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
    List<ProductAttribute> findAllByProductId(Integer productId);

}
