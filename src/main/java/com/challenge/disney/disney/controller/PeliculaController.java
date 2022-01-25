package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class PeliculaController {


    //=== Instancia de Service ===
    private PeliculaService peliculaService;
    public PeliculaController(@Autowired @Lazy PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    //=== Post ===
    @PostMapping
    public ResponseEntity<PeliculaDTO>save(@RequestBody PeliculaDTO pelicula){
        PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<PeliculaDTO>> obtenerPeliculas(){
        List<PeliculaDTO> peliculaDTOList = peliculaService.traerPeliculas();
        return ResponseEntity.status(HttpStatus.OK).body(peliculaDTOList);
    }

    //=== Get ===
    @GetMapping("/basic")
    public ResponseEntity<List<PeliculaBasicDTO>> obtenerPeliculasBasic(){
        List<PeliculaBasicDTO> peliculasBasic = peliculaService.traerPeliculasBasic();
        return ResponseEntity.status(HttpStatus.OK).body(peliculasBasic);
    }

    //=== Delete ===
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //=== Put ===
    @PutMapping("/edit/{id}")
    public ResponseEntity<PeliculaDTO> editarPelicula(@PathVariable Long id, @RequestBody PeliculaDTO edit){
        PeliculaDTO peliculaEditada = peliculaService.editPelicula(id, edit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaEditada);
    }

    //=== Get --- Filters ===
    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> detallesPorFiltros(
            @RequestParam(required = false)String titulo,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Set<Long> genero,
            @RequestParam(required  = false, defaultValue = "ASC") String order
    ){
        List<PeliculaDTO> peliculaDTOList = peliculaService.traerPorFiltros(titulo, genero, order, date);
        return ResponseEntity.status(HttpStatus.OK).body(peliculaDTOList);
    }

}