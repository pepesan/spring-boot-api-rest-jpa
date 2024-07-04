package com.cursosdedesarrollo.apirestjpa.entities.relations.manytomanybi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;


@Data
@Entity
//@Table(name = "stock", uniqueConstraints = {
//        @UniqueConstraint(columnNames = "STOCK_NAME"),
//        @UniqueConstraint(columnNames = "STOCK_CODE") })
public class Stock implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "stock_id", unique = true, nullable = false)
    private Integer stockId;
    @Column(name = "STOCK_CODE", unique = true, nullable = false, length = 10)
    private String stockCode;
    @Column(name = "STOCK_NAME", unique = true, nullable = false, length = 20)
    private String stockName;
    @ManyToMany
    @JoinTable(
            name = "stock_category",
            joinColumns = @JoinColumn(name = "stock_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

}


