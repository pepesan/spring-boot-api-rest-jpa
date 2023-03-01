package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.dto.Customer;
import com.cursosdedesarrollo.apirestjpa.dto.Dato;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

    private List<Customer> listado = new ArrayList<>();
    private Long lastID = 0L;
    @GetMapping
    public ResponseEntity<List<Customer>> index(){
        return ResponseEntity.ok(this.listado);
    }

    @PostMapping
    public ResponseEntity<Customer> add(@RequestBody Customer customer){
        lastID++;
        customer.setCustomerId(lastID);
        this.listado.add(customer);
        Link link = linkTo(CustomerApiController.class).slash(lastID).withSelfRel();
        customer.add(link);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> showById(@PathVariable("id") Long id){
        Customer customer = this.listado.stream().filter(dato -> dato.getCustomerId().equals(id)).findFirst().orElse(null);
        return ResponseEntity.ok(customer);
    }
}
