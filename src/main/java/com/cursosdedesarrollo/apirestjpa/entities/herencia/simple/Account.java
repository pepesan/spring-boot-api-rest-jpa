package com.cursosdedesarrollo.apirestjpa.entities.herencia.simple;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@MappedSuperclass
@Data
@NoArgsConstructor
public class Account {
    @Id
    private Long id;
    private String owner;
    private BigDecimal balance;
    private BigDecimal interestRate;
}
