package com.kcare.kcare.supplier.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SupplierRequest {

    private String businessCard;
    private String supplierName;
    private String phoneNumber;

}
