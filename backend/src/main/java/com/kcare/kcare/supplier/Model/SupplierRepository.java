package com.kcare.kcare.supplier.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    List<Supplier> findAllByProductId(Integer productId);

}
