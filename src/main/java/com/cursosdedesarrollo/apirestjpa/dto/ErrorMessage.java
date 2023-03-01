package com.cursosdedesarrollo.apirestjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private String message;
    private String description;
}
