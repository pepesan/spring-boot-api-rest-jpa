package com.cursosdedesarrollo.apirestjpa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Dato API", version = "2.0", description = "Dato API Demo"))
public class ApiRestJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestJpaApplication.class, args);
	}

}
