package com.cursosdedesarrollo.apirestjpa.repositories;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    @Query("SELECT a FROM Alumnos a WHERE a.edad = 1")
    public List<Alumno> findAlumnoByEdad(Integer edad);

    @Query("from Alumnos ORDER BY id DESC")
    public List<Alumno> findMaxId();

}
