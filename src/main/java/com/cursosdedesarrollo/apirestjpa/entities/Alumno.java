package com.cursosdedesarrollo.apirestjpa.entities;

// Alumno.java

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity(name = "Alumnos")
@Table(name = "ALUMNOS")
public class Alumno {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 3, max = 20, message = "el nombre debe tener mas de 3 letras y menos de 20.")
    private String nombre;

    private String apellidos;

    @Min(value = 18, message = "el usuario debe tener 18+")
    private  Integer edad;

}