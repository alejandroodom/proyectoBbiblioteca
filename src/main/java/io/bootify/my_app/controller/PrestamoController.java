package io.bootify.my_app.controller;


import io.bootify.my_app.form.PrestamoForm;
import io.bootify.my_app.domain.Prestamo;
import io.bootify.my_app.services.PrestamoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> getAllPrestamos() {
        List<Prestamo> prestamos = prestamoService.getPrestamos();
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Integer id) {
        Prestamo prestamo = prestamoService.obtenerPrestamoPorId(id).orElse(null);
        return new ResponseEntity<>(prestamo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody PrestamoForm prestamo) {
        Prestamo createdPrestamo = prestamoService.solicitarLibro(prestamo);
        return new ResponseEntity<>(createdPrestamo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Integer id, @RequestBody PrestamoForm prestamo) {
        Prestamo updatedPrestamo = prestamoService.updatePrestamo(id, prestamo);
        return new ResponseEntity<>(updatedPrestamo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable Integer id) {
        prestamoService.deletePrestamo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
