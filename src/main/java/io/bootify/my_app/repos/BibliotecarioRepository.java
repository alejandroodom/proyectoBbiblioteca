package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Bibliotecario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Integer> {
}
