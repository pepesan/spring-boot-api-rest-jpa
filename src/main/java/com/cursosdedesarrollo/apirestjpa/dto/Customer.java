package com.cursosdedesarrollo.apirestjpa.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class Customer extends RepresentationModel<Customer> {
    private Long customerId;
    private String customerName;
    private String companyName;

    // standard getters and setters
}
