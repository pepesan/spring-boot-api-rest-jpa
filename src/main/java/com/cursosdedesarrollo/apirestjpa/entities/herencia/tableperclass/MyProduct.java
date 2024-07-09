package com.cursosdedesarrollo.apirestjpa.entities.herencia.tableperclass;

import jakarta.persistence.*;

@Entity(name = "ProductTable")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ProductTable")
public class MyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private String name;

    // constructor, getters, setters
}