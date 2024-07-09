package com.cursosdedesarrollo.apirestjpa.entities.audit;

import com.cursosdedesarrollo.apirestjpa.controllers.ResponseEntityAPIController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/itemaudited")
@Slf4j
public class ItemAuditedController {
    @Autowired
    private ItemAuditedRepository itemAuditedRepository;
    @Autowired
    private ResponseEntityAPIController responseEntityAPIController;

    @GetMapping("/")
    public List<ItemAudited> index(){
        return this.itemAuditedRepository.findAll();
    }

    @PostMapping("/")
    public ItemAudited add(
            @RequestBody ItemAudited itemAudited
    ){
        return this.itemAuditedRepository.save(itemAudited);
    }

    @GetMapping("/audits/{id}")
    public String revisionsById(
            @PathVariable("id") Long id
            ){
        Revisions<Integer,ItemAudited> revisions = this.itemAuditedRepository.findRevisions(id);
        Revision<Integer,ItemAudited> revision= revisions.getLatestRevision();
        Optional<Integer> optionalNumber = revision.getRevisionNumber();
        Integer numero = 0;
        if (optionalNumber.isPresent()){
            numero = optionalNumber.get();
        }
        String entidad = revision.getEntity().getName();
        String className = revision.getClass().getName();
        return "Clase: "+ className+", Campo: "+ entidad + ", revisión: "+ numero;
    }
}
