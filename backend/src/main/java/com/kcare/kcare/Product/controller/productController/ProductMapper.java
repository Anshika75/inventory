package com.kcare.kcare.Product.controller.ProductController;

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
                .expiryDate(request.getExpiryDate())
                .thresholdValue(request.getThresholdValue())
                .isContainSubpart(request.isContainSubpart())
                .sgstTaxPercent(request.getSgstTaxPercent())
                .totalsgstTaxtAmount(request.getTotalsgstTaxtAmount())
                .cgstTaxPercent(request.getIgstTaxPercent())
                .totalcgstTaxAmount(request.getTotalcgstTaxAmount())
                .igstTaxPercent(request.getIgstTaxPercent())
                .totaligstTaxAmount(request.getTotaligstTaxAmount())
                .taxableAmount(request.getTaxableAmount())
                .build();
    }

    public Supplier toSupplier(ProductRequest request, Product product) {
        return Supplier.builder()
                .supplierName(request.getSupplierName())
                .contact(request.getPhoneNumber())
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
                .productCategory(product.getProductCategory())
                .buyingPrice(product.getBuyingPrice())
                .quantity(product.getQuantity())
                .expiryDate(product.getExpiryDate())
                .thresholdValue(product.getThresholdValue())
                .sgstTaxPercent(product.getSgstTaxPercent())
                .totalsgstTaxtAmount(product.getTotalsgstTaxtAmount())
                .cgstTaxPercent(product.getCgstTaxPercent())
                .totalcgstTaxAmount(product.getTotalcgstTaxAmount())
                .igstTaxPercent(product.getIgstTaxPercent())
                .totaligstTaxAmount(product.getTotaligstTaxAmount())
                .taxableAmount(product.getTaxableAmount())
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
