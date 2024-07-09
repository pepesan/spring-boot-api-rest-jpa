package com.cursosdedesarrollo.apirestjpa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Dato API", version = "2.0", description = "Dato API Demo"))
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class ApiRestJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestJpaApplication.class, args);
	}

}
