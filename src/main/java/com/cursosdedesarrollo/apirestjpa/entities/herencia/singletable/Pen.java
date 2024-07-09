package com.cursosdedesarrollo.apirestjpa.entities.herencia.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PenSingle")
@DiscriminatorValue("2")
@Data
@NoArgsConstructor
public class Pen extends MyProduct {
    private String tint;

    public Pen(String name, String tint){
        super(name);
        this.tint = tint;
    }
}