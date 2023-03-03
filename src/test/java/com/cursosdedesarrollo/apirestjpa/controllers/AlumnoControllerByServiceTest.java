package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import com.cursosdedesarrollo.apirestjpa.repositories.AlumnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AlumnoControllerByServiceTest {
    @Autowired
    private MockMvc mockMvc;

    private String baseURL = "/api/alumno-service";

    private static Long lastID;


    @BeforeAll
    public static void init (){
        lastID = 0L;
    }

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("lastID:" + lastID);
        testDeteleAll();
    }

    @Test
    @DisplayName("test alumno index list")
    public void testIndex() throws Exception {
        mockMvc.perform(get(baseURL)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("test delete all alumno data")
    public void testDeteleAll() throws Exception {
        mockMvc.perform(delete(baseURL)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("add alumno")
    public void testAdd() throws Exception {
        Alumno alumno = new Alumno();
        alumno.setNombre("David");
        alumno.setApellidos("Vaquero");
        alumno.setEdad(44);
        Alumno alumnoSalida = new Alumno();
        alumnoSalida.setNombre("David");
        alumnoSalida.setApellidos("Vaquero");
        alumnoSalida.setEdad(44);
        lastID++;
        alumnoSalida.setId(lastID);
        mockMvc.perform(post(baseURL)
                        .contentType("application/json")
                        .content(asJsonString(alumno))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(asJsonString(alumnoSalida)));
    }

    @Test
    @DisplayName("test show alumno by id")
    public void testShow() throws Exception {
        testAdd();
        Alumno alumnoSalida = new Alumno();
        alumnoSalida.setNombre("David");
        alumnoSalida.setApellidos("Vaquero");
        alumnoSalida.setEdad(44);
        alumnoSalida.setId(lastID);
        mockMvc.perform(get(baseURL+"/"+ lastID)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(asJsonString(alumnoSalida)));
    }

    @Test
    @DisplayName("test update alumno by id")
    public void testUpdate() throws Exception {
        testAdd();
        Alumno alumno = new Alumno();
        alumno.setNombre("David2");
        alumno.setApellidos("Vaquero2");
        alumno.setEdad(45);
        Alumno alumnoSalida = new Alumno();
        alumnoSalida.setNombre("David2");
        alumnoSalida.setApellidos("Vaquero2");
        alumnoSalida.setEdad(45);
        alumnoSalida.setId(lastID);
        mockMvc.perform(put(baseURL+"/"+ lastID)
                        .contentType("application/json")
                        .content(asJsonString(alumno)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(asJsonString(alumnoSalida)));
    }


    @Test
    @DisplayName("test delete alumno by id")
    public void testDelete() throws Exception {
        testAdd();
        Alumno alumnoSalida = new Alumno();
        alumnoSalida.setNombre("David");
        alumnoSalida.setApellidos("Vaquero");
        alumnoSalida.setEdad(44);
        alumnoSalida.setId(lastID);
        mockMvc.perform(delete(baseURL+"/"+ lastID)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(asJsonString(alumnoSalida)));
    }



}
