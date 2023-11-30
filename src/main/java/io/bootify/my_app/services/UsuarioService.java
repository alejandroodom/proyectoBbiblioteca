package io.bootify.my_app.services;

import io.bootify.my_app.form.UsuarioForm;
import io.bootify.my_app.domain.Usuario;
import io.bootify.my_app.repos.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioService() {
        usuarioRepository = null;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario crearNuevoUsuario(UsuarioForm usuarioForm) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuarioForm.getNombre());
        nuevoUsuario.setContraseA(usuarioForm.getContraseA());
        nuevoUsuario.setTipoUsuario(usuarioForm.getTipoUsuario());
        usuarioRepository.save(nuevoUsuario);
        return nuevoUsuario;
    }

    public void eliminarUsuarioPorId(Integer id) {
        usuarioRepository.deleteById(id);
    }


}

