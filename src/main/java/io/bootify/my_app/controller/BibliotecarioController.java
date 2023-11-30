package io.bootify.my_app.controller;


import io.bootify.my_app.form.BibliotecarioForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.bootify.my_app.domain.Bibliotecario;
import io.bootify.my_app.services.BibliotecarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/bibliotecarios")
public class BibliotecarioController {

    private final BibliotecarioService bibliotecarioService;

    public BibliotecarioController(BibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    @GetMapping
    public ResponseEntity<List<Bibliotecario>> getAllBibliotecarios() {
        List<Bibliotecario> bibliotecarios = bibliotecarioService.obtenerTodosLosBibliotecarios();
        return new ResponseEntity<>(bibliotecarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bibliotecario> getBibliotecarioById(@PathVariable Integer id) {
        Bibliotecario bibliotecario = bibliotecarioService.obtenerBibliotecarioPorId(id).orElse(null);
        return new ResponseEntity<>(bibliotecario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bibliotecario> createBibliotecario(@RequestBody BibliotecarioForm bibliotecario) {
        Bibliotecario createdBibliotecario = bibliotecarioService.crearNuevoBibliotecario(bibliotecario);
        return new ResponseEntity<>(createdBibliotecario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bibliotecario> updateBibliotecario(@PathVariable Integer id, @RequestBody Bibliotecario bibliotecario) {
        Bibliotecario updatedBibliotecario = bibliotecarioService.updateBibliotecario(id, bibliotecario);
        return new ResponseEntity<>(updatedBibliotecario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBibliotecario(@PathVariable Integer id) {
        bibliotecarioService.eliminarBibliotecarioPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


