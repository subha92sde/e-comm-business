package com.e.comm.business.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_category", schema = "e-comm-business")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_title")
    private String categoryTitle;
}
