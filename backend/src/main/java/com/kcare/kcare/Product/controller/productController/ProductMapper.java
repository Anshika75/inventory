package com.kcare.kcare.Product.controller.productController;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.Model.ProductAttribute;
import com.kcare.kcare.Product.Model.ProductSubpart;
import com.kcare.kcare.supplier.Model.Supplier;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .productName(request.getProductName())
                .productCategory(request.getProductCategory())
                .buyingPrice(request.getBuyingPrice())
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .expiryDate(request.getExpiryDate())
                .thresholdValue(request.getThresholdValue())
                .build();
    }

    public Supplier toSupplier(ProductRequest request, Product product) {
        return Supplier.builder()
                .supplierName(request.getSupplierName())
                .phoneNumber(request.getPhoneNumber())
                .supplierCategory(request.getSupplierCategory())
                .sellingPrices(request.getSellingPrices())
                .product(product)
                .build();
    }

    public ProductResponse toProductResponse(Product product, List<String> images, List<Supplier> supplier,
            List<ProductSubpartResponse> productSubpartResponses, List<ProductAttribute> productAttributes) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                // .productC(product.getCategory())
                .buyingPrice(product.getBuyingPrice())
                .quantity(product.getQuantity())
                .unit(product.getUnit())
                .expiryDate(product.getExpiryDate())
                .thresholdValue(product.getThresholdValue())
                .images(images)
                .suppliers(supplier)
                .productSubparts(productSubpartResponses)
                .productAttributes(productAttributes)
                .build();

    }

    public ProductSubpart toProductSubPart(Integer productId, Product parentProduct) {

        return ProductSubpart.builder()
                .subProductId(productId)
                .product(parentProduct)
                .build();

    }

    public ProductSubpartResponse toProductSubpartResponse(ProductSubpart productSubpart, String productName) {
        return ProductSubpartResponse.builder()
                .id(productSubpart.getId())
                .productId(productSubpart.getSubProductId())
                .productName(productName)
                .build();
    }

}
