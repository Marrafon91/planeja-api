package io.github.marrafon91.planeja_api.dominio.lancamento.dto;

import io.github.marrafon91.planeja_api.dominio.lancamento.model.TipoLancamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LancamentoForm(

        @NotNull(message = "Campo Obrigatório")
        UUID categoriaId,

        @NotNull(message = "Campo Obrigatório")
        TipoLancamento tipoLancamento,

        @NotNull(message = "Campo Obrigatório")
        LocalDate data,

        @NotNull(message = "Campo Obrigatório")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        UUID cartaoId
) {

}
