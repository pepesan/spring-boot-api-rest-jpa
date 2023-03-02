package com.cursosdedesarrollo.apirestjpa.repositories;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
