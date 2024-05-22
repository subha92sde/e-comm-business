package com.e.comm.business.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Transient;
import jakarta.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_product", schema = "e-comm-business")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_title", unique = true, length = 255)
    @Size(min = 5, max = 255)
    private String productTitle;

    @JsonIgnore
    @ManyToOne
    private Category category;

    @JsonIgnore
    @ManyToOne
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "active", length = 3)
    @Size(min = 2, max = 3)
    private ProductStatus active;

    @JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    ProductStockInfo productStockInfo;

    public enum ProductStatus {
        yes, no;
    }

    @Transient
    private Long categoryId;

    public Long getCategoryId() {
        return this.getCategory().getId();
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Transient
    private String categoryName;

    public String getCategoryName() {
        return this.getCategory().getCategoryTitle();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Transient
    private Long brandId;

    public Long getBrandId() {
        return this.getBrand().getId();
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @Transient
    private String brandName;

    public String getBrandName() {
        return this.getBrand().getBrandTitle();
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Transient
    private int inStockQuantity;

    public int getInStockQuantity() {
        return this.getProductStockInfo().getInStockQuantity();
    }

    public void setInStockQuantity(int inStockQuantity) {
        this.inStockQuantity = inStockQuantity;
    }

    @Transient
    private int maximumOrderCapacityPerBuyer;

    public int getMaximumOrderCapacityPerBuyer() {
        return this.getProductStockInfo().getMaximumOrderCapacityPerBuyer();
    }

    public void setMaximumOrderCapacityPerBuyer(int maximumOrderCapacityPerBuyer) {
        this.maximumOrderCapacityPerBuyer = maximumOrderCapacityPerBuyer;
    }

    @Transient
    private BigDecimal pricePerUnit;

    public BigDecimal getPricePerUnit() {
        return this.getProductStockInfo().getPricePerUnit();
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Transient
    private int unitDefinition;

    public int getUnitDefinition() {
        return this.getProductStockInfo().getUnitDefinition();
    }

    public void setUnitDefinition(int unitDefinition) {
        this.unitDefinition = unitDefinition;
    }

    @Transient
    private ProductStockInfo.CurrencyType currencyType;

    public ProductStockInfo.CurrencyType getCurrencyType() {
        return this.getProductStockInfo().getCurrencyType();
    }

    public void setCurrencyType(ProductStockInfo.CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
