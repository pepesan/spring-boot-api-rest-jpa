package com.cursosdedesarrollo.apirestjpa.dto;


import com.cursosdedesarrollo.apirestjpa.dto.Dato;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Unit")
public class DatoTest {
    @Test
    public void testConstructorWithOutFields(){
        // GIVEN
        Dato dato;
        // WHEN
        dato = new Dato();
        // THEN
        assertEquals(0L, dato.getId());
        assertEquals("", dato.getCadena());
    }
}
