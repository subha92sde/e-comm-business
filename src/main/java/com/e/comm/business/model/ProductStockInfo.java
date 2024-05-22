package com.e.comm.business.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_product_stock_info", schema = "e-comm-business")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockInfo {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Product product;

    @Column(name = " in_stock_quantity")
    private int inStockQuantity;

    @Column(name = "maximum_order_capacity_per_buyer")
    private int maximumOrderCapacityPerBuyer;

    @Column(name = "unit_definition")
    private int unitDefinition;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type", length = 3)
    @Size(min = 3, max = 3)
    private CurrencyType currencyType;

    public enum CurrencyType {
        BDT;
    }
}
