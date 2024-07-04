package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;


@Data
@Entity
@Table(name = "category")
public class Category implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "Ccategory_id", unique = true, nullable = false)
    private Integer categoryId;
    @Column(name = "NAME", nullable = false, length = 10)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Stock> stocks = new HashSet<Stock>(0);



}
