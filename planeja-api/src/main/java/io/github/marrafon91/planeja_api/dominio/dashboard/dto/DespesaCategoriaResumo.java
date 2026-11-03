package io.github.marrafon91.planeja_api.dominio.dashboard.dto;

import java.math.BigDecimal;

public record DespesaCategoriaResumo(
        String categoria,
        BigDecimal valor
) {
}
