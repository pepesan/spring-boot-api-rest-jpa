package com.cursosdedesarrollo.apirestjpa.entities.herencia.tableperclass;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "ProductTable")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ProductTable")
@Data
public class MyProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private String name;

    // constructor, getters, setters
}