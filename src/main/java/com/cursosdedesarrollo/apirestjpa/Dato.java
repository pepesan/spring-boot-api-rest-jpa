package com.cursosdedesarrollo.apirestjpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
public class Dato {
    private Long id;

    private String cadena;

    public Dato(){
        this.id = 0L;
        this.cadena = "";
    }

}
