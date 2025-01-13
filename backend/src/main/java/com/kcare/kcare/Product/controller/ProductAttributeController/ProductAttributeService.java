package com.kcare.kcare.Product.controller.ProductAttributeController;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.Model.ProductAttribute;
import com.kcare.kcare.Product.controller.ProductController.ProductMapper;
import com.kcare.kcare.Product.controller.ProductController.ProductRequest;
import com.kcare.kcare.Product.repository.ProductAttributeRepository;
import com.kcare.kcare.Product.repository.ProductRepository;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.supplier.Model.Supplier;
import com.kcare.kcare.supplier.Model.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAttributeService {

    private final ProductRepository productRepository;
    private final ProductAttributeRepository productAttributeRepository;
    private final ProductMapper productMapper;
    private final SupplierRepository supplierRepository;

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

    public Response<ProductRequest> createDynamicAttribute(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        productRepository.save(product);

        return new Response<>(
                productRequest,
                LocalDateTime.now(),
                "New Column created",
                HttpStatus.CREATED);
    }

    public Response<ProductRequest> createDynamicProductTable(ProductRequest productRequest) {

        Product newProduct = new Product();

        if (productRequest.getIgstTaxPercent() != null && productRequest.getCgstTaxPercent() == null
                && productRequest.getSgstTaxPercent() == null) {
            Double calIgstTaxAmount = (productRequest.getIgstTaxPercent() / 100)
                    * productRequest.getBuyingPrice();
            log.info("productTaxAmount: ", calIgstTaxAmount);
            if (productRequest.getTotaligstTaxAmount() != calIgstTaxAmount) {
                log.info("Wrong Entered Value TotalIgstTaxAmount");
            }
            Double taxableAmount = productRequest.getBuyingPrice() + calIgstTaxAmount;
            if (productRequest.getTotaligstTaxAmount() != calIgstTaxAmount) {
                log.info("Invalid Taxalbe Data");
                // throw new MismatchedInputException(null, "sfdsf");

            }
            newProduct.setTotaligstTaxAmount(calIgstTaxAmount);
            newProduct.setTaxableAmount(taxableAmount);

        } else {

            Double calSgstTaxAmount = (productRequest.getSgstTaxPercent() / 100) * (productRequest.getBuyingPrice());

            Double calCgstTaxAmount = (productRequest.getCgstTaxPercent() / 100) * (productRequest.getBuyingPrice());

            Double totalTax = calCgstTaxAmount + calCgstTaxAmount;

        }

        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        Supplier supplier = productMapper.toSupplier(productRequest, savedProduct);
        supplierRepository.save(supplier);

        if (productRequest.getProductAttributes() == null) {
            log.info("koi extra row create kiya hi nhi tum");
        }

        if (productRequest.getProductAttributes() != null) {
            log.info("tum check kro code start hua ki nhi");
            List<ProductAttribute> productAttributes = productRequest.getProductAttributes();
            if (!productAttributes.isEmpty()) {
                productAttributes.forEach(p -> {
                    ProductAttribute productAttribute = new ProductAttribute();
                    productAttribute.setAttributeName(p.getAttributeName());
                    productAttribute.setAttributeValue(p.getAttributeValue());
                    productAttribute.setProduct(savedProduct);
                    productAttributeRepository.save(productAttribute);
                });
            }

        }
        return new Response<>(
                productRequest,
                LocalDateTime.now(),
                "Product detail saved successfully",
                HttpStatus.CREATED);
    }

}
