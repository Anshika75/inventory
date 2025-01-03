package com.kcare.kcare.supplier.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.common.Response;
import com.kcare.kcare.supplier.Model.Supplier;
import com.kcare.kcare.supplier.Model.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public Response<SupplierRequest> saveSupplierDetail(SupplierRequest supplierRequest) {

        Supplier supplier = supplierMapper.toSupplier(supplierRequest);
        supplierRepository.save(supplier);
        return new Response<>(
                supplierRequest,
                LocalDateTime.now(),
                "Supplier detail saved",
                HttpStatus.CREATED

        );

    }

}
