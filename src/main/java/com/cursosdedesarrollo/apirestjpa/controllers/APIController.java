package com.cursosdedesarrollo.apirestjpa.controllers;

import com.cursosdedesarrollo.apirestjpa.dto.Dato;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/dato")
@Tag(name = "dato", description = "the dato simple API")
public class APIController {

    public List<Dato> listado = new LinkedList<>();
    public Long lastID = 0L;
    @GetMapping
    @Operation(summary = "show list of dato objects", description = "Shows a list of dato in an output array", tags = { "dato" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Dato.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = Dato.class)) }),
            @ApiResponse(description = "successful operation")

    })
    public List<Dato> index(){
        return this.listado;
    }

    @Operation(summary = "Create dato", description = "Create a dato object", tags = { "dato" })
    @ApiResponses(
            value = {
                @ApiResponse(
                        description = "successful operation",
                        content = {
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Dato.class)
                                ),
                                @Content(mediaType = "application/xml",
                                        schema = @Schema(implementation = Dato.class))
                        })
            })
    @PostMapping(consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" })
    public Dato addDato(
            @Parameter(description = "Created user object") @Valid @RequestBody Dato dato) {
        lastID++;
        dato.setId(lastID);
        this.listado.add(dato);
        return dato;
    }

    @DeleteMapping()
    @Operation(summary = "clear all dato objects", description = "Delete the list of dato objects", tags = { "dato" })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Long.class)),
                            @Content(mediaType = "application/xml",
                                    schema = @Schema(implementation = Long.class))
                    }),
            @ApiResponse(description = "successful operation")

    })
    public Long clearData(){
        this.listado = new ArrayList<>();
        this.lastID = 0L;
        return this.lastID;
    }

    @Operation(summary = "Show dato", description = "Shows a  dato object by ID", tags = { "dato" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Dato not found"),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Dato.class)),
                            @Content(mediaType = "application/xml",
                                    schema = @Schema(implementation = Dato.class))
                    }),
    })
    @GetMapping("/{id}")
    public Dato showDatoById(@Parameter(description = "The Id of the Dato", required = true) @PathVariable("id") Long id){
        Dato d = this.listado.stream().filter(dato -> dato.getId().equals(id)).findFirst().orElse(null);
        System.out.println(d);
        return d;
    }

    @Operation(summary = "Update dato", description = "Update a dato object by ID", tags = { "dato" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Dato not found"),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Dato.class)),
                            @Content(mediaType = "application/xml",
                                    schema = @Schema(implementation = Dato.class))
                    }),
    })
    @PutMapping(value = "/{id}")
    public Dato editDatoById(
            @Parameter(description = "The Id of the Dato", required = true) @PathVariable("id") Long id,
            @Parameter(description = "updated user object data") @Valid @RequestBody Dato dato) {
        Dato d = this.listado.stream().filter(elemento -> elemento.getId().equals(id)).findFirst().orElse(null);
        if (dato!=null){
            int index = this.listado.indexOf(d);
            dato.setId(id);
            this.listado.set(index, dato);
            return dato;
        }else{
            return new Dato();
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete dato", description = "Delete a dato object by ID", tags = { "dato" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Dato not found"),
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Dato.class)),
                            @Content(mediaType = "application/xml",
                                    schema = @Schema(implementation = Dato.class))
                    }),
    })
    public Dato deleteDatoById(
            @Parameter(description = "The Id of the Dato", required = true) @PathVariable Long id){
        Dato d = this.listado.stream().filter(elemento -> elemento.getId().equals(id)).findFirst().orElse(null);
        if (d !=null){
            this.listado.remove(d);
            return d;
        }else{
            return new Dato();
        }
    }
}
