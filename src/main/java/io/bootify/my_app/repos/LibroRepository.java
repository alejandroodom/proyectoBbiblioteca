package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LibroRepository extends JpaRepository<Libro, Integer> {
}
