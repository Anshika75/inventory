package com.kcare.kcare.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcare.kcare.Product.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}