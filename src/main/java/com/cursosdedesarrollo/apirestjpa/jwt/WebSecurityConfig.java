package com.cursosdedesarrollo.apirestjpa.jwt;

import com.cursosdedesarrollo.apirestjpa.jwt.filters.JwtRequestFilter;
import com.cursosdedesarrollo.apirestjpa.jwt.services.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/*
     References:
     - https://www.javainuse.com/spring/boot-jwt
     - https://github.com/Ozair0/Spring-Boot-3-Auth-JWT-Cookie-JPA
     - https://jskim1991.medium.com/spring-boot-tdd-with-spring-boot-starter-security-jwt-d29e455c08cb
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    private final JwtUserDetailsService jwtUserDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/")
                        .permitAll()
                        .requestMatchers("/api/dato")
                        .permitAll()
                        .requestMatchers("/api/dato/**")
                        .permitAll()
                        .requestMatchers("/api/dato/response/")
                        .permitAll()
                        .requestMatchers("/api/dato/response/**")
                        .permitAll()
                        .requestMatchers("/api/dato/responsev2/")
                        .permitAll()
                        .requestMatchers("/api/dato/responsev2/**")
                        .permitAll()
                        .requestMatchers("/authenticate")
                        .permitAll()
                        .anyRequest()
                        .authenticated())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(jwtUserDetailsService)

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}