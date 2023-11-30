package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}
