package io.github.marrafon91.planeja_api.dominio.cartao;

import io.github.marrafon91.planeja_api.dominio.cartao.model.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartaoRepository extends JpaRepository<CartaoEntity, UUID> {
}
