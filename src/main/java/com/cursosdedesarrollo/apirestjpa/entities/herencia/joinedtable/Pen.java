package com.cursosdedesarrollo.apirestjpa.entities.herencia.joinedtable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PenJoined")
@PrimaryKeyJoinColumn(name = "productId")
@Data
@NoArgsConstructor
public class Pen extends MyProduct {
    private String tint;
}