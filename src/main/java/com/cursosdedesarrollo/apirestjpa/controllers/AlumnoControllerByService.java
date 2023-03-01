package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import com.cursosdedesarrollo.apirestjpa.repositories.AlumnoRepository;
import com.cursosdedesarrollo.apirestjpa.services.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumno-service")
public class AlumnoControllerByService {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> index(){
        return ResponseEntity.ok(this.alumnoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Alumno> add(@RequestBody @Valid Alumno alumno){
        this.alumnoService.add(alumno);
        return ResponseEntity.ok(alumno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> showById(@PathVariable("id") Long id){
        Alumno alumno = this.alumnoService.getById(id);
        if (alumno != null){
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
        Alumno alumnoBBDD = this.alumnoService.getById(id);
        if (alumnoBBDD != null){
            alumnoBBDD.setNombre(alumno.getNombre());
            alumnoBBDD.setApellidos(alumno.getApellidos());
            alumnoBBDD.setEdad(alumno.getEdad());
            this.alumnoService.updateById(id, alumnoBBDD);
            return ResponseEntity.ok(alumnoBBDD);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Alumno> deleteById(@PathVariable("id") Long id){
        Alumno alumno = this.alumnoService.getById(id);
        if (alumno!= null){
            this.alumnoService.deleteById(id);
            return ResponseEntity.ok(alumno);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
