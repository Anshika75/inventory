package com.kcare.kcare.Product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcare.kcare.Product.Model.ProductSubpart;

public interface ProductSubpartRepository extends JpaRepository<ProductSubpart, Integer> {

    List<ProductSubpart> findAllByProductId(Integer productId);

}
