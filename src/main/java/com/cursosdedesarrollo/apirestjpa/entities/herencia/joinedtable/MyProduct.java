package com.cursosdedesarrollo.apirestjpa.entities.herencia.joinedtable;

import jakarta.persistence.*;

@Entity(name = "ProductJoined")
@Inheritance(strategy = InheritanceType.JOINED)
public class MyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String name;

    // constructor, getters, setters
}