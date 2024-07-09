package com.cursosdedesarrollo.apirestjpa.entities.herencia.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BookSingle")
@DiscriminatorValue("1")
@Data
@NoArgsConstructor
public class Book extends MyProduct {
    private String title;

    public Book(String name, String title){
        super(name);
        this.title = title;
    }
}
