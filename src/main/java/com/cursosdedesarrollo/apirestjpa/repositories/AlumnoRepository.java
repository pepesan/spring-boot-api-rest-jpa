package com.cursosdedesarrollo.apirestjpa.repositories;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
