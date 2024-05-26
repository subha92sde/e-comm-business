package com.e.comm.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_order_detail", schema = "e-comm-business")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "unit")
    @NotNull
    private int unit;

    @Column(name = "total_price")
    @NotNull
    private BigDecimal totalPrice;

    @JsonIgnore
    @ManyToOne
    private Buyer buyer;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    public enum OrderStatus {
        sold;
    }

    @Column(name = "placed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date placedAt;
}
