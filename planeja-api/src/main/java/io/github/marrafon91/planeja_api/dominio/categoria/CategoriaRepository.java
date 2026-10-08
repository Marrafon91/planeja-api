package io.github.marrafon91.planeja_api.dominio.categoria;

import io.github.marrafon91.planeja_api.dominio.cartao.model.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID> {

    boolean existsByNome(String nome);

    List<CategoriaEntity> findByAtivoTrue();
}
