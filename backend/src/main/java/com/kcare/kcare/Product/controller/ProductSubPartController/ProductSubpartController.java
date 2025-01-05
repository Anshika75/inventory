package com.kcare.kcare.Product.controller.ProductSubPartController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productSubPart")
@RequiredArgsConstructor
public class ProductSubpartController {

    private final ProductSubpartService productSubpartService;

    @PostMapping("/saves")
    public String postMethodName(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

}
