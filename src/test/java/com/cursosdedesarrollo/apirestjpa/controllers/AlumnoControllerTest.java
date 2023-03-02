package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import com.cursosdedesarrollo.apirestjpa.repositories.AlumnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class AlumnoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlumnoRepository alumnoRepository;

    private String baseURL = "/api/alumno";

    private static Long lastID;

    @BeforeAll
    public static void init (){
        lastID = 0L;
    }

    @BeforeEach
    public void setUp() throws Exception {
        List<Alumno> list = this.alumnoRepository.findMaxId();
        if (list != null && list.size()>0){
            lastID = list.get(0).getId();
            testDeteleAll();
        }
    }

    @Test
    @DisplayName("test alumno index list")
    public void testIndex() throws Exception {
        mockMvc.perform(get(baseURL)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("test alumno lastid")
    public void testLastId() throws Exception {
        mockMvc.perform(get(baseURL+"/lastid")
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
        log.info("lastID: "+lastID);
        if (lastID >= 1){
            lastID++;
        }else{
            lastID = 1L;
        }
        alumnoSalida.setId(lastID);
        mockMvc.perform(post(baseURL)
                        .contentType("application/json")
                        .content(asJsonString(alumno))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(asJsonString(alumnoSalida)));
        lastID = this.alumnoRepository.findMaxId().get(0).getId();
        log.info("add lastId: "+ lastID);
    }

    @Test
    @DisplayName("test show alumno by id")
    public void testShow() throws Exception {
        testAdd();
        Alumno alumnoSalida = new Alumno();
        alumnoSalida.setNombre("David");
        alumnoSalida.setApellidos("Vaquero");
        alumnoSalida.setEdad(44);
        log.info("lastID"+lastID);
        alumnoSalida.setId(lastID);
        mockMvc.perform(get(baseURL+"/"+lastID)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(asJsonString(alumnoSalida)));
    }

    @Test
    @DisplayName("test show alumno by id")
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
        mockMvc.perform(put(baseURL+"/"+lastID)
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
        mockMvc.perform(delete(baseURL+"/"+lastID)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(asJsonString(alumnoSalida)));
    }



}
