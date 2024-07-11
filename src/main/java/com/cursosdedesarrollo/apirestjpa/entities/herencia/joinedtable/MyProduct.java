package com.cursosdedesarrollo.apirestjpa.entities.herencia.joinedtable;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "ProductJoined")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class MyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String name;

    // constructor, getters, setters
}