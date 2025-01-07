package com.kcare.kcare.Product.controller.ProductAttributeController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.Model.ProductAttribute;
import com.kcare.kcare.Product.repository.ProductAttributeRepository;
import com.kcare.kcare.Product.repository.ProductRepository;
import com.kcare.kcare.common.Response;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final ProductRepository productRepository;
    private final ProductAttributeRepository productAttributeRepository;

    public Response<ProductAttributeRequest> addAttriubte(ProductAttributeRequest productAttributeRequest) {
        Product product = productRepository.findById(productAttributeRequest.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product Not Available with Id: " +
                        productAttributeRequest.getProductId()));

        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setAttributeName(productAttributeRequest.getAttributeName());
        productAttribute.setAttributeValue(productAttribute.getAttributeValue());
        productAttribute.setProduct(product);
        productAttributeRepository.save(productAttribute);

        return new Response<>(
                productAttributeRequest,
                LocalDateTime.now(),
                "New Column created",
                HttpStatus.CREATED);

    }

}
