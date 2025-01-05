package com.kcare.kcare.Product.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "productSubPart")

public class ProductSubpart extends BaseEntity {

    private Integer subProductId;

    @ManyToOne
    @JoinColumn(name = "parentProductId")
    @JsonBackReference
    private Product product;

}
