package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.dto.Customer;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateById(
            @PathVariable("id") Long id,
            @RequestBody Customer customer
    ){
        Customer c= this.listado.stream().filter(dato -> dato.getCustomerId().equals(id)).findFirst().orElse(null);
        if (c!=null){
            int index = this.listado.indexOf(c);
            customer.setCustomerId(id);
            Link link = linkTo(CustomerApiController.class).slash(id).withSelfRel();
            customer.add(link);
            this.listado.set(index, customer);
            return ResponseEntity.ok(customer);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteById(@PathVariable Long id){
        Customer customer = this.listado.stream().filter(elemento -> elemento.getCustomerId().equals(id)).findFirst().orElse(null);
        if (customer !=null){
            this.listado.remove(customer);
            return ResponseEntity.ok(customer);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
