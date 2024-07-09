package com.cursosdedesarrollo.apirestjpa.entities.herencia.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PenTable")
@Data
@NoArgsConstructor
public class Pen extends MyProduct {
    private String tint;
}