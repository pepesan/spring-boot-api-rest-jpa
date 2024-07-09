package com.cursosdedesarrollo.apirestjpa.entities.herencia.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BookTable")
@Data
@NoArgsConstructor
public class Book extends MyProduct {
    private String title;
}
