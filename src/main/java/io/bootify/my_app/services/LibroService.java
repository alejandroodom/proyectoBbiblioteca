package io.bootify.my_app.services;

import io.bootify.my_app.domain.Prestamo;
import io.bootify.my_app.form.LibroForm;
import io.bootify.my_app.domain.Libro;
import io.bootify.my_app.repos.LibroRepository;
import io.bootify.my_app.repos.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final PrestamoRepository prestamoRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository, PrestamoRepository prestamoRepository) {
        this.libroRepository = libroRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibroPorId(Integer id) {
        return libroRepository.findById(id);
    }

    public Libro crearNuevoLibro(LibroForm libroForm) {
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(libroForm.getTitulo());
        nuevoLibro.setAutor(libroForm.getAutor());
        nuevoLibro.setAOPublicacion(libroForm.getAnoPublicacion());
        nuevoLibro.setCantidadDisponible(libroForm.getCantidadDisponible());
        nuevoLibro.setDanado(libroForm.getDanado());
        libroRepository.save(nuevoLibro);
        return nuevoLibro;
    }



    public void eliminarLibro(Integer idLibro) {
        Optional<Libro> libroOptional = libroRepository.findById(idLibro);

        libroOptional.ifPresent(libro -> {
            libroRepository.delete(libro);
        });
    }



    public void actualizarCantidadDisponible(Libro libro, int nuevaCantidadDisponible) {
        // Actualizar la cantidad disponible del libro
        libro.setCantidadDisponible(nuevaCantidadDisponible);

        // Guardar el libro actualizado en la base de datos
        libroRepository.save(libro);
    }
    public Libro updateLibro(Integer id, LibroForm updatedLibro) {
        Libro existingLibro = libroRepository.findById(id).orElse(null);

        // Actualizar los campos del libro existente con los valores proporcionados
        existingLibro.setTitulo(updatedLibro.getTitulo());
        existingLibro.setAutor(updatedLibro.getAutor());
        existingLibro.setAOPublicacion(updatedLibro.getAnoPublicacion());
        existingLibro.setCantidadDisponible(updatedLibro.getCantidadDisponible());
        existingLibro.setDanado(updatedLibro.getDanado());

        // Guardar el libro actualizado
        libroRepository.save(existingLibro);
        return existingLibro;
    }
}

