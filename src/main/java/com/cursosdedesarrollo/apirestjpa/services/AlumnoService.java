package com.cursosdedesarrollo.apirestjpa.services;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlumnoService {
    public List<Alumno> findAll();
    public List<Alumno> findAll(Pageable pageable);
    public Alumno add(Alumno alumno);
    public Alumno getById(Long id);
    public Alumno updateById(Long id, Alumno alumno);
    public Alumno deleteById(Long id);
    public Boolean deteleAll();
}
