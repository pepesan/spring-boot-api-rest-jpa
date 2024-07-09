package com.cursosdedesarrollo.apirestjpa.entities.herencia.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyProductRepository extends JpaRepository<MyProduct, Long> {
}
