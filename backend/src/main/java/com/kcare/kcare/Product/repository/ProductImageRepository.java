package com.kcare.kcare.Product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcare.kcare.Product.Model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    List<ProductImage> findAllByProductId(Integer productId);

}
