package io.bootify.my_app.services;

import io.bootify.my_app.domain.Bibliotecario;
import io.bootify.my_app.domain.Libro;
import io.bootify.my_app.form.BibliotecarioForm;
import io.bootify.my_app.form.LibroForm;
import io.bootify.my_app.repos.BibliotecarioRepository;
import io.bootify.my_app.repos.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;

    public void agregarLibro(LibroForm libroForm) {
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(libroForm.getTitulo());
        nuevoLibro.setAutor(libroForm.getAutor());
        nuevoLibro.setAOPublicacion(libroForm.getAnoPublicacion());
        nuevoLibro.setCantidadDisponible(libroForm.getCantidadDisponible());
        nuevoLibro.setDanado(libroForm.getDanado());

        libroRepository.save(nuevoLibro);
    }

    public void eliminarLibro(Integer idLibro) {
        libroRepository.deleteById(idLibro);
    }

    public void agregarBibliotecario(BibliotecarioForm bibliotecarioForm) {
        Bibliotecario nuevoBibliotecario = new Bibliotecario();
        nuevoBibliotecario.setFechaContratacion(bibliotecarioForm.getFechaContratacion());

        bibliotecarioRepository.save(nuevoBibliotecario);
    }

    public void eliminarBibliotecario(Integer idBibliotecario) {
        bibliotecarioRepository.deleteById(idBibliotecario);
    }
}


