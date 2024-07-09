package com.cursosdedesarrollo.apirestjpa.entities.herencia.simple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitAccountRepository extends JpaRepository<DebitAccount, Long> {
}
