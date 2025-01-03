package com.kcare.kcare.supplier.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "suppliersDetail")

public class Supplier extends BaseEntity {

    private String businessCard;
    private String supplierName;
    private String phoneNumber;
    private String supplierCategory;
    private Integer sellingPrices;
    // private ReturnType

    @ManyToOne
    @JoinColumn(name = "product_Id")
    @JsonBackReference
    private Product product;

}
