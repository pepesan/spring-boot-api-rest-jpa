package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import com.cursosdedesarrollo.apirestjpa.repositories.AlumnoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoController {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping
    public ResponseEntity<Iterable<Alumno>> index(){
        return ResponseEntity.ok(this.alumnoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Alumno> add(@RequestBody @Valid Alumno alumno){
        this.alumnoRepository.save(alumno);
        return ResponseEntity.ok(alumno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Alumno>> showById(@PathVariable("id") Long id){
        Optional<Alumno> alumno = this.alumnoRepository.findById(id);
        if (alumno.isPresent()){
            return ResponseEntity.ok(alumno);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateById(
            @PathVariable("id") Long id,
            @RequestBody Alumno alumno
    ){
        Optional<Alumno> alumnoOptional = this.alumnoRepository.findById(id);
        Alumno alumnoBBDD = null;
        if (alumnoOptional.isPresent()){
            alumnoBBDD = alumnoOptional.get();
            alumnoBBDD.setNombre(alumno.getNombre());
            alumnoBBDD.setApellidos(alumno.getApellidos());
            alumnoBBDD.setEdad(alumno.getEdad());
            this.alumnoRepository.save(alumnoBBDD);
            return ResponseEntity.ok(alumnoBBDD);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Alumno> deleteById(@PathVariable("id") Long id){
        Optional<Alumno> alumnoOptional = this.alumnoRepository.findById(id);
        return alumnoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
