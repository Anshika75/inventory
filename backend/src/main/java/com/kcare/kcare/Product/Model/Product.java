package com.kcare.kcare.Product.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kcare.kcare.common.BaseEntity;
import com.kcare.kcare.supplier.Model.Supplier;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product")

public class Product extends BaseEntity {

    private String productName;
    private String productDescription;
    private String productCategory; // asset/cosumable
    private Integer buyingPrice; // ! sgst + cgst or igst
    private Integer quantity;
    private LocalDate expiryDate;
    private Integer thresholdValue;
    private boolean isContainSubpart;
    private Integer sgstTaxPercent;
    private Double totalsgstTaxtAmount;
    private Double cgstTaxPercent;
    private Double totalcgstTaxAmount;

    private Double igstTaxPercent;
    private Double totaligstTaxAmount;
    private Double taxableAmount; // ! taxableAmount

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductImage> image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductSubpart> productSubPart;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductAttribute> productAttributes;

}
