package com.kcare.kcare.supplier.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcare.kcare.common.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/suppler")
@RequiredArgsConstructor
// @Tag( name="Supplier Controller")
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/")
    public ResponseEntity<Response<SupplierRequest>> saveSupplierDetail(@RequestBody SupplierRequest supplierRequest) {

        return ResponseEntity.ok(supplierService.saveSupplierDetail(supplierRequest));
    }

}
