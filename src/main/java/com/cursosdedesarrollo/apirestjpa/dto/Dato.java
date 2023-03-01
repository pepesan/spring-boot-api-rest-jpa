package com.cursosdedesarrollo.apirestjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
public class Dato {
    private Long id;

    @NonNull
    private String cadena;

    public Dato(){
        this.id = 0L;
        this.cadena = "";
    }

}
