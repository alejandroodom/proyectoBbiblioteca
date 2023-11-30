package io.bootify.my_app.services;

import io.bootify.my_app.form.LectorForm;
import io.bootify.my_app.domain.Lector;
import io.bootify.my_app.repos.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectorService {

    private final LectorRepository lectorRepository;

    @Autowired
    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public List<Lector> obtenerTodosLosLectores() {
        return lectorRepository.findAll();
    }

    public Optional<Lector> obtenerLectorPorId(Integer id) {
        return lectorRepository.findById(id);
    }

    public Lector crearNuevoLector(LectorForm lectorForm) {
        Lector nuevoLector = new Lector();
        nuevoLector.setDireccion(lectorForm.getDireccion());
        nuevoLector.setTelefono(lectorForm.getTelefono());
        nuevoLector.setLibrosPrestados(lectorForm.getLibrosPrestados());
        lectorRepository.save(nuevoLector);
        return nuevoLector;
    }

    public void eliminarLectorPorId(Integer id) {
        lectorRepository.deleteById(id);
    }

    public Lector updateLector(Integer id, LectorForm lector) {
        Optional<Lector> existingLectorOptional = lectorRepository.findById(id);

        if (existingLectorOptional.isPresent()) {
            Lector existingLector = existingLectorOptional.get();
            // Actualizar los campos del lector con los valores proporcionados
            existingLector.setIdLector(lector.getIdLector());
            // Repite esto para todos los campos que necesitas actualizar

            // Guardar el lector actualizado
            lectorRepository.save(existingLector);
        } else {
        }
        return null;
    }

    public void deleteLector(Integer id) {
        if (lectorRepository.existsById(id)) {
            lectorRepository.deleteById(id);
        } else {

        }
    }

}
