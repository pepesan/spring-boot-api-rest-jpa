package com.cursosdedesarrollo.apirestjpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
public class ResponseEntityApiControllerTest {

    private static final String BASEURL = "/api/dato/response/";
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    public void clearRestData() throws Exception {
        System.out.println("limpiando");
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(BASEURL+"clear")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testListShouldReturnOkResult() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(BASEURL)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testListShouldReturnEmptyArray() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(BASEURL)
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
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(BASEURL)
                                .content(asJsonString(new Dato(0L,"valor")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(new Dato(1L,"valor"))));
    }

    @Test
    public void testGetByIDShouldReturnDato() throws Exception {
        testAddShouldReturnDato();
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(BASEURL+"1")
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
                                .put(BASEURL+"1")
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
                                .delete(BASEURL+"1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(new Dato(1L,"valor"))));
    }
}
