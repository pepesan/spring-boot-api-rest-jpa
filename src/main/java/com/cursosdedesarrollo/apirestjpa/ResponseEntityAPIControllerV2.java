package com.cursosdedesarrollo.apirestjpa;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/dato/responsev2/")
@Tag(name = "response-entity-controller-advice", description = "the ResponseEntity ControllerAdvice API")
public class ResponseEntityAPIControllerV2 {

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
    @ResponseStatus
    public ResponseEntity<Dato> showDatoById(@PathVariable("id") Long id){
        Dato d = this.listado.stream().filter(dato -> dato.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
        System.out.println(d);
        return ResponseEntity.ok(d);

    }


    @PutMapping(value = "/{id}")
    @ResponseStatus
    public ResponseEntity<Dato> editDatoById(
            @PathVariable("id") Long id,
            @RequestBody Dato dato) {
        Dato d = this.listado.stream().filter(elemento -> elemento.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
        int index = this.listado.indexOf(d);
        dato.setId(id);
        this.listado.set(index, dato);

        return ResponseEntity.ok(dato);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus
    public ResponseEntity<Dato> deleteDatoById(@PathVariable Long id){
        Dato d = this.listado.stream().filter(elemento -> elemento.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
        this.listado.remove(d);
        return ResponseEntity.ok(d);

    }
}
