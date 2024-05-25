package com.e.comm.business.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_buyer", schema = "e-comm-business")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, length = 25)
    @Size(min = 5, max = 25)
    private String username;

    @Column(name = "email", unique = true, length = 30)
    @Size(min = 10, max = 30)
    private String email;

    @OneToMany(targetEntity = BuyerWishlist.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private List<BuyerWishlist> buyerWishlists;
}
