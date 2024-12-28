package com.kcare.kcare.supplier.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SupplierRequest {

    private String image;
    private String name;
    private String phoneNumber;

}
