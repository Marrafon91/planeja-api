package io.github.marrafon91.planeja_api.dominio.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID> {
}
