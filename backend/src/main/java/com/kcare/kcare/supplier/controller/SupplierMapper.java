package com.kcare.kcare.supplier.controller;

import org.springframework.stereotype.Service;

import com.kcare.kcare.supplier.Model.Supplier;

@Service
public class SupplierMapper {

    public Supplier toSupplier(SupplierRequest supplierRequest) {
        return Supplier.builder()
                .businessCard(supplierRequest.getBusinessCard())
                .supplierName(supplierRequest.getSupplierName())
                .phoneNumber(supplierRequest.getPhoneNumber())
                .build();
    }

}
