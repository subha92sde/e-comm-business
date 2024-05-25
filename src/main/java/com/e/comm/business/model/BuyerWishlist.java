package com.e.comm.business.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tbl_buyer_wishlist", schema = "e-comm-business", uniqueConstraints = @UniqueConstraint(columnNames = {"product", "buyer"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerWishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Buyer buyer;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Transient
    private Long productId;

    public Long getProductId() {
        return this.getProduct().getId();
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Transient
    private Long buyerId;

    public Long getBuyerId() {
        return this.getBuyer().getId();
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    @Transient
    private String productTitle;

    public String getProductTitle() {
        return this.getProduct().getProductTitle();
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    @Transient
    private String username;

    public String getUsername() {
        return this.getBuyer().getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
