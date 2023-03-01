package com.cursosdedesarrollo.apirestjpa.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class Customer extends RepresentationModel<Customer> {
    private Long customerId;
    private String customerName;
    private String companyName;
}
