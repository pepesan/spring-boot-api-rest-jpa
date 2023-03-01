package com.cursosdedesarrollo.apirestjpa.advice;

import com.cursosdedesarrollo.apirestjpa.dto.ErrorMessage;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                200,
                new Date(),
                ex.getMessage(),
                "Error capturado por ResourceNotFoundEcxeption");

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}

