package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "Category_id", unique = true, nullable = false)
    private Integer categoryId;
    @Column(name = "NAME", nullable = false, length = 10)
    private String name;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "categories")
    //@JsonBackReference
    private Set<Stock> stocks = new HashSet<Stock>(0);



}
