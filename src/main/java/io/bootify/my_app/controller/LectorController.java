package io.bootify.my_app.controller;

import io.bootify.my_app.form.LectorForm;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import io.bootify.my_app.domain.Lector;
import io.bootify.my_app.services.LectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/lectores")
public class LectorController {

    private final LectorService lectorService;

    public LectorController(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @GetMapping
    public ResponseEntity<List<Lector>> getAllLectores() {
        List<Lector> lectores = lectorService.obtenerTodosLosLectores();
        return new ResponseEntity<>(lectores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lector> getLectorById(@PathVariable Integer id) {
        Lector lector = lectorService.obtenerLectorPorId(id).orElse(null);
        return new ResponseEntity<>(lector, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lector> createLector(@RequestBody LectorForm lector) {
        Lector createdLector = lectorService.crearNuevoLector(lector);
        return new ResponseEntity<>(createdLector, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lector> updateLector(@PathVariable Integer id, @RequestBody LectorForm lector) {
        Lector updatedLector = lectorService.updateLector(id, lector);
        return new ResponseEntity<>(updatedLector, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLector(@PathVariable Integer id) {
        lectorService.deleteLector(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

