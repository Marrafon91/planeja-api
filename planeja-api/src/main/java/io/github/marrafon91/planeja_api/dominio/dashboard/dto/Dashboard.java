package io.github.marrafon91.planeja_api.dominio.dashboard.dto;

import java.util.List;

public record Dashboard(
        String mes,
        ResumoFinanceiro resumo,
        List<DespesaCategoriaResumo> despesasPorCategoria
) {
}
