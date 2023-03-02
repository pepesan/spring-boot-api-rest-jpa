package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.entities.Alumno;
import com.cursosdedesarrollo.apirestjpa.repositories.AlumnoRepository;
import com.cursosdedesarrollo.apirestjpa.services.AlumnoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAll(){
        this.alumnoService.deteleAll();
        return ResponseEntity.ok(true);
    }


    @GetMapping("/search/{pag}/{size}")
    public ResponseEntity<List<Alumno>> search(
            @PathVariable("pag") @Min(0) Integer pag,
            @PathVariable("size") @Min(1) Integer size
            ){
        Pageable paginacion = PageRequest.of(pag, size);
        return ResponseEntity.ok(this.alumnoService.findAll(paginacion));
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
        Alumno alumnoGuardado = this.alumnoService.updateById(id, alumno);
        if (alumnoGuardado != null){
            return ResponseEntity.ok(alumnoGuardado);
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
