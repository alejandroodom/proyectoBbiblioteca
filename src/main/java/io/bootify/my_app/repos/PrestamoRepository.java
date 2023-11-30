package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
