package com.cursosdedesarrollo.apirestjpa.services;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import com.cursosdedesarrollo.apirestjpa.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Alumno> findAll() {
        Iterable<Alumno> iterable  = this.alumnoRepository.findAll();
        List<Alumno> list = new LinkedList<>();
        iterable.forEach(list::add);
        return list;
    }

    @Override
    public Alumno add(Alumno alumno) {
        this.alumnoRepository.save(alumno);
        return alumno;
    }

    @Override
    public Alumno getById(Long id) {
        Optional<Alumno>  alumnoOptional = this.alumnoRepository.findById(id);
        return alumnoOptional.orElse(null);
    }

    @Override
    public Alumno updateById(Long id, Alumno alumno) {
        Alumno  alumnoBBDD = this.alumnoRepository.findById(id).orElse(null);
        if (alumnoBBDD != null){
            alumnoBBDD.setNombre(alumno.getNombre());
            alumnoBBDD.setApellidos(alumno.getApellidos());
            alumnoBBDD.setEdad(alumno.getEdad());
            this.alumnoRepository.save(alumnoBBDD);
        }
        return alumnoBBDD;
    }

    @Override
    public Alumno deleteById(Long id) {
        Optional<Alumno>  alumnoOptional = this.alumnoRepository.findById(id);
        if (alumnoOptional.isPresent()){
            this.alumnoRepository.deleteById(id);
        }
        return alumnoOptional.orElse(null);
    }
}
