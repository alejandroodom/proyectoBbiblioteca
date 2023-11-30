package io.bootify.my_app.controller;

import io.bootify.my_app.form.LibroForm;

import io.bootify.my_app.domain.Libro;
import io.bootify.my_app.services.LibroService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Integer id) {
        Libro libro = libroService.obtenerLibroPorId(id).orElse(null);
        return new ResponseEntity<>(libro, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody LibroForm libro) {
        Libro createdLibro = libroService.crearNuevoLibro(libro);
        return new ResponseEntity<>(createdLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Integer id, @RequestBody LibroForm libro) {
        Libro updatedLibro = libroService.updateLibro(id, libro);
        return new ResponseEntity<>(updatedLibro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Integer id) {
        libroService.eliminarLibro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


