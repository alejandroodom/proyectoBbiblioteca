package io.bootify.my_app.services;

import io.bootify.my_app.form.BibliotecarioForm;
import io.bootify.my_app.domain.Bibliotecario;
import io.bootify.my_app.domain.Usuario;
import io.bootify.my_app.form.UsuarioForm;
import io.bootify.my_app.repos.BibliotecarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecarioService {

    private final BibliotecarioRepository bibliotecarioRepository;

    @Autowired
    public BibliotecarioService(BibliotecarioRepository bibliotecarioRepository) {
        this.bibliotecarioRepository = bibliotecarioRepository;
    }

    public List<Bibliotecario> obtenerTodosLosBibliotecarios() {
        return bibliotecarioRepository.findAll();
    }

    public Optional<Bibliotecario> obtenerBibliotecarioPorId(Integer id) {
        return bibliotecarioRepository.findById(id);
    }

    public Bibliotecario crearNuevoBibliotecario(BibliotecarioForm bibliotecarioForm) {
        Bibliotecario nuevoBibliotecario = new Bibliotecario();
        nuevoBibliotecario.setFechaContratacion(bibliotecarioForm.getFechaContratacion());
        bibliotecarioRepository.save(nuevoBibliotecario);
        return nuevoBibliotecario;
    }

    public void eliminarBibliotecarioPorId(Integer id) {
        bibliotecarioRepository.deleteById(id);
    }

    public void agregarBibliotecario(BibliotecarioForm bibliotecarioForm) {
        // Crea una instancia de Bibliotecario a partir de BibliotecarioForm
        Bibliotecario nuevoBibliotecario = new Bibliotecario();
        nuevoBibliotecario.setFechaContratacion(bibliotecarioForm.getFechaContratacion());
        UsuarioService usuarioService = new UsuarioService();
        UsuarioForm usuarioForm = new UsuarioForm();

        // Crea un nuevo Usuario asociado al bibliotecario
        Usuario nuevoUsuario = usuarioService.crearNuevoUsuario(usuarioForm); // Asegúrate de tener este método en tu UsuarioService
        nuevoBibliotecario.setUsuario(nuevoUsuario);

        // Guarda el nuevo bibliotecario en la base de datos
        bibliotecarioRepository.save(nuevoBibliotecario);
    }

    public void eliminarBibliotecario(Integer idBibliotecario) {
        // Verifica si el bibliotecario existe en la base de datos
        Optional<Bibliotecario> bibliotecarioOptional = bibliotecarioRepository.findById(idBibliotecario);

        bibliotecarioOptional.ifPresent(bibliotecario -> {
            // Elimina el bibliotecario de la base de datos
            bibliotecarioRepository.delete(bibliotecario);
        });
    }

    public Bibliotecario updateBibliotecario(Integer id, Bibliotecario bibliotecario) {
        Optional<Bibliotecario> existingBibliotecarioOptional = bibliotecarioRepository.findById(id);

        if (existingBibliotecarioOptional.isPresent()) {
            Bibliotecario existingBibliotecario = existingBibliotecarioOptional.get();
            existingBibliotecario.setIdBibliotecario(bibliotecario.getIdBibliotecario());


            return bibliotecarioRepository.save(existingBibliotecario);
        } else {
            return null;
        }
    }

}

