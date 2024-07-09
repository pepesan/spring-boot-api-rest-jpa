package com.cursosdedesarrollo.apirestjpa.entities.audit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import java.util.Date;

@Entity
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited //to audit the whole entity
public class ItemAudited {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Audited // to audit specific properties only
    private String name;


}
