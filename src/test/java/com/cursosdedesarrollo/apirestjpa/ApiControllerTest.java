package com.cursosdedesarrollo.apirestjpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Tag("Aceptance")
public class ApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    public void clearRestData() throws Exception {
        System.out.println("limpiando");
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/dato")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testListShouldReturnOkResult() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/dato")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testListShouldReturnEmptyArray() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/dato")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(mapper.writeValueAsString(new ArrayList<Dato>())));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testAddShouldReturnDato() throws Exception {
        // Given
        Long id = 0L;
        String cadena = "valor";
        Dato datoEntrada = new Dato(id, cadena);
        Dato datoSalida = new Dato(1L, cadena);
        // WHEN
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/dato")
                                .content(asJsonString(datoEntrada))
                                .contentType(MediaType.APPLICATION_JSON))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(datoSalida)));
    }

    @Test
    public void testGetByIDShouldReturnDato() throws Exception {
        testAddShouldReturnDato();
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/dato/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(new Dato(1L,"valor"))));
    }
    @Test
    public void testUpdateShouldReturnDato() throws Exception {
        testAddShouldReturnDato();
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/dato/1")
                                .content(asJsonString(new Dato(0L,"valor1")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(new Dato(1L,"valor1"))));
    }
    @Test
    public void testRemoveByIDShouldReturnDato() throws Exception {
        testAddShouldReturnDato();
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/dato/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(new Dato(1L,"valor"))));
    }
}
