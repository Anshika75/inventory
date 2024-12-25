package com.kcare.kcare.Product.Model;

import java.time.LocalDate;
import java.util.List;

import com.kcare.kcare.common.BaseEntity;

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
    private String category;
    private Integer buyingPrice;
    private Integer quantity;
    private Integer unit;
    private LocalDate expiryDate;
    private Integer thresholdValue;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> image;

}
