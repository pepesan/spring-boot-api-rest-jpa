package com.cursosdedesarrollo.apirestjpa.entities.herencia.simple;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "DebitAccount")
@Data
@NoArgsConstructor
public class DebitAccount extends Account {
    private BigDecimal overdraftFee;
}
