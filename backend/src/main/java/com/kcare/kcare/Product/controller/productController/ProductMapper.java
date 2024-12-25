package com.kcare.kcare.Product.controller.productController;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {

        return Product.builder()
                .productName(request.getProductName())
                .category(request.getCategory())
                .buyingPrice(request.getBuyingPrice())
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .expiryDate(request.getExpiryDate())
                .thresholdValue(request.getThresholdValue())
                .build();

    }

    public ProductResponse toProductResponse(Product product, List<String> images) {

        return ProductResponse.builder()
                .productName(product.getProductName())
                .category(product.getCategory())
                .buyingPrice(product.getBuyingPrice())
                .quantity(product.getQuantity())
                .unit(product.getUnit())
                .expiryDate(product.getExpiryDate())
                .thresholdValue(product.getThresholdValue())
                .images(images)
                .build();

    }

}
