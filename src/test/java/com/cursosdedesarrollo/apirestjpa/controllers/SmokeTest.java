package com.cursosdedesarrollo.apirestjpa.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.cursosdedesarrollo.apirestjpa.controllers.MiRestController;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Ref: https://spring.io/guides/gs/testing-web/

@SpringBootTest
@Tag("Smoke")
public class SmokeTest {
    @Autowired
    private MiRestController controller;

    @Test
    public void miRestControllerLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
