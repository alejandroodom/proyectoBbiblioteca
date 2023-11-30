package io.bootify.my_app.services;

import io.bootify.my_app.form.PrestamoForm;
import io.bootify.my_app.domain.Bibliotecario;
import io.bootify.my_app.domain.Lector;
import io.bootify.my_app.domain.Libro;
import io.bootify.my_app.domain.Prestamo;
import io.bootify.my_app.repos.BibliotecarioRepository;
import io.bootify.my_app.repos.LectorRepository;
import io.bootify.my_app.repos.LibroRepository;
import io.bootify.my_app.repos.PrestamoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LectorRepository lectorRepository;
    private final LibroRepository libroRepository;
    private final BibliotecarioRepository bibliotecarioRepository;
    private final LibroService libroService;
    private final LectorService lectorService;

    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository,
                           LectorRepository lectorRepository,
                           LibroRepository libroRepository,
                           BibliotecarioRepository bibliotecarioRepository, LibroService libroService, LectorService lectorService) {
        this.prestamoRepository = prestamoRepository;
        this.lectorRepository = lectorRepository;
        this.libroRepository = libroRepository;
        this.bibliotecarioRepository = bibliotecarioRepository;
        this.libroService = libroService;
        this.lectorService = lectorService;
    }

    public void crearNuevoPrestamo(PrestamoForm prestamoForm) {
        Prestamo nuevoPrestamo = new Prestamo();

        // Obtener el lector, el libro y el bibliotecario por sus IDs
        Lector lector = lectorRepository.findById(prestamoForm.getIdLector()).orElseThrow(() -> new EntityNotFoundException("Lector no encontrado"));
        Libro libro = libroRepository.findById(prestamoForm.getIdLibro()).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));
        Bibliotecario bibliotecario = bibliotecarioRepository.findById(prestamoForm.getIdBibliotecario()).orElseThrow(() -> new EntityNotFoundException("Bibliotecario no encontrado"));


        // Configurar los datos del préstamo
        nuevoPrestamo.setLector(lector);
        nuevoPrestamo.setLibro(libro);
        nuevoPrestamo.setBibliotecario(bibliotecario);
        nuevoPrestamo.setFechaInicio(LocalDate.now());
        //nuevoPrestamo.setFechaDevolucion(LocalDate.now().plusDays());
        nuevoPrestamo.setRenovado(false);

        // Guardar el nuevo préstamo en la base de datos
        prestamoRepository.save(nuevoPrestamo);
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    public void devolverLibro(PrestamoForm prestamoForm) {
        Integer idPrestamo = prestamoForm.getIdPrestamo();
        Optional<Prestamo> prestamoOptional = prestamoRepository.findById(idPrestamo);

        prestamoOptional.ifPresent(prestamo -> {
            // Realizar la lógica para manejar la devolución del libro
            if (!prestamo.getFechaDevolucion().isAfter(LocalDate.now())) {
                prestamo.setFechaDevolucion(LocalDate.now());
                prestamo.setRenovado(false); // Restablecer la bandera de renovación si es necesario
                // Otras operaciones relacionadas con la devolución del libro
                prestamoRepository.save(prestamo);
            } else {
                // Manejar el caso en el que el libro ya ha sido devuelto
                throw new RuntimeException("El libro ya ha sido devuelto.");
            }
        });
    }

    public List<Prestamo> obtenerPrestamosPorLector(Integer idLector) {
        // Obtén la lista de préstamos para un lector específico
        //return prestamoRepository.findByLectorId(idLector);
        return null;
    }

    public List<Prestamo> getPrestamos() {
        // Obtén la lista completa de préstamos
        return prestamoRepository.findAll();
    }

    public Prestamo solicitarLibro(PrestamoForm prestamoForm) {
        // Obtener el libro por su ID
        Optional<Libro> libroOptional = libroService.obtenerLibroPorId(prestamoForm.getIdLibro());

        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();

            // Verificar si hay suficientes copias disponibles del libro
            if (libro.getCantidadDisponible() > 0 && !libro.getDanado()) {
                // Obtener el lector por su ID
                Optional<Lector> lectorOptional = lectorService.obtenerLectorPorId(prestamoForm.getIdLector());

                if (lectorOptional.isPresent()) {
                    int periodoPrestamoDias = 15;
                    LocalDate fechaInicio = LocalDate.now();
                    LocalDate fechaDevolucion = fechaInicio.plusDays(periodoPrestamoDias);

                    Lector lector = lectorOptional.get();

                    // Crear un nuevo préstamo
                    Prestamo nuevoPrestamo = new Prestamo();
                    nuevoPrestamo.setFechaInicio(LocalDate.now());
                    nuevoPrestamo.setFechaDevolucion(fechaDevolucion);
                    nuevoPrestamo.setRenovado(false);
                    nuevoPrestamo.setLector(lector);
                    nuevoPrestamo.setLibro(libro);

                    // Guardar el nuevo préstamo en la base de datos
                    prestamoRepository.save(nuevoPrestamo);

                    // Actualizar la cantidad disponible del libro
                    libroService.actualizarCantidadDisponible(libro, libro.getCantidadDisponible() - 1);
                } else {
                    // Manejar el caso en el que no se encuentra el lector
                    // Puedes lanzar una excepción, enviar un mensaje de error, etc.
                }
            } else {
                // Manejar el caso en el que no hay copias disponibles del libro
                // Puedes lanzar una excepción, enviar un mensaje de error, etc.
            }
        } else {
            // Manejar el caso en el que no se encuentra el libro
            // Puedes lanzar una excepción, enviar un mensaje de error, etc.
        }
        return null;
    }

    public Optional<Prestamo> obtenerPrestamoPorId(Integer id) {
        // Utilizar el repositorio para obtener el préstamo por su ID
        return prestamoRepository.findById(id);
    }

    public void deletePrestamo(Integer id) {
        Optional<Prestamo> prestamoOptional = prestamoRepository.findById(id);

        if (prestamoOptional.isPresent()) {
            prestamoRepository.deleteById(id);
        } else {
        }
    }

    public Prestamo updatePrestamo(Integer id, PrestamoForm prestamo) {
        Optional<Prestamo> existingPrestamoOptional = prestamoRepository.findById(id);

        if (existingPrestamoOptional.isPresent()) {
            Prestamo existingPrestamo = existingPrestamoOptional.get();

            existingPrestamo.setIdPrestamo(prestamo.getIdPrestamo());



            prestamoRepository.save(existingPrestamo);
        } else {

        }
        return null;
    }
}


