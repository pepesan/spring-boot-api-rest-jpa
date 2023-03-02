package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import com.cursosdedesarrollo.apirestjpa.repositories.AlumnoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/alumno")
@Slf4j
public class AlumnoController {
    @Autowired
    private AlumnoRepository alumnoRepository;

    private Long lastID = 0L;

    @GetMapping
    public ResponseEntity<Iterable<Alumno>> index(){
        log.info("index");
        return ResponseEntity.ok(this.alumnoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Alumno> add(@RequestBody @Valid Alumno alumno){
        this.alumnoRepository.save(alumno);
        return ResponseEntity.ok(alumno);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAll(){
        log.info("limpiando");
        this.alumnoRepository.deleteAll();
        return ResponseEntity.ok(true);
    }

    @GetMapping("/edad/{edad}")
    public List<Alumno> getByEdad(@PathVariable("edad") Integer edad){
        return this.alumnoRepository.findAlumnoByEdad(edad);
    }

    @GetMapping("/lastid")
    public Long getLastid(){
        List<Alumno> list = this.alumnoRepository.findMaxId();

        Optional<Alumno> alumno = list.stream().findFirst();
        if (alumno.isPresent()){
            log.info("lastID:" + alumno.get().getId());
            lastID = alumno.get().getId();
            return alumno.get().getId();
        }else {
            return lastID;
        }
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
        return alumnoOptional
                .map(alumno ->{
                    this.alumnoRepository.deleteById(id);
                    return ResponseEntity.ok(alumno);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
