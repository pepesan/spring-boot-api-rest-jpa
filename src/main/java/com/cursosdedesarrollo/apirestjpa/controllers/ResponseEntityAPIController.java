package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.dto.Dato;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/dato/response/")
@Tag(name = "response-entity", description = "the ResponseEntity API")
public class ResponseEntityAPIController {

    public List<Dato> listado = new LinkedList<>();
    public Long lastID = 0L;
    @GetMapping
    public ResponseEntity<List<Dato>> index(){
        return ResponseEntity.ok(this.listado);
    }
    @PostMapping
    public ResponseEntity<Dato> addDato(@RequestBody Dato dato) {
        lastID++;
        dato.setId(lastID);
        this.listado.add(dato);
        return ResponseEntity.ok(dato);
    }

    @GetMapping("/clear")
    public ResponseEntity<Long> clearData(){
        this.listado = new ArrayList<>();
        this.lastID = 0L;
        return ResponseEntity.ok(this.lastID);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dato> showDatoById(@PathVariable("id") Long id){
        Dato d = this.listado.stream().filter(dato -> dato.getId().equals(id)).findFirst().orElse(null);
        System.out.println(d);
        ResponseEntity<Dato> datoResponseEntity = null;
        if (d!=null){
            datoResponseEntity = ResponseEntity.ok(d);
        }else{
            datoResponseEntity = ResponseEntity.notFound().build();
        }
        return datoResponseEntity;

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Dato> editDatoById(
            @PathVariable("id") Long id,
            @RequestBody Dato dato) {
        Dato d = this.listado.stream().filter(elemento -> elemento.getId().equals(id)).findFirst().orElse(null);
        if (dato!=null){
            int index = this.listado.indexOf(d);
            dato.setId(id);
            this.listado.set(index, dato);
            return ResponseEntity.ok(dato);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Dato> deleteDatoById(@PathVariable Long id){
        Dato d = this.listado.stream().filter(elemento -> elemento.getId().equals(id)).findFirst().orElse(null);
        if (d !=null){
            this.listado.remove(d);
            return ResponseEntity.ok(d);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
